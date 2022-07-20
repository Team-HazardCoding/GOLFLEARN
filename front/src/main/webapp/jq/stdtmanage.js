$(function(){
    let data = location.search.substring(1);
    /*console.log(data);*/
    $.ajax({
        url : "/back/viewstudentmanage",
        method : 'get',
        data : data,
        success : function(jsonObj){
            let $tableObj = $('div.tr');
            $(jsonObj).each(function(i,element){
                $copyObj = $tableObj.clone();

                let lsn_no = element.lsnNo;
                let lsn_title = element.lsn.lsnTitle;
                let user_name = element.user.userName;
                let user_phone = element.user.userPhone;
                let user_email = element.user.userEmail;
                let lsn_start_dt = element.lsnHistory.strLsnStartDt;
                let lsn_exp_dt = element.strLsnExpDt;
                let current_lsn_cnt = element.lsnHistory.currentLsnCnt;
                let lsn_cnt_sum = element.lsn.lsnCntSum;
                let stdt_lsn_status = element.stdtLsnStatus;
                
                // let stdtLine = '<div class = "tr">';
                // stdtLine += '<div class = "td">';
                // stdtLine += '<span class = "title">' + lsn_title + '</span>';
                let stdtLine = '<div class = "content">';
                stdtLine += '<span class = "no">' + lsn_no + '</span>';
                stdtLine += '<span class = "title">' + lsn_title + '</span>';
                stdtLine += '<span class = "name">' + user_name + '</span>';
                stdtLine += '<span class = "phone">' + user_phone + '</span>';
                stdtLine += '<span class = "email"> ' + user_email + ' </span>';
                stdtLine += '<span class = "startdt">' + lsn_start_dt + '</span>';
                stdtLine += '<span class = "expdt">' + lsn_exp_dt + '</span>';
                stdtLine += '<span class = "crntlsncnt">' + current_lsn_cnt + '</span>';
                stdtLine += '<span class = "lsncntsum">' + lsn_cnt_sum + '</span>';
                stdtLine += '<input type="button" class = "lsnchk" name="lsnchk" value="수업완료">';
                stdtLine += '</div>';
                // stdtLine += '</div>';

                $copyObj.find('div.td').html(stdtLine);

                $('div.table').append($copyObj);
            });
            $lsnchkObj = $('div.td');
            $lsnchkObj.on('click', 'input.lsnchk', function(){
                location.href = "/front/html/lessoncheckmodal.html";
            });
            
            // $lsnchkTest = $('input.lsnchk');
            // $modalTest = $('div.modal-content');
            // $lsnchkTest.click(function(){
            //     $modalTest.text("dkdkdkdkdkdkdk");
            // })
        },
        error : function(jqXHR){
            alert('오류 : ' + jqXHR.status);
        }
    });
    
    
 
    //수강완료시 1씩늘어나기..
    //클릭하면 팝업뜨게하고 확인누르면 전송 
    //jquery클릭 이벤트 누르고, 
    //html에 1씩 더해주고
    //서블릿으로 1씩더한값 보내서
    //서블릿에서 더한값받은것 매개변수로한 매서드 작동하면
    //DB에 업데이트되게 
    //max num보다 커지면 안되게 설정 

    //수강중단 누를시 
    //팝업뜨게하고 확인누르면 수강재개로 변경됨
    //레슨정보변경사항 서블릿으로 보내고
    //이거 받아서.. 위와 동일
    //추가적으로 메인페이지에서 내려가야됨.
    //아니면 상세페이지들어갔을때 수강신청이 막히게 하거나. -> 복잡

    //잡스케줄러 오류 수정하기 -> 쌤? 

    //css

});
