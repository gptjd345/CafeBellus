<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" href="/resources/css/view.css">
<!-- figcaption 폰트관련링크 -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&display=swap" rel="stylesheet">
    
<title>Insert title here</title>

<script>

//피키캐스트 이미지 수정화면 생성함수
$(function(){
	$('#btnWrite').click(function(event){
	    var pnum = $(event.target).next().val();
	
	    $.ajax({
			type : "get",
			url : "/view/write.do",
			success: function(result){
				$("#write-form").html(result);
				//태그의 css('속성','값')
				$("#write-form").css("display","block");
				
				}
			});
	
	});
});


//피키캐스트 이미지 수정화면 생성함수
$(function(){
	$('.btnModify').click(function(event){
	    var pnum = $(event.target).next().val();
	
	    $.ajax({
			type : "post",
			url : "/view/detail/"+pnum,
			success: function(result){
				$("#modify-form").html(result);
				//태그의 css('속성','값')
				$("#modify-form").css("display","block");
				
				}
			});
	
	});
});

</script>

</head>
<body>
<!--  --------header  ------------------------>
    <%@ include file="../include/nav.jsp" %>

	<main>
	<section class="view-Wrapper">
		<div class="view-Content">
				<header>
					<h2>View</h2>
				</header>
				<!-- 관리자만 수정 버튼을 볼수있다. -->
				<c:if test="${sessionScope.userid == 'admin'}">
					<input type="button" id="btnWrite" value="그림 추가">
				</c:if>
				<div id="write-form"></div>
				<div id="columns">
					<c:forEach var="row" items="${list}">
						<figure>
							<img src="${row.imagepath}" alt="${row.figcaption}">
							<figcaption>
								${row.figcaption} 
								<!-- 관리자만 수정 버튼을 볼수있다. -->
								<c:if test="${sessionScope.userid == 'admin'}">
									<input type="button" class="btnModify" value="수정" />
									<!-- 피키캐스트의 번호를 히든속성으로 저장 -->
									<input type="hidden" class="pikiNum" value="${row.pnum}"/>
								</c:if>	
							</figcaption>
						</figure>
					</c:forEach>
				</div>
				<div id="modify-form"></div>
		        <!-- <div id="columns">
		            <figure>
		                <img src="/resources/img/Gallery/0811_001.png" alt="">
		                <figcaption>
		                    WWW
		                </figcaption>
		
		            </figure>
		
		            <figure>
		                <img src="/resources/img/Gallery/0811_002.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_003.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_004.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_005.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_006.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_007.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_008.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_009.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_010.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_011.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_012.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_013.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_014.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_015.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_016.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/0811_017.png" alt="">
		                <figcaption>
		
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/초저녁카페 내부.png" alt="">
		                <figcaption>
		                    비오는 날
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/카푸치노.png" alt="">
		                <figcaption>
		                    카푸치노
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/크림브라우니.png" alt="">
		                <figcaption>
		                    크림브라우니
		                </figcaption>
		
		            </figure>
		            <figure>
		                <img src="/resources/img/Gallery/트위스트라떼.png" alt="">
		                <figcaption>
		                    트위스트라떼
		                </figcaption>
		
		            </figure>
		        </div> -->
		    </div>    	 
        </section>   
    </main>

<!-- ----------- footer  ------------------>
    <%@ include file="../include/footer.jsp" %>

</body>
</html>