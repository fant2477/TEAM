package project_db;

import java.util.Date;

public class User {
    int User_ID;
    String Username;
    String Password;
    String Name;
    String Surname;
    String BusinessGroup;
    Date Date_created;
    Date Date_modified;

    public User(int user_ID, String username, String password, String name,
			String surname, String businessGroup, Date date_created,
			Date date_modified) {
		User_ID = user_ID;
		Username = username;
		Password = password;
		Name = name;
		Surname = surname;
		BusinessGroup = businessGroup;
		Date_created = date_created;
		Date_modified = date_modified;
	}

	public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getBusinessGroup() {
        return BusinessGroup;
    }

    public void setBusinessGroup(String businessGroup) {
        BusinessGroup = businessGroup;
    }

    public Date getDate_created() {
        return Date_created;
    }

    public void setDate_created(Date date_created) {
        Date_created = date_created;
    }

    public Date getDate_modified() {
        return Date_modified;
    }

    public void setDate_modified(Date date_modified) {
        Date_modified = date_modified;
    }

	@Override
	public String toString() {
		return "User [User_ID=" + User_ID + ", Username=" + Username
				+ ", Password=" + Password + ", Name=" + Name + ", Surname="
				+ Surname + ", BusinessGroup=" + BusinessGroup
				+ ", Date_created=" + Date_created + ", Date_modified="
				+ Date_modified + "]";
	}
}
