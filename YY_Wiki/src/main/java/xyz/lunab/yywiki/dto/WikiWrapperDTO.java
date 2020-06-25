package xyz.lunab.yywiki.dto;

import java.util.Date;

public class WikiWrapperDTO {
	private int wiki_id;
	private String title;
	private int permission_id;
	
	public String content;
	public Date regdate;
	
	public int getWiki_id() {
		return wiki_id;
	}
	public void setWiki_id(int wiki_id) {
		this.wiki_id = wiki_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "WikiWrapperDTO [wiki_id=" + wiki_id + ", title=" + title + ", permission_id=" + permission_id
				+ ", content=" + content + ", regdate=" + regdate + "]";
	}
	
}
