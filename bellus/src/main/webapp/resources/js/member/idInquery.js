//name 유효성 검사

var nameValidate= function()
{
    var name = $('#name').val().trim();
    var nameCheck = RegExp(/^[가-힣\s]{2,4}$/);
	var nameCheck2 = RegExp(/^[a-zA-Z\s]{2,17}$/);
    if(!nameCheck.test(name))
    { 
    	if(!nameCheck2.test(name)){
	        $('#name').nextAll("*").remove();
	        $('#name').after('<span class="alert">올바른 이름 형식이 아닙니다.</span>');
        }else{
        	 $('#name').nextAll("*").remove();
        	 $('#name').after('<span class="success"></span>');
        }
    }
    else
    {
        $('#name').nextAll("*").remove();
        $('#name').after('<span class="success"></span>');
        
    } 
    
};

//name 유효성 검사
//한글2글자~4글자, 영어이름 
$('body').on('blur','#name',nameValidate);

//email 유효성 검사 
//숫자,영문자로 시작하며 @앞에 - _ .이 0 또는 1개  숫자, 영문이 0 또는 1개 @뒤에 숫자 영어 0개~1개 그리고 . 뒤에 영문 2~6글자 대소문자 구분 없음 
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
$('body').on('click','#form-submit',function(e)
{   
    nameValidate();
    emailValidate();
    
	if($('.success').length != 2)
	{
		alert("이름과 이메일을 확인해주세요!!!");
		
	}
	else
		{
            var data = $('.idInquery-form').serialize();

            $.ajax({
                url : '/member/idInquery.do',
                type : 'POST',
                data : data,
                dataType: 'text',
                success: function(result){
                
                    $("#next").html(result);
                    $("#prev").css("display","none");
                  
                }
            });
        }
});        



