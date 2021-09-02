var date = ["2021-05-07", "2021-05-06", "2021-04-30", "2021-04-29", "2021-04-28", "2021-04-27", "2021-04-26", "2021-04-23", "2021-04-22", "2021-04-21", "2021-04-20", "2021-04-19", "2021-04-16", "2021-04-15", "2021-04-14", "2021-04-13", "2021-04-12", "2021-04-09", "2021-04-08", "2021-04-07"];

var price = [13897, 14068, 14233, 14340, 14220, 14144, 14107, 14257, 14133, 14148, 14107, 14116, 13799, 13755, 13837, 13731, 13752, 13981, 14183, 14159];

var ma5 = [13897, 13982, 14066, 14134, 14151, 14201, 14208, 14213, 14172, 14157, 14150, 14152, 14060, 13985, 13922, 13847, 13774, 13811, 13896, 13961];

var myChart = echarts.init(document.getElementById('main'));

var option = {
    title: {
        text: '折线图堆叠'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: date
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '价格',
            type: 'line',
            stack: '价格',
            data: price
        }, {
            name: 'MA5',
            type: 'line',
            stack: 'MA5',
            data: ma5
        }
    ]
};

myChart.setOption(option);