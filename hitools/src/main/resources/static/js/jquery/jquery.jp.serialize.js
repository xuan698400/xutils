/**
 * jQuery serialize
 * depends jquery kiss
 *
 *  操作菜单
 */(function ($) {
    $.fn.extend({
        serialize: function () {
            var _this = $(this);
            var data = {};
            var texts = _this.find('input[type="text"],input[type="hidden"],textarea');
            var selects = _this.find('select');
            var redioes = _this.find('input[type="radio"]');
            texts.each(function (i, t) {
                var text = $(t);
                data[text.prop('name')] = text.val();
            })
            selects.each(function (i, s) {
                var select = $(s);
                data[select.prop('name')] = select.val();
            })
            redioes.each(function (i, r) {
                var rredioe = $(r);
                data[redio.prop('name')] = redio.is(':checked');
            })
            return data;
        }
    });
})(jQuery);