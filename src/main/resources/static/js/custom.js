'use strict';
// 메인 index 슬라이드 시간 설정
$('.carousel').carousel({
    interval: 4000,
    pause: false // 활성화시 슬라이드에 마우스가 있더라도 슬라이드쇼 허용(비허용시 주석처리)
});

// 회원정보 수정 - 회원 구분에 따라 추가 양식 제공(memberEdit)
$('input[type=radio][name=memberRole]').on('click', function() {
    var chkValue = $('input[type=radio][name=memberRole]:checked').val();

    if (chkValue == 'normal') {
        $('#guardForm').css('display', 'none');
        $('#registerForm').css('display', 'none');
        $('#normalEdit').show();
        // return false;
    } else if (chkValue == 'guard') {
        $('#guardForm').css('display', 'block');
        $('#registerForm').css('display', 'none');
        $('#normalEdit').hide();
        // return false;
    } else if (chkValue == 'register') {
        $('#guardForm').css('display', 'none');
        $('#registerForm').css('display', 'block');
        $('#normalEdit').hide();
        // return false;
    }
});