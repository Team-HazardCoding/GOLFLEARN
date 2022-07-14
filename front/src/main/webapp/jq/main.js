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
