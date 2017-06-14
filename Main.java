import java.util.*;
import java.text.*;

import java.sql.*;
import java.io.*;


class Main {
	
	public static String humanReadableByteCount(long bytes) {
	    int unit = 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    char pre = "KMGTPE".charAt(exp - 1);
	    String s = String.valueOf(bytes / Math.pow(unit, exp));
	    s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
	    return String.format("%s %sB", s, pre);
	}
	
	public static void main(String[] args) {
		RunningDocument x = new RunningDocument("plaitiang");
		// Account a = new Account();
		// a.createNewAccount("Araya", "Siriadun", "username1", "1234", "1234");

		// "C:\\Users\\plait\\Desktop\\Fintech.docx"

		/*
		SQLCommand.deleteAllRecord("History");
		SQLCommand.deleteAllRecord("Document");
		SQLCommand.deleteAllRecord("DataFile");
		
		x.addFile("C:\\Users\\plait\\Desktop\\ch1.pdf");
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech.docx");
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech2.docx","This is detail");
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech3.docx");
		x.deleteFile(60002);
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech.docx","This is detail");
		x.deleteFile(60003);
		x.addFile("C:\\Users\\plait\\Desktop\\Fintech3.docx");
		x.getFile(60004, "C:\\Users\\plait\\Downloads\\");
		x.getAllHistory();  */
		
		SQLCommand.tabletoString("History");
		
		
		//DB db = new DB();

		//db.insertPDF("C:\\Users\\plait\\Desktop\\dc.pdf");
		//db.getPDFData("dc.pdf");

	}
}
