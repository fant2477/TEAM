package project_db;

import java.sql.ResultSet;

public class UserValidation {

    // Use in Text Box of Name and surname
    public static String validName(String name) {
        // return "OK" iff name or surname is valid
        if (name.isEmpty()) {
            return "You can't leave this empty.";
        }

        String pattern = "[a-zA-Z]+";
        if (!name.matches(pattern)) {
            return "Please use only english letters (a-z, A-Z).";
        }

        return "OK";
    }

    public static boolean isValidName(String name) {
        // return true iff name or surname is valid
        return UserValidation.validName(name).equals("OK");
    }

    public static boolean isUsernameTaken(String username) {
        try {
            String sql =
                    String.format("SELECT Username FROM Account WHERE Username = '%s'", username);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                rs.close();
                return true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Use in Text Box of username
    public static String validUsername(String username) {
        // You can use letter, numbers and full stops.

        if (username.isEmpty()) {
            return "You can't leave this empty.";
        }

        String pattern = "(?).{3,50}";
        if (!username.matches(pattern)) {
            return "Please use between 3 and 50 characters.";
        }

        pattern = "[a-zA-Z0-9\\._\\-].{3,50}";
        if (!username.matches(pattern)) {
            return "Please use only letters (a-z, A-Z), numbers and full stops.";
        }

        if (UserValidation.isUsernameTaken(username)) {
            return "That username is taken. Try another.";
        }

        return "OK";
    }

    public static boolean isValidUsername(String username) {
        // return true if username is valid.
        return UserValidation.validUsername(username).equals("OK");
    }

    public static String validBusinessGroup(String BusinessGroup) {
        if (BusinessGroup.isEmpty()) {
            return "You can't leave this empty.";
        }

        return "OK";
    }

    public static boolean isValidBusinessGroup(String BusinessGroup) {
        // return true iff password is valid.
        return UserValidation.validBusinessGroup(BusinessGroup).equals("OK");
    }

    // use in Text Box of Password
    public static String validPassword(String password) {

        if (password.isEmpty()) {
            return "You can't leave this empty.";
        }

        String pattern = "(?).{4,}";
        if (!password.matches(pattern)) {
            return "Short passwords are easy to guess. Try one with at least 4 characters.";
        }

        pattern = "(?).{4,50}";
        if (!password.matches(pattern)) {
            return "Too long passwords. Try one with at less than 51 characters.";
        }

        return "OK";
    }

    public static boolean isValidPass(String password) {
        // return true iff password is valid.
        return UserValidation.validPassword(password).equals("OK");
    }

    // use in Text Box of confirm passowrd
    public static boolean isValidConfirmpass(String password, String confirmpass) {
        // return true iff confirm password is correctly.
        return password.equals(confirmpass);
    }

    public static boolean isValidAll(
            String username,
            String password,
            String confirmpass,
            String name,
            String surname,
            String businessGroup) {
        return isValidName(name)
                && isValidName(surname)
                && isValidUsername(username)
                && isValidPass(password)
                && isValidConfirmpass(password, confirmpass)
                && isValidBusinessGroup(businessGroup);
    }

    // use in Text box of username at Login
    public static String validUsernameLogin(String username) {
        if (username.isEmpty()) {
            return "You can't leave this empty.";
        } else if (!UserValidation.isUsernameTaken(username)) {
            return "Couldn't find your account.";
        }
        return "OK";
    }

    // use in Text box of password
    public static String validPasswordLogin(String password) {
        if (password.isEmpty()) {
            return "You can't leave this empty.";
        }
        return "OK";
    }

    // use when click login
    public static boolean validLogin(String username, String password) {
        try {
            String sql =
                    String.format("SELECT Password FROM Account WHERE Username = '%s'", username);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("Password").equals(password)) {
                    System.out.println("Login correctly");
                    rs.close();
                    return true;
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isIDTaken(int id) {
        try {
            String sql = String.format("SELECT User_ID FROM Account WHERE User_ID = %d", id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                rs.close();
                return true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
