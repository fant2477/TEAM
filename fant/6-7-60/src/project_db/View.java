package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class View {

    public static List<DocumentDetail> toListofDocDetail() {
        List<DocumentDetail> table = new ArrayList<DocumentDetail>();
        try {
            String sql = "SELECT Doc_ID FROM Document_detail ORDER BY Doc_ID";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<Integer>();
            while (rs.next()) {
                rowValues.add(rs.getInt(1));
            }
            rs.close();
            for (int i : rowValues) {
                table.add(DocumentManager.getGeneralFile(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<DocumentDetail> toListofDocDetail(int Doc_header_ID) {
        List<DocumentDetail> table = new ArrayList<DocumentDetail>();
        try {
            String sql =
                    String.format(
                            "SELECT Doc_ID FROM Document_detail "
                                    + "WHERE Doc_header_ID = %d ORDER BY Doc_ID",
                            Doc_header_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<Integer>();
            while (rs.next()) {
                rowValues.add(rs.getInt(1));
            }
            rs.close();
            for (int i : rowValues) {
                table.add(DocumentManager.getGeneralFile(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<DocumentDetail> toListofDocDetail(String textline) {
        List<DocumentDetail> table = new ArrayList<DocumentDetail>();
        try {
            String sql =
                    String.format(
                            "SELECT Doc_ID FROM Document_detail " + "WHERE %s",
                            SQL.search(
                                    new String[] {"Doc_ID", "Doc_header_ID", "Doc_name"},
                                    textline));
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<Integer>();
            while (rs.next()) {
                rowValues.add(rs.getInt(1));
            }
            rs.close();
            for (int i : rowValues) {
                table.add(DocumentManager.getGeneralFile(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<DocumentHeader> toListofDocHeader() {
        List<DocumentHeader> table = new ArrayList<DocumentHeader>();
        try {
            String sql = "SELECT Doc_header_ID FROM Document_header ORDER BY Date_created";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<Integer>();
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

    public static List<DocumentHeader> toListofDocHeader(String textline) {
        List<DocumentHeader> table = new ArrayList<DocumentHeader>();
        try {
            String sql =
                    "SELECT Doc_header_ID FROM Document_header "
                            + "WHERE "
                            + SQL.search(
                                    new String[] {"Doc_header_ID", "Doc_header_subject"}, textline)
                            + "ORDER BY Date_created";
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<Integer>();
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

    public static List<DocumentHeader> toListofDocHeader(int id) {
        List<DocumentHeader> table = new ArrayList<DocumentHeader>();
        try {
            String sql =
                    String.format(
                            "SELECT Doc_header_ID FROM Document_header "
                                    + "WHERE Doc_header_ID = %d"
                                    + "ORDER BY Date_created",
                            id);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            List<Integer> rowValues = new ArrayList<Integer>();
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
}
