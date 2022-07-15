// $(function() {
//     $.ajax({
// 		url: "http://localhost:1124/back/login",
// 		success: function (jsonObj) {
// 		console.log("usertype =" + jsonObj.type);
// 		$contentObj = $("div#content>div#content-right>div#logined");
// 		let $tabObjHtml = "";
	
// 		if (jsonObj.type == 0) {
// 			// $(location).attr("href", "mypage.html");
// 		} else {
// 			$tabObjHtml = '<a id="mypage" href="/front/html/addlesson.html">레슨등록</a>';
// 			// $(location).attr("href", "mypropage.html");
// 		}
// 		let $copyObj = $contentObj.clone();
//         $copyObj.html($tabObjHtml);

// 		$contentObj.append($copyObj);
// 		$contentObj.hide();
// 		},
// 		error: function (jqXHR) {
// 		alert("오류 : " + jqXHR.status);
// 		},
// 	});
// })

function mypage() {
	$.ajax({
		url: "http://localhost:1124/back/login",
		//   dataType: "json",
		success: function (jsonObj) {
		console.log("usertype =" + jsonObj.type);

		if (jsonObj.type == 0) {
			$(location).attr("href", "mypage.html");
		} else {	
			$(location).attr("href", "mypropage.html");
		}
		},
		error: function (jqXHR) {
		alert("오류 : " + jqXHR.status);
		},
	});
}

function logout() {
	console.log("shittttt");
	$.ajax({
		url: "http://localhost:1124/back/logout",
		success: function () {
			alert("로그아웃 되었습니다.");
			location.href = "";
		},
		error: function (jqXHR) {
		alert("error: " + jqXHR.status);
		},
	});
	return false;
}
