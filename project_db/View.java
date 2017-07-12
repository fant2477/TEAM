package project_db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class View {

    public static int MaximumPageNo = 1;

    public static int getMaximumPageNo() {
        return MaximumPageNo;
    }

    public static void setMaximumPageNo(int maximumPageNo) {
        MaximumPageNo = maximumPageNo;
    }

    public static List<DocumentDetail> toListofDocDetail(int pageNo, int pageMax) {
        return toListofDocDetail(pageNo, pageMax, "", "Doc_ID");
    }

    public static List<DocumentDetail> toListofDocDetail(
            int pageNo, int pageMax, String searchLine, String order) {
        List<DocumentDetail> table = new ArrayList<DocumentDetail>();
        try {
            String sql =
                    String.format(
                            "SELECT COUNT(*) FROM Document_detail WHERE %s ",
                            search(
                                    new String[] {"Doc_ID", "Doc_header_ID", "Doc_name"},
                                    searchLine));
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int result = rs.getInt(1);
                setMaximumPageNo((result == 0) ? 1 : (int) Math.ceil(result / (double) pageMax));
            }
            sql =
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    sql.replace(
                                            "SELECT COUNT(*)",
                                            "Doc_ID, Doc_header_ID, Doc_name, "
                                                    + "Date_created, Date_modified, "
                                                    + "User_ID_created, User_ID_modified, Size"))
                            + String.format("ORDER BY %s", order);
            rs = ConnectionDB.statement.executeQuery(sql);
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
        List<DocumentDetail> table = new ArrayList<DocumentDetail>();
        try {
            String sql =
                    String.format(
                            "SELECT COUNT(*) FROM Document_detail WHERE %s AND "
                                    + "Doc_header_ID = %d",
                            search(
                                    new String[] {"Doc_ID", "Doc_header_ID", "Doc_name"},
                                    searchLine),
                            Doc_header_ID);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int result = rs.getInt(1);
                setMaximumPageNo((result == 0) ? 1 : (int) Math.ceil(result / (double) pageMax));
            }
            rs.close();
            sql =
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    sql.replace(
                                            "SELECT COUNT(*)",
                                            "Doc_ID, Doc_header_ID, Doc_name, "
                                                    + "Date_created, Date_modified, "
                                                    + "User_ID_created, User_ID_modified, Size"))
                            + String.format("ORDER BY %s", order);
            rs = ConnectionDB.statement.executeQuery(sql);
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
        List<DocumentHeader> table = new ArrayList<DocumentHeader>();
        try {
            String sql =
                    String.format(
                            "SELECT COUNT(*) FROM Document_header WHERE %s ",
                            search(
                                    new String[] {
                                        "Doc_header_ID",
                                        "Doc_header_subject",
                                        "CONVERT(VARCHAR(MAX), Doc_header_description)"
                                    },
                                    searchLine));
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int result = rs.getInt(1);
                setMaximumPageNo((result == 0) ? 1 : (int) Math.ceil(result / (double) pageMax));
            }
            rs.close();
            sql =
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    sql.replace("SELECT COUNT(*)", "*"))
                            + String.format("ORDER BY %s", order);
            rs = ConnectionDB.statement.executeQuery(sql);
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

    public static List<DocumentHeader> toListofDocHeader(
            int pageNo, int pageMax, int User_ID_created, String searchLine, String order) {
        List<DocumentHeader> table = new ArrayList<DocumentHeader>();
        try {
            String sql =
                    String.format(
                            "SELECT COUNT(*) FROM Document_header WHERE %s AND User_ID_created = %d",
                            search(
                                    new String[] {
                                        "Doc_header_ID",
                                        "Doc_header_subject",
                                        "CONVERT(VARCHAR(MAX), Doc_header_description)"
                                    },
                                    searchLine),
                            User_ID_created);
            ResultSet rs = ConnectionDB.statement.executeQuery(sql);
            if (rs.next()) {
                int result = rs.getInt(1);
                setMaximumPageNo((result == 0) ? 1 : (int) Math.ceil(result / (double) pageMax));
            }
            rs.close();
            sql =
                    paging(
                                    (pageMax * pageNo) - pageMax + 1,
                                    (pageMax * pageNo),
                                    sql.replace("SELECT COUNT(*)", "*"))
                            + String.format("ORDER BY %s", order);
            rs = ConnectionDB.statement.executeQuery(sql);
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
                "SELECT * FROM (SELECT Row_Number() OVER (ORDER BY (SELECT NULL)) AS "
                        + "RowIndex, %s) AS Sub WHERE Sub.RowIndex BETWEEN %d AND %d ",
                sql, pageNo, pageMax);
    }

    public static String search(String[] ColumnName, String searchLine) {
        List<String> condition = new ArrayList<String>();
        for (String keyword : searchLine.split("\\s+")) {
            List<String> each = new ArrayList<String>();
            for (String column : ColumnName) {
                each.add("(UPPER(" + column + ") LIKE UPPER('%" + keyword + "%'))");
            }
            condition.add("(" + SQL.join(" OR ", each) + ")");
        }
        return SQL.join(" AND ", condition);
    }
}
