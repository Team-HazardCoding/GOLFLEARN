$(function() {
	let $inputId = $('input[name=user_id]');
	let $inputEmail = $('input[name=user_email]');

	let $form = $('form');
	$form.submit(function () {
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
					alert(jsonObj.msg);
					location.href = "http://localhost:1124/front/html/changepwd.html";
				}else {
					alert(jsonObj.msg);
				}
			},
			error: function(jqXHR,textStatus,errorThrown) {
				alert(jqXHR.status + ": " + jqXHR.statusText);
			}
		});
		return false;
	});
});