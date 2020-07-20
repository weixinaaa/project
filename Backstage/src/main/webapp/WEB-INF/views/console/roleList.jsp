<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css"
	media="all">
</head>
<body>
	<div class="layui-form layui-card-header layuiadmin-card-header-auto">
		<div class="test-table-reload-btn" style="margin-bottom: 10px;">
			<div class="layui-inline">
				<input class="layui-input roleName" placeholder="角色名称"
					name="roleName" id="test-table-demoReload" autocomplete="off">
			</div>
			<button id="search_btn" class="layui-btn" data-type="reload">搜索</button>
			<button id="add" class="layui-btn" data-type="add">添加</button>
			<button id="del" class="layui-btn" data-type="del">删除</button>
		</div>
	</div>
	<table id="role" lay-filter="test"></table>

	<script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-xs" lay-event="seltool">权限设置</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>         
    </script>
	
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script>
		layui.use('table', function(){
		  var table = layui.table;
		  var $ = layui.jquery;
		  //第一个实例
		  table.render({
		    elem: '#role'
		    ,url: '${pageContext.request.contextPath}/console/role/selRole' //数据接口
		    ,page: true //开启分页
		    ,cols: [[ //表头
		    	{type: 'checkbox', fixed: 'left'}
		      ,{field: 'id', title: 'ID',align:'center', sort: true, fixed: 'left'}
		      ,{field: 'roleName',align:'center', title: '角色名'}
			  ,{field: 'time',align:'center', title: '创建时间'}
		      ,{fixed: 'right', title: '操作框',  align:'center', toolbar: '#barDemo'}
		    ]]
		  });
		//搜索
		$('#search_btn').click(function(){
			 table.reload('role', {
				    where: { //设定异步数据接口的额外参数，任意设
				      roleName : $('.roleName').val()
				      //…
				    }
				    ,page: {
				      curr: 1 //重新从第 1 页开始
				    }
				  }); //只重载数据
			});
		// 弹出添加窗口
		$('#add').click(function(){
			layer.open({
		        type: 2 // iframe层
		        ,title: '添加角色' // 标题
		        ,area: ['500px', '300px'] // 宽高
				,anim: 6 // 抖动
				,resize : false // 不允许拉伸
		        ,content: '${pageContext.request.contextPath}/console/roleAdd' // 内容
		        ,shade: 0 //不显示遮罩
		      });
		});
		// 删除角色
		$('#del').click(function(){
			var checkStatus = table.checkStatus('role')
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
					for (let role of data) {
						ids.push(role.id) ;
					}
					// 发送请求
					$.ajax({
						type : 'POST',
						url : '${pageContext.request.contextPath}/console/role/delRole',
						traditional: true,
						data : {id : ids},
						success : function(){
							layer.msg('删除成功', {
								icon: 1,
								time: 1500 //2秒关闭（如果不配置，默认是3秒）
							});
						}
					});
				  table.reload('role');
				  layer.close(index);
				});
			}
		});
		//监听工具条 
		table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		 
		  if(layEvent === 'del'){ //删除
		    layer.confirm('真的删除该角色么', function(index){
		    	$.post('${pageContext.request.contextPath}/console/role/delRole',
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
			  console.log(data)
			  layer.open({
			        type: 2 // iframe层
			        ,title: '修改角色' // 标题
			        ,area: ['500px', '300px'] // 宽高
					,anim: 6 // 抖动
					,resize : false // 不允许拉伸
			        ,content: '${pageContext.request.contextPath}/console/role/toEdit?id='+data.id // 内容
			        ,shade: 0 //不显示遮罩
			      });
		  } else if(layEvent === 'seltool'){ //权限设置
			  layer.open({
			        type: 2 // iframe层
			        ,title: '权限设置' // 标题
			        ,area: ['500px', '300px'] // 宽高
					,anim: 6 // 抖动
					,resize : false // 不允许拉伸
			        ,content: '${pageContext.request.contextPath}/menu/toroot.action?roleId='+data.id
			        ,shade: 0 //不显示遮罩
			      });
		  }
		});
	});
	</script>
</body>
</html>