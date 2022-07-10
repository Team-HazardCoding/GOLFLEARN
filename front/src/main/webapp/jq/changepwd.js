$(function () {
	let $inputAuthUser = $("input[name=authenticationUser]");
	let $inputNewPwd = $("input[name=user_newpwd]");
	let $inputChkNewPwd = $("input[name=user_chknewpwd]");

	let $form = $('form');
	$form.submit(function () {
		let url = "http://localhost:1124/back/changepwd";
		let  inputAuthUser, inputNewPwd, inputChkNewPwd;
		
		inputAuthUser = $inputAuthUser.val();
		inputNewPwd = $inputNewPwd.val();
		inputChkNewPwd = $inputChkNewPwd.val();
		console.log(inputAuthUser,inputEmailValue,inputChkNewPwd);
		
		let data = "authenticationUser=" + inputAuthUser + "&user_newpwd=" + inputNewPwd + "&user_chknewpwd=" + inputChkNewPwd;
		console.log(data);
		
		$.ajax({
			url: url,
			method: "POST",
			data: data,
			success: function(jsonObj) {
				//let jsonObj = JSON.parse(jsonObj);
				if(jsonObj.status == 1){
					alert("비밀번호가 변경되었습니다")
					location.href = "http://localhost:1124/front/html/login";
				} else {
					alert("비밀번호 변경 실패");
				}
			},
			error: function (jqXHR,textStatus,errorThrown) {
				alert(jqXHR.status + ": " + jqXHR.statusText);
			}
		});
		return false;
	});
});