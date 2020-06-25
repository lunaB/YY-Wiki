package xyz.lunab.yywiki.domain;

import java.util.Date;

public class WikiContentVO {
	
	private int wiki_id;
	private String content;
	private String user_id;
	private String user_ip;
	private String summary;
	private Date regdate;
	
	public int getWiki_id() {
		return wiki_id;
	}
	public void setWiki_id(int wiki_id) {
		this.wiki_id = wiki_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
