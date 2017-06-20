package project_db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time {
	public static int getCurrentBEYear() {
		// get 2 digit of Buddhist Era
		return (Integer.parseInt(new SimpleDateFormat("yyyy", Locale.UK)
				.format(new Date(System.currentTimeMillis())).toString()) + 543) % 100;
	}

	public static Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}

	public static String currentTimetoString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.UK)
				.format(getCurrentTime());
	}
}
