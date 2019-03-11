class Upload {
    _file;
    _suffix;
    _name;
    _upToken;

    listener;

    constructor(file) {
        this._file = file;
    }

    start() {
        this._getUploadToken();
    }

    _getUploadToken() {
        let that = this;
        this._suffix = Utils.getSuffix(that._file["name"]);
        Web.request("M1015", {
            "suffix": that._suffix
        }, {
            onSuccess: function (rp) {
                let data = rp.data;
                that._name = data.name;
                that._upToken = data.token;
                that._upload();
            },
            onError: function (rp) {
                that.listener.error("response.code", rp.msg);
            }
        });
    }

    _upload() {
        let that = this;
        Qiniu.uploadImage(that._file, that._name, that._upToken, {
            next: function (res) {
                console.log("       upload:", res);
                that.listener.next(that._file["name"], res.total.percent);
            },
            error: function (err) {
                that.listener.error("-1", err);
            },
            complete: function (res) {
                res.fileName = that._name;
                that.listener.complete(res);
            }
        });
    }
}
