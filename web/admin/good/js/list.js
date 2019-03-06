let Page = {
    delete: function (id) {
        LoadingDialog.show();
        Web.request("M1012", {"id": id}, {
            onSuccess: function (p) {
                $("tr_" + id).hide();
                LoadingDialog.hide();
            },
            onError: function (p) {
                LoadingDialog.hide();
            }
        })
    }
}