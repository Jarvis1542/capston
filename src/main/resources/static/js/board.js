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

$('#reply').click(function () {

    var data = {
        rwriter : $('#rwriter').val(),
        rcontent : $('#rcontent').val()
    };

    $.ajax({
        type : 'post',
        url : '/rest/reply',
        data : data
    }).done(function () {
        alert('댓글달기 완료');
        window.location.href='/select/'+bno;
    }).fail(function (error) {
        alert(error);
    });
});

$('#delete').click(function () {
    var data = {
        bno : $('#bno').val(),
    };

    $.ajax({
        type : 'post',
        url : '/rest/delete',
        data : data
    }).done(function () {
        alert('삭제 완료');
        window.location.href='/';
    }).fail(function (error) {
        alert(error);
    });
});
