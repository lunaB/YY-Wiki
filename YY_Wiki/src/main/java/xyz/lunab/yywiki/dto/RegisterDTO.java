package xyz.lunab.yywiki.dto;

public class RegisterDTO {

	private String user_id;
	private String user_name;
	private String user_pw;
	private String user_pw_conf;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_pw_conf() {
		return user_pw_conf;
	}
	public void setUser_pw_conf(String user_pw_conf) {
		this.user_pw_conf = user_pw_conf;
	}
	@Override
	public String toString() {
		return "RegisterDTO [user_id=" + user_id + ", user_name=" + user_name + ", user_pw=" + user_pw
				+ ", user_pw_conf=" + user_pw_conf + "]";
	}
	
}
