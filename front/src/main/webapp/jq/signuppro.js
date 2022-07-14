$(function () {
	//아이디 입력 객체 찾기
	let $inputId = $("input[name=user_id]");

	// ----- 아이디 중복확인 버튼 클릭 START -----
	let $btIddupchk = $("button[name=iddupchk]");

	$btIddupchk.click(function () {
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
		return false;
	});
  // ----- 아이디 중복확인 버튼 클릭 END -----

  // 데이터를 한번에 보낼 form 객체 생성

	// 가입버튼 입력 객체 찾기
	let $btSignup = $("button[name=signup]");

	// 가입하기 버튼 클릭 이벤트 발생
	$btSignup.click(function () {

		// ----- 비밀번호 중복확인 START -----
		let $inputPwd = $("input[name=user_pwd]");
		let $inputPwdChk = $("input[name=user_pwd_chk]");
		if ($inputPwd.val() != $inputPwdChk.val()) {
			alert("비밀번호가 일치하지 않습니다");
			$inputPwd.focus();
			return false;
		}
    	// ----- 비밀번호 중복확인 END -----

		// 데이터 전송을 위한 폼 객체 불러옴
		let $formObj = $("form.signuppro");
		// 불러온 폼 객체를 FormData parameter에 담아줌
		//[0]은 첫번째 form을 의미함
		let formData = new FormData($formObj[0]);
		// formObj[1][0] - 두번째 form의 첫번째 요소를 의미

		$.ajax({
			url: "http://localhost:1124/back/signuppro",
			type: "post",
			processData: false,
			contentType: false,
			data: formData,
			success: function (jsonObj) {
				if (jsonObj.status == 1) {
					alert("가입 성공"); // jsonObj.msg로 작성 시 오류
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

// processData false로 항상 설정 해 주어야 함
// 일반적으로 서버에 전달되는 데이터는 query string 형태
// data 파라미터로 전달된 데이터를 jQuery 내부적으로 query string 형태로 만드는 데, 파일 전송의 경우 이를 하지 않아야하므로 processData를 false로 설정
//contentType 은 default 값이 "application/x-www-form-urlencoded; charset=UTF-8" 인데, "multipart/form-data" 로 전송이 되게 false 로 넣는 것