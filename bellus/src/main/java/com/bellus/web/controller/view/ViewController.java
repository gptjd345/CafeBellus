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
@RequestMapping("view/*")
public class ViewController {
	
	@Inject
	ViewService viewService ;
	
	@RequestMapping("list.do")
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
	
	//피키캐스트 페이지 글쓰기
	//이미지 배포디렉토리에 업로드하고 pikicast 테이블에 경로추가
	@ResponseBody
	@RequestMapping("imageInsert.do")
	public String imageInsert(
			@RequestParam MultipartFile uploadImage,
			@ModelAttribute ViewDTO dto)  
				throws Exception {
		
		//이미지를 올리지 않은 경우
		if( uploadImage.getSize() == 0 )
		{
			return "noImage";
		}else {
			//배포디렉토리에 이미지를 업로드 한다.
			String filePath = PikiUploadFileUtils.pikiUpload(uploadImage);
			//imagepath 저장
			dto.setImagepath(filePath);
			System.out.println("filePath: "+dto.getImagepath());
			//이미지 추가하기 
			 viewService.insert(dto);
			
			return "success";
		}
		
		
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
	//피키캐스트의 이미지를 업데이트할때  dto에 전달 되는 값 다담아서 초기화 시켜줌
	//업로드 파일 없이 그냥 수정버튼을 누르면 figcaption만 바뀜
	@ResponseBody
	@RequestMapping("imageUpdate.do")
	public void imageUpload(
			@RequestParam MultipartFile uploadImage,
			@ModelAttribute ViewDTO dto)  
				throws Exception {
		
		//이미지 파일을 수정한경우 기존이미지는 배포디렉토리에서 삭제 
		if(uploadImage.getSize() != 0)
		{
			String filePath = PikiUploadFileUtils.pikiUpload(uploadImage);
			ViewDTO prevDTO = viewService.detail(dto.getPnum());
			String prevFilePath = prevDTO.getImagepath();
			
			//filePath에서 앞에 /resources/img/2020/12/14/ 총 26글자 빼면 파일 명이 됨
			PikiUploadFileUtils.deleteFile(prevFilePath);
			
			//imagepath 저장
			dto.setImagepath(filePath);
		}
		//피키캐스트 업데이트 수행
		viewService.update(dto);
						
	}
	//배포디렉토리 경로에서 이미지를 삭제한후 pikicast 테이블에 있는 내용도 모두 삭제한다.
	@ResponseBody
	@RequestMapping("imageDelete.do")
	public void imageDelete(
			@ModelAttribute ViewDTO dto) throws Exception {
		
		//폼에서 넘어온 이미지번호를 이용하여 이미지 path 정보를 가져온다.
		ViewDTO viewDTO = viewService.detail(dto.getPnum());
		
		//만약 pikicast 테이블의 imagepath 필드가 null 일경우 테이블의 내용만 삭제
		if(viewDTO.getImagepath() != "" && viewDTO.getImagepath() != null )
		{
			PikiUploadFileUtils.deleteFile(viewDTO.getImagepath());
		}
		
		//이미지 번호를 이용하여 pikicast 테이블의 해당 로우 삭제 
		viewService.delete(viewDTO.getPnum());
		
	}
	
}
