	
	$(function(){
	
		//수정 버튼
	    $("#btnUpdate").click(function(){
	    	
			var title = $("#title").val();
			var content = $("#content").val();
			
			if(title == ""){
				alert("제목을 입력하세요");
				document.form1.title.focus();
				return;
			}
			
	        //첨부파일 이름들을 폼에 추가
	        var str="";
	        //id가 uploadList인것들 (div)에 클래스에 file인것 각각 (each) 
	        $("#uploadedList .file").each(function(){
	            
	            str+="<input type='hidden' name='files' value='"+$(this).val()+"'>";
	                //파일이름에다 인덱스 번호를 하나씩 붙인다.
	        });
	        
	        //폼에 hidden 태그들을 추가함
	        $("#form1").append(str);
	        
	        document.form1.action="/board/update.do"; //document.전송할 form의 id.action= 보내고 싶은 url; form1은 키워드가 아니다 주의할것!!!
	        document.form1.submit();						 //document.전송할 form의 id.submit();
	    });
	    
	    
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
	            dataType: "text",                 // text 타입으로 보낸다.
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
	    
	    
	    //첨부파일 삭제
		//id가 uploadedList인 태그의 class가 file_del인 태그 클릭
	    $("#uploadedList").on("click",".file_del",function(e){
	        var that=$(this); //클릭한 태그
			//data: {fileName: $(this).attr("data-src") },        
	        $.ajax({
	            type: "post",
	            url: "/upload/deleteFile",
	            data: {fileName: $(this).attr("data-src")},        
	            dataType: "text",
	            
	            //컨트롤러에서 삭제가 성공하면 결과값이 result로 넘어오고
	            //result의 값이 deleted와 같으면 화면에서 div 태그를 제거한다.
	            
	            success: function(result){
	                if(result=="deleted"){ 
	                    //컨트롤러에서 넘어온 리턴값이 deleted면 첨부파일이 정상적으로 삭제되었다는 뜻이므로 밑의 구문을 생성
	                    
	                    //화면에서 태그 제거
	                    
	                    that.parent("div").remove();  
	                    //that(this를 의미)의 클릭한 태그의 parent의 div를 삭제
	                    //div태그를 삭제해야 파일에 관한 링크와 정보들이 삭제된다.
	                }
	            }
	        });
	    });
	    
	 
	    
	});
	
	  
	
	
	