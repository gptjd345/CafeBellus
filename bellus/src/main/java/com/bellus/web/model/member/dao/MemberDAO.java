package com.bellus.web.model.member.dao;

import com.bellus.web.model.member.dto.MemberDTO;

public interface MemberDAO {
	public String loginCheck(MemberDTO dto);
	public String adminCheck(MemberDTO dto);
	
	//id중복체크 1이상이면 중복
	public int idCheck(String userid);
	
	///email 중복체크 1이상이면 중복
	public int email_Check(String email);
	
	//회원 정보 받아서 insert
	public void insert(MemberDTO dto);
	
	//회원 아이디 찾기
	public String idSearch(MemberDTO dto);
	
	//회원 비밀번호 찾기
	int pwSearch(MemberDTO dto);
	
	//비밀번호 업데이트
	void update(MemberDTO dto);
}
