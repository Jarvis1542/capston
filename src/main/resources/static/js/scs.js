// 걍 지우지 마셈
'use strict';

// 거주지를 한꺼번에 등록할 배열
let csResultData = [];
$(document).ready(function () {
    $('input[type=radio][name=cable][value="DC차데몬"]:checked');
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
                    csResultData.push(data[i]);
                    if($('#res_name'+i+'').length==0){
                        $('#loadResidenceList').append(
                            '<li style="list-style-type : disc; text-indent: 3em;">' +
                            '<a id="res_name'+i+'" href="#'+i+'">'+data[i].res_name+'</a>' +
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
    $(document).on('click', '#res_name0', function () {
        $('#res_name').val(csResultData[0].res_name);
        $('#post_code').val(csResultData[0].post_code);
        $('#road_addr').val(csResultData[0].road_addr);
        $('#jibun_addr').val(csResultData[0].jibun_addr);
        $('#extra_addr').val(csResultData[0].extra_addr);
        $('#detail_addr').val(csResultData[0].detail_addr);
        $('#modalClose').click();
    });
// 모달에 두번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name1', function () {
        $('#res_name').val(csResultData[1].res_name);
        $('#post_code').val(csResultData[1].post_code);
        $('#road_addr').val(csResultData[1].road_addr);
        $('#jibun_addr').val(csResultData[1].jibun_addr);
        $('#extra_addr').val(csResultData[1].extra_addr);
        $('#detail_addr').val(csResultData[1].detail_addr);
        $('#modalClose').click();
    });
// 모달에 세번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name2', function () {
        $('#res_name').val(csResultData[2].res_name);
        $('#post_code').val(csResultData[2].post_code);
        $('#road_addr').val(csResultData[2].road_addr);
        $('#jibun_addr').val(csResultData[2].jibun_addr);
        $('#extra_addr').val(csResultData[2].extra_addr);
        $('#detail_addr').val(csResultData[2].detail_addr);
        $('#modalClose').click();
    });

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
