import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class SQLCommand {
	public static void deleteAllRecord(String table_name) {
		try {
			// "jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017";
			ConnectionDB.connect();

			// Delete record in table
			String sql = String.format("DELETE FROM %s", table_name);
			ConnectionDB.statement.executeUpdate(sql);

			// Close connection
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

		/*
		 * print result for( String[] row: table ){ for( String s: row ){
		 * System.out.print( " " + s ); } System.out.println(); }
		 */
	}

	public static void tabletoString(String table_name) {
		for (String[] row : SQLCommand.getArrayofTable(table_name)) {
			for (String s : row) {
				System.out.print(" " + s);
			}
			System.out.println();
		}
	}
}
