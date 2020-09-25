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

// 충전소 지도 검색
$('#keySearch').on('click', function () {

    var data = {
        scs_name : $('#keyword').val()
    }
    $.ajax({
        data : data,
        type : 'POST',
        url : '/rest/scsMapSearch',
        success : function (data) {
            setCenter(data[0].lat, data[0].lng);
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 충전소 타입별 검색
var type = {
    "AC3상" : false,
    "DC콤보" : false,
    "DC차데모" : false,
    "수퍼차저" : false,
    "완속" : false,
    "데스티네이션" : false,
}
var types =[];
function makeFilter(target){
    var tval = target.value;
    type[tval]=!type[tval];
    console.log(type);

    $.ajax({
        data : JSON.stringify(type),
        type : 'POST',
        contentType:'application/json',
        dataType: 'json',
        url : '/rest/typeFilter',
        success : function (data) {
            console.log("타입data :" + JSON.stringify(data));
            for(let i=0; i < data.length; i++) {
                types[i] = data[i];
                console.log("===============");
                console.log("타입결과 충전소 이름 : " + types[i].scs_name);
                console.log("타입결과 충전소 타입 : " + types[i].scs_type);


            }
        },
        error : function (error) {
            console.log('실패');
            console.log(JSON.stringify(error));
        }
    });
}

// 로그인 엔터
$("#search_frm input").keypress(function( event ) {
    if ( event.which == 13 ) {
        event.preventDefault();
        if($("#search_frm input").eq(0).val() == '' && $("#search_frm input").eq(1).val() == '') {
            return false;
        }
        $("#keySearch").first().trigger("click");
    }
});