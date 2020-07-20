<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>设备修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="../../css/news.css" />
</head>

<body class="childrenBody">
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入类型名称" id="title" value="">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label "><span style="color: red;">*</span>广告类型</label>
                <div class="layui-input-block w300">
                    <select class="layui-input w300" id="typeId" name="supplierId">
                        <e:forEach items="${advertisementType}" var="data">
                            <option value="${data.id}" >${data.typeName}</option>
                        </e:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label "><span style="color: red;">*</span>广告厂商</label>
                <div class="layui-input-block w300">
                    <select class="layui-input w300" id="businessId" name="supplierId">
                        <e:forEach items="${advertisementBusiness}" var="data">
                            <option value="${data.id}" >${data.name}</option>
                        </e:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">点击量</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="点击量" id="clicks" value="">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="点击量" id="sort" value="">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <input type="text" name="identity" lay-verify="identity" placeholder=""id="briefIntroduction" autocomplete="off" class="layui-input" value="">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline" style="float:left;margin-top:20px">
            <label class="layui-form-label">封面图</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="coverMap" src="">
                    </div>
                    <button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>上传图片</button>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">简介</label>
        <div class="layui-block">
            <textarea class="layui-textarea" id="content" style="display: none">

            </textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <a class="layui-btn layui-btn-normal" lay-filter="demo1" id="add">立即提交</a>
        </div>
    </div>
</form>
<script type="text/javascript" src="../../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script src="../../layui/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../../js/common.js"></script>

<script>

    layui.use('upload',function(){
        var upload = layui.upload;
        //图片上传
        var uploadInst = upload.render({
            elem:'#test1',
            url:'../../file/upload',
            multiple:'true',
            before:function(obj){
            },
            done:function(res){
                //上传完毕
                $("#coverMap").attr("src",res.data);
                $("#coverMap").val(res.data);
            }
        });
    });

    layui.use(['form'], function() {
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit;
        var content = layedit.getContent(index);

        //构建一个默认的编辑器
        var index = layedit.build('content');

        $("#add").click(function() {
            if($("#typeName").val() == "") {
                layer.msg('请输入类型名称', {
                    icon: 2
                });
                return;
            }
            $.ajax({
                type: "post",
                url: "../../console/advertisement/addAdvertisement",
                data: {
                    title:$("#title").val(),
                    typeId:$("#typeId").val(),
                    businessId:$("#businessId").val(),
                    coverMap:$("#coverMap").val(),
                    briefIntroduction:$("#briefIntroduction").val(),
                    content:layedit.getContent(index),
                    sort:$("#sort").val(),
                    clicks:$("#clicks").val()
                },
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
    });


</script>
</body>

</html>