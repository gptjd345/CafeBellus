package com.bellus.web.controller.view;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bellus.web.model.view.dto.ViewDTO;
import com.bellus.web.service.view.ViewService;
import com.bellus.web.util.PikiUploadFileUtils;

@Controller
@RequestMapping("pikicast/*")
public class PikiUploadController {
	//로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(PikiUploadController.class);
	
	@Inject
	ViewService viewService;
	
	//업로드 경로를 uploadPath.properties 파일에서 받아옴
	@Value("#{uploadPath['win.PikiUploadPath']}")
	String winPikiUploadPath;
	
	@Value("#{uploadPath['ubuntu.PikiUploadPath']}")
	String ubuntuPikiUploadPath;
	
	
	//피키캐스트 이미지 업로드
	//이미지 배포디렉토리에 업로드하고 pikicast 테이블에 경로추가
	@ResponseBody
	@RequestMapping("imageInsert.do")
	public String imageInsert(
			@RequestParam MultipartFile uploadImage,
			@ModelAttribute ViewDTO dto, HttpServletRequest request)  
				throws Exception {
		
		//이미지를 올리지 않은 경우
		if( uploadImage.getSize() == 0 )
		{
			return "noImage";
		}else {
			
			String pikiUploadPath;
			//이클립스로 실행하는 경우 배포디렉토리 경로에 로컬의 작업내용이 복사되어 실행되지만 
			//리눅스 서버에서는 톰켓을 직접 실행시키는 방식이다보니 webapps 폴더 하위에 프로젝트명으로 폴더가 생김
			//운영체제가 windows면 배포디렉토리경로, 리눅스면 빈문자열을 넣는다.
			String realPath = "";
			 //운영체제가 window이면 win.FileUploadPath를 사용
	        if(File.separatorChar == '\\')
	        {
	        	pikiUploadPath = winPikiUploadPath;
	        	// 프로젝트의 실제 배포 경로를 가져온다. 
	  	      // D:\\springMVC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\bellus
	  			ServletContext context = request.getSession().getServletContext();
	  			realPath = context.getRealPath("/");
	  			System.out.println("realPath: "+realPath);
	        }
	        //운영체제가 Ubuntu이면 ubuntu.FileUploadPath
	        else { pikiUploadPath = ubuntuPikiUploadPath; }
	        
	        
			//배포디렉토리에 이미지를 업로드 한다.
			String filePath = 
					PikiUploadFileUtils.pikiUpload( realPath, pikiUploadPath, uploadImage.getOriginalFilename(), uploadImage.getBytes());
			//imagepath 저장
			dto.setImagepath(filePath);
			System.out.println("filePath: "+dto.getImagepath());
			//이미지 추가하기 
			 viewService.insert(dto);
			
			return "success";
		}
		
		
	}

	//피키캐스트의 이미지를 업데이트할때  dto에 전달 되는 값 다담아서 초기화 시켜줌
	//업로드 파일 없이 그냥 수정버튼을 누르면 figcaption만 바뀜
	// pikicast/imageUpdate.do
	@ResponseBody
	@RequestMapping("imageUpdate.do")
	public void imageUpload(
			@RequestParam MultipartFile uploadImage,
			@ModelAttribute ViewDTO dto, HttpServletRequest request)  
				throws Exception {
		
		String pikiUploadPath;
		//이클립스로 실행하는 경우 배포디렉토리 경로에 로컬의 작업내용이 복사되어 실행되지만 
		//리눅스 서버에서는 톰켓을 직접 실행시키는 방식이다보니 webapps 폴더 하위에 프로젝트명으로 폴더가 생김
		//운영체제가 windows면 배포디렉토리경로, 리눅스면 빈문자열을 넣는다.
		String realPath = "";
        
        //운영체제가 window이면 win.FileUploadPath를 사용
        if(File.separatorChar == '\\')
        {
        	pikiUploadPath = winPikiUploadPath;
        	 // 프로젝트의 실제 배포 경로를 가져온다. 
            // D:\\springMVC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\bellus
    		ServletContext context = request.getSession().getServletContext();
    		realPath = context.getRealPath("/");
    		System.out.println("realPath: "+realPath);
        }
        //운영체제가 Ubuntu이면 ubuntu.FileUploadPath
        else { pikiUploadPath = ubuntuPikiUploadPath; }
		
       
        
		//이미지 파일을 수정한경우 기존이미지는 배포디렉토리에서 삭제 
		if(uploadImage.getSize() != 0)
		{
			String filePath = PikiUploadFileUtils.pikiUpload( realPath, pikiUploadPath, uploadImage.getOriginalFilename(), uploadImage.getBytes());
			ViewDTO prevDTO = viewService.detail(dto.getPnum());
			String prevFilePath = prevDTO.getImagepath();
			
			//filePath에서 앞에 /resources/img/2020/12/14/ 총 26글자 빼면 파일 명이 됨
			PikiUploadFileUtils.deleteFile(prevFilePath,request);
			
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
			@ModelAttribute ViewDTO dto, HttpServletRequest request) throws Exception {
		
		//폼에서 넘어온 이미지번호를 이용하여 이미지 path 정보를 가져온다.
		ViewDTO viewDTO = viewService.detail(dto.getPnum());
		
		//만약 pikicast 테이블의 imagepath 필드가 null 일경우 테이블의 내용만 삭제
		if(viewDTO.getImagepath() != "" && viewDTO.getImagepath() != null )
		{
			PikiUploadFileUtils.deleteFile(viewDTO.getImagepath(),request);
		}
		
		//이미지 번호를 이용하여 pikicast 테이블의 해당 로우 삭제 
		viewService.delete(viewDTO.getPnum());
		
	}
    
    
}
