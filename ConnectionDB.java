import java.sql.*;

public class ConnectionDB {
	static Connection connect = null;
	static Statement statement = null;
	final static String localhost = "192.168.1.249";
	final static String DatabaseName = "STUDENT";
	final static String Username = "sa";
	final static String Password = "Team*2017";

	public static void connect() {
		try {
			String server = String.format(
					"jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s",
					localhost, DatabaseName, Username, Password);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connect = DriverManager.getConnection(server);
			if (connect != null) {
				// System.out.println("Database Connected.");
			} else {
				System.err.println("Database Connect Failed.");
			}
			statement = connect.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		try {
			connect.close();
			// System.out.println("Database Disconnected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
