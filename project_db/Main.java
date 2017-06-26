package project_db;

import java.io.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ConnectionDB.connect();

        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");

        UserManager x = new UserManager();
        User m = x.getUser("khonfuu");

        DocumentManager a = new DocumentManager(m);
        a.setCurrentHeader(a.getHeader(600004));


        DateTimeFormatter formatte =
                DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
                        .withLocale( Locale.UK )
                        .withZone( ZoneId.systemDefault() );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("Asia/Bangkok"));

        Instant instant = Instant.now();
        String output = formatter.format( instant );

        
        Log.sortedLog();
        ConnectionDB.disconnect();
    }
}
