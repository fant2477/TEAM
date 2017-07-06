package project_db;

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
