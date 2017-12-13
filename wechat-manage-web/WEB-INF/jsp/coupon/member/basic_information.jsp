<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/coupon/member/basic_infomation.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf"%>
<head>
<style>
.inline {
	display: inline;
}

.topline {
	
}
</style>
</head>
<body>
	<section class="vbox">
		<div class="row"
			style="padding-right: 8px; padding-left: 8px; padding-top: 8ps; padding-bottom: 30px;">
			<div class="col-md-12 portlet ui-sortable"
				style="padding-bottom: 15px;">
				<section class="panel panel-success portlet-item">
					<header class="panel-heading">
						<i class="fa fa-briefcase"></i> 会员详情
					</header>
					<table class="table table-striped table-bordered table-hover"
						width="100%" style="vertical-align: middle; min-height: 220px;">
						<tbody id="tbody">
							<tr>
								<td width="33%" rowspan='7' align="center">
									<table class="inline">
										<tr>
											<td style="line-height: 0.5em;">会员折扣</td>
										</tr>
										<tr>
											<td style="line-height: 0.5em;"><span
												style="color: #A0B573; font-size: 28px;">xx</span> %</td>
										</tr>
									</table>
									<div class="inline">
										<img style="width: 100px; height: 100px;" alt=""
											src="${member.headimgurl }">
									</div>
									<table class="inline">
										<tr>
											<td style="line-height: 0.5em;">当前积分</td>
										</tr>
										<tr>
											<td style="line-height: 0.5em;"><span
												style="color: #A0B573; font-size: 28px;">xx</span></td>
										</tr>
									</table>
									<table style="margin-bottom: 1px;">
										<tr align="center">
											<td style="line-height: 0.5em; font-size: 10px;">${member.nickname}
												<span style="color: black; font-size: 20px;">&nbsp;|&nbsp;</span>xx会员
											</td>
										</tr>
										<tr align="center">
											<td style="line-height: 0.5em; font-size: 10px;">
											${member.country }&nbsp;&nbsp;${member.province }&nbsp;&nbsp;${member.city}
											</td>
										</tr>
										<tr align="center">
											<td style="line-height: 0.5em; font-size: 10px;">成长值：xxxx</td>
										</tr>
									</table>
								</td>
								<td width="33%"
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>手机：&nbsp;&nbsp;&nbsp;
									<span>
										<c:if test="${not empty member.mobile}">
											${member.mobile}
										</c:if>
									</span>
								</td>
								<td width="33%"
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>线下卡号：&nbsp;&nbsp;&nbsp;<span>xxx</span></td>
							</tr>
							<tr>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>会员卡号：&nbsp;&nbsp;&nbsp;
									<span>
										${member.memberCode}
									</span>
								</td>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>出生日期：&nbsp;&nbsp;&nbsp;<span>xxx</span></td>

							</tr>
							<tr>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>所在城市：&nbsp;&nbsp;&nbsp;<span>${member.province }&nbsp;&nbsp;${member.city}</span></td>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>别名：&nbsp;&nbsp;&nbsp;<span>${member.remark }</span></td>
							</tr>
							<tr>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>邮箱：&nbsp;&nbsp;&nbsp;<span>${member.email }</span>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>QQ：&nbsp;&nbsp;&nbsp;<span>xxx</span></td>
							</tr>
							<tr>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>微博：&nbsp;&nbsp;&nbsp;<span>xxx</span></td>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>专属导购：&nbsp;&nbsp;&nbsp;<span>xxx</span></td>
							</tr>
							<tr>
								<td style='vertical-align: middle;'>微信openid：&nbsp;&nbsp;&nbsp;<span
									style='text-align: rigth;'>${member.openid }</span></td>
								<td style='vertical-align: middle;'>微信Unionld：&nbsp;&nbsp;&nbsp;<span
									style='text-align: rigth;'>${member.unionid }</span></td>
							</tr>
							<tr>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>常用地址：&nbsp;&nbsp;&nbsp;<span>	${member.country }&nbsp;&nbsp;${member.province }&nbsp;&nbsp;${member.city}</span></td>
								<td
									style='padding-left: 10px; text-align: left; vertical-align: middle;'>
								</td>
							</tr>
						</tbody>
					</table>
				</section>
			</div>
		</div>
	</section>
</body>
