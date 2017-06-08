
import java.sql.*;

public class Account {
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
		} catch (Exception e) {
	      e.printStackTrace();
		}
	}
	
	public void createNewAccount(String name, String surname, String username, String password, String confirmpass){
		AccountValidaiton acc = new AccountValidaiton();
		if (acc.isValidUser(username) && acc.isValidPass(password) && AccountValidaiton.confirmPass(password, confirmpass)){
			this.addAccount(name, surname, username, password);
		}else{
			System.out.println("Can't create new user.");
		}
	}
	
	private void addAccount(String name, String surname, String username, String password){
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
	
	public void deleteAccount(String username){
		if (this.isUsenameIn(username)){
			this.deleteFromUsername(username);
			System.out.println("Delete "+username+" Successfully.");
		}else{
			System.out.println(username+" :not in Database.");
		}
	}
	
	public void deleteFromUsername(String username){
		this.connect();
		 try {
		      this.statement = this.connect.createStatement();
		      String sql = String.format("DELETE FROM Account WHERE username = '%s'", username);
		      this.statement.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		   this.disconnect();
	}
	
	public boolean isUsenameIn(String username){
		this.connect();
		 try {
			  this.statement = this.connect.createStatement();
			  String sql = "SELECT username FROM Account";
		      ResultSet rs = this.statement.executeQuery(sql);
		      while(rs.next()){
		    	  if (rs.getString("username").equals(username)){
		    		  rs.close();
		    		  this.disconnect();
		    		  return true;
		    	  }
		      }
		      rs.close();
		      this.disconnect();
		      return false;
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		return false;
	}
	
	public boolean isLogin(String username, String password){
		 this.connect();
		 try {
			  this.statement = this.connect.createStatement();
			  String sql = "SELECT password " +
                           "FROM Account " +
                           String.format("WHERE username = '%s'", username);
		      ResultSet rs = this.statement.executeQuery(sql);
		      if (rs.getString("password").equals(password)){
		    	  System.out.println("Login correctly");
		    	  rs.close();
		    	  this.disconnect();
		    	  return true;
		      }
		      rs.close();
		      this.disconnect();
		      return false;
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		return false;
	}
}
