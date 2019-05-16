<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="css/search.css?v=${version}" rel="stylesheet">
<script src="/js/qiniu.min.js"></script>
<script src="js/search.js?v=${version}"></script>
<script>
    let imageHost = "${imageHost}";
    $(function () {
        console.log("imageHost : " + imageHost);
        Page.data = ${data.detail==null?"null":data.detail};
        Page.init();
    });
</script>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <form method="get" class="row form">
        <input name="id" type="text" placeholder="商品编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>

    <hr/>
    <c:if test="${data.id!=null}">
        <div id="content">
            <div>
                <label>商品编号：</label>
                    ${data.id}
                <input id="id" type="hidden" value="${data.id}"/>
            </div>
            <div class="row">
                <div class="col-md-9 base_info">
                    <div class="row">
                        <div class="col-md-3">
                            <label>名称: </label>
                            <input class="form-control" disabled value="${data.name}" id="name"/>
                        </div>
                        <div class="col-md-3">
                            <label>价格: </label>
                            <input class="form-control" disabled value="${data.price}" id="price"
                                   onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                        </div>
                        <div class="col-md-3">
                            <label>拼团人数: </label>
                            <input class="form-control" disabled value="${data.groupNum}" id="groupNum"/>
                        </div>
                        <div class="col-md-3">
                            <label>状态: </label>
                            <div class="dropdown">
                                <button id="state_text" class="btn btn-secondary dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${data.stateStr}
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" onclick="Page.changeState('1')">上架</a>
                                    <a class="dropdown-item" onclick="Page.changeState('2')">下架</a>
                                </div>
                            </div>
                            <input value="${data.state}" type="hidden" id="state"/>
                        </div>
                    </div>

                    <div class="row row2">
                        <div class="col-md-3">
                            <label>结束时间</label>
                            <input class="form-control" disabled
                                   value="<fmt:formatDate value="${data.stopTime}" pattern="yyyy-MM-dd"/>"
                                   id="stopTime"/>
                        </div>
                        <div class="col-md-9">
                            <label>取货时间 </label>
                            <div class="get_time">
                                <input class="form-control" disabled
                                       value="<fmt:formatDate value="${data.getTimeStart}" pattern="yyyy-MM-dd"/>"
                                       id="getTimeStart"/>
                                至
                                <input class="form-control" disabled
                                       value="<fmt:formatDate value="${data.getTimeStop}" pattern="yyyy-MM-dd"/>"
                                       id="getTimeStop"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label>描述: </label>
                        </div>
                        <div class="col-md-12">
                            <textarea disabled class="form-control" id="description"
                                      rows="2">${data.description}</textarea>
                        </div>
                    </div>
                </div>

            </div>


            <div class="row image">
                <div class="item coverImage col-md-3" onclick="Page.changeCoverImage()">
                    <h5>封面图片</h5>
                    <div class="holder">
                        <img alt="封面图片" id="coverImageUrl" src="${data.imageUrl}">
                        <div class="button">点击修改</div>
                    </div>
                </div>
                <div class="item goodImage col-md-3">
                    <h5>商品图片</h5>
                    <div class="holder">
                        <img alt="商品图片" id="goodImageUrl" src="${data.goodImageUrl}">
                        <div class="button">不可修改</div>
                    </div>
                </div>
                <div class="item shareImage col-md-3" onclick="Page.changeShareImage()">
                    <h5>分享图片</h5>
                    <div class="holder">
                        <img alt="分享图片" id="shareImageUrl" src="${data.shareImageUrl}">
                        <div class="button">点击修改</div>
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
    </c:if>
</div>