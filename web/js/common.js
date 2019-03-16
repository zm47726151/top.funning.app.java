$(function () {
    Reminder.init();
});

let Common = {
    exit: function () {
        if (!window.confirm("确认退出吗?")) return;

        LoadingDialog.show();
        Web.request("M1020", {}, {
            onSuccess: function (rp) {
                window.location.href = "/login";
            },
            onError: function (rp) {
                alert(rp.msg);
                LoadingDialog.hide();
            }
        })
    }
}


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
    uploadImage: function (imageFile, name, upToken, callback) {
        let putExtra = {
            fname: imageFile["name"],
            params: {}
        };
        let config = {
            useCdnDomain: true
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
    getImageInfo: function (imageFile, listener) {
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
    },
    /*
    作者：laixiao_hero
    来源：CSDN
    原文：https://blog.csdn.net/laixiao_hero/article/details/78665592
    版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    validMoney: function (money) {
        let reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if (reg.test(money)) {
            return true;
        } else {
            return false;
        }
        ;
    }
}

let Reminder = {
    init: function () {
        let host;
        if (window.location.protocol == 'http:') {
            host = 'ws://' + window.location.host + '/admin/remind';
        } else {
            host = 'wss://' + window.location.host + '/admin/remind';
        }

        let socket;
        if ('WebSocket' in window) {
            socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            socket = new MozWebSocket(host);
        } else {
            Console.log('Error: WebSocket is not supported by this browser.');
            return;
        }
        socket.onopen = function () {
            console.log("WebSocket", "open");
        };
        socket.onclose = function () {
            console.log("WebSocket", "close");
        };
        socket.onmessage = function (message) {
            console.log("WebSocket", "message : " + message);
            Manager.remind(message)
        };
    },
}

let Manager = {
    remind: function (msg) {
        let m = document.getElementById('sound_remind');
        m.play();//播放
        Web.request("M1017", {}, {
            onSuccess: function (rp) {
                let value = rp.data.value;
                $("#red_point").html(value);
                value = Number(value);
                if (value < 1) {
                    $("#red_point").hide();
                } else {
                    $("#red_point").show();
                }
            },
            onError: function (rp) {

            }
        });
    }
}
