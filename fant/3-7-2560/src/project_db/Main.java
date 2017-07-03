package project_db;

import java.io.*;
import java.lang.*;
//import java.time.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ConnectionDB.connect();

        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");

        UserManager x = new UserManager();
        User m = x.getUser("fant");
//
        DocumentManager a = new DocumentManager(m);
        DocumentHeader.toStr();
        
        Log lg = new Log();
        lg.toStr();
//        a.setCurrentHeader(a.getHeader(600004));
//
//        System.out.println(Long.MAX_VALUE);
        
        //a.createHeader("eiei");

        Log.sortedLog();
        ConnectionDB.disconnect();
    }
}
