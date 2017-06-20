package project_db;

public class Main {
    public static void main(String[] args) {
        UserManager x = new UserManager();
        //SQL.deleteAllRecord("Account");
        User m = x.createNewUser("khonfuu", "12345678", "12345678", "Araya", "Siriadun", "ICT");
        
        
    }
}
