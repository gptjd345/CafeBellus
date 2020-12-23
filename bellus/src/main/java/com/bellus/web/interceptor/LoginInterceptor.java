package com.bellus.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
 
// HandlerInterceptorAdapter 추상클래스 상속
// preHandle(), postHandler() 오버라이딩
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
	// 로그인을 하기전에 로그인이 되어있는 상태인지 검사하고 로그인이 되어있으면 메인으로 이동하고,
	// 로그인이 안되어있으면 로그인 페이지로 이동시킨다.
	/** 메인 액션이 실행되기 전 실행되는 메소드 **/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//세션 객체 생성
		HttpSession session = request.getSession();
        
		if(session.getAttribute("userid") == "admin") {
			System.out.println("관리자 계정으로 로그인 중");
			return true;
		}
		//세션이 없으면(로그인되지 않은 상태)
		else if(session.getAttribute("userid") == null ) {
			//login 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/login.do?message=nologin");
			
			return false; //메인 액션으로 가지 않음
		}else {
			//preHandle의 return 은 컨트롤러의 요청 url로 가도 되냐 안되냐 허가의 의미 true이면 요청 url로 이동한다는 의미 
			System.out.println("LoginInterceptor 정상실행");
			return true; //메인 액션으로 이동
		}
	}
    
	
}
