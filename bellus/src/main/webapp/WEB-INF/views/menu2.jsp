<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="include/header.jsp" %>
<link rel="stylesheet" href="/resources/css/menu.css">
<title>Insert title here</title>
</head>
<body>
<form id="jeongA" name="jeongA"  method="post">
	<input type="text" name="id"/>
	<input type="password" name="pw"/>
	<button type="button" id = "fsubmit" name = "fsubmit">전송</button>
	<button type="button" onclick="self.close();">닫기</button>
</form>


<main>
	<section class="menu-Wrapper">	
		<div class="menu-Content">  
			<header>
				<h2>Menu</h2>
			</header>
		    <div id="menu"><img src="/resources/img/카페메뉴판.jpg" alt="카페메뉴사진"></div>    
        </div>
     </section>   
        

</main>
	<script type="text/javascript">
/* 	$('#fsubmit').click(function(){
		
		$.ajax({
			url :"board/jeongA"
			data : $("#jeongA").serialize();
			type : "post"
			success : function(result)
			{
			
				alert("저장완료!!");
				window.open('','_blank').self.close();
				
			}
		});
		
		
	
		
		
	}); */
	$('#fsubmit').click(function(){
		document.jeongA.action = "board/jeongA";
		document.jeongA.submit();
		opener.reloadPage();
		opener.close();

	});
	function fsubmitClose()
	{
		self.close();
	}
	</script>

	<!-- ----------- footer  ------------------>
    <%@ include file="include/footer.jsp" %>
</body>
</html>