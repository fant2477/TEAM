import java.sql.*;

public class ConnectionDB {
	
	public static Connection connect() {
		Connection connect = null;
		try {
			String server = "jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connect = DriverManager.getConnection(server);
			if (connect != null) {
				System.out.println("Database Connected.");
				return connect;
			} else {
				System.out.println("Database Connect Failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}

	public static void disconnect(Connection connect) {
		try {
			connect.close();
			System.out.println("Database Disconnected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
