package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentHeader {
    private int Doc_header_ID;
    private int User_ID_created;
    private int User_ID_modified;
    private Date Date_created;
    private Date Date_modified;
    private String Status;

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

    public static List<DocumentHeader> getArrayofTable() {
        List<DocumentHeader> table = new ArrayList<>();
        ConnectionDB.connect();
        try {
            String sql = "SELECT * FROM Document_header ORDER BY Date_created";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            int nCol = rs.getMetaData().getColumnCount();
            //table = new DocumentDetail[nCol];
            while (rs.next()) {
                int id = rs.getInt(1);
                DocumentHeader x = DocumentManager.getHeader(id);
                table.add(x);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void tabletoString() {
        for (DocumentHeader record : DocumentHeader.getArrayofTable()) {
            System.out.print(record.getDoc_header_ID());
            System.out.print('\t');
            System.out.print(UserManager.getUsername(record.getUser_ID_created()));
            System.out.print('\t');
            System.out.print(UserManager.getUsername(record.getUser_ID_modified()));
            System.out.print('\t');
            System.out.print(Time.datetoFullTime(record.getDate_created()));
            System.out.print('\t');
            System.out.print(Time.datetoFullTime(record.getDate_modified()));
            System.out.print('\t');
            System.out.println();
        }
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
