$(function() {

	let $sidoBtn = $('div#sidebar');
	let sidoguUrl = 'http://localhost:1124/back/seeksidosigu';
	let $tagContent = $('#tag-container');
	$sidoBtn.on('click', 'li', function(event) {
		$(this).toggleClass('choice');

		$tagContent.empty();
		let $sidoVal = $(this);
		$.ajax({
			url: sidoguUrl,
			data: {sido : $sidoVal.val()},
			success: function(jsonObj) {
				let sigu='';
				$(jsonObj.sigungu).each(function(index, item) {
					// console.log(item);
					$keyObj = Object.keys(item);
					$itemObj = Object.values(item);
					for ( let i = 0; i < $itemObj.length; i++ ) {
						sigu += '<li class="sigu" name="sigu" value="' + $keyObj[i] + '"';
						sigu+= '>' + $itemObj[i] + '</li>';	
					}
				})
				$tagContent.append(sigu);
				// console.log($sidoNm);
				// 각 시군구가 tag-name에 뿌려져야 함.
				// tag-name 을 리스트로 받아와서 반복문을 돌아서 뿌려줌
				// event.preventDefault();
				return false;
			},
			error: function(jqXHR){
				alert("error: " + jqXHR.status);
				
			}
			
		})
	});
	//-----------------try-------------------
	$('#tag-container').on('click','li',function(e) {
		// e.preventDefault();
		$(this).toggleClass('choice');

		let data = ''; //sigu=1111&sigu=2222
		$('#tag-container>li').each(function(index, element){
			if($(element).hasClass('choice')){
				if(data != ''){
					data += '&';
				}
				data += 'sigu=' + $(element).attr('value');
			}
			// return false;
		});
		let url = '';

		console.log(data);
		if(data == ''){
			url ='http://localhost:1124/back/main';
		} else {
			url ='http://localhost:1124/back/filterlesson';
		}
		$.ajax({
			url:url,
			data:data,
			success: function(jsonObj) {
				// console.log(url);
				// console.log(jsonObj.lsns);
				$('div#card-container').empty();
				$lsnObj = $('<div class="col">');
				$('div#card-container').append($lsnObj);
				// let product ='';
				$(jsonObj.lsns).each(function(index, item) {
					// console.log(item);
					let product = '<div class="lsn" id='+ item.lsnNo + '>';
					product +='<img src="../lsn_images/' + item.lsnNo+ '_LessonThumbnail.jpg">';// 각레슨의 이미지경로 다시 설정해야함 c밑의 경로임
					product +='<div class="lsn_content">';
					product +='<h5 class="lsn_title">' + item.lsnTitle + '</h5>';
					product +='<p class="prod_price">프로이름 : '+item.user.userName + '</p>';
					// product +='<p class="tag_name">태그이름 : ' + item.locNo + '</p>';// 지역번호
					product +='<p class="star_point">별점  : '+ item.lsnStarPoint + '</p></div>';
					product +='</div>';
					
					// console.log(product);
					// $copyObj.html(product);
					let $copyObj = $lsnObj.clone(); //복제본 생성
					$copyObj.html(product);
					$('div#card-container').append($copyObj);
				});
				$lsnObj.hide(); // 복제본이 아닌 td태그를 숨김
				return false;
			},
			error: function(jqXHR){
				console.log("error : "+jqXHR);
			}
		});
		
	});
})
