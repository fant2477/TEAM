import java.sql.*;
import java.io.File;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class RunningDocument {
	String currentUser;

	Connection connect;
	Statement statement;

	int lastestYear = getLastID() / 10000;

	public RunningDocument(String currentUser) {
		this.currentUser = currentUser;
	}

	public int getCurrentYear() {
		// return 2 digit of year in <th>
		return Calendar.getInstance().get(Calendar.YEAR) - 1957;
	}

	public String getFullCurrentTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
				.format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public int getLastID() {
		int result = 0;
		connect = ConnectionDB.connect();
		try {
			statement = connect.createStatement();
			String sql = "SELECT MAX(ID) FROM History";
			ResultSet rs = this.statement.executeQuery(sql);
			// int currentValue = rs.getInt("ID");
			if (rs.next()) {
				result = rs.getInt(1);
			}
			if (getCurrentYear() * 1000 > result) {
				rs.close();
				return getCurrentYear() * 1000;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * if (result % 10000 == 9999) { return -1; } else { return result; }
		 */
		return result;
	}

	public void addFile(String DocPath) {
		addFile(DocPath, "");
	}

	public void addFile(String DocPath, String detail) {
		File f = new File(DocPath);
		if (f.exists()) {
			connect = ConnectionDB.connect();
			try {
				this.statement = this.connect.createStatement();
				String currentTime = this.getFullCurrentTime();
				String sql = String
						.format("INSERT INTO Document VALUES(%d, '%s', '%s', '%s', '%s', '%s')",
								getLastID() + 1, f.getName(), currentTime,
								this.currentUser, DocPath, detail);
				System.out.println(sql);
				this.statement.executeUpdate(sql);
				System.out.println("Added new file Correctly.");
				this.addLog("Added", f.getName(), getLastID() + 1, DocPath,
						currentTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lastestYear = this.getCurrentYear();
			ConnectionDB.disconnect(connect);
		} else {
			System.out.println("File doesn't exits.");
		}
	}

	public void deleteFile(String DocPath) {
		File f = new File(DocPath);
		if (f.exists()) {
			connect = ConnectionDB.connect();
			try {
				this.statement = this.connect.createStatement();
				// get ID of file in Database.
				String sql = String.format(
						"SELECT ID FROM Document WHERE Path = '%s'", DocPath);

				System.out.println(sql);
				ResultSet rs = this.statement.executeQuery(sql);
				int id = 0;
				if (rs.next()) {
					id = rs.getInt("ID");
					System.out.println("ID: " + id);
				} else {
					System.out.println("No row.");
				}

				rs.close();

				// Delete that file.
				sql = String.format("DELETE FROM Document WHERE Path = '%s'",
						DocPath);
				String currentTime = this.getFullCurrentTime();
				this.statement.executeUpdate(sql);
				System.out.println("deleted file Correctly.");

				// Add log
				this.addLog("Deleted", f.getName(), id, DocPath, currentTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionDB.disconnect(connect);
		} else {
			System.out.println("File doesn't exits.");
		}
	}

	public void addLog(String status, String filename, int id, String path,
			String currentTime) {
		connect = ConnectionDB.connect();
		try {
			this.statement = this.connect.createStatement();
			String sql = String
					.format("INSERT INTO History VALUES('%s', '%s %s by %s', %d, '%s')",
							currentTime, status, filename, currentUser, id,
							path);
			this.statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect(connect);
	}

	public void getAllHistory() {
		connect = ConnectionDB.connect();
		try {
			this.statement = this.connect.createStatement();
			String sql = String
					.format("SELECT ID, Description, Time FROM History");
			ResultSet rs = this.statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String descript = rs.getString("Description");
				Time t = rs.getTime("Time");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Descript: " + descript);
				System.out.println(", Time: " + t);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect(connect);
	}
	
}
