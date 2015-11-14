package physicalSystems;
/**
 * Emulates a theoretical gyroscope. Would normally read in values, in this scenario it will update its own values according
 * to the relevant states passed in.
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */

public class Gyroscope{
	private double direction, pitch;

	public void updateDirection(double currentSpeed){
		//TODO implement
	}//updateDirection()
	
	/**
	 * Takes current pitch, alters according to the states passed in.
	 * currentSpeed - Positive.
	 * flapState    - -90 -> 90 inclusive
	 * @param flapState
	 * @param currentSpeed
	 */
	public void updatePitch(double flapState, double currentSpeed){
		//TODO implement
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
	
}//Gyroscope
