<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>Excel导入_jsp</title>
	<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/plugins/easyui/jquery.easyui.min.js' />"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/easyui/themes/default/easyui.css' />">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<a href="javascript:importExsl();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">提交</a>
		<form id="form" action="/jadk/servlet" enctype="multipart/form-data" method="post">
			<input type="file" name="filename"/>
		</form>
	</div>
	
<script type="text/javascript">
	function importExsl() {
		$('#form').submit();
	}
</script>
</body>
</html>