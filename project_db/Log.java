package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Log {

    static void addLog(String currentTime, String eventDetail) {
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log(Time, Event) VALUES('%s', '%s')",
                            currentTime, eventDetail);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addLog(String currentTime, String eventDetail, int id) {
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log VALUES('%s', '%s', %d)",
                            currentTime, eventDetail, id);
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
                table.add(new String[] {Time.datetoString(rs.getTimestamp(1)), rs.getString(2)});
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<String[]> getLog(String textline) {
        List<String[]> table = new ArrayList<>();
        try {
            String sql =
                    "SELECT * FROM Event_log "
                            + "WHERE "
                            + SQL.search(new String[] {"CONVERT(VARCHAR(MAX), Event)"}, textline)
                            + " ORDER BY Time";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            while (rs.next()) {
                table.add(new String[] {Time.datetoString(rs.getTimestamp(1)), rs.getString(2)});
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

    public static void toStr(String textline) {
        for (String[] row : Log.getLog(textline)) {
            for (String s : row) {
                System.out.printf("%s ", s);
            }
            System.out.println();
        }
    }
}
