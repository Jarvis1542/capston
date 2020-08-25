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

$('select[name=select]').on('click', function () {
    var text = $('select[name=select] option[value="충전소"]').text();
    alert(text);
});
$('#parking').on('click', function () {
    var text = $('#parking').text();
    alert(text);
});

// 회원정보 수정 - 회원 구분에 따라 추가 양식 제공(memberEdit)
$('input[type=radio][name=mem_role]').on('click', function() {
    var chkValue = $('input[type=radio][name=mem_role]:checked').val();

    if (chkValue == 'no') {
        $('#mp_form').css('display', 'none');
        $('#reg_form').css('display', 'none');
        $('#no_edit').show();
        // return false;
    } else if (chkValue == 'mp') {
        $('#mp_form').css('display', 'block');
        $('#reg_form').css('display', 'none');
        $('#no_edit').hide();
        // return false;
    } else if (chkValue == 'reg') {
        $('#mp_form').css('display', 'none');
        $('#reg_form').css('display', 'block');
        $('#no_edit').hide();
        // return false;
    }
});

// 회원 정보 수정 버튼
$('#no_edit').on('click', function (event) {
    event.preventDefault();
    var data = {
        name : $('#name').val(),
        phone : $('#phone').val(),
        email : $('#email').val()
    }

    $.ajax({
        type : 'POST',
        url : '/rest/edit',
        data : data,
        success : function (data) {
            if(data==3){
                alert('회원 정보 수정 완료했습니다.');
                window.location.href = "/";
            }else{
                event.preventDefault();
                alert('다시 입력');
            }
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 회원 정보 경비 요청 버튼
$('#mp_edit').on('click', function (event) {
    event.preventDefault();
    var form = $('#edit_form')[0];
    var formData = new FormData(form);

    $.ajax({
        type : 'POST',
        url : '/rest/edit',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        data : formData,
        success : function (data) {
            if(data==1){
                alert('경비 요청 완료했습니다.');
                window.location.href = "/";
            }else{
                event.preventDefault();
                alert('다시 입력');
            }
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 회원 정보 등록자 요청 버튼
$('#reg_edit').on('click', function (event) {
    event.preventDefault();
    var form = $('#edit_form')[0];
    var formData = new FormData(form);

    $.ajax({
        type : 'POST',
        url : '/rest/edit',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        data : formData,
        success : function (data) {
            if(data==2){
                alert('등록자 요청 완료했습니다.');
                window.location.href = "/";
            }else{
                event.preventDefault();
                alert('다시 입력');
            }
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 즐겨찾기의 예약 버튼
$('#moveHpBook').on('click', function () {
    let parkingName = $('#parkingName').text();
    let email = $('#email').val()
    window.location.href = '/happyParking/happyParkingBook/'+parkingName+'+'+email;
})