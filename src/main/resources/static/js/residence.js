var totalData = [];

// 중복검사 버튼
$('#checkResidence').on('click', function () {
    if($('#resName').val()=='' || $('#resName').val()==null){
        var html = "";
        html += '<p style="font-size: 80%; ' +
            'color: red; text-indent: 5em;">' +
            '<strong>주차장 이름을 입력해주세요.</strong></p>';
        $('#resultChk').empty();
        return $('#resultChk').append(html);
    }

        if(totalData.length==0){
            var dataaa = {
                resName : $('#resName').val()
            }

            $.ajax({
                data : dataaa,
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
                if(totalData[i]==$('#resName').val()) {
                    var html = "";
                    html += '<p style="font-size: 80%; ' +
                        'color: red; text-indent: 5em;">' +
                        '<strong>중복되는 아파트 이름이 있습니다.&emsp14;&emsp14;' +
                        '다른 이름으로 바꿔주세요!</strong></p>';
                    $('#resultChk').empty();
                    return $('#resultChk').append(html);
                }
            }

                var dataa = {
                    resName : $('#resName').val()
                }

                $.ajax({
                    data : dataa,
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
            if(totalData[i]==$('#resName').val()){
                alert('중복되는 아파트 이름이 있습니다!\n' +
                    '아파트 이름을 바꿔주세요!');
                return;
            }
        }
    }

    totalData.push($('#resName').val());
    totalData.push($('#postcode').val());
    totalData.push($('#roadAddress').val());
    totalData.push($('#jibunAddress').val());
    totalData.push($('#extraAddress').val());
    totalData.push($('#detailAddress').val());
    totalData.push($('#lat').text());
    totalData.push($('#lng').text());
    totalData.push($('#email').val());

    // for(var i=1; i<=totalData.length; i++){
    //     console.log(totalData[i].toString());
    // }

    console.log("=====================================");

    // if(totalData.length==0){
    //     totalData.push(data);
    //     var showStoreRes = $('#showStoreRes');
    //     showStoreRes.append('<li style="list-style-type : disc;' +
    //         ' text-indent: 3em;">'+ $('#resName').val() + '</li>');
    //     return;
    // }

    var showStoreRes = $('#showStoreRes');
    showStoreRes.append('<li style="list-style-type : disc;' +
        ' text-indent: 3em;">'+ $('#resName').val() + '</li>');
    alert('상단에 아파트 이름이 등록되었습니다.');
});

// 거주지 등록 버튼
$('#residence').on('click', function () {
    var data = {
        totalData : totalData
    };
    for(var i=0; i<totalData.length; i++){
        console.log(totalData[i].toString());
    }
    $.ajax({
        type : 'post',
        url : '/rest/residence',
        data : data,
        dataType : 'json',
        contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
        success : function () {
            alert('등록 완료 되었습니다!');
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});