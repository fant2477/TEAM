package project_db;

import java.sql.ResultSet;

public class UserManager {

    public static User getUser(int User_ID) {
        try {
            String sql = String.format("SELECT * FROM Account WHERE User_ID = %d", User_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                User u =
                        new User(
                                rs.getInt("User_ID"),
                                rs.getString("Username"),
                                rs.getString("Password"),
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("BusinessGroup"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"));
                rs.close();

                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUsername(int User_ID) {
        String user = null;
        try {
            String sql = String.format("SELECT Username FROM Account WHERE User_ID = %d", User_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                user = rs.getString("Username");
                rs.close();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUser(User user) {
        try {
            String sql =
                    String.format(
                            "UPDATE Account "
                                    + "SET Username = '%s', "
                                    + "Password = '%s', "
                                    + "Name = '%s' , "
                                    + "Surname = '%s', "
                                    + "BusinessGroup = '%s', "
                                    + "Date_modified = %s "
                                    + "WHERE User_ID = %d",
                            user.getUsername(),
                            user.getPassword(),
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
            String t = Time.currentTimetoString();
            String sql =
                    String.format(
                            "INSERT INTO Account "
                                    + "(Username, "
                                    + "Password, "
                                    + "Name, "
                                    + "Surname, "
                                    + "BusinessGroup, "
                                    + "Date_created, "
                                    + "Date_modified) "
                                    + "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                            username, password, name, surname, businessGroup, t, t);
            ConnectionDB.statement.executeUpdate(sql);
            System.out.printf("Create new account: %s successfully.\n", username);

            // Get * from Table by ID
            sql = String.format("SELECT * FROM Account WHERE Username = '%s'", username);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int User_ID = rs.getInt("User_ID");
                rs.close();
                return getUser(User_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String username) {
        try {
            String sql = String.format("SELECT * FROM Account WHERE Username = '%s'", username);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                User u =
                        new User(
                                rs.getInt("User_ID"),
                                rs.getString("Username"),
                                rs.getString("Password"),
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("BusinessGroup"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"));
                rs.close();
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(String username) {
        if (UserValidation.isUsernameTaken(username)) {
            try {
                String sql = String.format("DELETE FROM Account WHERE Username = '%s'", username);
                ConnectionDB.statement.executeUpdate(sql);
                System.out.printf("Delete account: %s Successfully.\n", username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(username + " is not found.");
        }
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
}
