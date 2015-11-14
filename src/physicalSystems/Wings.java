package physicalSystems;

/**
 * Represent the two wings on an Airplane, holds the angle the flaps are positioned.
 * @author Christopher McDonald, Eric Le Fort
 * @version 1.0
 */
public class Wings{
	private double flapPositionL, flapPositionR;
	
	/**
	 * Creates a new set of Wings with standardized angles (0 degrees). 
	 */
	public Wings(){
		flapPositionL = 0;
		flapPositionR = 0;
	}//Constructor()
	
	/**
	 * Creates a new set of Wings with specified angles. 
	 * @param leftWing
	 * @param rightWing
	 */
	public Wings(double leftWing, double rightWing){
		if((-45 >= leftWing && leftWing >= 45) && (-45 >= leftWing && leftWing >= 45)){
			throw new IllegalArgumentException("Wings cannot be further than 45 (or -45) degrees turned.");
		}
		flapPositionL = leftWing;
		flapPositionR = rightWing;
	}//Contructor()
	
	/**
	 * Returns the Difference in Wing angles to find left/right drag. 
	 * @return double
	 */
	public double getDifference(){ return flapPositionL - flapPositionR; }//getDifference()
	
	/**
	 * Returns the average of the Wing angles to find up/down drag.
	 * @return double
	 */
	public double getAverage(){ return (flapPositionL + flapPositionR)/2; }//getAverage()
	
	/**
	 * Sets Left Wing Angle to newAngle
	 * @param newAngle
	 * @throws IllegalArgumentException
	 */
	public void setLeftAngle(double newAngle){
		if(-45 >= newAngle && newAngle >= 45){
			throw new IllegalArgumentException("Wings cannot be further than 45 (or -45) degrees turned.");
		}
		flapPositionL = newAngle;
	}//setLeftAngle()
	
	/**
	 * Sets Right Wing Angle to newAngle
	 * @param newAngle
	 * @throws IllegalArgumentException
	 */
	public void setRightAngle(double newAngle){
		if(-45 >= newAngle && newAngle >= 45){
			throw new IllegalArgumentException("Wings cannot be further than 45 (or -45) degrees turned.");
		}
		flapPositionR = newAngle;
	}//setRightAngle()
	
}//Wings
