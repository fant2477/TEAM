package project_connectionDB;

import java.sql.*;

public class AccountManager {

	public Account createNewAccount(String name, String surname,
			String username, String password, String confirmpass) {
		if (AccountValidaiton.isValidUser(username)
				&& AccountValidaiton.isValidPass(password)
				&& AccountValidaiton.confirmPass(password, confirmpass)) {
			return new Account(name, surname, username, password);
		}
		System.err.println("Can't create new user.");
		return null;
	}

	public void addAccount(String name, String surname, String username,
			String password) {
		ConnectionDB.connect();
		try {
			String sql = String.format(
					"INSERT INTO ACCOUNT VALUES('%s', '%s', '%s', '%s')", name,
					surname, username, password);
			ConnectionDB.statement.executeUpdate(sql);
			System.out.printf("Create new account: %s successfully.\n",
					username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public Account getAccount(String username){
		String name;
		String surname;
		String password;
		ConnectionDB.connect();
		try {
			// get filename and path
			String sql = String.format("SELECT * FROM Account WHERE username = '%s'",
					username);
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("name");
				surname = rs.getString("surname");
				password = rs.getString("password");
				rs.close();
				return new Account(name, surname, username, password);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
		throw new java.lang.Error(String.format(
		"Account not found."));
	}

	public void deleteAccount(String username) {
		if (AccountValidaiton.isUsenameTaken(username)) {
			ConnectionDB.connect();
			try {
				String sql = String.format(
						"DELETE FROM Account WHERE username = '%s'", username);
				ConnectionDB.statement.executeUpdate(sql);
				System.out.printf("Delete account: %s Successfully.\n",
						username);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionDB.disconnect();
		} else {
			System.err.println(username + " is not found.");
		}
	}

	public static boolean validLogin(String username, String password) {
		ConnectionDB.connect();
		try {
			String sql = String.format(
					"SELECT password FROM Account WHERE username = '%s'",
					username);
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					System.out.println("Login correctly");
					rs.close();
					ConnectionDB.disconnect();
					return true;
				}
			}
			rs.close();
			ConnectionDB.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void setPassword(String username, String newPass) {
		ConnectionDB.connect();
		try {
			String sql = String.format(
					"UPDATE Account SET password = '%s' WHERE username = '%s'",
					newPass, username);
			ConnectionDB.statement.executeUpdate(sql);
			System.out.println("Password has changed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public static void setName(String username, String newName) {
		ConnectionDB.connect();
		try {
			String sql = String.format(
					"UPDATE Account SET name = '%s' WHERE username = '%s'",
					newName, username);
			ConnectionDB.statement.executeUpdate(sql);
			System.out.printf("Change to %s\n", newName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public static void setSurname(String username, String newName) {
		ConnectionDB.connect();
		try {
			String sql = String.format(
					"UPDATE Account SET surname = '%s' WHERE username = '%s'",
					newName, username);
			ConnectionDB.statement.executeUpdate(sql);
			System.out.printf("Change to %s\n", newName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

}
