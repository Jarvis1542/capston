// 맴버 그래프
$(document).ready(function () {

    // 1~31일 저장 배열
    var date_days_arr = [];

    // 한 달의 모든 주간 주차별 배열
    var date_all_week_arr = [];

    // 한 주를 구해오는 배열
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
    // year_arr 은 2020, 2019, 218 이라 0이 3개다
    var year_arr = [ 0, 0, 0 ];
    var year_check_arr = [2020, 2019, 2018];
    // month_arr 은 1~12 비교 카운트 배열
    var month_arr = [];
    // month2_arr 은 1~31 비교 카운트 배열
    var month2_arr = [];
    var week_arr = [];

    // 1~12 를 비교하기 위한 배열 MonthStatistic에서 사용된다.
    var month_12_arr = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ];

    // month 월별 select box 가 바뀌었을 때
    $("#month").change(function() {
        year = $("#year").val();
        month = $("#month").val();
        if(year == '년') {
            alert("년도를 선택해 주시길 바랍니다.");
            return false;
        }
        if(month == '월') {
            return false;
        }
        if(month < 10) {
            month = "0" + month;
        }
        data = {
            DATE_SEND: year + month + "01"
        };
        // 1~31일 월 일자 구해주는 함수
        Date_Month();
        // 해당 월이 몇 주차인지 구해주는 함수
        Date_Week();

    });

    //
    $(".search_button").click(function() {
        year = $("#year").val();
        month = $("#month").val();
        if($("#year").val() != '년' && $("#month").val() != '월' && $("#week").val() != '주간') {
            week = $("#week").val();
            Date_One_Week(date_all_week_arr, week);
        } else if($("#year").val() != '년' && $("#month").val() != '월') {
            // 1~31일 비교함수
            Month2Statistics(month);
        } else if($("#year").val() != '년'){
            // 1~12월 비교 함수
            MonthStatistics(year);
        } else {
            // 2020, 2019, 2018 비교 함수
            YearStatistics();
        }
    });

    // 1~31일 월 일자 구해주는 함수
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
                date_all_week_arr[i] = date_new2;
            }
            // 태그 id 값 default-select3 안에 있는 클래스 .list 를 찾아서 li 태그를 찾아서 삭제
            $("#default-select3 .list").find("li").remove();

            // 태그 id 값 default-select3 안에 있는 .list 를 찾아서 li 태그를 추가
            $("#default-select3 .list").append('<li data-value="주간" class="option selected focus">주간</li>');
            for(var i = 0; i < date_all_week_arr.length; i++) {
                $("#default-select3 .list").append("<li data-value=" + (i+1) + " class='option'>" + (i+1) + "</li>");
            }

        }).fail(function (error) {
            console.log("에러 : ", error);
        });
    }

    // 특정한 주차의 한개의 주를 구해준다.
    function Date_One_Week(date_all_week_arr, week) {
        // date_all_week_arr은 Date_Week 함수에서 모든 주차를 구한 것을 가져와
        // week - 1 로 사용자가 정한 특정한 주간 주차를 가져와서 Date 객체로 변수에 넣은 다음
        date_new3 = new Date(date_all_week_arr[week-1]);
        // Date 변수 객체로 되어 있는 것의 Year, Month, Day를 합쳐서 data2 객체의 DATA_SEND 에 넣어서
        // ajax로 보낸다.
        data2.DATE_SEND = date_new3.getFullYear() + "" + ("0" + (date_new3.getMonth() + 1)).slice(-2) + "" + ("0" + date_new3.getDate()).slice(-2);
        $.ajax({
            method: "post",
            url: "/rest/DateOneWeek",
            data: data2
        }).done(function (msg) {
            // 한 주를 date_week_arr 배열에 넣는다.
            for(var i = 0; i < msg.length; i++) {
                date_new3 = new Date(msg[i].one_WEEK);
                date_week_arr[i] = date_new3;
            }
            WeekStatistics();
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
            console.log("msg : ", msg);
            for(var i = 0; i < msg.length; i++) {
                date_new4 = new Date(msg[i].created_DATE);
                created_date_arr[i] = date_new4;
            }
        }).fail(function (error) {
            console.log(error);
        });
    }

    // 2020, 2019, 2018 비교 함수
    function YearStatistics() {
        // MemberLoadData 함수에서 DB에서
        // 가져온 값 created_date_arr 로 년도를 비교한다.
        for(var i = 0; i < created_date_arr.length; i++) {
            if(created_date_arr[i].getFullYear() == 2020) {
                year_arr[0] += 1;
            } else if(created_date_arr[i].getFullYear() == 2019) {
                year_arr[1] += 1;
            } else if(created_date_arr[i].getFullYear() == 2018) {
                year_arr[1] += 2;
            }
        }

        YearChart(year_arr);
    }

    // 1~12월 비교 함수
    function MonthStatistics(year) {
        // 1~12월이기 때문에 카운트 할 배열을 0으로 12개 초기화 시킨다.
        for(var i = 0; i < month_12_arr.length; i++) {
            month_arr[i] = 0 ;
        }
        console.log("created_date_arr : ", created_date_arr);
        for(var i = 0; i < month_12_arr.length; i++) {
            for(var j = 0; j < created_date_arr.length; j++) {
                // DB에서 가져온 값과 배열로 선언한 1~12 중에 일치하면 카운트 시킨다.
                if (month_12_arr[i] == created_date_arr[j].getMonth()+1 && created_date_arr[j].getFullYear() == year) {
                    month_arr[i] += 1;
                }
            }
        }

        MonthChart(month_12_arr, month_arr);
    }

    // 1~31일 비교 함수
    function Month2Statistics(month) {

        // 31개의 배열로 초기화
        for(var i = 0; i < date_days_arr.length; i++) {
            month2_arr[i] = 0;
        }
        for(var i = 0; i < date_days_arr.length; i++) {
            for(var j = 0; j < created_date_arr.length; j++) {
                // 일자와 달이 같으면 카운트 시켜준다.
                if (i + 1 == created_date_arr[j].getDate() && created_date_arr[j].getMonth()+1 == month) {
                    month2_arr[i] += 1;
                }
            }
        }

        Month2Chart(date_days_arr, month2_arr);
    }

    //주간 주차 한 주 비교 함수
    function WeekStatistics() {
        for(var i = 0; i < date_week_arr.length; i++) {
            week_arr[i] = 0;
        }
        for(var i = 0; i < date_week_arr.length; i++) {
            for(var j = 0; j < created_date_arr.length; j++) {
                // Date_One_Week 함수에서 가져 온 date_week_arr 한 주에서 월과 일자가 같으면 카운트 시켜준다.
                if (date_week_arr[i].getDate() == created_date_arr[j].getDate() && created_date_arr[j].getMonth()+1 == date_week_arr[i].getMonth() + 1) {
                    week_arr[i] += 1;
                }

            }
        }

        WeekChart(date_week_arr, week_arr);
    }


    ChartReset();

    var ctx, chart;
    function ChartReset() {
        // 차트 삭제
        $("#myChart").remove();

        // 차트 다시 추가
        $(".chart1").append("<canvas id='myChart'></canvas>");

        ctx = document.getElementById('myChart').getContext('2d');
        chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'bar',

            // The data for our dataset
            data: {
                datasets: [{
                    label: '주간 가입자 통계',
                    backgroundColor: 'rgb(255,171,45)',
                    borderColor: 'rgb(64,64,64)'
                }]
            },

            // Configuration options go here
            options: {}
        });
    }

    function YearChart(year_arr) {
        ChartReset();
        chart.data.labels.push(2020);
        chart.data.labels.push(2019);
        chart.data.labels.push(2018);
        for(var i = 0; i < year_arr.length; i++) {
            chart.data.datasets[0].data.push(year_arr[i]);
        }
        chart.update();
    }

    function MonthChart(month_12_arr, month_arr) {
        ChartReset();
        for(var i = 0; i < month_12_arr.length; i++) {
            chart.data.labels.push(i+1);
            chart.data.datasets[0].data.push(month_arr[i]);
        }
        chart.update();

    }

    function Month2Chart(date_days_arr, month2_arr) {
        ChartReset();
        for(var i = 0; i < date_days_arr.length; i++) {
            chart.data.labels.push(i+1);
            chart.data.datasets[0].data.push(month2_arr[i]);
        }
        chart.update();
    }

    function WeekChart(date_week_arr, week_arr) {
        ChartReset();
        for(var i = 0; i < date_week_arr.length; i++) {
            chart.data.labels.push(date_week_arr[i].getDate());
            chart.data.datasets[0].data.push(week_arr[i]);
        }
        chart.update();
    }

});
