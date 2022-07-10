$(function() {
	
	// 아이디 입력 객체 찾기
    let $inputId = $('input[name=user_id]');
	
    // 가입버튼 객체 찾기
    let $btSignup = $('button[name=bt_signup]');
	
    //--- 아이디 중복확인 버튼 클릭 START ---
    let $btIddupchk = $('button[name=iddupchk]');
	
	console.log($btIddupchk);
	
    $btIddupchk.click(function () {
		$.ajax({
			url:'http://localhost:1124/back/iddupchk',
            type:'GET',
            data:{user_id : $inputId.val()},
            success: function(jsonObj){
				if(jsonObj.status == 1){
					alert(jsonObj.msg);
                }else{
					alert(jsonObj.msg);
                }
            },
            error: function(jqXHR){
				alert(jqXHR.status + ":" + jqXHR.statusText);
            }
        });
		console.log({user_id : $inputId.val()});
    })
    // ----- 아이디 중복확인 버튼 클릭 END -----
    
    // ----- 비밀번호 중복확인 START
    let $userpwd = $('input[name="user_pwd"]');
    let $userpwdchk = $('input[name="user_pwd_chk"]');
	
    if($userpwd.val() != $userpwdchk.val()){
		alert('비밀번호가 일치하지 않습니다.')
		$userpwd.focus();
		return false;
    }
    // ----- 비밀번호 중복확인 END ----- 
	
	// ----- 데이터를 한번에 보내 줄 form 객체 생성
	let $form = $('form');
	$form.submit(function(){
	let data = $(this).serialize();
	
	$.ajax({
		url :'http://localhost:1124/back/signupstdt',
		method :'POST',
			data:data,
			success: function(jsonObj){
				if(jsonObj.status ==1){
					alert(jsonObj.msg);
				}else{
					alert(jsonObj.msg);
				}
			},
				error:function(jqXHR){
					alert(jqXHR.status+":"+jqXHR.statusText);
				},
			});
			return false;
	});

});