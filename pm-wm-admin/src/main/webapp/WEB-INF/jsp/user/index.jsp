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
<script src="<c:url value='/plugins/jquery-1.12.4.min.js' />"></script>
<script src="<c:url value='/plugins/bootstrap/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/plugins/bootstrap-table/bootstrap-table.min.js' />"></script>
<script src="<c:url value='/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js' />"></script>
<link rel="stylesheet" href="<c:url value='/plugins/bootstrap/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/plugins/bootstrap-table/bootstrap-table.min.css' />">
<!-- hplus -->
<link href="<c:url value='/plugins/hplus/css/animate.min.css' />" rel="stylesheet">
<link href="<c:url value='/plugins/hplus/css/style.min.css?v=4.0.0' />" rel="stylesheet">
<title>用户index</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <form role="form" class="form-inline">
	        <div class="form-group">
	            <label class="control-label">姓名</label>
	            <input type="text" id="name" name="name" class="form-control">
	        </div>
	        <div class="form-group">
                <button type="button" class="btn btn-primary" onclick="queryAll();">重置</button>
                <button type="button" class="btn btn-primary" onclick="query();">查询</button>
	        </div>
        </form>
        <table id="table" data-toolbar="#toolbar"></table>
    </div>

<script type="text/javascript">

var $table = $('#table');
$table.bootstrapTable({
    url: 'user/queryForPage',
    sidePagination: 'server',
    queryParamsType: '',
    queryParams: function(params) {
    	return params;
    },
    columns: [{
        field: 'id',
        title: 'Item ID'
    }, {
        field: 'name',
        title: '姓名'
    }, {
        field: 'age',
        title: '年龄'
    } ],
//     height: document.body.clientHeight - 74,
    striped: true,
    pagination: true,
    pageList: [5, 10, 25, 50, 100]
});

function query() {
	var para = {
	    pageNumber: 1,
		name: $('#name').val()
	};
	$table.bootstrapTable('refresh', {query: para});
}

function queryAll() {
	$('#name').val('');
	query();
}
</script>
</body>
</html>