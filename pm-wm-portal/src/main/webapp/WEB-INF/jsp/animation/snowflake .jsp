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
<title>雪花</title>
<script src="<c:url value='/plugins/jquery-1.12.4.min.js' />"></script>
<style type="text/css">
    body {
        background: black;
        height: 100%;
        overflow: hidden;
    }
    .xh {
        cursor: pointer;
    }
</style>
</head>
<body>
	<div class="bk"></div>

<script type="text/javascript">
var minSize = 5; //最小字体
var maxSize = 50;//最大字体
var newOne = 200; //生成雪花间隔
var flakColor = "#fff"; //雪花颜色
var flak = $("<div class='xh'></div>").css({position:"absolute","top":"0px"}).html("❀");//定义一个雪花
var dhight = $(window).height(); //定义视图高度
var dw =$(window).width(); //定义视图宽度
setInterval(function(){
    var sizeflak = minSize+Math.random()*maxSize; //产生大小不等的雪花
    var startLeft = Math.random()*dw;  //雪花生成是随机的left值
    var startopcity = 0.7+Math.random()*0.3;  //随机透明度
    var endpositionTop= dhight-100;  //雪花停止top的位置
    var endLeft= Math.random()*dw; //雪花停止的left位置
    var durationfull = 5000+Math.random()*3000; //雪花飘落速度不同
    
    flak.clone().appendTo($("body")).css({
        "left":startLeft ,
        "opacity":startopcity,
        "font-size":sizeflak,
        "color":flakColor
    }).animate({
        "top":endpositionTop,
        "left":endLeft,
        "apacity":0.1
    },durationfull,function(){
        $(this).remove()
    });
},newOne);
</script>
</body>
</html>