<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="include/header.jsp" %>
    
    <!-- image slide 관련 css 되면 이름 바꿀것 -->
    <link rel="stylesheet" href="/resources/css/slide.css"> 
    
    <!-- slick은 jQuery를 사용하기떄문에 jQuery에 대한 선언이 반드시 먼저 이루어진 후에 slick에 대한 내용을 받아올수 있습니다.  -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script src="/resources/js/image__slide.js" defer></script>

</head>
<body>

    <!--  --------header  ------------------------>
    <%@ include file="include/nav.jsp" %>

    <main>
        
        <section class="image__slide">
            <div><img src="${pageContext.request.contextPath}/resources/img/sgniture menu 1.png" alt=""></div>
            <div><img src="${pageContext.request.contextPath}/resources/img/signiture menu 2.png" alt=""></div>
            <div><img src="${pageContext.request.contextPath}/resources/img/카페 메인 1번임다.png" alt=""></div>
            <div><img src="${pageContext.request.contextPath}/resources/img/카페 메인 2번.png" alt=""></div>
            <div><img src="${pageContext.request.contextPath}/resources/img/카페 메인 3번.png" alt=""></div>
            <div><img src="${pageContext.request.contextPath}/resources/img/카페내부 테이블 배치 1.png" alt=""></div>
            <div><img src="${pageContext.request.contextPath}/resources/img/카페내부 테이블 배치 2.png" alt=""></div>
        </section>
        

    </main>



    <!-- ----------- footer  ------------------>
    <%@ include file="include/footer.jsp" %>
   
</body>
</html>