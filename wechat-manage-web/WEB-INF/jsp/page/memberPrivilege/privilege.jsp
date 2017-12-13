<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/template.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/scope.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/_disposeOldData.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/Sortable.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/customPage.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/customPageUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/webuploader.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/cuspicture.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/jquery.lazyload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/knockout-3.3.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap-colorselector.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/knockout.validation.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/PageManageWxPrivileges.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/ueditor.uemy.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/ueditor.zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/ueditor.addimagedialog.js"></script>
<link href="${pageContext.request.contextPath}/css/ezr.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/center_conf.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/ezp-global.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wxhome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wx-pc.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/webuploader.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wfj-old.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wxpage.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wfj-wxglobal.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/privilege.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-switch.css">
<div class="table-responsive">
	<div class="tabbable-line">
		<ul class="nav nav-tabs ">
			<li><a href="#tab_15_1" id="btnCouponList" data-toggle="tab"
				onclick="toPage('0')"> 会员首页 </a></li>
			<li><a href="#tab_15_2" data-toggle="tab" onclick="toPage('1')">
					完善资料</a></li>
			<li class="active"><a href="#tab_15_4" data-toggle="tab">
					会员特权 </a></li>
			<li><a href="#tab_15_5" data-toggle="tab" onclick="toPage('3')">
					绑定会员卡</a></li>
			<li><a href="#tab_15_6" data-toggle="tab" onclick="toPage('4')">
					激活手机</a></li>
			<li><a href="#tab_15_7" data-toggle="tab" onclick="toPage('5')">
					邀请有礼</a></li>
			<li><a href="#tab_15_8" data-toggle="tab" onclick="toPage('6')">
					品牌会员卡</a></li>
			<li><a href="#tab_15_9" data-toggle="tab" onclick="toPage('7')">
					微信会员卡</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane  active" id="tab_15_4">
				<!-- <div class="ui-prompt margin-bottom-base ui-shadow-bottom">
					<div class="grid-nav-bar">
						<div class="grid-nav-bar-item">
							页面地址：<span data-bind="text:PageUrl"></span>
						</div>
					</div>
				</div> -->
				<div id="datalistDiv">
					<div class="grid-nav-bar">
						<div class="grid-nav-bar-item text-top"
							style="width: 361px; padding-right: 30px;">
							<div class="appphone_details_left">
								<div class="appphone_header"></div>
								<div class="appphone_container">
									<div class="appphone_btn"></div>
									<div class="appphone_main">
										<div class="appphone_top">
											<span class="apptop_tittle">会员特权</span>
										</div>
										<div class="appphone_content" style="padding: 10px;">
											<!-- <div style="margin-bottom: 10px;" id="defaultWxHeadHtml">
												<div class="ui-form-field ui-node-img" style="height: 124px">
													<span class="ui-form-field-inner  bg-gray"
														style="height: 124px"> <b
														class="ui-round warp-64 bg-white text-center"> <img
															style="border-radius: 100px" id="brandLogo">
													</b> <span class="ui-node-cot"> <span
															class="ui-node-title fz-48 display-block pz-relativef-l">
																<span data-bind="text:brandName" class="nw-lh"></span> <span
																class="display-block fz-24"> <span class="">{会员等级}</span><br>
																	<span class="c-gray">{会员卡号}</span>
															</span>
														</span>
													</span>
													</span>
												</div>
											</div> -->
											<div id="defaultWxHeadHtml"><span>会员首页没有设置该项</span></div>
											<div style="margin-bottom: 10px;" id="wxHeadHtml"></div>
											<div
												class="appphone_content_navitem bg-normal-gray-310 text-left color-normal-gray-810"
												style="padding-left: 10px; height: 40px; line-height: 40px;">
												<span>会员特权内容</span>
											</div>
										</div>
									</div>
								</div>
								<div class="appphone_bottom"></div>
							</div>
						</div>
						<div class="grid-nav-bar-item text-left text-top"
							style="padding-top: 85px;">
							<div class="ui-shadow padding-base"
								style="position: relative; margin-bottom: 20px;">
								<div class="ui-triangle-left"></div>
								<div class="ui-form-horizontal">
									<div class=" grid-nav-bar">
										<div class="ui-form-field-label grid-nav-bar-item"
											style="width: 80px; padding-left: 0;">
											<span class="ui-form-field-label-span">会员卡号</span>
										</div>
										<div class="ui-form-field-cot grid-nav-bar-item text-left">
											<div class="actions">
												<div class="">
													<input class="switch-state" type="checkbox" id="showHead">
												</div>
											</div>
										</div>
										<div  class="color-normal-gray-810">
											<span>启用后，顶部背景图片显示与会员首页一致</span>
										</div>
									</div>
								</div>
							</div>
							<div class="ui-shadow padding-base" style="position: relative;">
								<div class="ui-triangle-left"></div>
								<script type="text/plain" id="myContentEditor"
									style="width: 600px; min-height: 200px;"></script>
							</div>
						</div>
					</div>
					<div class="ui-bottom-fixedbtn ui-shadow-top">
						<button class="ui-btn ui-btn-green w-120"
							onclick="alertPrivilege()">发布</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
