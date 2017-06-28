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
        DocumentDetail d = null;
        try {
            String sql = String.format("SELECT * FROM Document_detail WHERE Doc_ID = %d", id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                d =
                        new DocumentDetail(
                                rs.getInt("Doc_ID"),
                                rs.getInt("Doc_header_ID"),
                                rs.getString("Doc_name"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getLong("Size"),
                                rs.getBytes("Data_file"));
                //Add Log
                Log.addLog(
                        Time.currentTimetoString(),
                        rs.getString("Doc_name"),
                        "accessed",
                        getCurrentUser().getUsername());
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static DocumentDetail getGenneralFile(int id) {
        DocumentDetail d = null;
        try {
            String sql =
                    String.format(
                            "SELECT Doc_ID, "
                                    + "Doc_header_ID, "
                                    + "Doc_name, "
                                    + "Date_created, "
                                    + "Date_modified, "
                                    + "User_ID_created, "
                                    + "User_ID_modified, "
                                    + "Size "
                                    + "FROM Document_detail WHERE Doc_ID = %d",
                            id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                d =
                        new DocumentDetail(
                                rs.getInt("Doc_ID"),
                                rs.getInt("Doc_header_ID"),
                                rs.getString("Doc_name"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getLong("Size"));
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
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

    private int getLatestID() {
        int currentYear = Time.getCurrentBEYear();
        int LatestID;
        try {
            String sql = "SELECT MAX(ID) FROM Event_log";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                LatestID = rs.getInt(1);
                if (currentYear == LatestID / 10000) {
                    return LatestID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentYear * 10000;
    }

    public DocumentHeader createHeader() {
        try {
            // Add file to DocumentHeader
            String t = Time.currentTimetoString();
            int currentID = getLatestID() + 1;
            String sql =
                    String.format(
                            "INSERT INTO Document_header VALUES(%d, %d, %d, '%s', '%s', '%s')",
                            currentID,
                            currentUser.getUser_ID(),
                            currentUser.getUser_ID(),
                            t,
                            t,
                            "");
            ConnectionDB.statement.executeUpdate(sql);

            // Add log
            Log.addLog(t, "New Header", "added", currentUser.getUsername(), currentID);

            return getHeader(currentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DocumentHeader getHeader(int id) {
        DocumentHeader h = null;
        try {
            String sql =
                    String.format("SELECT * FROM Document_header WHERE Doc_header_ID = %d", id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                h =
                        new DocumentHeader(
                                rs.getInt("Doc_header_ID"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"));
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return h;
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
        try {
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
            String t = Time.datetoString(d);
            pstmt.setInt(1, getCurrentHeader().getDoc_header_ID());
            pstmt.setString(2, file.getName());
            pstmt.setString(3, t);
            pstmt.setString(4, t);
            pstmt.setInt(5, getCurrentUser().getUser_ID());
            pstmt.setInt(6, getCurrentUser().getUser_ID());
            pstmt.setLong(7, file.length());

            // Add log
            Log.addLog(Time.currentTimetoString(), file.getName(), "start uploading", getCurrentUser().getUsername());

            pstmt.setBinaryStream(8, new FileInputStream(file));
            pstmt.executeUpdate();
            pstmt.close();
            // Add log
            Log.addLog(Time.currentTimetoString(), file.getName(), "uploaded successfully", getCurrentUser().getUsername());


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
            Log.addLog(t, file.getName(), "added", getCurrentUser().getUsername());

            return getFile(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void downloadFile(int id, String targetPath) {
        byte[] fileBytes;
        String query;
        try {
            query =
                    String.format(
                            "SELECT Doc_name, Data_file FROM Document_detail WHERE Doc_ID = %d",
                            id);
            ResultSet rs = ConnectionDB.statement.executeQuery(query);
            if (rs.next()) {
                String name = rs.getString("Doc_name");
                //Add Log
                System.out.println("start downloading");
                Log.addLog(
                        Time.currentTimetoString(),
                        name,
                        "start downloading",
                        DocumentManager.currentUser.getUsername());

                fileBytes = rs.getBytes("Data_file");
                rs.close();
                // OutputStream targetFile = new FileOutputStream(targetPath + id + name);
                //String target = Paths.get(targetPath, id + name).toString();
                String target = "10.101.101.10/";
                OutputStream targetFile = new FileOutputStream(target);

                targetFile.write(fileBytes);
                targetFile.close();
                if (!new File(target).exists()) {
                    System.err.println("Download Fail ;(");
                    Log.addLog(
                            Time.currentTimetoString(),
                            name,
                            "download failed",
                            DocumentManager.currentUser.getUsername());
                } else {
                    System.out.println("downloaded successfully");
                    Log.addLog(
                            Time.currentTimetoString(),
                            name,
                            "downloaded successfully",
                            DocumentManager.currentUser.getUsername());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateHeaderModified(Date currentTime) {
        this.getCurrentHeader().setDate_modified(currentTime);
        this.getCurrentHeader().setUser_ID_modified(DocumentManager.getCurrentUser().getUser_ID());
        try {
            String s =
                    "UPDATE Document_header "
                            + "SET User_ID_modified = '%s', "
                            + "Date_modified = '%s' "
                            + "WHERE Doc_header_ID = %d";
            String sql =
                    String.format(
                            s,
                            DocumentManager.getCurrentUser().getUser_ID(),
                            Time.datetoString(currentTime),
                            this.getCurrentHeader().getDoc_header_ID());
            ConnectionDB.statement.executeUpdate(sql);
            System.out.println("Header Data was updated. :)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
