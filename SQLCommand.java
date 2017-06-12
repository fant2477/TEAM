import java.sql.*;

public class SQLCommand {
	public static void deleteAllRecord(String table_name) {
		try {
			// "jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017";
			ConnectionDB.connect();

			// Delete record in table
			String sql = String.format("DELETE FROM %s", table_name);
			ConnectionDB.statement.executeUpdate(sql);

			// Close connection
			ConnectionDB.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
