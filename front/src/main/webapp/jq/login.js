$(function () {
  //아이디 입력 객체 찾기
  let $inputId = $("input[name=user_id]");

  //비밀번호 입력 객체 찾기
  let $inputPwd = $("input[name=user_pwd]");

  // 데이터 한번에 전송 해 줄 form 객체 생성
  let $form = $("form");
  $form.submit(function () {
    let url = "http://localhost:1124/back/login";
    let inputIdValue = $inputId.val();
    let inputPwdValue = $inputPwd.val();
    let data = "user_id=" + inputIdValue + "&user_pwd=" + inputPwdValue;

    $.ajax({
      url: url,
      method: "post",
      data: data,
      success: function (jsonObj) {
        if (jsonObj.status == 1) {
          alert(jsonObj.msg);
          location.replace("http://localhost:1124/front/html/main.html");
        } else {
          alert(jsonObj.msg);
        }
      },
      error: function (jqXHR, statusText, errorThrown) {
        alert(jqXHR.status + ":" + jqXHR.statusText);
      },
    });
    return false;
  });
});
