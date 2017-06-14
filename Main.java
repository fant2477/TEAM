import java.util.*;
import java.util.Date;
import java.text.*;
import java.util.TimeZone;

import java.sql.*;
import java.io.*;


class Main {
	
	public static void main(String[] args) {
		RunningDocument x = new RunningDocument("plaitiang");
		// Account a = new Account();
		// a.createNewAccount("Araya", "Siriadun", "username1", "1234", "1234");

		// "C:\\Users\\plait\\Desktop\\Fintech.docx"
		//SQLCommand.deleteAllRecord("DataFile");
		
		
		SQLCommand.deleteAllRecord("History");
		SQLCommand.deleteAllRecord("Document");
		SQLCommand.deleteAllRecord("DataFile");
		
		/*
		x.addFile("C:\\Users\\plait\\Desktop\\ch1.pdf");
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech.docx");
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech2.docx","This is detail");
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech3.docx");
		x.deleteFile(600002);
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech.docx","This is detail");
		x.deleteFile(600003);
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech3.docx");
		RunningDocument.getFile(600004, "C:\\Users\\plait\\Downloads\\");
		SQLCommand.tabletoString("History");
		*/
		
		System.out.println(RunningDocument.getFullCurrentTime());
		
	}
}
