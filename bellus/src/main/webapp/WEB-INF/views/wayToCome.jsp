<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/WaytoCome.css">

<!-- 카카오 지도 API -->
<!-- 지도관련 -->

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2616aabdd722b0973cb4421e9a6a6fdc"></script>
 <script type="text/javascript" src="/resources/js/wayToCome.js" defer></script>

<title>Insert title here</title>
</head>
<body>
<!--  --------header  ------------------------>
    <%@ include file="include/nav.jsp" %>

<main>
	<section class="wayToCome-Wrapper">	
		<div class="wayToCome-Content">  
			<header>
				<h2>Way &nbsp;To&nbsp; Come</h2>
			</header>
			<div id="map"></div>  
		</div>
	 </section>
		
</main>





<!-- ----------- footer  ------------------>
    <%@ include file="include/footer.jsp" %>
   
</body>
</html> 