'use strict';

// 거주지를 한꺼번에 등록할 배열
let csResultData = [];
$(document).ready(function () {
    $('input[type=radio][name=cable][value="DC차데몬"]:checked');
    $('input[type=radio][name=parkingType][value="공동 주택"]:checked');
    $('#loadResidence').on('click', function () {
        let data = {
            email : $('#email').val()
        }
        $.ajax({
            data : data,
            type : 'post',
            url : '/rest/loadResidence',
            success : function (data) {
                for(let i=0; i<=data.length; i++){
                    csResultData.push(data[i]);
                    if($('#res_name'+i+'').length==0){
                        $('#loadResidenceList').append(
                            '<li style="list-style-type : disc; text-indent: 3em;">' +
                            '<a id="res_name'+i+'" href="#'+i+'">'+data[i].res_name+'</a>' +
                            '</li><br>'
                        );
                    }else{
                        return;
                    }
                }
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    });
// 모달에 첫번째 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name0', function () {
        $('#res_name').val(csResultData[0].res_name);
        $('#post_code').val(csResultData[0].post_code);
        $('#road_addr').val(csResultData[0].road_addr);
        $('#jibun_addr').val(csResultData[0].jibun_addr);
        $('#extra_addr').val(csResultData[0].extra_addr);
        $('#detail_addr').val(csResultData[0].detail_addr);
        $('#modalClose').click();
    });
// 모달에 두번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name1', function () {
        $('#res_name').val(csResultData[1].res_name);
        $('#post_code').val(csResultData[1].post_code);
        $('#road_addr').val(csResultData[1].road_addr);
        $('#jibun_addr').val(csResultData[1].jibun_addr);
        $('#extra_addr').val(csResultData[1].extra_addr);
        $('#detail_addr').val(csResultData[1].detail_addr);
        $('#modalClose').click();
    });
// 모달에 세번쨰 주차장 이름 클릭하면 그 주차장 주소가 주소란에 들어감
    $(document).on('click', '#res_name2', function () {
        $('#res_name').val(csResultData[2].res_name);
        $('#post_code').val(csResultData[2].post_code);
        $('#road_addr').val(csResultData[2].road_addr);
        $('#jibun_addr').val(csResultData[2].jibun_addr);
        $('#extra_addr').val(csResultData[2].extra_addr);
        $('#detail_addr').val(csResultData[2].detail_addr);
        $('#modalClose').click();
    });
});