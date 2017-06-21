package project_db;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");
        //SQL.deleteAllRecord("Document_detail");

        UserManager x = new UserManager();
        User m = x.getUser(3);
        //m.setUsername("khonfuu");

        //DocumentManager a = new DocumentManager(m);
        DocumentHeader.tabletoString();
    }
}
