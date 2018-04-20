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
<link rel="stylesheet" href="<c:url value='/plugins/adminlte/css/AdminLTE.min.css' />">
<title>用户index</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header">
    </header>
    
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <section class="sidebar">
            <ul class="sidebar-menu" data-widget="tree">
		        <li class="header">MAIN NAVIGATION</li>
		        <li class="active treeview">
		        <a href="#">
		            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
		            <span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
		          </a>
		          <ul class="treeview-menu">
		            <li class="active"><a href="user/index"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
		          </ul>
		        </li>
            </ul>
        </section>
    </aside>
    
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
        </section>
        <section class="content">
        </section>
    </div>
    
    <footer class="main-footer">
    </footer>
</div>

<script type="text/javascript"> 
</script>
</body>
</html>