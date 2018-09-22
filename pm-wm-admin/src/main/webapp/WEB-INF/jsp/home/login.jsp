<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = path + "/";
%>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script src="plugins/jquery-1.12.4.min.js"></script>
<title>登录</title>
</head>

<body>
	<div class="layui-container">
        <input type="text" id="username" name="username">
        <input type="password" id="password" name="password">
        <input type="button" onclick="login()" value="登录">
	</div>
<script>
function login() {
	var username = $('#username').val();
	var password = $('#password').val();
	$.post('home/login', {
		username: username,
		password: password
	}, function(data, status) {
		console.log(data);
	});
}
</script>
</body>
</html>