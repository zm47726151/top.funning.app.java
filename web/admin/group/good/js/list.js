let Page = {
    delete: function (id) {
        console.log("a");
        if (confirm("确定删除吗?")) {
            LoadingDialog.show();
            Web.request("M1031", {"id": id}, {
                onSuccess: function (p) {
                    $("#tr_" + id).hide();
                    LoadingDialog.hide();
                },
                onError: function (p) {
                    LoadingDialog.hide();
                }
            })
        }
    },
    search: function (id) {
        window.open('search?id=' + id, '_blank')
    }
}