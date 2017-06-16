package project_connectionDB;

import java.sql.*;
import java.text.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RunningDocument {
	String currentUser;

	public RunningDocument(String currentUser) {
		if (AccountValidaiton.validUserLogin(currentUser) == "OK") {
			this.currentUser = currentUser;
		}
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public static int getCurrentThaiYear() {
		// return 2 digit of year in <th>
		return (Integer.parseInt(new SimpleDateFormat("yyyy", Locale.UK)
				.format(new Date(System.currentTimeMillis())).toString()) + 543) % 100;
	}

	/*
	 * public static SimpleDateFormat getFullCurrentTime() { return new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.UK) .format(new
	 * Date(System.currentTimeMillis())); }
	 */

	public static Date getFullCurrentTime() {
		return new Date(System.currentTimeMillis());
	}

	public static String currentTimetoString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.UK)
				.format(getFullCurrentTime());
	}

	public static String toSize(long bytes) {
		if (bytes < 1024)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(1024));
		char pre = "KMGTPE".charAt(exp - 1);
		return String.format("%.2f %sB", bytes / Math.pow(1024, exp), pre);
	}

	public int getLastID() {
		int currentYear = getCurrentThaiYear();
		int maxLastID = 0;
		ConnectionDB.connect();
		try {
			String sql = "SELECT MAX(ID) FROM History";
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				maxLastID = rs.getInt(1);
				if (currentYear != maxLastID / 10000) {
					return currentYear * 10000;
				} else {
					return maxLastID;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Document createFile(String FilePath) {
		return this.createFile(FilePath, "");
	}

	public Document createFile(String FilePath, String detail) {
		File f = new File(FilePath);
		if (f.exists()) {
			return new Document(getLastID() + 1, f.getName(), null, f.length(),
					currentUser, FilePath, detail);
		}
		System.err.println("File doesn't exits.");
		return null;
	}

	public void addFile(Document file) {
		PreparedStatement pstmt;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(file.getPath()));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}

		ConnectionDB.connect();
		try {
			// Add Data of file to DataFile.
			String sql = ("INSERT INTO DataFile VALUES(?, ?, ?, ?)");
			pstmt = ConnectionDB.connect.prepareStatement(sql);
			pstmt.setInt(1, file.getID());
			pstmt.setString(2, file.getFilename());
			pstmt.setDouble(3, file.getSize());
			pstmt.setBinaryStream(4, fis);
			pstmt.executeUpdate();
			String currentTime = RunningDocument.currentTimetoString();
			pstmt.close();
			System.out.println(String.format("Added %s Correctly.",
					file.getFilename()));

			// Add file to Document
			sql = String
					.format("INSERT INTO Document VALUES(%d, '%s', '%s','%s', '%s', '%s', '%s')",
							file.getID(), file.getFilename(), currentTime,
							RunningDocument.toSize(file.getSize()),
							this.currentUser, file.getPath(),
							file.getDescription());
			ConnectionDB.statement.executeUpdate(sql);

			// Add log
			this.addLog("added", file.getFilename(), file.getID(),
					file.getPath(), currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public void deleteFile(int id) {
		String name = "";
		String path = "";
		ConnectionDB.connect();
		try {
			// get filename and path
			String sql = String.format("SELECT * FROM Document WHERE ID = %d",
					id);
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("Filename");
				path = rs.getString("Path");
				rs.close();
			} else {
				throw new java.lang.Error(String.format(
						"File ID:%d not found.", id));
			}
			// Delete that file.
			sql = String.format("DELETE FROM Document WHERE ID = %d", id);
			String currentTime = RunningDocument.currentTimetoString();
			ConnectionDB.statement.executeUpdate(sql);
			System.out.println(String.format("Deleted %s Correctly.", name));

			// Delete from Data file
			sql = String.format("DELETE FROM Datafile WHERE ID = %d", id);
			ConnectionDB.statement.executeUpdate(sql);

			// Add log
			this.addLog("deleted", name, id, path, currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	public static void getFile(int id, String targetPath) {
		byte[] fileBytes;
		String query;
		try {
			ConnectionDB.connect();
			query = String.format("SELECT * FROM DataFile WHERE ID = %d", id);
			ResultSet rs = ConnectionDB.statement.executeQuery(query);
			if (rs.next()) {
				fileBytes = rs.getBytes("Data");
				String name = rs.getString("Filename");

				OutputStream targetFile = new FileOutputStream(targetPath + id
						+ name);
				rs.close();
				targetFile.write(fileBytes);
				targetFile.close();
			}
			ConnectionDB.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addLog(String status, String filename, int id, String path,
			String currentTime) {
		ConnectionDB.connect();
		try {
			String sql = String
					.format("INSERT INTO History VALUES('%s', %d, '%s was %s by %s', '%s')",
							currentTime, id, filename, status, currentUser,
							path);
			ConnectionDB.statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
	}

	// Unused
	public static int getMaxLenFilename() {
		int maxLen = 0;
		int currentLen;
		ConnectionDB.connect();
		try {
			String sql = String.format("SELECT Filename FROM Document");
			ResultSet rs = ConnectionDB.statement.executeQuery(sql);
			while (rs.next()) {
				currentLen = rs.getString("Filename").length();
				if (currentLen > maxLen) {
					maxLen = currentLen;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDB.disconnect();
		return maxLen;
	}

	public void changePassword(String oldPass, String newPass) {
		if (AccountManager.validLogin(currentUser, oldPass)
				&& AccountValidaiton.isValidPass(newPass)) {
			AccountManager.setPassword(currentUser, newPass);
		}
	}

	public void changeName(String newName) {
		if (!newName.isEmpty()) {
			AccountManager.setName(currentUser, newName);
		}
	}

	public void changeSurname(String newName) {
		if (!newName.isEmpty()) {
			AccountManager.setSurname(currentUser, newName);
		}
	}

}
