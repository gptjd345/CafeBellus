<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/member/pwAnswer.css">

<!-- 폼데이터를 json 형태로 만들어줌 밑에 js에서 이걸 사용-->
<!-- <script type="text/javascript" src="/resources/js/jquery.serializeObject.min.js" defer></script>
<script type="text/javascript" src="/resources/js/jquery.serializeObject.js" defer></script> -->

<!-- 로그인 버튼 클릭시 처리 -->
<script type="text/javascript" src="/resources/js/member/pwAnswer.js" defer></script>


<title>Insert title here</title>
</head>
<body>

      <section class="pwAnswer-wrapper">
        <div class="pwAnswer-content">
            <section>
            
                <div id="PW-Answer-label">
                    <span id="choice">비밀번호 재설정</span>
                </div>
                <div id="pwAnswer-description">
                	<c:choose>
                		<c:when test="${map.result == 1}">
                    		<span>새로운 비밀번호를 입력해주십시오. </span>
                    	</c:when>
                    	<c:otherwise>
                    		<span>입력하신 정보와 일치하는 회원정보가 존재하지 않습니다. </span>
                    	</c:otherwise>
                    </c:choose>
                </div>
                <form action="" class="PW-find-form" method="post">
                    <div class="pwAnswer-input-group">
                    	<c:choose>
		                	<c:when test="${map.result == 1}">
		                		<div class="pwAnswer-input-group">
		                        	<label for="password1">새로운 비밀번호</label>
			                        <input type="password" placeholder="새로운 비밀번호" id="password1"/>
		                        </div>
		                        <div class="pwAnswer-input-group">
			                        <label for="passwd">비밀번호 확인</label>
			                        <input type="password" placeholder="비밀번호 확인" id="passwd" name="passwd"/>
		                        </div>
		                        <input type="hidden" name="userid" value="${map.userid}"/>
		                     </c:when>
	                     </c:choose>   
                    </div>
                    <div class="buttons">
                    	<c:choose>
		                	<c:when test="${map.result == 1}">
                        		<button type="button" id="success-submit">확인</button>
                        	</c:when>
                        	<c:otherwise>
                        		<button type="button" id="fail-submit">확인</button>
                        	</c:otherwise>
                        </c:choose>
                        		
                    </div>
                    
                </form>
            </section>
             
        </div>
    </section>
  
</body>
</html> 