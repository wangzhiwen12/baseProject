<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/coupon/member/list.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<div class="m-b-md" style="padding-left: 15px;">
	<a href="javascript:void(0)" class="btn btn-default" id="memberTop">品牌会员查询</a>
	<a href="javascript:void(0)" class="btn btn-default" id="blackTop">我的会员查询</a>
</div>
<header class="panel-heading">
	<input type="hidden" id="menuId" value="${menuId}">
	<div class="doc-buttons"></div>
	<style>
#myTagModal {
	top: 300px;
	left: 600px;
}

#myFansModal {
	top: 300px;
	left: 600px;
}
</style>
</header>
<div class="table-responsive" id="mens">
	<div class="m-b-md" style="padding-left: 15px;">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle"></span></label> <input
					class="input-medium ui-autocomplete-input" id="param">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
		</form>

	</div>
	<%--<div id="paging" class="pagclass"></div>--%>
	<div class="">
		<div class="col-md-12">
			<div class="portlet box blue-hoki" id="title">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>会员列表
					</div>
				</div>
				<div class="portlet-body" id="body">

					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="memberList">
						<thead>
							<tr role="row">
								<th>会员卡号</th>
								<th>姓名</th>
								<th>电话</th>
								<th>性别</th>
								<th>会员等级</th>
								<th>所属门店</th>
								<th>专属导购</th>
								<th>操作</th>
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
		<div class="col-md-12">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>会员列表
					</div>
				</div>
				<div class="portlet-body">
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="blackList">
						<thead>
							<tr role="row">
								<th>会员卡号</th>
								<th>姓名</th>
								<th>电话</th>
								<th>性别</th>
								<th>会员等级</th>
								<th>所属门店</th>
								<th>专属导购</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


