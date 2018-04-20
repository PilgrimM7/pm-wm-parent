<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>开始使用select2</title>
  <link href="../../plugins/select2/css/select2.min.css" rel="stylesheet" />
</head>
<body>
 
<select class="select2" style="width: 200px;" name="state">
  <option value="AL">Alabama</option>
  <option value="WY">Wyoming</option>
</select>

<select class="js-example-basic-multiple" style="width: 200px;" name="states[]" multiple="multiple">
    <option value="AL">Alabama</option>
    ...
    <option value="WY">Wyoming</option>
</select>

<script src="../../plugins/jquery-1.12.4.min.js"></script>
<script src="../../plugins/select2/js/select2.min.js"></script>
<script>
//In your Javascript (external .js resource or <script> tag)
$(document).ready(function() {
    $('.select2').select2();
    
    $('.js-example-basic-multiple').select2();
    
    $(".js-example-basic-multiple").val(['AL']).trigger("change");//设置多个值选中
    
});
</script> 
</body>
</html>