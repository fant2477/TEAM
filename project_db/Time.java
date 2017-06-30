package project_db;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Time {

    static int getCurrentBEYear() {
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

    static Date getCurrentTime() {
        Date d = null;
        try {
            String sql = "CURRENT_TIMESTAMP";
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

    static String currentTimetoString() {
        return datetoString(getCurrentTime());
    }

    static String datetoString(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(d);
    }

    static String datetoReadableString(Date d) {
        return new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(d);
    }
}
