BW = {}

BW.getWebsite = function () {
    return 'http://localhost:8080/';
    //return 'http://bpm.xuanner.com/';
}

BW.post = function (subUrl, params, success, fail, logoutFail) {
    var url = BW.getWebsite() + subUrl;
    var user = BW.getUser();
    if (null != user && user.logined) {
        BW.putCommonParams(params, user);
    }
    $.post(url, params, function (result) {
        if (!result.success && 'CHECK_LOGIN_ERROR' == result.code) {
            if (logoutFail) {
                logoutFail();
            } else {
                location.href = '/html/login.html?redirect=' + BW.encode(location.href);
            }
        } else {
            if (result.success) {
                if (success) {
                    success(result);
                }
            } else {
                if (fail) {
                    fail(result);
                } else {
                    layer.alert(result.msg, {
                        title: '提示'
                    })
                }
            }
        }
    });
}

BW.putCommonParams = function (data, user) {
    data.userId = user.userId;
    data.timestamp = new Date().getTime();
    //排序先
    var values = [];
    for (var key in data) {
        values.push(data[key]);
    }
    values.push(user.token);
    values.sort();
    //生成串
    var beforeMD5 = values.join('');
    //MD5
    var afterMD5 = $.md5(beforeMD5);
    data.signature = afterMD5;
}

//html
BW.saveUser = function (token, userId) {
    var user = {
        token: token,
        userId: userId,
        logined: true
    };
    BW.setCookie('user', BW.toJsonStr(user));
}
BW.clearUser = function () {
    var user = {
        token: '',
        passportId: '',
        logined: false
    };
    BW.setCookie('user', BW.toJsonStr(user));
}
BW.getUser = function () {
    var userStr = BW.getCookie('user');
    if (null == userStr) {
        return null;
    }
    return BW.toJsonObj(userStr);
}

//cookie
BW.setCookie = function (name, value) {
    var days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=/";
}

BW.getCookie = function (name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    }
    return null;
}
BW.delCookie = function (name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) {
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
}


//json
BW.toJsonStr = function (obj) {
    return JSON.stringify(obj);
}
BW.toJsonObj = function (str) {
    return JSON.parse(str);
}

//url
BW.getParamFromUrl = function (name, defaultStr) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    } else {
        if (defaultStr != undefined) {
            return defaultStr;
        } else {
            return '';
        }
    }
}
BW.encode = function (url) {
    return encodeURIComponent(url);
}
BW.decode = function (url) {
    return decodeURIComponent(url);
}
