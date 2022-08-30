$(function () {
    window.$K = {
        //ajax简单封装
        http: function (url, data, success, fail) {
            $.post(url, data, function (r) {
                if (r.code != 1) {
                    if (r.message) {
                        $.zxxbox.remind(r.message);
                    } else {
                        $.zxxbox.remind('登录超时，请重新登录', function () {
                            top.location.href = location.href;
                        });
                    }
                    if (fail) {
                        fail(r);
                    }
                    return;
                }
                if (success) {
                    success(r);
                }
            }).error(function (r) {
                $.zxxbox.remind("操作失败。", null, {delay: 1000});
                if (fail) {
                    fail(r);
                }
            });
        },
        tools: {
            // 是否为空
            isBlank: function (val) {
                if (!val) {
                    return true;
                }
                var re = /^\s*$/g;
                return re.test(val);
            },
            timeFormat: function (time, format) {
                Date.prototype.format = function (format) {
                    var o = {
                        "M+": this.getMonth() + 1, // month
                        "d+": this.getDate(), // day
                        "h+": this.getHours(), // hour
                        "m+": this.getMinutes(), // minute
                        "s+": this.getSeconds(), // second
                        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
                        "S": this.getMilliseconds()
                    };
                    if (/(y+)/.test(format)) {
                        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                    }
                    for (var k in o)
                        if (new RegExp("(" + k + ")").test(format)) {
                            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                        }
                    return format;
                };
                if (typeof time == 'number') {
                    time = new Date(time);
                }
                var _time = time || new Date();
                var datatime = _time.format(format || 'yyyy-MM-dd hh:mm:ss');
                return datatime;
            }
        }
    }
});