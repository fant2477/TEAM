import java.sql.*;

public class Account {

	Connection connect;
	Statement statement;
	
	/*
	public Connection connect() {
		try {
			String server = "jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connect = DriverManager.getConnection(server);
			if (connect != null) {
				System.out.println("Database Connected.");
			} else {
				System.out.println("Database Connect Failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}

	public void disconnect() {
		try {
			connect.close();
			System.out.println("Database Disconnected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */

	public void createNewAccount(String name, String surname, String username,
			String password, String confirmpass) {
		AccountValidaiton acc = new AccountValidaiton();
		if (acc.isValidUser(username) && AccountValidaiton.isValidPass(password)
				&& AccountValidaiton.confirmPass(password, confirmpass)) {
			this.addAccount(name, surname, username, password);
		} else {
			System.err.println("Can't create new user.");
		}
	}

	private void addAccount(String name, String surname, String username,
			String password) {
		connect = ConnectionDB.connect();
		try {
			this.statement = this.connect.createStatement();
			String sql = String.format(
					"INSERT INTO ACCOUNT VALUES('%s', '%s', '%s', '%s')", name,
					surname, username, password);
			System.out.println(sql);
			this.statement.executeUpdate(sql);
			System.out.println("Create new account successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect(connect);
	}

	public void deleteAccount(String username) {
		if (this.isUsenameIn(username)) {
			this.deleteFromUsername(username);
			System.out.println("Delete " + username + " Successfully.");
		} else {
			System.err.println(username + " :not in Database.");
		}
	}

	private void deleteFromUsername(String username) {
		connect = ConnectionDB.connect();
		try {
			this.statement = this.connect.createStatement();
			String sql = String.format(
					"DELETE FROM Account WHERE username = '%s'", username);
			this.statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect(connect);
	}

	public boolean isUsenameIn(String username) {
		connect = ConnectionDB.connect();
		try {
			this.statement = this.connect.createStatement();
			String sql = "SELECT username FROM Account";
			ResultSet rs = this.statement.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("username").equals(username)) {
					rs.close();
					ConnectionDB.disconnect(connect);
					return true;
				}
			}
			rs.close();
			ConnectionDB.disconnect(connect);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isLogin(String username, String password) {
		connect = ConnectionDB.connect();
		try {
			this.statement = this.connect.createStatement();
			String sql = "SELECT password " + "FROM Account "
					+ String.format("WHERE username = '%s'", username);
			ResultSet rs = this.statement.executeQuery(sql);
			if (rs.getString("password").equals(password)) {
				System.out.println("Login correctly");
				rs.close();
				ConnectionDB.disconnect(connect);
				return true;
			}
			rs.close();
			ConnectionDB.disconnect(connect);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
