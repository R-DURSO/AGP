package persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * this class will be use for the connection with BD the script for create it are placed on the other folder
 * 
 *
 */
class JdbcConnection {
	private static String host = "localhost";
	private static String base = "agp";
	private static String user = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://" + host + "/" + base;

	/**
	 * Lazy singleton instance.
	 */
	private static Connection connection;


	public static Connection getConnection() {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());
			}
		}
		return connection;
	}
}
