public class AccountValidaiton {

	Account acc = new Account();
	
	// Name and surname Text Box
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
	public String validUsername(String username) {
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

		if (this.isUsenameTaken(username)) {
			return "That username is taken. Try another.";
		}

		return "OK";
	}

	boolean isUsenameTaken(String username) {
		return acc.isUsenameIn(username);
	}

	public boolean isValidUser(String username) {
		// return true if user name can use.
		return this.validUsername(username).equals("OK");
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

	// Confirm username in Login state
	public String validUserLogin(String username) {
		if (!this.isUsenameTaken(username)) {
			return "Counld't find your account.";
		}
		return "OK";
	}

	public String validLogin(String username, String password) {
		// Cofirm to login.
		if (this.validUserLogin(username) == "OK"
				&& !acc.isLogin(username, password)) {
			return "Wrong password. Try agian.";
		} else {
			return "OK";
		}
	}
}
