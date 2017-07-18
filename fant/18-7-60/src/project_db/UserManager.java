package project_db;

import java.sql.ResultSet;
import javax.xml.bind.DatatypeConverter;

public class UserManager {

    static String encrypt(String password) {
        int times = 7;
        try {
            for (int i = 0; i < times; i++) {
                password = DatatypeConverter.printBase64Binary(password.getBytes("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return password;
    }

    static String decrypt(String encoded) {
        int times = 7;
        try {
            for (int i = 0; i < times; i++) {
                encoded = new String(DatatypeConverter.parseBase64Binary(encoded), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return encoded;
    }

    public static User getUser(int User_ID) {
        User u = null;
        try {
            String sql = String.format("SELECT * FROM Account WHERE User_ID = %d", User_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                u =
                        new User(
                                rs.getInt("User_ID"),
                                rs.getString("Username"),
                                decrypt(rs.getString("Password")),
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("BusinessGroup"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"));
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public static String getUsername(int User_ID) {
        String username = null;
        try {
            String sql = String.format("SELECT Username FROM Account WHERE User_ID = %d", User_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                username = rs.getString("Username");
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public static void updateUser(User user) {
        try {
            String sql =
                    String.format(
                            "UPDATE Account SET "
                                    + "Username = '%s', "
                                    + "Password = '%s', "
                                    + "Name = '%s' , "
                                    + "Surname = '%s', "
                                    + "BusinessGroup = '%s', "
                                    + "Date_modified = %s "
                                    + "WHERE User_ID = %d",
                            user.getUsername(),
                            encrypt(user.getPassword()),
                            user.getName(),
                            user.getSurname(),
                            user.getBusinessGroup(),
                            Time.currentTime,
                            user.getUser_ID());
            ConnectionDB.statement.executeUpdate(sql);
            Log.addLog(
                    String.format(
                            "(SELECT Date_modified FROM Account WHERE User_ID = %d)",
                            user.getUser_ID()),
                    String.format(
                            "Information of %d: %s was updated",
                            user.getUser_ID(), user.getUsername()));
            System.out.println("User Data updated. :)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User createNewUser(
            String username,
            String password,
            String confirmpass,
            String name,
            String surname,
            String businessGroup) {
        if (UserValidation.isValidAll(username, password, confirmpass, name, surname)) {
            return insertNewUser(username, password, name, surname, businessGroup);
        } else {
            System.err.println("Can't create new user.");
            return null;
        }
    }

    private User insertNewUser(
            String username, String password, String name, String surname, String businessGroup) {
        try {
            String sql =
                    String.format(
                            "INSERT INTO Account ("
                                    + "Username, "
                                    + "Password, "
                                    + "Name, "
                                    + "Surname, "
                                    + "BusinessGroup, "
                                    + "Date_created, "
                                    + "Date_modified) "
                                    + "VALUES('%s', '%s', '%s', '%s', '%s', %s, %s)",
                            username,
                            encrypt(password),
                            name,
                            surname,
                            businessGroup,
                            Time.currentTime,
                            Time.currentTime);
            ConnectionDB.statement.executeUpdate(sql);
            System.out.printf("Create new account: %s successfully.\n", username);

            sql = String.format("SELECT User_ID FROM Account WHERE Username = '%s'", username);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int User_ID = rs.getInt("User_ID");
                rs.close();
                return getUser(User_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.addLog(Time.currentTime, "Can't create new account.");
        return null;
    }

    public User getUser(String username) {
        User u = null;
        try {
            String sql = String.format("SELECT * FROM Account WHERE Username = '%s'", username);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                u =
                        new User(
                                rs.getInt("User_ID"),
                                rs.getString("Username"),
                                decrypt(rs.getString("Password")),
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("BusinessGroup"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"));
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public void deleteUser(int id) {
        if (UserValidation.isIDTaken(id)) {
            try {
                String sql = String.format("DELETE FROM Account WHERE User_ID = %d", id);
                ConnectionDB.statement.executeUpdate(sql);
                System.out.printf("Delete account id: %d Successfully.\n", id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(id + " is not found.");
        }
    }

    public static String getBusinessGroup(int User_ID) {
        String BusinessGroup = null;
        try {
            String sql =
                    String.format("SELECT BusinessGroup FROM Account WHERE User_ID = %d", User_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                BusinessGroup = rs.getString("BusinessGroup");
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BusinessGroup;
    }
}
