$(function(){
    //1)페이지 로딩되었을 때 프로면 레슨 보여주기...
    $.ajax({
        url : "/back/viewmypage",
        method : 'get',
        success : function(jsonObj){
            let $lsnObj = $('div.lsnlist');
            $(jsonObj).each(function(i,element){
                $copyObj = $lsnObj.clone();
                
                let lsn_no = element.lsnNo;
                let lsn_title = element.lsnTitle;
                let lsn_status = element.lsnStatus;
                
                let lsns = '<div>';
                lsns += '<img src = "../lsn_images/' + lsn_no +'.jpg" alt="' + lsn_no + '번째레슨" width = "200" height = "200">';
                lsns += '<div class = "no">' + lsn_no + '</div>';
                lsns += '<div class = "title">' + lsn_title + '</div>';
                // lsns += '<div class = "expdate">종료일자</div>';
                // lsns += '<div class = "crnlsncnt">레슨진행횟수</div>';
                // lsns += '<div class = "lsncntsum">총레슨횟수</div>';
                lsns += '<input type="button" value="수강생관리">';  //미구현
                lsns += '<input type="button" value="레슨종료버튼">';  //미구현
                lsns += '<input type="button" value="레슨재개버튼">'; //미구현
                lsns += '</div>';


                //왜 하나만 사라지지??? 
                $closeObj = $('input[value=레슨종료버튼]');
                $resumeObj = $('input[value=레슨재개버튼]');

                if (lsn_status == 0) {
                    $closeObj.hide();
                }else{
                    $resumeObj.hide();
                }

                $copyObj.find('div.lsndetail').html(lsns);

                $('div.lsn').append($copyObj);
            })  
        },
        error : function(jqXHR){
            alert('오류 : ' + jqXHR.status);
        }
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