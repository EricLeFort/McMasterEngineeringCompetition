package softwareSystems;
/**
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */
import physicalSystems.*;

public class AircraftController{
	private static Airplane airplane;

	public static void main(String... args){
		GUI gui = new GUI();
		Engine[] engines = new Engine[]{
				new Engine(7500),
				new Engine(7500),
				new Engine(7500),
				new Engine(7500)
		};
		
		airplane = new Airplane(engines, new Wings(), new Gyrocompass(), new GPS(), 278, 39000, 0.1, gui);
		
		new Thread(airplane).start();
	}//main()
	
}//AircraftController
