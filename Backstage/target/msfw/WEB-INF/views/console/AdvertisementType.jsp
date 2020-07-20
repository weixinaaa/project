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
                <input type="text" value="" placeholder="typeName" class="layui-input search_input" id="typeName">
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
    <div class="layui-col-xs6">
        <div class="grid-demo"><div class="layui-inline">
            <input type="button" value="警报信息"  class="layui-btn  layui-btn-danger " onclick="todanger()" style="display:none" id="jingbaobtn">
        </div></div>
    </div>
</div>
<form action="" class="layui-form table-box">
    <div id="listView"></div>
    <div id="laypage" class="laypage"></div>
</form>

<input id="mid" value="" type="hidden" />
<div id="wenjianxiazai" class="showBox" style="display: none">
    <table class="layui-table">
        <thead>
        <tr>
            <th>文件名</th>
            <th style="width: 200px;">操作</th>
        </tr>
        </thead>
        <tbody id="neirong">

        <tr>
            <td></td>
            <td>
                <a><i class="layui-icon">&#xe642;</i>下载</a>

            </td>
        </tr>

        <td colspan="7" align="center">无数据</td>

        </tbody>
    </table>

</div>
<div id="deldevice" class="showBox" style="display: none">
    <form class="layui-form" action="">
        <div class="layui-input-inline" style="margin-left:30%;margin-top:10%">
            <input id="deldd" type="text" value="" placeholder="请输入设备密码" class="layui-input">
        </div>
    </form>
</div>
<div id="rizhichakan" class="showBox" style="display: none">
    <table class="layui-table">
        <input type="hidden" id="getSn" value="">
        <input type="button" value="获取日志" id="getRiZhi" class="layui-btn layui-btn-normal" onclick="getRiZhi()"/>
        <thead>
        <tr>
            <th >日志类型</th>
            <th style="width: 200px;">日志名称</th>
            <th style="width: 200px;">内容</th>
        </tr>
        </thead>
        <tbody id="rizhi">

        <tr>
            <td></td>
            <td></td>
            <td>

            </td>
        </tr>

        <td colspan="7" align="center">无数据</td>

        </tbody>
    </table>

</div>
<div id="jingbao" class="showBox" style="display: none">
    <table class="layui-table">
        <thead>
        <tr>
            <th  style="width: 200px;">sn</th>
            <th style="width: 200px;">错误原因</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="jingbaoneirong">

        <tr>
            <td></td>
            <td></td>
            <td>

        </tr>

        <td colspan="7" align="center">无数据</td>

        </tbody>
    </table>

</div>
<script type="text/javascript" src="../layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script src="../layui/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/common.js" type="text/javascript" charset="utf-8"></script>
<script id="tmpList" type="text/html">
    <table class="layui-table" lay-even lay-skin="row" id="tt">
        <thead>
        <tr>
            <th>ID</th>
            <th>类型名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{# layui.each(d.rows, function(index, item){ }}
        <tr>
            <td>{{item.id}}</td>
            <td>{{item.typeName}}</td>
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

    $ = layui.jquery;
    $(document).ready(function() {
        $("#search").click(function() {
            getList({
                typeName:$("#typeName").val(),

            });
        });
        hello()
        getList({});
    });

    function getList(data) {
        CommonLoadList("../console/advertisement/getTypeAll", data, page, "tmpList", "listView", "laypage");
    }
    //点击添加文章弹窗（全屏弹窗）
    $(window).one("resize", function() {
        $(".newsAdd_btn").click(function() {
            var index = layui.layer.open({
                title: "添加",
                type: 2,

                content: "AdvertisementTypeAdd",
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

    function toDetail(id) {

        $("#mid").val(id);
        $(window).one("resize", function() {
            //$(".news_edit").click(function(){
            var index = layui.layer.open({
                title: "详情",
                type: 2,
                content: "todetail",
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

    function DetailEdit(id) {
        $("#mid").val(id);
        $(window).one("resize", function() {
            //$(".news_edit").click(function(){
            var index = layui.layer.open({
                title: "信息编辑",
                type: 2,
                content: "../console/advertisement/toEditAdvertisementType?id=" + id,
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


    function fileManage(id) {

        $("#mid").val(id);
        $(window).one("resize", function() {
            //$(".news_edit").click(function(){
            var index = layui.layer.open({
                title: "文件上传",
                type: 2,
                content: "fileManage",
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
    var arr2;
    function filejiazai(id){
        hyAjax.baseAjax("../console/device/todetail", {
            'id' : id
        }, function(data) {
            hyAjax.baseAjax("../console/device/folder", {
                'id' : id
            } ,function(data1) {
                arr2 = data1;
                if (data.code == 0) {
                    $("#neirong").html("");
                    var html ="";
                    var datalist = data.data.filename;

                    if(datalist!=null){
                        var arr = datalist.split(",");
                        var arr1= data.data.file.split(",");
                        for(var i=0;i<arr.length;i++){
                            var list = {"file":arr1[i],
                                "filename":arr[i]};
                            var str = JSON.stringify(list);
                            if(arr[i]!='null' && arr[i]!=''){
                                alert(arr2.data[i]);
                                html+="<tr id='i'>";
                                html+="<td>"+arr[i]+"</td>";
                                html+="<td><a  href='javascript:down("+id+","+i+")'>下载</a>";
                                html+="<a style='padding-left:5px'  href='javascript:delfile("+id+","+i+","+arr2.data[i]+")'>删除</a></td>";
                                html+="</tr>";
                            }
                        }

                    }else{
                        html+='<td colspan="7" align="center">无数据</td>';
                    }
                    $("#neirong").html(html);
                    html="";
                } else {
                    layer.msg(data.msg, {
                        icon : 2
                    });
                }
            });

        });
    }
    function dongjie(id,isdongjie) {
        var s=null;
        if(isdongjie==0){
            s="冻结";
        }else{
            s="解冻";
        }
        layer.confirm("确定要"+s+"设备吗？", { title: "设备操作" }, function (index) {
            $.post("../console/device/dongjie?id="+id, {  }, function (data)
            {
                layer.close(index); //如果设定了yes回调，需进行手工关闭
                setTimeout(function(){
                    //刷新
                    location.reload();
                },100);
            });
        });

    }
    function startTime(){
        //获取iframe
        var index = parent.layer.getFrameIndex(window.name);
        //再执行关闭
        parent.layer.close(index);
        //刷新父页面
        window.parent.location.reload();
    }
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
                    $.post("../console/advertisement/deleteAdvertisementType?id="+id, {  }, function (data)
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
    function filetoManage(id) {
        $("#mid").val(id);
        layui.use('layer', function() { //独立版的layer无需执行这一句

            var $ = layui.jquery,
                layer = layui.layer; //独立版的layer无需执行这一句
            layer.open({
                type: 1,
                title: '文件下载',
                area: ['50%', '70%'],
                content: $("#wenjianxiazai"), //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                btn: ['确定', '关闭'],
                btnAlign: 'c',
                success: function(layero, index) {
                    filejiazai(id)
                },
                yes: function(index, layero) {

                    //按钮【按钮一】的回调
                    $("#wenjianxiazai").hide();
                    layer.close(index);
                },
                btn2: function(index, layero) {
                    //按钮【按钮二】的回调
                    $("#wenjianxiazai").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                },
                cancel: function() {
                    //右上角关闭回调
                    $("#wenjianxiazai").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        });
    }
    function delfile(id,i,arr){
        alert(arr);
        $.post("${pageContext.request.contextPath }/console/device/delfile",{"id":id,"index":i,"arr":arr},function(result){
            if(result.code==0){
                alert(result.msg);
                /* window.location.reload();  */
                document.getElementById("i").style.display="none";

            }else{
                alert(result.msg)
            }
        });
    }
    function down(id,i){

        location.href ="${pageContext.request.contextPath }/console/device/down?id="+id+"&index="+i;
    }
    /* 		function down(list){
                alert(123213);
                $.post("${pageContext.request.contextPath }/console/device/down",{"filepath":list.file,"filename":list.filename},function(result){
				  });
			} */
    function scanMessage(sn) {
        $("#getSn").val(sn);

        layui.use('layer', function() { //独立版的layer无需执行这一句

            var $ = layui.jquery,
                layer = layui.layer; //独立版的layer无需执行这一句
            layer.open({
                type: 1,
                title: '日志查看',
                area: ['50%', '70%'],
                content: $("#rizhichakan"), //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                btn: ['确定', '关闭'],
                btnAlign: 'c',
                success: function(layero, index) {
                    hyAjax.baseAjax("../console/device/rizhi", {
                        'sn' : sn
                    }, function(data) {
                        if (data.code == 0) {
                            $("#rizhi").html("");
                            var html ="";

                        } else {
                            $("#rizhi").html("");
                            var html ="";
                            if(data.msg=='2'){
                                layer.msg("设备故障", {
                                    icon : 2
                                });
                            }else if(data.msg=='3'){
                                layer.msg("设备离线", {
                                    icon : 2
                                });
                            }

                        }
                    });
                },
                yes: function(index, layero) {

                    //按钮【按钮一】的回调
                    $("#rizhichakan").hide();
                    layer.close(index);
                },
                btn2: function(index, layero) {
                    //按钮【按钮二】的回调
                    $("#rizhichakan").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                },
                cancel: function() {
                    //右上角关闭回调
                    $("#rizhichakan").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        });
    }
    function getRiZhi() {
        var sn = $("#getSn").val();
        hyAjax.baseAjax("../console/device/getrizhi", {
            'sn' : sn
        }, function(data) {
            if (data.code == 0) {

                $("#rizhi").html("");
                var html ="";
                var datalist = data.rows[0].result;
                var obj = JSON.parse(datalist);
                if(obj!=null&&obj.fileList!=null){
                    var list = obj.fileList[0]
                    var l = list[0]
                    for(var i=0;i<list.length;i++){

                        if(list[i]!=null){
                            html+="<tr>";
                            console.log(list[i])
                            console.log(list[i].attribute)
                            if(list[i].attribute==0){
                                html+="<td>普通日志</td>";
                            }else{
                                html+="<td>警告日志</td>";
                            }

                            html+="<td>"+list[i].fileName+"</td>";
                            html+='<td>'+list[i].fileData+'</td>';
                            html+="</tr>";
                        }
                    }
                }else{
                    html+='<td colspan="7" align="center">无数据</td>';
                }
                $("#rizhi").html(html);
                html="";
            } else {
                layer.msg(data.msg, {
                    icon : 2
                });
            }
        });
    }
    function todanger() {
        $("#getSn").val(sn);

        layui.use('layer', function() { //独立版的layer无需执行这一句

            var $ = layui.jquery,
                layer = layui.layer; //独立版的layer无需执行这一句
            layer.open({
                type: 1,
                title: '日志查看',
                area: ['50%', '70%'],
                content: $("#jingbao"), //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                btn: ['确定', '关闭'],
                btnAlign: 'c',
                success: function(layero, index) {

                },
                yes: function(index, layero) {

                    //按钮【按钮一】的回调
                    $("#jingbao").hide();
                    layer.close(index);
                },
                btn2: function(index, layero) {
                    //按钮【按钮二】的回调
                    $("#jingbao").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                },
                cancel: function() {
                    //右上角关闭回调
                    $("#jingbao").hide();
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        });
    }

    function hello(){
        hyAjax.baseAjax("../console/device/getjingbao", {
        }, function(data) {
            if (data.code == 0) {
                if(data.total>0){
                    $("#jingbaobtn").show()
                    $("#jingbaobtn").val(data.total+"条警报")
                }
                $("#jingbaoneirong").html("");
                var html ="";
                var datalist = data.rows;

                if(datalist!=null){
                    for(var i=0;i<datalist.length;i++){
                        html+="<tr>";
                        html+="<td>"+datalist[i].sn+"</td>";
                        switch(datalist[i].wrong){
                            case 1:
                                html+="<td>"+"模块A TE片故障"+"</td>"
                                break;
                            case 2:
                                html+="<td>"+"模块B TE片故障"+"</td>"
                                break;
                            case 3:
                                html+="<td>"+"模块A传感器故障"+"</td>"
                                break;
                            case 4:
                                html+="<td>"+"模块B传感器故障"+"</td>"
                                break;
                            case 5:
                                html+="<td>"+"热盖A传感器故障"+"</td>"
                                break;
                            case 6:
                                html+="<td>"+"热盖B传感器故障"+"</td>"
                                break;
                            case 7:
                                html+="<td>"+"散热器传感器故障"+"</td>"
                                break;
                            case 8:
                                html+="<td>"+"热盖A控温故障"+"</td>"
                                break;
                            case 9:
                                html+="<td>"+"热盖B控温故障"+"</td>"
                                break;
                            case 10:
                                html+="<td>"+"环境超温报警"+"</td>"
                                break;
                            case 11:
                                html+="<td>"+"环境超温故障"+"</td>"
                                break;
                            case 12:
                                html+="<td>"+"AD初始化失败"+"</td>"
                                break;
                            case 13:
                                html+="<td>"+"散热器故障"+"</td>"
                                break;
                            case 14:
                                html+="<td>"+"热盖故障"+"</td>"
                                break;
                            case 15:
                                html+="<td>"+"设备异常"+"</td>"
                                break;
                            default:
                                html+="<td>"+"设备正常"+"</td>"
                        }
                        html+='<td><input type="button" value="删除" onclick="sanchu('+datalist[i].id+')"></td>';
                        html+="</tr>";
                    }

                }else{
                    html+='<td colspan="7" align="center">无数据</td>';
                }
                $("#jingbaoneirong").html(html);
                html="";
            } else {
                layer.msg(data.msg, {
                    icon : 2
                });
            }
        });
    }
    function sanchu(id){
        hyAjax.baseAjax("../console/device/deljingbao", {
            'id':id
        }, function(data) {
            if(data.code==0){
                alert("删除成功")
                hello()

            }
        });
    }
    //重复执行某个方法
    //var t2 = window.setInterval("hello()",20000);
    //去掉定时器的方法
    //window.clearInterval(t1);
</script>
</body>

</html>