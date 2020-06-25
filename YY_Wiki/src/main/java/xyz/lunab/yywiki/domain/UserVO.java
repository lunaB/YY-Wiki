package xyz.lunab.yywiki.domain;

import java.util.Date;

public class UserVO {
	
	private String id;
	private String name;
	private String pass;
	private String role;
	private Date regdate;
	
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", pass=" + pass + ", role=" + role + ", regdate=" + regdate
				+ "]";
	}
	
}
