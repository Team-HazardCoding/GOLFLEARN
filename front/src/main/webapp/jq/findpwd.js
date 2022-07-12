$(function() {
	let $inputId = $('input[name=user_id]');
	let $inputEmail = $('input[name=user_email]');

	let $form = $('form');
	$form.submit(function (event) {
		let url = "http://localhost:1124/back/findpwd";
		let inputIdValue, inputEmailValue;

		inputIdValue = $inputId.val();
		inputEmailValue = $inputEmail.val();
		console.log(inputIdValue,inputEmailValue);

		let data = "user_id=" + inputIdValue + "&user_email=" + inputEmailValue;
		console.log(data);
		$.ajax({
			url : url,
			method: "POST",
			data: data,
			success: function(jsonObj) {
				if(jsonObj.status == 1){
					$('#content.modal-body').text("고객님의 메일로 인증코드가 전송되었습니다. 인증코드를 확인해주세요");
					$("button.btn-secondary").click(function () {
						location.href = "http://localhost:1124/front/html/changepwd.html";
					});
				}else {
					$('#content.modal-body').text("인증코드 발송에 실패했습니다. 아이디와 이메일을 정확히 입력해주세요.");
					$("button.btn-secondary").click(function () {
						location.href = "http://localhost:1124/front/html/findpwd.html";
					});
				}
			},
			error: function(jqXHR,textStatus,errorThrown) {
				$('#content.modal-body').text("인증코드 발송에 실패했습니다. 아이디와 이메일을 정확히 입력해주세요.");
					$("button.btn-secondary").click(function () {
						location.href = "http://localhost:1124/front/html/findpwd.html";
				});
			},
		});
		event.preventDefault();
		return false;
	});
});