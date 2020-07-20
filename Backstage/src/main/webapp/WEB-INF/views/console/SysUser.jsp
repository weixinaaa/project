<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>广告类别</title>
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
<blockquote class="layui-elem-quote news_search">
    <div class="layui-inline">
        <form class="layui-form" action="">
            <div class="layui-input-inline">
                <input type="text" value="" placeholder="用户名称" class="layui-input search_input" id="userName">
            </div>
            <div class="layui-input-inline">
                <input type="text" value="" placeholder="联系方式" class="layui-input search_input" id="tel">
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="createTime" placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
                -
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="endTime" placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
        </form>
    </div>
    <a class="layui-btn layui-btn-primary" id="search"><i class="layui-icon">&#xe615;</i>查询</a>

    </div>
</blockquote>
<div class="layui-row">
    <div class="layui-col-xs6">
        <div class="grid-demo grid-demo-bg1"><div class="layui-inline">
            <a class="layui-btn layui-btn-normal newsAdd_btn">添加</a>
        </div></div>
    </div>
</div>
<form action="" class="layui-form table-box">
    <div id="listView"></div>
    <div id="laypage" class="laypage"></div>
</form>

<script type="text/javascript" src="../layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script src="../layui/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
<script id="tmpList" type="text/html">
    <table class="layui-table" lay-even lay-skin="row" id="tt">
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名称</th>
            <th>角色</th>
            <th>联系方式</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{# layui.each(d.rows, function(index, item){ }}
        <tr>
            <td>{{item.id}}</td>
            <td>{{item.userName}}</td>
            <td>{{item.roleName}}</td>
            <td>{{item.tel}}</td>
            <td>
                {{#  if(item.status == 0){ }}
                    <input type="checkbox" value="{{item.id}}" name="status" lay-skin="switch" lay-filter="switchTest" lay-text="启用|禁用" checked>
                {{#  } }}
                {{#  if(item.status == 1){ }}
                    <input type="checkbox" value="{{item.id}}" name="status" lay-skin="switch"  lay-filter="switchTest" lay-text="启用|禁用">
                {{#  } }}
            </td>
            <td>{{item.createTime}}</td>
            <td>{{item.updateTime}}</td>
            <td><a class="layui-btn layui-btn-mini layui-btn-normal" onclick=DetailEdit({{item.id}})>编辑</a>
                <a class="layui-btn layui-btn-mini layui-btn-primary " onclick=delone({{item.id}})>删除</a>
            </td>
        </tr>
        {{# }); }} {{# if(d.rows.length === 0){ }}
        <td colspan="10" align="center">无数据</td>
        {{# } }}
        </tbody>
    </table>
</script>

<script>
    var from;
    layui.use(['form', 'layer', 'table'], function() {
        window.$ = layui.$; //定义全局$
        form = layui.form; //获取form模块
    });
    layui.use(['laydate'], function() {
        var laydate = layui.laydate;


        laydate.render({
            elem: '#createTime'
            ,type: 'datetime'
        });

        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
        });
    });

    form.on('switch(switchTest)', function(data) {
        //console.log(this.checked)
        var data = {
            "id": $(this).val(),
            "status": this.checked ? 1 : 0
        };
        hyAjax.baseAjax("/sysUser/editSysUser", data, function(data) {
            if(data.code == 0) {
                layer.msg('操作成功', {
                    icon: 1
                });
            } else {
                layer.msg(data.msg, {
                    icon: 2
                });
            }
        });
    });

    $ = layui.jquery;
    $(document).ready(function() {
        $("#search").click(function() {
            getList({
                userName:$("#userName").val(),
                tel:$("#title").val(),
                createTime:$("#createTime").val(),
                endTime:$("#endTime").val()
            });
        });
        getList({});
    });

    function getList(data) {
        CommonLoadList("../console/sysUser/getSysUserAll", data, page, "tmpList", "listView", "laypage");
    }


    function DetailEdit(id) {
        $("#mid").val(id);
        $(window).one("resize", function() {
            //$(".news_edit").click(function(){
            var index = layui.layer.open({
                title: "信息编辑",
                type: 2,
                content: "../console/sysUser/toEdit?id="+id,
                success: function(layero, index) {
                    setTimeout(function() {
                        layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            })
            layui.layer.full(index);
            //})
        }).resize();
    }


    //点击添加文章弹窗（全屏弹窗）
    $(window).one("resize", function() {
        $(".newsAdd_btn").click(function() {
            var index = layui.layer.open({
                title: "添加",
                type: 2,
                content: "SysUserAdd",
                success: function(layero, index) {
                    setTimeout(function() {
                        layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            })
            layui.layer.full(index);
        })
    }).resize();

    function delone(id) {
        layui.use('layer', function() { //独立版的layer无需执行这一句

            var $ = layui.jquery,
                layer = layui.layer; //独立版的layer无需执行这一句
            layer.open({
                type: 1,
                title: '设备删除',
                area: ['15%', '15%'],
                btn: ['确定', '关闭'],
                btnAlign: 'c',
                success: function(layero, index) {

                },
                yes: function(index, layero) {
                    $.post("../console/user/deleteUser?id="+id, {  }, function (data)
                    {
                        if (data.code == 0) {
                            layer.msg("删除成功!");
                            $("#deldevice").hide();
                            layer.close(index);
                            setTimeout(function(){
                                //刷新
                                location.reload();
                            },500);
                        } else {

                            layer.msg("密码错误");



                        }
                    });

                    //按钮【按钮一】的回调

                },
                btn2: function(index, layero) {
                    //按钮【按钮二】的回调
                    $("#deldevice").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                },
                cancel: function() {
                    //右上角关闭回调
                    $("#deldevice").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        });

    }
</script>
</body>

</html>