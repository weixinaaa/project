<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>设备添加</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="../css/news.css" />
</head>

<body class="childrenBody">
<form class="layui-form" action="">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">类型名称:</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入类型名称" id="typeName">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <a class="layui-btn layui-btn-normal" lay-filter="demo1" id="add">立即提交</a>
            <a class="layui-btn layui-btn-primary close">取消</a>
        </div>
    </div>
</form>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../layui/layui.js"></script>
<script src="../layui/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../js/common.js"></script>

<script>
    layui.use(['form'], function() {
        var form = layui.form,
            layer = layui.layer;
    });

    $(document).ready(function() {
    });
    //点击取消关闭当前弹窗
    $(".close").click(function() {
        layer.closeAll("iframe");
        parent.location.reload();
    });

    $("#add").click(function() {
        if($("#typeName").val() == "") {
            layer.msg('请输入类型名称', {
                icon: 2
            });
            return;
        }

        $.ajax({
            type: "post",
            url: "../console/advertisement/addAdvertisementType",
            data: {
                typeName:$("#typeName").val()
            },
            dataType: "json",
            success: function(data) {
                if(data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    var index = top.layer.msg('数据提交中，请稍候', {
                        icon: 16,
                        time: false,
                        shade: 0.8
                    });
                    setTimeout(function() {
                        top.layer.close(index);
                        top.layer.msg("添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 500);
                    //$("#userList").hide();
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                } else {
                    layer.msg(data.msg, {
                        icon: 2
                    });
                }
            }
        });
    });
</script>
</body>

</html>