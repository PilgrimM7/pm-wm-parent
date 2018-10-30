<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = path + "/";
	String basePathAll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
        + request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title></title>
<link rel="stylesheet" href="plugins/layui/css/layui.css">
<script src="plugins/layui/layui.js"></script>
<script src="plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    var _BaseServerUrl = "<%=basePathAll%>";
</script>
</head>
<body>
	<div class="layui-container">
	</div>

<script>
var layer;
layui.use(['layer'], function(){
  layer = layui.layer;
});
function send() {
	$.post('func/websocket/send', {}, function() {
	});
}
setTimeout('send()', '2000');
</script>
	
<script>
var websocket;
var clientId = '${user.username}';
var _WebSocketUrl = "ws" + _BaseServerUrl.replace('http', '').replace('https', '') + "ws";

if('WebSocket' in window) {
    websocket = new WebSocket(_WebSocketUrl + "?clientId=" + clientId);
} else if('MozWebSocket' in window) {
    websocket = new MozWebSocket(_WebSocketUrl + clientId);
} else {
    websocket = new SockJS(_WebSocketUrl + "/ws/sockjs" + clientId);
}
websocket.onopen = function(event) {
    console.log("WebSocket:已连接");
    console.log(event);
};
websocket.onmessage = function(event) {
    var data = JSON.parse(event.data);
    console.log("WebSocket:收到一条消息", data);
    layer.msg(data.msg);
};
websocket.onerror = function(event) {
    console.log("WebSocket:发生错误 ");
    console.log(event);
};
websocket.onclose = function(event) {
    console.log("WebSocket:已关闭");
    console.log(event);
}
</script>
</body>
</html>