package com.bellus.web.controller.view;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bellus.web.model.view.dto.ViewDTO;
import com.bellus.web.service.view.ViewService;
import com.bellus.web.util.PikiUploadFileUtils;

@Controller
@RequestMapping("view*")
public class ViewController {
	
	@Inject
	ViewService viewService ;
	
	@RequestMapping("")
	public ModelAndView view() throws Exception{
		
		// 피키캐스트 테이블의 모든 내용을 가져옴
		List<ViewDTO> list = viewService.listAll();
		System.out.println("list: "+ list);
	
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("list",list); //변수명, 값을 세트로 model이라는 변수에 넣겠다!!
		mav.setViewName("pikicast/view");
		
		return mav;
	}
	//이미지 추가 폼 가져오기
	@RequestMapping("write.do")
	public String write() {
		return "pikicast/viewWrite";
	}
	

	//이미지 수정 삭제할 수 있는 폼가져오기
	//url에 입력될 변수 값을 지정하기 위해 @PathVariable을 사용한다.
	@RequestMapping(value="detail/{pnum}",method=RequestMethod.POST)
	@ResponseBody //view가 아닌 데이터 자체를 리턴
	public ModelAndView viewDetail(@PathVariable("pnum") int pnum, ModelAndView mav) {
		ViewDTO dto = viewService.detail(pnum);
		System.out.println("Viewdetail: "+dto);
		mav.addObject("dto",dto);
		mav.setViewName("pikicast/viewDetail");
		return mav;
	}
	
	
	
}
