//이걸 왜 인식못하는지 모르겠다.
//var Name = $('#username').val();

//id 유효성 검사 
$('body').on('blur','#ID',function(e)
{   
    var ID = $(this).val().trim();
    
    var IdCheck = RegExp(/^[a-zA-Z0-9]{5,20}$/);
    
    if(!IdCheck.test(ID))
    { 
        $(this).nextAll("*").remove();
        $(this).after('<span class="alert">5~20자의 영문 대소문자, 숫자, 사용가능합니다.</span>');
    }
    else
    {
        $(this).nextAll("*").remove();
        //id중복확인해야함 ajax?
        $.ajax({
            url: "./idCheck",
            type: 'post',
            data:{"id":ID },
            success: function(result)
            {
                if(parseInt(result)==1)
                    $('#ID').after('<span class="alert">사용 중 이거나 휴면상태인 아이디입니다.</span>');
                else{
                    $('#ID').after('<span class="success">사용 가능한 아이디입니다.</span>');
                      
                }
            }
        });
    } 

}); 

var CheckPW2 = function(){
    var PW2 = $('#password2').val().trim();
    $('#password2').nextAll("*").remove();
    
    if(PW2 == "" || PW2 == null)
    {
        $('#password2').after('<span class="alert">비밀번호를 입력하세요</span>');

    }
    else if(PW2 != $('#password1').val().trim())
    { 
        $('#password2').after('<span class="alert">비밀번호가 일치하지 않습니다.</span>');
    }
    else
    {
        $('#password2').after('<span class="success">비밀번호가 일치합니다.</span>');
     
    } 
} 

//Password1 유효성 검사
$('body').on('blur','#password1',function(e)
{   
    var PW1 = $(this).val().trim();
    var PWCheck = RegExp(/^[a-zA-Z0-9]{5,20}$/);
    
    if(!PWCheck.test(PW1))
    { 
        $(this).nextAll("*").remove();
        $(this).after('<span class="alert">5~20자의 영문 대소문자, 숫자, 사용가능합니다.</span>');
    }    
    else
    {
        $(this).nextAll("*").remove();
        $(this).after('<span class="success">사용 가능한 비밀번호입니다.</span>');
        CheckPW2();
    } 

    
}); 


//Password2 유효성 검사
$('body').on('blur','#password2',CheckPW2); 


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

//가입버튼 입력시 입력데이터들 유효한지 검사
$('body').on('submit','.signUp-form',function(e)
{   
	
	if($('.success').length != 4)
	{
		alert("필수데이터를 입력하세요!")
		e.preventDefault();
	}
	else
		{
		alert("밍밍");
		}
	
	
	
	
    //유효성 검사 실패한부분이 없다면 action속성에 값을 추가하여 서블릿으로 데이터가 전송됨
//     if($('.input-group').find(".success").length == 4)
//     {
//         $('.signUp-form').attr('action','./signUp');
//     } //근데 이 방식은 버튼 누를때 form 액션이 진행되는데 스크립트가 우선인가 html이 우선인가? 애매

//    var ID = $('#ID').val();
//    var pw = $('#password1').val();
//    var username = $('#username').val();
//    
//    $.ajax({
//        url: "./signUp",
//        type: "post",
//        data:{
//                "id" : ID,
//                "pw" : pw,
//                "name" : username
//             },
//   
//        success: function(result)
//        {
//            alert(result);
//            alert("회원가입되었습니다.");
//            history.back();
//                
//        }
//              
//    });
	
//	var formData = JSON.stringify($('form').serializeObject());
	//
//		 $.ajax({
//	       url: "./signUp",
//	       type: "post",
//	       data: formData,
//	       dataType: "JSON",
//	       contentType : "application/json; char=UTF-8",
//	       
	//  
//	       success: function(result)
//	       {
//	           alert(result);
//	           alert("회원가입되었습니다.");
//	           history.back();
//	               
//	       }
//	             
	//   });
    
});