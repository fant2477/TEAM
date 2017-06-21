package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String[]> getLog() {
        List<String[]> table = new ArrayList<>();
        ConnectionDB.connect();
        try {
            String sql = "SELECT * FROM Event_log ORDER BY Time";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            //int nCol = rs.getMetaData().getColumnCount();
            //table = new DocumentDetail[nCol];
            while (rs.next()) {
                String[] row = new String[2];
                row[0] = Time.datetoFullTime(rs.getTimestamp(1));
                row[1] = rs.getString(2);
                table.add(row);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void printString() {
        for (String[] row : Log.getLog()) {
            for (String s : row) {
                System.out.print(s);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
