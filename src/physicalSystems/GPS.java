package physicalSystems;

/**
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */
public class GPS{
	private double lat, lon, altitude, groundLevel;

	/**
	 * Creates a new GPS with its latitude, longitude, altitude and ground level set to an initial value of 0.
	 */
	public GPS(){
		lat = lon = altitude = groundLevel = 0;
	}//Constructor
	
	/**
	 * Creates a new GPS with its latitude and longitude set to an initial value of 0 
	 * and its altitude and groundLevel set to whatever is passed in.
	 * @param altitude
	 * @param groundLevel
	 */
	public GPS(double altitude, double groundLevel){
		lat = lon = 0;
		this.altitude = altitude;
		this.groundLevel = groundLevel;
	}//Constructor
	
	/**
	 * Creates a new GPS with all of its fields initialized to the corresponding values passed in.
	 * @param lat
	 * @param lon
	 * @param altitude
	 * @param groundLevel
	 */
	public GPS(double lat, double lon, double altitude, double groudLevel){
		this.lat = lat;
		this.lon = lon;
		this.altitude = altitude;
		this.groundLevel = groundLevel;
	}//Constructor
	
	/**
	 * Takes the current latitude and longitude and alters them according to the states passed in over the given interval.
	 * @param direction - degrees, 0 -> 360, not inclusive on the 360 degree end.
	 * @param currentSpeed - km/h, positive
	 * @param timePassed - seconds, the time passed since the last update.
	 */
	public synchronized void updateLocation(double direction, double currentSpeed, double timePassed){
		if(direction >= 360 || direction < 0) {
			throw new IllegalArgumentException("Direction must be within 0 and 360(exclusive).");
		} else if(currentSpeed < 0) {
			throw new IllegalArgumentException("Speed must be positive.");
		} else if(timePassed < 0) {
			throw new IllegalArgumentException("TimePassed must be positive.");
		}

		lon += currentSpeed / 3.6 * Math.cos(direction*Math.PI/180) * timePassed;
		lat += currentSpeed / 3.6 * Math.sin(direction*Math.PI/180) * timePassed;
	}//updateLocation()
	
	/**
	 * Takes the current altitude and alters it according to the states passed in over the given interval.
	 * @param pitch - 
	 * @param currentSpeed - km/h, positive
	 * @param minClimbSpeed- km/h, 
	 * @param timePassed - seconds, the time passed since the last update.
	 */
	public synchronized void updateAltitude(double pitch, double currentSpeed, double minClimbSpeed, double timePassed){
		double difference;
		if(pitch >= 90 || pitch <= -90) {
			throw new IllegalArgumentException("Pitch must be between -90 and 90.");
		} else if(currentSpeed < 0) {
			throw new IllegalArgumentException("Speed must be positive.");
		} else if(timePassed < 0) {
			throw new IllegalArgumentException("TimePassed must be positive.");
		}
		
		if(currentSpeed >= minClimbSpeed){										//If the plane is too slow, no vertical climbing.
			altitude += Math.sin(pitch*Math.PI/180) * currentSpeed / 3.6 * timePassed;
		}else{
			difference = (9.8 - 9.8*(currentSpeed / minClimbSpeed)) * timePassed * timePassed;
			if(altitude - difference <= groundLevel) {
				altitude = groundLevel;
			} else {
				altitude -= difference;
			}
		}
	}//updateAltitude()
	
	/**
	 * Returns the current latitude value represented through a cartesian coordinate system.
	 * @return The current latitude.
	 */
	public synchronized double getLat(){ return lat; }//getLat()
	
	/**
	 * Returns the current longitude value represented through a cartesian coordinate system.
	 * @return The current longitude.
	 */
	public synchronized double getLon(){ return lon; }//getLon()
	
	/**
	 * Returns the current altitude. Assumes the altitude is measured from sea level and can therefore be negative.
	 * @return The current altitude.
	 */
	public synchronized double getAltitude(){ return altitude; }//getAltitude()
	
}//GPS
