<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/viewWrite.css">
<script>
    
// 피키캐스트 상세화면 닫기
$("#btnClose").click(function(){
    $("#write-form").css("display", "none");
});

//피키 캐스트 추가기능 ajax
$('#pikiAdd').click(function(){
    var form = $('#insertForm')[0];
	
    var data = new FormData(form);

    $.ajax({
        type : "POST",
        url : "/pikicast/imageInsert.do",
        
        data : data,
        processData : false,
        contentType : false,
        success: function(data){
            if(data == "success"){
            alert("그림 추가 완료되었습니다.");
            location.replace('../view/list.do');
            } else{
				alert("이미지를 선택해주세요 ");
                }
        },
        error: function(e){
            console.log("ERROR : ",+e);
            alert("추가를 실패하였습니다.");
        }
        
    });
    	
});


</script>

</head>
<body>
	<section id="UploadContainer">
	<!-- 컨트롤러에서 반환하는 이미지 번호--> 
			 이미지 번호: ${dto.pnum}<br>
            <form action="" method="post" enctype="multipart/form-data" id='insertForm'>
                <input type="file" name="uploadImage">
	            <textarea name="figcaption" id="figcaption"></textarea>
	            <div id="btns">
	                <input type="button" id="pikiAdd" value="추가">
	                <button type="button" id="btnClose" >닫기</button>
	            </div>
             </form>
     </section>
</body>
</html>