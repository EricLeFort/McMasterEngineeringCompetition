package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import physicalSystems.*;

public class GPSTest {

	@Test
	public void testUpdateLocation() {
		//Wings wings = new Wings();
		Engine[] engine = new Engine[2];
		GPS gps = new GPS();
		final double MAXRPM = 10000;
		double expectedLatLon = (100/3.6)*Math.sin(45*Math.PI/180)*.1;
		
		engine[0] = new Engine(MAXRPM);
		engine[1] = new Engine(MAXRPM);
		engine[0].setCurrentRPM(2000);
		engine[1].setCurrentRPM(2000);
		
		gps.updateLocation(45, 100, 0.1);
		assertEquals(gps.getLat(),expectedLatLon/3.6,.01);
		assertEquals(gps.getLon(),expectedLatLon/3.6,.01);
		
		gps.updateLocation(180, 300, 0.01);
		assertEquals(gps.getLat(),expectedLatLon,.001);
		assertEquals(gps.getLon(),-478/75,.001);
		
		try {
			gps.updateLocation(180, -300, 0.01);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Speed Test
		}
		
		try {
			gps.updateLocation(-180, 300, 0.01);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Speed Test
		}
		
		try {
			gps.updateLocation(180, 300, -0.01);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Speed Test
		}
	}
	
	@Test
	public void testUpdateAltitude() {
		Wings wings = new Wings();
		Engine[] engine = new Engine[2];
		GPS gps = new GPS();
		final double MAXRPM = 10000;
		engine[0] = new Engine(MAXRPM);
		engine[1] = new Engine(MAXRPM);
		
	}
}
