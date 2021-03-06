package com.bellus.web.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bellus.web.util.MediaUtils;
import com.bellus.web.util.UploadFileUtils;


public class PikiUploadFileUtils {
	 //이미지를 업로드할 디렉토리(배포 경로로 설정한다.)
//	static String uploadPath = "D:\\springMVC\\.metadata\\.plugins\\org.eclipse.wst.server.core"
//	+ "\\tmp0\\wtpwebapps\\bellus\\CafeBellus\\Views\\";
	
	
	//static으로 선언해야 스프링 컨트롤러에서 사용가능
//	public static String pikiUpload(MultipartFile uploadImage,HttpServletRequest request) throws Exception
//	{
//		String uploadPath;
//		//만약 운영체제가 윈도우라면
//		if(File.separatorChar == '\\')
//		{
//			//배포경로가 달라지더라도 상관없음
//			ServletContext context = request.getSession().getServletContext();
//			uploadPath = context.getRealPath("/CafeBellus/Views/");
//		}
//		//윈도우가 아니라면 서버 리눅스 
//		else {
//			uploadPath = "/CafeBellus/Views/";
//		}
//		//http body
//		//업로드한 파일 이름
//		String fileName = uploadImage.getOriginalFilename();
//		//파일을 바이트 배열로 변환
//		byte[] fileData = uploadImage.getBytes();
//			
//		// uuid 발급, 랜덤한uuid를 만들어 uid에 저장
//        UUID uid = UUID.randomUUID();
//        
//        //uuid를 추가한 파일 이름
//        String savedName = uid.toString() + "_" + fileName;
//        
//        // 업로드할 디렉토리 생성 (월, 일 날짜별로 디렉토리를 만든다.)
//        // calcPath는 년도, 월, 일이 출력되게하는 메소드이고, 밑에서 static으로 선언되었으므로 메모리에 제일 처음 올려져 있다.
//        // calcpath에 업로드 경로를 매개값으로 줘서, 업로드한 날짜를 savedPath에 저장하고, target변수에 File
//        
//        String savedPath = calcPath(uploadPath); //년월일로 만들어진 디렉토리경로
//        System.out.println("savedPath: "+savedPath);
//        File target = new File(uploadPath 
//                + savedPath, savedName);    //업로드경로와 저장경로에 저장한파일의 이름에 대한 File 객체를 생성한다.
//        
//        // 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
//        FileCopyUtils.copy(fileData, target); //target에 저장된 파일경로와 이름, 그리고 fileData(파일용량)을 복사
//		
//		
//        System.out.println("target Path: "+target.getPath());
//        
//        String fileUrl = target.getAbsolutePath().replace(File.separatorChar,'/');
//		fileUrl = fileUrl.substring(fileUrl.indexOf("/CafeBellus/Views"));
//		System.out.println("fileUrl:"+fileUrl);
//		
//		
//		return fileUrl;
//	}
	//pikicast 이미지 업로드
	// pikiUploadPath : \\CafeBellus\\Views 개발 디렉토리의 업로드 경로 
	// realPath : 톰켓이 만들어놓은 배포디렉토리 경로 
	// D:\\springMVC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\bellus
	// realPath + pikiUploadPath = pikicast 이미지가 실제로 저장되는 경로
	public static String pikiUpload( String realPath, String pikiUploadPath , String originalName, byte[] fileData) throws Exception
	{
			
		// uuid 발급, 랜덤한uuid를 만들어 uid에 저장
        UUID uid = UUID.randomUUID();
        
        //uuid를 추가한 파일 이름
        String savedName = uid.toString() + "_" + originalName;
        
        // 업로드할 디렉토리 생성 (월, 일 날짜별로 디렉토리를 만든다.)
        // calcPath는 년도, 월, 일이 출력되게하는 메소드이고, 밑에서 static으로 선언되었으므로 메모리에 제일 처음 올려져 있다.
        // calcpath에 업로드 경로를 매개값으로 줘서, 업로드한 날짜를 savedPath에 저장하고, target변수에 File
        
        String savedPath = calcPath(realPath + pikiUploadPath); //년월일로 만들어진 디렉토리경로
        System.out.println("savedPath: "+savedPath);
        File target = new File(realPath + pikiUploadPath
                + savedPath, savedName);    //업로드경로와 저장경로에 저장한파일의 이름에 대한 File 객체를 생성한다.
        
        // 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
        FileCopyUtils.copy(fileData, target); //target에 저장된 파일경로와 이름, 그리고 fileData(파일용량)을 복사
		
		
        String uploadedFileName = makeIcon(realPath , pikiUploadPath, savedPath, savedName);
    
        
        // /CafeBellus/views/2020/12/05/file명 이런식으로 반환
		return uploadedFileName;
	}
	// /CafeBellus/views/2020/12/05/file명 이런식으로 반환
    private static String makeIcon(String realPath, String pikiUploadPath
            , String calcPath, String fileName) throws Exception {

        String iconName = realPath + pikiUploadPath + calcPath + File.separator 
                + fileName;
      
 
        // File.separatorChar : 디렉토리 구분자
        // 윈도우 \ , 유닉스(리눅스) /
        return iconName.substring(realPath.length())
                .replace(File.separatorChar, '/');
        //파일 이름이라고 생각하면 된다.
    }
	
	private static String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator 
                + cal.get(Calendar.YEAR);
        // Calendar.YEAR : 현재 년도 가져옴
        // "월"이랑 "일"은 10보다 작을때가 있으므로 (1월,2월....은 01월, 02월 이런식으로 붙이기 위해)
        // 그러니까 자릿수를 맞춰주기 위해서 DecimalFormat를 사용
        
        String monthPath = yearPath + File.separator 
                + new DecimalFormat("00").format(
                        cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator 
                + new DecimalFormat("00").format(cal.get(
                        Calendar.DATE));
        
        //디렉토리를 생성
        makeDir(uploadPath, yearPath, monthPath, datePath);
       
        return datePath;
    }
	
	 private static void makeDir(
	            
	            // 위에서 makeDir을 호출할때는 매개변수가 4개 이지만, String뒤에 있는 ...이 가변사이즈 매개변수이기 
	            // 때문에 호출시에 매개변수의 숫자가 많아도 호출시에 매개변수가 배열로 만들어져 paths로 다 들어가기 때문에 쌓일수 있다.
	            // ex) paths 0번이 uploadPath 1번이 yearPath, 2번이 monthPath가 되고, 3번이 datePath가 된다.
	    		// paths[0] = uploadPath, paths[1] = yearPath , paths[2] = monthPath , paths[3] = datePath 
	            
	            String uploadPath, String... paths) {
	        //디렉토리가 존재하면 skip, (디렉토리가 기존에 존재하면 만들지 않는다는 뜻)
	        if (new File(paths[paths.length - 1]).exists()) {
	            return;
	        }
	        //디렉토리가 없을시에 디렉토리 생성 코드
	        //paths 배열에 저장된 값들을 하나씩 path에 저장하고, exist메소드를 통해 해당 경로가 존재하는지 확인하고 없으면 디렉토리만듬
	        //업로드 경로 뒤에 path붙여나감
	        for (String path : paths) {
	            File dirPath = new File(uploadPath + path);
	            if (!dirPath.exists()) {//디렉토리가 존재하지 않으면
	                dirPath.mkdir(); // 디렉토리 생성, mkdir()메소드는 패스명이 나타내는 디렉토리를 생성하는 메소드.
	            }
	        }
	    }
	 
	 
		//삭제
		//fileName에는 이미지 파일의 경우 원본 파일 이름이 넘어옴 /CafeBellus/Views/2020/12/14/image이름.png 이런식으로 넘어옴
	    static public void deleteFile(String filePath, HttpServletRequest request){
	    	String uploadPath;
			//만약 운영체제가 윈도우라면
			if(File.separatorChar == '\\')
			{
				//상대 경로로 경로 설정하기
				ServletContext context = request.getSession().getServletContext();
				uploadPath = context.getRealPath("/CafeBellus/Views/");
			}
			//윈도우가 아니라면 서버 리눅스 
			else {
				uploadPath = "/CafeBellus/Views/";
			}
			
	    	filePath = filePath.substring(18);
	    	System.out.println("filePath: "+filePath);
	       
	        //원본 파일 삭제
	        new File(uploadPath+filePath.replace('/',File.separatorChar)).delete();
	        
	    }
}	 
	


