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
        List<String> condition = new ArrayList<>();
        for (String keyword : textline.split("\\s+")) {
            List<String> each = new ArrayList<>();
            for (String column : ColumnName) {
                each.add("(UPPER(" + column + ") LIKE UPPER('%" + keyword + "%'))");
            }
            condition.add("(" + String.join(" OR ", each) + ")");
        }
        return String.join(" AND ", condition);
    }
}
