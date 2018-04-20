<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/include/head.jsp" />
<link href="<c:url value='/plugins/common/metronic/assets/global/css/plugins.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/plugins/bootstrap-fileinput/bootstrap-fileinput.css' />" rel="stylesheet" type="text/css" />
<script src="<c:url value='/plugins/bootstrap-fileinput/bootstrap-fileinput.js' />" type="text/javascript"></script>
<title>excel</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
	    <div class="row">
		    <div class="col-md-6">
				<div class="portlet box yellow">
				    <div class="portlet-title">
				        <div class="caption">
				            <i class="fa fa-gift"></i>Styled Tabs #2 </div>
				        <div class="tools">
				            <a href="javascript:;" class="collapse"> </a>
				            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
				        </div>
				    </div>
				    <div class="portlet-body">
				        <div class="tabbable-custom ">
				            <ul class="nav nav-tabs ">
				                <li class="active">
				                    <a href="#tab_1_1" data-toggle="tab"> Section 1 </a>
				                </li>
				                <li>
				                    <a href="#tab_1_2" data-toggle="tab"> Section 2 </a>
				                </li>
				            </ul>
				            <div class="tab-content">
				                <div class="tab-pane active" id="tab_1_1">
				                    <p> I'm in Section 1. </p>
				                    <p> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo
				                        consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat. </p>
				                </div>
				                <div class="tab-pane" id="tab_1_2">
				                    <p> Howdy, I'm in Section 2. </p>
				                    <p> Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie
				                        consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation. </p>
				                    <p>
				                        <a class="btn green" href="ui_tabs_accordions_navs.html#tab_5_2" target="_blank"> Activate this tab via URL </a>
				                    </p>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>
			</div>
			<div class="col-md-6">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-settings font-dark"></i>
                            <span class="caption-subject font-dark sbold uppercase">Horizontal Form</span>
                        </div>
                        <div class="actions">
                            <div class="btn-group btn-group-devided" data-toggle="buttons">
                                <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active">
                                    <input type="radio" name="options" class="toggle" id="option1">Actions</label>
                                <label class="btn btn-transparent dark btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">Settings</label>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form class="form-horizontal" role="form" action="func/excel/importByComment" enctype="multipart/form-data" method="post">
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Block Help</label>
                                    <div class="fileinput fileinput-new" data-provides="fileinput">
                                         <span class="btn green btn-file">
                                             <span class="fileinput-new"> Select file </span>
                                             <span class="fileinput-exists"> Change </span>
                                             <input type="file" name="filename"> </span>
                                         <span class="fileinput-filename"> </span> &nbsp;
                                         <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput"> </a>
                                     </div>
                                </div>
                            </div>
                            <div class="form-actions">
                                <div class="row">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="button" class="btn green" onclick="importExsl();">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
			</div>
		</div>
	</div>
	
<script>
function importExsl() {
	$('form').form('submit', {
        url: '/excel/importByComment',
        dataType: 'json',
        success: function() {
            
        }
    });
}
</script>
</body>
</html>