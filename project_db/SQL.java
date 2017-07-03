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
        //List<String> ans = new ArrayList<>();
        List<String> x = new ArrayList<>();
        for (String key : textline.split("\\s+")) {
            List<String> ans = new ArrayList<>();
            for (String column : ColumnName) {
                ans.add("(UPPER(" + column + ") LIKE UPPER('%" + key + "%'))");
            }
            x.add("(" + String.join(" OR ", ans) + ")");
        }
        return String.join(" AND ", x);
    }
}
