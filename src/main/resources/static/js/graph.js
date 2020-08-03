// 맴버 그래프
$(document).ready(function () {
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
});

// test2
$(document).ready(function () {
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
});