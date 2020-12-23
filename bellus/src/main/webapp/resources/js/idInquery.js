//name 유효성 검사
$('body').on('blur','#username',function(e)
{   
    var name = $(this).val().trim();

    if(name == null || name == "")
    { 
        $(this).nextAll("*").remove();
        $(this).after('<span class="alert">이름을 입력해주세요.</span>');
    }
    else
    {
        $(this).nextAll("*").remove();
        $(this).after('<span class="success"></span>');
    } 

}); 

//Password 유효성 검사
$('body').on('blur','#password',function(e)
{   
    var PW1 = $(this).val().trim();
    var PWCheck = RegExp(/^[a-zA-Z0-9]{5,20}$/);
    
    if(!PWCheck.test(PW1))
    { 
        $(this).nextAll("*").remove();
        $(this).after('<span class="alert">비밀번호를 입력해주세요. </span>');
    }    
    else
    {
        $(this).nextAll("*").remove();
        $(this).after('<span class="success"></span>');
        CheckPW2();
    } 

    
}); 

//가입버튼 입력시 입력데이터들 유효한지 검사
$('body').on('submit','.idInquery-form',function(e)
{   
	
	if($('.success').length != 2)
	{
		alert("필수데이터를 입력하세요!")
		e.preventDefault();
	}
	else
		{
		alert("밍밍");
        }
});        



