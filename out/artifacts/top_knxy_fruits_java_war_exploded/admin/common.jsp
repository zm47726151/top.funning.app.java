<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="icon" href="/admin/image/favicon.png">
    <title>Admin</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    <!-- Custom styles for this template -->
    <link href="/admin/css/common.css" rel="stylesheet">


    <!-- Bootstrap core JavaScript
        ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <script>window.jQuery || document.write('<script src="https://v4.bootcss.com/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <script src="/js/common.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
        feather.replace()
    </script>

</head>


<body>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">科宁新远小程序商城</a>
    <div class="w-100"></div>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">消息</a>
        </li>
    </ul>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">退出</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">

        <script>
            let page = "${currentURI}";
            $(function () {
                console.log(page);
                $("[href='" + page + "']").addClass("active");
            })
        </script>

        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/undo">
                            未处理订单
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/list">
                            <span data-feather="file"></span>
                            订单列表
                        </a>
                    </li>
                    <li class="nav-item line">
                        <a class="nav-link" href="/admin/order/search">
                            <span data-feather="file"></span>
                            订单详情
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/good">
                            <span data-feather="shopping-cart"></span>
                            商品列表<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/good">
                            <span data-feather="shopping-cart"></span>
                            添加商品<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/good">
                            <span data-feather="shopping-cart"></span>
                            商品详情<br/>
                        </a>
                    </li>

                    <li class="nav-item line">
                        <a class="nav-link" href="/admin/goodtype/list">
                            <span data-feather="shopping-cart"></span>
                            商品类型列表<br/>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/goodType">
                            <span data-feather="shopping-cart"></span>
                            修改密码<br/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/order/goodType">
                            <span data-feather="shopping-cart"></span>
                            添加管理员<br/>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- ${viewJsp} -->
        <jsp:include page="${viewJsp}"></jsp:include>
    </div>
</div>

<div id="LoadingDialog" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
    <view class="lv_floater">
        <div class="lv_container">
            <view class="lv_point lv_point1"></view>
            <view class="lv_point lv_point2"></view>
            <view class="lv_point lv_point3"></view>
        </div>
    </view>
</div>


</body>

</html>