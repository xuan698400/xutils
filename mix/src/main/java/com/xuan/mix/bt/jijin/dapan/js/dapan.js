$(function () {

    $.get('data/data.json', function (data) {
        var date = [];
        var price = [];

        for (i in data) {
            date.push(data[i].date);
            price.push(data[i].price);
        }

        var myChart = echarts.init(document.getElementById('item1'));
        // var option = {
        //     title: {
        //         text: '单价图'
        //     },
        //     tooltip: {
        //         trigger: 'axis'
        //     },
        //     legend: {
        //         data: []
        //     },
        //     grid: {
        //         left: '3%',
        //         right: '4%',
        //         bottom: '3%',
        //         containLabel: true
        //     },
        //     toolbox: {
        //         feature: {
        //             saveAsImage: {}
        //         }
        //     },
        //     xAxis: {
        //         type: 'category',
        //         boundaryGap: false,
        //         data: date
        //     },
        //     yAxis: {
        //         type: 'value'
        //     },
        //     series: [
        //         {
        //             name: '价格',
        //             type: 'line',
        //             stack: '价格',
        //             data: price
        //         }
        //     ]
        // };


        option = {
            //鼠标移动上去会展示值
            tooltip: {
                trigger: 'axis'
            },
            title: {
                text: '单价'
            },
            grid: {
                left: '1%',
                right: '1%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data: date
            },
            yAxis: {
                type: 'value',
                min: 1
            },
            series: [
                {
                    data: price,
                    type: 'line'
                }
            ]
        };

        myChart.setOption(option);
    });
});