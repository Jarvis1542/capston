'use strict';

// 예약
$('#csbook').on('click', function () {
    let today = new Date();

    var startDate = new Date($('#startDate').val());
    var endDate = new Date($('#endDate').val());

     // var startDate = new Date(2020,8,14,12,48,20);
     // var endDate = new Date(2020,8,14,13,48,20);
    console.log("시작일" + startDate);
    console.log("종료일" + endDate);

    var betweenDate = ((endDate.getTime() - startDate.getTime()) / 60000 / 10 );
    console.log("결과값" + (betweenDate));

    var hours = Math.floor((betweenDate % (1000*60*60*24))/(1000*60*60));
    var minutes = Math.floor((betweenDate % (1000*60*60))/(1000*60));
    var second = Math.floor((betweenDate % (1000*60))/(1000));

    console.log(hours + "시간" + minutes + "분" + second + "초");
    ///////////////////////////////////////////////////////////////////////

    // let startUseTime = $('#startUseTime').val();
    // let startUseTimeArray = startUseTime.split(":");
    // let startUseTimeObj = new Date(startUseTimeArray[0], startDateArray[1]);
    //
    // let endUseTime = $('#endUseTime').val();
    // let endUseTimeArray = endUseTime.split(":");
    // let endUseTimeObj = new Date(endUseTimeArray[0], endUseTimeArray[1]);


    // let betweenTime = Math.floor((endUseTimeObj.getTime() - startUseTimeObj.getTime())/1000/60/60);

    let addmin10Fee = $('#addmin10Fee').text();
    let min30Fee = $('#min30Fee').text();
    if(3<betweenDate)
        $('#price').text(betweenDate*addmin10Fee-min30Fee-min30Fee);
    else
        $('#price').text(min30Fee);


    // alert(betweenTime);



    var data = {
        startDate : result1,
        endDate : result2,
        startUseTime : $('#startUseTime').val(),
        endUseTime : $('#endUseTime').val(),
        email : $('#email').val(),
        chargeName : $('#chargeName').val()
    }
    $.ajax({
        data : data,
        type : 'post',
        url : '/rest/csbook',
        success : function () {
            alert("예약 완료 되었습니다.");
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 결제
$('#cspay').on('click', function () {
    var data = {
        chargeName : $('#chargeName').text(),
        price : $('#price').text(),
        email : $('#email').val(),
        phone : $('#phone').val(),
        userName : $('#name').val()
    }
    $.ajax({
        data : data,
        type : 'post',
        url : '/rest/cspay',
        success : function () {

        }
    });

    var IMP = window.IMP; // 생략가능
    IMP.init('imp89563591'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    IMP.request_pay({
        pg : 'kakao', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : data.chargeName,
        amount : data.price,
        buyer_email : data.email,
        buyer_name : data.userName,
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

