package project_connectionDB;

import java.util.*;
import java.util.Date;
import java.text.*;
import java.util.TimeZone;

import java.sql.*;
import java.io.*;

class Main {

	public static void main(String[] args) {
		AccountManager a = new AccountManager();
		RunningDocument x = new RunningDocument(a.getAccount("plaitiang"));
		// Account a = new Account();
		// a.createNewAccount("Araya", "Siriadun", "username1", "1234", "1234");

		// "C:\\Users\\plait\\Desktop\\Fintech.docx"
		
		
		// x.addFile("C:\\Users\\plait\\Desktop\\ch1.pdf");

		RunningDocument.getDataFile(600031, "C:\\Users\\plait\\Desktop\\");
		
		
	}
}
