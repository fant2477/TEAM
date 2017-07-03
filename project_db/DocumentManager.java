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
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static DocumentDetail getGeneralFile(int id) {
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

    public DocumentHeader createHeader(String subject) {
        return createHeader(subject, null);
    }

    public DocumentHeader createHeader(String subject, String description) {
        try {
            // Add file to DocumentHeader
            int currentID = getLatestID() + 1;
            String t = Time.currentTimetoString();
            String sql =
                    String.format(
                            "INSERT INTO Document_header "
                                    + "VALUES(%d, '%s', %d, %d, '%s', '%s', '%s', '%s')",
                            currentID,
                            subject,
                            currentUser.getUser_ID(),
                            currentUser.getUser_ID(),
                            t,
                            t,
                            description,
                            null);
            ConnectionDB.statement.executeUpdate(sql);

            // Add log
            Log.addLog(
                    t,
                    currentID + ": " + subject + "was added by" + currentUser.getUsername(),
                    currentID);

            return getHeader(currentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSubject(int Doc_header_ID) {
        String name = "";
        try {
            String sql =
                    String.format(
                            "SELECT Doc_header_subject FROM Document_header WHERE Doc_header_ID = %d",
                            Doc_header_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                name = rs.getString("Doc_header_subject");
                rs.close();
                return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void deleteHeader(int Doc_header_ID) {
        String name = getSubject(Doc_header_ID);
        try {
            String t = Time.currentTimetoString();
            String sql =
                    String.format(
                            "DELETE FROM Document_header WHERE Doc_header_ID = %d", Doc_header_ID);
            ConnectionDB.statement.executeUpdate(sql);
            sql =
                    String.format(
                            "DELETE FROM Document_detail WHERE Doc_header_ID = %d", Doc_header_ID);
            ConnectionDB.statement.executeUpdate(sql);
            Log.addLog(t, name + "was deleted by" + getCurrentUser().getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFilename(int Doc_ID) {
        String name = "";
        try {
            String sql =
                    String.format("SELECT Doc_name FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                name = rs.getString("Doc_name");
                rs.close();
                return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void deleteFile(int Doc_ID) {
        try {
            String name = getFilename(Doc_ID);
            String t = Time.currentTimetoString();
            String sql = String.format("DELETE FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
            ConnectionDB.statement.executeUpdate(sql);
            Log.addLog(t, name + "was deleted by" + getCurrentUser().getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                                rs.getString("Doc_header_subject"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"),
                                rs.getString("Doc_header_description"));
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
            Log.addLog(
                    Time.currentTimetoString(),
                    file.getName() + "was start uploading by" + getCurrentUser().getUsername());

            pstmt.setBinaryStream(8, new FileInputStream(file));
            pstmt.executeUpdate();
            pstmt.close();
            // Add log
            Log.addLog(
                    Time.currentTimetoString(),
                    file.getName() + "was uploaded successfully by" + getCurrentUser().getUsername());

            System.out.println(String.format("Added %s Correctly.", file.getName()));

            // return DocDetail
            sql = "SELECT MAX(Doc_ID) FROM Document_detail";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            rs.close();

            // Update Header
            this.updateHeaderModified(d);

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
                        name + "start downloading by" + DocumentManager.currentUser.getUsername());

                fileBytes = rs.getBytes("Data_file");
                rs.close();

                File target = new File(targetPath, id + name);
                OutputStream targetFile = new FileOutputStream(target.getPath());

                targetFile.write(fileBytes);
                targetFile.close();
                if (target.exists()) {
                    System.out.println("downloaded successfully");
                    Log.addLog(
                            Time.currentTimetoString(),
                            name
                                    + "downloaded successfully by"
                                    + DocumentManager.currentUser.getUsername());
                } else {
                    System.err.println("Download Fail ;(");
                    Log.addLog(
                            Time.currentTimetoString(),
                            name + "download failed by" + DocumentManager.currentUser.getUsername());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateHeaderModified(Date currentTime) {
        this.getCurrentHeader().setDate_modified(currentTime);
        this.getCurrentHeader().setUser_ID_modified(getCurrentUser().getUser_ID());
        try {
            String sql =
                    String.format(
                            "UPDATE Document_header "
                                    + "SET User_ID_modified = '%s', "
                                    + "Date_modified = '%s' "
                                    + "WHERE Doc_header_ID = %d",
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
