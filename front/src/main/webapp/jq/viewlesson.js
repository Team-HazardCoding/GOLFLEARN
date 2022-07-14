$(function(){
	//---------------화면 로딩되자마자----------------
	let queryString = location.search.substring(1); 
	// "?" 이후부터의 쿼리스트링값 추출

	$.ajax({
		url: "/back/viewlesson",
		method: 'get',
		//ex) data: 'lsn_no=' + '2',
		data: queryString, 
		success: function (jsonObj) {
			console.log(jsonObj);
			//레슨간략정보 가져오기
			let loc_no = jsonObj.lesson.locNo;	//지역코드(ex:11011에 해당하는 주소지역 다 문자로 표현)
			let lsn_title = jsonObj.lesson.lsnTitle;
			let lsn_star_score = jsonObj.lesson.lsnStarScore;
			let lsn_review_cnt = jsonObj.lesson.lsnStarPplCnt;	//리뷰갯수
			let user_name = jsonObj.lesson.user.userName;	//수정해야됨
			let pro_star_score = jsonObj.lesson.proStarScore;
			let lsn_no = jsonObj.lesson.lsnNo;
			//레슨상세정보 가져오기
			let lsn_intro = jsonObj.lesson.lsnIntro;
			let pro_intro = jsonObj.lesson.pro.proCareer;
			let review = jsonObj.lesson.lines[0].lsnReview.review;

			//레슨간략정보 붙이기
			// $('div.viewlesson>img').attr('src', 'C:\\Golflearn_lib\\user_images\\' + lsn_no + '.png')
			// 	.attr('alt', lsn_title);	//레슨썸네일 경로 파일 불러오기(경로 수정 필요)
			$('div.viewlesson>img').attr('src', '../images/' + lsn_no + '.jfif')
				.attr('alt', lsn_title);	
			$('div.viewlesson ul>li>span.loc_no').html(loc_no);	//지역은 api로 넘어갈때 수정
			$('div.viewlesson ul>li>span.lsn_title').html(lsn_title);
			$('div.viewlesson ul>li>span.lsn_star_score').html(lsn_star_score);
			$('div.viewlesson ul>li>span.lsn_review_cnt').html(lsn_review_cnt);
			$('div.viewlesson ul>li>span.user_name').html(user_name);	//프로명 못받아옴
			$('div.viewlesson ul>li>span.pro_star_score').html(pro_star_score);
			//레슨상세정보 붙이기
			$('div.detail>div.lsn_intro').html(lsn_intro);
			$('div.detail>div.pro_intro').html(pro_intro);
			$('div.detail>div.review').html(review);
		},
		error: function () {
			alert('오류:' + jqXHR.status);
		}
	});

	//------------수강신청 버튼 클릭 START-------------
	//		 (서블릿이없어서 alert띄우는방향으로하기)
	$('div.viewlesson ul>li>button').click(function(){
		let lsn_title = jsonObj.lesson.lsnTitle;
		alert('"' + lsn_title + '" 수강신청이 완료되었습니다.');
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

	// //------------레슨상세정보 네비바 클릭 START-------------
	$(".scroll_move").click(function(event){         
		event.preventDefault();
		$('html,body').animate({scrollTop:$(this.hash).offset().top}, 500);
	});
});