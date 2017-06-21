package project_db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DocumentManager {
    private static User currentUser = null;
    private static DocumentHeader currentHeader = null;

    public DocumentManager(User currentUser) {
        DocumentManager.currentUser = currentUser;
    }

    public static DocumentDetail getFile(int id) {
        ConnectionDB.connect();
        try {
            String sql = String.format("SELECT * FROM Document_detail WHERE Doc_ID = %d", id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int doc_ID = rs.getInt("Doc_ID");
                int doc_header_ID = rs.getInt("Doc_header_ID");
                String doc_name = rs.getString("Doc_name");
                Date date_created = rs.getTimestamp("Date_created");
                Date date_modified = rs.getTimestamp("Date_modified");
                int user_ID_created = rs.getInt("User_ID_created");
                int user_ID_modified = rs.getInt("User_ID_modified");
                long size = rs.getLong("Size");
                byte[] data_file = rs.getBytes("Data_file");
                rs.close();

                //Add Log
                Log.addLog(
                        Time.currentTimetoString(),
                        doc_name,
                        "accessed",
                        getCurrentUser().getUsername());

                return new DocumentDetail(
                        doc_ID,
                        doc_header_ID,
                        doc_name,
                        date_created,
                        date_modified,
                        user_ID_created,
                        user_ID_modified,
                        size,
                        data_file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.disconnect();
        return null;
    }

    private static User getCurrentUser() {
        return currentUser;
    }

    private DocumentHeader getCurrentHeader() {
        return currentHeader;
    }

    public void setCurrentHeader(DocumentHeader currentHeader) {
        DocumentManager.currentHeader = currentHeader;
    }

    private int getLastID() {
        int currentYear = Time.getCurrentBEYear();
        int maxLastID;
        ConnectionDB.connect();
        try {
            String sql = "SELECT MAX(ID) FROM Event_log";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                maxLastID = rs.getInt(1);
                if (currentYear != maxLastID / 10000) {
                    return currentYear * 10000;
                }
                return maxLastID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public DocumentHeader createHeader() {
        ConnectionDB.connect();
        try {
            // Add file to DocumentHeader
            String e = Time.currentTimetoString();
            int currentID = getLastID() + 1;
            String sql =
                    String.format(
                            "INSERT INTO Document_header VALUES(%d, %d, %d, '%s', '%s', '%s')",
                            currentID,
                            currentUser.getUser_ID(),
                            currentUser.getUser_ID(),
                            e,
                            e,
                            "");
            ConnectionDB.statement.executeUpdate(sql);

            // Add log
            Log.addLog(e, "New Header", "added", currentUser.getUsername(), currentID);

            return getHeader(getLastID() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.disconnect();
        return null;
    }

    public static DocumentHeader getHeader(int id) {
        ConnectionDB.connect();
        try {
            String sql =
                    String.format("SELECT * FROM Document_header WHERE Doc_header_ID = %d", id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int Doc_header_ID = rs.getInt("Doc_header_ID");
                int User_ID_created = rs.getInt("User_ID_created");
                int User_ID_modified = rs.getInt("User_ID_modified");
                Date Date_created = rs.getTimestamp("Date_created");
                Date Date_modified = rs.getTimestamp("Date_modified");
                rs.close();

                return new DocumentHeader(
                        Doc_header_ID,
                        User_ID_created,
                        User_ID_modified,
                        Date_created,
                        Date_modified);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.disconnect();
        return null;
    }

    public DocumentDetail createFile(String FilePath) {
        File f = new File(FilePath);
        if (f.exists()) {
            return insetFile(f);
        }
        System.err.println("File doesn't exits.");
        return null;
    }

    private DocumentDetail insetFile(File file) {
        PreparedStatement pstmt;

        ConnectionDB.connect();
        try {
            /* FileInputStream fis = new FileInputStream(file); */

            // Add Data of file to DataFile.
            String sql =
                    "INSERT INTO Document_detail "
                            + "(Doc_header_ID, "
                            + "Doc_name, "
                            + "Date_created, "
                            + "Date_modified, "
                            + "User_ID_created, "
                            + "User_ID_modified, "
                            + "Size, "
                            + "Data_file) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = ConnectionDB.connect.prepareStatement(sql);
            Date d = Time.getCurrentTime();
            String e = Time.datetoFullTime(d);
            pstmt.setInt(1, getCurrentHeader().getDoc_header_ID());
            pstmt.setString(2, file.getName());
            pstmt.setString(3, e);
            pstmt.setString(4, e);
            pstmt.setInt(5, getCurrentUser().getUser_ID());
            pstmt.setInt(6, getCurrentUser().getUser_ID());
            pstmt.setLong(7, file.length());
            pstmt.setBinaryStream(8, new FileInputStream(file));
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println(String.format("Added %s Correctly.", file.getName()));

            // return DocDeatail
            sql = "SELECT MAX(Doc_ID) FROM Document_detail";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();

            // Update Header
            this.updateHeaderModified(d);

            // Add log
            Log.addLog(e, file.getName(), "Added", getCurrentUser().getUsername());

            return getFile(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.disconnect();
        return null;
    }

    public void downloadFile(int id, String targetPath) {
        byte[] fileBytes;
        String query;
        try {
            ConnectionDB.connect();
            query =
                    String.format(
                            "SELECT Doc_name, Data_file FROM Document_detail WHERE Doc_ID = %d",
                            id);
            ResultSet rs = ConnectionDB.statement.executeQuery(query);
            if (rs.next()) {
                String name = rs.getString("Doc_name");
                fileBytes = rs.getBytes("Data_file");

                OutputStream targetFile = new FileOutputStream(targetPath + id + name);
                rs.close();

                //Add Log
                Log.addLog(
                        Time.currentTimetoString(),
                        name,
                        "start download",
                        DocumentManager.currentUser.getUsername());

                targetFile.write(fileBytes);
                targetFile.close();
                if (!new File(targetPath + id + name).exists()) {
                    System.err.println("Download Fail ;(");
                    Log.addLog(
                            Time.currentTimetoString(),
                            name,
                            "download failed",
                            DocumentManager.currentUser.getUsername());
                } else {
                    Log.addLog(
                            Time.currentTimetoString(),
                            name,
                            "downloaded successfully",
                            DocumentManager.currentUser.getUsername());
                }
            }
            ConnectionDB.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHeaderModified(Date currentTime) {
        this.getCurrentHeader().setDate_modified(currentTime);
        this.getCurrentHeader().setUser_ID_modified(DocumentManager.getCurrentUser().getUser_ID());
        ConnectionDB.connect();
        try {
            String s =
                    "UPDATE Document_header "
                            + "SET User_ID_modified = '%s', "
                            + "Date_modified = '%s', "
                            + "WHERE Doc_header_ID = %d";
            String sql =
                    String.format(
                            s,
                            DocumentManager.getCurrentUser().getUser_ID(),
                            currentTime,
                            this.getCurrentHeader().getDoc_header_ID());
            ConnectionDB.statement.executeUpdate(sql);
            System.out.println("Header Data was updated. :)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.disconnect();
    }
}
