package physicalSystems;
/**
 * @author Eric Le Fort
 * @version 1.0
 */

public class Airplane implements Runnable{
	private Engine[] engines;
	private Wings wings;
	private GPS gps;
	private Gyrocompass gyrocompass;
	private int currentSpeed;
	private boolean seatbeltsOn, inFlight;

	public Airplane(Engine engines, Wings wings){
		//TODO implement
	}//Constructor
	
	@Override
	public void run(){
		while(inFlight){
			//TODO implement
			try{ Thread.sleep(100); }catch(InterruptedException ie){ System.out.println(ie.getMessage()); }		//Waits 100 milliseconds to move.
		}
	}//run()

	/**
	 * Toggles the state of the seatbelt sign. If it is on it will be turned off and vice versa.
	 */
	public void toggleSeatbeltsSign(){
		if(seatbeltsOn){
			seatbeltsOn = false;
		}else{
			seatbeltsOn = true;
		}
	}//toggleSeatbeltsSign()

}//Airplane
