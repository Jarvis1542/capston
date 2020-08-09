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
