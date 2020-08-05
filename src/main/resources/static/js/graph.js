// 맴버 그래프
$(document).ready(function () {

    // 1~31일 저장 배열
    var date_days_arr = [];

    // 주간 주차별 배열
    var date_week_arr = [];

    // DB의 날짜 받아오는 배열
    var created_date_arr = [];


    // DB의 날짜 받아오는 함수 Ajax 실행
    // 위의 created_date_arr 배열을 아래의 함수에서 사용
    MemberLoadData();

    // 년 월 주간 입력 받음
    var year, month, week;

    // 입력 받은 데이터를 바탕으로 Date 객체 만들기
    // date_new 는 Date_Month 함수에서 사용.
    var date_new;
    // date_new2 는 Date_Week 에서 사용
    var date_new2;
    // date_new3 는 Date_One_Week 함수에서 사용
    var date_new3;
    // date_new4 는 MemberLoadData 에서 사용
    var date_new4;

    // Date_Month와 Date_Week 함수에서 사용하는 data 객체
    var data = {
        DATE_SEND: year + month + "01"
    };

    // Date_One_Week 함수에서 사용하는 data2 객체
    var data2 = {
        DATE_SEND: ""
    };

    // 년도, 월별, 주간 통계 배열
    var year_arr = [];
    var month_arr = [];
    var week_arr = [];

    // month 월별 select box 가 바뀌었을 때
    $("#month").change(function() {
        year = $("#year").val();
        month = $("#month").val();
        data = {
            DATE_SEND: year + month + "01"
        };
        Date_Month();
        Date_Week();
    });

    // 조회 버튼을 눌렀을 때
    $(".search_button").click(function() {
        week = $("#week").val();
        Date_One_Week(date_week_arr, week);
    })

    // 1~31 일 조회
    function Date_Month() {

        $.ajax({
            method: "post",
            url: "/rest/DateMonth",
            data: data
        }).done(function (msg) {
            // 배열에 1~31일과 주차별 날짜 입력
            for (var i = 0; i < msg.length; i++) {
                date_new = new Date(msg[i].days);
                date_days_arr[i] = date_new;
            }
        }).fail(function (error) {
            console.log("에러 : ", error);
        });
    }

    // 해당 월의 주간 주차를 모두 구해준다.
    function Date_Week() {
        $.ajax({
            method: "post",
            url: "/rest/DateWeek",
            data: data
        }).done(function (msg) {
            for(var i = 0; i < msg.length; i++) {
                date_new2 = new Date(msg[i].set_WEEKSTART);
                date_week_arr[i] = date_new2;
            }

        }).fail(function (error) {
            console.log("에러 : ", error);
        });
    }

    // 특정한 주차의 한개의 주를 구해준다.
    function Date_One_Week(date_week_arr, week) {
        date_new3 = new Date(date_week_arr[week]);
        data2.DATE_SEND = date_new3.getFullYear() + "" + ("0" + (date_new3.getMonth() + 1)).slice(-2) + "" + ("0" + date_new3.getDate()).slice(-2);
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

    // DB의 정보들을 가지고 온다.
    function MemberLoadData() {
        $.ajax({
            type: 'post',
            url: '/rest/MembersDate'
        }).done(function(msg){
            for(var i = 0; i < msg.length; i++) {
                date_new4 = new Date(msg[i].created_DATE);
                created_date_arr[i] = date_new4;
            }
        }).fail(function (error) {
            console.log(error);
        });
    }

    function YearStatistics() {
        /*
        // 1~31일 저장 배열
        var date_days_arr = [];

        // 주간 주차별 배열
        var date_week_arr = [];

        // DB의 날짜 받아오는 배열
        var created_date_arr = [];
        var year_arr = [];
        var month_arr = [];
        var week_arr = [];

         */
        for(var i = 0; i < created_date_arr.length; i++) {
            if(created_date_arr[i].getFullYear() == 2020) {
                year_arr[0] += 1;
            } else if(created_date_arr[i].getFullYear() == 2019) {
                year_arr[1] += 1;
            } else if(created_date_arr[i].getFullYear() == 2018) {
                year_arr[1] += 2;
            }

        }

    }

    function MonthStatistics() {
        for(var i = 0; i < date_days_arr.length; i++) {

        }
    }

    function WeekStatistics() {

    }




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
