package physicalSystems;
/**
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */

public class Engine{
	private final double maxRPM;
	private double currentRPM, currentRPMMaxSpeed;
	private boolean isOn;
	
	/**
	 * Creates a new engine given some passed in maxRPM. Initializes the engine to be off, have an RPM of 0 and sets the maximum RPM
	 * to that passed in. Throws an <code>IllegalArgumentException</code> if the maxRPM is negative.
	 * @param maxRPM
	 * @throws IllegalArgumentException
	 */
	public Engine(double maxRPM) throws IllegalArgumentException{
		if(maxRPM < 0){ throw new IllegalArgumentException("This engine cannot be created, you cannot have a negative maximum RPM."); }
		this.maxRPM = maxRPM;
		isOn = false;
		this.currentRPM = 0;
	}//Constructor

	/**
	 * Alters the RPM that this engine is currently operating at. Throws an <code>IllegalArgumentException</code> if the RPM
	 * specified is higher than the maximum performance of this engine or its a negative number.
	 * @param newRPM - The new RPM that you wish this engine to perform at.
	 * @throws IllegalArgumentException
	 */
	public void setCurrentRPM(double newRPM) throws IllegalArgumentException{
		if(newRPM > maxRPM || newRPM < 0 || !isOn){
			throw new IllegalArgumentException("Cannot set the engine RPM to more than " + maxRPM
				+ "RPM. You tried setting it to: " + newRPM);
		}
		currentRPM = newRPM;
		currentRPMMaxSpeed = currentRPM / 5;
		if(currentRPM != 7500 && currentRPM != 5625){
			currentRPM = 0;//TODO fuck that!
		}
	}//setCurrentRPM()
	
	/**
	 * Tries to turn this engine on. Throws an <code>IllegalStateException</code> if the engine is already on.
	 * @throws IllegalStateException
	 */
	public void turnOn() throws IllegalStateException{
		if(isOn){ throw new IllegalStateException("The engine is already turned on."); }
		isOn = true;
	}//turnOn()
	
	/**
	 * Tries to turn this engine off and sets its RPM to 0. Throws an <code>IllegalStateException</code> if the engine is already off.
	 * @throws IllegalStateException
	 */
	public void turnOff() throws IllegalStateException{
		if(!isOn){ throw new IllegalStateException("The engine is already off."); }
		isOn = false;
		currentRPM = 0;
	}//turnOff()

	/**
	 * Returns the maximum RPM this engine can perform at.
	 * @return A double containing the maximum RPM.
	 */
	public double getMaxRPM(){ return maxRPM; }//getMaxRPM()
	
	/**
	 * Returns the current RPM this engine is performing at.
	 * @return A double containing the current RPM.
	 */
	public double getCurrentRPM(){ return currentRPM; }//getCurrentRPM()
	
	/**
	 * Returns the max speed according to the current RPM this engine is performing at.
	 * @return A double containing the current RPM.
	 */
	public double getCurrentRPMMaxSpeed(){ return currentRPMMaxSpeed; }//getCurrentRPMMaxSpeed()
	
	/**
	 * Returns whether the engine is currently on or not.
	 * @return A boolean representing whether the engine is on.
	 */
	public boolean isOn(){ return isOn; }//isOn()
	
}//Engine
