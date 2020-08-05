'use strict';
// 비밀번호 수정하기 버튼
$('#updatePassword').click(function () {
    var data = {
        password : $('#password').val()
    };

    $.ajax({
        data : data,
        url : '/rest/updatePassword',
        type : 'post'
    }).done(function (data) {

    }).fail(function (error) {
        JSON.stringify(alert(error));
    });
});

$('select[name=select]').on('click', function () {
    var text = $('select[name=select] option[value="충전소"]').text();
    alert(text);
});
$('#parking').on('click', function () {
    var text = $('#parking').text();
    alert(text);
});

// select에서 충전소가 클릭되었을 때
// $('.selectpicker').selectpicker('val', '충전소').on('click', function () {
//
// });
//
// // select에서 주차장이 클릭되었을 때
// $('.selectpicker').selectpicker('val', '주차장').on('click', function () {
//     var email = $('#email').val();
//     window.location.href = '/myPage/hpBookmark/'+email;
//     let userHp = [];
//     let data = {
//         email : $('#email').val()
//     }
//     $.ajax({
//         data : data,
//         type : 'post',
//         url : '/rest/userHpBookmark',
//         success : function (data) {
//             for(let i=0; i<data.length; i++){
//                 userHp[i] = data[i];
//             }
//             userHp.
//         },
//         error : function (error) {
//             alert(JSON.stringify(error));
//         }
//     });
// });

// 즐겨찾기의 예약 버튼
$('#moveHpBook').on('click', function () {
    let parkingName = $('#parkingName').text();
    let email = $('#email').val()
    window.location.href = '/happyParking/happyParkingBook/'+parkingName+'+'+email;
})