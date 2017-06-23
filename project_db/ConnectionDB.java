package project_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionDB {

    static Connection connect = null;
    static Statement statement = null;

    public static void connect() {
        final String localhost = "192.168.1.249";
        final String DatabaseName = "STUDENT";
        final String Username = "sa";
        final String Password = "Team*2017";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect =
                    DriverManager.getConnection(
                            String.format(
                                    "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s",
                                    localhost, DatabaseName, Username, Password));
            if (connect == null) {
                throw new java.lang.Error("Database Connect Failed.");
            }
            statement = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connect != null) {
                connect.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
