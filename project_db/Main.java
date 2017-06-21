package project_db;

import java.time.chrono.ThaiBuddhistDate;

public class Main {
    public static void main(String[] args) {

        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");
        //SQL.deleteAllRecord("Document_detail");

        UserManager x = new UserManager();
        User m = x.getUser(3);
        //m.setUsername("khonfuu");
        System.out.println(ThaiBuddhistDate.now());
        //DocumentManager a = new DocumentManager(m);

    }
}
