<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/coupon/member/electronic_coupons.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(function() {
	});
</script>
<head>
<style>
</style>
</head>
<body>
	<table style="display: inline; padding-left: 18%;">
		<tr>
			<td><span style="font-size: 28px;">已使用数量</span></td>
		</tr>
		<tr>
			<td align="center"><span style="font-size: 28px;"> <c:if
						test="${not empty coupons.used}">
					${coupons.used}
				</c:if>
			</span></td>
		</tr>
	</table>
	<table style="display: inline; padding-left: 10%;">
		<tr>
			<td><span style="font-size: 28px;">未使用数量</span></td>
		</tr>
		<tr>
			<td align="center"><span style="font-size: 28px;"> <c:if
						test="${not empty coupons.notUseNum}">
									${coupons.notUseNum}
				</c:if>
			</span></td>
		</tr>
	</table>
	<table style="display: inline; padding-left: 10%;">
		<tr>
			<td><span style="font-size: 25px;">已过期</span></td>
		</tr>
		<tr>
			<td align="center"><span style="font-size: 23px;"> <c:if
						test="${not empty coupons.overdue}">
					${coupons.overdue}
				</c:if>
			</span></td>
		</tr>
	</table>

	<div class="table-responsive" id="fans">
		<div class="">
			<div class="col-md-10">
				<div class="portlet box blue-hoki" id="title">
					<div class="portlet-body" id="body">
						<table
							class="table table-striped table-bordered table-hover dataTable no-footer"
							id="couponsList">
							<thead>
								<tr role="row">
									<th>类型</th>
									<th>券ID</th>
									<th>名称</th>
									<th>状态</th>
									<th>有效期</th>
									<th>领取日期</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${coupons.coupons}" var="coupon"
									varStatus="status">
									<tr>
										<td><c:if test="${coupon.cardtype=='GROUPON'}">
										团购券
										</c:if> <c:if test="${coupon.cardtype=='CASH'}">
										代金券
										</c:if> <c:if test="${coupon.cardtype=='DISCOUNT'}">
										折扣券
										</c:if> <c:if test="${coupon.cardtype=='GIFT'}">
										兑换券
										</c:if> <c:if test="${coupon.cardtype=='GENERAL_COUPON'}">
										优惠券
										</c:if></td>
										<td>${coupon.cardId}</td>
										<td>${coupon.title}</td>
										<td><c:if test="${coupon.cardStatus=='0'}">
										 未使用 
										</c:if> <c:if test="${coupon.cardStatus=='1'}">
										已使用 
										</c:if> <c:if test="${coupon.cardStatus=='2'}">
										已过期
										</c:if></td>
										<td>${coupon.endTime}</td>
										<td>${coupon.collectionTime}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
