package project_db;

public class SQL {
    public static void exxcute(String query) {
        try {
            ConnectionDB.statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllRecord(String table_name) {
        try {
            String sql = String.format("DELETE FROM %s", table_name);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
