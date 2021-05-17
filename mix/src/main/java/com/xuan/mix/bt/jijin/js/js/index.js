//修改之后的加法
function Add(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    return (arg1 * m + arg2 * m) / m
}

//修改之后的减法
function Sub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2));
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

//修改之后的乘法
function Mul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length
    } catch (e) {
    }
    try {
        m += s2.split(".")[1].length
    } catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
}

//修改之后的除法
function Div(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length
    } catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length
    } catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""))
        r2 = Number(arg2.toString().replace(".", ""))
        return (r1 / r2) * pow(10, t2 - t1);
    }
}

var data = [
    20210223,
    0.30,
    20210222,
    2.94,
    20210219,
    0.17,
    20210218,
    0.67,
    20210210,
    2.05,
    20210209,
    2.10,
    20210208,
    1.41,
    20210205,
    0.16,
    20210204,
    0.19,
    20210203,
    0.27,
    20210202,
    1.43,
    20210201,
    1.16,
    20210129,
    0.46,
    20210128,
    2.57,
    20210127,
    0.26,
    20210126,
    1.92,
    20210125,
    0.96,
    20210122,
    0.11];

var data2 = [
    0.11, 0.96, -1.92, 0.26, -2.57, -0.46, 1.16, 1.43, -0.27, -0.19, 0.16, 1.41, 2.10, 2.05, -0.67, 0.17, -2.94, -0.30];

var dataX = [];
var dataY = [];
for (i in data) {
    if (i % 2) {
        dataX.push(data[i]);
    } else {
        dataY.push(data[i]);
    }
}


var fn_1 = function () {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    var option = {
        xAxis: {
            type: 'category',
            data: dataY
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: dataX,
            type: 'line'
        }]
    };

    myChart.setOption(option);
};

var fn_2 = function () {
    var lei_ji_fa_zhi = 2;
    var gu_ding_touru = 1000;
    var start_zj = 2000;
    var zhang_hu_ji_e = start_zj;

    var lei_ji_bili = 0;
    var touru = 0;
    var shouru = 0;

    for (i in data2) {
        var item = data2[i];

        //累计比例
        lei_ji_bili = Add(lei_ji_bili, item);
        //计算账户新值
        var rsjp = Div(item, 100);
        var rsj = Mul(zhang_hu_ji_e, rsjp);
        zhang_hu_ji_e = Add(zhang_hu_ji_e, rsj);

        //如果超过发值，减仓
        if (lei_ji_bili > lei_ji_fa_zhi) {
            zhang_hu_ji_e = Sub(zhang_hu_ji_e, gu_ding_touru);
            shouru = Add(shouru, gu_ding_touru);
            lei_ji_bili = 0;
            console.log("减仓" + gu_ding_touru);
        }

        //如果超过发值，加仓
        if (lei_ji_bili < -lei_ji_fa_zhi) {
            zhang_hu_ji_e = Add(zhang_hu_ji_e, gu_ding_touru);
            touru = Add(touru, gu_ding_touru);
            lei_ji_bili = 0;
            console.log("加仓" + gu_ding_touru);
        }

        console.log("lei_ji_bili:" + lei_ji_bili);
        console.log("touru:" + touru);
        console.log("shouru:" + shouru);
        console.log("zhang_hu_ji_e:" + zhang_hu_ji_e);
        console.log("-----------------------------------");
    }

    console.log("touru:" + touru);
    console.log("shouru:" + shouru);
    console.log("zhang_hu_ji_e:" + zhang_hu_ji_e);


    var start = Add(touru, start_zj);
    var end = Add(zhang_hu_ji_e, shouru);
    var sj = Sub(end, start);
    var sjp = Div(sj, start);
    console.log("收益:" + sjp);
};

fn_2();
fn_1();