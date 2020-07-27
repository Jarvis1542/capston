// 비밀번호 수정하기 버튼
$('#residence').on('click', function () {
    var data = {
        postcode : $('#postcode').val(),
        roadAddress : $('#roadAddress').val(),
        jibunAddress : $('#jibunAddress').val(),
        extraAddress : $('#extraAddress').val(),
        detailAddress : $('#detailAddress').val(),
        lat : $('#lat').text(),
        lng : $('#lng').text()
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

$('#')