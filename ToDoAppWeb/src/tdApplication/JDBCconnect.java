package tdApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCconnect {

	private static String databaseName = "todohibernate";
	private static String url = "jdbc:mysql://localhost:3306/" + databaseName;
	private static String username = "root";
	private static String password = "#g&C02232019";
	
	public static Connection getConnection() throws SQLException
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
}
