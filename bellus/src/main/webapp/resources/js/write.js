$(function(){
	//파일 첨부
	$(".fileDrop").on("dragenter dragover",function(e){
	    //기본 효과 막음, 기본효과가 있으면 이미지 파일이 실행되어 버림
	    e.preventDefault();
	});
	$(".fileDrop").on("drop",function(e){
	    e.preventDefault();
	    //첫번째 첨부파일
	    //드롭한 파일을 폼 데이터에 추가함
	    var files=e.originalEvent.dataTransfer.files;
	    
	    var file=files[0];
	    //폼 데이터에 첨부파일 추가 (0번 인덱스이므로 첫번째 파일만 붙였다.)
	    var formData=new FormData();
	    formData.append("file",file);
	    
	    $.ajax({ //비동기 방식으로 호출
	        url: "/upload/uploadAjax", // uploadAjax를 호출함
	        data: formData,                   // formData를 보내고                   
	        dataType: "text",                 // text 타입으로 결과를 받겠습니다.
	        processData: false,    // 요청으로 보낸 데이터를 query string 형태로 변환할지 여부
	        contentType: false,    // 서버로 보내지는 데이터의 기본값
	        type: "post",
	         
	        //호출처리가 완료된 다음 (성공한 후)에 실행되는 구문
	       //컨트롤러에서 업로드 경로와 상태코드 (200)을 리턴해서 이쪽으로 보낸다.
	        success: function(data){   //콜백 함수
	            console.log(data);
	            //data : 업로드한 파일 정보와 Http 상태 코드
	            var fileInfo=getFileInfo(data);  //첨부파일의 정보 common.js의 메소드
	            console.log(fileInfo);
	            var html="<a class='listAttach' href='"+fileInfo.getLink+"'>"+
	                fileInfo.fileName+"</a><br>";
	            html += "<input type='hidden' class='file' value='"
	                +fileInfo.fullName+"'>"; //hidden 태그를 추가 class='file'인 것이 생김
	            $("#uploadedList").append(html); //div에 추가
	        }
	    });
	});
	
	 //id가 btnSave인 버튼을 클릭하면 실행되는 구문. 
	 //post 방식으로 자료를 insert.do (컨트롤러)로 보낸다. 
    $("#btnSave").click(function(){
		
    	//첨부파일 이름들을 폼에 추가
        var str="";
        //id가 uploadList인것들 (div)에 클래스에 file인것 각각 (each) 
        $("#uploadedList .file").each(function(){
            
            str+="<input type='hidden' name='files' value='"+$(this).val()+"'>";
                //파일이름에다 인덱스 번호를 하나씩 붙인다.
        });
        //폼에 hidden 태그들을 추가함
        
		$("#form1").append(str);
	    
        document.form1.submit();
    });

});	