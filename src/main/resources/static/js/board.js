// 글작성하기 버튼
$('#boardwrite').on('click', function () {
    var data = {
        title : $('#title').val(),
        writer : $('#writer').val(),
        content : $('#content').val()
    };

    $.ajax({
        data : data,
        url : '/rest/qbwrite',
        type : 'post'
    }).done(function () {
        alert('글등록 완료');
        window.location.href='/';
    }).fail(function (error) {
        JSON.stringify(alert(error));
    });
});
