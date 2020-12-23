<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!-- 이미헤더 메뉴에 view 버튼에 해당하는 페이지로 이미 view.jsp를 만들었기 때문에 게시글의 상세페이지는 detail.jsp로한다.  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/detail.css">
<!-- 자주쓰이는 놈들 위해 만들어놓은 js파일 -->
<script type="text/javascript" src="/resources/js/common.js"></script>
 
<!-- ckeditor의 라이브러리를 사용하기 위해 추가하는 코드 -->
<script src="/resources/ckeditor/ckeditor.js"></script>

<!-- 게시글 상세페이지 수정, 첨부파일(추가,삭제),  -->
<script type="text/javascript" src="/resources/js/detail.js"></script>




<script>
$(function(){ //자동으로 실행되는 코드
    //처음 시작할때 댓글 목록 출력
    // listReply("1");  
    listReplyRest("1"); //첫번째 페이지에 댓글이 나오게 함 rest방식
    
    //댓글 쓰기
    $("#btnReply").click(function(){
        //reply(); 폼데이터로 입력
         replyJson(); //json으로 입력
     });
    
    //목록 버튼을 클릭하면 버튼클릭시 상세보기 화면에 있던 페이지, 검색옵션, 키워드 값을 가지고 목록으로 이동
    $("#btnList").click(function(){
        location.href="${path}/board/list.do?curPage=${curPage}&search_option=${search_option}&keyword=${keyword}";

    });

	//첨부파일 리스트를 출력하는 함수
	listAttach();
      
    //게시글 삭제
   $("#btnDelete").click(function(){
	//댓글이 존재하는 게시물의 삭제 처리 방지
	var count ="${count}";
	//댓글의 수가 0보다 크면 팝업, 함수 종료
	if(count > 0) {
		alert("댓글이 있는 게시물은 삭제 할 수 없습니다.");
		return;
		}   
	//댓글의 수가 0이면 삭제 처리
    if(confirm("삭제하시겠습니까?")){
        document.form1.action = "${path}/board/delete.do";
        document.form1.submit(); //id가 form1인 form태그를 위의 url로 전송한다. 
        
								}     
   									});
});		   

//첨부파일 리스트를 출력하는 함수
function listAttach(){
    $.ajax({
        type: "post",
        url: "${path}/board/getAttach/${dto.bno}",
        success: function(list){
            // list 변수 각각에 대하여
            //console.log(list);
            $.each(list, function(){
                var fileInfo=getFileInfo(this);
                //console.log(fileInfo);
                //파일정보를 보는 하이퍼링크를 만듬 (div에 하이퍼링크를 만듬)
                var html="<div class='listAttachWrapper'><a class='listAttach' href='"+fileInfo.getLink+"'>"
                    +fileInfo.fileName+"</a>&nbsp;&nbsp;";

                    //-------파일 삭제 -------
                    //세션에 담긴 userid와 dto에 담긴 작성자가 같으면
                    //담아 놓은 파일을 삭제하기 위해서 링크를 건다.
                <c:if test="${sessionScope.userid == dto.writer}">    
                    html+="<a href='#' class='file_del' data-src='"
                        +this+"'>[삭제]</a></div>";
                </c:if>
                $("#uploadedList").append(html);
            });
        }
    });
}
   
// 댓글 쓰기 함수 (json방식)
function replyJson(){
	var replytext=$("#replytext").val().trim(); // 댓글 내용
	if(replytext == null || replytext ==""){
		alert("댓글을 입력하세요");
		return ;
		}
    var bno="${dto.bno}"; // 게시물 번호
    var secret_reply="n"; // 비밀댓글 체크여부 
    if($("#secret_reply").is(":checked")){
    	secret_reply="y";
    }
    $.ajax({
        type: "post",
        url: "${path}/reply/insertRest.do",
        headers: {"Content-Type" : "application/json"},
        dataType: "text",
        data: JSON.stringify({
        	bno : bno,
        	replytext : replytext,
        	secret_reply : secret_reply
        }),
        success: function(){
            alert("댓글이 등록되었습니다.");
            //댓글 작성시 댓글 쓰기 textarea 초기화
            $("#replytext").val(''); 
            listReplyRest("1");   
        }
	});
}
			
// 댓글 목록 - Rest방식
function listReplyRest(num){
	$.ajax({
		type: "post",
		url: "${path}/reply/list/${dto.bno}/"+num,
		success: function(result){
			// responseText가 result에 저장
			$("#listReply").html(result);
		}
	});
}
//댓글 수정화면 생성함수
function showReplyModify(rno){
	$.ajax({
		type : "post",
		url : "${path}/reply/detail/"+rno,
		success: function(result){
			$("#modifyReply").html(result);
			//태그의 css('속성','값')
			$("#modifyReply").css('display','block');
			$("#detailReplytext").focus();
			
		}
	});
}

</script>

<title>Insert title here</title>
</head>
<body>
<!--  --------header  ------------------------>
    <%@ include file="../include/nav.jsp" %>


<!-----------------------  main --------------------------------- -->

<section class="board-Wrapper">
	<div class="board-Content">
		<header style="width:100%;">
			<h2>게시물 보기</h2>
		</header>
		
		<!-- 게시물을 작성하기 위해 컨트롤러의 insert.do로 맵핑 -->
		<form id="form1" name="form1" method="post" action="${path}/board/insert.do">
		    <div style="width:100%;">제목 : <input name="title" id="title" value="${dto.title}" placeholder="제목을 입력하세요">
		<!-- placeholder은 제목을 입력할 수 있도록 도움말을 출력함 -->
		    </div>
		    <div style="width:100%;">조회수 : ${dto.viewcnt}    </div>
		    <div style="width:100%;" id="content_box">
		        내용 <textarea style="width:100%;" id="content" name="content" 
		placeholder="내용을 입력하세요">${dto.content}</textarea>
		 
		<!-- 마찬가지로 내용을 입력하도록 도움말을 출력함 -->
		<script>
		// ckeditor 적용
		//id가 content인 태그 (글의 내용을 입력하는 태그)를 ck에디터를 적용한다는 의미
		CKEDITOR.replace("content",{
		    filebrowserUploadUrl: "${path}/imageUpload2.do",
		    height: "500px"
		});
		</script>
		
		    </div>
		    <div style="width:100%;"> 첨부파일 : 
		        <div style="width:100%;" id="uploadedList"></div>
		        <div class="fileDrop">파일을 마우스로 끌어놓으세요</div>
		    </div>
		
		   
				<!-- 수정,삭제에 필요한 글번호를 hidden 태그에 저장 -->    
		        <input type="hidden" name="bno" value="${dto.bno}">
		        
		        <!-- 버튼을 위한 flexbox 컨테이너 -->
		        <div class="buttons">
			        <!-- 목록은 본인이 아니어도 확인이 가능하게 할 예정 -->
			        <button type="button" id="btnList" class="btn">목록</button>
			        
			        <div id="updateAndDelete">
				        <!-- 본인만 수정,삭제 버튼 표시 -->
				        <c:if test="${sessionScope.userid == dto.writer}">
				            <button type="button" id="btnUpdate">수정</button>
				            <button type="button" id="btnDelete">삭제</button>
				        </c:if>
			        </div>
		        </div>
		        
		        
		   
		</form>
		
		
		<!-- 댓글 작성 -->
		<div style="width:100%;">
			<!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
		     <c:if test="${sessionScope.userid != null }">
		         <textarea style="width:100%;" id="replytext" placeholder="댓글을 작성하세요"></textarea>
		         <br>
		         <input type="checkbox" id="secret_reply">비밀 댓글
		         <button type="button" id="btnReply">댓글쓰기</button>
		     </c:if>
		</div>
		<!-- 댓글 목록 -->
		<div id="listReply" ></div>
	</div>	
</section>		 
		
		
		
		






<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>
</body>
</html> 