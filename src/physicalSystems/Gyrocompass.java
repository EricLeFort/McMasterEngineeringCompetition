package physicalSystems;

/**
 * Emulates a theoretical Gyrocompass. Would normally read in values, in this scenario it will update its own values according
 * to the relevant states passed in.
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */
public class Gyrocompass{
	private double direction, pitch;
	
	/**
	 * Creates new GyroCompass with standardized values.
	 */
	public Gyrocompass(){
		direction = 0;
		pitch = 0;
	}//Constructor()
	
	/**
	 * Creates new GyroCompass with a standardized pitch and a specified direction.
	 * Direction must be within 0 to 360.
	 * @param startDirection - Starting Direction of Airplane
	 * @throws AngleOutOfBoundsException 
	 */
	public Gyrocompass(double startDirection){
		if(startDirection >= 0 && startDirection < 360){											// Runs if 0 -> startDirection -> 360
			direction = startDirection;
			pitch = 0;
		}else{
			throw new IllegalArgumentException("Direction must be within 0 and 360(exclusive).");
		}
	}//Constructor()
	
	/**
	 * Creates new GyroCompass with a specified pitch and a specified direction.
	 * Direction must be within 0 to 360(exclusive) and Pitch must be within -90 and 90.
	 * @param startDirection - degrees, starting Direction of Airplane
	 * @param startPitch - degrees, starting Pitch of Airplane
	 * @throws AngleOutOfBoundsException 
	 */
	public Gyrocompass(double startDirection, double startPitch){
		if( (startDirection >= 0 && startDirection < 360) && (startPitch <= 90 && startPitch >= -90) ){
			direction = startDirection;
			pitch = startPitch;
		}else{
			throw new IllegalArgumentException("Direction must be within 0 and 360(exclusive) and Pitch must be within -90 and 90.");
		}
	}//Constructor()

	/**
	 * Takes the current direction and alters it according to the states passed in.
	 * @param currentAcceleration, m/s/s
	 * @param currentSpeed - km/h, positive
	 * @param flapState - -90 -> 90 degrees, inclusive
	 * @param timePassed - seconds, time since last update, positive
	 */
	public void updateDirection(double currentAcceleration, double currentSpeed, double flapState, double timePassed){
		if(currentSpeed < 0) {
			throw new IllegalArgumentException("Current speed must be positive.");
		} else if(flapState > 90 || flapState < -90) {
			throw new IllegalArgumentException("Flapstate must be between 90 and -90 inclusive.");
		} else if(timePassed < 0) {
			throw new IllegalArgumentException("TimePassed must be a positive value.");
		}
		double c = 0.5;																							//TODO Change to Realistic 
		currentSpeed = currentSpeed / 3.6;
		direction += c * flapState * ((currentSpeed * timePassed) + (currentAcceleration * timePassed * timePassed));
	}//updateDirection()
	
	/**
	 * Takes the current pitch and alters it according to the states passed in.
	 * @param flapState - -90 -> 90 degrees, inclusive
	 * @param currentSpeed - km/h, positive
	 * @param timePassed - seconds, time since last update, positive
	 */
	public void updatePitch(double flapState, double currentSpeed, double timePassed){
		if(currentSpeed < 0) {
			throw new IllegalArgumentException("Current speed must be positive.");
		} else if(flapState > 90 || flapState < -90) {
			throw new IllegalArgumentException("Flapstate must be between 90 and -90 inclusive.");
		} else if(timePassed < 0) {
			throw new IllegalArgumentException("TimePassed must be a positive value.");
		}
		double c = 0.5;																							//TODO Change to Realistic
		pitch += c * flapState * currentSpeed;
	}//updatePitch()
	
	/**
	 * Returns the current value held for pitch in degrees.
	 * 90  - full vertical climb
	 * 0   - flying level
	 * -90 - full vertical dive
	 * @return The current pitch.
	 */
	public double getPitch(){ return pitch; }//getPitch()
	
	/**
	 * Returns the current value held for direction in degrees.
	 * 
	 * N - 0
	 * E - 90
	 * S - 180
	 * W - 270
	 * 
	 * @return The current direction.
	 */
	public double getDirection(){ return direction; }//getDirection()
	
}//Gyrocompass
