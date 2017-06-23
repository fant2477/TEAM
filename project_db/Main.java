package project_db;

public class Main {
    public static void main(String[] args) {
        ConnectionDB.connect();
        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");
        //SQL.deleteAllRecord("Document_detail");

        UserManager x = new UserManager();
        User m = x.getUser("khonfuu");

        DocumentManager a = new DocumentManager(m);
        a.setCurrentHeader(a.getHeader(600001));
        ConnectionDB.disconnect();
    }
}
