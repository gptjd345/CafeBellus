package com.bellus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WayToController {
	
	@RequestMapping("/wayToCome.do")
	public String main(Model model)
	{
		
		return "wayToCome";
	}	
	
}
