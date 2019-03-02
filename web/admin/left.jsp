<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    let page = "${page}";
    $(function () {
        console.log(page);
        $("[href='" + page + "']").addClass("active");
    })

</script>

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="order/list">
                    <span data-feather="file"></span>
                    订单列表
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="order/search">
                    <span data-feather="file"></span>
                    订单查询
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="good">
                    <span data-feather="shopping-cart"></span>
                    商品管理<br/>（列表，添加，删除，修改，查询）
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="goodType">
                    <span data-feather="shopping-cart"></span>
                    商品类型管理<br/>（列表，添加，删除，修改，查询）
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="goodType">
                    <span data-feather="shopping-cart"></span>
                    修改密码<br/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="goodType">
                    <span data-feather="shopping-cart"></span>
                    添加管理员<br/>
                </a>
            </li>
        </ul>
    </div>
</nav>