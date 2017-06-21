package project_db;

import java.util.Arrays;
import java.util.Date;

public class DocumentDetail {
    int Doc_ID;
    int Doc_header_ID;
    String Doc_name;
    Date Date_created;
    Date Date_modified;
    int User_ID_created;
    int User_ID_modified;
    long Size;
    byte[] Data_file;

    public DocumentDetail(
            int doc_ID,
            int doc_header_ID,
            String doc_name,
            Date date_created,
            Date date_modified,
            int user_ID_created,
            int user_ID_modified,
            long size,
            byte[] data_file) {
        Doc_ID = doc_ID;
        Doc_header_ID = doc_header_ID;
        Doc_name = doc_name;
        Date_created = date_created;
        Date_modified = date_modified;
        User_ID_created = user_ID_created;
        User_ID_modified = user_ID_modified;
        Size = size;
        Data_file = data_file;
    }

    public int getDoc_ID() {
        return Doc_ID;
    }

    public void setDoc_ID(int doc_ID) {
        Doc_ID = doc_ID;
    }

    public int getDoc_header_ID() {
        return Doc_header_ID;
    }

    public void setDoc_header_ID(int doc_header_ID) {
        Doc_header_ID = doc_header_ID;
    }

    public String getDoc_name() {
        return Doc_name;
    }

    public void setDoc_name(String doc_name) {
        Doc_name = doc_name;
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

    public long getSize() {
        return Size;
    }

    public void setSize(long size) {
        Size = size;
    }

    public byte[] getData_file() {
        return Data_file;
    }

    public void setData_file(byte[] data_file) {
        Data_file = data_file;
    }

    @Override
    public String toString() {
        return "DocumentDetail [Doc_ID="
                + Doc_ID
                + ", Doc_header_ID="
                + Doc_header_ID
                + ", Doc_name="
                + Doc_name
                + ", Date_created="
                + Date_created
                + ", Date_modified="
                + Date_modified
                + ", User_ID_created="
                + User_ID_created
                + ", User_ID_modified="
                + User_ID_modified
                + ", Size="
                + Size
                + ", Data_file="
                + Arrays.toString(Data_file)
                + "]";
    }
}
