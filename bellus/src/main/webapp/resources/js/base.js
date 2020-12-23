var toggleBtn = document.querySelector('.navbar__toggleBtn');
var menu = document.querySelector('.navbar__menu');
var icons = document.querySelector('.navbar__icons');
var navMenu = document.querySelector('navbar__menu'); 


toggleBtn.addEventListener('click',() => {
    menu.classList.toggle('active');
    icons.classList.toggle('active');
}); 
// 버튼 클릭시 해당페이지로 이동하면서 클릭했던 내용이 저장되지않음 서버에서처리
// $('body').on('click','.navbar__menu li a',function(event){

//     $(this).addClass("selected");          
//     $(this).siblings().removeClass("selected");  //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰

// });