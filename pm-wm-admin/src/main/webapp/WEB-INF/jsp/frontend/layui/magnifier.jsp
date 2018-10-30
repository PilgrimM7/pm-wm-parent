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
<title>开始使用Layui</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css">
</head>
<body>
	<div class="layui-container">
		<h2>放大镜模块 - 方块</h2>
		<div class="layui-col-xs3 layui-col-sm12 layui-col-md3">
			<img id="lnk_thumb" src="img/shu.jpg" width="200" height="200" />
		</div>
		<div class="layui-col-xs4 layui-col-sm12 layui-col-md4">
			<div class="magnifier-preview" id="gallery-preview"
				style="width: 200px; height: 200px;"></div>
		</div>
		<!--预览图容器-->
	</div>
	<script src="plugins/layui/layui.js"></script>
	<script>
		layui.config({
			base : 'plugins/magnifier/' //假设这是你存放拓展模块的根目录
		}).extend({
			Magnifier : 'js/magnifier',
			Event : 'js/event' //你存放插件的目录
		});
		layui.use([ 'form', 'Event', 'Magnifier' ], function() {
			var $ = layui.jquery, 
			    evt = new layui.Event(), 
			    Magnifier = new layui.Magnifier(evt, {
			        largeWrapper : document.getElementById('gallery-preview'), // 预览图容器   
			    });
			Magnifier.attach({
				thumb : '#lnk_thumb',
				zoom : 6
			});
		});
	</script>
</body>
</html>