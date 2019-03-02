<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>商品价格</th>
                <th>运费</th>
                <th>总价</th>
                <th>电话</th>
                <th>收件人</th>
                <th>状态</th>
                <th>用户编号</th>
                <th>下单时间</th>
                <th>支付时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.price}</td>
                    <td>${order.poster}</td>
                    <td>${order.priceAmount}</td>
                    <td>${order.telNumber}</td>
                    <td>${order.userName}</td>
                    <td>${order.stateStr}</td>
                    <td>${order.userId}</td>
                    <td>${order.createDT}</td>
                    <td>${order.payDT}</td>
                    <td>完成</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="?page=1">上一页</a></li>
            <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
            <li class="page-item active"><a class="page-link" href="?page=2">2</a></li>
            <li class="page-item"><a class="page-link" href="?page=3">3</a></li>
            <li class="page-item"><a class="page-link" href="?page=4">下一页</a></li>
        </ul>
    </div>
</main>