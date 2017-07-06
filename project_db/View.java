package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class View {

    public static int getPossibleMaxPage(List<?> table, int pageMax) {
        return (int) Math.ceil(table.size() / (double) pageMax);
    }

    public static List<DocumentDetail> toListofDocDetail(int pageNo, int pageMax) {
        return toListofDocDetail(pageNo, pageMax, "", "Doc_ID");
    }

    public static List<DocumentDetail> toListofDocDetail(
            int pageNo, int pageMax, String searchLine, String order) {
        List<DocumentDetail> table = new ArrayList<>();
        try {
            String sql =
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    String.format(
                                            "Doc_ID, Doc_header_ID, Doc_name, Date_created, "
                                                    + "Date_modified, User_ID_created, "
                                                    + "User_ID_modified, Size "
                                                    + "FROM Document_detail WHERE %s ",
                                            search(
                                                    new String[] {
                                                        "Doc_ID", "Doc_header_ID", "Doc_name"
                                                    },
                                                    searchLine)))
                            + String.format("ORDER BY %s", order);
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
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    String.format(
                                            "* FROM Document_detail WHERE %s AND "
                                                    + "Doc_header_ID = %d",
                                            search(
                                                    new String[] {
                                                        "Doc_ID", "Doc_header_ID", "Doc_name"
                                                    },
                                                    searchLine),
                                            Doc_header_ID))
                            + String.format("ORDER BY %s", order);
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
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    String.format(
                                            "* FROM Document_header WHERE %s ",
                                            search(
                                                    new String[] {
                                                        "Doc_header_ID",
                                                        "Doc_header_subject",
                                                        "CONVERT(VARCHAR(MAX), Doc_header_description)"
                                                    },
                                                    searchLine)))
                            + String.format("ORDER BY %s", order);
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

    public static String paging(int pageNo, int pageMax, String sql) {
        return String.format(
                "SELECT * FROM (SELECT Row_Number() OVER (ORDER BY (SELECT NULL))"
                        + "AS RowIndex, %s"
                        + ") AS Sub WHERE Sub.RowIndex BETWEEN %d AND %d ",
                sql, pageNo, pageMax);
    }

    public static String search(String[] ColumnName, String searchLine) {
        List<String> condition = new ArrayList<>();
        for (String keyword : searchLine.split("\\s+")) {
            List<String> each = new ArrayList<>();
            for (String column : ColumnName) {
                each.add("(UPPER(" + column + ") LIKE UPPER('%" + keyword + "%'))");
            }
            condition.add("(" + SQL.join(" OR ", each) + ")");
        }
        return SQL.join(" AND ", condition);
    }
}
