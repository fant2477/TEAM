package project_db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Time {
    static int getCurrentBEYear() {
        // get 2 digit of Buddhist Era
        return (Integer.parseInt(
                                new SimpleDateFormat("yyyy", Locale.UK)
                                        .format(new Date(System.currentTimeMillis())))
                        + 543)
                % 100;
    }

    static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    static String currentTimetoString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.UK).format(getCurrentTime());
    }

    static String datetoFullTime(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.UK).format(d);
    }
}
