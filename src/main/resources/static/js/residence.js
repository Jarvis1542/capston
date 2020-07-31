var totalData = [];

// 거주지 등록 버튼
$('#residence').on('click', function () {
    var data = {
        postcode : $('#postcode').val(),
        roadAddress : $('#roadAddress').val(),
        jibunAddress : $('#jibunAddress').val(),
        extraAddress : $('#extraAddress').val(),
        detailAddress : $('#detailAddress').val(),
        lat : $('#lat').text(),
        lng : $('#lng').text()
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
    for(var i=0; i<totalData.length; i++){
        if($('#resName').val()==totalData[i].resName){
            var html = "";
            html += '<p style="font-size: 80%; ' +
                'color: red; text-indent: 5em;">' +
                '<strong>중복되는 아파트 이름이 있습니다.&emsp14;&emsp14;' +
                '다른 이름으로 바꿔주세요!</strong></p>';
            $('#resultChk').empty();
            return $('#resultChk').append(html);
        }
    }

    var data = {
        resName : $('#resName').val()
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
                    'color: red; text-indent: 3em;">' +
                    '<strong>이미 사용중인 이름입니다.</strong></p>';
                $('#resultChk').empty();
                return $('#resultChk').append(html);
            }
        },
        error : function (error) {
            JSON.stringify(alert(error));
        }
    });
});

// 등록 버튼
$('#firstReg').on('click', function () {
    var resName = $('#resName').val();
    var data = {
        resName : $('#resName').val(),
        postcode : $('#postcode').val(),
        roadAddress : $('#roadAddress').val(),
        jibunAddress : $('#jibunAddress').val(),
        extraAddress : $('#extraAddress').val(),
        detailAddress : $('#detailAddress').val(),
        lat : $('#lat').text(),
        lng : $('#lng').text()
    }
    console.log("resName : " + data.resName);
    console.log("postcode : " + data.postcode);
    console.log("roadAddress : " + data.roadAddress);
    console.log("jibunAddress : " + data.jibunAddress);
    console.log("extraAddress : " + data.extraAddress);
    console.log("detailAddress : " + data.detailAddress);
    console.log("lat : " + data.lat);
    console.log("lng : " + data.lng);
    console.log("=====================================");

    if(totalData.length==0){
        totalData.push(data);
        var showStoreRes = $('#showStoreRes');
        showStoreRes.append('<li style="list-style-type : disc;' +
            ' text-indent: 3em;">'+ resName + '</li>');
        return;
    }

    for(var i=0; i<totalData.length; i++){
        if(totalData[i].resName==resName){
            alert('중복되는 아파트 이름이 있습니다!\n' +
                '아파트 이름을 바꿔주세요!');
            return;
        }
    }
    totalData.push(data);

    var showStoreRes = $('#showStoreRes');
    showStoreRes.append('<li style="list-style-type : disc;' +
        ' text-indent: 3em;">'+ resName + '</li>');
});