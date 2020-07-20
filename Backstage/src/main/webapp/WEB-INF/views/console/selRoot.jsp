<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限设置</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css"
	media="all">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
	<form class="layui-form" id="frm">
		<div id="test1"></div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="edRole">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</body>
  <script>
  layui.use(['tree','form'], function(){
    var tree = layui.tree;
   var form = layui.form;
   var $ = layui.jquery;
   // 显示树形组件
    $.ajax({
    	type : 'POST',
    	url : '${pageContext.request.contextPath}/menu/selroot.action',
    	data : {roleId : ${roleid}},
    	success : function(res){
    		 //渲染
    	    var inst1 = tree.render({
    	      elem: '#test1'  //绑定元素
    	    	,id: 'test1'
    	    	,showCheckbox : true
    	      ,data: res
    	    });
    	}
    });
 // 监听提交
	form.on('submit(edRole)', function(data){
		//获得选中的节点
		var checkData = tree.getChecked('test1');
		// 处理给接口传递的参数
		var menuids = [];
		for (let menu of checkData) {
			menuids.push(menu.id)
			for (let children of menu.children) {
				menuids.push(children.id)
			}
		}
		// 发送请求
		$.ajax({
			 type : 'POST',
			 url : '${pageContext.request.contextPath}/menu/editroot.action',
			 traditional:true,
			 data : {roleId : ${roleid} , menuIds : menuids},
			 success : function(){
				 layer.msg('修改成功', {
					  icon: 1,
					  time: 1500 //2秒关闭（如果不配置，默认是3秒）
					},function(){
						//当你在iframe页面关闭自身时
						 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						 parent.layer.close(index); //再执行关闭   
						 top.location.reload(); // 刷新顶层页面
					}); 
			 }
		  });
		  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
  });
  </script>
</html>