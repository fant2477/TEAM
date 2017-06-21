package project_db;

public class Main {
    public static void main(String[] args) {
        UserManager x = new UserManager();
        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");
        //SQL.deleteAllRecord("Document_detail");

        User m = x.getUser(3);
        DocumentManager a = new DocumentManager(m);
        System.out.println(m.toString());


    }
}
