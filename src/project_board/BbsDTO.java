package project_board;

import java.sql.Timestamp;

public class BbsDTO {
	private int bbsid;
	private String bbstitle; 
	private String bbscontent;
	private Timestamp bbsdate; 
	private int bbshit=0; 
	private String bbscategory;
	private String id; 
	private int bbsAvailable; 
	private String b_fname; // 파일 경로
	
	public BbsDTO() {
		this(0,"","",null,0,"","",0,"");
	}
	
	public BbsDTO(int bbsid, String bbstitle, String bbscontent,Timestamp bbsdate, int bbshit, String bbscategory, String id, int bbsAvailable) {
		this.bbsid=bbsid;
		this.bbstitle=bbstitle;
		this.bbscontent=bbscontent;
		this.bbsdate=bbsdate;
		this.bbshit=bbshit;
		this.bbscategory=bbscategory;
		this.id=id;
		this.bbsAvailable=bbsAvailable;
	}
	
	public BbsDTO(int bbsid, String bbstitle, String bbscontent,Timestamp bbsdate, int bbshit, String bbscategory, String id, int bbsAvailable, String b_fname) {
		this.bbsid=bbsid;
		this.bbstitle=bbstitle;
		this.bbscontent=bbscontent;
		this.bbsdate=bbsdate;
		this.bbshit=bbshit;
		this.bbscategory=bbscategory;
		this.id=id;
		this.bbsAvailable=bbsAvailable;
		this.b_fname=b_fname;
	}
	
	public String getB_fname() {
		return b_fname;
	}

	public void setB_fname(String b_fname) {
		this.b_fname = b_fname;
	}

	public int getBbsid() {
		return bbsid;
	}

	public void setBbsid(int bbsid) {
		this.bbsid = bbsid;
	}

	public String getBbstitle() {
		return bbstitle;
	}

	public void setBbstitle(String bbstitle) {
		this.bbstitle = bbstitle;
	}

	public String getBbscontent() {
		return bbscontent;
	}

	public void setBbscontent(String bbscontent) {
		this.bbscontent = bbscontent;
	}

	public Timestamp getBbsdate() {
		return bbsdate;
	}

	public void setBbsdate(Timestamp bbsdate) {
		this.bbsdate = bbsdate;
	}

	public int getBbshit() {
		return bbshit;
	}

	public void setBbshit(int bbshit) {
		this.bbshit = bbshit;
	}

	public String getBbscategory() {
		return bbscategory;
	}

	public void setBbscategory(String bbscategory) {
		this.bbscategory = bbscategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBbsAvailable() {
		return bbsAvailable;
	}

	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}
	

}
