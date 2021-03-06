// 맴버 그래프
$(document).ready(function () {

    // 1~31일 저장 배열
    var date_days_arr = [];

    // 한 달의 모든 주간 주차별 배열
    var date_all_week1_arr = [];

    // 한 주를 구해오는 배열
    var date_week1_arr = [];

    // DB의 날짜 받아오는 배열
    var created_date_arr = [];


    // DB의 날짜 받아오는 함수 Ajax 실행
    // 위의 created_date_arr 배열을 아래의 함수에서 사용
    MemberLoadData();
    Member2LoadData();

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
    var year1_arr = [ 0, 0, 0 ], year2_arr = [ 0, 0, 0 ];
    // month1_1_arr 은 1~12 비교 카운트 배열, month1_2_arr 은 1~31 비교 카운트 배열
    var month1_1_arr = [], month1_2_arr = [], week1_arr = [];

    var month2_1_arr = [], month2_2_arr = [], week2_arr = [];

    // 1~12 를 비교하기 위한 배열 MonthStatistic에서 사용된다.
    var month_12_arr = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ];

    // 돈, 액수 배열
    var pay_arr = [], pay_arr2 = [];

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

    // 조회 버튼을 눌렀을 때
    $(".search_button").click(function() {
        year = $("#year").val();
        month = $("#month").val();
        week = $("#week").val();
        if($("#year").val() != '년' && $("#month").val() != '월' && $("#week").val() != '주간') {
            week = $("#week").val();
            Date_One_Week(date_all_week1_arr, week);
        } else if($("#year").val() != '년' && $("#month").val() != '월') {
            // 1~31일 비교함수
            Month2Statistics(month);
        } else if($("#year").val() != '년'){
            // 1~12월 비교 함수
            MonthStatistics();
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
                date_all_week1_arr[i] = date_new2;
            }
            // 태그 id 값 default-select3 안에 있는 클래스 .list 를 찾아서 li 태그를 찾아서 삭제
            $("#default-select3 .list").find("li").remove();

            // 태그 id 값 default-select3 안에 있는 .list 를 찾아서 li 태그를 추가
            $("#default-select3 .list").append('<li data-value="주간" class="option selected focus">주간</li>');
            for(var i = 0; i < date_all_week1_arr.length; i++) {
                $("#default-select3 .list").append("<li data-value=" + (i+1) + " class='option'>" + (i+1) + "</li>");
            }

        }).fail(function (error) {
            console.log("에러 : ", error);
        });
    }

    // 특정한 주차의 한개의 주를 구해준다.
    function Date_One_Week(date_all_week1_arr, week) {
        // date_all_week1_arr은 Date_Week 함수에서 모든 주차를 구한 것을 가져와
        // week - 1 로 사용자가 정한 특정한 주간 주차를 가져와서 Date 객체로 변수에 넣은 다음
        date_new3 = new Date(date_all_week1_arr[week-1]);
        // Date 변수 객체로 되어 있는 것의 Year, Month, Day를 합쳐서 data2 객체의 DATA_SEND 에 넣어서
        // ajax로 보낸다.
        data2.DATE_SEND = date_new3.getFullYear() + "" + ("0" + (date_new3.getMonth() + 1)).slice(-2) + "" + ("0" + date_new3.getDate()).slice(-2);
        $.ajax({
            method: "post",
            url: "/rest/DateOneWeek",
            data: data2
        }).done(function (msg) {
            // 한 주를 date_week1_arr 배열에 넣는다.
            for(var i = 0; i < msg.length; i++) {
                date_new3 = new Date(msg[i].one_WEEK);
                date_week1_arr[i] = date_new3;
            }
            WeekStatistics();
        }).fail(function (error) {
            console.log("에러 : ", error);
        });
    }

    function SalesPlus() {

    }

    // DB의 정보들을 가지고 온다.
    function MemberLoadData() {
        $.ajax({
            type: 'post',
            url: '/rest/Sales'
        }).done(function(msg){
            pay_arr = new Array(msg.length);
            for (var i = 0; i < pay_arr.length; i++) {
                pay_arr[i] = new Array(2);
            }
            for(var i = 0; i < msg.length; i++) {
                for(var j = 0; j < 2; j++) {
                    if(j == 0) {
                        pay_arr[i][j] = new Date(msg[i].pay_DATE);
                    } else if(j == 1) {
                        pay_arr[i][j] = msg[i].price;
                    }
                }
            }
            console.log("pay_arr : ", pay_arr);
        }).fail(function (error) {
            console.log(error);
        });
    }

    // DB의 정보들을 가지고 온다.
    function Member2LoadData() {
        $.ajax({
            type: 'post',
            url: '/rest/Sales2'
        }).done(function(msg){
            pay_arr2 = new Array(msg.length);
            for (var i = 0; i < pay_arr2.length; i++) {
                pay_arr2[i] = new Array(2);
            }
            for(var i = 0; i < msg.length; i++) {
                for(var j = 0; j < 2; j++) {
                    if(j == 0) {
                        pay_arr2[i][j] = new Date(msg[i].pay_DATE);
                    } else if(j == 1) {
                        pay_arr2[i][j] = msg[i].price;
                    }
                }
            }
            console.log("pay_arr2 : ", pay_arr2);
        }).fail(function (error) {
            console.log(error);
        });
    }

    // 2020, 2019, 2018 비교 함수
    function YearStatistics() {
        // MemberLoadData 함수에서 DB에서
        // 가져온 값 created_date_arr 로 년도를 비교한다.
        for(var i = 0; i < pay_arr.length; i++) {
            if(pay_arr[i][0].getFullYear() == 2020) {
                year1_arr[0] += parseInt(pay_arr[i][1], 10);
            } else if(pay_arr[i][0].getFullYear() == 2019) {
                year1_arr[1] += parseInt(pay_arr[i][1], 10);
            } else if(pay_arr[i][0].getFullYear() == 2018) {
                year1_arr[2] += parseInt(pay_arr[i][1], 10);
            }
        }

        for(var i = 0; i < pay_arr2.length; i++) {
            if(pay_arr2[i][0].getFullYear() == 2020) {
                year2_arr[0] += parseInt(pay_arr2[i][1], 10);
            } else if(pay_arr2[i][0].getFullYear() == 2019) {
                year2_arr[1] += parseInt(pay_arr2[i][1], 10);
            } else if(pay_arr2[i][0].getFullYear() == 2018) {
                year2_arr[2] += parseInt(pay_arr2[i][1], 10);
            }
        }
        YearChart(year1_arr, year2_arr);
    }

    // 1~12월 비교 함수
    function MonthStatistics() {
        // 1~12월이기 때문에 카운트 할 배열을 0으로 12개 초기화 시킨다.
        for(var i = 0; i < month_12_arr.length; i++) {
            month1_1_arr[i] = 0;
        }
        for(var i = 0; i < month_12_arr.length; i++) {
            month2_1_arr[i] = 0;
        }

        for(var i = 0; i < month_12_arr.length; i++) {
            for (var j = 0; j < pay_arr.length; j++) {
                if(pay_arr[j][0].getFullYear() == year && month_12_arr[i] == pay_arr[j][0].getMonth()+1) {
                    month1_1_arr[i] += parseInt(pay_arr[j][1], 10);
                }
            }
        }

        for(var i = 0; i < month_12_arr.length; i++) {
            for (var j = 0; j < pay_arr2.length; j++) {
                if(pay_arr2[j][0].getFullYear() == year && month_12_arr[i] == pay_arr2[j][0].getMonth()+1) {
                    month2_1_arr[i] += parseInt(pay_arr2[j][1], 10);
                }
            }
        }
        MonthChart(month_12_arr, month1_1_arr, month2_1_arr);
    }

    // 1~31일 비교 함수
    function Month2Statistics(month) {
        // 31개의 배열로 초기화
        for(var i = 0; i < date_days_arr.length; i++) {
            month1_2_arr[i] = 0;
        }
        for(var i = 0; i < date_days_arr.length; i++) {
            month2_2_arr[i] = 0;
        }
        for(var i = 0; i < date_days_arr.length; i++) {
            for(var j = 0; j < pay_arr.length; j++) {
                // 일자와 달이 같으면 카운트 시켜준다.
                if (i + 1 == pay_arr[j][0].getDate() && pay_arr[j][0].getFullYear() == year && pay_arr[j][0].getMonth()+1 == month) {
                    month1_2_arr[i] += parseInt(pay_arr[j][1], 10);
                }
            }
        }
        for(var i = 0; i < date_days_arr.length; i++) {
            for(var j = 0; j < pay_arr2.length; j++) {
                // 일자와 달이 같으면 카운트 시켜준다.
                if (i + 1 == pay_arr2[j][0].getDate() && pay_arr2[j][0].getFullYear() == year && pay_arr2[j][0].getMonth()+1 == month) {
                    month2_2_arr[i] += parseInt(pay_arr2[j][1], 10);
                }
            }
        }
        Month2Chart(date_days_arr, month1_2_arr, month2_2_arr);
    }

    //주간 주차 한 주 비교 함수
    function WeekStatistics() {
        for(var i = 0; i < date_week1_arr.length; i++) {
            week1_arr[i] = 0;
        }
        for(var i = 0; i < date_week1_arr.length; i++) {
            week2_arr[i] = 0;
        }
        for(var i = 0; i < date_week1_arr.length; i++) {
            for(var j = 0; j < pay_arr.length; j++) {
                // Date_One_Week 함수에서 가져 온 date_week1_arr 한 주에서 월과 일자가 같으면 카운트 시켜준다.
                if (date_week1_arr[i].getDate() == pay_arr[j][0].getDate() && pay_arr[j][0].getMonth()+1 == date_week1_arr[i].getMonth() + 1 &&
                    pay_arr[j][0].getFullYear() == year) {
                    week1_arr[i] += parseInt(pay_arr[j][1], 10);
                }
            }
        }

        for(var i = 0; i < date_week1_arr.length; i++) {
            for(var j = 0; j < pay_arr.length; j++) {
                // Date_One_Week 함수에서 가져 온 date_week1_arr 한 주에서 월과 일자가 같으면 카운트 시켜준다.
                if (date_week1_arr[i].getDate() == pay_arr2[j][0].getDate() && pay_arr2[j][0].getMonth()+1 == date_week1_arr[i].getMonth() + 1 &&
                    pay_arr2[j][0].getFullYear() == year) {
                    week2_arr[i] += parseInt(pay_arr2[j][1], 10);
                }
            }
        }

        WeekChart(date_week1_arr, week1_arr, week2_arr);
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
            type: 'line',

            // The data for our dataset
            data: {
                datasets: [{
                    label: '충전소 매출현황',
                    backgroundColor: 'rgba(0, 0, 0, 0.0)',
                    "lineTension": 0.1,
                    borderColor: 'rgb(13,224,200)'
                },{
                    label: '주차장 매출현황',
                    backgroundColor: 'rgba(0, 0, 0, 0.0)',
                    "lineTension": 0.1,
                    borderColor: 'rgb(77,142,255)'
                }]
            },

            // Configuration options go here
            options: {}
        });
    }

    function YearChart(year1_arr, year2_arr) {
        ChartReset();
        chart.data.labels.push(2020);
        chart.data.labels.push(2019);
        chart.data.labels.push(2018);
        for(var i = 0; i < year1_arr.length; i++) {
            chart.data.datasets[0].data.push(year1_arr[i]);
        }
        for(var i = 0; i < year2_arr.length; i++) {
            chart.data.datasets[1].data.push(year2_arr[i]);
        }
        chart.update();
    }

    function MonthChart(month_12_arr, month1_1_arr, month2_1_arr) {
        ChartReset();
        for(var i = 0; i < month_12_arr.length; i++) {
            chart.data.labels.push(i + 1);
        }
        for(var i = 0; i < month_12_arr.length; i++) {
            chart.data.datasets[0].data.push(month1_1_arr[i]);
        }
        for(var i = 0; i < month_12_arr.length; i++) {
            chart.data.datasets[1].data.push(month2_1_arr[i]);
        }
        chart.update();
    }

    function Month2Chart(date_days_arr, month1_2_arr, month2_2_arr) {
        ChartReset();
        for(var i = 0; i < date_days_arr.length; i++) {
            chart.data.labels.push(i+1);
        }
        for(var i = 0; i < date_days_arr.length; i++) {
            chart.data.datasets[0].data.push(month1_2_arr[i]);
        }
        for(var i = 0; i < date_days_arr.length; i++) {
            chart.data.datasets[1].data.push(month2_2_arr[i]);
        }
        chart.update();
    }

    function WeekChart(date_week1_arr, week1_arr, week2_arr) {
        ChartReset();
        for(var i = 0; i < date_week1_arr.length; i++) {
            chart.data.labels.push(date_week1_arr[i].getDate());
        }
        for(var i = 0; i < date_week1_arr.length; i++) {
            chart.data.datasets[0].data.push(week1_arr[i]);
        }
        for(var i = 0; i < date_week1_arr.length; i++) {
            chart.data.datasets[1].data.push(week2_arr[i]);
        }

        chart.update();
    }

});
