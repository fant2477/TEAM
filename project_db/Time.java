package project_db;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

class Time {

    static int getCurrentBEYear() {
        // get 2 digit of Buddhist Era
        return java.time.chrono.ThaiBuddhistDate.now().get(java.time.temporal.ChronoField.YEAR)
                % 100;
    }

    static Date getCurrentTime() {
        return new Date(Instant.now().toEpochMilli());
    }

    static String currentTimetoString() {
        return datetoString(getCurrentTime());
    }

    static String datetoString(Date d) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                .withZone(ZoneId.of("Asia/Bangkok"))
                .format(d.toInstant());
        //return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(d);
    }

    static String datetoReadableString(Date d) {
        return DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")
                .withZone(ZoneId.of("Asia/Bangkok"))
                .format(d.toInstant());
    }
}
