$(function(){

    //레슨이 눌렸을 때 레슨상세페이지로 연결 
    //레슨 내역에서 아무거나 눌러도 연결되게 -> 만약 img같은것에도 
    //일일이 달아줘야하는 경우 설정하기 
    let $viewlsnObj = $('div.lsnlist');
    $viewlsnObj.click(function(){
        // location.href("/front/html/종우가정한링크")
        $(location).attr('href', '/front/html/mypropage.html');
    });
    
    //수강취소버튼 눌렀을 시 alert
    let $cancelObj = $('input[type=button]');













})