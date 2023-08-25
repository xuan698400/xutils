var mohoDragDivShow = function (divObj, ignoreDivObj) {

    //获取弹出框的宽度和整个窗口的宽度
    var divObjW = divObj.width();
    var windowW = $(window).width();

    var x1, x2, y1, y2;
    if (ignoreDivObj) {
        y1 = ignoreDivObj.offset().top;  //div上面两个的点的y值
        y2 = y1 + ignoreDivObj.height();//div下面两个点的y值
        x1 = ignoreDivObj.offset().left;  //div左边两个的点的x值
        x2 = x1 + ignoreDivObj.width();  //div右边两个点的x的值
    }


    //叠加样式
    divObj.css({
        'position': 'absolute',
        'cursor': 'move',
        'left': (windowW - divObjW) / 2 + 'px'
    });

    var dragging = false;
    var iX, iY;

    divObj.mousedown(function (e) {
        dragging = true;

        if (ignoreDivObj) {
            if ($.contains(ignoreDivObj[0], e.target)) {
                dragging = false;
            }
        }

        iX = e.clientX - this.offsetLeft;
        iY = e.clientY - this.offsetTop;
        //this.setCapture && this.setCapture();
        return true;
    });

    document.onmousemove = function (event) {
        if (dragging) {
            var e = event || window.event;
            var oX = e.clientX - iX;
            var oY = e.clientY - iY;
            divObj.css({
                "left": oX + "px",
                "top": oY + "px"
            });
            return false;
        }
    };

    $(document).mouseup(function (e) {
        dragging = false;

        // divObj[0].releaseCapture && divObj[0].releaseCapture();
        // e.cancelBubble = true;
    })
};

var mohoDialogOptions = {
    top: '100px',
    width: '400px',
    title: '提示',
    btnTexts: ['确定'],
    btnClick: function (index, divObjs) {
        divObjs.mohoDialogDivObj.remove();
    }
};

var mohoDialogShow = function (contentObj, inputOptions) {
    //init options
    var options = JSON.parse(JSON.stringify(mohoDialogOptions));
    options.btnClick = mohoDialogOptions.btnClick;

    if (inputOptions) {
        if (inputOptions.top) {
            options.top = inputOptions.top;
        }
        if (inputOptions.width) {
            options.width = inputOptions.width;
        }
        if (inputOptions.title) {
            options.title = inputOptions.title;
        }
        if (inputOptions.btnTexts) {
            options.btnTexts = inputOptions.btnTexts;
        }
        if (inputOptions.btnClick) {
            options.btnClick = inputOptions.btnClick;
        }
    }

    //dialog
    var mohoDialogDivObj = $('<div></div>');
    mohoDialogDivObj.css('width', options.width);
    mohoDialogDivObj.css('-webkit-box-shadow', '0 12px 20px #d8e0e6');
    mohoDialogDivObj.css('box-shadow', '0 12px 20px #d8e0e6');
    mohoDialogDivObj.css('display', 'flex');
    mohoDialogDivObj.css('flex-direction', 'column');
    mohoDialogDivObj.css('justify-content', 'space-between');
    mohoDialogDivObj.css('top', options.top);

    // dialog - header
    var mohoDialogHeaderObj = $('<div></div>');
    mohoDialogHeaderObj.css('background-color', '#1E9FFF');
    mohoDialogHeaderObj.css('height', '40px');
    mohoDialogHeaderObj.css('display', 'flex');
    mohoDialogHeaderObj.css('justify-content', 'space-between');
    mohoDialogHeaderObj.css('align-items', 'center');
    mohoDialogHeaderObj.css('padding', '0 10px');
    mohoDialogHeaderObj.css('color', 'white');
    mohoDialogDivObj.append(mohoDialogHeaderObj);

    //dialog - header - title
    var mohoDialogHeaderTitleObj = $('<p>' + options.title + '</p>');
    mohoDialogHeaderObj.append(mohoDialogHeaderTitleObj);

    //dialog - header - close
    var mohoDialogHeaderCloseBtnObj = $('<p>关闭</p>');
    mohoDialogHeaderCloseBtnObj.css('cursor', 'pointer');
    mohoDialogHeaderCloseBtnObj.hover(function () {
        $(this).css('color', '#F00');
    }, function () {
        $(this).css('color', 'white');
    });
    mohoDialogHeaderCloseBtnObj.on('click', function () {
        mohoDialogDivObj.remove();
    });
    mohoDialogHeaderObj.append(mohoDialogHeaderCloseBtnObj);

    //dialog - body
    var mohoDialogBodyObj = $('<div></div>');
    mohoDialogBodyObj.css('display', 'flex');
    mohoDialogBodyObj.css('justify-content', 'center');
    mohoDialogBodyObj.css('align-items', 'center');
    mohoDialogBodyObj.css('height', '100%');
    mohoDialogBodyObj.css('width', '100%');
    mohoDialogDivObj.append(mohoDialogBodyObj);

    //dialog - body -content
    mohoDialogBodyObj.append(contentObj);

    //dialog - footer
    var mohoDialogFooterObj = $('<div></div>');
    mohoDialogFooterObj.css('height', '50px');
    mohoDialogFooterObj.css('display', 'flex');
    mohoDialogFooterObj.css('justify-content', 'center');
    mohoDialogFooterObj.css('align-items', 'center');
    mohoDialogFooterObj.css('background-color', '#F3F3F3');
    mohoDialogDivObj.append(mohoDialogFooterObj);

    //dialog - footer - btns
    for (i in options.btnTexts) {
        var mohoDialogFooterBtnObj = $('<div>' + options.btnTexts[i] + '</div>');
        mohoDialogFooterBtnObj.css('width', '100px');
        mohoDialogFooterBtnObj.css('height', '35px');
        mohoDialogFooterBtnObj.css('background-color', '#1E9FFF');
        mohoDialogFooterBtnObj.css('display', 'flex');
        mohoDialogFooterBtnObj.css('justify-content', 'center');
        mohoDialogFooterBtnObj.css('align-items', 'center');
        mohoDialogFooterBtnObj.css('color', 'white');
        mohoDialogFooterBtnObj.css('border-radius', '5px');
        mohoDialogFooterBtnObj.css('cursor', 'pointer');
        mohoDialogFooterBtnObj.css('margin', '0 10px');
        mohoDialogFooterBtnObj.data('index', i);
        mohoDialogFooterBtnObj.hover(function () {
            $(this).css('background-color', '#1E82FF');
        }, function () {
            $(this).css('background-color', '#1E9FFF');
        });
        mohoDialogFooterBtnObj.on('click', function () {
            if (options.btnClick) {
                options.btnClick($(this).data('index'), {
                    mohoDialogDivObj: mohoDialogDivObj,
                    mohoDialogBodyObj: mohoDialogBodyObj
                });
            }
        });
        mohoDialogFooterObj.append(mohoDialogFooterBtnObj);
    }

    $('body').append(mohoDialogDivObj);
    mohoDragDivShow(mohoDialogDivObj, mohoDialogBodyObj);
};

/**
 * 通知弹窗
 *
 * @param message
 */
var mohoAlert = function (message) {
    var alertContentObj = $('<div>' + message + '</div>');
    alertContentObj.css('background-color', 'white');
    alertContentObj.css('width', '100%');
    alertContentObj.css('height', '100%');
    alertContentObj.css('display', 'flex');
    alertContentObj.css('justify-content', 'center');
    alertContentObj.css('align-items', 'center');
    alertContentObj.css('word-wrap', 'break-word');
    alertContentObj.css('padding', '30px 10px');

    var alertConfig = {
        width: '300px'
    };
    mohoDialogShow(alertContentObj, alertConfig);
};

/**
 * 输入文本弹窗
 *
 * @param message
 * @param okCallback
 * @param oldInputVal
 */
var mohoPrompt = function (message, okCallback, oldInputVal) {
    var promptContentObj = $('<div></div>');
    promptContentObj.css('background-color', 'white');
    promptContentObj.css('width', '100%');
    promptContentObj.css('height', '100%');
    promptContentObj.css('display', 'flex');
    promptContentObj.css('justify-content', 'center');
    promptContentObj.css('align-items', 'center');
    promptContentObj.css('word-wrap', 'break-word');
    promptContentObj.css('padding', '30px 10px');

    var messageObj = $('<span style="font-size: 12px;">' + message + '</span>');
    promptContentObj.append(messageObj);

    var inputObj = $('<input type="text" style="width: 180px;height: 35px;border: 1px #E0E0E0 solid;margin-left: 10px;padding: 0 10px;" />');
    if (oldInputVal) {
        inputObj.val(oldInputVal);
    }
    promptContentObj.append(inputObj);

    var promptConfig = {
        width: '300px',
        btnClick: function (index, divObjs) {
            divObjs.mohoDialogDivObj.remove();
            if (okCallback) {
                okCallback(inputObj.val());
            }
        }
    };
    mohoDialogShow(promptContentObj, promptConfig);
};

/**
 * 二次确认弹窗
 *
 * @param message
 * @param okCallback
 * @param cancelCallback
 */
var mohoConfirm = function (message, okCallback, cancelCallback) {
    var confirmContentObj = $('<div>' + message + '</div>');
    confirmContentObj.css('background-color', 'white');
    confirmContentObj.css('width', '100%');
    confirmContentObj.css('height', '100%');
    confirmContentObj.css('display', 'flex');
    confirmContentObj.css('justify-content', 'center');
    confirmContentObj.css('align-items', 'center');
    confirmContentObj.css('word-wrap', 'break-word');
    confirmContentObj.css('padding', '30px 10px');

    var confirmConfig = {
        width: '300px',
        btnTexts: ['确定', '取消'],
        btnClick: function (index, divObjs) {
            console.log(index)
            divObjs.mohoDialogDivObj.remove();

            if (index === '0') {
                if (okCallback) {
                    okCallback();
                }
            } else if (index === '1') {
                if (cancelCallback) {
                    cancelCallback();
                }
            }
        }
    };
    mohoDialogShow(confirmContentObj, confirmConfig);
};

/**
 * 文本编辑弹窗
 *
 * @param oldVal
 * @param okCallback
 */
var mohoEditor = function (oldVal, okCallback) {
    var editorContentObj = $('<div></div>');
    editorContentObj.css('background-color', 'white');
    editorContentObj.css('width', '100%');
    editorContentObj.css('height', '100%');
    editorContentObj.css('display', 'flex');
    editorContentObj.css('justify-content', 'center');
    editorContentObj.css('align-items', 'center');
    editorContentObj.css('word-wrap', 'break-word');
    editorContentObj.css('padding', '5px 5px');

    var textareaObj = $('<textarea type="text" style="width: 100%;height: 450px;border: 1px #E0E0E0 solid;margin-left: 10px;padding: 0 10px;" />');
    if (oldVal) {
        textareaObj.val(oldVal);
    }
    editorContentObj.append(textareaObj);

    var editorConfig = {
        top: '10px',
        width: '800px',
        btnClick: function (index, divObjs) {
            divObjs.mohoDialogDivObj.remove();
            if (okCallback) {
                okCallback(textareaObj.val());
            }
        }
    };
    mohoDialogShow(editorContentObj, editorConfig);
};

/**
 * 上传文件弹窗
 *
 * @param callback
 */
var mohoUpload = function (callback) {
    var uploadContentObj = $('<div></div>');
    uploadContentObj.css('background-color', 'white');
    uploadContentObj.css('width', '100%');
    uploadContentObj.css('height', '100%');
    uploadContentObj.css('display', 'flex');
    uploadContentObj.css('justify-content', 'center');
    uploadContentObj.css('align-items', 'center');
    uploadContentObj.css('word-wrap', 'break-word');
    uploadContentObj.css('padding', '20px');

    var fileUploadInputObj = $('<input type="file" id="fileUploadInput"/>');
    uploadContentObj.append(fileUploadInputObj);

    var uploadConfig = {
        title: '文件上传',
        top: '10px',
        width: '300px',
        btnClick: function (index, divObjs) {
            divObjs.mohoDialogDivObj.remove();
            if (callback) {
                callback(fileUploadInputObj);
            }
        }
    };
    mohoDialogShow(uploadContentObj, uploadConfig);
};


