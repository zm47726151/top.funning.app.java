<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <form method="get" class="row form">
        <input name="id" type="text" placeholder="商品编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>

    <div id="content">
        <div><label>商品编号：</label>${data.id}</div>
        <div class="row">
            <div class="col-md-3">
                <img alt="商品图片" src="">
            </div>
            <div class="col-md-6">
                <div>商品名称</div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-9"> 商品描述</div>
        </div>

        <div class="row">
            <div class="col-md-9"> 商品价格</div>
            <div class="col-md-9"> 状态</div>
            <div class="col-md-9"> 类型</div>
        </div>

        <label>商品详情图片列表</label>
        <div class="good_list row">
            <c:forEach items="${data.goodList}" var="good">
                <div class="col-md-2">
                    <div goodId="${good.body.id}" class="item">
                        <img alt="${good.body.name}" src="${good.body.imageUrl}"/>
                        <div><label>商品名称：</label>${good.body.name}</div>
                        <div class="text_color_orange"><label>商品价格：</label>${good.body.price}</div>
                        <div class="text_color_orange"><label>购买数量：</label>${good.amount}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <label>商品详情说破图片列表</label>
        <div class="good_list row">
            <c:forEach items="${data.goodList}" var="good">
                <div class="col-md-2">
                    <div goodId="${good.body.id}" class="item">
                        <img alt="${good.body.name}" src="${good.body.imageUrl}"/>
                        <div><label>商品名称：</label>${good.body.name}</div>
                        <div class="text_color_orange"><label>商品价格：</label>${good.body.price}</div>
                        <div class="text_color_orange"><label>购买数量：</label>${good.amount}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <form class="operation" method="post">
            <input type="hidden" name="id" value="${data.id}">
            <input type="hidden" name="state">
            <button type="submit" currentState="2" onclick="toState('3')"
                    class="btn btn-primary col-md-3 hide">订单已完成
            </button>
            <button type="submit" currentState="4" onclick="toState('6')"
                    class="btn btn-primary col-md-3 hide">退款
            </button>
            <button type="submit" currentState="2" onclick="toState('5')"
                    class="btn btn-primary col-md-3 hide">取消订单
            </button>
        </form>
    </div>
</div>