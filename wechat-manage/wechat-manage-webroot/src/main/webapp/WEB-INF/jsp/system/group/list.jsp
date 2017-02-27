<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/group/list.js"></script>

	<div class="m-b-md" style="padding-left: 15px;">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">集团编码:</span></label> <input
				class="input-medium ui-autocomplete-input" id="organizationCode"
				name="groupFormMap.organizationCode">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<%--<a href="javascript:void(0)" class="btn btn-warning" id="callback_test">测试表格回调函数</a>--%>
			<%--<a href="javascript:exportExcel('/user/export.shtml')" class="btn btn-info" id="search">导出excel</a>--%>
		</form>
	</div>
	<header class="panel-heading">
		<input type="hidden" id="menuId" value="${menuId}">
	<div class="doc-buttons">
	</div>
	</header>
	<div class="table-responsive">
		<%--<div id="paging" class="pagclass"></div>--%>
		<div class="">
			<div class="col-md-12">
				<div class="portlet box blue-hoki">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>集团列表
						</div>
						<div class="actions" id="btnAdd">
							<c:forEach items="${res}" var="key">
								${key.description}
							</c:forEach>
						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable no-footer"
							   id="userList">
							<thead>
							<tr role="row">
								<th></th>
								<th>集团id</th>
								<th>集团名称</th>
								<th>集团编码</th>
								<th>集团状态</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>修改人</th>
								<th>修改时间</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="callback_div" class="table-responsive" style="display: none;">
		<div id="paging_callback" class="pagclass"></div>
	</div>
