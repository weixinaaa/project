<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>租车</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/layui/css/layui.css"
          media="all">
<body>
<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="margin-top: 0;height: 100px;">
    <div class="test-table-reload-btn" style="margin-bottom: 5px;">

    <div class="test-table-reload-btn" style="margin-top: 5px;">
        <div class="layui-inline">
            <div class="layui-input-inline">
            <input type="text" value="" placeholder="联系方式" class="layui-input search_input" id="contact">
        </div>
            <label class="layui-form-label">创建时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="startTime" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
            -
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="endTime" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>
        <button id="search_btn" class="layui-btn" data-type="reload">搜索</button>
        <button id="del" class="layui-btn" data-type="del">删除</button>
    </div>
</div>
<table id="partner" lay-filter="test1"></table>
<script id="barDemo" type="text/html">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['table','form','laydate'], function(){

        var laydate = layui.laydate;

        laydate.render({
            elem: '#startTime'
            ,type: 'datetime'
        });

        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
        });
        var form = layui.form;
        var table = layui.table;
        var $ = layui.jquery;
        //第一个实例
        table.render({
            elem: '#partner'
            ,url: '${pageContext.request.contextPath}/console/partner/getPartner' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID',align:'center', sort: true, fixed: 'left'}
                ,{field: 'contact',align:'center', title: '联系方式'}
                ,{field: 'name', align:'center',title: '合伙人姓名'}
                ,{field: 'userName',align:'center', title: '用户'}
                ,{field: 'note',align:'center', title: '备注'}
                ,{field: 'timeCreate',align:'center', title: '创建时间'}
                ,{field: 'timeUpdate',align:'center', title: '更新时间'}
                ,{fixed: 'right', title: '操作框',  align:'center', toolbar: '#barDemo'}
            ]]
        });

        //搜索
        $('#search_btn').click(function(){
            table.reload('partner', {//重新加载
                where: { //设定异步数据接口的额外参数，任意设
                    contact: $('#contact').val(),
                    startTime:$("#startTime").val(),
                    endTime:$("#endTime").val()
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })

        // 删除
        $('#del').click(function(){
            var checkStatus = table.checkStatus('partner')
                ,data = checkStatus.data; //获取选中的数据
            if (data.length == 0) {
                layer.msg('请选择需要删除的数据', {
                    icon: 0,
                    time: 1500 //2秒关闭（如果不配置，默认是3秒）
                });
            } else {
                //eg1
                layer.confirm('是否真的删除？', {icon: 3, title:'提示'}, function(index){
                    var ids = [];
                    for (let partner of data) {
                        ids.push(partner.id) ;
                    }
                    // 发送请求
                    $.ajax({
                        type : 'POST',
                        url : '${pageContext.request.contextPath}/console/partner/delPartner',
                        traditional: true,
                        data : {id : ids},
                        success : function(){
                            layer.msg('删除成功', {
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    });
                    table.reload('partner');
                    layer.close(index);
                });
            }
        });
        //监听工具条
        table.on('tool(test1)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除该角色么', function(index){
                    $.post('${pageContext.request.contextPath}/console/partner/delPartner',
                        {id : data.id},function(res){
                            layer.msg("删除成功", {
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            });
                        });
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            }
        });
    });

</script>
</body>

</html>