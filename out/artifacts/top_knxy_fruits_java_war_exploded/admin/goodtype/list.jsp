<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/list.css" rel="stylesheet">
<div role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <div class="type_add">
        <label>添加类型: </label>
        <input type="text" id="type_name" class="form-control form-control-sm col-md-3" placeholder="类型名称"/>
        <button type="submit" id="type_add" class="btn btn-primary btn-sm col-md-2">添加</button>
    </div>
    <script>
        $(function () {
            $("#type_add").click(function () {
                let name = $("#type_name").val();
                if(!name){
                    alert("请输入类型名称");
                    return;
                }
                Web.request("M1006", {"name": name}, {
                    onSuccess: function (response) {
                        window.location.reload();
                    },
                    onError(response) {
                        alert(response.msg);
                    }
                })
            });
        })

    </script>
    <hr/>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>状态</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.typeList}" var="type">
                <tr id="tr_${type.id}">
                    <td>${type.id}</td>
                    <td>
                        <span>${type.name}</span>
                        <input type="text" value="${type.name}" class="form-control form-control-sm col-md-3"
                               style="display: none; margin: 0"
                               placeholder="请输入名称"/>
                    </td>
                    <td>
                        <span state="${type.state}">
                        </span>
                        <div class="dropdown" style="display: none;">
                            <button state="${type.state}" class="btn btn-secondary dropdown-toggle" type="button"
                                    id="dropdownMenu2"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Dropdown
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <button class="dropdown-item" value="1" type="button">显示</button>
                                <button class="dropdown-item" value="2" type="button">隐藏</button>
                            </div>
                        </div>
                    </td>
                    <td>
                        <button click="edit" class="btn btn-sm btn-primary">编辑</button>
                        <button click="commit" class="btn btn-sm btn-success hide">提交</button>
                        <button click="delete" class="btn btn-sm btn-danger">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {
        $("[state='1']").html("显示");
        $("[state='2']").html("隐藏");

        $("[class='delete']").click(function () {
            let id = $($($(this).parent()).parent()).attr("id");
            $("#" + id + " td span").hide();
        });

        $("[class='dropdown-item']").click(function () {
            let state = $(this).attr("value");
            let ddl = $($(this).parent()).prev();
            $(ddl).attr({"state": state});

            if (state == "1") {
                $(ddl).html("显示");
            } else if (state == "2") {
                $(ddl).html("隐藏");
            }

        });

        $("[click='edit']").click(function () {
            let id = $($($(this).parent()).parent()).attr("id");

            $("#" + id + " td span").hide();
            $(this).hide();

            $("#" + id + " [class='dropdown']").show();
            $("#" + id + " [click='commit']").show();
            $("#" + id + " input").show();
        });

        $("[click='commit']").click(function () {
            let id = $($($(this).parent()).parent()).attr("id");

            $("#" + id + " [class='dropdown']").hide();
            $("#" + id + " input").hide();
            $(this).hide();

            $("#" + id + " [click='edit']").show();
            $("#" + id + " td span").show();
        });
    })
</script>