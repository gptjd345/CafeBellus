

var idValidate = function(){
    var ID = $('#userid').val().trim();
    
    var IdCheck = RegExp(/^[a-zA-Z0-9]{5,20}$/);
    var data = {"userid" : ID};
    if(!IdCheck.test(ID))
    { 
        $('#userid').nextAll("*").remove();
        $('#userid').after('<span class="alert">5~20자의 영문 대소문자, 숫자, 사용가능합니다.</span>');
    }
    else
    {
        $('#userid').nextAll("*").remove();
        //id중복확인해야함 ajax?
        $.ajax({
            url: "/member/idCheck.do",
            type: 'post',
            data: JSON.stringify(data),
            contentType : 'application/json',
            dataType: "json",
            success: function(result)
            {
                if(result==1)
                    $('#userid').after('<span class="alert">사용 중 이거나 휴면상태인 아이디입니다.</span>');
                else{
                    $('#userid').after('<span class="success">사용 가능한 아이디입니다.</span>');
                      
                }
            }
        });
    } 
  
};

//id 유효성 검사 
$('body').on('blur','#userid',idValidate); 

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

//Password2 유효성 검사
$('body').on('blur','#passwd',pw2Validate); 

//name 유효성검사 한글이면 한글만 영어면 영어만 둘다 섞어쓰기불가	
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


var emailValidate = function()
{
    var email = $('#email').val().trim();
    var emailCheck = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*(\.[a-zA-Z]{2,6}){1,2}$/i);
    if(!emailCheck.test(email))
    { 
        $('#email').nextAll("*").remove();
        $('#email').after('<span class="alert">올바른 이메일 형식이 아닙니다.</span>');
      
    }    
    else
    {
    	$('#email').nextAll("*").remove();
    	var data = {"email" : email};
        //email 중복확인해야함 ajax
        $.ajax({
            url: "/member/email_Check.do",
            type: 'post',
            data: JSON.stringify(data),
            contentType : 'application/json',
            dataType: "json",
            success: function(result)
            {
                if(result==1)
                    $('#email').after('<span class="alert">사용 중 이거나 휴면상태인 이메일입니다.</span>');
                else{
                    $('#email').after('<span class="success">사용 가능한 이메일입니다.</span>');
                      
                }
            }
        });
        
    } 
   
};

//email 유효성 검사
$('body').on('blur','#email',emailValidate);

//가입버튼 입력시 입력데이터들 유효한지 검사 맞으면 ajax 통신
$('body').on('click','#sign-up-btn',function(e)
{   
    idValidate();
    pw1Validate();
    pw2Validate();
  	nameValidate();
    emailValidate();
    
	if($('.alert').length == 0)
	{	
        var formSerializeArray = $('.signUp-form').serializeArray();
		var object = {};
		for (var i = 0; i < formSerializeArray.length; i++){
		    object[formSerializeArray[i]['name']] = formSerializeArray[i]['value'];
		}
		var json = JSON.stringify(object);
	
	 $.ajax({
       url: "/member/signUp.do",
       type: "post",
       data: json,
       dataType: 'text',
       contentType : 'application/json',
       success: function(result)
       {
           alert(result);
           alert("회원가입되었습니다.");
           window.location.replace('/member/login.do');
               
       }
             
  			});
	}else{
		alert($('.success').length);
		alert($('.alert').length);
	}
});