$(function () {
	//아이디 입력 객체 찾기
	let $inputId = $("input[name=user_id]");

	// ----- 아이디 중복확인 버튼 클릭 START -----
	let $btIddupchk = $("button[name=iddupchk]");
	$btIddupchk.click(function () {
		if($inputId.val()==''){
			alert('아이디를 입력하세요');
			return;
		}
		$.ajax({
			url: "http://localhost:1124/back/iddupchk",
			type: "get",
			data: { user_id: $inputId.val() },
			success: function (jsonObj) {
			if (jsonObj.status == 1) {
				alert(jsonObj.msg);
			} else {
				alert(jsonObj.msg);
			}
			},
			error: function (jqXHR) {
			alert(jqXHR.status + ":" + jqXHR.statusText);
			},
		});
		return false; //
	});
	// ----- 아이디 중복확인 버튼 클릭 END -----


	let $inputPwd = $('input[name=user_pwd]').val();
	let $inputPwdChk = $('input[name=user_pwd_chk]').val();
	let $inputName = $('input[name=user_name]').val();
	let $inputPhone = $('input[name=user_phone]').val();
	let $inputEmail = $('input[name=user_email]').val();
	let $inputSsn = $('input[name=user_ssn]').val();

	
	
	// 가입버튼 입력 객체 찾기
	let $btSignup = $("button[name=signup]");
	$btSignup.click(function () {
		
		
		if($inputPwd == ''){
			alert('비밀번호를 입력하세요');
			return;
		}else if($inputPwdChk == ''){
			alert('비밀번호 확인을 입력하세요')
			return;
		}else if ($inputName == "") {
			alert('이름을 입력하세요');
			return;
		}else if ($inputPhone == "") {
			alert('전화번호를 입력하세요');
			return;
		}else if ($inputEmail == "") {
			alert('이메일을 입력하세요');
			return;
		}else if ($inputSsn == "") {
			alert('생년월일을 입력하세요');
			return;
		}	

		// ----- 비밀번호 중복확인 START -----
		let $inputPwd = $("input[name=user_pwd]");
		let $inputPwdChk = $("input[name=user_pwd_chk]");
				if ($inputPwd.val() != $inputPwdChk.val()) {
				alert("비밀번호가 일치하지 않습니다");
				$inputPwd.focus();
				return false;
			}
			// ----- 비밀번호 중복확인 END -----
			
			// let $form = $('form');
			// $form.submit(function () {
				let $formObj = $("form.signupstdt");
				let formData = new FormData($formObj[0]);
	// console.log(formData);
	// console.log("---------------------------------");
	$.ajax({
		url: "http://localhost:1124/back/signupstdt",
		type: "post", //
		processData: false, // processData false로 항상 설정 해 주어야 함
		contentType: false,
		data: formData,
		success: function (jsonObj) {
			if (jsonObj.status == 1) {
				alert("가입 성공");
				location.replace("http://localhost:1124/front/html/login.html");
			}
		},
		error: function (jqXHR) {
			alert(jqXHR.status + ":  가입실패");
		},
	});
	return false;
});
});
