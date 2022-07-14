$(function () {
	//이름 입력 객체 찾기
	let $inputName = $("input[name=user_name]");
	//이메일 입력객체 찾기
	let $inputEmail = $("input[name=user_email]");
	//form 전송 START

	let $form = $("form");
	//let $btn = $("button.btn-primary");
	$form.submit(function (event) {
		let url = "http://localhost:1124/back/findid";
		let inputNameValue, inputEmailValue;

		inputNameValue = $inputName.val();
		inputEmailValue = $inputEmail.val();

		let data = "user_name=" + inputNameValue + "&user_email=" + inputEmailValue;
		console.log(data);

    //var arr = [];

    //arr = inputEmailValue.split("@");
    //let arr1 = arr[0];
    //let arr2 = arr[1];
    //console.log(inputNameValue, inputEmailValue);

    $.ajax({
		url: url,
		method: "POST",
		data: data,
		success: function (jsonObj) {
			if (jsonObj.status == 1) {
				$('#content.modal-body').text("고객님의 Golflearn 계정 ID는 " + jsonObj.id + " 입니다.");
				$('button#btn-secondary').click(function () {
					location.href = "http://localhost:1124/front/html/login.html";
					});
			}else{
				$('#content.modal-body').text("아이디나 이메일이 알맞지 않습니다. 아이디와 이메일을 정확하게 입력해주세요");
				$('button#btn-secondary').click(function () {
					location.href = "http://localhost:1124/front/html/findid.html";
				});
			}
		},	
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + ": " + jqXHR.statusText);
		},
	});
	event.preventDefault();
	return false;
	});
});
