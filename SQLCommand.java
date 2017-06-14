import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class SQLCommand {
	public static void deleteAllRecord(String table_name) {
		try {
			ConnectionDB.connect();

			String sql = String.format("DELETE FROM %s", table_name);
			ConnectionDB.statement.executeUpdate(sql);

			ConnectionDB.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String[]> getArrayofTable(String table_name) {
		List<String[]> table = new ArrayList<String[]>();
		ConnectionDB.connect();
		try {
			String sql = String.format("SELECT * FROM %s ORDER BY Time",
					table_name);
			ResultSet result = ConnectionDB.statement.executeQuery(sql);
			int nCol = result.getMetaData().getColumnCount();
			while (result.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = result.getObject(iCol);
					row[iCol - 1] = (obj == null) ? null : obj.toString();
				}
				table.add(row);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public static boolean isTimeStampValid(String inputString) {
		SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		try {
			format.parse(inputString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static void tabletoString(String table_name) {
		for (String[] row : SQLCommand.getArrayofTable(table_name)) {
			for (String s : row) {
				if (isTimeStampValid(s)) {
					System.out.print(" "
							+ s
							+ new String(new char[23 - s.length()]).replace(
									'\0', '0'));
				} else {
					System.out.print(" " + s);
				}
			}
			System.out.println();
		}
	}
}
