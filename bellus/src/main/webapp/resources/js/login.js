
$('.input-group').on('click','button',function(e){
    var userId = $("#userid").val().trim();
    var passWd = $("#passwd").val().trim();
    
    if(userId=="" || userId == null){
        alert("아이디를 입력하세요.");
        $("#userid").focus();
        return;
    }
    else if(passWd=="" || passWd == null){
        alert("비밀번호를 입력하세요");
        $("#passid").focus();
        return;
    }
    else {
	    $.ajax({
	        url: "./login_check.do",
	        type: 'post',
	        headers: {"Content-Type" : "application/json"},
	        data: JSON.stringify({
	            userid : userId,
	            passwd : passWd
	        }),
	        dataType: "text",
	        success: function(result)
	        {
	            if(result == "success"){
	                alert("로그인 되었습니다.");
	                window.location.replace('/');
	            }
	            else if(result == "error"){
	                alert("아이디 혹은 비밀번호를 잘못입력하셨습니다.");
	                window.location.href = './login.do';
	            }
	        }
	    });
    }


});