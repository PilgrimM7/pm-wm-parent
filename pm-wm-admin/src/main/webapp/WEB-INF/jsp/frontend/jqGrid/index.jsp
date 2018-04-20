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
<script src="<c:url value='/plugins/jqgrid/js/jquery.jqGrid.min.js' />"></script>
<script src="<c:url value='/plugins/jqgrid/js/grid.locale-cn.js' />"></script>
<script src="<c:url value='/plugins/bootstrap/js/bootstrap.min.js' />"></script>
<link rel="stylesheet" href="<c:url value='/plugins/bootstrap/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/plugins/jqgrid/css/ui.jqgrid-bootstrap.css' />">
</head>
<body>
	<table id="grid1"></table>
	<div id="pager1"></div>

<script type="text/javascript"> 
	$("#grid1").jqGrid({
// 	   	url: 'frontend/jqGrid/list',
	   	url: 'data/data2.json',
		datatype: "json",
		styleUI: 'Bootstrap', // 设置jqgrid的全局样式为bootstrap样式
	   	colModel:[
	   		{ label: 'Category Name', name: 'CategoryName', width: 75 },
			{ label: 'Product Name', name: 'ProductName', width: 90 },
			{ label: 'Country', name: 'Country', width: 100 },
			{ label: 'Price', name: 'Price', width: 80, sorttype: 'integer' },
			// sorttype is used only if the data is loaded locally or loadonce is set to true
			{ label: 'Quantity', name: 'Quantity', width: 80, sorttype: 'number' }		
	   	],
	   	rowNum: 10,
	   	rowList: [10,20,30],
	   	rownumbers: true,
	    viewrecords: true,
	    caption:"JSON Example",
	    pager: "#pager1"
	});
</script>
</body>
</html>