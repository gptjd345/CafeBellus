//id 유효성 검사 
$('body').on('blur','#userId',function(e)
{   
    var ID = $(this).val().trim();
    
    var IdCheck = RegExp(/^[a-zA-Z0-9]{5,20}$/);

    if(ID == null || ID == "")
    { 
        $(this).nextAll("*").remove();
        $(this).after('<span class="alert">아이디를 입력해주세요.</span>');
    }
    else if(!IdCheck.test(ID))
    { 
        $(this).nextAll("*").remove();
        $(this).after('<span class="alert">5~20자의 영문 대소문자, 숫자, 사용가능합니다.</span>');
    }
    else
    {
        $(this).nextAll("*").remove();
        $(this).after('<span class="success"></span>');
        
    } 

}); 

//name 유효성 검사
$('body').on('blur','#userName',function(e)
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
 

//가입버튼 입력시 입력데이터들 유효한지 검사
$('body').on('submit','.pwInquery-form',function(e)
{   
	
	if($('.success').length != 2)
	{
		alert("입력한 내용을 확인하십시오.")
		e.preventDefault();
	}
	else
		{
		alert("성공");
        }
});        



