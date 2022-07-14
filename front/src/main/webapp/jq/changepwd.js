$(function() {
	let $inputAuthUser = $("input[name=authenticationUser]");
	let $inputNewPwd = $("input[name=user_newpwd]");
	let $inputChkNewPwd = $("input[name=user_chknewpwd]");

	let $form = $('form');
	$form.submit(function (event) {
		if ($inputNewPwd.val() != $inputChkNewPwd.val()) {
			$('#content.modal-body').html("비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
			$("button.btn-secondary").click(function () {
				location.href = "http://localhost:1124/front/html/changepwd.html";
				});
			$inputNewPwd.focus();
			return false;
		}
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
					$('#content.modal-body').html("고객님의 계정 비밀번호가 변경되었습니다.");
					$("button.btn-secondary").click(function () {
						location.href = "http://localhost:1124/front/html/login.html";
						});
				} else {
					$('#content.modal-body').html("인증코드가 일치하지 않습니다. 인증코드를 정확하게 입력해주세요.");
					$('button#btn-secondary').click(function () {
						location.href = "http://localhost:1124/front/html/changepwd.html";
					});
				}
			},	
			error: function (jqXHR,textStatus,errorThrown) {
				$('#content.modal-body').html("입력한 값이 올바르지 않습니다. 정확하게 입력해주세요.");
				$('button#btn-secondary').click(function () {
					location.href = "http://localhost:1124/front/html/changepwd.html";
					});
			},
		});
		event.preventDefault();
		return false;
	});
});