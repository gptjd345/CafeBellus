package com.bellus.web.service.member;

import javax.servlet.http.HttpSession;

import com.bellus.web.model.member.dto.MemberDTO;

public interface MemberService {

	String loginCheck(MemberDTO dto, HttpSession session);

	void logout(HttpSession session);
	
	//id 중복체크 1이상이면 중복
	int idCheck(String userid);
	
	//email 중복체크 1이상이면 중복
	int email_Check(String email);
	
	//회원 추가
	void insert(MemberDTO dto);
	
	//회원 아이디 찾기
	String idSearch(MemberDTO dto);
	
	//회원 비밀번호 찾기
	int pwSearch(MemberDTO dto);
	
	//업데이트
	void update(MemberDTO dto);
	
}
