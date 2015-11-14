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
		
		engine[0] = new Engine(MAXRPM);
		engine[1] = new Engine(MAXRPM);
		engine[0].setCurrentRPM(2000);
		engine[1].setCurrentRPM(2000);
		System.out.println(gps.getLat());
		System.out.println(gps.getLon());
		
		gps.updateLocation(45, 100, 0.1);
		assertEquals(1.964185503, gps.getLat(), .001);
		assertEquals(1.964185503, gps.getLon(), .001);
		
		gps.updateLocation(180, 300, 0.1);
		assertEquals(1.964185503, gps.getLat(), .001);
		assertEquals(-478.0/75, gps.getLon(), .01);
		
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
