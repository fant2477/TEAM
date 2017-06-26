package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentHeader {
    private int Doc_header_ID;
    private String Doc_header_subject;
    private int User_ID_created;
    private int User_ID_modified;
    private Date Date_created;
    private Date Date_modified;
    private String Doc_header_description;
    private String Status;

    public DocumentHeader(
            int doc_header_ID,
            String doc_header_subject,
            int user_ID_created,
            int user_ID_modified,
            Date date_created,
            Date date_modified) {
        this(
                doc_header_ID,
                doc_header_subject,
                user_ID_created,
                user_ID_modified,
                date_created,
                date_modified,
                null);
    }

    public DocumentHeader(
            int doc_header_ID,
            String doc_header_subject,
            int user_ID_created,
            int user_ID_modified,
            Date date_created,
            Date date_modified,
            String doc_header_description) {
        Doc_header_ID = doc_header_ID;
        Doc_header_subject = doc_header_subject;
        User_ID_created = user_ID_created;
        User_ID_modified = user_ID_modified;
        Date_created = date_created;
        Date_modified = date_modified;
        Doc_header_description = doc_header_description;
    }

    public static List<DocumentHeader> toListofDocHeader() {
        List<DocumentHeader> table = new ArrayList<>();
        try {
            String sql = "SELECT Doc_header_ID FROM Document_header ORDER BY Date_created";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<>();
            while (rs.next()) {
                rowValues.add(rs.getInt(1));
            }
            rs.close();
            for (int i : rowValues) table.add(DocumentManager.getHeader(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void toStr() {
        for (DocumentHeader record : DocumentHeader.toListofDocHeader()) {
            System.out.print(record.getDoc_header_ID());
            System.out.print('\t');
            System.out.print(record.getDoc_header_subject());
            System.out.print('\t');
            System.out.print(UserManager.getUsername(record.getUser_ID_created()));
            System.out.print('\t');
            System.out.print(UserManager.getUsername(record.getUser_ID_modified()));
            System.out.print('\t');
            System.out.print(Time.datetoReadableString(record.getDate_created()));
            System.out.print('\t');
            System.out.print(Time.datetoReadableString(record.getDate_modified()));
            System.out.print('\t');
            System.out.print(record.getDoc_header_description());
            System.out.println();
        }
    }

    public int getDoc_header_ID() {
        return Doc_header_ID;
    }

    public int getUser_ID_created() {
        return User_ID_created;
    }

    public int getUser_ID_modified() {
        return User_ID_modified;
    }

    public void setUser_ID_modified(int user_ID_modified) {
        User_ID_modified = user_ID_modified;
    }

    public Date getDate_created() {
        return Date_created;
    }

    public Date getDate_modified() {
        return Date_modified;
    }

    public void setDate_modified(Date date_modified) {
        Date_modified = date_modified;
    }

    public String getDoc_header_subject() {
        return Doc_header_subject;
    }

    public void setDoc_header_subject(String doc_header_subject) {
        Doc_header_subject = doc_header_subject;
    }

    public String getDoc_header_description() {
        return Doc_header_description;
    }

    public void setDoc_header_description(String doc_header_description) {
        Doc_header_description = doc_header_description;
    }
}
