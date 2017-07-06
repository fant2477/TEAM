package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class View {

    public static int getPossibleMaxPage(List<?> table,int pageMax) {
        return (int) Math.ceil(table.size()/(double) pageMax);
    }

    public static List<DocumentDetail> toListofDocDetail(int pageNo, int pageMax) {
        return toListofDocDetail(pageNo, pageMax, "", "Doc_ID");
    }

    public static List<DocumentDetail> toListofDocDetail(
            int pageNo, int pageMax, String searchLine, String order) {
        List<DocumentDetail> table = new ArrayList<>();
        try {
            String sql =
                    String.format(
                            "SELECT * FROM (SELECT Row_Number() OVER (ORDER BY Doc_ID)"
                                    + "AS RowIndex, Doc_ID, Doc_header_ID, Doc_name, Date_created, "
                                    + "Date_modified, User_ID_created, User_ID_modified, Size "
                                    + "FROM Document_detail WHERE %s "
                                    + ") AS Sub WHERE Sub.RowIndex >= %d AND Sub.RowIndex <= %d"
                                    + "ORDER BY %s",
                            SQL.search(
                                    new String[] {"Doc_ID", "Doc_header_ID", "Doc_name"},
                                    searchLine),
                            (pageMax * pageNo) - pageMax + 1,
                            (pageMax * pageNo),
                            order);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            while (rs.next()) {
                table.add(
                        new DocumentDetail(
                                rs.getInt("Doc_ID"),
                                rs.getInt("Doc_header_ID"),
                                rs.getString("Doc_name"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getLong("Size")));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<DocumentDetail> toListofDocDetail(
            int pageNo, int pageMax, int Doc_header_ID) {
        return toListofDocDetail(pageNo, pageMax, Doc_header_ID, "", "Doc_ID");
    }

    public static List<DocumentDetail> toListofDocDetail(
            int pageNo, int pageMax, int Doc_header_ID, String searchLine, String order) {
        List<DocumentDetail> table = new ArrayList<>();
        try {
            String sql =
                    String.format(
                            "SELECT * FROM (SELECT Row_Number() OVER (ORDER BY Doc_ID)"
                                    + "AS RowIndex, Doc_ID, Doc_header_ID, Doc_name, Date_created, "
                                    + "Date_modified, User_ID_created, User_ID_modified, Size "
                                    + "FROM Document_detail WHERE %s "
                                    + "AND Doc_header_ID = %d"
                                    + ") AS Sub WHERE Sub.RowIndex >= %d AND Sub.RowIndex <= %d"
                                    + "ORDER BY %s",
                            SQL.search(
                                    new String[] {"Doc_ID", "Doc_header_ID", "Doc_name"},
                                    searchLine),
                            Doc_header_ID,
                            (pageMax * pageNo) - pageMax + 1,
                            (pageMax * pageNo),
                            order);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            while (rs.next()) {
                table.add(
                        new DocumentDetail(
                                rs.getInt("Doc_ID"),
                                rs.getInt("Doc_header_ID"),
                                rs.getString("Doc_name"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getLong("Size")));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<DocumentHeader> toListofDocHeader(int pageNo, int pageMax) {
        return toListofDocHeader(pageNo, pageMax, "", "Doc_header_ID");
    }

    public static List<DocumentHeader> toListofDocHeader(
            int pageNo, int pageMax, String searchLine, String order) {
        List<DocumentHeader> table = new ArrayList<>();
        try {
            String sql =
                    String.format(
                            "SELECT * FROM (SELECT Row_Number() OVER (ORDER BY Doc_header_ID)"
                                    + "AS RowIndex, * FROM Document_header WHERE %s) "
                                    + "AS Sub WHERE Sub.RowIndex >= %d AND Sub.RowIndex <= %d"
                                    + "ORDER BY %s",
                            SQL.search(
                                    new String[] {
                                        "Doc_header_ID",
                                        "Doc_header_subject",
                                        "Doc_header_description"
                                    },
                                    searchLine),
                            (pageMax * pageNo) - pageMax + 1,
                            (pageMax * pageNo),
                            order);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            while (rs.next()) {
                table.add(
                        new DocumentHeader(
                                rs.getInt("Doc_header_ID"),
                                rs.getString("Doc_header_subject"),
                                rs.getInt("User_ID_created"),
                                rs.getInt("User_ID_modified"),
                                rs.getTimestamp("Date_created"),
                                rs.getTimestamp("Date_modified"),
                                rs.getString("Doc_header_description")));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}
