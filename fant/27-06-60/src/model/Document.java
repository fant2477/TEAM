package model;

public class Document {
	private String id;
	private String name;
	private String srtDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrtDate() {
		return srtDate;
	}
	public void setSrtDate(String srtDate) {
		this.srtDate = srtDate;
	}
	public Document(String id, String name, String srtDate) {
		this.id = id;
		this.name = name;
		this.srtDate = srtDate;
	}
	
	public Document() {
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + ", srtDate=" + srtDate
				+ "]";
	}
	
	
	
}
