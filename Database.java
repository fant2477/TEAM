
import java.sql.*;

public class Database {
	Connection connect = null;
	Statement statement = null;
	
	public void connect(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connect = DriverManager.getConnection("" +
			"jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017");
			if(connect != null){
				System.out.println("Database Connected.");
				this.connect = DriverManager.getConnection("" +
				"jdbc:sqlserver://192.168.1.249;databaseName=STUDENT;user=sa;password=Team*2017");
			} else {
				System.out.println("Database Connect Failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			this.connect.close();
		} catch (SQLException e) {
	      e.printStackTrace();
		}
	}
	
	public void addAccount(String name, String surname, String username, String password){
		this.connect();
		 try {
		      this.statement = this.connect.createStatement();
		      String sql = String.format("INSERT INTO ACCOUNT VALUES('%s', '%s', '%s', '%s')",name, surname, username, password);
		      System.out.println(sql);
		      this.statement.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		   this.disconnect();
	}
	
	public void deleteAll(String table_name){
		this.connect();
		 try {
		      this.statement = this.connect.createStatement();
		      String sql = String.format("DELETE FROM %s", table_name);
		      this.statement.executeUpdate(sql);

		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		   this.disconnect();
	}
	
	public void deleteFromUsername(String username){
		this.connect();
		 try {
		      this.statement = this.connect.createStatement();
		      String sql = String.format("DELETE FROM Account WHERE name = %s", username);
		      this.statement.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		   this.disconnect();
	}
	
}
