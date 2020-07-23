// #아이디 중복 체크
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

// #회원가입
$('#join').click(function () {
    var email =  $('#email').val();
    var password =  $('#password').val();
    var name =  $('#name').val();

    function valid(val) {
        if(val === null) return true;
        if(typeof val === 'string' && val === '') return true;
        if(typeof val === 'undefined') return true;

        return false;
    }
    if(valid($('#email').val())){
        errEmail();
        return;
    }
    if(valid($('#password').val())){
        errPassword();
        return;
    }
    if(valid($('#name').val())){
        errName();
        return;
    }
    function errEmail() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; text-indent: 3em;"><strong>이메일을 다시 확인해주세요</strong></p>';
        $('#errEmail').empty();
        $('#errEmail').append(html);
    }
    function errPassword() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; text-indent: 3em;"><strong>비밀번호를 다시 확인해주세요</strong></p>';
        $('#errPassword').empty();
        $('#errPassword').append(html);
    }
    function errName() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; text-indent: 3em;"><strong>이름을 다시 확인해주세요</strong></p>';
        $('#errName').empty();
        $('#errName').append(html);
    }

    var data = {
        email : $('#email').val(),
        password : $('#password').val(),
        name : $('#name').val()
    };

    $.ajax({
        type: 'post',
        url: '/rest/join',
        data: data
    }).done(function(){
        alert("회원가입 완료");
        window.location.href="/index/login";
    }).fail(function (error) {
        alert(error);
    });
});

// #로그인
$('#login').click(function () {
    var data = {
        email : $('#id').val(),
        password : $('#password').val()
    };

    $.ajax({
        type: 'post',
        url: '/rest/login',
        data: data
    }).done(function(msg){
        console.log(msg);
        if(msg == "로그인 성공") {
            alert("로그인 완료");
            window.location.href="/";
        } else {
            alert("아이디와 비밀번호를 확인해주세요");
            location.reload();
        }
    }).fail(function (error) {
        alert(error);
    });
});

// 이메일 인증 전송 버튼
$('#emailAuth').click(function () {
    var userId =  $('#memEditId').val();
    var data = {
        userId : $('#memEditId').val()
    };
    $.ajax({
       data : data,
       type : 'post',
       url : '/service/mail/'+userId
    }).done(function () {
        alert('이메일 요청 완료');
    }).fail(function (error) {
        JSON.stringify(error);
    });
});

// 이메일 인증 버튼
$('#')