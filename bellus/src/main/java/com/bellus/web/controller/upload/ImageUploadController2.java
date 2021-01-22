package com.bellus.web.controller.upload;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bellus.web.util.UploadFileUtils;

@Controller //IKEDITOR를 이용한 이미지 업로드를 위한 컨트롤러
public class ImageUploadController2 {
	private static final Logger logger=
			LoggerFactory.getLogger(ImageUploadController2.class);
	
	//uploadPath.properties에서 CKEditor 이미지 업로드 저장 경로를 가져옴
	@Value("#{uploadPath['win.CKEditorUploadPath']}")
	String winCKEditorUploadPath;
	
	@Value("#{uploadPath['ubuntu.CKEditorUploadPath']}")
	String ubuntuCKEditorUploadPath;
	
	
	@ResponseBody
	@RequestMapping("imageUpload2.do")
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam MultipartFile upload) //CKEditor에서 데이터를 upload라는 이름으로 가져옴 
				throws Exception {
//		String uploadPath =
//		"D:\\springMVC\\.metadata\\.plugins\\org.eclipse.wst.server.core"
//		+ "\\tmp0\\wtpwebapps\\bellus\\resources\\img";
		
		//uploadPath는 Value 어노테이션을 사용해 가져온 값을 사용합니다.
		String uploadPath;
	        
        //운영체제가 window이면 win.CKEditorUploadPath를 사용
        if(File.separatorChar == '\\')
        {
        	uploadPath = winCKEditorUploadPath;
        }
        //운영체제가 Ubuntu이면 ubuntu.CKEditorUploadPath
        else { uploadPath = ubuntuCKEditorUploadPath; }
		
		//http header 설정 : 브라우저단의 인코딩 지정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//http body
		//업로드한 파일 이름
		String fileName = upload.getOriginalFilename();
		System.out.println("fileName : "+fileName);
		//파일을 바이트 배열로 변환
		byte[] bytes = upload.getBytes();
		//이미지를 업로드할 디렉토리(배포 경로로 설정한다.)
		

		
		// 폴더만들고 /2020/12/05/file명 이런식으로 날라옴
		String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, fileName, bytes);
		
		String callback = request.getParameter("CKEditorFuncNum"); //CKEditor에서 사용하는 이름이니 그대로씀
		
		//파일의 실제 저장 경로는 배포디렉토리의 경로지만 프로젝트에서 요청할때는 프로젝트의 내부경로에서 요청하듯이 해야한다.
		String fileUrl;
		
		if(File.separatorChar == '\\')
		{
			fileUrl = request.getContextPath()+"/resources/img"+uploadedFileName;
		}
		//AWS 리눅스 서버에서는 /CafeBellus/CKEditor 경로에 이미지가 저장되므로 파일을 요청할때는 이 경로로 요청해야한다.
		else {
			fileUrl = ubuntuCKEditorUploadPath+uploadedFileName;
		}
		
		
		PrintWriter printWriter = response.getWriter();
		
		System.out.println("fileUrl: "+fileUrl);
		printWriter.println(
		"<script>window.parent.CKEDITOR.tools.callFunction("
				+callback+",'" + fileUrl +"','이미지가 업로드되었습니다.')"
				+"</script>");
	
		
		//스트림 닫기
		printWriter.flush();
				
				
	}
			
	
}
