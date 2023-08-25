$(function () {
    //dom对象
    var inputObj;
    var containerObj;
    var dataObj;

    //页面数据
    var path = '/';
    var execInfo;

    /**
     * 页面初始化
     */
    var init = function () {
        initDom();
        initInputEvent();
        initBodyEvent();
        //进入就ls访问一次，并对输入框进行聚焦
        fn_exec('ls');
        //并且聚焦输入框
        inputObj.focus()
    };

    /**
     * 页面节点初始化获取
     */
    var initDom = function () {
        inputObj = $('#input');
        containerObj = $('.container');
        dataObj = $('#data');
    };

    /**
     * 初始化body点击事件
     */
    var initBodyEvent = function () {
        $('body').on('click', function () {
            $('#input').focus()
        });
    };

    /**
     * 初始化输入框
     */
    var initInputEvent = function f() {
        inputObj = $('#input');
        inputObj.keydown(function (event) {
            var cmd = inputObj.val();

            // 13表示回车
            if (event.keyCode === 13) {
                //cd命令处理
                if (cmd.substr(0, 2) === "cd") {
                    var dir = cmd.substr(3);

                    //命令：cd /aaa/bbb 表示用户输入了绝对路径，直接把路径给到path
                    if (dir.startsWith('/')) {
                        path = dir;
                    }
                    //命令：cd .. 表示往上一级目录，从path中截取上一级
                    else if (dir === '..') {
                        path = path.substr(0, path.lastIndexOf('/'));
                        if ('' === path) {
                            path = '/';
                        }
                    }
                    //命令：cd aaa/bbb 表示相对路径拼接path
                    else {
                        //相对目录
                        if (path === '/') {
                            path += dir;
                        } else {
                            path += '/' + dir;
                        }
                    }
                    //执行ls展示目录文件
                    fn_exec('ls');
                }

                //只是清理屏幕
                else if (cmd === "clear") {
                    $('#data').html('');
                }

                //其他命令直接提交处理
                else {
                    fn_exec(cmd);
                }
                inputObj.val('');
            }

            //9表示tab键补全命令
            else if (event.keyCode === 9) {
                var cmd = getTips(cmd, result.resultList);
                inputObj.val(cmd);
                return false;
            }
        });
    };

    /**
     * 请求服务器执行命令，得到的结果存储到execInfo变量中
     *
     * @param cmd 命令
     */
    var fn_exec = function (cmd) {

        $.post("/tools/cmd/exec", {path: path, cmd: cmd}, function (r) {
            if (!r.success) {
                alert(r.msg);
            } else {
                execInfo = r.data;
                path = execInfo['path'];
                fn_appendResult(cmd, execInfo);
            }
        });
    };

    /**
     * 控制台渲染一行数据
     *
     * @param fileName 文件名
     * @param fileType 文件类型
     * @param maxFileNameLength 当前目录下文件名最长的长度
     * @returns {string}
     */
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


    /**
     * 补齐命令
     *
     * @param cmd 原始命令
     * @param fileNameList 当前目录文件名列表
     * @returns {*} 补齐后新命令
     */
    var getTips = function (cmd, fileNameList) {
        var part1 = cmd.substring(0, cmd.lastIndexOf(' '));
        var part2 = cmd.substring(cmd.lastIndexOf(' ') + 1);

        for (var i in fileNameList) {
            var fileName = fileNameList[i];
            var fileType = execInfo['fileTypeMap'][fileName];

            if ((cmd.startsWith('cd') && 'dir' === fileType) || !cmd.startsWith('cd')) {
                if (fileName.startsWith(part2)) {
                    return part1 + ' ' + fileName;
                }
            }
        }
        return cmd;
    };

    /**
     * 滚动到底部
     */
    var toBottom = function () {
        var containerObj = $('.container');
        var h = containerObj.prop('scrollHeight');
        containerObj.scrollTop(h);
    };

    /**
     * 添加返回信息到页面
     *
     * @param cmd 执行的命令
     * @param execInfo 返回对象数据
     */
    var fn_appendResult = function (cmd, execInfo) {
        var resultList = execInfo['resultList'];
        var maxFileNameLength = execInfo['maxFileNameLength'];
        var fileTypeMap = execInfo['fileTypeMap'];

        var d = '<div><div class="line">[<span style="color: #8ae234;">' + execInfo.userName + '</span><span style="color: #fce94f;">@</span><span style="color: #ad7fa8;">' + execInfo.ip + '</span>&nbsp;&nbsp;&nbsp;<span>' + path + '</span>]</div>';

        //cmd
        d += '<div class="line"><span>$</span><span>' + cmd + '</span></div>';

        //result
        if (cmd === 'ls') {
            d += '<div class="line">';
            for (var j = 0, m = resultList.length; j < m; j++) {
                var fileName = resultList[j];
                d += renderLineItem(fileName, fileTypeMap[fileName], maxFileNameLength);
            }
            d += '</div>';
        } else {
            for (var i = 0, n = resultList.length; i < n; i++) {
                d += '<div class="line"><span>' + resultList[i] + '</span></div>';
            }
        }

        d += '</div>';
        dataObj.append(d);
        toBottom();
    };

    init();
});