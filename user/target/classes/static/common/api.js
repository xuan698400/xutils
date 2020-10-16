(function ($) {
    $.extend({
        apiCreateFile: function (name, type, parentId, callback) {
            var params = {
                name: name,
                type: type,
                parentId: parentId
            };
            BW.post("file/createFile", params, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiGetFiles: function (parentId, callback) {
            var params = {
                parentId: parentId
            };
            BW.post("file/getFiles", params, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiGetParentFiles: function (id, callback) {
            var params = {
                id: id
            };
            BW.post("file/getParentFiles", params, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiGetFileContent: function (fileId, callback) {
            BW.post("api/file/getFileContent", {fileId: fileId}, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiUpdateContent: function (fileId, content, callback) {
            BW.post("api/file/updateContent", {fileId: fileId, content: content}, function (result) {
                if (callback) {
                    callback(result.data);
                }
            }, function (result) {
                console.log(result);
            });
        },
        apiDeleteFile: function (fileId, callback) {
            BW.post("api/file/deleteFile", {fileId: fileId}, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiUpdateName: function (fileId, name, callback) {
            BW.post("api/file/updateName", {fileId: fileId, name: name}, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiGetUser: function (callback) {
            BW.post("user/getUser", {}, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        },
        apiUserRegister: function (email, password, callback) {
            BW.post("api/user/register", {email: email, password: password}, function (result) {
                if (callback) {
                    callback(result.data);
                }
            });
        }
    });
})(jQuery);