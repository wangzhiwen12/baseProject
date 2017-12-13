<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bass.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wfj-global.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/components.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-switch.css">
<style>
.divcss5 {
	margin-left: 12px;
	width: 98%;
}
.divcss4 {
	margin-left: 15px;
}

.ui-grid-cards-item {
	float: left;
	padding: 10px;
	width: 33.3333%;
}

.col-md-3 {
    width: 2%;
}


</style>
<div class="table-responsive">
	<div class="">
		<div class="col-md-12">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>微信模板消息管理
					</div>
				</div>
				<div class="portlet-body" id="div_body">
					
				</div>
			</div>
		</div>
	</div>
</div>
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config11" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				<div class="modal-dialog">
					<div class="modal-content" style="left:200px;">
						<div class="modal-header" >
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">修改模板ID</h4>
						</div>
						<div class="modal-body">
							 <input type="text"  class="form-control input-medium" size="16" id="storeTemplateId"/>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal" id="save">保存</button>
							<button type="button" class="btn default" data-dismiss="modal" id="target" >取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!--参数表单，修改用-->
                        <form id="data_form" action="">
                            <input type="hidden" id="wxTemplateNo_form" name="wxTemplateNo"/>
                         	<input type="hidden" id="storeTemplateId_form" name="storeTemplateId"/>
                         	<input type="hidden" id="infoSid_form" name="infoSid"/>
                       </form>
                       
<script
	src="${pageContext.request.contextPath}/js/wechat/fans/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wechat/wechatTemplate/list.js"></script>
<script
	src="${pageContext.request.contextPath}/js/wechat/fans/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/wechat/fans/bootstrap-switch.min.js"></script>