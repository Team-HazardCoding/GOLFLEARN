$(function () {
	//아이디 입력 객체 찾기
	let $inputId = $("input[name=user_id]");
	
	let $btSignup = $("button[name=signup]");
	// ----- 아이디 중복확인 버튼 클릭 START -----
	let $btIddupchk = $("button[name=iddupchk]");
	$btIddupchk.click(function () {
		$.ajax({
			url: "http://localhost:1124/back/iddupchk",
			method: "get",
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
});
  	// ----- 아이디 중복확인 버튼 클릭 END -----

  	// 가입버튼 입력 객체 찾기
	// $btSignup.submit(function () {
	//$btSignup.click(function () {
    // ----- 비밀번호 중복확인 START -----
    // ----- 비밀번호 중복확인 END -----
	
    let $formObj = $("#signupstdt").serialize();
    // let formObj =($(this)[0]);
	
	$formData.submit(function() {

		let $inputPwd = $("input[name=user_pwd]");
		let $inputPwdChk = $("input[name=user_pwd_chk]");
		if ($inputPwd.val() != $inputPwdChk.val()) {
			alert("비밀번호가 일치하지 않습니다");
			$inputPwd.focus();
			return false;
		}
		let url = "http://localhost:8888/back/signupstdt";
		$.ajax({
			url: url,
			method : "post",
			processData: false, // processData false로 항상 설정 해 주어야 함
			contentType: false,
			data: $formObj,
			success: function(data) {
				if (data.status == 1) {
					alert(data);
					console.log("성공");
					alert("알럿");
				}
			},
			error: function (jqXHR) {
				alert(jqXHR.status + ":" + jqXHR.statusText);
					console.log("실패");
			// location.href = "http://localhost:1124/front/html/login.html";
			},
		});
		return false;
	});
});
