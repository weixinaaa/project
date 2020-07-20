<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
    <script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/layui/layui.js"></script>
    <script>

    $(document).ready(function() {

    	$("#login").click(function() {
    		login();
    	});

    	$(document).keypress(function(e) {
    // 回车键事件
      		if(e.which == 13) {
   				login();
       		}
   		});
    });

    function login(){
    	layui.use('layer', function(){
    		var username = $("#username").val();
    		if(!username){
    			layer.msg('请输入用户名', {icon: 5});
    			return ;

    		}
    		var password = $("#password").val();
    		if(!password){
    			layer.msg('请输入密码', {icon: 5});
    			return ;
    		}

			$.ajax({
				type: "post",
		        url: "${pageContext.request.contextPath }/console/sysUser/login",
		        data: {
		        	username:username,
		        	password:password
		  	  },
		  	  dataType: "json",
		  	  success: function(data){
				if (data.code==0) {
					window.location.href="${pageContext.request.contextPath }/console/index";
				} else {
					layer.msg(data.msg, {icon: 2});
				}
		  	  }
		  });
    	});
    }

    </script>
    <style>
    	.logo p{
    		font-size: 40px;
		    color: #fff;
		    margin-top: 25px;
		}
    }
    </style>
</head>
<body>
<div class="login_bg">
    <div class="logo">
    	<p>Phone App管理后台</p>
    </div>
    <div class="popup login">
        <div class="login_title"><p>登录</p></div>
        <div class="login_items">
            <div class="login_input">
                <label for="username">用户名</label>
                <input type="text" id="username">
            </div>
            <div class="login_input">
                <label for="password">密码</label>
                <input type="password" id="password">
            </div>
            <button type="button" class="login_button" id="login" style="cursor: pointer;">登录</button>
            <!--<div class="login_tips">
                <p class="tips_left">没有账号？<a href="">立即注册</a></p>
                <a href="" class="tips_right">忘记密码</a>
            </div>-->
        </div>
    </div>
</div>
</body>
</html>