package project_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionDB {
    private static final String localhost = "192.168.1.249";
    private static final String DatabaseName = "STUDENT";
    private static final String Username = "sa";
    private static final String Password = "Team*2017";
    static Connection connect = null;
    static Statement statement = null;

    public static void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect =
                    DriverManager.getConnection(
                            String.format(
                                    "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s",
                                    localhost, DatabaseName, Username, Password));
            if (connect == null) {
                System.err.println("Database Connect Failed.");
            }
            statement = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connect.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
