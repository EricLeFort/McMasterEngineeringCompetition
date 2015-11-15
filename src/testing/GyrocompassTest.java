package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import physicalSystems.*;

public class GyrocompassTest {

	@Test
	public void testUpdateDirection() {
		
		Gyrocompass gCompass = new Gyrocompass();
		
		gCompass.updateDirection(0, 300, 45, 1);
		assertEquals(270, gCompass.getDirection(), .001);
		
		try {
			gCompass.updateDirection(0, -1, 0, 1);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Speed Test
		}
		
		try {
			gCompass.updateDirection(0, 300, -180, 1);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Incorrect Flap Value
		}
		
		try {
			gCompass.updateDirection(0, 300, 0, -1);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Time Test
		}
	}
	
	@Test
	public void testUpdatePitch() {
		
		Gyrocompass gCompass = new Gyrocompass();
		
		gCompass.updatePitch(45, 300, 1);
		assertEquals(-90, gCompass.getPitch(), .001);	// Pitch has gone to maximum of -90 degrees
		
		try {
			gCompass.updatePitch(0, -1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Speed Test
		}
		
		try {
			gCompass.updatePitch(-180, 300, 1);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Incorrect Flap Value
		}
		
		try {
			gCompass.updatePitch(0, 300, -1);
			fail();
		} catch (IllegalArgumentException e) {
			//Exception Caught, Pass Negative Time Test
		}
	}
}
