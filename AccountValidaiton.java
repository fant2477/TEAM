import java.util.*;


public class AccountValidaiton {
	
	AccountDB acc = new AccountDB();
	public AccountValidaiton() {}
	
	// User name Text Box
	public String validUsrname(String username){
		// You can use letter, numbers and full stops."
		
		if (username.isEmpty()){
			return "You can't leave this empty.";
		}
		
		String pattern1 = "(?).{3,30}";
		if (!username.matches(pattern1)){
			return "Please use between 3 and 30 characters.";
		}
	
		String pattern2 = "[a-zA-Z0-9\\._\\-].{3,30}";
		if (!username.matches(pattern2)){
			return "Please use only letters (a-z, A-Z), numbers and full stops.";
		}
		
		if (this.isUsenameTaken(username)){
			return "That username is taken. Try another.";
		}
		
		return "OK";
	}
	
	private boolean isUsenameTaken(String username){
		return acc.isUsenameIn(username);
	}
	
	public boolean isValidUser(String username){
		// return true if user name can use.
		if (this.validUsrname(username).equals("OK")){
			return true;
		}
		return false;
	}
	
	// Password Text Box
	public String validPassword(String password){
		// Use at least 8 characters
		
		if (password.isEmpty()){
			return "You can't leave this empty.";
		}
		
		String pattern1 = "(?).{4,30}";
		if (!password.matches(pattern1)){
			return "Short passwords are easy to guess. Try one with at least 4 characters.";
		}
		
		return "OK";
	}
	
	public boolean isValidPass(String password){
		// return true if password can use.
		if (this.validPassword(password).equals("OK")){
			return true;
		}
		return false;
	}
	
	// Confirm pass Text Box
	public static boolean confirmPass(String password, String confirmpass){
		// return true if confirm password is correctly.
		return password.equals(confirmpass);
	}
	
	public String validUserLogin(String username){
		if (!this.isUsenameTaken(username)){
			return "Counld't find your account.";
		}
		return "OK";
	}
	
	public String validLogin(String username, String password){
		if (this.validUserLogin(username) == "OK"){
			if (!acc.isLogin(username, password)){
				return "Wrong password. Try agian.";
			}else{
				return "OK";
			}
		}
		return null;
	}
}

class Main{
    public static void main(String[] args){
    	AccountDB x = new AccountDB();
    	AccountValidaiton y = new AccountValidaiton();
    	x.createNewAccount("Araya", "Siriadun", "khonfuu", "12345678", "12345678");
    }
}
