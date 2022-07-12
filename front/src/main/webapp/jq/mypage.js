$(function(){

    //1) 레슨이 눌렸을 때 레슨상세페이지로 연결 
    //레슨 내역에서 아무거나 눌러도 연결되게 -> 만약 img같은것에도 
    //일일이 달아줘야하는 경우 설정하기 
    let $viewlsnObj = $('div.lsnlist');
    $viewlsnObj.click(function(){
        // location.href("/front/html/종우가정한링크")
        $(location).attr('href', '/front/html/mypropage.html'); //테스트용
    });
    
    //수강상태 설정해주기 
    //수강예정 : 레슨신청일이 존재하나, 레슨일자가 존재하지 않음.
    //수강중 : 레슨일자가 존재함.
    //수강완료 : 레슨일자가 레슨총횟수와 같거나, 레슨만료일>현재날짜이면 수강완료시켜버림 

    //2)수강예정에서 수강취소버튼 눌렀을 시 삭제 --아마 삭제 sql도 구현해야 할 것... 보류 
    //수강중에 수강취소 버튼 눌렀을 시 alert stdt_lsn_status -> 1 
    //종료예정일이 현재날짜보다 지났으면 수강종료
    //LessonStatusUpdateServlet 만들어서 레슨 값 보내주기..? 
    //레슨라인넘버 보내줘야함 

    // let $lsncancelObj = $('input[type=button]');
    // let $lsnstatusObj = $('div.lsnstatus');
    // $lsncancelObj.click(function(){

    // });

    //$(this).val 해서 val값 설정해줄 수 있음 
    //3)레슨내역 주르르르르륵나오게 
    
    $.ajax({
        url : "/back/viewmypage",
        method : 'get',
        success : function(jsonObj){
            // let lsn_line_no = jsonObj.ll.lsnLineNo;
            // let lsn_no = jsonObj.ll.lsnNo;
            // let lsn_title = jsonObj.ll.lsn.lsnTitle;
            // let lsn_exp_dt = jsonObj.ll.lsnExpDt;
            // let stdt_lsn_status = jsonObj.ll.stdtLsnStatus;
            // let my_star_score = jsonObj.ll.lsnReview.myStarScore;
            // let crnt_lsn_cnt = jsonObj.ll.crntLsnCnt;
            // let lsn_cnt_sum = jsonObj.ll.lsnCntSum;

            // // $('div.lsnlist1>img').attr('src', '../images/' + prod_no + '.jpg').attr('alt', prod_no );
            // $('div.lsnlist2 span.my_lsn_no').html(lsn_no);
            // $('div.lsnlist2 span.title').html(lsn_title);
            // $('div.lsnlist2 span.exp_date').html(lsn_exp_dt);
            // $('div.lsnlist2 span.crnt_lsn_cnt').html(crnt_lsn_cnt);
            // $('div.lsnlist2 span.lsn_cnt_sum').html(lsn_cnt_sum);
            // $('div.lsnlist2 span.lsn_status').html(stdt_lsn_status);
            let jsonarr = jsonObj.ll;
            let $lsnObj = $('div.lsnlist');
            $(jsonarr).each(function(i,element){
                $copyObj = $lsnObj.clone();

                let lsn_line_no = element.lsnLineNo;
                let lsn_no = element.lsnNo;
                let lsn_title = element.lsn.lsnTitle;
                let lsn_exp_dt = element.lsnExpDt;
                let stdt_lsn_status = element.stdtLsnStatus;
                let my_star_score = element.lsnReview.myStarScore;
                let crnt_lsn_cnt = element.crntLsnCnt;
                let lsn_cnt_sum = element.lsnCntSum;
                console.log(lsn_line_no);

                let lessonLine = '<ul>';
                lessonLine += '<li><img src = "../lsn_images/' + lsn_line_no +'.jpg" alt="' + lsn_line_no + '번째레슨" width = "200" height = "200"></li>';
                lessonLine += '<li><div>레슨번호: <span class = "this_lsn_no">' + lsn_line_no + '</span></div></li>'
                lessonLine += '<li><div>가져온레슨명: <span class = "title">' + lsn_title + '</span></div></li>'
                lessonLine += '<li><div>종료일자: <span class = "exp_date">' + lsn_exp_dt + '</span></div></li>'
                lessonLine += '<li><div>현재진행횟수: <span class = "crnt_lsn_cnt">' + crnt_lsn_cnt + '</span></div></li>'
                lessonLine += '<li><div>현재진행횟수: <span class = "lsn_cnt_sum">' + lsn_cnt_sum + '</span></div></li>'
                lessonLine += '<li><div>현재수강상태: <span class = "lsn_status">' + stdt_lsn_status + '</span></div></li>'
                lessonLine += '</ul>'
                lessonLine += '<input type="button" class = "add_review" value="레슨후기작성">'
                lessonLine += '<input type="button" class = "modify_review" value="레슨후기수정">'
                lessonLine += '<input type="button" class = "cancel_lsn" value="수강취소버튼">'
                
                $copyObj.find('div.lsndetail').html(lessonLine);
                
                $('div.lsn').append($copyObj);
                

                // console.log(lsn_line_no);
                // console.log(lsn_title);
                // console.log(lsn_cnt_sum);
                // $copyObj = $lsnObj.clone();
                // let $imgObj = $copyObj.find("img");
                // $imgObj.attr("src", "../lsn_images/" + lsn_line_no + ".jpg");
                // $imgObj.attr("alt", lsn_line_no);
                // $copyObj.find("li.this_lsn_no").html(lsn_line_no);
                // $copyObj.find("li.title").html(lsn_title);
                // $copyObj.find("li.exp_date").html(lsn_exp_dt);
                // $copyObj.find("li.crnt_lsn_cnt").html(crnt_lsn_cnt);
                // $copyObj.find("li.lsn_cnt_sum").html(lsn_cnt_sum);
                // $copyObj.find("li.lsn_status").html(stdt_lsn_status);
                // $('div.lsn').append($copyObj);
            });
        },
        error : function(jqXHR){
            alert('오류 : ' + jqXHR.status);
        }
    });

    //4) 리뷰작성 눌렀을 때 누른 lsn_line_no, status 0 보내주기 
    // let $addReviewObj = $('input.add_review');
    
    let $lsnListObj = $('div.lsn');
    $lsnListObj.on('click', 'input[value=레슨후기작성]', function(){
        let $lsnLineNoObj = $(this).parent().find('span.this_lsn_no');
        let lsn_line_no = $lsnLineNoObj.html();
        location.href = "/front/html/review.html?reviewCnt=0&lsn_line_no=" + lsn_line_no;
    });
    
    // <input type="button" class = "add_review" value="레슨후기작성">
    // <input type="button" class = "modify_review" value="레슨후기수정">
    // <input type="button" class = "cancel_lsn" value="수강취소버튼"></input>

    //5) 수정 눌렀을 때 누른 lsn_line_no, status 1 보내주기 
        // location.href = "/front/html/viewproduct.html?prod_no=" + prod_no;
    // let $modifyReviewObj = $('input.modify_review');
    let $lsnListModiObj = $('div.lsn');
    $lsnListModiObj.on('click', 'input[value=레슨후기수정]', function(){
        let $lsnLineNoObj = $(this).parent().find('span.this_lsn_no');
        let lsn_line_no = $lsnLineNoObj.html();
        location.href = "/front/html/review.html?reviewCnt=1&lsn_line_no=" + lsn_line_no;
    });



})