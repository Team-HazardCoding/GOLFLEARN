$(function() {
    let url = 'http://localhost:1124/back/main';
    $.ajax({
        url: url,
        success: function(jsonObj) {
            //card
            if(jsonObj.usertype=='0') {
            }
            $sidebarObj = $('div#sidebar');
            var arr = [];
            let sido = '<ul>';
            $(jsonObj.sido).each(function(key, item) {
                // console.log(Object.values(item));
                $keyObj = Object.keys(item);
                $itemObj = Object.values(item);
                for ( let i = 0; i < $itemObj.length; i++ ) {
                    arr.push($itemObj[i]);
                    console.log($keyObj);
                    sido += '<li class="sido" name="sido" value="' + $keyObj[i] + '"';
                    sido+= '>' + $itemObj[i] + '</li>';
                }
            })
            sido += '</ul>';
            $sidebarObj.append(sido);

            $lsnObj = $('div.col');
            // $lsnObj = $('<div class="col">');
            // $('div#card-container').append($lsnObj);
            $(jsonObj.lsns).each(function(index, item) {
                console.log(item.user.userName + "  " + item.locNo);
                let product = '<div class="lsn" id='+ item.lsnNo + '>';
                product +='<img src="/images/"' + item.user.userId + '/LessonThumbnail.jpeg">';// 각레슨의 이미지경로 다시 설정해야함 c밑의 경로임
                product +='<div class="lsn_content">';
                product +='<h5 class="lsn_title">' + item.lsnTitle + '</h5>';
                product +='<p class="prod_price">프로이름 : '+item.user.userName + '</p>';
                product +='<p class="tag_name">태그이름 : ' + item.locNo + '</p>';// 지역번호
                product +='<p class="star_point">별점  : '+ item.lsnStarPoint + '</p></div>';
                product +='</div>';
                // console.log(product);
                let $copyObj = $lsnObj.clone(); //복제본 생성
                $copyObj.html(product);
                $('div#card-container').append($copyObj);
            });   
            $lsnObj.hide(); // 복제본이 아닌 td태그를 숨김
			return false;
        }, 
        error: function(jqXHR) {
            alert("error: " + jqXHR.status);
        }
    });
    $('div#card-container').on('click', 'div.col>div.lsn', function(e) {
        // 클릭한 lsn_no
        $lsnId = $(this).attr('id');
        console.log($lsnId);
        location.href = '/front/html/viewlesson.html?lsn_no=' + $lsnId;
    });
})
var slides = document.querySelectorAll(".slides"),
  slide = document.querySelectorAll(".slides li");
(currentInx = 0),
  (slideIndex = slide.length),
  (slideWidth = 200),
  (slideMargin = 30),
  (prevBtn = document.querySelectorAll(".prev")),
  (nectBtn = document.querySelectorAll(".next"));

makeClone();

function makeClone() {
  for (var i = 0; i < slides.Count; i++) {
    //a.cloneNode(), a.cloneNode(true)
    var cloneSlide = slide[i].cloneNode(true);
    cloneSlide.classList.add("clone");
    //a.appendchild(b)
    slides.appendChild(cloneSlide);
  }
  for (var i = slideCount - 1; i >= 0; i--) {
    var cloneSlide = slide[i].cloneNode(true);
    cloneSlide.classList.add("clone");
    //a.appendchild(b)
    slides.prepend(cloneSlide);
  }
  updateWidth();
  setInitialPos();
  setTimeout(function () {
    slides.classList.add("animated");
  }, 100);
}

function updateWidth() {
  var currentSlides = document.querySelectorAll(".sldies li");
  var newSlideCount = currentSlides.length;

  var newWidth =
    (slideWidth + slideMargin) * newSlideCount - slideMargin + "px";
  slide.style.width = newWidth;
}

function setInitialPos() {
  var initialTrasiateValue = -(slideWidth + slideMargin) * slideCount;
  //slides{ trasform:translateX(-1000px);}
  slides.style.transform = "translateX(" + initialTrasiateValue + "px)";
}
