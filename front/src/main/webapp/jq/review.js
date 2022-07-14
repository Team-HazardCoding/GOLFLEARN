$(function(){
    //1) viewreview만들기 맨처음 값 설정 
    //리뷰페이지로 이동하는 jquery에다가 data 클릭한레슨라인넘버 하기 
    //사용자가 입력한 값 세션으로 보내주기? 레슨번호, 리뷰, 마이스타스코어 
    //수강수정하기 누르면 1값을 보내고 아니면 2값을 보내기?  


    //1) 로드될때 수정이면 내용 출력되게...?
    $titleObj = $('div.lsn_title');
    $lineNoObj = $('input.lsn_line_no');
    $reviewObj = $('input.review_context');
    $starScoreObj = $('div.starform');

    //마이페이지에서 넘어올때 상태값 주고 querystring 으로 받기
    let params = location.search.substring(1).split('&');
    let review_exist = params[0].split("=")[1];
    console.log(params[1]);
    let lsn_line_no = params[1].split("=")[1]; 
    // let review_exist = location.search.substring(1); //reviewCnt=0&lsn_line_no=" + lsn_line_no
    // let lsn_line_no = location.search.substring(2);
    if(review_exist == 0) { //후기 작성요청 후기미존재
        $.ajax({
            url : "/back/viewreview",
            method : 'get',
            data : {lsn_line_no : lsn_line_no},
            success : function(jsonObj){
                // let lsn_line_no = jsonObj.lsnLine.lsnLineNo;
                let lsn_title = jsonObj.lsnTitle;
                $titleObj.html(lsn_title);
                $lineNoObj.val(lsn_line_no);
                // $lineNoObj.html(lsn_line_no);
                console.log("작성으로 왔다");
            },
            error : function(){
                alert('오류 : ' + jqXHR.status);
            }
        });
    }else{ //후기조회요청 후기존재  
        $.ajax({
            url : "/back/viewmodifyreview",
            method : 'get',
            data : {lsn_line_no : lsn_line_no},
            success : function(jsonObj){
                // let lsn_line_no = jsonObj.lsnReview.lsnLineNo;
                console.log(jsonObj);
                let lsn_title = jsonObj.lsn.lsnTitle;
                let review = jsonObj.lsnReview.review;
                let my_star_score = jsonObj.lsnReview.myStarScore;
                $titleObj.html(lsn_title);
                $lineNoObj.val(lsn_line_no);
                console.log(review);
                console.log(my_star_score);
                $reviewObj.val(review);
                $starScoreObj.val(my_star_score);
                console.log("조회로 왔다");
            },
            error : function(){
                alert('오류 : ' + jqXHR.status);
            }
        });
    };

    //나중에 타이틀클릭하면 상세내용 페이지로? ->click
    //2) 제출 클릭시  addreview
    let $form = $('form');
    $form.submit(function(){
        let data = $(this).serialize();
        console.log(data);
        $.ajax({
            url : "http://localhost:1124/back/addreview",
            method : 'post',
            data : data,
            success : function(jsonObj){
                alert(data);
            },
            error : function(jqXHR){
                alert('오류 : ' + jqXHR.status);
            }
        });
        return false;
    });

    //3) 이전 클릭시 이전 페이지로 ->click
    let $backObj = $('input.back');
    $backObj.click(function(){
        $(location).attr('href', '/front/html/mypage.html');
    });

























});