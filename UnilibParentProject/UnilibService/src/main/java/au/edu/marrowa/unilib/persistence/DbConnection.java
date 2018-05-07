/**
 * 
 */
package au.edu.marrowa.unilib.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author peter
 *
 */
public class DbConnection {

	private Connection con;

	private String dbName;
	private String dbHost;
	private String dbUsername; 
	private String dbPassword;
	private int dbPort;

	/**
	 * 
	 */
	public DbConnection() {
		dbName = "unilibdb";
		dbHost = "localhost";
		dbUsername = "unilib";
		dbPassword = "unilib@2018";
		dbPort = 5432;
	}

	public  Connection getJdbcConnection() {
		String dbURL;
		dbURL = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
		// Loading underlying JDBC driver
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("----- error 1 " + e.getMessage());
		} catch (SQLException ex) {
			System.out.println("------ error 2 " + ex.getMessage());
		}
		return con;
	} 

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.print("Cnnection is : " + new DbConnection().getJdbcConnection()); 

	}

}
