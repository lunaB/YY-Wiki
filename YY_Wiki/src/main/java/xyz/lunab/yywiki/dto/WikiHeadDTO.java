package xyz.lunab.yywiki.dto;

import java.util.Date;

public class WikiHeadDTO {
	
	private String title;
	private String title_entity;
	private String title_url;
	private int count;
	private Date regdate;

	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_entity() {
		return title_entity;
	}

	public void setTitle_entity(String title_entity) {
		this.title_entity = title_entity;
	}

	public String getTitle_url() {
		return title_url;
	}

	public void setTitle_url(String title_url) {
		this.title_url = title_url;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
