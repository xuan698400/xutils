/**
 * jQuery ajax btn
 * depends jquery kiss
 *
 *  操作菜单
 */(function ($) {
    $.fn.extend({
        ajaxbtn: function (url, data, pre, success, fail) {
            return this.each(function () {
                var _this = $(this);
                var txt = _this.val();
                _this.enabled = true;
                _this.on('click', function (e) {
                    if (pre && !pre()) {
                        return;
                    }
                    if (!_this.enabled) {
                        return;
                    }
                    _this.val('请稍后...');
                    _this.enabled = false;
                    var trueUrl = typeof url === 'function' ? url() : url;
                    $K.http(trueUrl, data(_this), function (r) {
                        _this.val(txt);
                        _this.enabled = true;
                        if (success) {
                            success(r)
                        } else {
                            $.zxxbox.remind("操作成功。", null, {
                                delay: 1000,
                                onclose: function () {
                                    location.reload();
                                }
                            });
                        }
                    }, function (r) {
                        _this.val(txt);
                        _this.enabled = true;
                        if (fail) {
                            fail(r)
                        }
                    })
                });

            });
        }
    });
})(jQuery);