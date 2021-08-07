package com.bellus.web.model.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bellus.web.model.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public String loginCheck(MemberDTO dto) {
		return sqlSession.selectOne("member.login_check",dto);
	}
	
	//login 시도하는 id가 admin인 경우 패스워드가 맞는지 확인
	@Override
	public String adminCheck(MemberDTO dto) {
		
		return sqlSession.selectOne("member.admin_check",dto);
	}

	//id중복체크 1이상이면 중복
	@Override
	public int idCheck(String userid) {
		System.out.println("dao userid: "+userid);
		
		int check = sqlSession.selectOne("member.idCheck",userid);
		System.out.println("check: "+check);
		return check;
	}
	
	//email중복체크 1이상이면 중복
	@Override
	public int email_Check(String email) {
		int check = sqlSession.selectOne("member.email_Check",email);
		
		System.out.println("check: "+check);
		return check;
	}

	@Override
	public void insert(MemberDTO dto) {
		sqlSession.selectOne("member.insert",dto);
		
	}
	//회원 아이디 찾기
	@Override
	public String idSearch(MemberDTO dto) {
		return sqlSession.selectOne("member.idSearch",dto);
	}

	@Override
	public int pwSearch(MemberDTO dto) {
		return sqlSession.selectOne("member.pwSearch",dto);
	}

	@Override
	public void update(MemberDTO dto) {
		System.out.println("아니 dto:"+dto);
		 sqlSession.selectOne("member.update",dto);
		
	}
	
	//2021-07-05 트랜잭션 롤백이슈 확인을위한 메소드 
	//회원 가입시 아이디와 가입일자 저장 JOININFO 테이블
	@Override
	public void joinInfo(MemberDTO dto) {
		sqlSession.selectOne("member.joinInfo",dto);
		
	}

	

}
