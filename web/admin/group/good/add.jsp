<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<link href="css/add.css?v=${version}" rel="stylesheet">
<script src="/js/qiniu.min.js"></script>
<script src="js/add.js?v=${version}"></script>
<script>
    let imageHost = "${imageHost}";
    $(function () {
        console.log("imageHost : " + imageHost);
        Page.init();
    });
</script>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <div id="content">

        <div class="row">
            <div class="col-md-9 base_info">

                <div class="row">
                    <div class="col-md-12">
                        <label>名称: </label>
                        <input class="form-control" value="" id="name"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <label>价格: </label>
                        <input class="form-control" value="" id="price"
                               onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                    </div>
                    <div class="col-md-4">
                        <label>拼团人数: </label>
                        <input type="number" class="form-control" value="" id="groupNum"/>
                    </div>
                    <div class="col-md-4">
                        <label>状态: </label>
                        <div class="dropdown">
                            <button id="state_text" class="btn btn-secondary dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                上架
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" onclick="Page.changeState('1')">上架</a>
                                <a class="dropdown-item" onclick="Page.changeState('2')">下架</a>
                            </div>
                        </div>
                        <input value="1" type="hidden" id="state"/>
                    </div>
                </div>

                <div class="row row2">
                    <div class="col-md-3">
                        <label>结束时间</label>
                        <input class="form-control"  id="stopTime"/>
                    </div>
                    <div class="col-md-9">
                        <label>取货时间 </label>
                        <div class="get_time">
                            <input class="form-control" placeholder="开始时间" id="getTimeStart"/>
                            至
                            <input class="form-control" placeholder="结束时间" id="getTimeStop"/>
                        </div>
                    </div>
                    <script>
                        $('#stopTime').datetimepicker({
                            format: 'yyyy-mm-dd hh:ii:00',
                            language: "zh-CN",
                            autoclose: true
                        });
                        $('#getTimeStart').datetimepicker({
                            format: 'yyyy-mm-dd hh:ii:00',
                            language: "zh-CN",
                            autoclose: true
                        });
                        $('#getTimeStop').datetimepicker({
                            format: 'yyyy-mm-dd hh:ii:00',
                            language: "zh-CN",
                            autoclose: true
                        });
                    </script>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label>描述: </label>
                    </div>
                    <div class="col-md-12">
                            <textarea class="form-control" id="description"
                                      rows="2"></textarea>
                    </div>
                </div>
            </div>

        </div>


        <div class="row image">
            <div class="item coverImage col-md-3" onclick="Page.addCoverImage()">
                <h5>封面图片</h5>
                <div class="holder">
                    <img alt="封面图片" id="coverImageUrl" src="">
                    <div class="button">点击添加</div>
                </div>
            </div>
            <div class="item goodImage col-md-3" onclick="Page.addGoodImage()">
                <h5>商品图片</h5>
                <div class="holder">
                    <img alt="商品图片" id="goodImageUrl" src="">
                    <div class="button">点击添加</div>
                </div>
            </div>
            <div class="item shareImage col-md-3" onclick="Page.addShareImage()">
                <h5>分享图片</h5>
                <div class="holder">
                    <img alt="分享图片" id="shareImageUrl" src="">
                    <div class="button">点击添加</div>
                </div>
            </div>
        </div>

        <!-- 暂时不做修改 -->
        <div class="detail">
            <div class="title">
                <h5>商品 Banner 图片列表</h5><span>(限定尺寸：750*750像素)</span>
            </div>
            <div id="detail_header_imageList" class="row">
            </div>

            <div class="title">
                <h5>商品详情图片列表</h5><span>(限定宽度：750像素)</span>
            </div>
            <div id="detail_content_imageList" class="row">
            </div>
        </div>

        <div>
            <input type="file" id="imageUrl_input" class="hide">
        </div>
        <div class="operation">
            <button type="submit" onclick="Page.save.click()" class="btn btn-success col-md-3">保存</button>
        </div>
    </div>

</div>