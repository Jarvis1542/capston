'use strict';
// 비밀번호 수정하기 버튼
$('#updatePassword').click(function () {
    var data = {
        password : $('#password').val()
    };

    $.ajax({
        data : data,
        url : '/rest/updatePassword',
        type : 'post'
    }).done(function (data) {

    }).fail(function (error) {
        JSON.stringify(alert(error));
    });
});