'use strict';
// 지도로 뒤로가기
$('#hpBookBack').on('click', function () {
    window.location.href = '/happyParking/hpSearch';
});

// 예약
$('#hpBook').on('click', function () {
    let carNum = "";
    let start_date = new Date($('#start_date').val());
    let end_date = new Date($('#end_date').val());
    let result1 = new Date(start_date);
    let result2 = new Date(end_date);
    console.log("시작일" + start_date);
    console.log("종료일" + end_date);

    let betweenDate = Math.ceil((end_date.getTime() - start_date.getTime()) / 60000 / 10 );
    console.log("결과값" + (betweenDate));

    let addmin10_fee = $('#addmin10_fee').text();
    let min30_fee = $('#min30_fee').text();

    if(3<betweenDate)
        $('#price').text(betweenDate*addmin10_fee-min30_fee-min30_fee);
    else
        $('#price').text(min30_fee);

    for(let i=0; i<5; i++){
        if($('#car'+i).length>0){
            carNum = $('#car'+i).val();
        }
    }

    var data = {
        start_date : result1,
        end_date : result2,
        email : $('#email').val(),
        hp_name : $('#hp_name').text(),
        car_num : carNum
    }
    $.ajax({
       data : data,
       type : 'post',
       url : '/rest/hpBook',
        success : function () {
            alert("예약 완료 되었습니다.");
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 결제
$('#hpPay').on('click', function () {
    var data = {
        hp_name : $('#hp_name').text(),
        price : $('#price').text(),
        email : $('#email').val(),
        phone : $('#phone').val(),
        user_name : $('#name').val()
    }
    $.ajax({
       data : data,
       type : 'post',
       url : '/rest/hpPay',
       success : function () {

       }
    });

    var IMP = window.IMP; // 생략가능
    IMP.init('imp89563591'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    IMP.request_pay({
        pg : 'kakao', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : data.hp_name,
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
$('#addHpBookmark').on('click', function () {
    $('#deleteHpBookmark').show();
    $('#addHpBookmark').hide();
    let hp_name = $('#hp_name').text();
    let data = {
        email : $('#email').val(),
        hp_name : hp_name
    }
    $.ajax({
        data : data,
        url : '/rest/addHpBookmark',
        type : 'post',
        success : function () {
            alert(hp_name+'을 즐겨찾기 추가했습니다.');

        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 즐겨찾기 해제
$('#deleteHpBookmark').on('click', function () {
    $('#addHpBookmark').show();
    $('#deleteHpBookmark').hide();
    let hp_name = $('#hp_name').text();
    let data = {
        email : $('#email').val(),
        hp_name : hp_name
    }
    $.ajax({
        data : data,
        url : '/rest/deleteHpBookmark',
        type : 'post',
        success : function () {
            alert(hp_name+'을 즐겨찾기 해제했습니다.');

        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

$(document).ready(function () {
    if($('#change').val()==0){
        $('#addHpBookmark').show();
        $('#deleteHpBookmark').hide();
    }
    if($('#change').val()==1){
        $('#addHpBookmark').hide();
        $('#deleteHpBookmark').show();
    }
});