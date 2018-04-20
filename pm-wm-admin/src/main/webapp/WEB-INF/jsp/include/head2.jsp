<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="plugins/metronic/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="plugins/metronic/assets/global/css/components.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="plugins/metronic/assets/layouts/layout4/css/layout.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/layouts/layout4/css/themes/default.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/metronic/assets/layouts/layout4/css/custom.min.css" rel="stylesheet" type="text/css" />
<!-- hplus -->
<link href="plugins/hplus/css/style.min.css?v=4.0.0" rel="stylesheet">
<!-- BEGIN CORE PLUGINS -->
<script src="plugins/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="plugins/metronic/assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="plugins/metronic/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<script src="plugins/metronic/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->