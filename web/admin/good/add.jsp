<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-06
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/search.js"></script>
<script>
    $(function () {
        Page.data = ${data.detail};
        Page.init();
    })

</script>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <form method="get" class="row form">
        <input name="id" type="text" placeholder="商品编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>

    <hr/>

    <div id="content">
        <div><label>商品编号：</label>${data.id}</div>
        <div class="row">
            <div class="col-md-3">
                <img alt="商品图片" id="imageUrl" src="${data.imageUrl}">
                <a onclick="changeImage()">点击上传</a>
                <!--
                    1. 使用shop_fruits --- image.fruits.knxy.top
                    2. 点击选择后记录地址，显示本地图片
                    3. 管理员点击保存，上传图片到七牛云，保存到数据库。
                -->
            </div>
            <div class="col-md-6">
                <input value="${data.name}" id="name"/>
            </div>
        </div>

        <div class="row">
            <input value="${data.description}" id="description"/>
        </div>

        <div class="row">
            <div class="col-md-3">
                <input value="${data.price}" id="price"/>
            </div>
            <div class="col-md-3">
                <div class="dropdown">
                    <button id="state_text" class="btn btn-secondary dropdown-toggle" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${data.stateStr}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" onclick="Page.changeState(this,'1')">上架</a>
                        <a class="dropdown-item" onclick="Page.changeState(this,'2')">下架</a>
                    </div>
                </div>
                <input value="${data.state}" type="hidden" id="state"/>
            </div>
            <div class="col-md-3">
                <div class="dropdown">
                    <button id="type_text"  class="btn btn-secondary dropdown-toggle" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${data.typeStr}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <c:forEach items="${data.typeList}" var="type">
                            <a class="dropdown-item" onclick="Page.changeType(this,'${type.id}')">${type.name}</a>
                        </c:forEach>
                    </div>
                </div>
                <input value="${data.type}" id="type"/>
            </div>
        </div>

        <!-- 暂时不做修改 -->
        <label>商品详情图片列表</label>
        <div id="detail_header_imageList" class="row">
        </div>

        <label>商品详情说破图片列表</label>
        <div id="detail_content_imageList" class="row">
        </div>

        <input type="file" id="imageUrl_input" onchange="imageChoice()" class="hide">
        <div class="operation">
            <button type="submit" onclick="Page.save()" class="btn btn-primary col-md-3">保存</button>
        </div>
    </div>
</div>