'use strict';
$(document).ready(function () {
    const picker2 = document.getElementById('start_date');
    const picker3 = document.getElementById('end_date');

    // 지도로 뒤로가기
    $('#scsBookBack').on('click', function () {
        window.location.href = '/chargingStation/scsSearch';
    });

    let start_manage_time, end_manage_time;
    function compare_func(hour, min) {
        let start_hour = start_manage_time.getHours();
        let end_hour = end_manage_time.getHours();
        let start_min = start_manage_time.getMinutes();
        let end_min = end_manage_time.getMinutes();

        if(start_hour == hour) {
            if(start_min <= min){
                console.log("운영 시간 범위 내에 들어옵니다.");
            } else {
                alert("운영시간이 초과 되었습니다.");
                $('#start_date').val("");
                $('#end_date').val("");
                return false;
            }
        } else if(end_hour == hour) {
            if(end_min >= min){
                console.log("운영 시간 범위 내에 들어옵니다.");
            } else {
                alert("운영시간이 초과 되었습니다.");
                $('#start_date').val("");
                $('#end_date').val("");
                return false;
            }
        } else if(start_hour <= hour && end_hour >= hour){
            console.log("운영 시간 범위 내에 들어옵니다.");
        } else {
            alert("운영시간이 초과 되었습니다.");
            $('#start_date').val("");
            $('#end_date').val("");
            return false;
        }
    }
    picker2.addEventListener('change', function(e){
        let start_date = new Date($('#start_date').val());
        let day = start_date.getDate();
        let month = start_date.getMonth() + 1;
        let year = start_date.getFullYear();
        let hour = start_date.getHours();
        let min = start_date.getMinutes();

        hour >= 10 ? hour = hour : hour = "0" + hour;
        min >= 10 ? min = min : min = "0" + min;
        month >= 10 ? month = month : month = "0" + month;

        let dateStr = year + "-" + month + "-" + day + "T" + hour + ":" + min;
        let dateStr2 = year + "-" + month + "-" + day;
        console.log(dateStr);

        start_manage_time = new Date(dateStr2+" "+$("#start_manage_time").text()+":00");
        end_manage_time = new Date(dateStr2+" "+$("#end_manage_time").text()+":00");

        let picker_bool = compare_func(hour, min, dateStr2);
        if(picker_bool != false) {
            $("#end_date").val(dateStr);
        }
    });

    picker3.addEventListener('change', function(e){
        let end_date = new Date($('#end_date').val());
        let hour = end_date.getHours();
        let min = end_date.getMinutes();

        compare_func(hour, min);
    });

    // 예약
    $('#scsBook').on('click', function (event) {
        let carNum = "";
        let start_date = new Date($('#start_date').val());
        let end_date = new Date($('#end_date').val());

        let day = start_date.getDate();
        let month = start_date.getMonth() + 1;
        let year = start_date.getFullYear();
        let dateStr = year + "-" + month + "-" + day;

        let start_manage_time = new Date(dateStr+" "+$("#start_manage_time").text()+":00");
        let end_manage_time = new Date(dateStr+" "+$("#end_manage_time").text()+":00");

        let result1 = new Date(start_date);
        let result2 = new Date(end_date);

        if(start_date >= start_manage_time && end_date <= end_manage_time) {
            console.log("운영시간 / 예약시간 일치");
        } else {
            alert("운영 시간을 확인하여 예약 시간을 다시 설정해주세요!");
            return false;
        }

        console.log("시작일" + start_date);
        console.log("종료일" + end_date);

        console.log("등록자 시작시간 " + start_manage_time);
        console.log("등록자 마감시간 " + end_manage_time);

        let betweenDate = Math.ceil((end_date.getTime() - start_date.getTime()) / 60000 / 10 );
        console.log("결과값" + (betweenDate));

        let addmin10_fee = $('#addmin10_fee').text();
        let min30_fee = $('#min30_fee').text();

        if(3<betweenDate)
            $('#price').text(eval(addmin10_fee*(betweenDate-3) + eval(min30_fee)));
        else
            $('#price').text(min30_fee);

        if($('#car0').length>0){
            carNum = $('#car0').val();
        }else{
            event.preventDefault();
            alert("예약할 차량을 선택해주십시오.");
            return false;
        }
        if($('#start_date').val()=='' || $('#start_date').val()==null){
            event.preventDefault();
            alert('예약시작날짜를 정해주십시오.');
            return false;
        }
        if($('#end_date').val()=='' || $('#end_date').val()==null){
            event.preventDefault();
            alert('예약종료날짜를 정해주십시오.');
            return false;
        }
        var data = {
            start_date : result1,
            end_date : result2,
            email : $('#email').val(),
            scs_name : $('#scs_name').text(),
            car_num : carNum
        }
        $.ajax({
            data : data,
            type : 'post',
            url : '/rest/scsBook',
            success : function () {
                alert("예약 완료 되었습니다.");
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
        $('#scsBook').hide();
        $('#scsBookCancel').show();
    });

    // 결제
    $('#scsPay').on('click', function () {
        let carNum = "";
        let start_date = new Date($('#start_date').val());
        for(let i=0; i<5; i++){
            if($('#car'+i).length>0){
                carNum = $('#car'+i).val();
            }
        }

        var data = {
            scs_name : $('#scs_name').text(),
            price : $('#price').text(),
            email : $('#email').val(),
            phone : $('#phone').val(),
            user_name : $('#name').val(),
            car_num : carNum,
            start_date : start_date
        }
        $.ajax({
            data : data,
            type : 'post',
            url : '/rest/scsPay',
            success : function () {

            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });

        var IMP = window.IMP; // 생략가능
        IMP.init('imp89563591'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        IMP.request_pay({
            pg : 'kakao', // version 1.1.0부터 지원.
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : data.scs_name,
            amount : data.price,
            buyer_email : data.email,
            buyer_name : data.user_name,
            buyer_tel : data.phone,
            buyer_addr : '서울특별시 강남구 삼성동 구구로99',
            buyer_postcode : '123-456',
            m_redirect_url : 'https://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
            }
            alert(msg);
        });
    });

    // 즐겨찾기 추가
    $('#addSCSBookmark').on('click', function () {
        $('#deleteSCSBookmark').show();
        $('#addSCSBookmark').hide();
        let scs_name = $('#scs_name').text();
        let data = {
            email : $('#email').val(),
            scs_name : scs_name
        }
        $.ajax({
            data : data,
            url : '/rest/addSCSBookmark',
            type : 'post',
            success : function () {
                alert(scs_name+'을 즐겨찾기 추가했습니다.');

            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    });

    // 즐겨찾기 해제
    $('#deleteSCSBookmark').on('click', function () {
        $('#addSCSBookmark').show();
        $('#deleteSCSBookmark').hide();
        let scs_name = $('#scs_name').text();
        let data = {
            email : $('#email').val(),
            scs_name : scs_name
        }
        $.ajax({
            data : data,
            url : '/rest/deleteSCSBookmark',
            type : 'post',
            success : function () {
                alert(scs_name+'을 즐겨찾기 해제했습니다.');

            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    });

    $('#scsBookCancel').on('click', function () {
        $('#scsBook').show();
        $('#scsBookCancel').hide();
        let data = {
            email : $('#email').val(),
            scs_name : $('#scs_name').text()
        }

        $.ajax({
            data : data,
            type : 'post',
            url : '/rest/scsBookCancel',
            success : function () {
                alert('예약 취소 되었습니다.')
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    });

    if($('#change').val()==0){
        $('#addSCSBookmark').show();
        $('#deleteSCSBookmark').hide();
    }

    if($('#change').val()==1){
        $('#addSCSBookmark').hide();
        $('#deleteSCSBookmark').show();
    }

    if($('#checkSCSBookBtn').val()==0){
        $('#scsBook').show();
        $('#scsBookCancel').hide();
    }

    if($('#checkSCSBookBtn').val()==1){
        $('#scsBook').hide();
        $('#scsBookCancel').show();
    }
});