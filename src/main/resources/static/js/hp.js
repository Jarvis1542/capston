// 걍 지우지 마셈
'use strict';

// 거주지를 한꺼번에 등록할 배열
let resultData = []; 
$(document).ready(function () {
    $('input[type=radio][name=parkingType][value="공동 주택"]:checked');
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
        $('#res_name').val(resultData[0].res_name);
        $('#post_code').val(resultData[0].post_code);
        $('#road_addr').val(resultData[0].road_addr);
        $('#jibun_addr').val(resultData[0].jibun_addr);
        $('#extra_addr').val(resultData[0].extra_addr);
        $('#detail_addr').val(resultData[0].detail_addr);
        $('#modalClose').click();
    });
// 모달에 두번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name1', function () {
        $('#res_name').val(resultData[1].res_name);
        $('#post_code').val(resultData[1].post_code);
        $('#road_addr').val(resultData[1].road_addr);
        $('#jibun_addr').val(resultData[1].jibun_addr);
        $('#extra_addr').val(resultData[1].extra_addr);
        $('#detail_addr').val(resultData[1].detail_addr);
        $('#modalClose').click();
    });
// 모달에 세번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name2', function () {
        $('#res_name').val(resultData[2].res_name);
        $('#post_code').val(resultData[2].post_code);
        $('#road_addr').val(resultData[2].road_addr);
        $('#jibun_addr').val(resultData[2].jibun_addr);
        $('#extra_addr').val(resultData[2].extra_addr);
        $('#detail_addr').val(resultData[2].detail_addr);
        $('#modalClose').click();
    });

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
                'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
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
                'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
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
                'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
            $('#addMin10_fee').focus();
            $('#errAddMin10Fee').empty();
            $('#errAddMin10Fee').append(html);
        }
        if($('#manage_time').val()=='' || $('#manage_time').val()==null){
            errManageTime();
            return;
        }
        function errManageTime() {
            let html = "";
            html += '<p style="font-size: 80%; color: red; ' +
                'text-indent: 10em;"><strong>주차장 타입을 입력해주세요.</strong></p>';
            $('#manage_time').focus();
            $('#errManageTime').empty();
            $('#errManageTime').append(html);
        }

        let formData = new FormData($('#hp_form')[0]);

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
});
