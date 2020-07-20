<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改车辆</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css"
	media="all" />
</head>
<body>
<form class="layui-form" id="frm">
	<div class="layui-form-item" hidden="" >
		<label class="layui-form-label">ID</label>
		<div class="layui-input-inline">
			<input type="text" name="id" required lay-verify="required"
				   placeholder="" autocomplete="off" value="${carRental.id }" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">车名：</label>
			<div class="layui-input-block">
				<input type="text" data-field="carName" id="carName" name="carName" placeholder="请输入车名" class="layui-input" value="${carRental.carName}">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">车型：</label>
			<div class="layui-input-block">
				<select id="carTypeid" name="carTypeId" lay-filter="carTypeid">
					<option value="${carRental.carTypeId}">请选择车型</option>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">发动机机型：</label>
			<div class="layui-input-block">
				<input type="text" data-field="engineType" name="engineType" placeholder="请输入发动机机型" class="layui-input"
					   id="engineType" value="${carRental.engineType}" style="width: 180px;">
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">省：</label>
			<div class="layui-input-block">
				<select id="proid" name="proId" lay-filter="proid">
					<option value="${carRental.proId}">请选择省份</option>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">市：</label>
			<div class="layui-input-block">
				<select id="cityid" name="cityId" lay-filter="cityid">
					<option value="${carRental.cityId}">请选择城市</option>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">区：</label>
			<div class="layui-input-block">
				<select id="areaid" name="areaId" lay-filter="areaid">
					<option value="${carRental.areaId}">请选择地区</option>
				</select>
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">车档：</label>
				<div class="layui-input-block w300">
					<select class="layui-input w300" id="gearType" name="gearType">
						<option value="2">其他</option>>
						<option value="0" <c:if test="${carRental.gearType==0}">selected</c:if>>手动挡</option>
						<option value="1" <c:if test="${carRental.gearType==1}">selected</c:if>>自动挡</option>
					</select>
				</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">车座数量：</label>
			<div class="layui-input-block">
				<input type="text" data-field="seatNum" id="seatNum" name="seatNum" placeholder="请输入车型" class="layui-input" value="${carRental.seatNum}">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">日租价格：</label>
			<div class="layui-input-block">
				<input type="text" data-field="dailyRentPrice" id="dailyRentPrice" name="dailyRentPrice" placeholder="请输入日租价格" class="layui-input" value="${carRental.dailyRentPrice}">
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline" style="float:left;margin-top:20px">
			<label class="layui-form-label">图片</label>
			<div class="layui-input-block">
				<div class="layui-upload">
					<button type="button" class="layui-btn layui-btn-normal" id="test1">
						<i class="layui-icon">&#xe67c;</i>选择图片
					</button>
					<button type="button" class="layui-btn" id="test9">开始上传</button>
				</div>
				<input type="text" name="img" id="img" hidden="" value="${carRental.img}">
				<div class="layui-upload-list">
					<img class="layui-upload-img" src="${carRental.img}" id="ioimg" width="160" height="100">
				</div>
				<p id="demoText"></p>
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="edit">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
</body>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>

	layui.use(['upload','form'],function(){
		var upload = layui.upload;
		var $ = layui.jquery;
		//图片上传
		var uploadInst = upload.render({
			elem:'#test1',
			url:'../../file/upload',
			auto: false,//选完文件后不自动上传
			//multiple: true,
			bindAction: '#test9',
			choose: function(obj){
				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result){
					$('#ioimg').attr('src', result); //图片链接（base64）#demo1:id名
				})
			}
			,done: function(res, index, upload){
				layer.msg(res.msg,{
					icon:1,
					time:500
				})
				$('#img').attr("value", res.data);
			}
		});
		var form = layui.form;

		//获取车型
		$.ajax({
			type:'post'
			,url:'${pageContext.request.contextPath}/console/carType/selCarType'
			,data:{}
			,success : function(res){
				console.log(res);
				var html = '';
				var carTypeid =${carRental.carTypeId};
				for (let carType of res.rows) {
					if (carType.id == carTypeid){
						html += '<option value="'+carType.id+'" selected> '+carType.carType+'</option>'
					}else {
						html += '<option value="'+carType.id+'">'+carType.carType+'</option>'
					}
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
				var proid = ${carRental.proId};
				for (let pro of res.rows) {
					if (pro.id==proid){
						html += '<option value="'+pro.id+'" selected> '+pro.proName+'</option>'
					}else {
						html += '<option value="'+pro.id+'">'+pro.proName+'</option>'
					}
				}
				$('#proid').append(html);//将html语句追加到select框里
				form.render('select');
			}
		})

		//获取市
		$('#cityid').html('<option value="0">请选择城市</option>')
		$.ajax({
			type:'post'
			,url:'${pageContext.request.contextPath}/pca/selCity'
			,data:{pid : ${carRental.proId}}
			,success :function(res){
				var html = '';
				var cityid = ${carRental.cityId};
				for (let city of res.rows) {
					if(city.id == cityid){
						html += '<option value="'+city.id+'" selected>'+city.cityName+'</option>'
					}else{
						html += '<option value="'+city.id+'">'+city.cityName+'</option>'
					}
				}
				$('#cityid').append(html);
				form.render('select');
			}
		})
		//获取市
		form.on('select(proid)', function(data){
			$('#cityid').html('<option value="0">请选择城市</option>')
			$('#areaid').html('<option value="0">请选择区域市</option>')
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
		});

		//获取区域
		$.ajax({
			type:'post'
			,url:'${pageContext.request.contextPath}/pca/selArea'
			,data:{pid : ${carRental.cityId}}
			,success :function(res){//res数据接口的返回值
				var html = '';
				var areaid = ${carRental.areaId};
				for (let area of res.rows) {
					if (area.id==areaid){
						html += '<option value="'+area.id+'" selected> '+area.areaName+'</option>'
					}else {
						html += '<option value="'+area.id+'">'+area.areaName+'</option>'
					}
				}
				$('#areaid').append(html);
				form.render('select');
			}
		})
		//获取区域
		form.on('select(cityid)', function(data){
			$('#areaid').html('<option value="0">请选择区/县</option>')
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
		});


		//监听提交
		form.on('submit(edit)', function(data){
			$.ajax({
				type:'post',//数据请求接口的方式
				url: '${pageContext.request.contextPath}/console/carRental/editCarRental'//	数据接口
				,data : data.field
				,success : function(res){
					layer.msg(res.msg, {
						icon: 1,
						time: 1500 //2秒关闭（如果不配置，默认是3秒）
					},function(){
						//当你在iframe页面关闭自身时
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
						parent.location.reload();
					});
				}
			})
			return false; //	阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});

	})
</script>
</html>