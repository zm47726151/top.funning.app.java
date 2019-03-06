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

let LoadingDialog = {
    show: function () {
        $("#LoadingDialog").modal('show');
    },
    hide: function () {
        $("#LoadingDialog").modal('hide');
    },
    msg:function (msg) {
        $("#LoadingDialog [class='lv_msg']").html(msg);

    }
}
