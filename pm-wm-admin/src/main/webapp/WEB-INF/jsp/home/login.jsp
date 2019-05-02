<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/include/head.jsp" />
<link href="plugins/hplus/css/font-awesome.min.css" rel="stylesheet">
<link href="plugins/hplus/css/style.min.css" rel="stylesheet">
<script src="plugins/rsa/jsbn.js"></script>
<script src="plugins/rsa/prng4.js"></script>
<script src="plugins/rsa/rng.js"></script>
<script src="plugins/rsa/rsa.js"></script>
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
	
	        <form class="m-t" role="form" action="index.html" autocomplete="off">
	            <div class="form-group">
	                <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required="">
	            </div>
	            <div class="form-group">
	                <input type="password" id="password" name="password" class="form-control" placeholder="密码" required="">
	            </div>
	            <div class="form-group">
	                <input type="text" id="code" name="code" class="form-control" required="" style="width: 60%;">
                    <img id="codeImg" alt="验证码" onclick="refreshCode()" 
                        style="float: right; cursor: pointer;" />
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
$(function() {
	refreshCode();
});

function login() {
	var username = $('#username').val();
	var password = $('#password').val();
	var code = $('#code').val();
	
	var rsa = new RSAKey();
    var modulus = "8b423ccb821d0a54a7f03cb8bb1db274efd7d284856b6fe056092b73d3c70abfbae2ecf9c727ccf60ea4b86e3a026dee3842e1dfc5a333daa7c736dbb0761a7f41b34398f974333b4ab3c3e224d19340e21a83328e745c383a73fd9806f44a34171f875992c3b74e6aaa527bcf156332d36c70c7f1457221d288ab3533597a03";
    var exponent = "10001";
    rsa.setPublic(modulus, exponent);
    password = rsa.encrypt(password);
	
	$.post('home/login', {
		username: username,
		password: password,
		code: code
	}, function(data, status) {
		if (data.errcode == '0') {
			document.location.href = 'home/index';
		} else {
			alert(data.errmsg);
			refreshCode();
		}
	});
}

function refreshCode() {
    $("#code").val("");
    var codeImg = $("#codeImg");
    codeImg.attr('src', 'home/getCode' + '?_=' + new Date().getTime());
}
</script>
</body>
</html>