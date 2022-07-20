$(function () {
	//화면로딩되자마자 시도 목록 출력
	$.ajax({
        url: 'http://localhost:1124/back/main', 
        success: function (jsonObj) {
            $sidoObj = $('select[name=sd]');
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

    });

	$(window).scroll(function(){
		if ($(this).scrollTop() > 300){
			$('.btn_gotop').show();
		} else{
			$('.btn_gotop').hide();
		}
	});
	$('.btn_gotop').click(function(){
		$('html, body').animate({scrollTop:0},400);
		return false;
	});

	//------------레슨정보 등록버튼 START------------
	let $btRegister = $('button[name=register]');

	$btRegister.click(function () {
		// 폼안에 input태그 입력값 유효검증
		// if($.trim($(".input").val())==''){
		// 	alert("입력해주세요.");
		// 	return false;
		// } 


		let $formObj = $('form.lessonregister');
		let formData = new FormData($formObj[0]);

		//WB에서 입력한 값이 잘 추출되는지 확인
		let $locNoVal = $('option[name=sigungu]').val();
		let $clubNo = $('input[name=club_no]').val();
		let $lessonTitle = $('input[name=lsn_title]').val();
		let $lessonPrice = $('input[name=lsn_price]').val();
		let $lessonLV = $('option[name=lsn_lv]').val();
		let $lsnCntSum = $('input[name=lsn_cnt_sum]').val();
		let $lsnPerTime = $('input[name=lsn_per_time]').val();
		let $lsnIntro = $('input[name=lsn_intro]').val();
		let $lsnDays = $('input[name=lsn_days]').val();

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

				//WB에서 입력한 값이 잘 추출되는지 확인
				console.log($locNoVal);
				console.log($clubNo);
				console.log($lessonTitle);
				console.log($lessonPrice);
				console.log($lessonLV);
				console.log($lsnCntSum);
				console.log($lsnPerTime);
				console.log($lsnIntro);
				console.log($lsnDays);
			}
		});

		return false;	
	});

	//--------------이미지파일업로드 미리보기 START--------------
	$("div.tr2 input[name=lsn_thumbnail]").on("change", function(event) {
		// #lessonThumbnail
		var file = event.target.files[0];
		var reader = new FileReader(); 

		reader.onload = function(e) {
			$("#preview").attr("src", e.target.result);
		}

		reader.readAsDataURL(file);
	});
	// 확장자가 이미지 파일인지 확인
	function isImageFile(file) {
		var ext = file.name.split(".").pop().toLowerCase(); // 파일명에서 확장자를 가져온다. 

		return ($.inArray(ext, ["jpg", "jpeg", "gif", "png"]) === -1) ? false : true;
	}
	// 파일의 최대 사이즈 확인
	function isOverSize(file) {
		var maxSize = 3 * 1024 * 1024; // 3MB로 제한 

		return (file.size > maxSize) ? true : false;
	}

	//-----------상단이동버튼 클릭 START-------------
	$(window).scroll(function(){
		if ($(this).scrollTop() > 300){
			$('.btn_gotop').show();
		} else{
			$('.btn_gotop').hide();
		}
	});
	$('.btn_gotop').click(function(){
		$('html, body').animate({scrollTop:0},400);
		return false;
	});
});