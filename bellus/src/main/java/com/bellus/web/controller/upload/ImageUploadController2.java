package com.bellus.web.controller.upload;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@ResponseBody
	@RequestMapping("imageUpload2.do")
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam MultipartFile upload) //CKEditor에서 데이터를 upload라는 이름으로 가져옴 
				throws Exception {
		
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
		String uploadPath =
		"D:\\springMVC\\.metadata\\.plugins\\org.eclipse.wst.server.core"
		+ "\\tmp0\\wtpwebapps\\bellus\\resources\\img";
		
		// 폴더만들고 /2020/12/05/file명 이런식으로 날라옴
		String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, fileName, bytes);
		
		String callback = request.getParameter("CKEditorFuncNum"); //CKEditor에서 사용하는 이름이니 그대로씀
		
		String fileUrl
		 = request.getContextPath()+"/resources/img"+uploadedFileName;
		
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
