package xyz.lunab.yywiki.domain;

public class WikiVO {
	
	private int wiki_id;
	private String title;
	private int permission_id;
	
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
	
	@Override
	public String toString() {
		return "WikiVO [wiki_id=" + wiki_id + ", title=" + title + ", permission_id=" + permission_id + "]";
	}
	
}
