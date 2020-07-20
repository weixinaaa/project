<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>代理下方用户</title>
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
            <th>头像</th>
            <th>用户名称</th>
            <th>手机号</th>
            <th>用户类型</th>
            <th>邀请码</th>
            <th>邀请人名称</th>
            <th>佣金余额</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{# layui.each(d.rows, function(index, item){ }}
        <tr>
            <td>{{item.id}}</td>
            <td><image width='50px' height='50px' src="{{item.img}}" /></td>
            <td>{{item.userName}}</td>
            <td>{{item.tel}}</td>
            <td>{{item.type==1?"乘客":"司机"}}</td>
            <td>{{item.invitationCode}}</td>
            <td>{{item.inviterName}}</td>
            <td>{{item.commissionBalance}}</td>
            <td>{{item.gender==0?"男":"女"}}</td>
            <td>{{item.mailbox}}</td>
            <td>{{item.createTime}}</td>
            <td>{{item.updateTime}}</td>
            <td><a class="layui-btn layui-btn-mini layui-btn-normal" onclick=addCommission({{item.id}})>编辑</a>
            </td>
        </tr>
        {{# }); }} {{# if(d.rows.length === 0){ }}
        <td colspan="14" align="center">无数据</td>
        {{# } }}
        </tbody>
    </table>
</script>

<script>

    layui.use('laydate', function() {
        var laydate = layui.laydate;

        laydate.render({
            elem: '#createTime'
            ,type: 'datetime'
        });

        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
        });
    })

    $ = layui.jquery;
    $(document).ready(function() {
        $("#search").click(function() {
            getList({
                userName:$("#userName").val(),
                tel:$("#tel").val(),
                createTime:$("#createTime").val(),
                endTime:$("#endTime").val()
            });
        });
        getList({});
    });

    function getList(data) {
        CommonLoadList("../console/agent/selRegion", data, page, "tmpList", "listView", "laypage");
    }

    //添加佣金
    function addCommission(id) {
        $("#mid").val(id);
        $(window).one("resize", function() {
            //$(".news_edit").click(function(){
            var index = layui.layer.open({
                title: "添加佣金",
                type: 2,
                content: "../console/agent/toAddRegionCommission?id="+id,
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


</script>
</body>

</html>