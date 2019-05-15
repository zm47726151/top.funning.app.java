
let Page = {
    onLoad: function (id) {

    },
    finish: function (id) {
        if (window.confirm('你确定吗？')) {
            LoadingDialog.show()
            Web.request("M1028", {
                id: id
            }, {
                onSuccess: function (res) {
                    LoadingDialog.hide();
                    window.location.reload();
                },
                onError: function (res) {
                    LoadingDialog.hide();
                    alert(res.msg);
                }
            });
        }
    },
    refund: function (id) {
        if (window.confirm('你确定吗？')) {
            LoadingDialog.show()
            Web.request("M1029", {
                id: id
            }, {
                onSuccess: function (res) {
                    LoadingDialog.hide();
                    window.location.reload();
                },
                onError: function (res) {
                    LoadingDialog.hide();
                    alert(res.msg);
                }
            });
        }
    },
    cancel: function (id) {
        if (window.confirm('你确定吗？')) {
            LoadingDialog.show()
            Web.request("M1027", {
                id: id
            }, {
                onSuccess: function (res) {
                    LoadingDialog.hide();
                    window.location.reload();
                },
                onError: function (res) {
                    LoadingDialog.hide();
                    alert(res.msg);
                }
            });
        }
    }
}

