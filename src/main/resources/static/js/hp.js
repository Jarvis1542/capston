// 걍 지우지 마셈
'use strict';

// 주차장 등록
$('#hpReg').on('click', function (event) {
    event.preventDefault();
    if($('#hp_name').val()=='' || $('#hp_name').val()==null){
        errParkingName();
        return;
    }
    function errParkingName() {
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 이름을 입력해주세요.</strong></p>';
        $('#hp_name').focus();
        $('#errParkingName').empty();
        $('#errParkingName').append(html);
    }

    if($('#place').val()=='' || $('#place').val()==null){
        errPlace();
        return;
    }
    function errPlace() {
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 공간 수를 입력해주세요.</strong></p>';
        $('#place').focus();
        $('#errPlace').empty();
        $('#errPlace').append(html);
    }
    if($('#min30_fee').val()=='' || $('#min30_fee').val()==null){
        errMin30Fee();
        return;
    }
    function errMin30Fee() {
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>기본 요금을 입력해주세요.</strong></p>';
        $('#min30_fee').focus();
        $('#errMin30Fee').empty();
        $('#errMin30Fee').append(html);
    }
    if($('#addMin10_fee').val()=='' || $('#addMin10_fee').val()==null){
        errAddMin10Fee();
        return;
    }
    function errAddMin10Fee() {
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>추가 요금을 입력해주세요.</strong></p>';
        $('#addMin10_fee').focus();
        $('#errAddMin10Fee').empty();
        $('#errAddMin10Fee').append(html);
    }
    if($('#start_manage_time').val()=='' || $('#start_manage_time').val()==null){
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>운영 시작 시간을 입력해주세요.</strong></p>';
        $('#start_manage_time').focus();
        $('#errStartManageTime').empty();
        $('#errStartManageTime').append(html);
        return;
    }
    if($('#end_manage_time').val()=='' || $('#end_manage_time').val()==null){
        let html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 3em;"><strong>운영 시작 시간을 입력해주세요.</strong></p>';
        $('#end_manage_time').focus();
        $('#errEndManageTime').empty();
        $('#errEndManageTime').append(html);
        return;
    }

    let formData = new FormData($('#hp_form')[0]);
    console.log(formData);

    $.ajax({
        type : 'POST',
        url : '/rest/hpReg',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        data : formData,
        success : function () {
            alert('주차장 등록이 완료되었습니다.');
            window.location.href = '/';
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 주차장 요청 리스트로 뒤로가기
$('#hpBack').on('click', function () {
    window.location.href = '/admin/hpRequestList';
});

// 주차장 요청 승인하여 check를 n -> y 로 바꿈
$('#hpRequestBtn').on('click', function () {
    let data = {
        hp_name : $('#hp_name'). val()
    }

    $.ajax({
        data : data,
        type : 'put',
        url : '/admin/updateParkingChk',
        success : function () {
            alert('승인 완료 되었습니다.');
            window.location.href = '/admin/hpRequestList';
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 주차장 지도 검색
$('#keySearch').on('click', function () {

    var data = {
        hp_name : $('#keyword').val()
    }
    $.ajax({
        data : data,
        type : 'POST',
        url : '/rest/hpMapSearch',
        success : function (data) {
            setCenter(data[0].lat, data[0].lng);
            // window.location.replace(document.location.href);
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});
