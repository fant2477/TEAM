package project_db;

import java.util.Date;

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

    public static void toStr() {
        for (DocumentHeader record : View.toListofDocHeader()) {
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

    public static void toStr(String textline) {
        for (DocumentHeader record : View.toListofDocHeader(textline)) {
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
