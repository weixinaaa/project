<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>后台管理系统</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
		<link rel="stylesheet" href="../css/main.css" media="all" />
	</head>

	<body class="childrenBody" onload="systemTime()">
		<div class="main_bg">
			<img src="../images/blank.png" />
			<div class="box">
				<div id="time"></div>
				<p>欢迎来到Phone App后台管理系统</p>
			</div>
		</div>
		<!--<div class="panel_box row">
		<div class="panel col">
			<a href="javascript:;" data-url="">
				<div class="panel_icon">
					<i class="layui-icon" data-icon="&#xe63a;">&#xe63a;</i>
				</div>
				<div class="panel_word newMessage">
					<span>10</span>
					<cite>新消息</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="">
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
				</div>
				<div class="panel_word userAll">
					<span>18</span>
					<cite>新增人数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="">
				<div class="panel_icon" style="background-color:#009688;">
					<i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
				</div>
				<div class="panel_word userAll">
					<span>12</span>
					<cite>用户总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="">
				<div class="panel_icon" style="background-color:#5FB878;">
					<i class="layui-icon" data-icon="&#xe64a;">&#xe64a;</i>
				</div>
				<div class="panel_word imgAll">
					<span>12</span>
					<cite>图片总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word waitNews">
					<span>35</span>
					<cite>待审核文章</cite>
				</div>
			</a>
		</div>
		<div class="panel col max_panel">
			<a href="javascript:;" data-url="">
				<div class="panel_icon" style="background-color:#2F4056;">
					<i class="iconfont icon-text" data-icon="icon-text"></i>
				</div>
				<div class="panel_word allNews">
					<span>24</span>
					<em>文章总数</em>
					<cite>文章列表</cite>
				</div>
			</a>
		</div>
	</div>
	<div class="row">
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">操作日志</blockquote>
			<div class="layui-elem-quote layui-quote-nm">
				<h3># v1.0.1（优化） - 2017-06-25</h3>
				<p>* 修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”</p>
				<p>* 优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】</p>
				<p>* “个人资料”提供修改功能</p>
				<p>* 顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】</p>
				<p>* 优化“用户列表”无法查询到新添加的用户【竟然是因为我把key值写错了，该死。。。】</p>
				<p>* 将左侧菜单做成json方式调用，而不是js调用，方便开发使用。同时添加了参数配置和非窗口模式打开的判断，【如登录页面】</p>
				<p>* 优化部分页面样式问题</p>
				<p>* 优化添加窗时如果导航不存在图标无法添加成功</p>
			</div>
		</div>
	</div>-->
		<script type="text/javascript" src="../layui/layui.js"></script>
		<script type="text/javascript" src="../js/main.js"></script>
		<script>
			//获取系统时间，将时间以指定格式显示到页面。  
			function systemTime() {
				//获取系统时间。  
				var dateTime = new Date();
				var hh = dateTime.getHours();
				var mm = dateTime.getMinutes();
				var ss = dateTime.getSeconds();
				//分秒时间是一位数字，在数字前补0。  
				mm = extra(mm);
				ss = extra(ss);
				//将时间显示到ID为time的位置，时间格式形如：19:18:02  
				document.getElementById("time").innerHTML = hh + ":" + mm + ":" + ss;
				//每隔1000ms执行方法systemTime()。  
				setTimeout("systemTime()", 1000);
			}
			//补位函数。  
			function extra(x) {
				//如果传入数字小于10，数字前补一位0。  
				if(x < 10) {
					return "0" + x;
				} else {
					return x;
				}
			}
		</script>
	</body>

</html>