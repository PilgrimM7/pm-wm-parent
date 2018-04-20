<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<link href="plugins/common/metronic/assets/global/css/fonts.css" rel="stylesheet" type="text/css" />
<link href="plugins/common/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/common/metronic/assets/global/css/components.min.css" rel="stylesheet" type="text/css" />
<link href="plugins/common/css/style.css" rel="stylesheet" type="text/css">
<script src="plugins/jquery-1.12.4.min.js" type="text/javascript"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="plugins/common/metronic/assets/global/scripts/app.min.js" type="text/javascript"></script>