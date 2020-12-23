var CheckPW2 = function(){
    var PW2 = $('#checkPW').val().trim();
    $('#checkPW').nextAll("span").remove();
    
    if(PW2 == "" || PW2 == null)
    {
        $('#checkPW').after('<span class="alert">비밀번호를 입력하세요</span>');

    }
    else if(PW2 != $('#newPW').val().trim())
    { 
        $('#checkPW').after('<span class="alert">비밀번호가 일치하지 않습니다.</span>');
    }
    else
    {
        $('#checkPW').after('<span class="success">비밀번호가 일치합니다.</span>');
     
    } 
} 

//Password1 유효성 검사

$('body').on('blur','#newPW',function(e)
{   
    var PW1 = $(this).val().trim();
    var PWCheck = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/);
    
    if(!PWCheck.test(PW1))
    { 
        $(this).nextAll("span").remove();
        $(this).after('<span class="alert">8~16 자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</span>');
    }    
    else
    {
        $(this).nextAll("span").remove();
        $(this).after('<span class="success">사용 가능한 비밀번호입니다.</span>');
        CheckPW2();
    } 

    
}); 


//Password2 유효성 검사
$('body').on('blur','#checkPW',CheckPW2); 
 

//가입버튼 입력시 입력데이터들 유효한지 검사
$('body').on('submit','.PW-find-form',function(e)
{   
	
	if($('.success').length != 2)
	{
		alert("입력한 내용을 확인하십시오.")
		e.preventDefault();
	}
	else
		{
        alert("비밀번호가 변경되었습니다.");
        history.back();
        }
});        



