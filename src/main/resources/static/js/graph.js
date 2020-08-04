// 맴버 그래프
$(document).ready(function () {
    $("#Months").change(function() {

        // 1~31일 저장 배열
        var date_days_arr = [];

        // 주간 주차별 배열
        var date_week_arr = [];

        // 년 월 주간 입력 받음
        var year = $("#Years").val();
        var month = $("#Months").val();
        var week = $("#weeks").val();

        // 입력 받은 데이터를 바탕으로 Date 객체 만들기
        var date_new;
        //console.log(date_send);
        console.log(year + month);
        var data = {
            DATE_SEND: "20200101"
        };

        Date_Month();
        Date_Week();
        function Date_Month() {
            $.ajax({
                method: "post",
                url: "/rest/DateMonth",
                data: data
            }).done(function (msg) {
                // 배열에 1~31일과 주차별 날짜 입력
                for (var i = 0; i < msg.length; i++) {
                    date_days_arr[i] = msg[i].days;
                }
            }).fail(function (error) {
                console.log("에러 : ", error);
            });
        }

        var data2 = {
            DATE_SEND: ""
        };
        function Date_Week() {
            $.ajax({
                method: "post",
                url: "/rest/DateWeek",
                data: data
            }).done(function (msg) {
                for(var i = 0; i < msg.length; i++) {
                    date_week_arr[i] = msg[i].set_WEEKSTART;
                }
                Date_One_Week(date_week_arr, week);
            }).fail(function (error) {
                console.log("에러 : ", error);
            });
        }

        function Date_One_Week(date_week_arr, week) {
            date_new = new Date(date_week_arr[week]);
            data2.DATE_SEND = date_new.getFullYear() + "" + ("0" + (date_new.getMonth() + 1)).slice(-2) + "" + ("0" + date_new.getDate()).slice(-2);

            $.ajax({
                method: "post",
                url: "/rest/DateOneWeek",
                data: data2
            }).done(function (msg) {
                console.log("DateOneWeek : ", msg);
            }).fail(function (error) {
                console.log("에러 : ", error);
            });
        }



    });



/*

    let data = {
        year : $('#Years').val()
    };

    $.ajax({
        type: 'post',
        url: '/rest/MembersYears',
        data: data
    }).done(function(data){
        let ctx = document.getElementById('myChart').getContext('2d');
        let chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['2018', '2019','2020'],
                datasets: [{
                    label: '가입자 수',
                    backgroundColor: 'rgb(70,182,83)',
                    borderColor: 'rgb(127,127,127)',
                    data: [0,0,data]
                }]
            }
        });
    }).fail(function (error) {
        alert(error);
    });

 */
});

// test2
$(document).ready(function () {
    /*
    let datax = {
        month : $('#Months').val()
    };

    $.ajax({
        type: 'post',
        url: '/rest/MembersMonths',
        data: data
    }).done(function(data){
        let ctx = document.getElementById('myChart').getContext('2d');
        let chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['2018', '2019','2020'],
                datasets: [{
                    label: '가입자 수',
                    backgroundColor: 'rgb(70,182,83)',
                    borderColor: 'rgb(127,127,127)',
                    data: [0,0,data]
                }]
            }
        });
    }).fail(function (error) {
        alert(error);
    });

     */
});