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
            <label class="layui-form-label">用户名称：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入类型名称" id="username" value="${user.username}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机号：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入手机号" id="tel" value="${user.tel}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入密码" id="passwrod" value="${user.passwrod}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label "><span style="color: red;">*</span>性别</label>
                <div class="layui-input-block w300">
                    <select class="layui-input w300" id="gender" name="supplierId">
                        <option value="2">其他</option>>
                        <option value="0" <c:if test="${user.gender==0}">selected</c:if>>男</option>
                        <option value="1" <c:if test="${user.gender==1}">selected</c:if>>女</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邀请码：</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入" id="invitationCode" value="${user.invitationCode}">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label ">邀请人</label>
                <div class="layui-input-block w300">
                    <select class="layui-input w300" id="inviterId" name="inviterId">
                        <option value="0">无</option>
                        <e:forEach items="${userList}" var="data">
                            <option value="${data.id}" <c:if test="${user.inviterId==data.id}">selected</c:if> >${data.username}</option>
                        </e:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">佣金余额：</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="佣金余额" id="commissionBalance" value="${user.commissionBalance}" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱：</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="邮箱" id="mailbox" value="${user.mailbox}">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户类型：</label>
                <div class="layui-input-inline">
                    <select class="layui-input w300" id="type" name="supplierId">
                        <option value="1" <c:if test="${user.type==1}">selected</c:if>>乘客</option>
                        <option value="2" <c:if test="${user.type==2}">selected</c:if>>司机</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline" style="float:left;margin-top:20px">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="img" src="${user.img}">
                    </div>
                    <button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>上传图片</button>
                </div>
            </div>
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
                $("#img").attr("src",res.data);
                $("#img").val(res.data);
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
                url: "../../console/user/editUser",
                data: {
                    id:id,
                    username:$("#username").val(),
                    passwrod:$("#passwrod").val(),
                    img:$("#img").val(),
                    tel:$("#tel").val(),
                    type:$("#type").val(),
                    invitationCode:$("#invitationCode").val(),
                    inviterId:$("#inviterId").val(),
                    commissionBalance:$("#commissionBalance").val(),
                    gender:$("#gender").val(),
                    mailbox:$("#mailbox").val()
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

    $(document).ready(function() {
    });

    var id = ${user.id};


</script>
</body>

</html>