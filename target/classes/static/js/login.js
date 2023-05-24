$("button[name=login]").click(function () {
    // 로그인 버튼이 클릭되었습니다.
    $.ajax({
        url: "/login",
        type: "POST",
        data: { user_id: "my_user_id", user_pw: "my_user_pw" },
        success: function (response) {
            if (response.success) {
                // 로그인 성공
                alert("로그인 성공!");
                window.location.href = "/home";
            } else {
                // 로그인 실패
                alert("Invalid user_id or user_pw.");
            }
        }
    });
});