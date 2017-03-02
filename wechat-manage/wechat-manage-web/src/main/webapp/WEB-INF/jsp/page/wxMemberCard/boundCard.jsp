<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/scope.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/_disposeOldData.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/Sortable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/customPage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/customPageUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/webuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/cuspicture.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/knockout-3.3.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/knockout.validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/charlimit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-colorselector/js/bootstrap-colorselector.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/PageManageWxCard.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/page/boundCard.js"></script>
<link href="${pageContext.request.contextPath}/css/ezr.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/center_conf.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/ezp-global.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/wxhome.css"   rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wx-pc.css"   rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/wx-card.css"   rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-colorselector/css/bootstrap-colorselector.css"   rel="stylesheet"/>
<style type="text/css">
   	.input-border{
    	width:70%;border:0px;background:none;
   	}
</style>
<div class="table-responsive">
	<div class="tabbable-line">
		<ul class="nav nav-tabs ">
			<li><a href="#tab_15_1" id="btnCouponList" data-toggle="tab" onclick="toPage('0')"> 会员首页 </a></li>
			<li><a href="#tab_15_2" data-toggle="tab" onclick="toPage('1')"> 完善资料</a></li>
			<li><a href="#tab_15_4" data-toggle="tab" onclick="toPage('2')"> 会员特权 </a></li>
			<li class="active"><a href="#tab_15_5" data-toggle="tab"> 绑定会员卡</a></li>
			<li><a href="#tab_15_6" data-toggle="tab" onclick="toPage('4')"> 激活手机</a></li>
			<li><a href="#tab_15_7" data-toggle="tab" onclick="toPage('5')"> 邀请有礼</a></li>
			<li><a href="#tab_15_8" data-toggle="tab" onclick="toPage('6')"> 品牌会员卡</a></li>
			<li><a href="#tab_15_9" data-toggle="tab" onclick="toPage('7')"> 微信会员卡</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_15_5">
				<div class="col-md-12 content-wrapper">
					<div class="content">
						<div class="main-content" id="div_main">
							<div id="div_main2">
								<!-- 微信会员卡开始-->
								<div class="tab-pane" id="tab_15_5">
									<div style="margin-bottom: 45px;" id="" class="app-design clearfix without-add-region row" >
								        <div class="grid-nav-bar">
								            <div class="grid-nav-bar-item text-top" style="width: 361px;padding-right: 30px;" >
								                <div class="appphone_header"></div>
								                <div class="appphone_container">
								                    <div class="appphone_btn"></div>
								                    <div class="appphone_main">
								                        <div class="appphone_top"></div>
								                        <div class="appphone_content" style="padding: 10px;" id="WxHeadHtml">
								                            <div>
								                                <div class="appphone_content_navitem bg-normal-gray-310 text-left color-normal-gray-810" style="padding-left: 10px;height: 40px;line-height: 40px;">
								                                </div>
								                            </div>
								                            <ul role="tablist" class="nav nav-tabs">
								                                <li class="active" role="presentation" id="li-phone">
								                                    <a href="#ForPhone" aria-expanded="true" data-toggle="tab" role="tab"><span>按手机号</span></a>
								                                </li>
								                                <li role="presentation" id="li-card">
								                                    <a href="#ForIdCardNo" aria-expanded="true" data-toggle="tab" role="tab"><span>按会员卡号</span></a>
								                                </li>
								                            </ul>
								                            <div style="background: #fff;padding:10px;margin-top: 10px;">
								                                <div class="tab-content clear-padding">
								                                    <div class="tab-pane fade active in" id="ForPhone">
								                                        <div class="appphone_content_navitem grid-nav-bar" id="phone-sjh">
								                                            <div class="grid-nav-bar-item text-left" id="input-sjh">
								                                               	 <label style="width:25%;text-align: center;">手机号</label><input type="text" class="input-border" style="width: 40%;" id="input-content-sjh" />
								                                            </div>
								                                        </div>
								                                        <div class="appphone_content_navitem grid-nav-bar" id="phone-yzm">
								                                            <div class="grid-nav-bar-item text-left" id="input-yzm">
																				<label style="width:25%;text-align: center;">验证码</label><input type="text" id="input-content-yam"  class="input-border"/>
								                                            </div>
								                                        </div>
								                                        <div class="appphone_content_navitem grid-nav-bar" id="phone-sr">
								                                            <div class="grid-nav-bar-item text-left" id="input-sr">
								                                               	 <label style="width:25%;text-align: center;">生&nbsp;&nbsp;&nbsp;&nbsp;日</label>
								                                            </div>
								                                        </div>
								                                        <div class="appphone_content_navitem grid-nav-bar" id="phone-hykh">
								                                            <div class="grid-nav-bar-item text-left" id="input-hykh">
																				<label style="width:25%;text-align: center;">会员卡号</label><input type="text" id="input-content-hykh"  class="input-border"/>
								                                            </div>
								                                        </div>
								                                        <div class="appphone_content_navitem grid-nav-bar" id="phone-pwd">
								                                            <div class="grid-nav-bar-item text-left" id="input-pwd">
																				<label style="width:25%;text-align: center;">密&nbsp;&nbsp;&nbsp;&nbsp;码</label><input type="password" id="input-content-pwd"  class="input-border"/>
								                                            </div>
								                                        </div>
								                                    </div>
								                                    <div class="tab-pane fade" id="ForIdCardNo">
								                                        <div class="appphone_content_navitem grid-nav-bar" id="card-hykh">
								                                            <div class="grid-nav-bar-item text-left" id="input-hykh-two">
																				<label style="width:25%;text-align: center;">会员卡号</label><input type="text" id="input-content-hykh-two"  class="input-border"/>
								                                            </div>
								                                        </div>
								                                        <div class="appphone_content_navitem grid-nav-bar" id="card-sfzh">
								                                            <div class="grid-nav-bar-item text-left" id="input-sfzh">
																				<label style="width:25%;text-align: center;">身份证号</label><input type="text" id="input-content-sfzh"  class="input-border"/>
								                                            </div>
								                                        </div>
								                                    </div>
								                                </div>
								                            </div>
								                             <div style="margin-top: 10px">
								                                <button class="btn" type="button" style="width: 100%; background: #2b323a; color: white"> 立即绑定</button>
								                            </div>
								                        </div>
								                    </div>
								                </div>
								                <div class="appphone_bottom"></div>
								            </div>
								            <div class="grid-nav-bar-item text-left text-top" id="WxHomeHtml">
								                <fieldset>
								                <!-- ↓↓ 是否禁用活动 ↓↓ -->
								                
								                <div class="wrap-title">
								                    <span class="wrap-title-cot"><b>是否禁用活动</b></span>
								                    <div class="ui-form-group pull-right">
									                    <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                        <input id="isDisabled" class="onoffswitch-checkbox" name="onoffswitch" checked type="checkbox">
									                        <label for="isDisabled" class="onoffswitch-label">
									                            <span class="onoffswitch-inner">
									                                <span class="onoffswitch-before">on</span>
									                                <span class="onoffswitch-after">off</span>
									                            </span>
									                            <span class="onoffswitch-switch"></span>
									                        </label>
									                    </div>
									                </div>
								                </div>
								                <div class="ui-shadow padding-base" style="position:relative;display:none; " id="activity" >
								                    <div class="ui-triangle-left"></div>
								                    <div>
								                         <ul class="nav nav-tabs" role="tablist">
								                             <li id="cntab" class="active"><a href="#cnInfo" aria-controls="cnInfo" role="tab" data-toggle="tab" aria-expanded="true">送优惠券</a></li>
								                             <li id="bstab" class=""><a href="#bsInfo" aria-controls="bsInfo" role="tab" data-toggle="tab" aria-expanded="false">送积分</a></li>
								                         </ul>
								                         <div class="tab-content">
								                             <div role="tabpanel" class="tab-pane active" id="cnInfo">
								                                 <div class="modal-body" >
								                                     <fieldset>
								                                         <form class="form-horizontal">
								                                             <div class="form-group">
								                                                 <label class="col-sm-3 control-label">优惠名称</label>
								                                                 <div class="col-sm-9">
								                                                     <input class="form-control" placeholder="" type="text"><span class="validationMessage" style="display: none;"></span>
								                                                 </div>
								                                             </div>
								                                             <div class="form-group">
								                                                 <label class="col-sm-3 control-label">优惠券</label>
								                                                 <div class="col-sm-9">
								                                                     <div class="input-group" >
								                                                         <input class="form-control input-sm" placeholder="选择电子券" disabled type="text">
								                                                         <span class="input-group-addon"><a href="#"><i class="fa fa-credit-card"></i></a></span>
								                                                     </div>
								                                                     <span class="validationMessage" style="display: none;"></span>
								                                                 </div>
								                                             </div>
								                                             <div class="form-group">
								                                                 <label class="col-sm-3 control-label">有效时间</label>
								                                                 <div class="col-sm-9">
								                                                     <div class="form-group form-inline" >
								                                                         	自赠送日起
								                                                         <input class="form-control" placeholder="" type="text">
								                                                         	天有效
								                                                     </div>
								                                                     <span class="validationMessage" style="display: none;"></span>
								                                                 </div>
								
								                                             </div>
								                                             <div class="form-group">
								                                                 <label class="col-sm-3 control-label">使用说明</label>
								                                                 <div class="col-sm-9">
								                                                     <textarea rows="4" class="form-control" placeholder="用于向买家展示，如该优惠券不得与其他活动同时使用；节假日不得使用等，回车即可划分段落" maxlength="512"></textarea><span class="validationMessage" style="display: none;"></span>
								                                                 </div>
								                                             </div>
								                                         </form>
								                                     </fieldset>
								                                 </div>
								                             </div>
								                             <div role="tabpanel" class="tab-pane" id="bsInfo" >
								                                 <div class="modal-body">
								                                     <fieldset >
								                                         <form class="form-horizontal">
								                                             <div class="form-group">
								                                                 <label class="col-sm-3 control-label">活动名称</label>
								                                                 <div class="col-sm-9">
								                                                     <input class="form-control" placeholder="" type="text"><span class="validationMessage" style="display: none;"></span>
								                                                 </div>
								                                             </div>
								                                             <div class="form-group">
								                                                 <label class="col-sm-3 control-label">积分值</label>
								                                                 <div class="col-sm-9">
								                                                     <input class="form-control" type="text"><span class="validationMessage" style="display: none;"></span>
								                                                 </div>
								                                             </div>
								                                         </form>
								                                     </fieldset>
								                                 </div>
								                             </div>
								                         </div>
								                    </div>
								                </div>
								                
								                <!-- ↓↓ 基于手机号的绑卡/合卡资料验证项↓↓ -->
								                <div class="wrap-title">
									                <span class="wrap-title-cot"><b>基于手机号的绑卡/合卡资料验证项</b>&nbsp;&nbsp;
									                <small class="color-normal-gray-810">(只有通过验证，才能合卡成功)</small></span>
									                <div class="ui-form-group pull-right">
									                    <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                        <input class="onoffswitch-checkbox" name="onoffswitch" id="checkboxType1" checked type="checkbox">
									                        <label for="checkboxType1" class="onoffswitch-label">
									                            <span class="onoffswitch-inner">
									                                <span class="onoffswitch-before">on</span>
									                                <span class="onoffswitch-after">off</span>
									                            </span>
									                            <span class="onoffswitch-switch"></span>
									                        </label>
									                    </div>
									                </div>
									            </div>
								                <div class="ui-shadow padding-base" style="position:relative;" id="phone_binding" >
								                    <div class="ui-triangle-left"></div>
								                    <table class="ui-table ui-striped-rows">
									                    <thead class="text-center">
									                        <tr>
									                            <th>验证资料名称</th>
									                            <th>是否启用</th>
									                        </tr>
									                    </thead>
									                    <tbody class="text-center">
									                        <tr>
									                            <td>手机号</td>
									                            <td>启用</td>
									                        </tr>
									                        <tr>
									                            <td>验证码</td>
									                            <td>
									                                <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                                    <input class="onoffswitch-checkbox" name="onoffswitch" id="checkBindMoblieCode" checked type="checkbox">
									                                    <label for="checkBindMoblieCode" class="onoffswitch-label">
									                                        <span class="onoffswitch-inner">
									                                            <span class="onoffswitch-before">on</span>
									                                            <span class="onoffswitch-after">off</span>
									                                        </span>
									                                        <span class="onoffswitch-switch"></span>
									                                    </label>
									                                </div>
									                            </td>
									                        </tr>
									                        <tr>
									                            <td>生日</td>
									                            <td>
									                                <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                                    <input class="onoffswitch-checkbox" name="onoffswitch" id="checkboxBirthday" checked type="checkbox">
									                                    <label for="checkboxBirthday" class="onoffswitch-label">
									                                        <span class="onoffswitch-inner">
									                                            <span class="onoffswitch-before">on</span>
									                                            <span class="onoffswitch-after">off</span>
									                                        </span>
									                                        <span class="onoffswitch-switch"></span>
									                                    </label>
									                                </div>
									                            </td>
									                        </tr>
									                        <tr>
									                            <td>会员卡号</td>
									                            <td>
									                                <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                                    <input class="onoffswitch-checkbox" name="onoffswitch" id="checkboxCode" checked type="checkbox">
									                                    <label for="checkboxCode" class="onoffswitch-label">
									                                        <span class="onoffswitch-inner">
									                                            <span class="onoffswitch-before">on</span>
									                                            <span class="onoffswitch-after">off</span>
									                                        </span>
									                                        <span class="onoffswitch-switch"></span>
									                                    </label>
									                                </div>
									                            </td>
									                        </tr>
									                        <tr>
									                            <td>密码</td>
									                            <td>
									                                <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                                    <input class="onoffswitch-checkbox" name="onoffswitch" id="checkboxPwd" checked type="checkbox">
									                                    <label for="checkboxPwd" class="onoffswitch-label">
									                                        <span class="onoffswitch-inner">
									                                            <span class="onoffswitch-before">on</span>
									                                            <span class="onoffswitch-after">off</span>
									                                        </span>
									                                        <span class="onoffswitch-switch"></span>
									                                    </label>
									                                </div>
									                            </td>
									                        </tr>
									                    </tbody>
									                </table>
								               	</div>
								
								
								                <!-- ↓↓ 基于会员卡号的绑卡/合卡验证 ↓↓ -->
								                <div class="wrap-title">
									                <span class="wrap-title-cot"><b>基于会员卡号的绑卡/合卡验证</b>&nbsp;&nbsp;
									                <small class="color-normal-gray-810">(只有通过验证，才能合卡成功)</small></span>
									                <div class="ui-form-group pull-right">
									                    <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                        <input class="onoffswitch-checkbox" name="onoffswitch" id="checkboxType2" checked type="checkbox">
									                        <label for="checkboxType2" class="onoffswitch-label">
									                            <span class="onoffswitch-inner">
									                                <span class="onoffswitch-before">on</span>
									                                <span class="onoffswitch-after">off</span>
									                            </span>
									                            <span class="onoffswitch-switch"></span>
									                        </label>
									                    </div>
									                </div>
									            </div>
								                <div class="ui-shadow padding-base" style="position:relative;" id="card_binding">
								                     <div class="ui-triangle-left"></div>
								                     <table class="ui-table ui-striped-rows">
									                    <thead class="text-center">
									                        <tr>
									                            <th>验证资料名称</th>
									                            <th>是否启用</th>
									                        </tr>
									                    </thead>
									                    <tbody class="text-center">
									                        <tr>
									                            <td>会员卡号</td>
									                            <td>启用</td>
									                        </tr>
									                        <tr>
									                            <td>身份证号<br><small class="color-normal-gray-810">身份证号码后6位</small></td>
									                            <td>
									                                <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
									                                    <input class="onoffswitch-checkbox" name="onoffswitch" id="checkboxIdCardNo" checked type="checkbox">
									                                    <label for="checkboxIdCardNo" class="onoffswitch-label">
									                                        <span class="onoffswitch-inner">
									                                            <span class="onoffswitch-before">on</span>
									                                            <span class="onoffswitch-after">off</span>
									                                        </span>
									                                        <span class="onoffswitch-switch"></span>
									                                    </label>
									                                </div>
									                            </td>
									                        </tr>
									                    </tbody>
									                </table>
								                </div>
								
								                <div class="ui-bottom-fixedbtn ui-shadow-top">
											        <button class="ui-btn ui-btn-green w-120" id = "save-btn">发布</button>
											    </div>
								            </fieldset>
								
								            </div>
								        </div>
					    			</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
