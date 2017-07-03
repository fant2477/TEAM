package project_db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time {

	public static int getCurrentBEYear() {
        // get 2 digit of Buddhist Era
        return Integer.parseInt(
                new SimpleDateFormat("yy", new Locale("th", "TH")).format(new Date()));
    }

	public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
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
