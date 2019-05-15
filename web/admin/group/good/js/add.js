let Page = {
    id: 0,
    data: {},
    init: function () {
        Page.dataInit();
        Page.detail.header.init();
        Page.detail.content.init();
    },
    dataInit: function () {
        Page.data = {
            "id": 0,
            "header": {"imageList": []},
            "content": {"imageList": []}
        }
    },
    addCoverImage: function () {
        let ele = document.getElementById("imageUrl_input");
        ele.value = null;
        ele.onchange = function () {
            let imageFile = document.getElementById("imageUrl_input").files[0];
            Utils.getImageInfo(imageFile, function (width, height) {
                if (width == 690) {
                    let path = window.URL.createObjectURL(imageFile);
                    Page.data.coverImageFile = imageFile;
                    $("#coverImageUrl").attr({"src": path});
                } else {
                    alert("请选择一张宽度等于690px的图片");
                }
            });
        };
        $("#imageUrl_input").trigger("click");
    },
    addGoodImage: function () {
        let ele = document.getElementById("imageUrl_input");
        ele.value = null;
        ele.onchange = function () {
            let imageFile = document.getElementById("imageUrl_input").files[0];
            Utils.getImageInfo(imageFile, function (width, height) {
                if (width == height) {
                    let path = window.URL.createObjectURL(imageFile);
                    Page.data.goodImageFile = imageFile;
                    $("#goodImageUrl").attr({"src": path});
                } else {
                    alert("请选择一张正方形的图片");
                }
            });
        };
        $("#imageUrl_input").trigger("click");
    },
    addShareImage: function () {
        let ele = document.getElementById("imageUrl_input");
        ele.value = null;
        ele.onchange = function () {
            let imageFile = document.getElementById("imageUrl_input").files[0];
            Utils.getImageInfo(imageFile, function (width, height) {
                if ((width / height) == 1.25) {
                    let path = window.URL.createObjectURL(imageFile);
                    Page.data.shareImageFile = imageFile;
                    $("#shareImageUrl").attr({"src": path});
                } else {
                    alert("请选择一张长宽比为4:5的图片");
                }
            });
        };
        $("#imageUrl_input").trigger("click");
    },
    changeState: function (value) {
        $("#state").val(value);
        if (value == "1") {
            $("#state_text").html("上架");
        } else if (value == "2") {
            $("#state_text").html("下架");
        }
    },
    detail: {
        header: {
            init: function () {
                let html = "";
                let imageList = Page.data.header.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    html += this.getItemView(imageList[i]);
                }
                html += this.getInsertView();
                $("#detail_header_imageList").html(html);
            },
            getItemView: function (item) {
                let html = `
                <div class="col-md-2 item">
                    <img src="{imageUrl}"/>
                    <button class="btn btn-sm btn-danger" onclick="Page.detail.header.delete('{id}')">删除</button>
                </div>`;
                html = html.replace("{imageUrl}", item.url).replace("{id}", item.id);
                return html;
            },
            getInsertView: function () {
                return `
                <div onclick="Page.detail.header.add()" class="col-md-2">
                    <img class="add" src="image/add.png" />
                </div>
                `;
            },
            add: function () {
                let ele = document.getElementById("imageUrl_input");
                ele.value = null;
                ele.onchange = function () {
                    let imageFile = document.getElementById("imageUrl_input").files[0];
                    Utils.getImageInfo(imageFile, function (width, height) {
                        if (width == height) {
                            let path = window.URL.createObjectURL(imageFile);
                            let imageList = Page.data.header.imageList;
                            Page.id = Page.id + 1;
                            imageList.push({
                                "id": Page.id,
                                "url": path,
                                "type": "local",
                                "file": imageFile
                            });
                            Page.detail.header.init();
                        } else {
                            alert("请选择一张正方形的图片");
                        }
                    });
                };
                $("#imageUrl_input").trigger("click");
            },
            delete: function (id) {
                console.log(id);
                let imageList = Page.data.header.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    let item = imageList[i];
                    if (item.id == id) {
                        imageList.splice(i, 1);
                        break;
                    }
                }
                Page.detail.header.init();
            }
        },
        content: {
            init: function () {
                let html = "";
                let imageList = Page.data.content.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    html += this.getItemView(imageList[i]);
                }
                html += this.getInsertView();
                $("#detail_content_imageList").html(html);
            },
            getItemView: function (item) {
                let html = `
                <div  class="col-md-2 item">
                    <img src="{imageUrl}"/>
                    <button class="btn btn-sm btn-danger" onclick="Page.detail.content.delete('{id}')">删除</button>
                </div>`;
                html = html.replace("{imageUrl}", item.url).replace("{id}", item.id);
                return html;
            },
            getInsertView: function () {
                return `
                <div onclick="Page.detail.content.add()" class="col-md-2">
                       <img class="add" src="image/add.png" />
                </div>
                `;
            },
            add: function () {
                let ele = document.getElementById("imageUrl_input");
                ele.value = null;
                ele.onchange = function () {
                    let imageFile = document.getElementById("imageUrl_input").files[0];
                    Utils.getImageInfo(imageFile, function (width, height) {
                        if (width == height) {
                            let path = window.URL.createObjectURL(imageFile);
                            let imageList = Page.data.content.imageList;
                            Page.id = Page.id + 1;
                            imageList.push({
                                "id": Page.id,
                                "url": path,
                                "type": "local",
                                "file": imageFile
                            });
                            Page.detail.content.init();
                        } else {
                            alert("请选择一张正方形的图片");
                        }
                    });
                };

                $("#imageUrl_input").trigger("click");
            },
            delete: function (id) {
                console.log("delete");
                let imageList = Page.data.content.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    let item = imageList[i];
                    if (item.id == id) {
                        imageList.splice(i, 1);
                        break;
                    }
                }
                Page.detail.content.init();
            }
        }
    },
    save: {
        data: {},
        click: function () {
            console.log("save", "click");
            Page.save.data = {
                "name": $("#name").val(),
                "price": $("#price").val(),
                "groupNum": $("#groupNum").val(),
                "state": $("#state").val(),
                "stopTime": $("#stopTime").val(),
                "getTimeStart": $("#getTimeStart").val(),
                "getTimeStop": $("#getTimeStop").val(),
                "description": $("#description").val(),
                "coverImageUrl": $("#coverImageUrl").attr("src"),
                "goodImageUrl": $("#coverImageUrl").attr("src"),
                "shareImageUrl": $("#coverImageUrl").attr("src"),
                "detail": {
                    "content": {
                        "imageList": []
                    },
                    "header": {
                        "imageList": []
                    }
                }
            };


            if (!Page.save.data.coverImageUrl) {
                alert("封面图片不能为空");
                return;
            }
            if (!Page.save.data.goodImageUrl) {
                alert("商品图片不能为空");
                return;
            }
            if (!Page.save.data.shareImageUrl) {
                alert("分享图片不能为空");
                return;
            }
            if (!Page.save.data.name) {
                alert("名称不能为空");
                return;
            }
            if (!Page.save.data.price) {
                alert("价格不能为空");
                return;
            }
            if (!Page.save.data.groupNum) {
                alert("拼团人数不能为空");
                return;
            }
            if (!Page.save.data.stopTime) {
                alert("拼团结束时间不能为空");
                return;
            }
            if (!Page.save.data.getTimeStart) {
                alert("取货时间不能为空");
                return;
            }
            if (!Page.save.data.getTimeStop) {
                alert("取货时间不能为空");
                return;
            }
            if (!Page.save.data.description) {
                alert("描述不能为空");
                return;
            }
            if (!Utils.validMoney(Page.save.data.price)) {
                alert("金额格式不正确");
                return;
            }

            let now = new Date();
            let stopTime = new Date(Page.save.data.stopTime);
            let getStartTime = new Date(Page.save.data.getTimeStart);
            let getStopTime = new Date(Page.save.data.getTimeStop);

            if (now.getTime() > stopTime.getTime()) {
                alert("结束时间不能早与当前时间");
                return;
            }

            if (stopTime.getTime() > getStartTime.getTime()) {
                alert("结束时间不能晚与取货开始时间");
                return;
            }

            if (getStopTime.getTime() < getStartTime.getTime()) {
                alert("取货开始时间晚与取货结束时间");
                return;
            }

            let list = Page.data.header.imageList;
            if (!list || list.length < 1) {
                alert("详情页图片列表不能为空");
                return;
            }

            LoadingDialog.show();
            Page.save._uploadCoverImage();
        },
        _uploadCoverImage: function () {
            console.log("save", "uploadCover");
            if (!Page.data.coverImageFile) {
                Page.save._uploadGoodImage(0);
                return;
            }
            let imageFile = Page.data.coverImageFile;
            let upload = new Upload(imageFile);
            upload.listener = {
                next: function (fname, percent) {
                    let message = "正在上传 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                    LoadingDialog.msg(message);
                },
                error: function (code, msg) {
                    LoadingDialog.hide();
                    alert(msg);
                },
                complete: function (res) {
                    Page.save.data.coverImageUrl = imageHost + res.fileName;
                    Page.save._uploadGoodImage(0);
                }
            };
            upload.start();
        },
        _uploadGoodImage: function () {
            console.log("save", "uploadCover");
            if (!Page.data.goodImageFile) {
                Page.save._uploadShareImage(0);
                return;
            }
            let imageFile = Page.data.goodImageFile;
            let upload = new Upload(imageFile);
            upload.listener = {
                next: function (fname, percent) {
                    let message = "正在上传 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                    LoadingDialog.msg(message);
                },
                error: function (code, msg) {
                    LoadingDialog.hide();
                    alert(msg);
                },
                complete: function (res) {
                    Page.save.data.goodImageUrl = imageHost + res.fileName;
                    Page.save._uploadShareImage(0);
                }
            };
            upload.start();
        },
        _uploadShareImage: function () {
            console.log("save", "uploadCover");
            if (!Page.data.shareImageFile) {
                Page.save._uploadContentImageList(0);
                return;
            }
            let imageFile = Page.data.shareImageFile;
            let upload = new Upload(imageFile);
            upload.listener = {
                next: function (fname, percent) {
                    let message = "正在上传 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                    LoadingDialog.msg(message);
                },
                error: function (code, msg) {
                    LoadingDialog.hide();
                    alert(msg);
                },
                complete: function (res) {
                    Page.save.data.shareImageUrl = imageHost + res.fileName;
                    Page.save._uploadContentImageList(0);
                }
            };
            upload.start();
        },
        _uploadContentImageList: function (index) {
            let imageList = Page.data.content.imageList;
            if (!imageList || index >= imageList.length) {
                this._uploadHeaderImageList(0);
                return;
            }
            let item = imageList[index];
            index = index + 1;
            if (item.type == "server") {
                this._uploadContentImageList(index);
                return;
            }
            console.log("save", "uploadContentImageList index = " + index);
            let upload = new Upload(item.file);
            upload.listener = {
                next: function (fname, percent) {
                    let message = "正在上传 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                    LoadingDialog.msg(message);
                },
                error: function (code, msg) {
                    alert(msg);
                    LoadingDialog.hide();
                },
                complete: function (res) {
                    item.url = imageHost + res.fileName;
                    item.type = "server";
                    item.file = null;
                    Page.save._uploadContentImageList(index);
                }
            };
            upload.start();
        },
        _uploadHeaderImageList: function (index) {
            let imageList = Page.data.header.imageList;
            if (!imageList || index >= imageList.length) {
                Page.save._commit();
                return;
            }
            let item = imageList[index];
            index = index + 1;
            if (item.type == "server") {
                this._uploadHeaderImageList(index);
                return;
            }
            console.log("save", "uploadHeaderImageList index = " + index);
            let upload = new Upload(item.file);
            upload.listener = {
                next: function (fname, percent) {
                    let message = "正在上传 \"" + fname + "\"<br/>已经上传了: " + percent.toFixed(2) + "%";
                    LoadingDialog.msg(message);
                },
                error: function (code, msg) {
                    alert(msg);
                    LoadingDialog.hide();
                },
                complete: function (res) {
                    item.url = imageHost + res.fileName;
                    item.type = "server";
                    item.file = null;
                    Page.save._uploadHeaderImageList(index);
                }
            };
            upload.start();
        },
        _commit: function () {
            console.log("save", "commit");
            let data = Page.save.data;
            let images;
            images = Page.data.content.imageList;
            for (let i = 0; i < images.length; i++) {
                let item = images[i];
                data.detail.content.imageList.push(item.url);
            }

            images = Page.data.header.imageList;
            for (let i = 0; i < images.length; i++) {
                let item = images[i];
                data.detail.header.imageList.push(item.url);
            }

            Web.request("M1034", data, {
                onSuccess: function (p) {
                    LoadingDialog.hide();
                    window.location.reload();
                },
                onError: function (p) {
                    LoadingDialog.hide();
                    alert(p.msg);
                }
            })
        },
    }
}

