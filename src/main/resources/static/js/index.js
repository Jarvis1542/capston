// 구글 로그인
function googleLogin(){
    window.location.href='/oauth2/authorization/google';
}

$('#checkId').click(function () {
    var data = {
        email : $('#email').val()
    };
    $.ajax({
        type: 'post',
        url: '/rest/checkId',
        data: data
    }).done(function(data){
        errEmail(data);
    }).fail(function (error) {
        alert(error);
    });
    function errEmail(data) {
        var html = '';
        if(data==0){
            html += '<p style="font-size: 80%; ' +
                'color: green; text-indent: 3em;">' +
                '<strong>사용 가능한 이메일입니다.</strong></p>';
            $('#errEmail').empty();
            return $('#errEmail').append(html);
        }else{
            html += '<p style="font-size: 80%; ' +
                'color: red; text-indent: 3em;">' +
                '<strong>이미 사용중인 아이디입니다.</strong></p>';
            $('#errEmail').empty();
            return $('#errEmail').append(html);
        }

    }
});