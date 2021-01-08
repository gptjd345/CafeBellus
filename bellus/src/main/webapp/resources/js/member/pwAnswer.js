//password1 유효성 검사
var pw1Validate = function()
{
    var PW1 = $('#password1').val().trim();
    var PWCheck = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/);
    
    if(!PWCheck.test(PW1))
    { 
        $('#password1').nextAll("*").remove();
        $('#password1').after('<span class="alert"> 8~16 자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</span>');
    }    
    else
    {
        $('#password1').nextAll("*").remove();
        $('#password1').after('<span class="success">사용 가능한 비밀번호입니다.</span>');
        
    } 
    
};
//Password1 유효성 검사
//8~16 자 영문 대 소문자, 숫자, 특수문자를 사용하세요.
$('body').on('blur','#password1',pw1Validate);

//passwd 유효성 검사
var pw2Validate = function(){
    var PW2 = $('#passwd').val().trim();
    $('#passwd').nextAll("*").remove();
    
    if(PW2 == "" || PW2 == null)
    {
        $('#passwd').after('<span class="alert">비밀번호를 입력하세요</span>');

    }
    else if(PW2 != $('#password1').val().trim())
    { 
        $('#passwd').after('<span class="alert">비밀번호가 일치하지 않습니다.</span>');
    }
    else
    {
        $('#passwd').after('<span class="success">비밀번호가 일치합니다.</span>');
         
    } 
   
};


//passwd 유효성 검사
$('body').on('blur','#passwd',pw2Validate); 
 

//비밀번호 재설정 컨트롤러로 이동
$('body').on('click','#success-submit',function(e)
{   
	pw1Validate();
    pw2Validate();
	
	if($('.alert').length != 0)
	{
		alert($('.alert').length);
		alert($('.success').length);
	}
	else
		{
			var data = $('.PW-find-form').serialize();
			
        	 $.ajax({
                url : '/member/update.do',
                type : 'POST',
                data : data,
                dataType: 'text',
                success: function(result){
                	alert("비밀번호가 재설정 되었습니다.");
                    window.location.replace('/member/login.do');
                  
                }
            });
        }
});     

//비밀번호 재설정 불가 로그인 페이지로 리다이렉트
$('body').on('click','#fail-submit',function(e){
	window.location.replace('/member/login.do');

});   



