package com.bellus.web.service.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.bellus.web.model.member.dao.MemberDAO;
import com.bellus.web.model.member.dto.MemberDTO;
import com.bellus.web.service.member.MemberService;

	@Service
	public class MemberServiceImpl implements MemberService {

		@Inject
		MemberDAO memberDao;
		
		@Override
		public String loginCheck(MemberDTO dto, HttpSession session) {
			
			//만약 로그인 창에서 로그인 시도한 id가 admin이라면 직접 값을 넣었으니 하드 코딩 고칠필요있음
			if(dto.getUserid().equals("admin"))
			{
				String name = memberDao.adminCheck(dto);
				if(name != null) {
					//세션에 변수 등록 기본적으로 15분 유지
					session.setAttribute("userid", dto.getUserid());
					session.setAttribute("name", name);
					return name;
				}
				return null;
			}
			else {
				//맞으면 이름이 넘어오고 틀리면 null이 넘어옴
				String name = memberDao.loginCheck(dto);
				
				if(name != null) { //맞으면
					//세션 변수 등록 기본적으로 15분 유지
					session.setAttribute("userid", dto.getUserid());
					session.setAttribute("name", name);
				}
				return name;
			}
			
		}


		@Override
		public void logout(HttpSession session) {
			//세션을 모두 초기화 시킴
			session.invalidate();
		}

		//id 중복체크 1이상이면 중복임
		@Override
		public int idCheck(String userid) {
			
			return memberDao.idCheck(userid);
		}
		//email 중복체크 1이상이면 중복임
		@Override
		public int email_Check(String email) {
			return memberDao.email_Check(email);
		}


		@Override
		public void insert(MemberDTO dto) {
			memberDao.insert(dto);
			
		}

		//회원 아이디 찾기
		@Override
		public String idSearch(MemberDTO dto) {
			return memberDao.idSearch(dto);
		}


		@Override
		public int pwSearch(MemberDTO dto) {
			
			return memberDao.pwSearch(dto);
		}


		@Override
		public void update(MemberDTO dto) {
			
			 memberDao.update(dto);
		}

	}