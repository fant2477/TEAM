package project_db;

import java.sql.ResultSet;
import java.util.Date;

public class UserManager {
	public User createNewUser(String username, String password,
			String confirmpass, String name, String surname,
			String businessGroup) {
		if (UserValidation.isValidAll(username, password, confirmpass, name,
				surname, businessGroup)) {
			return insertUser(username, password, name, surname, businessGroup);
		} else {
			System.err.println("Can't create new user.");
			return null;
		}
	}

	private User insertUser(String username, String password, String name,
			String surname, String businessGroup) {
		ConnectionDB.connect();
		try {
			String e = Time.currentTimetoString();
			String sql = String
					.format("INSERT INTO Account (Username, Password, Name, Surname, BusinessGroup, Date_created, Date_modified) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
							username, password, name, surname, businessGroup, e, e);
			ConnectionDB.statement.executeUpdate(sql);
			System.out.printf("Create new account: %s successfully.\n",
					username);
			sql = String.format("SELECT * FROM Account WHERE Username = '%s'",
					username);
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				String user = rs.getString("Username");
				rs.close();
				return getUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
		return null;
	}

	public User getUser(String username) {
		ConnectionDB.connect();
		try {
			String sql = String.format("SELECT * FROM Account WHERE Username = '%s'",
					username);
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				int User_ID = rs.getInt("User_ID");
				String user = rs.getString("Username");
	            String pass = rs.getString("Password");
	            String nam = rs.getString("Name");
	            String surnam = rs.getString("Surname");
	            String bG = rs.getString("BusinessGroup");
	            Date date_created = rs.getDate("Date_created");
	            Date date_modified = rs.getDate("Date_modified");
				rs.close();
				return new User(User_ID, user, pass, nam,
						surnam, bG, date_created,
						date_modified) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
		return null;
	}
}
