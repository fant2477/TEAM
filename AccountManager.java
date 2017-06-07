import java.util.*;


public class AccountManager {
	List<Account> AccountList = new ArrayList<Account>();
	

	public AccountManager() {
		//
	}

	public void createAccount(String name, String surname, String username, String password, String confirmpass){
		if (isValidUser(username) && isValidPass(password) && confirmPass(password, confirmpass)){
			Database acc = new Database();
			acc.addAccount(name, surname, username, password);
		}else{
			System.out.println("Can't create new user.");
		}
	}
	
	
	
	public boolean isValidUser(String username){
		// return true if username can use.
		if (this.validUsrname(username).equals("OK")){
			return true;
		}
		return false;
	}
	
	public String validUsrname(String username){
		// You can use letter, numbers and full stops."
		
		if (username.isEmpty()){
			return "You can't leave this empty.";
		}
		
		String pattern1 = "(?).{6,30}";
		if (!username.matches(pattern1)){
			return "Please use between 6 and 30 characters.";
		}
	
		String pattern2 = "[a-zA-Z0-9\\._\\-].{6,30}";
		if (!username.matches(pattern2)){
			return "Please use only letters (a-z, A-Z), numbers and full stops.";
		}
		
		if (this.isUsenameTaken(username)){
			return "That username is taken. Try another.";
		}
		
		return "OK";
	}
	
	private boolean isUsenameTaken(String username){
		//
	}
	
	public boolean isValidPass(String password){
		// return true if password can use.
		if (this.validPassword(password).equals("OK")){
			return true;
		}
		return false;
	}
	
	public String validPassword(String password){
		// Use at least 8 characters
		
		if (password.isEmpty()){
			return "You can't leave this empty.";
		}
		
		String pattern1 = "(?).{8,30}";
		if (!password.matches(pattern1)){
			return "Short passwords are easy to guess. Try one with at least 8 characters.";
		}
		
		return "OK";
	}
	
	public boolean confirmPass(String password, String confirmpass){
		return password.equals(confirmpass);
	}
	
	public void deleteAccount(String AccountName){
		for(Account e: AccountList){
			if(e.getUsername().equals(AccountName)){
				AccountList.remove(e);
			}
		}
	}

	@Override
	public String toString() {
		return "AccountManager [AccountList=" + AccountList + "]";
	}
		
}

class Main{
    public static void main(String[] args){
    	AccountManager x = new AccountManager();
    	Database y = new Database();
    	
    	
    }
}
