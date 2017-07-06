package project_db;

import java.util.ArrayList;
import java.util.List;

public class SQL {
    public static void exxcute(String query) {
        try {
            ConnectionDB.statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllRecord(String table_name) {
        try {
            String sql = String.format("DELETE FROM %s", table_name);
            ConnectionDB.statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String search(String[] ColumnName, String textline) {
        List<String> condition = new ArrayList<String>();
        for (String keyword : textline.split("\\s+")) {
            List<String> each = new ArrayList<String>();
            for (String column : ColumnName) {
                each.add("(UPPER(" + column + ") LIKE UPPER('%" + keyword + "%'))");
            }
            condition.add("(" + SQL.join(" OR ", each) + ")");
        }
        return SQL.join(" AND ", condition);
    }

    public static String join(String delimiter, List<String> elements) {
        String delim = "";
        StringBuilder sb = new StringBuilder();
        for (String i : elements) {
            sb.append(delim).append(i);
            delim = delimiter;
        }
        return sb.toString();
    }
}
