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

    var fn_appendResult = function (cmd, rs) {
        var d = '<div><div class="line">[<span style="color: #8ae234;">' + username + '</span><span style="color: #fce94f;">@</span><span style="color: #ad7fa8;">' + result.ip + '</span>&nbsp;&nbsp;&nbsp;<span>' + path + '</span>]</div>';

        //cmd
        d += '<div class="line"><span>$</span><span>' + cmd + '</span></div>';

        //result
        if (cmd === 'ls') {
            d += '<div class="line">';
            for (var j = 0, m = rs.length; j < m; j++) {
                var file = rs[j];
                d += '<span style="color: ' + result.file2ColorMap[file] + ';">' + rs[j] + '&nbsp;&nbsp;&nbsp;&nbsp;</span>';
            }
            d += '</div>';
        } else {
            for (var i = 0, n = rs.length; i < n; i++) {
                d += '<div class="line"><span>' + rs[i] + '</span></div>';
            }
        }

        d += '</div>';
        $('#data').append(d);
    };

    var inputObj = $('#input');
    inputObj.bind('keypress', function (event) {
        if (event.keyCode === 13) {
            var inputObj = $('#input');
            var cmd = inputObj.val();
            if (cmd.substr(0, 2) === "cd") {
                var dir = cmd.substr(3);

                if (dir.startsWith('/')) {
                    //cd到绝对路径
                    path = dir;
                } else if (dir === '..') {
                    //往上一级目录
                    path = path.substr(0, path.lastIndexOf('/'));
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
        }
    });

    $('body').on('click', function () {
        $('#input').focus()
    });

    //进来就访问一次
    fn_exec('ls');
    inputObj.focus()
});