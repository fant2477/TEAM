import java.sql.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RunningDocument {
	String currentUser;

	int lastestYear = getLastID() / 10000;

	public RunningDocument(String currentUser) {
		this.currentUser = currentUser;
	}

	public static int getCurrentThaiYear() {
		// return 2 digit of year in <th>
		return Calendar.getInstance().get(Calendar.YEAR) - 1957;
	}

	public static String getFullCurrentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(
				new Date()).toString();
	}

	public int getLastID() {
		int result = getCurrentThaiYear() * 1000;
		ConnectionDB.connect();
		try {
			String sql = "SELECT MAX(ID) FROM History";
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			// currentValue = rs.getInt("ID");
			if (rs.next()) {
				int max = rs.getInt(1);
				if (result > max) {
					return result;
				} else {
					return max;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void addFile(String DocPath) {
		addFile(DocPath, "");
	}

	public void addFile(String DocPath, String detail) {
		File f = new File(DocPath);
		if (f.exists()) {
			ConnectionDB.connect();
			try {
				String currentTime = RunningDocument.getFullCurrentTime();
				String sql = String
						.format("INSERT INTO Document VALUES(%d, '%s', '%s', '%s', '%s', '%s')",
								getLastID() + 1, f.getName(), currentTime,
								this.currentUser, DocPath, detail);
				ConnectionDB.statement.executeUpdate(sql);
				System.out.println(String.format("Added %s Correctly.",
						f.getName()));
				this.addLog("Added", f.getName(), getLastID() + 1, DocPath,
						currentTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lastestYear = RunningDocument.getCurrentThaiYear();
			ConnectionDB.disconnect();
		} else {
			System.out.println("File doesn't exits.");
		}
	}

	public void deleteFile(String DocPath) {
		File f = new File(DocPath);
		if (f.exists()) {
			ConnectionDB.connect();
			try {
				// get ID of file in Database.
				String sql = String.format(
						"SELECT ID FROM Document WHERE Path = '%s'", DocPath);

				ResultSet rs = ConnectionDB.statement.executeQuery(sql);
				int id = 0;
				if (rs.next()) {
					id = rs.getInt("ID");
					// System.out.println("ID: " + id);
				} else {
					System.err.println("No row.");
				}

				rs.close();

				// Delete that file.
				sql = String.format("DELETE FROM Document WHERE Path = '%s'",
						DocPath);
				String currentTime = RunningDocument.getFullCurrentTime();
				ConnectionDB.statement.executeUpdate(sql);
				System.out.println(String.format("Deleted %s Correctly.",
						f.getName()));

				// Add log
				this.addLog("Deleted", f.getName(), id, DocPath, currentTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionDB.disconnect();
		} else {
			System.out.println("File doesn't exits.");
		}
	}

	public void addLog(String status, String filename, int id, String path,
			String currentTime) {
		ConnectionDB.connect();
		try {
			String sql = String
					.format("INSERT INTO History VALUES('%s', '%s %s by %s', %d, '%s')",
							currentTime, status, filename, currentUser, id,
							path);
			ConnectionDB.statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public static int getMaxLenFilename() {
		int maxLen = 0;
		int currentLen;
		ConnectionDB.connect();
		try {
			String sql = String.format("SELECT Filename FROM Document");
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			while (rs.next()) {
				currentLen = rs.getString("Filename").length();
				if (currentLen > maxLen)
					;
				maxLen = currentLen;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
		return maxLen;
	}

	public void getAllHistory() {
		ConnectionDB.connect();
		try {
			String sql = String
					.format("SELECT ID, Description, Time FROM History ORDER BY Time");
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String descript = rs.getString("Description");
				Timestamp ts = rs.getTimestamp("Time");

				// Display values

				System.out.println("Time: "
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
								.format(ts));
				System.out.print(": " + descript);
				System.out.println(" ID: " + id);

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public void changePassword(String oldPass, String newPass) {
		if (Account.validLogin(currentUser, oldPass)
				&& AccountValidaiton.isValidPass(newPass)) {
			Account.setPassword(currentUser, newPass);
		}
	}

	public void changeName(String newName) {
		if (!newName.isEmpty()) {
			Account.setName(currentUser, newName);
		}
	}

	public void changeSurname(String newName) {
		if (!newName.isEmpty()) {
			Account.setSurname(currentUser, newName);
		}
	}

}
