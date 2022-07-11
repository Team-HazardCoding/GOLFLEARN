$(function() {
	let $inputAuthUser = $("input[name=authenticationUser]");
	let $inputNewPwd = $("input[name=user_newpwd]");
	let $inputChkNewPwd = $("input[name=user_chknewpwd]");

	let $form = $('form');
	console.log($form);
	$form.submit(function () {
		let url = "http://localhost:1124/back/changepwd";
		let  inputAuthUser, inputNewPwd, inputChkNewPwd;
		
		inputAuthUser = $inputAuthUser.val();
		inputNewPwd = $inputNewPwd.val();
		inputChkNewPwd = $inputChkNewPwd.val();
		console.log(inputAuthUser,inputNewPwd,inputChkNewPwd);
		
		let data = "authenticationUser=" + inputAuthUser + "&user_newpwd=" + inputNewPwd + "&user_chknewpwd=" + inputChkNewPwd;
		console.log(data);
		//location.href = "http://localhost:1124/front/html/login.html";
		$.ajax({
			url: url,
			method: "POST",
			data: data,
			success: function(jsonObj) {
				if(jsonObj.status == 1){
					location.href='http://localhost:1124/front/html/login.html';
					alert("비밀번호가 변경되었습니다");
					console.log(data);
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