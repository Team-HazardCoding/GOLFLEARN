$(function() {
    $.ajax({
        url: 'http://localhost:1124/back/main',
        success: function(jsonObj) {
            //card
            // console.log(jsonObj);
            // console.log(jsonObj.lsns);

            $lsnObj = $('div.col');
            
            $(jsonObj.lsns).each(function(index, item) {
                console.log(item.user.userName + "  " + item.locNo);

                let product = '<div class="lsn"><img>';
                product +='<div class="lsn_content">';
                product +='<h5 class="lsn_title">' + item.lsnTitle + '</h5>';
                product +='<p class="prod_price">프로이름 : '+item.user.userName + '</p>';
                product +='<p class="tag_name">태그이름 : ' + '</p>';
                product +='<p class="star_point">별점  : '+ item.lsnStarPoint + '</p></div>';
                product +='</div>';
                
                console.log(product);

                // let $table = $('div.col'); // 복제본 append 할 상대
                let $copyObj = $lsnObj.clone(); //복제본 생성
                // console.log($copyObj.text());

                // $titleObj.html('<div>title</div>');
                // item.lsn_title
                // $imgObj.attr("src","../img/" + item.user.userName + "thumbnail.jpeg"); // 이미지 href 속성 변경하기
                // $imgObj.attr("alt", item.user.userName); // 이미지의 alt 속성을 변경함
                // $liObj.html(item.prod_name); // li의 텍스트 값을 prod_name의 값으로 변경함
                $copyObj.html(product);
                $('div#card-container').append($copyObj);
                // $table.append($copyObj); // table이 감싸고 있는 태그이기 때문에 table에 clone한 copyObj를 append 시켜줌
            });   
            $lsnObj.hide(); // 복제본이 아닌 td태그를 숨김
        }, 
        error: function(jqXHR) {
            alert("error: " + jqXHR.status);
        }
    });
})