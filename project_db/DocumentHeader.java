package project_db;

import java.util.Date;

public class DocumentHeader {
    int Doc_header_ID;
    int User_ID_created;
    int User_ID_modified;
    Date Date_created;
    Date Date_modified;
    String Status;

    public DocumentHeader(
            int doc_header_ID,
            int user_ID_created,
            int user_ID_modified,
            Date date_created,
            Date date_modified) {
        Doc_header_ID = doc_header_ID;
        User_ID_created = user_ID_created;
        User_ID_modified = user_ID_modified;
        Date_created = date_created;
        Date_modified = date_modified;
    }

    public int getDoc_header_ID() {
        return Doc_header_ID;
    }

    public void setDoc_header_ID(int doc_header_ID) {
        Doc_header_ID = doc_header_ID;
    }

    public int getUser_ID_created() {
        return User_ID_created;
    }

    public void setUser_ID_created(int user_ID_created) {
        User_ID_created = user_ID_created;
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

    public void setDate_created(Date date_created) {
        Date_created = date_created;
    }

    public Date getDate_modified() {
        return Date_modified;
    }

    public void setDate_modified(Date date_modified) {
        Date_modified = date_modified;
    }

    @Override
    public String toString() {
        return "DocumentHeader [Doc_header_ID="
                + Doc_header_ID
                + ", User_ID_created="
                + User_ID_created
                + ", User_ID_modified="
                + User_ID_modified
                + ", Date_created="
                + Date_created
                + ", Date_modified="
                + Date_modified
                + "]";
    }
}
