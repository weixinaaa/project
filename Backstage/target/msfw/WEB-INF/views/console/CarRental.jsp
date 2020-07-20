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
        <div class="layui-inline">
            <select id="carTypeid" name="carTypeId" lay-filter="carTypeid">
                <option value="0">请选择车型</option>
            </select>
        </div>
        <div class="layui-inline">
            <select id="proid" name="proId" lay-filter="proid">
                <option value="0">请选择省份</option>
            </select>
        </div>
        <div class="layui-inline">
            <select id="cityid" name="cityId" lay-filter="cityid">
                <option value="0">请选择城市</option>
            </select>
        </div>
        <div class="layui-inline">
            <select id="areaid" name="areaId" lay-filter="areaid">
                <option value="0">请选择区/县</option>
            </select>
        </div>
    </div>
    <div class="test-table-reload-btn" style="margin-top: 5px;">
        <div class="layui-inline">
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
    <button id="add" class="layui-btn" data-type="add">添加</button>
    <button id="del" class="layui-btn" data-type="del">删除</button>
    </div>
</div>
    <table id="canRental" lay-filter="test1"></table>
<script id="barDemo" type="text/html">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
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
            elem: '#canRental'
            ,url: '${pageContext.request.contextPath}/console/carRental/getCarRental' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID',align:'center', sort: true, fixed: 'left'}
                ,{field: 'carName',align:'center', title: '车名'}
                ,{field: 'carTypeName', align:'center',title: '车型'}
                ,{field: 'engineType',align:'center', title: '发动机机型'}
                ,{field: 'address',align:'center', title: '地址',templet:function (data){//根据状态显示开关
                    return data.proName+data.cityName+data.areaName
                }}
                ,{field: 'gearType',align:'center', title: '挡位',templet:function (data){//根据状态显示开关
                    return data.gearType == 1 ? "自动挡" : "手动挡"
                }}
                ,{field: 'img',align:'center', title: '图片',templet:function (data){
                    return '<img class="layui-upload-img" src="'+data.img+'" >'
                }}
                ,{field: 'seatNum',align:'center', title: '座位'}
                ,{field: 'dailyRentPrice',align:'center', title: '日租价格'}
                ,{field: 'timeCreate',align:'center', title: '创建时间'}
                ,{field: 'timeUpdate',align:'center', title: '更新时间'}
                ,{fixed: 'right', title: '操作框',  align:'center', toolbar: '#barDemo'}
            ]]
        });

        //获取车型
        $.ajax({
            type:'post'
            ,url:'${pageContext.request.contextPath}/console/carType/selCarType'
            ,data:{}
            ,success : function(res){
                var html = '';
                    for (let carType of res.rows) {
                    html += '<option value="'+carType.id+'">'+carType.carType+'</option>'
                }
                $('#carTypeid').append(html);//将html语句追加到select框里
                form.render('select');
            }
        })
        //获取省
        $.ajax({
            type:'post'
            ,url:'${pageContext.request.contextPath}/pca/selPro'
            ,data:{}
            ,success : function(res){
                var html = '';
                for (let pro of res.rows) {
                    html += '<option value="'+pro.id+'">'+pro.proName+'</option>'
                }
                $('#proid').append(html);//将html语句追加到select框里
                form.render('select');
            }
        })
        //获取市
        form.on('select(proid)', function(data){
            $('#cityid').html('<option value="0">请选择城市</option>')
            if (data.value!=0){
                $.ajax({
                    type:'post'
                    ,url:'${pageContext.request.contextPath}/pca/selCity'
                    ,data:{pid : data.value}
                    ,success :function(res){
                        var html = '';
                        for (let city of res.rows) {
                            html += '<option value="'+city.id+'">'+city.cityName+'</option>'
                        }
                        $('#cityid').append(html);
                        form.render('select');
                    }
                })
            }
        });
        //获取区域
        form.on('select(cityid)', function(data){
            $('#areaid').html('<option value="0">请选择区/县</option>')
            if (data.value!=0){
                $.ajax({
                    type:'post'
                    ,url:'${pageContext.request.contextPath}/pca/selArea'
                    ,data:{pid : data.value}
                    ,success :function(res){//res数据接口的返回值
                        var html = '';
                        if (res.code==-1){
                            html += '<option value="0">请选择区/县</option>'
                        }else{
                            for (let area of res.rows) {
                                html += '<option value="'+area.id+'">'+area.areaName+'</option>'
                            }
                        }
                        $('#areaid').append(html);
                        form.render('select');
                    }
                })
            }
        });

        //搜索
        $('#search_btn').click(function(){
            table.reload('canRental', {//重新加载
                where: { //设定异步数据接口的额外参数，任意设
                    carTypeId: $('#carTypeid').val()
                    ,proId: $('#proid').val()
                    ,cityId: $('#cityid').val()
                    ,areaId: $('#areaid').val()
                    ,startTime:$("#startTime").val()
                    ,endTime:$("#endTime").val()

                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })

        // 弹出添加窗口
        $('#add').click(function(){
            layer.open({
                type: 2 // iframe层
                ,title: '添加车辆' // 标题
                ,area: ['100%', '100%'] // 宽高
                ,anim: 6 // 抖动
                ,resize : false // 不允许拉伸
                ,content: '${pageContext.request.contextPath}/console/CarRentalAdd' // 内容
                ,shade: 0 //不显示遮罩
            });
        });

        // 删除角色
        $('#del').click(function(){
            var checkStatus = table.checkStatus('canRental')
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
                    for (let canRental of data) {
                        ids.push(canRental.id) ;
                    }
                    // 发送请求
                    $.ajax({
                        type : 'POST',
                        url : '${pageContext.request.contextPath}/console/carRental/delCarRental',
                        traditional: true,
                        data : {id : ids},
                        success : function(){
                            layer.msg('删除成功', {
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    });
                    table.reload('canRental');
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
                    $.post('${pageContext.request.contextPath}/console/carRental/delCarRental',
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
            } else if(layEvent === 'edit'){ //编辑
                layer.open({
                    type: 2 // iframe层
                    ,title: '修改车辆' // 标题
                    ,area: ['100%', '100%'] // 宽高
                    ,anim: 6 // 抖动
                    ,resize : false // 不允许拉伸
                    ,content: '${pageContext.request.contextPath}/console/carRental/toEdit?id='+data.id // 内容
                    ,shade: 0 //不显示遮罩
                });
            }
        });
    });

</script>
</body>

</html>