package softwareSystems;
/**
 * @author Eric Le Fort, Christopher McDonald
 * @version 1.0
 */
import physicalSystems.*;
import java.sql.SQLException;

public class AircraftController{
	private static Airplane airplane;

	public static void main(String... args){
		Engine[] engines = new Engine[]{
				new Engine(7500),
				new Engine(7500),
				new Engine(7500),
				new Engine(7500)
		};
		try{
			airplane = new Airplane(engines, new Wings(), new Gyrocompass(), new GPS(), 250, 39000, 0.1);
			GUI gui = new GUI(airplane);
			airplane.setGUI(gui);

			new Thread(airplane).start();
		}catch(SQLException sqle){ sqle.getMessage(); }
	}//main()

}//AircraftController
