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

