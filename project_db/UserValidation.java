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
        final int min = 3;

        if (username.isEmpty()) {
            return "You can't leave this empty.";
        }

        String pattern = String.format("(?).{%d,50}", min);
        if (!username.matches(pattern)) {
            return String.format("Please use between %d and 50 characters.", min);
        }

        pattern = String.format("[a-zA-Z0-9._-]{%d,50}", min);
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

    // use in Text Box of Password
    public static String validPassword(String password) {
        final int min = 4;
        if (password.isEmpty()) {
            return "You can't leave this empty.";
        }

        String pattern = String.format("(?).{%d,}", min);
        if (!password.matches(pattern)) {
            return String.format(
                    "Short passwords are easy to guess. Try one with at least %d characters.", min);
        }

        pattern = String.format("(?).{%d,50}", min);
        if (!password.matches(pattern)) {
            return "Too long passwords. Try one with at less than 51 characters.";
        }

        return "OK";
    }

    public static boolean isValidPass(String password) {
        // return true iff password is valid.
        return UserValidation.validPassword(password).equals("OK");
    }

    public static String ValidConfirmpass(String password, String confirmpass) {
        if (password.equals(confirmpass)) {
            return "";
        }
        return "Your password is't match!";
    }
    // use in Text Box of confirm password
    public static boolean isValidConfirmpass(String password, String confirmpass) {
        // return true if confirm password is correctly.
        return password.equals(confirmpass);
    }

    public static boolean isValidAll(
            String username, String password, String confirmpass, String name, String surname) {
        return isValidName(name)
                && isValidName(surname)
                && isValidUsername(username)
                && isValidPass(password)
                && isValidConfirmpass(password, confirmpass);
    }

    // ====================== user_info ===============
    public static String UserValidUsername(String username, String current_username) {
        if (current_username.equals(username)) {
            return "OK";
        }
        return UserValidation.validUsername(username);
    }

    public static boolean isUserValidUsername(String username, String current_username) {
        return UserValidation.UserValidUsername(username, current_username).equals("OK");
    }

    public static boolean isUserValidAll(
            String username,
            String password,
            String name,
            String surname,
            String current_username) {
        return isValidName(name)
                && isValidName(surname)
                && isUserValidUsername(username, current_username)
                && isValidPass(password);
    }

    // ==================================== user_info end ==================================
    // ======================================== Login ==================================

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
    public static String validPasswordLogin(String username, String password) {
        if (password.isEmpty()) {
            return "You can't leave this empty.";
        } else if (UserValidation.validUsernameLogin(username).equals("OK")
                && !UserValidation.validLogin(username, password)) {
            return "Password won't match!";
        }
        return "";
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
