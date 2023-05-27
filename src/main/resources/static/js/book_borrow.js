// myscript.js 파일에 저장할 JavaScript 코드
document.getElementById("myForm").addEventListener("submit", function (event) {
    event.preventDefault(); // 기본 폼 제출 동작을 막음

    var form = event.target; // 폼 요소 가져오기
    var formData = new FormData(form); // 폼 데이터 가져오기

    // AJAX 요청
    var xhr = new XMLHttpRequest();
    xhr.open(form.method, form.action, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onload = function () {
        if (xhr.status === 200) {
            var response = xhr.responseText;

            // 팝업 창 띄우기
            var popupWindow = window.open("", "popupWindow", "width=500,height=300");
            popupWindow.document.write(response);
        }
    };

    xhr.send(new URLSearchParams(formData)); // 폼 데이터 전송
});