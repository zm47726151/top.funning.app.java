let Web = {
    request: function (cmd, data, listenet) {
        if (cmd == null) {
            alert("local error:cmd is empty")
            return;
        }
        let json = {
            type: "POST",
            url: "/admin/api",
            dataType: "Json",
            success: function (response) {
                console.log(response);
                if (response.code == "1") {
                    listenet.onSuccess(response);
                } else {
                    listenet.onError(response);
                }
            },
            error: function (xmlHttpRequest, errorMsg, exception) {
                console.log(response);
                let response = {
                    code: "L0000",
                    msg: errorMsg
                };
                listenet.onError(response);
            }
        }

        let postJson = {
            "cmd": cmd
        };
        if (data != null) {
            postJson.data = data;
        }
        json.data = JSON.stringify({
            "cmd": cmd,
            "data": data
        });
        $.ajax(json);
    }
}

let Qiniu = {
    uploadImage : function(imageFile, name, upToken, callback) {
        let putExtra = {
            fname : imageFile["name"],
            params : {}
        };
        let config = {
            useCdnDomain : true
        };
        let observable = qiniu.upload(imageFile, name, upToken, putExtra, config)
        let subscription = observable.subscribe(callback);
    }
};

let LoadingDialog = {
    show: function () {
        $("#LoadingDialog").modal('show');
    },
    hide: function () {
        $("#LoadingDialog").modal('hide');
        $("#LoadingDialog [class='lv_msg']").hide();
    },
    msg: function (msg) {
        $("#LoadingDialog [class='lv_msg']").show();
        $("#LoadingDialog [class='lv_msg']").html(msg);
    }
}

let Utils = {
    getSuffix: function (name) {
        let index = name.lastIndexOf(".");
        if (index < 0) {
            return;
        }

        let suffix = name.substring(index + 1);
        return suffix;
    },
    getImageInfo: function (imageFile,listener) {
        let reader = new FileReader();
        reader.onload = function (e) {
            let data = e.target.result;
            let image = new Image();
            image.onload = function () {
                listener(image.width, image.height);
            }
            image.src = data;
        }
        reader.readAsDataURL(imageFile);
    }
}
