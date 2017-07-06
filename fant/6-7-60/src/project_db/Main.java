package project_db;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ConnectionDB.connect();

        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");

        UserManager x = new UserManager();
        User m = x.getUser("khonfuu");

        DocumentManager a = new DocumentManager(m);
        a.setCurrentHeader(a.getHeader(600006));

        Log.toStr("917 start");

        Log.sortedLog();
        ConnectionDB.disconnect();
    }
}
