'use strict';
$('#bookBack').on('click', function () {
    window.location.href = '/happyParking/happyParkingSearch';
});

// 즐겨찾기 등록
$('#firstBookmark').on('click', function () {
    let src = ($(this).attr('src') === '/img/bookmark.png') ? '/img/bookmark2.png' : '/img/bookmark.png';
    if(src === '/img/bookmark.png'){
        var data = {
            parkingName : $('#parkingName').text(),
            email : $('#email').val(),
            imgSrc : src.toString()
        }
        $.ajax({
            data : data,
            type : 'post',
            url : '/rest/hpBookmark',
            success : function () {
                alert('즐겨찾기 추가 되었습니다!');
                $('#bookmark').attr('src', src);
                window.location.href = '/happyParking/' + data.parkingName + '+' + data.email;
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    }
});

// 즐겨찾기 취소
$('#secondBookmark').on('click', function () {
    let src = ($(this).attr('src') === '/img/bookmark.png') ? '/img/bookmark2.png' : '/img/bookmark.png';
    if (src === '/img/bookmark2.png') {
        var data = {
            parkingName: $('#parkingName').val(),
            email: $('#email').val(),
            imgSrc: src
        }
        $.ajax({
            data: data,
            type: 'post',
            url: '/rest/hpBookmark',
            success: function () {
                alert('즐겨찾기 해제 되었습니다.');
                $('#bookmark').attr('src', src);
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    }
});
// 예약
$('#book').on('click', function () {
    let today = new Date();

    let startDate = $('#startDate').val();
    let startDateArray = startDate.split("-");
    let startDateObj = new Date(startDateArray[0], Number(startDateArray[1])-1, startDateArray[2]);
    let result1 = new Date(startDate);
    console.log(result1);


    let endDate = $('#endDate').val();
    let endDateArray = endDate.split("-");
    let endDateObj = new Date(endDateArray[0], Number(endDateArray[1])-1, endDateArray[2]);
    let result2 = new Date(endDate);

    let betweenDate = Math.floor((endDateObj.getTime() - startDateObj.getTime())/1000/60/60/24);
    ///////////////////////////////////////////////////////////////////////

    // let startUseTime = $('#startUseTime').val();
    // let startUseTimeArray = startUseTime.split(":");
    // let startUseTimeObj = new Date(startUseTimeArray[0], startDateArray[1]);
    //
    // let endUseTime = $('#endUseTime').val();
    // let endUseTimeArray = endUseTime.split(":");
    // let endUseTimeObj = new Date(endUseTimeArray[0], endUseTimeArray[1]);


    // let betweenTime = Math.floor((endUseTimeObj.getTime() - startUseTimeObj.getTime())/1000/60/60);

    let min30Fee = $('#min30Fee').text();

    $('#price').text(betweenDate*min30Fee);


    // alert(betweenTime);



    var data = {
        startDate : result1,
        endDate : result2,
        startUseTime : $('#startUseTime').val(),
        endUseTime : $('#endUseTime').val(),
        email : $('#email').val(),
        parkingName : $('#parkingName').val()
    }
    $.ajax({
       data : data,
       type : 'post',
       url : '/rest/book',
        success : function () {
            alert("예약 완료 되었습니다.");
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

// 결제
$('#pay').on('click', function () {
    var data = {
        parkingName : $('#parkingName').text(),
        price : $('#price').text(),
        email : $('#email').val(),
        phone : $('#phone').val(),
        userName : $('#name').val()
    }
    $.ajax({
       data : data,
       type : 'post',
       url : '/rest/pay',
       success : function () {

       }
    });

    var IMP = window.IMP; // 생략가능
    IMP.init('imp89563591'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    IMP.request_pay({
        pg : 'kakao', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : data.parkingName,
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

