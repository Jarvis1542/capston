// 걍 지우지 마셈
'use strict';

// 충전소 등록
$('#csReg').on('click', function (event) {
    event.preventDefault();
    if($('#scs_name').val()=='' || $('#scs_name').val()==null){
        errChargeName();
        return;
    }
    function errChargeName() {
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>충전소 이름을 입력해주세요.</strong></p>';
        $('#scs_name').focus();
        $('#errChargeName').empty();
        $('#errChargeName').append(html);
    }

    let formData = new FormData($('#cs_form')[0]);

    $.ajax({
        type : 'POST',
        url : '/rest/scsReg',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        data : formData,
        success : function () {
            alert('충전소 등록이 완료되었습니다.');
            window.location.href = '/';
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 충전소 요청 리스트로 뒤로가기
$('#scsBack').on('click', function () {
    window.location.href = '/admin/scsRequestList';
});

// 충전소 요청 승인하여 check를 n -> y 로 바꿈
$('#csRequestBtn').on('click', function () {
    let data = {
        scs_name : $('#scs_name'). val()
    }
    $.ajax({
        data : data,
        type : 'put',
        url : '/admin/updateChargingChk',
        success : function () {
            alert('승인 완료 되었습니다.');
            window.location.href = '/admin/scsRequestList';
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});
