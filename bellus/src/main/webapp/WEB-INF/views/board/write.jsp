<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<!-- 자주쓰이는 놈들 위해 만들어놓은 js파일 -->
<script type="text/javascript" src="/resources/js/common.js"></script>

<!-- ckeditor의 라이브러리를 사용하기 위해 추가하는 코드 -->
<script src="/resources/ckeditor/ckeditor.js"></script>

<link rel="stylesheet" href="/resources/css/write.css">

<!-- 글쓰기 페이지에서 파일 첨부 기능 및 글쓰기 기능 -->
<script src="/resources/js/write.js" defer ></script>
<title>Insert title here</title>


</head>
<body>
<!--  --------header  ------------------------>
<%@ include file="../include/nav.jsp" %>
<section class="board-Wrapper">
	<div class="board-Content">
		<header style="width:100%;">
			<h2>글쓰기</h2>
		</header>
		<form id="form1" name="form1" method="post" action="${path}/board/insert.do">
			<!-- 제목 입력 -->
		    <div>제목 <input name="title" id="title" size="80" placeholder="제목을 입력하세요"> 
		    </div>
		    <!-- 내용 입력 -->
		    <div style="width:100%;">
		        내용 <textarea id="content" name="content" rows="3" cols="80" placeholder="내용을 입력하세요"></textarea>
		    </div>
		    
		    <script>
				// ckeditor 적용
				//id가 content인 태그 (글의 내용을 입력하는 태그)를 ck에디터를 적용한다는 의미
				CKEDITOR.replace("content",{
				    filebrowserUploadUrl: "${path}/imageUpload2.do",
				    height: "500px"
				});
			</script>
				
		    <!-- 첨부파일 등록 -->
		    <div class="attachContainer"> 첨부파일을 등록하세요.
		        <div id="uploadedList"></div>
		        <div class="fileDrop">파일을 마우스로 끌어놓으세요</div>
		    </div>
		    <div style="width:100%; text-align:center;">
		        <button type="button" id="btnSave">확인</button>
		    </div>
		</form>
	</div>
</section>



<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>
</body>
</html> 