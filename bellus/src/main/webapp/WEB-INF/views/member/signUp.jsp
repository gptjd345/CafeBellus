<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/member/signUp.css">

<!-- 폼데이터를 json 형태로 만들어줌 밑에 js에서 이걸 사용-->
<!-- <script type="text/javascript" src="/resources/js/jquery.serializeObject.min.js" defer></script>
<script type="text/javascript" src="/resources/js/jquery.serializeObject.js" defer></script> -->

<!-- 로그인 버튼 클릭시 처리 -->
<script type="text/javascript" src="/resources/js/member/signUp.js" defer></script>


<title>Insert title here</title>
</head>
<body>
<!--  --------header  ------------------------>
    <%@ include file="../include/nav.jsp" %>



<main>
    
   <section class="sign-up">
        <div class="content">
            <header>
                <h1>BELLUS</h1>
            </header>
            <section>
                <form action="" class="signUp-form" method="post">
                    <div class="input-group">
                        <label for="userid">아이디</label>
                        <input type="text" id="userid" name="userid"/>
                    </div>
                    <div class="input-group">
                        <label for="password1">비밀번호</label>
                        <input type="password" id="password1" name="password1"/>
                    </div>
                    <div class="input-group">
                        <label for="passwd">비밀번호 확인</label>
                        <input type="password" id="passwd" name="passwd"/>
                    </div>
                    <div class="input-group">
                        <label for="name">이름</label>
                        <input type="text" id="name" name="name"/>
                    </div>
                    <div class="input-group">
                        <label for="email">이메일</label>
                        <input type="text" id="email" name="email"/>
                    </div>
                    
                    <div class="input-group">
                        <button type="button" id="sign-up-btn">가입 하기</button>
                    </div>
                </form>
            </section>
        </div>
    </section>
        
    </main>








<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>
</body>
</html> 