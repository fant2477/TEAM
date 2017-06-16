package project_connectionDB;

import java.sql.ResultSet;

public class AccountValidaiton {

	AccountManager acc = new AccountManager();

	// Name and surname Text Box
	public static boolean isValidName(String username) {
		// return true if user name can use.
		return AccountValidaiton.validName(username).equals("OK");
	}

	public static String validName(String name) {
		// Valid of name and surname
		if (name.isEmpty()) {
			return "You can't leave this empty.";
		}

		String pattern = "[a-zA-Z]+";
		if (!name.matches(pattern)) {
			return "Please use only english letters (a-z, A-Z).";
		}

		return "OK";

	}

	// User name Text Box
	public static String validUsername(String username) {
		// You can use letter, numbers and full stops."

		if (username.isEmpty()) {
			return "You can't leave this empty.";
		}

		String pattern1 = "(?).{3,30}";
		if (!username.matches(pattern1)) {
			return "Please use between 3 and 30 characters.";
		}

		String pattern2 = "[a-zA-Z0-9\\._\\-].{3,30}";
		if (!username.matches(pattern2)) {
			return "Please use only letters (a-z, A-Z), numbers and full stops.";
		}

		if (AccountValidaiton.isUsenameTaken(username)) {
			return "That username is taken. Try another.";
		}

		return "OK";
	}

	public static boolean isUsenameTaken(String username) {
		ConnectionDB.connect();
		try {
			String sql = String.format(
					"SELECT username FROM Account WHERE username = '%s'",
					username);
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				rs.close();
				ConnectionDB.disconnect();
				return true;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
		return false;
	}

	public static boolean isValidUser(String username) {
		// return true if user name can use.
		return AccountValidaiton.validUsername(username).equals("OK");
	}

	// Password Text Box
	public static String validPassword(String password) {
		// Use at least 8 characters

		if (password.isEmpty()) {
			return "You can't leave this empty.";
		}

		String pattern1 = "(?).{4,30}";
		if (!password.matches(pattern1)) {
			return "Short passwords are easy to guess. Try one with at least 4 characters.";
		}

		return "OK";
	}

	public static boolean isValidPass(String password) {
		// return true if password can use.
		return AccountValidaiton.validPassword(password).equals("OK");
	}

	// Confirm pass Text Box
	public static boolean confirmPass(String password, String confirmpass) {
		// return true if confirm password is correctly.
		return password.equals(confirmpass);
	}

	public static boolean isValidLogin(String username,String password) {
		// return true if password can use.
		return AccountValidaiton.validLogin(username,password).equals("OK") && AccountValidaiton.validUserLogin(username).equals("OK");
	}

	// Confirm user name in Login state
	public static String validUserLogin(String username) {
		if (username.isEmpty()) {
			return "You can't leave this empty.";
		}
		else if (!AccountValidaiton.isUsenameTaken(username)) {
			return "Counld't find your account.";
		}
		return "OK";
	}

	public static String validLogin(String username, String password) {
		// Confirm to login.
		if (password.isEmpty()) {
			return "You can't leave this empty.";
		}
		else if (AccountValidaiton.validUserLogin(username) == "OK"
				&& !AccountManager.validLogin(username, password)) {
			return "Wrong password. Try agian.";
		} else {
			return "OK";
		}
	}
}
