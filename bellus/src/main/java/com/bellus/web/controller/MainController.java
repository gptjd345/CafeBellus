package com.bellus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main(Model model)
	{
		model.addAttribute("message","홈페이지 방문을 환영합니다."); //변수명, 값을 세트로 model이라는 변수에 넣겠다!!
		model.addAttribute("name","김정아.");
		return "main";
	}
	@RequestMapping("/menu2.do")
	public String menu2()
	{
		return "menu2";
	}
}
