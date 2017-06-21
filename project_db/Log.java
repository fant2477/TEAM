package project_db;

public class Log {
    public static void addLog(
            String currentTime, String filename, String status, String user, int id) {
        ConnectionDB.connect();
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log VALUES('%s', '%s was %s by %s', %d)",
                            currentTime, filename, status, user, id);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.disconnect();
    }
}
