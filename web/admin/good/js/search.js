let Page = {
    id: 0,
    data: {},
    init: function () {
        let oldImgs, newImgs;
        oldImgs = this.data.detail.header.imageList;
        newImgs = [];
        for (let i = 0; i < oldImgs.length; i++) {
            let url = oldImgs[i];
            newImgs.push({
                "id": ++this.id,
                "url": url,
                "type": "local"
            });
        }
        this.data.detail.header.imageList = newImgs;


        oldImgs = this.data.detail.content.imageList;
        newImgs = [];
        for (let i = 0; i < oldImgs.length; i++) {
            let url = oldImgs[i];
            newImgs.push({
                "id": ++this.id,
                "url": url,
                "type": "local"
            });
        }
        this.data.detail.content.imageList = newImgs;
    },
    changeImage: function () {
        $("#imageUrl_input").change = function () {
            let imageFile = document.getElementById("imageUrl_input").files[0];
            $("#imageUrl").attr({"src": imageFile.path});
        };
        $("#imageUrl_input").trigger("click");

    },
    changeState: function (value) {
        $("#state").val(value);
        let text = $(this).html();
        $("#state_text").html(text);
    },
    changeType: function (value) {
        $("#type").val(value);
        let text = $(this).html();
        $("#type_text").html(text);
    },
    detail: {
        header: {
            init: function () {
                let html = "";
                let imageList = this.data.detail.header.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    html += this.getItemView(imageList[i]);
                }

                $("#detail_header_imageList").html(html);
            },
            getItemView: function (item) {
                let html = `
                <div class="col-md-2">
                    <img src="{imageUrl}"/>
                    <a onclidk="Page.detail.header.delete('{imageUrl}')">删除</a>
                </div>`;
                html = html.replace("{imageUrl}", item.src).replace("{id}", item.id);
                return html;
            },
            getInsertView: function () {
                return `
                <div onclick="Page.detail.detail.add()" class="add">
                    +
                </div>
                `;
            },
            add: function () {
                $("#imageUrl_input").change = function () {
                    let imageList = this.data.detail.header.imageList;
                    let imageFile = document.getElementById("imageUrl_input").files[0];
                    imageList.push({
                        "id": ++this.id,
                        "url": imageFile.url,
                        "type": "local"
                    });
                };
                $("#imageUrl_input").trigger("click");
                this.init();

            },
            delete: function (id) {
                let imageList = this.data.detail.header.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    let item = imageList[i];
                    if (item.id == id) {
                        imageList.remove(item);
                        break;
                    }
                }
            }
        },
        content: {
            init: function () {
                let html = "";
                let imageList = this.data.detail.content.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    html += this.getItemView(imageList[i]);
                }

                $("#detail_content_imageList").html(html);
            },
            getItemView: function (item) {
                let html = `
                <div class="col-md-2">
                    <img src="{imageUrl}"/>
                    <a onclidk="Page.detail.content.delete('{id}')">删除</a>
                </div>`;
                html = html.replace("{imageUrl}", item.src).replace("{id}", item.id);
                return html;
            },
            getInsertView: function () {
                return `
                <div onclick="Page.detail.content.add()" class="add">
                    +
                </div>
                `;
            },
            add: function () {
                $("#imageUrl_input").change = function () {
                    let imageList = this.data.detail.content.imageList;
                    let imageFile = document.getElementById("imageUrl_input").files[0];
                    imageList.push({
                        "id": ++this.id,
                        "url": imageFile.url,
                        "type": "local"
                    });
                };
                $("#imageUrl_input").trigger("click");
                this.init();
            },
            delete: function (id) {
                let imageList = this.data.detail.content.imageList;
                for (let i = 0; i < imageList.length; i++) {
                    let item = imageList[i];
                    if (item.id == id) {
                        imageList.remove(item);
                        break;
                    }
                }
            }
        }
    },
    save: function () {
        LoadingDialog.show();
        this.upload();
        let oldImgs, newImgs;

        oldImgs = this.data.detail.header.imageList;
        newImgs = [];
        for (let i = 0; i < oldImgs.length; i++) {
            let item = oldImgs[i];
            newImgs.push(item.url);
        }
        this.data.detail.header.imageList = newImgs;

        oldImgs = this.data.detail.content.imageList;
        newImgs = [];
        for (let i = 0; i < oldImgs.length; i++) {
            let item = oldImgs[i];
            newImgs.push(item.url);
        }
        this.data.detail.content.imageList = newImgs;

        let data = {
            "id": $("id").val(),
            "name": $("name").val(),
            "description": $("description").val(),
            "price": $("price").val(),
            "state": $("price").val(),
            "type": $("type").val(),
            "detail": {
                "content": {
                    "imageList": Page.data.detail.content.imageList
                },
                "header": {
                    "imageList": Page.data.detail.header.imageList
                }
            }
        };

        if (!data.name) {
            alert("名称不能为空");
            return;
        }
        if (!data.price) {
            alert("价格不能为空");
            return;
        }
        if (!data.detail.header.imageList) {
            alert("详情页图片列表不能为空");
            return;
        }
        Web.request("M1011", data, {
            onSuccess: function (p) {
                LoadingDialog.hide();
            },
            onError: function (p) {
                LoadingDialog.hide();
                alert(p.msg);
            }
        })
    },
    upload: function () {

    }
}