<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/member/idInquery.css">

<!-- 로그인 버튼 클릭시 처리 -->
<script type="text/javascript" src="/resources/js/member/idInquery.js" defer></script>


<title>Insert title here</title>
</head>
<body>
<!--  --------header  ------------------------>
    <%@ include file="../include/nav.jsp" %>



<main>
    
    <section class="wrapper">
        <div class="content">
            <header>
                <h1>BELLUS</h1>
            </header>
            <section>
                <nav class="inquery">
                    <a href="./idInquery.do" id="idInquery">아이디 찾기</a>
                    <a href="./pwInquery.do" id="pwInquery">비밀번호 찾기</a>
                </nav>
                <div id="next"></div>
                <div id="prev">
                <div id="choice-box">
                    <span id="choice">아이디 찾기</span>
                </div>
                <div id="description">
                    <span>이름과 이메일을 입력해주세요. </span>
                </div>
                <form action="#" class="idInquery-form">
                    <div class="input-group">
                        <label for="name">이름</label>
                        <input type="text" id="name" name="name"/>
                    </div>
                    <div class="input-group">
                        <label for="email">이메일</label>
                        <input type="text" id="email" name="email"/>
                    </div>
                    <div class="input-group">
                        <button type="button" id="form-submit">확인</button>
                    </div>
                </form>
                </div>	
            </section>
       	</div>     
    </section>
        
    </main>








<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>
</body>
</html> 