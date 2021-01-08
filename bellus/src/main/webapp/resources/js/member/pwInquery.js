//id 유효성 검사 
var idValidate = function(){
    var ID = $('#userid').val().trim();
    
    var IdCheck = RegExp(/^[a-zA-Z0-9]{5,20}$/);
    
    if(!ID)
    { 
       	$('#userid').nextAll("*").remove();
        $('#userid').after('<span class="alert">아이디를 입력해주세요!</span>');
    }
    else{
        $('#userid').nextAll("*").remove();
        $('#userid').after('<span class="success"></span>');
    }    
}

//id 유효성 검사 
$('body').on('blur','#userid',idValidate); 

var emailValidate = function()
{
    var email = $('#email').val().trim();
    var emailCheck = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,6}$/i);
    if(!emailCheck.test(email))
    { 
        $('#email').nextAll("*").remove();
        $('#email').after('<span class="alert">올바른 이메일 형식이 아닙니다.</span>');
      
    }    
    else
    {
        $('#email').nextAll("*").remove();
        $('#email').after('<span class="success"></span>');
        
    } 
   
};

//email 유효성 검사
$('body').on('blur','#email',emailValidate);
 

//가입버튼 입력시 입력데이터들 유효한지 검사
$('body').on('click','#pwInquery-submit',function(e)
{   
    idValidate();
    emailValidate();
	
	if($('.success').length != 2)
	{
		alert("입력한 내용을 확인하십시오.")
	}
	else
		{
            var data = $('.pwInquery-form').serialize();
           

            $.ajax({
                url : '/member/pwInquery.do',
                type : 'post',
                data : data,
                dataType: 'text',
                success: function(result){
                    $("#next").html(result);
                    $("#prev").css("display","none");
                  
                }
            });
        }
});        



