package project_comment;

import java.sql.Timestamp;

public class CommentDTO {
	private int commid;                              
	 private int bbsid;                                              
	 private String id;                                               
	 private Timestamp commdate;                                         
	 private String commtext;                                         
	 private int comAvailable;
	 
	public CommentDTO() {
		this(0,0,"",null,"",0);
	}
	
	public CommentDTO(int commid, int bbsid, String id, Timestamp commdate, String commtext, int comAvailable) {
		this.commid=commid;
		this.bbsid=bbsid;
		this.id=id;
		this.commdate=commdate;
		this.commtext=commtext;
		this.comAvailable=comAvailable;
	}

	public int getCommid() {
		return commid;
	}

	public void setCommid(int commid) {
		this.commid = commid;
	}

	public int getBbsid() {
		return bbsid;
	}

	public void setBbsid(int bbsid) {
		this.bbsid = bbsid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCommdate() {
		return commdate;
	}

	public void setCommdate(Timestamp commdate) {
		this.commdate = commdate;
	}

	public String getCommtext() {
		return commtext;
	}

	public void setCommtext(String commtext) {
		this.commtext = commtext;
	}

	public int getComAvailable() {
		return comAvailable;
	}

	public void setComAvailable(int comAvailable) {
		this.comAvailable = comAvailable;
	}
	
}
