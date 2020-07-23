// 아이디 중복 체크
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

// 회원가입
$('#join').click(function () {
    if($('#email').val()==undefined){
        errEmail();
        return;
    }
    if($('#email').val()==null){
        errEmail();
        return;
    }
    if($('#email').val()==0){
        errEmail();
        return;
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
        window.location.href="/index/login";
    }).fail(function (error) {
        alert(error);
    });

    function errEmail() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>이메일을 다시 확인해주세요.</strong></p>';
        $('#errEmail').empty();
        $('#errEmail').append(html);
    }
    function errPassword() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>비밀번호를 다시 확인해주세요.</strong></p>';
        $('#errPassword').empty();
        $('#errPassword').append(html);
    }
    function errName() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>이름을 다시 확인해주세요.</strong></p>';
        $('#errName').empty();
        $('#errName').append(html);
    }
    function valid(val) {
        if(val === '') return false;
        if(val === null) return true;
        if(typeof val === 'string' && val === '') return true;
        if(typeof val === 'undefined') return true;

        return false;
    }
});

// 로그인
$('#login').click(function () {
    if(valid($('#email').val())){
        errEmail();
        return;
    }
    if(valid($('#password').val())){
        errPassword();
        return;
    }
    var data = {
        email : $('#email').val(),
        password : $('#password').val()
    };

    $.ajax({
        type: 'post',
        url: '/rest/login',
        data: data
    }).done(function(){
        window.location.href="/";
    }).fail(function (error) {
        alert(error);
    });

    function valid(val) {
        if(val === null) return true;
        if(typeof val === 'string' && val === '') return true;
        if(typeof val === 'undefined') return true;

        return false;
    }
    function errEmail() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>아이디를 다시 확인해주세요.</strong></p>';
        $('#errEmail').empty();
        $('#errEmail').append(html);
    }
    function errPassword() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>비밀번호를 다시 확인해주세요.</strong></p>';
        $('#errPassword').empty();
        $('#errPassword').append(html);
    }
});

// 이메일 인증 전송 버튼
$('#emailAuth').click(function () {
    var userId =  $('#memEditId').val();
    var data = {
        userId : $('#memEditId').val()
    };

    if(valid($('#memEditId').val())){
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>이메일을 입력해주세요.</strong></p>';
        $('#errEmailAuth').empty();
        $('#errEmailAuth').append(html);
    }

    $.ajax({
       data : data,
       type : 'post',
       url : '/service/mail/'+userId
    }).done(function () {
        var html = "";
        html += '<p style="font-size: 80%; color: green; ' +
            'text-indent: 3em;"><strong>이메일 인증 전송 완료했습니다.</strong></p>';
        $('#errEmailAuth').empty();
        $('#errEmailAuth').append(html);
    }).fail(function (error) {
        JSON.stringify(error);
    });

    function valid(val) {
        if(val === null) return true;
        if(typeof val === 'string' && val === '') return true;
        if(typeof val === 'undefined') return true;

        return false;
    }
});

// 이메일 인증 버튼
$('#EmailAuthBtn').click(function () {
    var data = {
        emailAuthText : $('#emailAuthText').val()
    }

    $.ajax({
       data : data,
       type : 'post',
        url : '/verifyCode'
    }).done(function (data) {
        if(data==0){
            var html = "";
            html += '<p style="font-size: 80%; color: green; ' +
                'text-indent: 3em;"><strong>인증코드와 맞지 않습니다.</strong></p>';
            $('#errEmailAuthBtn').empty();
            $('#errEmailAuthBtn').append(html);
        }else{
            var html = "";
            html += '<p style="font-size: 80%; color: green; ' +
                'text-indent: 3em;"><strong>인증 완료했습니다.</strong></p>';
            $('#errEmailAuthBtn').empty();
            $('#errEmailAuthBtn').append(html);
        }
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
});