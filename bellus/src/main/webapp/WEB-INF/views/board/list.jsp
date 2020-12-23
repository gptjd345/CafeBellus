<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>


<link rel="stylesheet" href="/resources/css/bulletin-board.css">


<!-- 게시판 페이지에서 글쓰기 버튼 눌렀을때 , 게시글 페이지 번호를 받아서 게시글 리스트를 바꿔주는 함수 포함 -->
<script>
	$(function(){
		$("#btnWrite").click(function(){
			location.href="${path}/board/write.do";
		});
	});

	function list(page){
		location.href="${path}/board/list.do?curPage="+page
			+"&search_option=${map.search_option}"
			+"&keyword=${map.keyword}";
	
		}

</script>
<title>Insert title here</title>
</head>
<body>
<!--  --------header  ------------------------>
    <%@ include file="../include/nav.jsp" %>
    
 <!---------------  main 시작 -------------------->   
    
<section class="board-Wrapper">
	<div class="board-Content">    
		<header>
			<h2>Bulletin board</h2>
		</header>
		
		<form name="search-form" class="search-form" method="post" action="${path}/board/list.do">
			<select name="search_option">
				<c:choose>
					<c:when test="${map.search_option == 'all' }">
						<option value="all" selected>작성자+내용+제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
						<option value="title" >제목</option>
					</c:when>
					<c:when test="${map.search_option == 'writer' }">
						<option value="all" >작성자+내용+제목</option>
						<option value="writer" selected>이름</option>
						<option value="content">내용</option>
						<option value="title" >제목</option>
					</c:when>
					<c:when test="${map.search_option == 'content' }">
						<option value="all" >작성자+내용+제목</option>
						<option value="writer">작성자</option>
						<option value="content" selected>내용</option>
						<option value="title" >제목</option>
					</c:when>
					<c:when test="${map.search_option == 'title' }">
						<option value="all" >작성자+내용+제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
						<option value="title" selected>제목</option>
					</c:when>
				</c:choose>	
			</select>
			<input name="keyword" value="${map.keyword}"/>
			<input type="submit" value="조회"/>
		</form>
		
		
		
		<div class=write_button_wrapper>
			
				<button type="button" id="btnWrite">글쓰기</button>
				<div><span id="list_count">${map.count} </span> 개의 게시물이 있습니다.</div>
			
		</div>
		
		
		<table border="1">
			<tr>
				<th class="w70">번호</th>
				<th class="expand">제목</th>
				<th class="w80">작성자</th> <!-- 이름말고 작성자라고하고 작성자의 ID를 넣을생각임 -->
				<th class="expand">날짜</th>
				<th class="w60">조회수</th>
			</tr>
			<!-- forEach var="개별데이터" items="집합데이터" -->
		<c:forEach var="row" items="${map.list}"> <!-- 컨트롤러에서 map안에 list를 넣었기 때문에 이렇게 받는다. -->
			<tr>
				<td>${row.bno}</td> <!-- 글 번호 -->
				<td>
				<a class="detail_link" href="${path}/board/detail.do?bno=${row.bno}
				&curPage=${map.pager.curPage}
				&search_option=${map.search_option}
				&keyword=${map.keyword}">${row.title}</a> <!-- 글 제목 -->
				<!-- 댓글의 개수 -->
				<c:if test="${row.cnt > 0}">
					<span style="color:red;">( ${row.cnt} )</span>
				</c:if>	
				</td> 
				<td>${row.writer}</td> <!-- 작성자 아이디 -->
				
				<td><fmt:formatDate value = "${row.updatedate}" pattern="yyyy-MM-dd HH:mm:ss" /></td> <!-- 날짜의 출력형식을 변경 -->
				<td>${row.viewcnt}</td> <!-- 조회수 -->
			</tr>
		</c:forEach>
		<!--  페이지 네비게이션  현재 페이지의 list함수에 변수를 넣어줌-->
			<tr>
				<td colspan="5" align="center">
					<c:if test="${map.pager.curBlock > 1}">
						<a href="javascript:list('${map.pager.prevPage}')"><i class="fas fa-angle-left" title="이전"></i></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					</c:if>
				
					<c:if test="${map.pager.curBlock > 1}">
						 <a href="javascript:list('1')">1</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					
						
					 <c:forEach var="num" 
		                begin="${map.pager.blockBegin}"
		                end="${map.pager.blockEnd}">
		                <c:choose>
		                    <c:when test="${num == map.pager.curPage}">
		                    
		                    <!-- 현재 페이지인 경우 하이퍼링크 제거 -->
		                    <!-- 현재 페이지인 경우에는 링크를 빼고 빨간색으로 처리를 한다. -->
		                        <span style="color:red;">${num}</span>&nbsp;
		                    </c:when>
		                    <c:otherwise>
		                        <a href="javascript:list('${num}')">${num}</a>&nbsp;
		                    </c:otherwise>
		                    
		                </c:choose>
		            </c:forEach>
		            
		            <c:if test="${map.pager.curPage < map.pager.totPage}">
		                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:list('${map.pager.totPage}')">${map.pager.totPage} </a>
		            </c:if> <!-- 현재 페이지블록이 총 페이지블록보다 작으면 끝으로 갈 수 있도록 링크를 추가함-->
		            
		            <c:if test="${map.pager.curBlock < map.pager.totBlock}">
		                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:list('${map.pager.nextPage}')"><i class="fas fa-angle-right" title="다음"></i> </a>
		            </c:if> <!-- 현재 페이지블록이 총 페이지블록보다 작으면 다음으로 갈 수있도록 링크를 추가 -->
		            
		            
		            
		          </td>
		     </tr>
		
		</table>
	</div>	
</section>





<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>
</body>
</html> 