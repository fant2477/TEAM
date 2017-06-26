package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Log {

    static void addLog(String currentTime, String filename, String status, String user) {
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log(Time, Event) VALUES('%s', '%s was %s by %s')",
                            currentTime, filename, status, user);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addLog(String currentTime, String filename, String status, String user, int id) {
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log VALUES('%s', '%s was %s by %s', %d)",
                            currentTime, filename, status, user, id);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sortedLog() {
        try {
            String sql =
                    "SELECT * INTO NewLog FROM Event_log ORDER BY Time "
                            + "DROP TABLE Event_log "
                            + "EXEC sp_rename 'NewLog', 'Event_log'";
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getLog() {
        List<String[]> table = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Event_log ORDER BY Time";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            while (rs.next()) {
                String[] row = {Time.datetoString(rs.getTimestamp(1)), rs.getString(2)};
                table.add(row);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void toStr() {
        for (String[] row : Log.getLog()) {
            for (String s : row) {
                System.out.printf("%s ", s);
            }
            System.out.println();
        }
    }
}
