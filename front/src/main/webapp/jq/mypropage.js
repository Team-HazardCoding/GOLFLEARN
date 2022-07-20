$(function(){
    $.ajax({
		url: "http://localhost:1124/back/login",
		success: function (jsonObj) {
		let $tabObj = $("div#content>div#content-right");
		let $tabObjHtml = "";
		console.log("tetetetete" + jsonObj.type);
		if (jsonObj.status == 1) {
			console.log("hihiho");
			// $('header div#logined').show();

			$tabObjHtml +=
			'<div id="logined"><div id="logout" onclick="logout()">로그아웃</div>';
			$tabObjHtml +=
			'<div id="mypage" onclick="mypage()">마이페이지</div></div>';
			if (jsonObj.type == 1) {
				$tabObjHtml += '<a id="mypage" href="/front/html/addlesson.html">레슨등록</a>';
			}

		} else {
			// $('header div#normal').show();
			$tabObjHtml +=
			' <div id="normal"><a href="/front/html/login.html">로그인</a>';
			$tabObjHtml +=
			'<a href="/front/html/signuptype.html">회원가입</a></div>';
		}
		$tabObj.html($tabObjHtml);
		
		// return false;
		},
		error: function (jqXHR) {
		alert(jqXHR.status);
		},
	});
    //1)페이지 로딩되었을 때 프로면 레슨 보여주기...
    $.ajax({
        url : "/back/viewmypage",
        method : 'get',
        success : function(jsonObj){
            let $lsnObj = $('div.tr');
            $(jsonObj).each(function(i,element){
                $copyObj = $lsnObj.clone();
                // $lsnObj.hide();
                
                let lsn_no = element.lsnNo;
                let lsn_title = element.lsnTitle;
                let lsn_status = element.lsnStatus;
                
                // let lsns = '<div class = "context">';
                // lsns += '<div class = "no">레슨번호: <span class = "no">' + lsn_no + '</span></div>';
                // lsns += '<img src = "../lsn_images/' + lsn_no +'.jpg" alt="' + lsn_no + '번째레슨">';
                // lsns += '<div class = "title">' + lsn_title + '</div>';
                // // lsns += '<div class = "expdate">종료일자</div>';
                // // lsns += '<div class = "crnlsncnt">레슨진행횟수</div>';
                // // lsns += '<div class = "lsncntsum">총레슨횟수</div>';
                // lsns += '<input type="button" value="수강생관리">';  //미구현
                // lsns += '<input type="button" value="레슨종료">';  //미구현
                // lsns += '<input type="button" value="레슨재개">'; //미구현
                // lsns += '</div>';

                let lsns = '<div class = "no">' + lsn_no + '</div>'
                lsns += '<img src = "../lsn_images/' + lsn_no + '.jpg" alt="' + lsn_no + '번째레슨">'
                lsns += '<div class = "title">' + lsn_title + '</div>'
                lsns += '<input type="button" class = "stdt_manage" value="수강생관리">'
                lsns += '<input type="button" class = "lsn_close" value="레슨종료">'
                lsns += '<input type="button" class = "lsn_resume" value="레슨재개">'

                $copyObj.find('div.td').html(lsns);

                $('div.table').append($copyObj);

                //왜 하나만 사라지지??? 
                $closeObj = $copyObj.find('input[value=레슨종료]');
                $resumeObj = $copyObj.find('input[value=레슨재개]');
                // $resumeObj.hide();
                // $closeObj.hide();

                console.log(lsn_status);
                if (lsn_status = 0) {
                    $closeObj.hide();
                }else if(lsn_status = 1){
                    $resumeObj.hide();
                }else {
                    alert("잘못된 상태의 강의입니다")
                };
                
                // $closeObj.click(function(){
                //     alert("곧 구현 예정입니다 :) 기다려주세요!");
                // });
                // $resumeObj.click(function(){
                //     alert("곧 구현 예정입니다 :) 기다려주세요!");
                // }); 

            })  
        },
        error : function(jqXHR){
            alert('오류 : ' + jqXHR.status);
        }
    });

    //클릭한 객체의 부모, 클릭한 객체의 자식 찾기 
    $('div.table').on('click', 'input[value=레슨종료]', function(){
        // $(this).parent() -> 나중에 기능 구현할 때 사용 
            alert("곧 구현 예정입니다 :) 기다려주세요!");
    });
    $('div.table').on('click', 'input[value=레슨재개]', function(){
        // $(this).parent() -> 나중에 기능 구현할 때 사용 
            alert("곧 구현 예정입니다 :) 기다려주세요!");
    });
    //2)레슨사진눌렀을때 레슨상세페이지 연결 
    $mypropageObj = $('section');
    $lsnNoObj = $('div.no');
    console.log($lsnNoObj);
    $mypropageObj.on('click', 'img', function(){
        //레슨번호 찾아오기  //1번완료 후 
        let $lsnNoObj = $(this).parent().find('div.no');  //URL종우한테 확인 필요 
        let lsn_no = $lsnNoObj.html(); 
        location.href = "/front/html/viewlesson.html?lsn_no=" + lsn_no;
    });


    //3)수강생관리 클릭시 수강생관리 페이지 연결  
    $mypropageObj.on('click', 'input[value=수강생관리]', function(){
        let $lsnNoObj = $(this).parent().find('div.no');  //URL종우한테 확인 필요 
        let lsn_no = $lsnNoObj.html();
        location.href = "/front/html/studentmanage.html?lsn_no=" + lsn_no;
    });    

})