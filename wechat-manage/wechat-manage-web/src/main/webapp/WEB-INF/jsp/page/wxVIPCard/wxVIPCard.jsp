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
	src="${pageContext.request.contextPath}/js/front/knockout-3.3.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/knockout.validation.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap-colorselector.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/cuspicture.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/charlimit.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/PageManageWxVipCard.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<link href="${pageContext.request.contextPath}/css/ezr.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/center_conf.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/ezp-global.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wxhome.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wx-pc.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wxpage.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wfj-old.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wfj-wxglobal.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wxVIPCard.css"
	rel="stylesheet" />
<div class="table-responsive">
	<div class="tabbable-line">
		<ul class="nav nav-tabs ">
			<li><a href="#tab_15_1" id="btnCouponList" data-toggle="tab"
				onclick="toPage('0')"> 会员首页 </a></li>
			<li><a href="#tab_15_2" data-toggle="tab" onclick="toPage('1')">
					完善资料</a></li>
			<li><a href="#tab_15_4" data-toggle="tab" onclick="toPage('2')">
					会员特权 </a></li>
			<li><a href="#tab_15_5" data-toggle="tab" onclick="toPage('3')">
					绑定会员卡</a></li>
			<li><a href="#tab_15_6" data-toggle="tab" onclick="toPage('4')">
					激活手机</a></li>
			<li><a href="#tab_15_7" data-toggle="tab" onclick="toPage('5')">
					邀请有礼</a></li>
			<li class="active"><a href="#tab_15_8" data-toggle="tab">
					品牌会员卡</a></li>
			<li><a href="#tab_15_9" data-toggle="tab" onclick="toPage('7')">
					微信会员卡</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane  active" id="tab_15_8">
				<!-- <div class="ui-prompt margin-bottom-base ui-shadow-bottom">
					<div class="grid-nav-bar">
						<div class="grid-nav-bar-item">
							页面地址：<span data-bind="text:cardCfg().PageUrl"></span>
						</div>
					</div>
				</div> -->
				<div id="datalistDiv">
					<div class="grid-nav-bar">
						<div class="grid-nav-bar-item"
							style="width: 361px; padding-right: 30px;">
							<div class="appphone_details_left">
								<div class="appphone_header"></div>
								<div class="appphone_container">
									<div class="appphone_btn"></div>
									<div class="appphone_main ">
										<div class="appphone_top">
											<span class="apptop_tittle" id="headName"
												data-bind="text:$root.cardCfg().PageName"></span>
										</div>
										<!-- <div class="appphone_banner margin-bottom-base" data-bind="visible:$root
.cardCfg().HeadPictureUrl()==''?false:true,attr:{src:$root.cardCfg().FullHeadPictureUrl()}" style="width
: 100%; height: 146px; position: absolute; left: 0; top: 0; " > -->
										<div class="appphone_banner" id="headPictureUrl"
											data-bind="style:{backgroundImage:'url(\''+ $root.cardCfg().FullHeadPictureUrl() + '\')'}"
											style="background-size: 100% 100%">
											<div class="grid-nav-bar padding-base">
												<div class="grid-nav-bar-item text-left"
													style="width: 50px; vertical-align: bottom; font-size: 18px;">
													金卡</div>
												<div class="grid-nav-bar-item text-left"
													style="vertical-align: bottom;">8888888888</div>
											</div>
										</div>
										<div
											class="ui-page-inner bg-white-deep-0 hoverOperation text-center">
											<div style="margin-top: 10px">品牌会员卡</div>
											<div style="margin-top: 10px">
												<img style="width: 164px;" src="./images/vipcardqrcode.png" />
											</div>
											<div style="margin-top: 10px">
												<img style="width: 164px;" src="./images/vipcardbarcode.png" />
											</div>
										</div>
									</div>
								</div>
								<div class="appphone_bottom"></div>
							</div>
						</div>
						<div class="grid-nav-bar-item text-left text-top"
							style="padding-top: 85px;">
							<div class="ui-shadow padding-base" style="position: relative;">
								<div class="ui-triangle-left"></div>
								<div class="ui-form-horizontal">
									<div class="ui-form-fields grid-nav-bar">
										<div class="ui-form-field-label grid-nav-bar-item"
											style="width: 80px; padding-left: 0;">
											<span class="ui-form-field-label-span">标题</span>
										</div>
										<div class="ui-form-field-cot grid-nav-bar-item">
											<input type="text"
												class="ui-form-control readLength width-100 " maxlength="8"
												placeholder="" id="headline"
												data-bind="value:$root.cardCfg().PageName"> <span
												class="validationMessage"
												data-bind="validationMessage: $root.cardCfg"></span>
										</div>
									</div>
								</div>
								<div class="ui-form-horizontal">
									<div class="ui-form-fields grid-nav-bar">
										<div class="ui-form-field-label grid-nav-bar-item"
											style="width: 80px; padding-left: 0;">
											<span class="ui-form-field-label-span">背景图片</span>
										</div>
										<div class="ui-form-field-cot grid-nav-bar-item"
											style="width: 140px; cursor: pointer; padding-left: 10px;"
											data-bind="click:selectPic" id="selectPic">
											<img id="imgHeadPictureUrl" width="140" height="70"
												style="vertical-align: bottom;"> <input type="hidden"
												id="wxHeadPictureUrl">
										</div>
										<div class="grid-nav-bar-item text-left text-top"
											style="padding-top: 5px;">

											<span class="color-normal-gray-810"> 建议尺寸：640×320像素 </span>
										</div>
									</div>
								</div>
								<div class="ui-form-horizontal">
									<div class="ui-form-fields grid-nav-bar">
										<div class="ui-form-field-label grid-nav-bar-item"
											style="width: 80px; padding-left: 0;">
											<span class="ui-form-field-label-span">会员卡显示</span>
										</div>
										<div class="ui-form-field-cot grid-nav-bar-item">
											<label class="control-inline"
												style="margin-right: 5px; margin-top: 5px"> <input
												type="checkbox" id="isShowBrandCode"
												data-bind="checked: $root.cardCfg().IsShowBrandCode"><span>品牌会员卡</span>
											</label> <label class="control-inline"
												style="margin-right: 5px; margin-top: 5px"> <input
												type="checkbox" id="isShowOnlineCode"
												data-bind="checked: $root.cardCfg().IsShowOnlineCode"><span>电子会员卡</span>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="ui-bottom-fixedbtn ui-shadow-top">
						<button type="button" class="ui-btn ui-btn-green w-120"
							id="saveData">发布</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
