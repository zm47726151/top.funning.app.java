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
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <form method="get" class="row form">
        <input name="id" type="text" placeholder="订单编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>
    <hr/>
    <c:if test="${data.id!=null}">
        <div id="content">
            <div class="row">
                <div class="col-md-10">
                    <div><label>订单编号：</label>${data.id}</div>
                    <div class="row">
                        <div class="col-md-3"><label>价格：</label>${data.price}</div>
                        <div class="col-md-5"><label>取货时间：</label>
                            <fmt:formatDate value="${data.getTimeStart}" pattern="yyyy-MM-dd"/> 至
                            <fmt:formatDate value="${data.getTimeStop}" pattern="yyyy-MM-dd"/>
                        </div>
                        <div class="col-md-4 text_color_red"><label>状态：</label>${data.stateStr}</div>
                    </div>

                    <div class="row">
                        <div class="col-md-3"><label>团编号：</label>${data.groupGoodId}</div>
                        <div class="col-md-5"><label>商品名称：</label>${data.name}</div>
                        <div class="col-md-4"><label>商品描述：</label>${data.description}</div>
                    </div>

                    <div class="row">
                        <div class="col-md-3"><label>消费者编号：</label>${data.userId}</div>
                        <div class="col-md-5"><label>下单时间：</label>
                            <fmt:formatDate value="${data.createDT}" pattern="yyyy-MM-dd"/>
                        </div>
                        <div class="col-md-4"><label>支付时间：</label>
                            <fmt:formatDate value="${data.payDT}" pattern="yyyy-MM-dd"/>
                        </div>
                    </div>

                </div>

                <div class="col-md-2 good_info">
                    <img alt="${data.name}" src="${data.imageUrl}"/>
                </div>
            </div>
            <form class="operation" method="post">
                <input type="hidden" name="id" value="${data.id}">
                <input type="hidden" name="state">
                <c:if test="${data.state == 3}">
                    <button type="button" onclick="Page.finish('${data.id}')"
                            class="btn btn-primary col-md-3">订单已完成
                    </button>
                </c:if>
                <c:if test="${data.state == 5}">
                    <button type="button" onclick="Page.refund('${data.id}')"
                            class="btn btn-primary col-md-3">退款
                    </button>
                </c:if>
                <c:if test="${data.state == 1}">
                    <button type="button" onclick="Page.cancel('${data.id}')"
                            class="btn btn-primary col-md-3">取消订单
                    </button>
                </c:if>
            </form>
        </div>
    </c:if>
</main>
<script src="js/search.js?v=${version}"></script>