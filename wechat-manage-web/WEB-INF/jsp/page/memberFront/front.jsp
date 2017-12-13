<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/scope.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/_disposeOldData.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/Sortable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/customPage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/customPageUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/webuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/cuspicture.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/PageManageWxHome.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<link href="${pageContext.request.contextPath}/css/ezr.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/center_conf.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/ezp-global.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/wxhome.css"   rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wx-pc.css"   rel="stylesheet"/>
<div class="table-responsive">
	<div class="tabbable-line">
		<ul class="nav nav-tabs ">
			<li class="active"><a href="#tab_15_1" id="btnCouponList"
				data-toggle="tab"> 会员首页 </a></li>
			<li><a href="#tab_15_2" data-toggle="tab" onclick="toPage('1')"> 完善资料</a></li>
			<li><a href="#tab_15_4" data-toggle="tab" onclick="toPage('2')"> 会员特权 </a></li>
			<li><a href="#tab_15_5" data-toggle="tab" onclick="toPage('3')"> 绑定会员卡</a></li>
			<li><a href="#tab_15_6" data-toggle="tab" onclick="toPage('4')"> 激活手机</a></li>
			<li><a href="#tab_15_7" data-toggle="tab" onclick="toPage('5')"> 邀请有礼</a></li>
			<li><a href="#tab_15_8" data-toggle="tab" onclick="toPage('6')"> 品牌会员卡</a></li>
			<li><a href="#tab_15_9" data-toggle="tab" onclick="toPage('7')"> 微信会员卡</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_15_1">
				<div class="col-md-12 content-wrapper">
					<div class="content">
						<div class="main-content" id="div_main">
							<div id="div_main2">
							
								<div class="ui-prompt ui-shadow-bottom">
									<div class="grid-nav-bar">
										<div class="grid-nav-bar-item">
											<!--  页面地址：<span id="pageurl"></span>-->
										</div>
									</div>
								</div>

								<div class="ui-bottom-fixedbtn ui-shadow-top">
									<form action="edit.php" method="post">
										<input name="ret0" id="Ret0" type="hidden"> <input
											name="edit" id="edit" type="hidden"><br>
										<!--<input type="submit" class="medit" value="编辑">-->
									</form>
									<a class="btn btn-primary w-120" href="javascript:void(0)"
										id="save" style="height: 40px; line-height: 28px;">保存</a>
								</div>


								<!--20160905-->
								<div class="ceremony">
									<div class="grid-nav-bar">
										<div style="width: 361px; padding-right: 30px;"
											class="grid-nav-bar-item">
											<div class="appphone_details_left">
												<div class="appphone_header"></div>
												<div class="appphone_container">
													<div class="appphone_btn"></div>
													<div class="appphone_main">
														<div class="appphone_top">
															<span class="apptop_tittle"></span>
														</div>
														<div class="appphone_content">
															<!--内容区域-->
															<form role="form" id="p-data" class="form-horizontal"
																way-data="formData" way-persistent=""
																style="float: none;">
																<div id="container" class="container">
																	<div id="my">
																		<div id="P" way-data="All">
																			<!-- 模板填充内容 -->
																		</div>
																	</div>
																</div>
															</form>
															<!-- 模板组件开始 -->
															     <!--底部btn-->
                            <div style="background: #545454;color:#fff;">
                                <div class="grid-nav-bar" style="height: 80px;">

                                    <div class="grid-nav-bar-item add-common btn-text-navigation" data-type="textnavigation" style="width: 80px;">
                                        <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img14.png" alt=""><br>文本导航</div>
                                    <div class="grid-nav-bar-item add-common btn-img-navigation" data-type="imgnavigation" style="width: 80px;">
                                        <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img12.png"><br>图片导航</div>
                                    <div class="grid-nav-bar-item add-common btn-uxiliary-blank" data-type="uxiliaryblank">
                                        <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img17.png"><br>辅助空白</div>
                                    <div class="grid-nav-bar-item add-common btn-title-navigation" data-type="titlenavigation" style="width: 80px;">
                                        <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img10.png"><br>标题</div>

                                </div>
                                <div class="grid-nav-bar" style="height: 80px;">

                                    <div class="grid-nav-bar-item ui-grid-cards-item add-common btn-picture-navigation" data-type="picturenavigation" style="width: 80px;">
                                        <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img09.png"><br>图片</div>
                                    <div class="grid-nav-bar-item add-common btn-mall-orders" data-type="mallorders" style="width: 80px;">
                                        <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img09.png"><br>订单标签</div>
                                    <!--<div class="grid-nav-bar-item add-common btn-comheader" data-type="comheader"><span class="glyphicon glyphicon-euro"></span><br>头部</div>-->
                                    <div class="grid-nav-bar-item" style="width: 80px;">
                                    </div>
                                    <div class="grid-nav-bar-item" style="width: 80px;">
                                    </div>

                                </div>
                            </div>
															<!-- 模板组件结束-->
														</div>
													</div>
												</div>
												<div class="appphone_bottom"></div>
											</div>
										</div>
										<div style="padding-top: 85px;"
											class="grid-nav-bar-item text-left text-top"></div>
									</div>
								</div>
								
								<div class="insertCommon wdn">
    <div class="grid-nav-bar" style="height: 80px;">

        <div class="grid-nav-bar-item add-common btn-text-navigation" data-insert="true" data-type="textnavigation" style="width: 80px;">
            <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img14.png" alt=""><br>文本导航</div>
        <div class="grid-nav-bar-item add-common btn-img-navigation" data-insert="true" data-type="imgnavigation" style="width: 80px;">
            <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img12.png"><br>图片导航</div>
        <div class="grid-nav-bar-item add-common btn-uxiliary-blank" data-insert="true" data-type="uxiliaryblank">
            <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img17.png"><br>辅助空白</div>
        <div class="grid-nav-bar-item add-common btn-title-navigation" data-insert="true" data-type="titlenavigation" style="width: 80px;">
            <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img17.png"><br>标题</div>

    </div>
    <div class="grid-nav-bar" style="height: 80px;">

        <div class="grid-nav-bar-item ui-grid-cards-item add-common btn-picture-navigation" data-insert="true" data-type="picturenavigation" style="width: 80px;">
            <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img09.png"><br>图片</div>
        <div class="grid-nav-bar-item add-common btn-mall-orders" data-insert="true" data-type="mallorders" style="width: 80px;">
            <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img09.png"><br>订单标签</div>
        <div class="grid-nav-bar-item" style="width: 80px;">
        </div>
        <div class="grid-nav-bar-item" style="width: 80px;">
        </div>

    </div>
</div>
								
								<div class="toJson">
									<div class="alert bg-warning">
										<div class="btn btn-sm btn-default pull-right"
											style="position: relative; top: -5px;" way-clear=""
											way-persistent="">Clear</div>
									</div>
									<pre way-data="__all__" way-json="true" way-default="{}"></pre>
								</div>

								<!-- 引入模板文件-->
								<jsp:include page="template.jsp" flush="true" />
								<script>
									//初始
									$(".nodeItem").removeClass("cur");
									$("#viewsTgt").children("div:first")
											.addClass("cur");
									$("#controllerTgt").children("div:first")
											.addClass("cur");
								</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
