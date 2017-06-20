package project_db;

public class SQL {
	public static void exxcute(String query) {
		try {
			ConnectionDB.connect();
			ConnectionDB.statement.executeUpdate(query);
			ConnectionDB.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteAllRecord(String table_name) {
		try {
			ConnectionDB.connect();

			String sql = String.format("DELETE FROM %s", table_name);
			ConnectionDB.statement.executeUpdate(sql);

			ConnectionDB.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
