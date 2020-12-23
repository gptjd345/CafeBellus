package com.bellus.web.model.member.dto;

import java.util.Date;

public class MemberDTO {
	private String userid;
	//회원가입 비밀번호 입력시 첫번째 비밀번호 입력 란에서 넘어오는 데이터를 받아줌 없으면 바인딩시 에러가 발생합니다.
	private String password1;
	private String passwd;
	private String name;
	private String email;
	private Date join_date; // java.util.Date
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", email=" + email
				+ ", join_date=" + join_date + "]";
	}
	

}
