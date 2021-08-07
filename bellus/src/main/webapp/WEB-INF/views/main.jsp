<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<% response.setHeader("Cache-Control", "no-store,no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="include/header.jsp" %>
    
    <!-- image slide 관련 css 되면 이름 바꿀것 -->
    <link rel="stylesheet" href="/resources/css/slide.css?version=1.2"> 
    
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
    <!-- 가상태그로 form 태그 생성하여 전송시 302응답코드뜨는지 확인 -->
    <div id="box">
    	<input type="submit" id="submitBaby"/>
    </div>
    
    <script type="text/javascript">
    $("#submitBaby").click(function(){
    	$.ajax({
    		data : JSON.stringify({userid : "gptjd345", passwd : "tkeoqn12"}),
    		url : "view",
    		type : "post",
    		dataType : "json",
    		contentType : "application/json; charset=UTF-8",
    		success : function(data) {
    			
    			console.log(data);
    			
    			var newForm = $('<form></form>');
    			
    			newForm.attr("id","newForm");
    			newForm.attr("method","post");
    			newForm.attr("action","/board");
    			
    			newForm.append($('<input/>', {type: 'hidden', name: 'mberId',value:'gptjd345'}));
    			newForm.append($('<input/>', {type: 'hidden', name: 'mberPwd',value:'tkeoqn12!!'}));
    			
    			newForm.appendTo('body');
    			
    			newForm.submit();
    		}
    			
    	})
    });
    </script>
<!--     <script type="text/javascript">
		var w = (window.screen.width/2) - 200;
		var h = (window.screen.height/2) - 200;
		var url = "/menu2.do"
    	window.open(url,"_blank","width=400,height=400,left="+w+",top="+h);
	</script>
 -->
    <!-- ----------- footer  ------------------>
    <%@ include file="include/footer.jsp" %>
   
</body>
</html>