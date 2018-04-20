<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<title>JqGrid</title>
<script src="<c:url value='/plugins/jquery-1.12.4.min.js' />"></script>
<script src="<c:url value='/plugins/bootstrap-table/bootstrap-table.min.js' />"></script>
<script src="<c:url value='/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js' />"></script>
<script src="<c:url value='/plugins/bootstrap/js/bootstrap.min.js' />"></script>
<link rel="stylesheet" href="<c:url value='/plugins/bootstrap/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/plugins/bootstrap-table/bootstrap-table.min.css' />">
</head>
<body>
	<table id="table"></table>

<script type="text/javascript"> 
$('#table').bootstrapTable({
    url: 'user/list',
    columns: [{
        field: 'name',
        title: '姓名'
    }, {
        field: 'age',
        title: '年龄'
    }, ],
    striped: true,
    pagination: true,
    sidePagination: 'server'
});
</script>
</body>
</html>