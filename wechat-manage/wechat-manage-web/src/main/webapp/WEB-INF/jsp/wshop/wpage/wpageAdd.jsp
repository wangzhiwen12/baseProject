<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>

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

<div class="table-responsive">
	<div class="tabbable-line">
		<div class="">
			<div class="tab-pane active" id="tab_15_1">
				<div class="col-md-12 content-wrapper">
					<div class="content">
						<div class="main-content" id="div_main">
							<div id="div_main2">
								<div>
									<input id="back" class="btn btn-info marR10" value="返回上一级"
										type="button">
								</div>

								<br>
								<div class="ui-bottom-fixedbtn ui-shadow-top">
									<form action="edit.php" method="post">
										<input name="ret0" id="Ret0" type="hidden"> <input
											name="edit" id="edit" type="hidden"><br>
										<!--<input type="submit" class="medit" value="编辑">-->
									</form>
									<input id="save" class="btn btn-info marR10" value="上架"
										type="button"> <input id="saveDraft"
										class="btn btn-info marR10" value="保存为草稿" type="button">
									<input class="wPageId" value="${wPageId }" 	type="hidden" /> 
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
																		
																		</div>
																	</div>
																</div>
															</form>
															<!-- 模板组件开始 -->
															<!--底部btn-->
															<div style="background: #545454; color: #fff;">
																<div class="grid-nav-bar" style="height: 80px;">
																	
																	<div
																		class="grid-nav-bar-item add-common btn-add-richText"
																		data-insert="true" data-type="addrichText"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img07.png"
																			alt=""><br>富文本
																	</div>
																	<div class="grid-nav-bar-item add-common btn-add-goods"
																		data-insert="true" data-type="addgoods"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img08.png"
																			alt=""><br>商品
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-add-goodsList"
																		data-insert="true" data-type="addgoodsList"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img19.png"
																			alt=""><br>商品列表
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-picture-navigation"
																		data-insert="true" data-type="picturenavigation"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img09.png"
																			alt=""><br>图片广告
																	</div>
																</div>
																<div class="grid-nav-bar" style="height: 80px;">
																	<div
																		class="grid-nav-bar-item add-common btn-text-navigation"
																		data-insert="true" data-type="textnavigation"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img10.png"
																			alt=""><br>图文导航
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-img-navigation"
																		data-insert="true" data-type="imgnavigation"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img12.png"><br>图片导航
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-add-search"
																		data-insert="true" data-type="addsearch"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img13.png"><br>商品搜索
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-add-subline"
																		data-insert="true" data-type="addsubline"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img16.png"><br>辅助线
																	</div>
																</div>
																<div class="grid-nav-bar" style="height: 80px;">
																	<div
																		class="grid-nav-bar-item add-common btn-uxiliary-blank"
																		data-insert="true" data-type="uxiliaryblank">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img17.png"><br>辅助空白
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-title-navigation"
																		data-insert="true" data-type="titlenavigation"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img10.png"><br>标题
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-add-notice"
																		data-insert="true" data-type="addnotice"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img15.png"><br>公告
																	</div>
																	<div
																		class="grid-nav-bar-item add-common btn-add-coupons"
																		data-insert="true" data-type="addcoupons"
																		style="width: 80px;">
																		<img
																			src="${pageContext.request.contextPath}/images/page/icon/channelpage_img20.png"><br>添加优惠券
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

								<div class="insertCommon wdn"></div>

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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/template.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/scope.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/_disposeOldData.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/Sortable.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/customPage.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/customPageUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/webuploader.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/cuspicture.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/PageManageWxHome.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/list.js"></script>

