<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/member/idAnswer.css">

<title>Insert title here</title>
</head>
<body>

      <section class="idAnswer-wrapper">
        <div class="idAnswer-content">
            <section>
                <div id="ID-find-label">
                    <span id="choice">아이디찾기</span>
                </div>
                <div id="description">
                	<c:choose>
	                	<c:when test="${userid == null}">
	                		<span>고객님의 정보와 일치하는 아이디가 없습니다. </span>
	                	</c:when>
	                	<c:otherwise>
	                    	<span>고객님의 정보와 일치하는 아이디입니다. </span>
	                    </c:otherwise>
                	</c:choose>
                </div>
                <form action="" class="ID-find-form">
                    <div class="input-group">
                    <c:if test="${userid != null}">
					    <span id="resultId">${userid}</span>
					</c:if>    
                    </div>
                    <div class="buttons">
                        <a href="./login.do">로그인</a>
                        <c:if test="${userid != null}">
                        	<a href="./pwInquery.do">비밀번호 찾기</a>
                        </c:if>
                    </div>
                </form>
            </section>
        </div>
    </section>
  
</body>
</html> 