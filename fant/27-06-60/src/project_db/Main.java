package project_db;

import static java.lang.Long.MAX_VALUE;

public class Main {
    public static String toSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        char pre = "KMGTPE".charAt(exp - 1);
        return String.format("%.2f %sB", bytes / Math.pow(1024, exp), pre);
    }

    public static void main(String[] args) {
        ConnectionDB.connect();
        //SQL.deleteAllRecord("Account");
        //SQL.deleteAllRecord("Document_header");
        //SQL.deleteAllRecord("Document_detail");

        UserManager x = new UserManager();
        User m = x.getUser("khonfuu");

        DocumentManager a = new DocumentManager(m);
        a.setCurrentHeader(a.getHeader(600001));

        long q = MAX_VALUE;

        System.out.println(q);

        ConnectionDB.disconnect();
    }
}
