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
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css' />" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="<c:url value='/plugins/metronic/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/morris/morris.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/fullcalendar/fullcalendar.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/plugins/jqvmap/jqvmap/jqvmap.css' />" rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="<c:url value='/plugins/metronic/assets/global/css/components.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/global/css/plugins.min.css' />" rel="stylesheet" type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="<c:url value='/plugins/metronic/assets/layouts/layout4/css/layout.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/layouts/layout4/css/themes/default.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/metronic/assets/layouts/layout4/css/custom.min.css' />" rel="stylesheet" type="text/css" />
<title>用户index</title>
<style>
body {
    height: 100%;
}

.page-container {
    height: 100%;
}

.page-content-wrapper {
    height: 100%;
}

.page-content {
    height: 100%;
}

#content_main {
    height: calc(100% - 140px);
    overflow: hidden;
}
</style>
</head>
<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo" style="overflow: hidden;">
    <!-- BEGIN HEADER -->
    <div class="page-header navbar navbar-fixed-top">
    </div>
    
    <!-- BEGIN HEADER & CONTENT DIVIDER -->
    <div class="clearfix"> </div>
    
    <!-- BEGIN CONTAINER -->
    <div class="page-container">
        <div class="page-sidebar-wrapper">
            <div class="page-sidebar navbar-collapse collapse">
                <ul class="page-sidebar-menu   " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                    <li class="nav-item start active open">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-home"></i>
                            <span class="title">Dashboard</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>
                        <ul class="sub-menu">
                            <li class="nav-item start active open">
                                <a href="user/index" target="iframe_main" class="nav-link ">
                                    <i class="icon-bar-chart"></i>
                                    <span class="title">Dashboard 1</span>
                                    <span class="selected"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="page-content-wrapper">
            <div class="page-content">
	            <div id="content_main">
                    <iframe id="iframe_main" name="iframe_main" style="width: 100%; height: 100%; border-style: none;" src="user/index"></iframe>
	            </div>
            </div>
        </div>
    </div>
    
    <!-- BEGIN FOOTER -->
    <div class="page-footer">
    </div>

<!-- BEGIN CORE PLUGINS -->
<script src="<c:url value='/plugins/metronic/assets/global/plugins/jquery.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/global/plugins/js.cookie.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/global/plugins/jquery.blockui.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js' />" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="<c:url value='/plugins/metronic/assets/global/scripts/app.min.js' />" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="<c:url value='/plugins/metronic/assets/layouts/layout2/scripts/layout.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/layouts/layout2/scripts/demo.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/layouts/global/scripts/quick-sidebar.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/plugins/metronic/assets/layouts/global/scripts/quick-nav.min.js' />" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<script type="text/javascript">
</script>
</body>
</html>