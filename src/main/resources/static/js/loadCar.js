'use strict';

let totalMyCarData = [];
$('#loadMyCarModalBtn').on('click', function () {
    let data = {
        email : $('#email').val()
    }
    if($('#car0').length>0 || $('#car1').length>0 || $('#car2').length>0 ||
        $('#car3').length>0 || $('#car4').length>0 || $('#car5').length>0) {
        alert('차량 선택은 하나만 가능합니다.');
        return false;
    }
    $.ajax({
        data : data,
        url : '/rest/loadMyCar',
        type : 'post',
        success : function (data) {
            for(let i=0; data.length; i++){
                totalMyCarData.push(data[i]);
                console.log(data[i].car_id);
                if($('#my_car'+i).length==0){
                    $('#loadMyCarList').append(
                        '<li style="list-style-type : disc; text-indent: 3em;">' +
                        '<a id="my_car'+i+'" href="#'+i+'">'+data[i].car_id+'</a>' +
                        '</li><br>'
                    );
                    $('#hiddenCar').val(data[i].car_id);
                }
            }
        },
        error : function (error) {
            alert(JSON.stringify(error));
        }
    });
});

$(document).on('click', '#my_car0', function () {
    let html = "";
    html += '<br><button class="btn-primary" disabled>'+totalMyCarData[0].car_id+'</button>'+
        '<input type="hidden" id="car0" value="'+totalMyCarData[0].car_id+'">'
    $('#loadMyCar').append(html);
    $('#modalClose').click();
});
$(document).on('click', '#my_car1', function () {
    let html = "";
    html += '<br><button class="btn-primary" disabled>'+totalMyCarData[1].car_id+'</button>'+
        '<input type="hidden" id="car1" value="'+totalMyCarData[1].car_id+'">'
    $('#loadMyCar').append(html);
    $('#modalClose').click();
});
$(document).on('click', '#my_car2', function () {
    let html = "";
    html += '<br><button class="btn-primary" disabled>'+totalMyCarData[2].car_id+'</button>'+
        '<input type="hidden" id="car2" value="'+totalMyCarData[2].car_id+'">'
    $('#loadMyCar').append(html);
    $('#modalClose').click();
});
$(document).on('click', '#my_car3', function () {
    let html = "";
    html += '<br><button class="btn-primary" disabled>'+totalMyCarData[3].car_id+'</button>'+
        '<input type="hidden" id="car3" value="'+totalMyCarData[3].car_id+'">'
    $('#loadMyCar').append(html);
    $('#modalClose').click();
});
$(document).on('click', '#my_car4', function () {
    let html = "";
    html += '<br><button class="btn-primary" disabled>'+totalMyCarData[4].car_id+'</button>'+
        '<input type="hidden" id="car4" value="'+totalMyCarData[4].car_id+'">'
    $('#loadMyCar').append(html);
    $('#modalClose').click();
});
$(document).on('click', '#my_car5', function () {
    let html = "";
    html += '<br><button class="btn-primary" disabled>'+totalMyCarData[5].car_id+'</button>'+
        '<input type="hidden" id="car5" value="'+totalMyCarData[5].car_id+'">'
    $('#loadMyCar').append(html);
    $('#modalClose').click();
});