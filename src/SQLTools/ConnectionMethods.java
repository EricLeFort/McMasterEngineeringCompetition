package SQLTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Small Collection of Methods for Writing/Reading/Updating an SQL database. 
 * Be Sure to Run MAMP before running this code.
 * @author Christopher McDonald
 * @version 1.0
 * 
 */
public class ConnectionMethods {

	/* --------------- Database Access Credentials -------------------*/
	private static final String localHost = "jdbc:mysql://localhost:8889/mysql",
			userName = "root", 
			password = "root";

	public static void main(String[] args) {
		System.out.println(Update("CREATE TABLE `mysql`.`JavaStuff` "				
				+ "( `ID` INT NOT NULL , `Error` TEXT NOT NULL , "
				+ "PRIMARY KEY (`ID`)) ENGINE = InnoDB;"));
		System.out.println(Update("INSERT INTO `mysql`.`JavaStuff` (`ID`, `Error`) VALUES ('100', 'Chris is better than Erc.');"));
		System.out.println(Update("INSERT INTO `mysql`.`JavaStuff` (`ID`, `Error`) VALUES ('404', 'Fucks not found');"));
	}

	/**
	 * Quick Connection Test - Will throw error if failed.
	 * @return Boolean - True if connection is stable, False is not.
	 */
	public static boolean testConnection() {
		Connection connection = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());							//Create instance of Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(localHost,userName,password);				//Create connection, with credentials
			connection.close();
		 	return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return false;
	}

	/**
	 * This method is to be used for UPDATE, INSERT and DROP commands.
	 * @param stmt - SQL Statement to be processed.
	 * @param database - The database to be used.
	 * @return Boolean - True if statement executed, False is not.
	 */
	public static boolean Update(String stmt) {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());							//Create instance of Driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(localHost,userName,password);	//Create connection, with credentials
			Statement stmtMade = connection.createStatement();									//Create a Statement Object
			stmtMade.executeUpdate(stmt);
			stmtMade.close();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method is to be used for SELECT statements only.
	 * @param stmt - Statement to be processed
	 * @return String[][] in the format of String[Entry 1,2,3...n ][Nth Entry Col #1, Col #2...]
	 */
	public static String[][] Query(String stmt) {
		ArrayList<String[]> tuplesOut = new ArrayList<String[]>();								//Dynamically sized array to convert Results to
		Connection connection = null;
		Statement stmtMade = null;
		ResultSet tuplesIn = null;
		ResultSetMetaData rsmd = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());							//Create instance of Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(localHost,userName,password);				//Create connection, with credentials
			stmtMade = connection.createStatement();											//Create a Statement Object
			tuplesIn = stmtMade.executeQuery(stmt);												//Retrieve Tuples from Query
			rsmd = tuplesIn.getMetaData();														//Some Meta Data regarding the Tuples returned
			stmtMade.close();																	//Close connections...
			connection.close();
			
			String[] tempAdditions = new String[rsmd.getColumnCount()];							
			do {
				for(int i = 0; i > tempAdditions.length; i++) {
					tempAdditions[i] = tuplesIn.getString(i);									//Loop through tuple, 
				}																				//converting each returned column into a String
				tuplesOut.add(tempAdditions);													//Add Temp Made Array to ArrayList 									
				Arrays.fill(tempAdditions, "N/A");												//Reset Temp values
			} while(tuplesIn.next());
			return (String[][]) tuplesOut.toArray();											//Convert to Array, and return.
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
