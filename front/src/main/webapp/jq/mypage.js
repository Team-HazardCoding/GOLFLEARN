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
    //레슨내역 주르르르르륵나오게 
    


    

    $.ajax({
        url : "/back/viewmypage",
        // method : 'get',
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
            let $lsnObj = $('div.lsnlist');
            $(jsonObj).each(function(i,element){
                $copyObj = $lsnObj.clone();

                //*****여기서 ll [ JSON ] 으로 오는 정보를 어떻게 일일이 빼올지 고민 
                let jsonarr = jsonObj.ll[i];
                $(jsonarr).each(function(i){

                
                let lsn_line_no = jsonarr.lsnLineNo;
                let lsn_no = jsonarr.lsnNo;
                let lsn_title = jsonarr.lsn.lsnTitle;
                let lsn_exp_dt = jsonarr.lsnExpDt;
                let stdt_lsn_status = jsonarr.stdtLsnStatus;
                let my_star_score = jsonarr.lsnReview.myStarScore;
                let crnt_lsn_cnt = jsonarr.crntLsnCnt;
                let lsn_cnt_sum = jsonarr.lsnCntSum;

                console.log(Object.keys(jsonObj).length);
                console.log(lsn_line_no);
                // let lsn_line_no = jsonObj.ll[0].lsnLineNo;
                // let lsn_no = jsonObj.ll[0].lsnNo;
                // let lsn_title = jsonObj.ll[0].lsn.lsnTitle;
                // let lsn_exp_dt = jsonObj.ll[0].lsnExpDt;
                // let stdt_lsn_status = jsonObj.ll[0].stdtLsnStatus;
                // let my_star_score = jsonObj.ll[0].lsnReview.myStarScore;
                // let crnt_lsn_cnt = jsonObj.ll[0].crntLsnCnt;
                // let lsn_cnt_sum = jsonObj.ll[0].lsnCntSum;
                
                let lessonLine = '<ul>';
                lessonLine += '<li><img src = "../lsn_images/' + lsn_line_no +'.jpg" alt="' + lsn_line_no + '번째레슨" width = "200" height = "200"></li>';
                lessonLine += '<li><div>레슨번호: ' + lsn_line_no + '<span class = "this_lsn_no"></span></div></li>'
                lessonLine += '<li><div>가져온레슨명: ' + lsn_title + '<span class = "title"></span></div></li>'
                lessonLine += '<li><div>종료일자: ' + lsn_exp_dt + '<span class = "exp_date"></span></div></li>'
                lessonLine += '<li><div>현재진행횟수: ' + crnt_lsn_cnt + '<span class = "crnt_lsn_cnt"></span></div></li>'
                lessonLine += '<li><div>현재진행횟수: ' + lsn_cnt_sum + '<span class = "lsn_cnt_sum"></span></div></li>'
                lessonLine += '<li><div>현재수강상태: ' + stdt_lsn_status + '<span class = "lsn_status"></span></div></li>'
                lessonLine += '</ul>'
                
                $copyObj.find('div.lsndetail1').html(lessonLine);
                
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



                // $('div.lsnlist1>img').attr('src', '../images/' + prod_no + '.jpg').attr('alt', prod_no );
                // $('div.lsnlist2 span.my_lsn_no').html(lsn_no);
                // $('div.lsnlist2 span.title').html(lsn_title);
                // $('div.lsnlist2 span.exp_date').html(lsn_exp_dt);
                // $('div.lsnlist2 span.crnt_lsn_cnt').html(crnt_lsn_cnt);
                // $('div.lsnlist2 span.lsn_cnt_sum').html(lsn_cnt_sum);
                // $('div.lsnlist2 span.lsn_status').html(stdt_lsn_status);
        },
        error : function(jqXHR){
            alert('오류 : ' + jqXHR.status + lsn_no);
        }

    });










})