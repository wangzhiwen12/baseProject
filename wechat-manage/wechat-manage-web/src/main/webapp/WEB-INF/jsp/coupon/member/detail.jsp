
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@include file="/common/common.jspf"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/member/js/bootstrap-3.3.5-dist/css/bootstrap.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#basic_information" data-toggle="tab">基础信息</a></li>
		<li><a href="#electronic_coupons" data-toggle="tab">电子优惠券</a></li>
		<li><a href="#transaction_history" data-toggle="tab">交易历史</a></li>
		<li><a href="#integral_history" data-toggle="tab">积分历史</a></li>
		<li><a href="#communication_history" data-toggle="tab">沟通历史</a></li>
		<li><a href="#combined_card_history" data-toggle="tab">合卡记录</a></li>
		<li><a href="#purchased_goods" data-toggle="tab">购买的商品</a></li>
		<li><a href="#collection_of_goods" data-toggle="tab">收藏的商品</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="basic_information">
			<div class="form-group" id="basic_informationUI"
				data-url="/member/basic_informationUI.shtml?openid=${openid }">
			</div>
		</div>
		<div class="tab-pane" id="electronic_coupons">
			<div class="form-group" id="electronic_couponsUI"
				data-url="/member/electronic_couponsUI.shtml?openid=${openid }">
			</div>
		</div>
		<div class="tab-pane" id="transaction_history">333</div>
		<div class="tab-pane" id="communication_history">444</div>
		<div class="tab-pane" id="integral_history">555</div>
		<div class="tab-pane" id="combined_card_history">666</div>
		<div class="tab-pane" id="purchased_goods">777</div>
		<div class="tab-pane" id="collection_of_goods">888</div>
	</div>

</body>
<script type="text/javascript">
	onloadurl();
</script>