package physicalSystems;
/**
 * @author Eric Le Fort
 * @version 1.0
 */
import softwareSystems.GUI;
import SQLTools.JDBConnection;

import java.sql.SQLException;

public class Airplane implements Runnable{
	private final double minClimbSpeed;
	private JDBConnection jdbc;
	private GUI gui;
	private Engine[] engines;
	private Wings wings;
	private GPS gps;
	private Gyrocompass gyrocompass;
	private double samplingTime, speed, acceleration;
	private int cruisingAltitude, tokens;
	private boolean seatbeltsOn, inFlight;

	public Airplane(Engine[] engines, Wings wings, Gyrocompass gyrocompass, GPS gps, double minClimbSpeed,
			int cruisingAltitude, double samplingTime) throws SQLException{
		this.engines = engines;
		this.wings = wings;
		this.gyrocompass = gyrocompass;
		this.gps = gps;
		this.minClimbSpeed = minClimbSpeed;
		this.cruisingAltitude = cruisingAltitude;
		this.samplingTime = samplingTime;
		speed = acceleration = 0;
		seatbeltsOn = false;
		inFlight = true;
		tokens = 0;

		jdbc = new JDBConnection("jdbc:mysql://localhost:8889/mysql", "root", "root", "mysql");
		jdbc.dropTable("FlightLog");
		jdbc.createTable("FlightLog",
				new String[]{"ID", "LAT", "LON", "ALT"},
				new String[]{"INT NOT NULL", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)"},
				new String[]{});
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
				Thread.sleep(5);
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
		boolean trigger = true;
		wings.setLeftAngle(-45);
		wings.setRightAngle(-45);

		for(int i = 0; i < engines.length; i++){
			engines[i].turnOn();
			engines[i].setCurrentRPM(engines[i].getMaxRPM());
		}

		while(gps.getAltitude() < altitude - 10000){								//Releases the aircraft when it is close to altitude.
			move();

			if(gyrocompass.getPitch() > 45 && trigger){
				wings.setLeftAngle(0);
				wings.setRightAngle(0);
				trigger = false;
			}

			try{
				Thread.sleep(5);
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
		double oldSpeed = speed, averageRPM = 0;
		tokens++;
		for(int i = 0; i < engines.length; i++){
			averageRPM += engines[i].getCurrentRPM();
		}
		averageRPM /= (engines.length*5);
		speed += (averageRPM - oldSpeed)*.01; 
		acceleration = (speed - oldSpeed) / samplingTime;

		gps.updateAltitude(gyrocompass.getPitch(), speed, minClimbSpeed, samplingTime);
		gps.updateLocation(gyrocompass.getDirection(), speed, samplingTime);

		gyrocompass.updateDirection(acceleration, speed, wings.getDifference(), samplingTime);
		gyrocompass.updatePitch(wings.getAverage(), speed, samplingTime);

		if(tokens % 100 == 0){
			logData();
		}
		if(tokens % 25 == 0){
			gui.updateLbls(gps.getAltitude(), gyrocompass.getDirection(), gyrocompass.getPitch(), gps.getLat(),
					gps.getLon(), speed, engines[0].getCurrentRPM(), engines[1].getCurrentRPM(),
					engines[2].getCurrentRPM(), engines[3].getCurrentRPM());
		}
	}//move()

	public void logData(){
		jdbc.insert("FlightLog", new String[]{"ID", "LAT", "LON", "ALT"}, new String[]{"1", Double.toString(gps.getLat()),
				Double.toString(gps.getLon()), Double.toString(gps.getAltitude())});
	}

	public void setGUI(GUI gui){ this.gui = gui; }//setGUI()

	/**
	 * 
	 * @param engineNumber
	 * @param deltaRPM
	 */
	public void setEngineRPM(int engineNumber, double deltaRPM) {
		engines[engineNumber].setCurrentRPM(engines[engineNumber].getCurrentRPM() + deltaRPM);
	}

	/**
	 * 
	 * @param deltaAngle
	 */
	public void setLeftFlap(double deltaAngle) {
		if(!(wings.getLeftAngle() + deltaAngle > 45 || wings.getLeftAngle() + deltaAngle < -45)){
			wings.setLeftAngle(wings.getLeftAngle() + deltaAngle);
		}
	}

	/**
	 * 
	 * @param deltaAngle
	 */
	public void setRightFlap(double deltaAngle) {
		if(!(wings.getRightAngle() + deltaAngle > 45 || wings.getRightAngle() + deltaAngle < -45)){
			wings.setRightAngle(wings.getRightAngle() + deltaAngle);
		}
	}

	public double getLeftFlap() {return wings.getLeftAngle();}

	public double getRightFlap() {return wings.getRightAngle();}
}//Airplane
