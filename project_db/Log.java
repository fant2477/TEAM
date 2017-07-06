package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Log {

    static void addLog(String currentTime, String eventDetail) {
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(currentTime);
            currentTime = String.format("'%s'", currentTime);
        } catch (Exception e) {
        }
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log(Time, Event) VALUES(%s, '%s')",
                            currentTime, eventDetail);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addLog(String currentTime, String eventDetail, int id) {
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(currentTime);
            currentTime = String.format("'%s'", currentTime);
        } catch (Exception e) {
        }
        try {
            String sql =
                    String.format(
                            "INSERT INTO Event_log VALUES(%s, '%s', %d)",
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

    public static List<String[]> getLog(int pageNo, int pageMax) {
        return getLog(pageNo, pageMax, "", "Time");
    }

    public static List<String[]> getLog(int pageNo, int pageMax, String searchLine, String order) {
        List<String[]> table = new ArrayList<>();
        try {
            String sql =
                    View.paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    String.format(
                                            "* FROM Event_log WHERE %s ",
                                            View.search(
                                                    new String[] {"CONVERT(VARCHAR(MAX), Event)"},
                                                    searchLine)))
                            + String.format("ORDER BY %s", order);
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

    public static void toStr(int pageNo, int pageMax) {
        toStr(pageNo, pageMax, "", "Time");
    }

    public static void toStr(int pageNo, int pageMax, String searchLine, String order) {
        for (String[] row : Log.getLog(pageNo, pageMax, searchLine, order)) {
            for (String s : row) {
                System.out.printf("%s ", s);
            }
            System.out.println();
        }
    }
}
