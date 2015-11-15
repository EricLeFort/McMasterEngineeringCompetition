package physicalSystems;
/**
 * @author Eric Le Fort
 * @version 1.0
 */

public class Airplane implements Runnable{
	private final double maxSpeed;
	private final double minClimbSpeed;
	private Engine[] engines;
	private Wings wings;
	private GPS gps;
	private Gyrocompass gyrocompass;
	private double samplingTime, speed, acceleration;
	private int cruisingAltitude;
	private boolean seatbeltsOn, inFlight;

	public Airplane(Engine[] engines, Wings wings, Gyrocompass gyrocompass, GPS gps, double maxSpeed, double minClimbSpeed,
			int cruisingAltitude, double samplingTime){
		this.engines = engines;
		this.wings = wings;
		this.gyrocompass = gyrocompass;
		this.gps = gps;
		this.maxSpeed = maxSpeed;
		this.minClimbSpeed = minClimbSpeed;
		this.cruisingAltitude = cruisingAltitude;
		this.samplingTime = samplingTime;
		speed = acceleration = 0;
		seatbeltsOn = false;
		inFlight = true;
	}//Constructor
	
	@Override
	public void run(){
		takeOff(cruisingAltitude);
		
		while(inFlight){
			move();																	//Updates the location of the aircraft.
			
			if(gps.getAltitude() <= gps.getGroundLevel()){							//Checks to see if the aircraft is still in flight.
				inFlight = false;
			}
			
			try{
				System.out.println("Altitude: " + gps.getAltitude() + "   X: " + gps.getLon() + "   Y: " + gps.getLat());
				Thread.sleep((long)samplingTime * 1000);
			}catch(InterruptedException ie){ System.out.println(ie.getMessage()); }	//Waits 100 milliseconds to move.
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

	/**
	 * Emulates the plane starting its engines, going down the track and building speed, taking off, climbing until reaching its desired
	 * altitude and then levelling out and lowering engine speed.
	 * @param altitude - metres, double representing the desired altitude.
	 */
	public void takeOff(double altitude){
		wings.setLeftAngle(-45);
		wings.setRightAngle(-45);
		
		for(int i = 0; i < engines.length; i++){
			engines[i].turnOn();
			engines[i].setCurrentRPM(engines[i].getMaxRPM());
		}
		
		while(gps.getAltitude() < altitude){
			move();
			try{
				Thread.sleep((long)samplingTime * 1000);
			}catch(InterruptedException ie){ System.out.println(ie.getMessage()); }	//Waits 100 milliseconds to move.
		}
		
		for(int i = 0; i < engines.length; i++){
			engines[i].setCurrentRPM(engines[i].getMaxRPM() * 3 / 4);
		}
		wings.setLeftAngle(0);
		wings.setRightAngle(0);
	}//takeOff()
	
	/**
	 * Allows the sensors to update the aircraft's location based on the state of various other sensors.
	 */
	public void move(){
		double oldSpeed = speed;
		for(int i = 0; i < engines.length; i++){
			speed += engines[i].getCurrentRPMMaxSpeed() / 120 / samplingTime;
			if(speed > maxSpeed) {
				speed = maxSpeed;
			}
		}
		acceleration = (speed - oldSpeed) / samplingTime;
		
		gps.updateAltitude(gyrocompass.getPitch(), speed, minClimbSpeed, samplingTime);
		gps.updateLocation(gyrocompass.getDirection(), speed, samplingTime);
		
		gyrocompass.updateDirection(acceleration, speed, wings.getDifference(), samplingTime);
		gyrocompass.updatePitch(wings.getAverage(), speed, samplingTime);
	}//move()
	
}//Airplane
