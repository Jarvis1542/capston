'use strict';
var resultData = [];
function valid(val) {
    if(val === null) return true;
    if(typeof val === 'string' && val === '') return true;
    if(typeof val === 'undefined') return true;

    return false;
}
$('#loadResidence').on('click', function () {
    var data = {
        email : $('#email').val()
    }
    $.ajax({
        data : data,
        type : 'post',
        url : '/rest/loadResidence',
        success : function (data) {
            for(var i=0; i<=data.length; i++){
                resultData.push(data[i]);
                if($('#resName'+i+'').length==0){
                    $('#loadResidenceList').append(
                        '<li style="list-style-type : disc; text-indent: 3em;">' +
                        '<a id="resName'+i+'" href="#'+i+'">'+data[i].resName+'</a>' +
                        '</li><br>'
                    );
                }else{
                    return;
                }
            }
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});
// 모달에 첫번째 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
$(document).on('click', '#resName0', function () {
    $('#resName').val(resultData[0].resName);
    $('#postcode').val(resultData[0].postcode);
    $('#roadAddress').val(resultData[0].roadAddress);
    $('#jibunAddress').val(resultData[0].jibunAddress);
    $('#extraAddress').val(resultData[0].extraAddress);
    $('#detailAddress').val(resultData[0].detailAddress);
});
// 모달에 두번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
$(document).on('click', '#resName1', function () {
    $('#resName').val(resultData[1].resName);
    $('#postcode').val(resultData[1].postcode);
    $('#roadAddress').val(resultData[1].roadAddress);
    $('#jibunAddress').val(resultData[1].jibunAddress);
    $('#extraAddress').val(resultData[1].extraAddress);
    $('#detailAddress').val(resultData[1].detailAddress);
});
// 모달에 세번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
$(document).on('click', '#resName2', function () {
    $('#resName').val(resultData[2].resName);
    $('#postcode').val(resultData[2].postcode);
    $('#roadAddress').val(resultData[2].roadAddress);
    $('#jibunAddress').val(resultData[2].jibunAddress);
    $('#extraAddress').val(resultData[2].extraAddress);
    $('#detailAddress').val(resultData[2].detailAddress);
});
// $('#overlay').modal('show');
//
// setTimeout(function() {
//     $('#overlay').modal('hide');
// }, 5000);

// 기타 버튼 눌리면 기타 입력란 제공
$('input[type=radio][name=parkingType]').on('click', function() {
    var chkValue = $('input[type=radio][name=parkingType]:checked').val();
    if (chkValue == '단독 주택') {
        $('#showEtc').hide();
    } else if (chkValue == '개인 사유지') {
        $('#showEtc').hide();
    } else if (chkValue == '공동 주택') {
        $('#showEtc').hide();
    } else if(chkValue == '기타'){
        $('#showEtc').show();
    }
});


$('#hpReg').on('click', function (event) {
    event.preventDefault();
    if($('#parkingName').val()=='' || $('#parkingName').val()==null){
        errParkingName();
        return;
    }
    function errParkingName() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 이름을 입력해주세요.</strong></p>';
        $('#parkingName').focus();
        $('#errParkingName').empty();
        $('#errParkingName').append(html);
    }
    if($('#showEtc').show==true){
        if($('#etc').val()=='' || $('#etc').val()==null){
            errEtc();
            return;
        }
    }
    function errEtc() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
        $('#etc').focus();
        $('#errEtc').empty();
        $('#errEtc').append(html);
    }
    if($('#place').val()=='' || $('#place').val()==null){
        errPlace();
        return;
    }
    function errPlace() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
        $('#place').focus();
        $('#errPlace').empty();
        $('#errPlace').append(html);
    }
    if($('#min30Fee').val()=='' || $('#min30Fee').val()==null){
        errMin30Fee();
        return;
    }
    function errMin30Fee() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
        $('#min30Fee').focus();
        $('#errMin30Fee').empty();
        $('#errMin30Fee').append(html);
    }
    if($('#addMin10Fee').val()=='' || $('#addMin10Fee').val()==null){
        errAddMin10Fee();
        return;
    }
    function errAddMin10Fee() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
        $('#addMin10Fee').focus();
        $('#errAddMin10Fee').empty();
        $('#errAddMin10Fee').append(html);
    }
    if($('#manageTime').val()=='' || $('#manageTime').val()==null){
        errManageTime();
        return;
    }
    function errManageTime() {
        var html = "";
        html += '<p style="font-size: 80%; color: red; ' +
            'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
        $('#manageTime').focus();
        $('#errManageTime').empty();
        $('#errManageTime').append(html);
    }

    var formData = new FormData($('#hpForm')[0]);

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
