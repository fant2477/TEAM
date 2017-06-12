import java.sql.*;

public class SQLCommand {
	public static void deleteAllRecord(String table_name) {
		Connection connect = null;
		Statement statement = null;
		try {
			String server = "jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connect = DriverManager.getConnection(server);
			if (connect != null) {
				System.out.println("Database Connected.");
				//this.connect = DriverManager.getConnection(server);
			} else {
				System.out.println("Database Connect Failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			statement = connect.createStatement();
			String sql = String.format("DELETE FROM %s", table_name);
			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
