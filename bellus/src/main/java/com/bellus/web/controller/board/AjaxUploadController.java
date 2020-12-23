package com.bellus.web.controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bellus.web.service.board.BoardService;
import com.bellus.web.util.MediaUtils;
import com.bellus.web.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	//로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	@Inject
	BoardService boardService;
	
	//업로드 디렉토리 servlet-context.xml에 설정되어있음
	/* @Resource(name = "uploadPath") 이게 안됨 왜안되는지 모르겟음*/
	String uploadPath="d:\\upload\\temp";
	
	// 파일첨부 페이지로 이동
    @RequestMapping(value = "/upload/uploadAjax", 
            method = RequestMethod.GET)
    public String uploadAjax() {
        return "/upload/uploadAjax";
    }
 
    // 업로드한 파일은 MultipartFile 변수에 저장됨
    @ResponseBody // json 형식으로 리턴
    @RequestMapping(value = "/upload/uploadAjax", 
    method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
    	//파일의 정보를 로그에 출력
        logger.info("originaName:"
             +file.getOriginalFilename());
        logger.info("size:" +file.getSize());
        logger.info("contentType:" +file.getContentType());
        // 업로드한 파일 정보와 Http 상태 코드를 함께 리턴
        return new ResponseEntity<String>(
                UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
    }
 
    // 이미지 표시 기능
    // 보여줄때 원본파일을 보여줌 _s 안붙어있음
    @ResponseBody // view가 아닌 data 리턴
    @RequestMapping("/upload/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) 
            throws Exception {
        // 서버의 파일을 다운로드하기 위한 스트림
        InputStream in = null; // java.io
        ResponseEntity<byte[]> entity = null;
        System.out.println("displayFile fileName : "+fileName);
        try {
            // 확장자 검사
            String formatName = fileName.substring(
                    fileName.lastIndexOf(".") + 1);
            MediaType mType = MediaUtils.getMediaType(formatName);
            // 헤더 구성 객체
            HttpHeaders headers = new HttpHeaders();
            // InputStream 생성
            in = new FileInputStream(uploadPath + fileName);
//            if (mType != null) { // 이미지 파일이면
//                headers.setContentType(mType);
//            } else { // 이미지가 아니면
//                fileName = fileName.substring(
//                        fileName.indexOf("_") + 1);
                // 다운로드용 컨텐트 타입 표준으로 정의되어있지 않은 파일(이미지가 아닌것들을)바이너리로 받아오겠습니다.
                headers.setContentType(
                        MediaType.APPLICATION_OCTET_STREAM);
                // 큰 따옴표 내부에 " \" "
                // 바이트배열을 스트링으로
                // iso-8859-1 서유럽언어
                // new String(fileName.getBytes("utf-8"),"iso-8859-1")
                // Content-Disposition은 브라우저에서 다운도르창을 띄우는 역할을 하며 뒤에 파라미터를 통해 이름과 확장자를 지정한다.
                // 파일이름을 utf-8로 받아온후 그것을 다시 톰켓이 이해할수 있도록 톰켓에서 사용하는 iso-8859-1로 변환해서 전달한다.
                headers.add("Content-Disposition",
                        "attachment; filename=\"" 
                                + new String(
fileName.getBytes("utf-8"), "iso-8859-1") + "\""); 
                // headers.add("Content-Disposition" 
                // ,"attachment; filename='"+fileName+"'");
//            }
            //inputStream은 데이터를 바이트 단위로 읽어들인다. inputStream에 있는 데이터를 바이트 배열로 받으려면 
            //commons.io에서 제공하는 클래스의 메소드 IOUtils.toByteArray()를 사용한다. entity는 바이트배열과 헤더, 상태코드 정보를 가진다.
            entity = new ResponseEntity<byte[]>(
                    IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(
                    HttpStatus.BAD_REQUEST);
        } finally {
            if (in != null)
                in.close(); // 스트림 닫기
        }
        return entity;
    }
    
    @ResponseBody //뷰가 아닌 데이터를 리턴
    @RequestMapping(value="/upload/deleteFile"
        ,method=RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName){
        logger.info("fileName:"+fileName); 
        //fileName에는 이미지 파일의 경우 썸네일 파일 이름이 넘어옴
        //확장자 검사
        String formatName=fileName.substring(
                fileName.lastIndexOf(".")+1);
        
        MediaType mType=MediaUtils.getMediaType(formatName);
        if(mType != null) { //이미지 파일이면 원본이미지 삭제
            //썸네일 이미지가 아닌 원본 이미지를 삭제하기위해 이름에서 s_부분을 지워야하기 때문에 이렇게 함
        	String front=fileName.substring(0, 12);
            String end=fileName.substring(14);
//         File.separatorChar : 유닉스 / 윈도우즈\\    
            new File(uploadPath+(front+end).replace('/',File.separatorChar)).delete();
        }
        //원본 파일 삭제(이미지이면 썸네일 삭제)
        new File(uploadPath+fileName.replace('/',File.separatorChar)).delete();
        //레코드 삭제
        boardService.deleteFile(fileName); 
        
        return new ResponseEntity<String>("deleted"
                ,HttpStatus.OK);
    }
    
    
}
