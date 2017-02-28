<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wechat/fans/list.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/wechat/fans/js/bootstrap.min.js"></script>
<div class="m-b-md" style="padding-left: 15px;">
	<a href="javascript:void(0)" class="btn btn-default" id="reloadTop">已关注</a>
	<a href="javascript:void(0)" class="btn btn-default" id="blackTop">黑名单</a>
</div>
<header class="panel-heading">
	<input type="hidden" id="menuId" value="${menuId}">
	<div class="doc-buttons"></div>
<style>
    #myTagModal
    {
        top:300px;
        left:600px;
    }
    #myFansModal
    {
        top:300px;
        left:600px;
    }
</style>
</header>
<div class="table-responsive" id="fans">
	<div class="m-b-md" style="padding-left: 15px;">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">用户昵称:</span></label> <input
					class="input-medium ui-autocomplete-input" id="accountName"
					name="userFormMap.accountName">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
		</form>

	</div>
	<%--<div id="paging" class="pagclass"></div>--%>
	<div class="">
		<div class="col-md-8">
			<div class="portlet box blue-hoki" id="title">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>粉丝列表
					</div>
					<div class="actions" id="btnAdd">
						<c:forEach items="${res}" var="key">
								${key.description}
							</c:forEach>
					</div>
				</div>
				<div class="portlet-body" id="body">

					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="fansList">
						<thead>
							<tr role="row">
								<th></th>
								<th>用户</th>
								<th>标签</th>
								<th>修改备注</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>粉丝标签管理
					</div>
				</div>
				<div class="portlet-body">
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="tagList">
						<thead>
							<tr role="row">
								<th>id</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="table-responsive" id="black" style="display: none">
	<%--<div id="paging" class="pagclass"></div>--%>
	<div class="">
		<div class="col-md-8">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>黑名单列表
					</div>
					<div align="right">
						<a href="javascript:void(0)" class="btn btn-default"
							id="removeBlack">移出黑名单</a>
					</div>
				</div>
				<div class="portlet-body">
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="blackList">
						<thead>
							<tr role="row">
								<th></th>
								<th>用户</th>
								<th>修改备注</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="myTagModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:380px;">
		<div class="modal-content">
			<div class="modal-body">
				<div>
				<h4 class="popover_title">标签名称</h4>
				<span>
				<input  type="text" data-provide="typeahead"  id="TagInput" >
				</span>
				</div>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="reName()">
					确认
				</button>
				<button type="button" class="btn btn-default" onclick="modalClose()">关闭
				</button>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="modal fade" id="myFansModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:380px;">
		<div class="modal-content">
			<div class="modal-body">
				<div>
				<h4 class="popover_title">用户备注名称</h4>
				<span>
				 <input  type="text" id="FansInput" > 
				 <input  type="text" id="openid"  style="display:none"> 
				</span>
				</div>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="reMarks()">
					确认
				</button>
				<button type="button" class="btn btn-default" onclick="modalClose()">关闭
				</button>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
