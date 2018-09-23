<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String _BasePath = path + "/";
%>
<base href="<%=_BasePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugins/hplus/css/font-awesome.min.css" rel="stylesheet">
<link href="plugins/hplus/css/style.min.css" rel="stylesheet">
<script src="plugins/jquery-1.12.4.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<meta http-equiv="refresh" content="0;ie.html"/><![endif]-->
<script>
if (window.top !== window.self) {
    window.top.location = window.location;
}
</script>
<title>登录</title>
</head>

<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
	    <div>
	        <div>
	            <h1 class="logo-name">H+</h1>
	        </div>
	        <h3>欢迎使用 H+</h3>
	
	        <form class="m-t" role="form" action="index.html">
	            <div class="form-group">
	                <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required="">
	            </div>
	            <div class="form-group">
	                <input type="password" id="password" name="password" class="form-control" placeholder="密码" required="">
	            </div>
	            <input type="button" onclick="login()" value="登录" class="btn btn-primary block full-width m-b">
	            <!-- 
	            <p class="text-muted text-center">
	                <a href="login.html#">
	                    <small>忘记密码了？</small>
	                </a>
	                |
	                <a href="register.html">注册一个新账号</a>
	            </p>
	             -->
	        </form>
	    </div>
	</div>
<script>
function login() {
	var username = $('#username').val();
	var password = $('#password').val();
	$.post('home/login', {
		username: username,
		password: password
	}, function(data, status) {
		if (data.errcode == '0') {
			document.location.href = 'home/index';
		} else {
			alert(data.errmsg);
		}
	});
}
</script>
</body>
</html>