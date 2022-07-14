$(function () {
	//------------썸네일 첨부 START------------
	// $("div.td1>input[type=button].ajax").click(function () {
	// 	let formObj = this.parentNode;
	// 	let formData = new FormData(formObj);
	// 	$.ajax({
	// 		url: "/back4/upload",
	// 		method: "post",
	// 		processData: false,
	// 		contentType: false,
	// 		data: formData, //요청전달데이터 
	// 		//(주의! : 서버에 업로드할때 전달객체는 무조건 FormData의 객체여야한다)
	// 		success: function (responseObj) {

	// 		}, error: function (jqXHR) {
	// 			alert("에러:" + jqXHR.status);
	// 		}
	// 	});
	// 	return false;
	// });

	//파일 미리보기 제공
	// function readFile(event) {
	// 	let file = event.target.files[0];

	// 	let href = window.URL.createObjectURL(file)
	// 	$("#lessonThumbnail").attr('src', href)

	// 	//5분뒤에 메모리 해제(메모리 누수방지)
	// 	setTimeout(function () {
	// 		window.URL.revokeObjectURL(href)
	// 	}, 1000 * 60 * 5)
	// }
    $.ajax({
        url: 'http://localhost:1124/back/main', 
        success: function (jsonObj) {
            $sidoObj = $('select[name=sido]');
            var arr = [];
            let sido = '';
            $(jsonObj.sido).each(function(key, item) {
                // console.log(Object.values(item));
                $keyObj = Object.keys(item);
                $itemObj = Object.values(item);
                for ( let i = 0; i < $itemObj.length; i++ ) {
                    arr.push($itemObj[i]);
                    console.log($keyObj);
                    sido += '<option class="sido" name="sido" value="' + $keyObj[i] + '"';
                    sido+= '>' + $itemObj[i] + '</option>';
                }
            })
            // sido += '</ul>';
            $sidoObj.append(sido);
            return false;
        },
        error: function (jqXHR) {
            alert("error: " + jqXHR.status);
        }

    })


	//------------지역 START------------
	//------------레슨분류 START------------


	//------------레슨정보 등록버튼 START------------
	let $formObj = $('form.lessonregister');
	let formData = new FormData($formObj[0]);
	let $btRegister = $('button[name=register]');

	$btRegister.click(function () {
		// //레슨분류
		// let ClsfcWood = $('#wood').val()
		// let ClsfcIron = $('#iron').val()
		// let ClsfcWedge = $('#wedge').val()
		// let ClsfcPutter = $('#putter').val()
		// let ClsfcSynthesis = $('#synthesis').val()

		// //레슨분류 외 정보
		// let lsnTitle = $('#lsn_title').val()
		// let lsnPrice = $('#lsn_price').val()
		// let lsnLv = $('#lsn_lv').val()
		// let lsnCntSum = $('#lsn_cnt_sum').val()
		// let lsnPerTime = $('#lsn_per_time').val()
		// let lsnIntro = $('#lsn_intro').val()
		// let lsnDays = $('#lsn_days').val()

		// let formData = new FormData();

		

		$.ajax({
			url: "http://localhost:1124/back/addlesson",
			method: 'post',
			data: formData,
			processData: false,
			contentType: false,
			success: function (jsonObj) {
				if (jsonObj.status == 1) {
					alert("등록성공");
					location.replace("http://localhost:1124/front/html/main.html");
				}
			},
			error: function (jqXHR) {
				alert('오류 : ' + jqXHR.status);
			}
		});
		return false;
		
	});
});