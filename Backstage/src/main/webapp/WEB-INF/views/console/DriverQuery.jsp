<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>司机查询</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/layui/css/layui.css"
          media="all">
<body>
<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="margin-top: 0;height: 100px;">
    <div class="test-table-reload-btn" style="margin-bottom: 5px;">
        <div class="layui-inline">
            <input class="layui-input driverName" placeholder="司机姓名"
                   name="driverName" id="" autocomplete="off">
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
    </div>
</div>
<table id="driverQuery" lay-filter="test1"></table>


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
            elem: '#driverQuery'
            ,url: '${pageContext.request.contextPath}/console/driverRegistered/selDriver' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID',align:'center', sort: true, fixed: 'left'}
                ,{field: 'driverName',align:'center', title: '司机姓名'}
                ,{field: 'contactTel', align:'center',title: '联系电话'}
                ,{field: 'isHave',align:'center', title: '网约资格证',templet:function (data){//根据状态显示开关
                        return data.isHave == 1? "有":"无"
                    }}
                ,{field: 'address',align:'center', title: '地址',templet:function (data){
                        return data.proName+data.cityName+data.areaName
                    }}
                ,{field: 'note',align:'center', title: '备注'}
                ,{field: 'timeCreate',align:'center', title: '创建时间'}
                ,{field: 'timeUpdate',align:'center', title: '更新时间'}
            ]]
        });

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
            table.reload('driverQuery', {//重新加载
                where: { //设定异步数据接口的额外参数，任意设
                    driverName: $('#driverName').val()
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


    });

</script>
</body>

</html>