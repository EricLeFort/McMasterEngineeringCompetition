package physicalSystems;

/**
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */
public class GPS{
	private double lat, lon, altitude;

	/**
	 * Creates a new GPS with its latitude, longitude and altitude set to an initial value of 0.
	 */
	public GPS(){
		lat = lon = altitude = 0;
	}//Constructor
	
	/**
	 * Creates a new GPS with its latitude and longitude set to an initial value of 0 and its altitude set to whatever is passed in.
	 * @param altitude
	 */
	public GPS(double altitude){
		lat = lon = 0;
		this.altitude = altitude;
	}//Constructor
	
	/**
	 * Creates a new GPS with all of its fields initialized to the corresponding values passed in.
	 * @param lat
	 * @param lon
	 * @param altitude
	 */
	public GPS(double lat, double lon, double altitude){
		this.lat = lat;
		this.lon = lon;
		this.altitude = altitude;
	}//Constructor
	
	/**
	 * Takes the current latitude and longitude and alters them according to the states passed in over the given interval.
	 * @param direction - degrees, 0 -> 360, not inclusive on the 360 degree end.
	 * @param currentSpeed - km/h, positive
	 * @param timePassed - seconds, the time passed since the last update.
	 */
	public void updateLocation(double direction, double currentSpeed, double timePassed){
		if(direction >= 360 || direction < 0) {
			throw new IllegalArgumentException("Direction must be within 0 and 360(exclusive).");
		} else if(currentSpeed < 0) {
			throw new IllegalArgumentException("Speed must be positive.");
		} else if(timePassed < 0) {
			throw new IllegalArgumentException("TimePassed must be positive.");
		}
		lon += currentSpeed * Math.cos(direction) * timePassed;
		lat += currentSpeed * Math.sin(direction) * timePassed;
	}//updateLocation()
	
	/**
	 * Takes the current altitude and alters it according to the states passed in over the given interval.
	 * @param pitch - 
	 * @param currentSpeed - km/h, positive
	 * @param timePassed - seconds, the time passed since the last update.
	 */
	public void updateAltitude(double pitch, double currentSpeed, double timePassed){
		if(pitch >= 90 || pitch <= -90) {
			throw new IllegalArgumentException("Pitch must be between -90 and 90.");
		} else if(currentSpeed < 0) {
			throw new IllegalArgumentException("Speed must be positive.");
		} else if(timePassed < 0) {
			throw new IllegalArgumentException("TimePassed must be positive.");
		}
		altitude += Math.sin(pitch) * currentSpeed * timePassed;
	}//updateAltitude()
	
	/**
	 * Returns the current latitude value represented through a cartesian coordinate system.
	 * @return The current latitude.
	 */
	public double getLat(){ return lat; }//getLat()
	
	/**
	 * Returns the current longitude value represented through a cartesian coordinate system.
	 * @return The current longitude.
	 */
	public double getLon(){ return lon; }//getLon()
	
	/**
	 * Returns the current altitude. Assumes the altitude is measured from sea level and can therefore be negative.
	 * @return The current altitude.
	 */
	public double getAltitude(){ return altitude; }//getAltitude()
	
}//GPS
