<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/login.css">

<!-- 로그인 버튼 클릭시 처리 -->
<script type="text/javascript" src="/resources/js/member/login.js" defer></script>
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
                <form action="" class="login-form" name = "form1" method="post">
                    <div class="input-group">
                        <input type="text" placeholder="아이디" id="userid" name="userid"/>
                    </div>
                    <div class="input-group">
                        <input type="password" placeholder="비밀번호" id="passwd" name="passwd"/>
                    </div>
                    <div class="input-group">
                        <button type="button" id="btnLogin">로그인</button>
                    </div>
                </form>
            </section>
            <footer>
                <a href="./idInquery.do" title="Fotgot ID">아이디 찾기 </a> | 
                <a href="./pwInquery.do" title="Forgot Password">비밀번호 찾기</a> |
                <a href="./signUp.do" title="SignUp">회원 가입</a>
            </footer> 
             
        </div>
    </section>
        
        

    </main>








<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>
</body>
</html> 