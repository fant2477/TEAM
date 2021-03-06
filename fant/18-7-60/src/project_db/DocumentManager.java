package project_db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentManager {
    private static User currentUser = null;
    private static DocumentHeader currentHeader = null;

    public DocumentManager(User currentUser) {
        DocumentManager.currentUser = currentUser;
    }

    public static DocumentDetail getFile(int Doc_ID) {
        DocumentDetail d = null;
        try {
            String sql = String.format("SELECT * FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
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

    public static DocumentDetail getGeneralFile(int Doc_ID) {
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
                            Doc_ID);
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
        int currentPrefix = Time.getCurrentPrefixID();
        int LatestID;
        try {
            String sql = "SELECT MAX(ID) FROM Event_log";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                LatestID = rs.getInt(1);
                if (currentPrefix == LatestID / 10000) {
                    return LatestID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPrefix * 10000;
    }

    public DocumentHeader createHeader(String subject) {
        return createHeader(subject, null);
    }

    public DocumentHeader createHeader(String subject, String description) {
        int currentID = getLatestID() + 1;
        try {
            // Add file to DocumentHeader
            String sql =
                    String.format(
                            "INSERT INTO Document_header "
                                    + "VALUES(%d, '%s', %d, %d, %s, %s, '%s', '%s')",
                            currentID,
                            subject,
                            currentUser.getUser_ID(),
                            currentUser.getUser_ID(),
                            Time.currentTime,
                            Time.currentTime,
                            description,
                            null);
            ConnectionDB.statement.executeUpdate(sql);

            // Add log
            Log.addLog(
                    String.format(
                            "(SELECT Date_created FROM Document_header WHERE Doc_header_ID = %d)",
                            currentID),
                    String.format(
                            "%d: %s was added by %s",
                            currentID, subject, currentUser.getUsername()),
                    currentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getHeader(currentID);
    }

    public String getSubject(int Doc_header_ID) {
        String name = "";
        try {
            String sql =
                    String.format(
                            "SELECT Doc_header_subject FROM Document_header "
                                    + "WHERE Doc_header_ID = %d",
                            Doc_header_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                name = rs.getString("Doc_header_subject");
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void deleteHeader(int Doc_header_ID) {
        String name = getSubject(Doc_header_ID);
        try {
            deleteFileFromHeader(Doc_header_ID);
            String sql =
                    String.format(
                            "DELETE FROM Document_header WHERE Doc_header_ID = %d", Doc_header_ID);
            ConnectionDB.statement.executeUpdate(sql);
            Log.addLog(
                    Time.currentTime,
                    String.format(
                            "%d: %s was deleted by %s",
                            Doc_header_ID, name, getCurrentUser().getUsername()));
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    private boolean isFileExists(int Doc_ID) {
        try {
            String sql =
                    String.format("SELECT Doc_ID FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                rs.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteFile(int Doc_ID) {
        if (isFileExists(Doc_ID)) {
            deleteRecordFile(Doc_ID);
        } else {
            System.err.println("File doesn't exist.");
        }
    }

    public void deleteRecordFile(int Doc_ID) {
        try {
            String name = getFilename(Doc_ID);
            String sql = String.format("DELETE FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
            ConnectionDB.statement.executeUpdate(sql);
            Date deleted = Time.getCurrentTime();
            Log.addLog(
                    Time.datetoString(deleted),
                    String.format(
                            "%d: %s was deleted by %s",
                            Doc_ID, name, getCurrentUser().getUsername()));
            this.updateHeaderModified(deleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFileFromHeader(int Doc_header_ID) {
        List<Integer> DocID = new ArrayList<Integer>();
        try {
            String sql =
                    String.format(
                            "SELECT Doc_ID FROM Document_detail WHERE Doc_header_ID = %d",
                            Doc_header_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            while (rs.next()) {
                DocID.add(rs.getInt("Doc_ID"));
            }
            rs.close();
            for (int id : DocID) {
                deleteRecordFile(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DocumentHeader getHeader(int Doc_header_ID) {
        DocumentHeader h = null;
        try {
            String sql =
                    String.format(
                            "SELECT * FROM Document_header WHERE Doc_header_ID = %d",
                            Doc_header_ID);
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
                    String.format(
                            "INSERT INTO Document_detail "
                                    + "(Doc_header_ID, "
                                    + "Doc_name, "
                                    + "Date_created, "
                                    + "Date_modified, "
                                    + "User_ID_created, "
                                    + "User_ID_modified, "
                                    + "Size, "
                                    + "Data_file) "
                                    + "VALUES(?, ?, %s, %s, ?, ?, ?, ?)",
                            Time.currentTime, Time.currentTime);
            pstmt = ConnectionDB.connect.prepareStatement(sql, new String[] {"Doc_ID"});
            pstmt.setInt(1, getCurrentHeader().getDoc_header_ID());
            pstmt.setString(2, file.getName());
            pstmt.setInt(3, getCurrentUser().getUser_ID());
            pstmt.setInt(4, getCurrentUser().getUser_ID());
            pstmt.setLong(5, file.length());

            System.out.println(file.getName() + " start uploading.");

            Log.addLog(
                    Time.currentTime,
                    String.format(
                            "%s start uploading by %s",
                            file.getName(), getCurrentUser().getUsername()));

            pstmt.setBinaryStream(6, new FileInputStream(file));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            int Doc_ID = 0;
            if (rs.next()) {
                Doc_ID = rs.getInt(1);
            }
            rs.close();
            pstmt.close();

            System.out.println(String.format("Added %s Correctly.", file.getName()));
            // Add log successfully
            Log.addLog(
                    String.format(
                            "(SELECT Date_created FROM Document_detail WHERE Doc_ID = %d)", Doc_ID),
                    String.format(
                            "%d: %s was uploaded successfully by %s",
                            Doc_ID, file.getName(), getCurrentUser().getUsername()));

            sql =
                    String.format(
                            "SELECT Date_created FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
            rs = ConnectionDB.statement.executeQuery(sql);
            Date uploaded = null;
            if (rs.next()) {
                uploaded = rs.getTimestamp(1);
            }
            rs.close();
            // Update Header
            this.updateHeaderModified(uploaded);

            return getFile(Doc_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.addLog(
                Time.currentTime,
                String.format(
                        "%s was upload failed by %s",
                        file.getName(), getCurrentUser().getUsername()));
        return null;
    }

    private DocumentDetail uploadFile(String Doc_name, long size, InputStream Data_file) {
        PreparedStatement pstmt;
        try {
            // Add Data of file to DataFile.
            String sql =
                    String.format(
                            "INSERT INTO Document_detail "
                                    + "(Doc_header_ID, "
                                    + "Doc_name, "
                                    + "Date_created, "
                                    + "Date_modified, "
                                    + "User_ID_created, "
                                    + "User_ID_modified, "
                                    + "Size, "
                                    + "Data_file) "
                                    + "VALUES(?, ?, %s, %s, ?, ?, ?, ?)",
                            Time.currentTime, Time.currentTime);
            pstmt = ConnectionDB.connect.prepareStatement(sql, new String[] {"Doc_ID"});
            pstmt.setInt(1, getCurrentHeader().getDoc_header_ID());
            pstmt.setString(2, Doc_name);
            pstmt.setInt(3, getCurrentUser().getUser_ID());
            pstmt.setInt(4, getCurrentUser().getUser_ID());
            pstmt.setLong(5, size);

            System.out.println(Doc_name + " start uploading.");

            Log.addLog(
                    Time.currentTime,
                    String.format(
                            "%s start uploading by %s", Doc_name, getCurrentUser().getUsername()));

            pstmt.setBinaryStream(6, Data_file);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            int Doc_ID = 0;
            if (rs.next()) {
                Doc_ID = rs.getInt(1);
            }
            rs.close();
            pstmt.close();

            System.out.println(String.format("Added %s Correctly.", Doc_name));
            // Add log successfully
            Log.addLog(
                    String.format(
                            "(SELECT Date_created FROM Document_detail WHERE Doc_ID = %d)", Doc_ID),
                    String.format(
                            "%d: %s was uploaded successfully by %s",
                            Doc_ID, Doc_name, getCurrentUser().getUsername()));

            sql =
                    String.format(
                            "SELECT Date_created FROM Document_detail WHERE Doc_ID = %d", Doc_ID);
            rs = ConnectionDB.statement.executeQuery(sql);
            Date uploaded = null;
            if (rs.next()) {
                uploaded = rs.getTimestamp(1);
            }
            rs.close();
            // Update Header
            this.updateHeaderModified(uploaded);

            return getFile(Doc_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.addLog(
                Time.currentTime,
                String.format(
                        "%s was upload failed by %s", Doc_name, getCurrentUser().getUsername()));
        return null;
    }

    public void downloadFile(int Doc_ID, String targetPath) {
        byte[] fileBytes;
        File target = null;
        String filename = "";
        String downloaded = "";
        try {
            String sql =
                    String.format(
                            "SELECT Doc_name, Data_file FROM Document_detail WHERE Doc_ID = %d",
                            Doc_ID);
            System.out.println("start downloading");

            Log.addLog(
                    Time.currentTimetoString(),
                    String.format(
                            "%d: %s start downloading by %s",
                            Doc_ID, getFilename(Doc_ID), currentUser.getUsername()));

            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                filename = rs.getString("Doc_name");
                fileBytes = rs.getBytes("Data_file");
                target = new File(targetPath, Doc_ID + filename);
                OutputStream targetFile = new FileOutputStream(target.getPath());
                targetFile.write(fileBytes);

                downloaded = Time.currentTimetoString();
                rs.close();
                targetFile.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (target.exists()) {
                System.out.println("downloaded successfully");
                Log.addLog(
                        downloaded,
                        String.format(
                                "%s downloaded successfully by %s",
                                filename, currentUser.getUsername()));
            } else {
                System.err.println("Download Fail ;(");
                Log.addLog(
                        downloaded,
                        String.format(
                                "%s download failed by %s", filename, currentUser.getUsername()));
            }
        }
    }

    public byte[] getDataFile(int Doc_ID) {
        byte[] fileBytes = new byte[4096];
        try {
            String sql =
                    String.format(
                            "SELECT Data_file FROM Document_detail WHERE Doc_ID = %d", Doc_ID);

            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                fileBytes = rs.getBytes("Data_file");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

    public void updateDocumentDetail(DocumentDetail file) {
        try {
            String sql =
                    String.format(
                            "UPDATE Document_detail "
                                    + "SET Doc_header_ID = %d, "
                                    + "Doc_name = '%s', "
                                    + "Date_modified = %s, "
                                    + "User_ID_modified = '%s' "
                                    + "WHERE Doc_ID = %d",
                            file.getDoc_header_ID(),
                            file.getDoc_name(),
                            Time.currentTime,
                            getCurrentUser().getUser_ID(),
                            file.getDoc_ID());
            ConnectionDB.statement.executeUpdate(sql);
            sql =
                    String.format(
                            "SELECT Date_modified FROM Document_detail WHERE Doc_ID = %d",
                            file.getDoc_ID());
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                updateHeaderModified(rs.getTimestamp(1));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHeader(DocumentHeader h) {
        try {
            String sql =
                    String.format(
                            "UPDATE Document_header SET "
                                    + "Doc_header_subject = '%s', "
                                    + "User_ID_modified = '%s', "
                                    + "Date_modified = %s, "
                                    + "Doc_header_description = '%s' "
                                    + "WHERE Doc_header_ID = %d",
                            h.getDoc_header_subject(),
                            getCurrentUser().getUser_ID(),
                            Time.currentTime,
                            h.getDoc_header_description(),
                            h.getDoc_header_ID());
            ConnectionDB.statement.executeUpdate(sql);
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
