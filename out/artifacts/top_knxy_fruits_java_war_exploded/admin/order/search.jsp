<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/search.css" rel="stylesheet">
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <form method="get" class="row form">
        <input name="id" type="text" placeholder="订单编号" class="form-control col-md-3" value="${data.id}"/>
        <button type="submit" class="btn btn-primary col-md-1">查询</button>
    </form>

    <div id="content">
        <div><label>订单编号：</label>${data.id}</div>
        <div class="row">
            <div class="col-md-3"><label>商品价格：</label>${data.price}</div>
            <div class="col-md-3"><label>运费：</label>${data.poster}</div>
            <div class="col-md-3 text_color_red"><label>总价：</label>${data.priceAmount}</div>
            <div class="col-md-3 text_color_red"><label>状态：</label>${data.stateStr}</div>
        </div>
        <div class="row">
            <div class="col-md-3"><label>消费者编号：</label>${data.userId}</div>
            <div class="col-md-3"><label>下单时间：</label>${data.createDT}</div>
            <div class="col-md-3"><label>支付时间：</label>${data.payDT}</div>
        </div>
        <div>
            <label>运送地址：</label>
            ${data.provinceName} ${data.cityName} ${data.countyName}
            ${data.detailInfo}
            ${data.telNumber}
            ${data.userName}
        </div>

        <div>
            <label>备注：</label>${data.note}
        </div>

        <label>商品列表</label>
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
</main>

<script>

    $(function () {
        let id = "${data.id}";
        if (!id) {
            $("#content").hide();
        }

        let state = "${data.state}";
        $("[currentState='" + state + "']").removeClass("hide");
    });


    function toState(s) {
        if (window.confirm('你确定吗？')) {
            $("[name='state']").val(s);
            return true;
        } else {
            return false;
        }
    }
</script>