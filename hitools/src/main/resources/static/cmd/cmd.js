$(function () {
    var username = 'admin';
    var path = '/';
    var result;

    var fn_exec = function (cmd) {
        $.post("/hitools/cmd/exec", {path: path, cmd: cmd}, function (r) {
            if (!r.success) {
                alert(r.msg);
                console.log(r)
            } else {
                result = r;
                fn_appendResult(cmd, r.data);
            }
        });
    };

    var renderLineItem = function (fileName, fileType, maxFileNameLength) {
        var fontColor = "#ffffff";
        if ('dir' === fileType) {
            fontColor = '#729fcf';
        } else if ('sh' === fileType) {
            fontColor = '#8ae234';
        }

        var lineItem = '<div style="color:{color};width:{width}px;">{fileName}</div>';
        return lineItem.replace("{color}", fontColor).replace("{fileName}", fileName).replace("{width}", maxFileNameLength * 10);
    };

    var getTips = function (inputFile, fileNameList) {
        var realInputFile = inputFile.replace('cd', '').trim();
        for (var i in fileNameList) {
            var fileName = fileNameList[i];
            var fileType = result['fileTypeMap'][fileName];
            console.log(fileName);
            console.log(fileType);
            if ('dir' === fileType) {
                if (fileName.startsWith(realInputFile)) {
                    return fileName;
                }
            }
        }
        return realInputFile;
    };

    var toBottom = function () {
        var containerObj = $('.container');
        var h = containerObj.prop('scrollHeight');
        containerObj.scrollTop(h);
    };

    var fn_appendResult = function (cmd, rs) {
        var d = '<div><div class="line">[<span style="color: #8ae234;">' + username + '</span><span style="color: #fce94f;">@</span><span style="color: #ad7fa8;">' + result.ip + '</span>&nbsp;&nbsp;&nbsp;<span>' + path + '</span>]</div>';

        //cmd
        d += '<div class="line"><span>$</span><span>' + cmd + '</span></div>';

        //result
        if (cmd === 'ls') {
            d += '<div class="line">';
            for (var j = 0, m = rs.length; j < m; j++) {
                var fileName = rs[j];
                d += renderLineItem(fileName, result['fileTypeMap'][fileName], result['maxFileNameLength']);
            }
            d += '</div>';
        } else {
            for (var i = 0, n = rs.length; i < n; i++) {
                d += '<div class="line"><span>' + rs[i] + '</span></div>';
            }
        }

        d += '</div>';
        $('#data').append(d);
        toBottom();
    };

    var inputObj = $('#input');
    inputObj.keydown(function (event) {
        var cmd = inputObj.val();
        if (event.keyCode === 13) {
            if (cmd.substr(0, 2) === "cd") {
                var dir = cmd.substr(3);

                if (dir.startsWith('/')) {
                    //cd到绝对路径
                    path = dir;
                } else if (dir === '..') {
                    //往上一级目录
                    path = path.substr(0, path.lastIndexOf('/'));
                    if ('' === path) {
                        path = '/';
                    }
                } else {
                    //相对目录
                    if (path === '/') {
                        path += dir;
                    } else {
                        path += '/' + dir;
                    }
                }
                fn_exec('ls');
            }
            else if (cmd === "clear") {
                //只是清理屏幕
                $('#data').html('');
            } else {
                fn_exec(cmd);
            }
            inputObj.val('');
        } else if (event.keyCode === 9 && cmd.startsWith('cd ')) {
            var fileName = getTips(cmd, result.data);
            inputObj.val('cd ' + fileName);
            return false;
        }
    });

    $('body').on('click', function () {
        $('#input').focus()
    });

    //进来就访问一次
    fn_exec('ls');
    inputObj.focus()
});