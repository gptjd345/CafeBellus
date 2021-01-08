//아이디 입력확인
var idValidate = function(){
	var userId = $('#userid').val().trim();
	
	if(!userId){
		$('#userid').nextAll("*").remove();
        $('#userid').after('<span class="alert">아이디를 입력하세요. </span>');
	}
	else{
		$('#userid').nextAll("*").remove();
		$('#userid').after('<span class="success"></span>');
	}
};

$('#userid').on('blur',idValidate);


//비밀번호 입력확인
var pwValidate = function(){
	var passwd = $('#passwd').val().trim();
	
	if(!passwd){
		$('#passwd').nextAll("*").remove();
        $('#passwd').after('<span class="alert">비밀번호를 입력하세요. </span>');
	}
	else{
		$('#passwd').nextAll("*").remove();
		$('#passwd').after('<span class="success"></span>');
	}
};

$('#passwd').on('blur',pwValidate);


$('.input-group').on('click','button',function(e){

	idValidate();
	pwValidate();
	
	var userId = $('#userid').val().trim();
	var passWd = $('#passwd').val().trim();
	
	if($('.alert').length == 0)
	{
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
	else{
		alert("아이디와 비밀번호를 확인하세요!!!");
	}
	
    

});