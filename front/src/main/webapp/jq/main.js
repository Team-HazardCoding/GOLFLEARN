$(function () {
  let url = "http://http://localhost:1124/back/main";
  $.ajax({
    url: url,
    success: function (jsonObj) {
      //card
      $sidebarObj = $("div#sidebar");
      var arr = [];
      let sido = "<ul>";
      $(jsonObj.sido).each(function (key, item) {
        // console.log(Object.values(item));
        $keyObj = Object.keys(item);
        $itemObj = Object.values(item);
        for (let i = 0; i < $itemObj.length; i++) {
          arr.push($itemObj[i]);
          console.log($keyObj);
          sido += '<li class="sido" name="sido" value="' + $keyObj[i] + '"';
          sido += ">" + $itemObj[i] + "</li>";
        }
      });
      sido += "</ul>";
      $sidebarObj.append(sido);

      $lsnObj = $("div.col");
      $(jsonObj.lsns).each(function (index, item) {
        console.log(item.user.userName + "  " + item.locNo);

        let product = '<div class="lsn"><img>';
        product += '<div class="lsn_content">';
        product += '<h5 class="lsn_title">' + item.lsnTitle + "</h5>";
        product +=
          '<p class="prod_price">프로이름 : ' + item.user.userName + "</p>";
        product += '<p class="tag_name">태그이름 : ' + "</p>";
        product +=
          '<p class="star_point">별점  : ' + item.lsnStarPoint + "</p></div>";
        product += "</div>";

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
        $("div#card-container").append($copyObj);
        // $table.append($copyObj); // table이 감싸고 있는 태그이기 때문에 table에 clone한 copyObj를 append 시켜줌
      });
      $lsnObj.hide(); // 복제본이 아닌 td태그를 숨김
      return false;
    },
    error: function (jqXHR) {
      alert("error: " + jqXHR.status);
    },
  });

  let $sidoBtn = $("div#sidebar");
  // let $sidoNm = $('li[name=sido]');
  $sidoBtn.on("click", "li", function () {
    // let sidoName = $(this).name();

    // console.log($sidoNm);
    let $sidoVal = $(this);
    $.ajax({
      url: url,
      method: "get",
      data: { sido: $sidoVal.val() },
      success: function () {
        console.log($sidoVal.val());
        // $.ajax({
        //     url:url,
        //     method: 'get',
        //     success: function
        // })
      },
      error: function (jqXHR) {
        alert("error: " + jqXHR.status);
      },
    });
    // 각 시군구가 tag-name에 뿌려져야 함.
    // tag-name 을 리스트로 받아와서 반복문을 돌아서 뿌려줌
  });
}); //fucntion 닫힘
