$(function() {
	//이름 입력 객체 찾기
	let $inputName = $('input[name=user_name]');
	//이메일 입력객체 찾기
	let $inputEmail = $('input[name=user_email]');
	//form 전송 START
	let $form = $('form');
	$form.submit(function () {
		let url = "http://localhost:1124/back/findid";
		let inputNameValue, inputEmailValue;
		
		inputNameValue = $inputName.val();
		inputEmailValue = $inputEmail.val();
		console.log(inputNameValue,inputEmailValue);
		
		let data = "user_name=" + inputNameValue + "&user_email=" + inputEmailValue;
		console.log(data);
		
		$.ajax({
			url: url,
			method: "POST",
			data: data,
			success: function(jsonObj) {
				//let jsonObj = JSON.parse(jsonObj);
				if(jsonObj.status == 1){
					alert("아이디 조회 성공");
				} else {
					alert("아이디 조회 실패");
				}
			},
			error: function (jqXHR,textStatus,errorThrown) {
				alert(jqXHR.status + ": " + jqXHR.statusText);
			}
		});
		return false;
	});
});