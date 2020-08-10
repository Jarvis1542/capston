// 걍 지우지 마셈
'use strict';

// 거주지를 한꺼번에 등록할 배열
let resultData = [];
$(document).ready(function () {
    $('input[type=radio][name=cable][value="DC차데몬"]:checked');
    $('#showEtc').hide();
    $('#loadResidence').on('click', function () {
        let data = {
            email : $('#email').val()
        }
        $.ajax({
            data : data,
            type : 'post',
            url : '/rest/loadResidence',
            success : function (data) {
                for(let i=0; i<=data.length; i++){
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
        $('#modalClose').click();
    });
// 모달에 두번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#resName1', function () {
        $('#resName').val(resultData[1].resName);
        $('#postcode').val(resultData[1].postcode);
        $('#roadAddress').val(resultData[1].roadAddress);
        $('#jibunAddress').val(resultData[1].jibunAddress);
        $('#extraAddress').val(resultData[1].extraAddress);
        $('#detailAddress').val(resultData[1].detailAddress);
        $('#modalClose').click();
    });
// 모달에 세번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#resName2', function () {
        $('#resName').val(resultData[2].resName);
        $('#postcode').val(resultData[2].postcode);
        $('#roadAddress').val(resultData[2].roadAddress);
        $('#jibunAddress').val(resultData[2].jibunAddress);
        $('#extraAddress').val(resultData[2].extraAddress);
        $('#detailAddress').val(resultData[2].detailAddress);
        $('#modalClose').click();
    });

// 충전소 등록
    $('#csReg').on('click', function (event) {
        event.preventDefault();
        if($('#chargeName').val()=='' || $('#chargeName').val()==null){
            errChargeName();
            return;
        }
        function errChargeName() {
            let html = "";
            html += '<p style="font-size: 80%; color: red; ' +
                'text-indent: 10em;"><strong>충전소 이름을 입력해주세요.</strong></p>';
            $('#chargeName').focus();
            $('#errChargeName').empty();
            $('#errChargeName').append(html);
        }

        let formData = new FormData($('#csForm')[0]);

        $.ajax({
            type : 'POST',
            url : '/rest/csReg',
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


// 주차장 요청 리스트로 뒤로가기
    $('#back').on('click', function () {
        window.location.href = '/admin/happyParkingRequestList';
    });

// 주차장 요청 승인하여 check를 n -> y 로 바꿈
    $('#request').on('click', function () {
        let data = {
            parkingName : $('#parkingName'). val()
        }

        $.ajax({
            data : data,
            type : 'put',
            url : '/admin/updateParkingChk',
            success : function () {
                alert('승인 완료 되었습니다.');
                window.location.href = '/admin/happyParkingRequestList';
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    });
});

// 주차장 요청 승인하여 check를 n -> y 로 바꿈
$('#requestbtn').on('click', function () {
    let data = {
        chargeName : $('#CHARGENAME'). val()
    }
    alert(data.chargeName);
    $.ajax({
        data : data,
        type : 'put',
        url : '/admin/updateChargingChk',
        success : function () {
            alert('승인 완료 되었습니다.');
            window.location.href = '/admin/csRequestList';
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});
