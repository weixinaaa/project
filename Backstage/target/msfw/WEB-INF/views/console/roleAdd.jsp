<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加角色</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css"
	media="all" />
</head>
<body>
	<form class="layui-form" id="frm">
		<div class="layui-form-item">
			<label class="layui-form-label">角色名：</label>
			<div class="layui-input-block">
				<input type="text" data-field="roleName" name="roleName" required
					lay-verify="required" placeholder="请输入角色名" autocomplete="off"
					class="layui-input" value="">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="edRole">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</body>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use('form',function(){
			var form = layui.form;
			var $ = layui.jquery;
			// 监听提交
			form.on('submit(edRole)', function(data){
				  console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
				  $.ajax({
					 type : 'POST',
					 url : '${pageContext.request.contextPath}/console/role/addRole',
					 data : data.field,
					 success : function(res){
						 layer.msg('res.msg', {
							  icon: 1,
							  time: 1500 //2秒关闭（如果不配置，默认是3秒）
							},function(){
						 		if (res.flag){
									//当你在iframe页面关闭自身时
									var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭
									parent.location.reload();
								}
							});
					 }
				  });
				  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
				});
		})
	</script>
</html>