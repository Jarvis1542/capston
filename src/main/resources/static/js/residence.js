'use strict';
var totalData = [];

// 중복검사 버튼
$('#checkResidence').on('click', function () {
    if($('#res_name').val()=='' || $('#res_name').val()==null){
        var html = "";
        html += '<p style="font-size: 80%; ' +
            'color: red; text-indent: 5em;">' +
            '<strong>주차장 이름을 입력해주세요.</strong></p>';
        $('#resultChk').empty();
        return $('#resultChk').append(html);
    }

        if(totalData.length==0){
            let data = {
                res_name : $('#res_name').val()
            }

            $.ajax({
                data : data,
                url : '/rest/checkResidence',
                type : 'post',
                success : function (re) {
                    var html = '';
                    if(re==0){
                        html += '<p style="font-size: 80%; ' +
                            'color: green; text-indent: 5em;">' +
                            '<strong>사용 가능한 이름입니다.</strong></p>';
                        $('#resultChk').empty();
                        return $('#resultChk').append(html);
                    }else{
                        html += '<p style="font-size: 80%; ' +
                            'color: red; text-indent: 5em;">' +
                            '<strong>이미 사용중인 이름입니다.</strong></p>';
                        $('#resultChk').empty();
                        return $('#resultChk').append(html);
                    }
                },
                error : function (error) {
                    JSON.stringify(alert(error));
                }
            });
            return;
        } // end of totalData.length == 0

        if(totalData.length>0){
            for(var i=0; i<totalData.length; i++){
                if(totalData[i]==$('#res_name').val()) {
                    var html = "";
                    html += '<p style="font-size: 80%; ' +
                        'color: red; text-indent: 5em;">' +
                        '<strong>중복되는 아파트 이름이 있습니다.&emsp14;&emsp14;' +
                        '다른 이름으로 바꿔주세요!</strong></p>';
                    $('#resultChk').empty();
                    return $('#resultChk').append(html);
                }
            }

                let data = {
                    res_name : $('#res_name').val()
                }

                $.ajax({
                    data : data,
                    url : '/rest/checkResidence',
                    type : 'post',
                    success : function (re) {
                        var html = '';
                        if(re==0){
                            html += '<p style="font-size: 80%; ' +
                                'color: green; text-indent: 5em;">' +
                                '<strong>사용 가능한 이름입니다.</strong></p>';
                            $('#resultChk').empty();
                            return $('#resultChk').append(html);
                        }else{
                            html += '<p style="font-size: 80%; ' +
                                'color: red; text-indent: 5em;">' +
                                '<strong>이미 사용중인 이름입니다.</strong></p>';
                            $('#resultChk').empty();
                            return $('#resultChk').append(html);
                        }
                    },
                    error : function (error) {
                        JSON.stringify(alert(error));
                    }
                });

        }
});

// 등록 버튼
$('#firstReg').on('click', function () {
    if(totalData.length>0){
        for(var i=0; i<=totalData.length; i++){
            if(totalData[i]==$('#res_name').val()){
                alert('중복되는 아파트 이름이 있습니다!\n' +
                    '아파트 이름을 바꿔주세요!');
                return;
            }
        }
    }

    totalData.push($('#res_name').val());
    totalData.push($('#post_code').val());
    totalData.push($('#road_addr').val());
    totalData.push($('#jibun_addr').val());
    totalData.push($('#extra_addr').val());
    totalData.push($('#detail_addr').val());
    totalData.push($('#lat').text());
    totalData.push($('#lng').text());
    totalData.push($('#email').val());

    var showStoreRes = $('#showStoreRes');
    showStoreRes.append('<li style="list-style-type : disc;' +
        ' text-indent: 3em;">'+ $('#res_name').val() + '</li>');
    alert('상단에 아파트 이름이 등록되었습니다.');
});

// 거주지 등록 버튼
$('#residence').on('click', function () {
    var data = {
        totalData : totalData
    };
    for(let i=0; i<totalData.length; i++){
        console.log(totalData[i].toString());
    }
    $.ajax({
        type : 'post',
        url : '/rest/residence',
        data : data,
        success : function () {
            alert('거주지 등록 완료 되었습니다!');
            window.location.href = '/';
        },
    });
});