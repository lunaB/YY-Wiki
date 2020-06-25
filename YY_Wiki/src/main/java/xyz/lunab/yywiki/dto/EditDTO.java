package xyz.lunab.yywiki.dto;

public class EditDTO {
	
	private int wiki_id;
	private String content;
	private String summary;
	
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Override
	public String toString() {
		return "EditDTO [wiki_id=" + wiki_id + ", content=" + content + ", summary=" + summary + "]";
	}
	
}
