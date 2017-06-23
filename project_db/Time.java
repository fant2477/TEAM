package project_db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Time {

    static int getCurrentBEYear() {
        // get 2 digit of Buddhist Era
        try {
            return java.time.chrono.ThaiBuddhistDate.now().get(java.time.temporal.ChronoField.YEAR)
                    % 100;
        } catch (Exception e) {
            return (Integer.parseInt(new SimpleDateFormat("yyyy", Locale.UK).format(new Date()))
                            + 43)
                    % 100;
        }
    }

    static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    static String currentTimetoString() {
        return datetoString(getCurrentTime());
    }

    static String datetoString(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", Locale.UK).format(d);
    }

    static String datetoReadableString(Date d) {
        return new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.UK).format(d);
    }
}
