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

<link rel="stylesheet" href="/resources/css/viewDetail.css">
<script>
    
// 피키캐스트 상세화면 닫기
$("#btnReplyClose").click(function(){
    $("#modify-form").css("display", "none");
});

//피키 캐스트 수정기능 ajax
$('#pikiUpdate').click(function(){
    var form = $('#piki-updateForm')[0];
	
    var data = new FormData(form);

    $.ajax({
        type : "POST",
        url : "/view/imageUpdate.do",
        
        data : data,
        processData : false,
        contentType : false,
        success: function(data){
            alert("수정이 완료되었습니다.");
            location.replace('../view/list.do');
        },
        error: function(e){
            console.log("ERROR : ",+e);
            alert("수정이 실패하였습니다.");
        }
        
    });
    	
});

//피키 캐스트 삭제 기능 
$('#pikiDelete').click(function(){
    var form = $('#piki-updateForm')[0];
    var data = new FormData(form);

    $.ajax({
        type : "POST",
        url : "/view/imageDelete.do",
        
        data : data,
        processData : false,
        contentType : false,
        success: function(data){
            alert("수정이 완료되었습니다.");
            location.replace('../view/list.do');
        },
        error: function(e){
            console.log("ERROR : ",+e);
            alert("수정이 실패하였습니다.");
        }
        
    });
});


</script>

</head>
<body>
	<section id="UploadContainer">
	<!-- 컨트롤러에서 반환하는 이미지 번호--> 
			 이미지 번호: ${dto.pnum}<br>
            <form action="" method="post" enctype="multipart/form-data" id='piki-updateForm'>
                <input type="file" name="uploadImage">
           		<input type="hidden" name="pnum" value="${dto.pnum}">
	            <!-- XML 마크업 문자로 인식될 문자열을 삭제한다. 오라클데이터 베이스에서 빈문자열은 ""로저장되므로 가져올때 ""를 제거해준다. -->
	            <c:set var="fig" value='${fn:escapeXml(dto.figcaption)}'/>
	            <textarea name="figcaption" id="figcaption" >${fig}</textarea>
	            <div id="btns">
	                <input type="button" id="pikiUpdate" value="수정">
	                <button type="button" id="pikiDelete" >삭제</button>
	                <button type="button" id="btnReplyClose" >닫기</button>
	            </div>
             </form>
     </section>
</body>
</html>