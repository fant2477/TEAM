import java.util.Date;

public class Document {
	private int ID;
	private String Filename;
	private Date Time_Added;
	private long Size;
	private String Author;
	private String Path;
	private String Description;

	public Document(int iD, String filename, Date time, long size,
			String author, String path) {
		ID = iD;
		Filename = filename;
		Time_Added = time;
		Size = size;
		Author = author;
		Path = path;
		Description = "";
	}

	public Document(int iD, String filename, Date time, long size,
			String author, String path, String description) {
		ID = iD;
		Filename = filename;
		Time_Added = time;
		Size = size;
		Author = author;
		Path = path;
		Description = description;
	}

	public int getID() {
		return ID;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public Date getTime_Added() {
		return Time_Added;
	}
	
	public void setTime_Added(Date time) {
		Time_Added = time;
	}

	public long getSize() {
		return Size;
	}

	public void setSize(long size) {
		Size = size;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
}
