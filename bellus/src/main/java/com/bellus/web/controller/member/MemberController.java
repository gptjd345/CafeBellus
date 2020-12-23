package com.bellus.web.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bellus.web.controller.member.MemberController;
import com.bellus.web.model.member.dto.MemberDTO;
import com.bellus.web.service.member.MemberService;

@Controller
@RequestMapping("/member/*") // 해당 클래스 전체에서 사용할 공통적인 url Mapping
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberService memberService;

	@RequestMapping("login.do") // /member/login.do
	public String login() {
		return "member/login";
	}
 

	@RequestMapping(value = "login_check.do", method = RequestMethod.POST)
	public ResponseEntity<String> login_check(@RequestBody MemberDTO dto, HttpSession session) { 
		// 로그인 성공하면 이름이 넘어오고 , 실패하면 null이 넘어옴
		System.out.println("아니이게 안먹어?"+dto.getUserid());																						
		String name = memberService.loginCheck(dto, session);
		logger.info("name: " + name);
		ResponseEntity<String> entity = null;

		if (name != null) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("error", HttpStatus.OK);
			
		}
		// 수정 처리 HTTP 상태 메시지 리턴 return entity;
		return entity;

	}
	
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {

		memberService.logout(session); // 세션 invalidate 진행 sesssion.invalidate() 해도됨 굳이 컨트롤러에 돌아갈 필요없이
										// 그러나 컨트롤러는 제어의 역할을 수행하므로 기능은 서비스에서 구현하는게 좋다.
		mav.setViewName("member/login");
		mav.addObject("message", "logout"); // message변수에 logout 저장
		return mav; // login페이지로 이동
	}
	
	//회원가입 폼으로가는 GET 요청
	@RequestMapping(value="signUp.do", method = RequestMethod.GET)
	public String signUp() {
		return "member/signUp";
	}
	//id 중복체크
	@ResponseBody
	@RequestMapping(value = "idCheck.do" ,method = RequestMethod.POST, 
						produces = "application/json;charset=utf8")
	public int idCheck(@RequestBody Map<String,Object> map) {
		String userid = (String)map.get("userid");
		System.out.println("userid: "+userid);
		
		// 회원가입폼에서 아이디 중복 확인하기 1 이상이면 중복임
		return memberService.idCheck(userid);
		
	}
	
	
	//email 중복체크
	@ResponseBody
	@RequestMapping(value = "email_Check.do" ,method = RequestMethod.POST, 
						produces = "application/json;charset=utf8")
	public int email_Check(@RequestBody Map<String,Object> map) {
		String email = (String)map.get("email");
		System.out.println("email: "+email);
		
		// 회원가입폼에서 아이디 중복 확인하기 1 이상이면 중복임
		return memberService.email_Check(email);
		
	}
	
	//회원가입 처리를 해주는 POST 요청
	@ResponseBody
	@RequestMapping(value="signUp.do", method = RequestMethod.POST,produces = "application/json;charset=utf8")
	public String signUpPost(@RequestBody MemberDTO dto) {
		
		//회원정보를 받아 테이블에 추가
		memberService.insert(dto);
		
		System.out.println("dto Name: "+dto.getName());
		return "success";
	}
	
	//아이디 찾기 뷰페이지로 이동
	@RequestMapping(value="idInquery.do",method=RequestMethod.GET)
	public String idInquery()
	{
		return "/member/idInquery";
	}
	
	//ajax통신을 하면 @ResponseBody 쓰든말든 ajax로 응답을 하게되있음
	//아이디 찾기 기능 처리
	@RequestMapping(value="idInquery.do", method=RequestMethod.POST)
	public ModelAndView idInqueryPost(@ModelAttribute MemberDTO dto, ModelAndView mav)
	{
		System.out.println("dto :"+dto);
		//이름과 이메일정보를 받아 id 찾기
		String userid = memberService.idSearch(dto);
		System.out.println("userid: "+userid);
		
//		ModelAndView mav = new ModelAndView();
		
		//받아온 결과를 userid 라는 변수에 저장 null이냐 userid이냐는 뷰페이지에서 제어
		mav.setViewName("member/idAnswer");	
		mav.addObject("userid",userid);
		
		System.out.println("도대체 뭔데" + mav.getViewName());
		
		return mav;
	}
	
	//아이디 찾기 결과 페이지로 이동
	@RequestMapping("idAnswer.do")
	public String idAnswer()
	{
		return "/member/idAnswer";
	}
	
	//비밀번호 찾기 페이지로 이동
	@RequestMapping(value="pwInquery.do", method=RequestMethod.GET)
	public String pwInquery()
	{
		return "/member/pwInquery";
	}
	
	//@ResponseBody 쓰든안쓰든 ajax통신 보낸쪽으로 결과 보내줌
	//비밀번호 찾기 기능 처리
	@ResponseBody
	@RequestMapping(value="pwInquery.do", method=RequestMethod.POST)
	public ModelAndView pwInqueryPost(@ModelAttribute MemberDTO dto)
	{
		System.out.println("dto: "+dto);
		
		//아이디와 이메일정보로 확인하고 해당 비밀번호있으면 1반환
		int result = memberService.pwSearch(dto);
		
		System.out.println("result: "+result);
		
		//map에 자료저장
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("result", result); 
		map.put("userid", dto.getUserid()); 
		
		
		//result 일단 넘겨주고 처리는 뷰페이지에서 할것
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/pwAnswer");	
		mav.addObject("map",map);
		
		
		return mav;
	}
	//변수 하나 받을건데 dto로 받을 필요있나?
	@ResponseBody
	@RequestMapping(value="update.do",method=RequestMethod.POST,produces = "application/text;charset=utf8")
	public ModelAndView update(@ModelAttribute MemberDTO dto)
	{
		System.out.println("dto: "+dto);
		
		memberService.update(dto);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		return mav;
	}
	
	
}
