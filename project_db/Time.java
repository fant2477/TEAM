package project_db;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time {
    public static final String currentTime = "CURRENT_TIMESTAMP";

    public static int getCurrentBEYear() {
        // get 2 digit of Buddhist Era
        int BE = 0;
        try {
            String sql = "SELECT (YEAR(CURRENT_TIMESTAMP) + 43) % 100";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                BE = rs.getInt(1);
                rs.close();
            }
            return BE;
        } catch (Exception e) {
            return Integer.parseInt(
                    new SimpleDateFormat("yy", new Locale("th", "TH")).format(new Date()));
        }
    }

    public static Date getCurrentTime() {
        Date d = null;
        try {
            String sql = "SELECT CURRENT_TIMESTAMP";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                d = rs.getTimestamp(1);
                rs.close();
            }
            return d;
        } catch (Exception e) {
            return new Date(System.currentTimeMillis());
        }
    }

    public static String currentTimetoString() {
        return datetoString(getCurrentTime());
    }

    public static String datetoString(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("en", "TH")).format(d);
    }

    public static String datetoReadableString(Date d) {
        return new SimpleDateFormat("dd MMM yyyy HH:mm:ss", new Locale("en", "TH")).format(d);
    }
}
