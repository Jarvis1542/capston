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
        window.location.href='/otherService/QnA';
    }).fail(function (error) {
        JSON.stringify(alert(error));
    });
});

//삭제하기 버튼
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
        window.location.href='/otherService/QnA';
    }).fail(function (error) {
        alert(error);
    });
});

//수정하는 페이지로 넘어가는 버튼
$('#updateBtn').click(function () {
    var bno = $('#bno').val();
    window.location.href='/otherService/update/'+ bno;
})

//수전하기 버튼
$('#update').click(function () {
    var data = {
        title : $('#title').val(),
        writer : $('#writer').val(),
        content : $('#content').val(),
    };

    $.ajax({
        type : 'post',
        url : '/rest/update',
        data : data
    }).done(function () {
        alert('수정 완료');
        window.location.href='/';
    }).fail(function (error) {
        alert(error);
    });
});