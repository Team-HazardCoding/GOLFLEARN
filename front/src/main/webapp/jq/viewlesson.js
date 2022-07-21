$(function(){
	//---------------화면 로딩되자마자----------------
	
	//레슨정보 서블릿에 보내기
	let queryString = location.search.substring(1); 

	$.ajax({
		url: "/back/viewlesson",
		method: 'get',
		//ex) data: 'lsn_no=' + '2',
		data: queryString, 
		success: function (jsonObj) {
			console.log(jsonObj);
			//레슨간략정보(레슨제목, 레슨별점,,, 등) 서블릿에서 가져오기
			let loc_no = jsonObj.lesson.locNo;	//지역코드(ex:11011에 해당하는 주소지역 다 문자로 표현)
			let lsn_title = jsonObj.lesson.lsnTitle;
			let lsn_star_score = jsonObj.lesson.lsnStarScore;
			let lsn_review_cnt = jsonObj.lesson.lsnStarPplCnt; //리뷰갯수
			let user_name = jsonObj.lesson.user.userName;	
			let pro_star_score = jsonObj.lesson.proStarScore;
			let lsn_no = jsonObj.lesson.lsnNo;
			let user_id = jsonObj.lesson.user.userID;
			//레슨상세정보(레슨소개, 프로소개) 서블릿에서 가져오기
			let lsn_intro = jsonObj.lesson.lsnIntro;
			let pro_intro = jsonObj.lesson.pro.proCareer;

			//레슨간략정보 WB에 붙이기
			// $('div.viewlesson>img').attr('src', 'C:\\Golflearn_lib\\user_images\\' + lsn_no + '.png')
			// 	.attr('alt', lsn_title);	//레슨썸네일 경로 파일 불러오기(경로 수정 필요)
			// $('div.viewlesson>img').attr('src', '../lsn_images/' + lsn_no + '.jpg')
			// 	.attr('alt', lsn_title);
			$('div.viewlesson>img').attr('src', 'C:/Golflearn_lib/user_images/' + user_id + '/' + lsn_no + 'LessonThumbnail.jpg')
				.attr('alt', lsn_title);	
			$('div.viewlesson ul>li>span.loc_no').html(loc_no);	//지역은 api로 넘어갈때 수정
			$('div.viewlesson ul>li>span.lsn_title').html(lsn_title);
			$('div.viewlesson ul>li>span.lsn_star_score').html(lsn_star_score);
			$('div.viewlesson ul>li>span.lsn_review_cnt').html(lsn_review_cnt);
			$('div.viewlesson ul>li>span.user_name').html(user_name);
			$('div.viewlesson ul>li>span.pro_star_score').html(pro_star_score);
			//레슨상세정보 WB에 붙이기
			$('div.lsn_intro').html(lsn_intro);
			$('div.pro_intro').html(pro_intro);

			//-----------레슨후기 list START-------------
			let jsonarr = jsonObj.lesson.lines;
            let $lsnObj = $('div.reviewlist');
            $(jsonarr).each(function(i, element){
				$copyObj = $lsnObj.clone();
                
				let reviewId = element.user.userID;
				//리뷰작성날짜 sql에서 얻어온 값 형변환
				let reviewDt = element.lsnReview.reviewDt;
				let convertReviewDt = new Date(reviewDt);
				convertReviewDt = convertReviewDt.toLocaleDateString();
                let review = element.lsnReview.review;

                let lessonReview = '<ul>';
                lessonReview += '<li><div>작성자아이디: <span class = "reviewId">' + reviewId + '</span></div></li>'
                lessonReview += '<li><div>작성날짜: <span class = "reviewDt">' + convertReviewDt + '</span></div></li>'
                lessonReview += '<li><div>리뷰: <span class = "review">' + review + '</span></div></li>'
				lessonReview += '<hr>'
                lessonReview += '</ul>'
                
                $copyObj.find('div.reviewdetail').html(lessonReview);
                
                $('div.lsn').append($copyObj);
			});	
		},
		error: function (jqXHR) {
			alert('오류:' + jqXHR.status);
		}
	});

	//------------수강신청 버튼 클릭 START-------------
	//(서블릿이없어서 alert띄우는방향으로하기 : 추후 결제와 실제수강신청구현)
	$('div.simple button').click(function(jsonObj){
		// let lsn_title = jsonObj.lesson.lsnTitle;
		// alert('"' + lsn_title + '" 수강신청이 완료되었습니다.');
		alert('수강신청이 완료되었습니다');
	});

	// $('div.viewlesson ul>li>button').click(function(){
	// 	let lsn_no = $('div.viewlesson ul>li>span.lsn_no').html();
	// 	let quantity = $('div.viewlesson ul>li>input[name=quantity]').val();

	// 	$.ajax({
	// 		url: '/back/addcart', // 아직안만든 서블릿 : /back/addEnrolment
	// 		method: 'get',
	// 		data: {수강생아이디, 레슨라인넘버} , 
	// 		success: function(jsonObj){
	// 			$('div.viewlesson div.result').show();
	// 		},
	// 		error: function(jqXHR){
	// 			alert('오류:' + jqXHR.status);
	// 		}
	// 	});
	// 	return false;
	// });

	//------------레슨상세정보 네비바 클릭 START-------------
	$(document).ready(function(){
		$('#lsnIntroLink').click(function(){
			var offset = $('div.lsn_intro').offset(); //선택한 태그의 위치를 반환                
			//animate()메서드를 이용해서 선택한 태그의 스크롤 위치를 지정해서 0.4초 동안 부드럽게 해당 위치로 이동함
			$('html').animate({scrollTop : offset.top}, 400);		
		});	
	});
	$(document).ready(function(){
		$('#proIntroLink').click(function(){
			var offset = $('div.pro_intro').offset(); //선택한 태그의 위치를 반환                
			//animate()메서드를 이용해서 선택한 태그의 스크롤 위치를 지정해서 0.4초 동안 부드럽게 해당 위치로 이동함
			$('html').animate({scrollTop : offset.top}, 400);		
		});	
	});
	$(document).ready(function(){
		$('#reviewLink').click(function(){
			var offset = $('div.lsn').offset(); //선택한 태그의 위치를 반환                
			//animate()메서드를 이용해서 선택한 태그의 스크롤 위치를 지정해서 0.4초 동안 부드럽게 해당 위치로 이동함
			$('html').animate({scrollTop : offset.top}, 400);		
		});	
	});

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

	//------------레슨등록 프로회원만 보이도록 만들기 START-------------

});