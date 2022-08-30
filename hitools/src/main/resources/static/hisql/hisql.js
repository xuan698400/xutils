$(function () {
    var m_tableObj = $('.tablelist');
    var m_dataSourceSelectObj = $('.select1');
    var m_sqlTextareaObj = $('#sqlTextarea');
    var m_btn = $('.btn');
    var m_running = false;

    var renderObj = function (template, obj, customRenderCallback) {
        var regex = /\{(.+?)\}/g;
        var vars = template.match(regex);
        if (vars) {
            for (var i = 0, n = vars.length; i < n; i++) {
                var attr = vars[i].replace('{', '').replace('}', '');
                var attrs = attr.split('.');

                var v = obj;
                for (var j = 0, m = attrs.length; j < m; j++) {
                    v = v[attrs[j]];
                }
                template = template.replace(vars[i], customRenderCallback ? customRenderCallback(vars[i], v) : v);
            }
        }
        return template;
    };

    var renderList = function (template, list, customRenderCallback) {
        if (!list) {
            return;
        }
        var result = '';
        for (var i = 0, n = list.length; i < n; i++) {
            result += renderObj(template, list[i], customRenderCallback);
        }
        return result;
    };


    $.get('/hitools/hisql/dataSources', function (r) {
        if (!r.success) {
            alert(r.msg);
        } else {
            for (var i in r.data) {
                var dataSource = r.data[i];
            }
            m_dataSourceSelectObj.append(renderObj('<option value="{code}">{name}</option>', dataSource));
            m_dataSourceSelectObj.uedSelect({width: 300});
            m_dataSourceSelectObj.find("option").eq(0).prop("selected", true);
        }
    });

    m_btn.on('click', function () {
        if (m_running) {
            return;
        }
        m_running = true;
        m_btn.val('运行中...');

        var code = m_dataSourceSelectObj.val();
        var sql = m_sqlTextareaObj.val();
        $.get('/hitools/hisql/exec', {code: code, sql: sql}, function (r) {
            if (!r.success) {
                alert(r.msg);
            } else {
                if (r.data.length > 0) {
                    //表头处理
                    var firstItem = r.data[0];
                    var theads = [];
                    for (var i in firstItem) {
                        theads.push({theadName: i});
                    }
                    m_tableObj.find('thead').html('');
                    m_tableObj.find('thead').append('<tr>' + renderList('<th>{theadName}</th>', theads) + '</tr>');
                    //数据处理
                    for (var j in r.data) {
                        var lineObj = r.data[j];
                        var tds;
                        for (var x in lineObj) {
                            tds += '<td>' + lineObj[x] + '</td>';
                        }
                        m_tableObj.find('tbody').html('');
                        m_tableObj.find('tbody').append('<tr>' + tds + '</tr>');
                    }
                    //表单美化
                    $('.tablelist tbody tr:odd').addClass('odd');
                }
            }
            m_running = false;
            m_btn.val('执行');
        });
    });
});