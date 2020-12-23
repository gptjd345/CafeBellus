<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/reply_list.css">
<%@ include file="../include/header.jsp" %>



<title>Insert title here</title>

</head>
<body>
<table id="reply_table" style="width:700px" >
<c:forEach var="row" items="${list}">
	<tr>
		<td>
			${row.replyer}
			(<fmt:formatDate value="${row.updatedate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
			<br>
			${row.replytext}
			<!-- 본인의 댓글만 수정, 삭제가 가능하도록 처리 -->
			<c:if test="${sessionScope.userid == row.replyer}">
				<input type="button" id="btnModify" value="수정" onclick="showReplyModify('${row.rno}')">
																
			</c:if>
			<hr>
		</td>
	</tr>
</c:forEach>
<!-- 페이지 나누기 -->
	<tr>
		<td>
			<c:if test="${pager.curBlock > 1}">
				<a href="javascript:listReplyRest('${pager.prevPage}')">[이전]</a>&nbsp;
			</c:if>
			
			<c:forEach var="num" 
                begin="${pager.blockBegin}"
                end="${pager.blockEnd}">
                <c:choose>
                
                    <c:when test="${num == pager.curPage}">
                    
                    <!-- 현재 페이지인 경우 하이퍼링크 제거 -->
                    <!-- 현재 페이지인 경우에는 링크를 빼고 빨간색으로 처리를 한다. -->
                        <span style="color:red;">${num}</span>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:listReplyRest('${num}')">${num}</a>&nbsp;
                    </c:otherwise>
                    
                </c:choose>
            </c:forEach>
            
            <c:if test="${pager.curBlock <= pager.totBlock}">
                <a href="javascript:listReplyRest('${pager.nextPage}')"><i class="fas fa-angle-right" title="다음"></i></a>
            </c:if> <!-- 현재 페이지블록이 총 페이지블록보다 작으면 다음으로 갈 수있도록 링크를 추가 -->

		</td>
	</tr>
</table>

<!-- 댓글 수정 영역 -->
<div id="modifyReply"></div>
</body>
</html> 