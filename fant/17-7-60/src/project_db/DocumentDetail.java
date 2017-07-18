package project_db;

import java.util.Date;

public class DocumentDetail {

    private int Doc_ID;
    private int Doc_header_ID;
    private String Doc_name;
    private Date Date_created;
    private Date Date_modified;
    private int User_ID_created;
    private int User_ID_modified;
    private long Size;
    private byte[] Data_file;

    DocumentDetail(
            int doc_ID,
            int doc_header_ID,
            String doc_name,
            Date date_created,
            Date date_modified,
            int user_ID_created,
            int user_ID_modified,
            long size) {
        this(
                doc_ID,
                doc_header_ID,
                doc_name,
                date_created,
                date_modified,
                user_ID_created,
                user_ID_modified,
                size,
                null);
    }

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

    public static void toTable(int pageNo, int pageMax, String searchLine, String order) {
        System.out.println(
                "Doc_ID Doc_header_ID    "
                        + "    Doc_name     "
                        + "       Date_created    "
                        + "       Date_modified "
                        + "  User_ID_created  User_ID_modified  "
                        + "     Size");
        for (DocumentDetail record : View.toListofDocDetail(pageNo, pageMax, searchLine, order)) {
            System.out.format(
                    "%6s %13s %15s %23s %23s %17s %17s %10s",
                    record.getDoc_ID(),
                    record.Doc_header_ID,
                    record.getDoc_name(),
                    Time.datetoReadableString(record.getDate_created()),
                    Time.datetoReadableString(record.getDate_modified()),
                    UserManager.getUsername(record.getUser_ID_created()),
                    UserManager.getUsername(record.getUser_ID_modified()),
                    record.getSizetoString());
            System.out.println();
        }
    }

    public static void toTable(
            int pageNo, int pageMax, int Doc_header_ID, String searchLine, String order) {
        System.out.println(
                "Doc_ID Doc_header_ID    "
                        + "    Doc_name     "
                        + "       Date_created    "
                        + "       Date_modified "
                        + "  User_ID_created  User_ID_modified  "
                        + "     Size");
        for (DocumentDetail record :
                View.toListofDocDetail(pageNo, pageMax, Doc_header_ID, searchLine, order)) {
            System.out.format(
                    "%6s %13s %15s %23s %23s %17s %17s %10s",
                    record.getDoc_ID(),
                    record.Doc_header_ID,
                    record.getDoc_name(),
                    Time.datetoReadableString(record.getDate_created()),
                    Time.datetoReadableString(record.getDate_modified()),
                    UserManager.getUsername(record.getUser_ID_created()),
                    UserManager.getUsername(record.getUser_ID_modified()),
                    record.getSizetoString());
            System.out.println();
        }
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

    public String getSizetoString() {
        return toSize(Size);
    }

    public byte[] getData_file() {
        return Data_file;
    }

    public void setData_file(byte[] data_file) {
        Data_file = data_file;
    }

    private String toSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        return String.format("%.2f %sB", bytes / Math.pow(1024, exp), "KMGTPE".charAt(exp - 1));
    }
}