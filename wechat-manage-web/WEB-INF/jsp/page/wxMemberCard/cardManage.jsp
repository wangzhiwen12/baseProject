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
<link href="${pageContext.request.contextPath}/css/ezr.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/center_conf.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/ezp-global.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/wxhome.css"   rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wx-pc.css"   rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/wx-card.css"   rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-colorselector/css/bootstrap-colorselector.css"   rel="stylesheet"/>
<div class="table-responsive">
	<div class="tabbable-line">
		<ul class="nav nav-tabs ">
			<li><a href="#tab_15_1" id="btnCouponList" data-toggle="tab" onclick="toPage('0')"> 会员首页 </a></li>
			<li><a href="#tab_15_2" data-toggle="tab" onclick="toPage('1')"> 完善资料</a></li>
			<li><a href="#tab_15_4" data-toggle="tab" onclick="toPage('2')"> 会员特权 </a></li>
			<li><a href="#tab_15_5" data-toggle="tab" onclick="toPage('3')"> 绑定会员卡</a></li>
			<li><a href="#tab_15_6" data-toggle="tab" onclick="toPage('4')"> 激活手机</a></li>
			<li><a href="#tab_15_7" data-toggle="tab" onclick="toPage('5')"> 邀请有礼</a></li>
			<li><a href="#tab_15_8" data-toggle="tab" onclick="toPage('6')"> 品牌会员卡</a></li>
			<li class="active"><a href="#tab_15_9" data-toggle="tab"> 微信会员卡</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_15_9">
				<div class="col-md-12 content-wrapper">
					<div class="content">
						<div class="main-content" id="div_main">
							<div id="div_main2">
								<!-- 微信会员卡开始-->
			<div class="tab-pane" id="tab_15_9">
				<div style="margin-bottom: 45px;" id="" class="app-design clearfix without-add-region row" >
        <div class="">
            <!-- ko if: ($root.cardCfg().IsWxVipCard()) -->

            <!-- ko if: ($root.cardCfg().Id() == 0) -->
            <span>如需使用微信券卡，请记得先在 <span class="ezp-c-yellow">微信公众平台 - 券卡功能</span> 中申请开通会员卡哦！</span>
            <!-- /ko -->
            <!-- ko if: ($root.cardCfg().Id() > 0) -->
            <!-- ko if: ($root.cardCfg().StatusTag() == 0) -->
            <div class="ui-prompt margin-bottom-base ui-shadow-bottom">
                <div class="grid-nav-bar">
                    <div class="grid-nav-bar-item">会员卡状态：编辑中</div>
                </div>
            </div>
            <!-- /ko -->
            <!-- ko if: ($root.cardCfg().StatusTag() == 1) -->
            <div class="ui-prompt margin-bottom-base ui-shadow-bottom">
                <div class="grid-nav-bar">
                    <div class="grid-nav-bar-item">会员卡状态：微信正在进行审核处理，审核一般在1-3个工作日，审核期间不允许修改会员卡</div>

                </div>
            </div>
            <!-- /ko -->
            <!-- ko if: ($root.cardCfg().StatusTag() == 2) -->
            <!-- <div class="bg-primary" style="margin-bottom: 24px; padding: 7px; text-align: center
; background: #5cb85c !important; ">
                会员卡状态：会员卡已生效，可正常使用
            </div> -->
            <div class="ui-instructions  margin-bottom-base ui-shadow-bottom">
                <div class="grid-nav-bar">
                    <div class="grid-nav-bar-item">会员卡状态：会员卡已生效，可正常使用； 领卡地址：<a href="javascript:void
(0)" class="popovers" data-toggle="popover" data-original-title="" data-bind="attr:{'data-content':$root
.cardCfg().PageUrl}">点击查看</a>
                    </div>
                </div>
            </div>
            <!-- /ko -->
            <!-- ko if: ($root.cardCfg().StatusTag() == 3) -->
            <div class="ui-warning  margin-bottom-base ui-shadow-bottom">
                <div class="grid-nav-bar">
                    <div class="grid-nav-bar-item">会员卡状态：<span data-bind="text:$root.cardCfg().StatusMsg"
></span>
                    </div>
                </div>
            </div>
            <!-- /ko -->
            <!-- /ko -->
            <!-- /ko -->
            
            <!-- ko if: (!$root.cardCfg().IsWxVipCard()) -->
            <span>如需使用微信券卡，请先联系管理员开通微信会员卡功能哦！</span>
            <!-- /ko -->
        </div>


        <div class="grid-nav-bar">
            <div class="grid-nav-bar-item text-top" style="width: 361px;padding-right: 30px;" >
                <div class="appphone_header"></div>
                <div class="appphone_container">
                    <div class="appphone_btn"></div>
                    <div class="appphone_main">
                        <div class="appphone_top"></div>
                        <div class="appphone_content " data-bind="with:cardCfg" >
                            <div class="appphone_content_nav">
                            <!-- ko if: ($root.cardCfg().CoverType() == 'PC') -->
                                <div class=" codedetailshow" data-bind="style:{background:CoverColor
,backgroundImage:'url(\'' + FullCoverPic() + '\')'}" >
                                    <div class="grid-nav-bar" style="margin-bottom: 60px;">
                                        <div class="grid-nav-bar-item" style="width:48px;padding-right
: 10px;">
                                            <img width="48px" height="48px" class="img-circle" data-bind
="attr:{src:FullCardLogoPic}" alt="">
                                        </div>
                                        <div class="grid-nav-bar-item text-left">
                                            <span data-bind="html:CardBrandName"></span><br>
                                            <span data-bind="text:CardTitle"></span>
                                        </div>
                                        <!-- ko if: ($root.cardCfg().CodeType() != 'CODE_TYPE_TEXT')
 -->
                                        <div class="grid-nav-bar-item" style="width: 34px;padding-left
: 10px;">
                                            <img width="34px" height="34px"  class="media-object " src
="https://static.ezrpro.com/assets/images/shipcode.png">
                                        </div>
                                        <!-- /ko -->
                                    </div>
                                    <div class="grid-nav-bar" >
                                        <div class="grid-nav-bar-item text-left">
                                            <span style="font-size:18px;">8888 888 888</span>
                                        </div>
                                        <!--<div class="grid-nav-bar-item" style="width: 24px;padding-left
: 10px;">
                                            <img width="24px" height="24px" src="https://static.ezrpro
.com/assets/images/shipcadr.png " alt="">
                                        </div>-->
                                    </div>
                                </div>
                                 <!-- /ko -->
                                <!-- ko if: ($root.cardCfg().CoverType() == 'CL') -->
                                <div class=" codedetailshow" data-bind="style:{background:CoverColor
}">
                                    <div class="grid-nav-bar" style="margin-bottom: 60px;">
                                        <div class="grid-nav-bar-item" style="width:48px;padding-right
: 10px;">
                                            <img width="48px" height="48px" class="img-circle" data-bind
="attr:{src:FullCardLogoPic}" alt="">
                                        </div>
                                        <div class="grid-nav-bar-item text-left">
                                            <span data-bind="html:CardBrandName"></span><br>
                                            <span data-bind="text:CardTitle"></span>
                                        </div>
                                        <div class="grid-nav-bar-item" style="width: 34px;padding-left
: 10px;">
                                            <img width="34px" height="34px"  class="media-object " src
="https://static.ezrpro.com/assets/images/shipcode.png">
                                        </div>
                                    </div>
                                    <div class="grid-nav-bar" >
                                        <div class="grid-nav-bar-item text-left">
                                            <span style="font-size:18px;">8888 888 888</span>
                                        </div>
                                        <!--<div class="grid-nav-bar-item" style="width: 24px;padding-left
: 10px;">
                                            <img width="24px" height="24px" src="https://static.ezrpro
.com/assets/images/shipcadr.png " alt="">
                                        </div>-->
                                    </div>
                                </div>
                                <!-- /ko -->


                                <div class="grid-nav-bar margin-bottom-base" style="margin-top: 20px
;">
                                    <div class="grid-nav-bar-item">
                                        <div class="col-nav col-nav-3" >
                                            <div class="col-nav-item">
                                                <span>积分</span>
                                                <br>
                                                <span class="color-light-green-500 h4" id="labOrderMoney"
>0</span>
                                            </div>
                                            <div class="col-nav-item">
                                                <span>优惠券</span>
                                                <br>
                                                <span class="color-light-green-500 h4" id="labCoupCnt"
>查看</span>
                                            </div>
                                            <div class="col-nav-item">
                                                <span>等级</span>
                                                <br>
                                                <span class="color-light-green-500 h4" id="labDjMoney"
>普通会员</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- ko if: CenterTitle() -->
                                <div class="ui-form-inline text-center" style="margin-bottom: 10px;"
>
                                    <button type="button" class="ui-btn-default ui-btn w-120" data-bind
="text:CenterTitle" style="width: 200px;"></button>
                                </div>
                                <!-- /ko -->
                                <div class="ui-form-inline text-center">
                                    <span class="color-normal-gray-810" data-bind="text:CenterSubTitle"
></span>
                                </div>
                                
                            </div>
                            <div class="appphone_content" style="margin-top: 10px;background: #fff;padding
:10px;">
                                <div data-bind="foreach:$root.cusItem" >
                                    <div class="appphone_content_navitem grid-nav-bar">
                                        <div class="grid-nav-bar-item text-left">
                                            <span data-bind="text:Name"></span>
                                        </div>
                                        <div class="grid-nav-bar-item" style="width:16px;padding-left
: 10px;">
                                            <span aria-hidden="true" class="glyphicon glyphicon-menu-right"
></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="appphone_content_navitem grid-nav-bar" style="padding-top
: 10px;border-top:1px solid #d9d9d9;margin-top: 10px; ">
                                    <div class="grid-nav-bar-item text-left">
                                        会员卡详情
                                    </div>
                                    <div class="grid-nav-bar-item" style="width:16px;padding-left: 10px
;">
                                        <span aria-hidden="true" class="glyphicon glyphicon-menu-right"
></span>
                                    </div>
                                </div>
                                <div class="appphone_content_navitem grid-nav-bar">
                                    <div class="grid-nav-bar-item text-left">
                                        适用门店
                                    </div>
                                    <div class="grid-nav-bar-item" style="width:16px;padding-left: 10px
;">
                                        <span aria-hidden="true" class="glyphicon glyphicon-menu-right"
></span>
                                    </div>
                                </div>
                                <div class="appphone_content_navitem grid-nav-bar">
                                    <div class="grid-nav-bar-item text-left">
                                        公众号
                                    </div>
                                    <div class="grid-nav-bar-item" style="width:16px;padding-left: 10px
;">
                                        <span aria-hidden="true" class="glyphicon glyphicon-menu-right"
></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="appphone_bottom"></div>
            </div>
            <div class="grid-nav-bar-item text-left text-top">
                 <!--  <fieldset data-bind="attr:{disabled:$root.cardCfg().StatusTag() == 1 || !$root.cardCfg
().IsWxVipCard()}">-->
                <fieldset>
                <div class="ui-shadow padding-base" style="position:relative;">
                    <div class="ui-triangle-left"></div>
                    <div data-bind="with:$root.cardCfg">
                        <div class="ui-form-horizontal">
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
; padding-left: 0;">
                                    <span class="color-normal-red-500">*</span>
                                    <span class="ui-form-field-label-span">品牌LOGO</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item" style="width: 100px
;cursor: pointer;padding-left: 10px;" data-bind="click:$root.selectPic">
                                    <img data-bind="attr:{src:FullCardLogoPic()}" width="100" height
="100" style="vertical-align: bottom;">
                                </div>
                                <div class="grid-nav-bar-item text-left text-top" style="padding-top
: 5px;">
                                    <span class="color-normal-gray-810">
                                        建议尺寸：300×300像素
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="ui-form-horizontal">
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="color-normal-red-500">*</span>
                                    <span class="ui-form-field-label-span">主标题</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <input type="text" data-bind="value:CardBrandName,enable:!CardId
()" maxlength="12" class="ui-form-control readLength width-100 ">
                                    <span class="color-normal-gray-810">会员卡微信审核通过后，该名称不可再修改</span>
                                </div>
                            </div>
                        </div>
                        <div class="ui-form-horizontal">
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="color-normal-red-500">*</span>
                                    <span class="ui-form-field-label-span">副标题</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <input type="text" data-bind="value:CardTitle" maxlength="9" class
="ui-form-control readLength width-100 ">
                                    <span class="color-normal-gray-810">建议会员卡标题包含商户名或服务内容，如驿氪会员卡</span
>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="ui-form-horizontal">
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="color-normal-red-500">*</span>
                                    <span class="ui-form-field-label-span">卡券封面</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <div>
                                        <label>
                                            <input type="radio" class="fields" value="CL" data-bind="checked
:$root.cardCfg().CoverType" name="CoverType">颜色
                                        </label>
                                        <label>
                                            <input type="radio" class="fields" value="PC" data-bind="checked
:$root.cardCfg().CoverType" name="CoverType">图片
                                        </label>

                                        <div class="codeselect">
                                            <div class="codeselectpicture" data-bind="visible:$root.cardCfg
().CoverType() == 'PC'">
                                                <div class="ui-form-field-cot grid-nav-bar-item" style
="width: 100px; cursor: pointer;" data-bind="click:$root.selectCoverPic">
                                                    <img data-bind="attr:{src:$root.cardCfg().FullCoverPic
}" width="100" height="100" style="vertical-align: bottom;">
                                                </div>
                                                <p class="help-block">请参照 <a class="color-light-green-500"
 target="_blank" href="https://mp.weixin.qq.com/cgi-bin/readtemplate?t=cardticket/card_cover_tmpl&type
=info&lang=zh_CN">图片规范</a> 上传</p>
                                                <!--<div class="controls">
                                                    <div id="fileList" class="uploader-list" style="margin-bottom
: 5px">
                                                        <div id="WU_FILE_0" class="file-item thumbnail"
><img style="width: 100px;height: 100px" data-bind="attr:{src:$root.cardCfg().FullCoverPic}"></div>

                                                    </div>
                                                    <div id="filePicker" class="singleselectpic">选择图
片</div>
                                                    <span class="validationMessage" data-bind="validationMessage
: $root.cardCfg().CoverPic">请选择图片</span>
                                                    <p class="help-block">请参照 <a class="color-light-green-500"
 href="https://mp.weixin.qq.com/cgi-bin/readtemplate?t=cardticket/card_cover_tmpl&type=info&lang=zh_CN"
>图片规范</a> 上传</p>
                                                </div>-->
                                            </div>
                                            
                                            <div data-bind="visible:$root.cardCfg().CoverType() == 'CL'"
>
                                                <!--<input class="ui-input-color" type="color" data-bind
="value:$root.cardCfg().CoverColor">-->
                                                <select id="colorselector" style="display: none;" data-bind
="value:$root.cardCfg().CoverColor">
                                                    <option value="#63b359" data-color="#63b359">#63b359
</option>
                                                    <option value="#2c9f67" data-color="#2c9f67">#2c9f67
</option>
                                                    <option value="#509fc9" data-color="#509fc9">#509fc9
</option>
                                                    <option value="#5885cf" data-color="#5885cf">#5885cf
</option>
                                                    <option value="#9062c0" data-color="#9062c0">#9062c0
</option>
                                                    <option value="#d09a45" data-color="#d09a45">#d09a45
</option>
                                                    <option value="#e4b138" data-color="#e4b138">#e4b138
</option>
                                                    <option value="#ee903c" data-color="#ee903c">#ee903c
</option>
                                                    <option value="#f08500" data-color="#f08500">#f08500
</option>
                                                    <option value="#a9d92d" data-color="#a9d92d">#a9d92d
</option>
                                                    <option value="#dd6549" data-color="#dd6549">#dd6549
</option>
                                                    <option value="#cc463d" data-color="#cc463d">#cc463d
</option>
                                                    <option value="#cf3e36" data-color="#cf3e36">#cf3e36
</option>
                                                    <option value="#5E6671" data-color="#5E6671">#5E6671
</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    
                </div>
                <!-- ↓↓ 会员卡二维码 ↓↓ -->
                <div class="wrap-title">
                    <span class="wrap-title-cot"><b>会员卡号</b></span>
                </div>
                <div class="ui-shadow padding-base" style="position:relative;" data-bind="with:$root
.cardCfg">
                    <div class="ui-triangle-left"></div>
                    <div class="ui-form-horizontal">
                        <div class="ui-form-fields grid-nav-bar">
                            <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px;padding-left
: 0;">
                                <span class="color-normal-red-500">*</span>
                                <span class="ui-form-field-label-span">会员卡号</span>
                            </div>
                            <div class="ui-form-field-cot grid-nav-bar-item">
                                <label>
                                    <input type="radio" value="CODE" data-bind="checked:CardNoType" name
="CardNoType">&nbsp;电子卡号
                                </label>&nbsp;&nbsp;
                                <label>
                                    <input type="radio" value="MBNO" data-bind="checked:CardNoType" name
="CardNoType">&nbsp;手机号
                                </label>&nbsp;&nbsp;
                                <label>
                                    <input type="radio" value="OCODE" data-bind="checked:CardNoType"
 name="CardNoType">&nbsp;品牌卡号&nbsp;<span class="color-normal-gray-810">(如果无品牌卡号，则显示手机号)</span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="ui-form-horizontal">
                        <div class="ui-form-fields grid-nav-bar">
                            <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px;padding-left
: 0;">
                                <span class="color-normal-red-500">*</span>
                                <span class="ui-form-field-label-span">会员卡识别</span>
                            </div>
                            <div class="ui-form-field-cot grid-nav-bar-item">
                                <div class="row verification" >
                                    <div class="col-md-4">
                                        <label>
                                            <input type="radio" name="showMode" value="CODE_TYPE_TEXT"
 data-bind="checked:CodeType" data-value="CODE_TYPE_TEXT" class="fields">仅卡号
                                        </label>
                                        <span style="margin-right:10px;" class="selectpicture pull-left
 text-center">
                                            <span class="media-middle media-body ">
                                                <span>111-222-333</span>
                                            </span>
                                        </span>
                                        <!--<p class="ezp-c-gray text-center">输码后可销券</p>-->
                                    </div>
                                    <div class="col-md-4">
                                        <label>
                                            <input type="radio" name="showMode" value="CODE_TYPE_QRCODE"
 data-bind="checked:CodeType" data-value="CODE_TYPE_QRCODE" class="fields">卡号和二维码
                                        </label>
                                        <span style="margin-right:10px;" class="selectpicture pull-left
 text-center">
                                            <span class="media-middle media-body ">
                                                <img height="34px" class=" " src="https://static.ezrpro
.com/assets/images/shipcode.png">
                                                <br>
                                                <span>111-222-333</span>
                                            </span>
                                        </span>
                                        <!--<p class="ezp-c-gray text-center">扫码或输码后可销券</p>-->
                                    </div>
                                    <div class="col-md-4">
                                        <label>
                                            <input type="radio" name="showMode" value="CODE_TYPE_BARCODE"
 data-bind="checked:CodeType" data-value="CODE_TYPE_BARCODE" class="fields">卡号和条形码
                                        </label>
                                        <span style="margin-right:10px;" class="selectpicture pull-left
 text-center">
                                            <span class="media-middle media-body ">
                                                <img height="34px" class=" " src="https://static.ezrpro
.com/assets/images/shipwocode.png">
                                                <br>
                                                <span>111-222-333</span>
                                            </span>
                                        </span>
                                        <!--<p class="ezp-c-gray text-center">扫码或输码后可销券</p>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                           <div class="ui-form-horizontal">
                                <div class="ui-form-fields grid-nav-bar">
                                    <div class="ui-form-field-label grid-nav-bar-item" style="width:
 95px;padding-left: 0;">
                                        <span class="color-normal-red-500">*</span>
                                        <span class="ui-form-field-label-span">操作提示</span>
                                    </div>
                                    <div class="ui-form-field-cot grid-nav-bar-item">
                                         <input type="text" data-bind="value:Notice" placeholder="" maxlength
="16" class="ui-form-control readLength width-100 ">
                                         <span class="color-normal-gray-810">建议引导用户到店出示会员卡</span>
                                    </div>
                                </div>
                            </div> 
                </div>

                <!-- ↓↓ 顶部菜单 ↓↓ -->
                <!--<div class="wrap-title">
                    <span class="wrap-title-cot"><b>顶部菜单</b></span>
                    <small class="color-normal-gray-810">(最多勾选3个，默认菜单勾选保存后不可更改)</small>
                </div>
                    <div class="ui-shadow padding-base" style="position:relative;" data-bind="with:$root
.cardCfg">
                        <div class="ui-triangle-left"></div>
                        <div class="ui-form-horizontal">
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="ui-form-field-label-span">默认菜单</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item ui-form-inline">
                                    <label>
                                        <input type="checkbox" data-bind="checked:SupplyBonus,enable
:(Id()==0 && !SupplyBonus())">&nbsp;积分
                                    </label>
                                </div>
                            </div>
                            <div class="ui-form-fields grid-nav-bar clear-margin">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="ui-form-field-label-span">顶部菜单</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <div class="grid-nav-bar ui-form-inline" style="margin-bottom: 20px
;" >
                                        <label>
                                            <input type="checkbox" data-bind="checked:SupplyCoupon">
&nbsp;优惠券
                                        </label>&nbsp;&nbsp;
                                        <label>
                                            <input type="checkbox" data-bind="checked:SupplyGrade">&nbsp
;等级
                                        </label>
                                    </div>
                                    <div class="grid-nav-bar ui-form-inline" style="background: #f9f9f9
;padding:20px;">
                                        <div class="grid-nav-bar-item text-left text-top" style="width
: 17px;"> 
                                            <label>
                                                <input type="checkbox" data-bind="checked:SupplyBalance"
>
                                            </label>
                                        </div>
                                        <div class="grid-nav-bar-item">
                                            <div style="position: relative">
                                                <input class="ui-form-control readLength width-100 "
 data-bind="value:BalanceName" type="text" name="" data-maxlength="4" maxlength="4" placeholder="自定义
菜单名称">
                                            </div>
                                            <div style="height: 20px;"></div>
                                            <div style="position: relative">
                                                <input class="ui-form-control readLength width-100 "
 data-bind="value:BalanceUrl" type="text" name="" data-maxlength="128" maxlength="128" placeholder="
菜单跳转链接">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->



                <!-- ↓↓ 中心按钮 ↓↓ -->
                <div class="wrap-title">
                    <span class="wrap-title-cot"><b>中心按钮</b></span>
                    <small class="color-normal-gray-810">(会员卡激活后才会显示)</small>
                </div>
                    <div class="ui-shadow padding-base" style="position:relative;" data-bind="with:$root
.cardCfg">
                        <div class="ui-form-horizontal">
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="ui-form-field-label-span">按钮类型</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <label>
                                        <input type="radio" value="CS" data-bind="checked:CenterType"
 name="CenterBtType">&nbsp;自定义按钮
                                    </label>&nbsp;&nbsp;
                                    <label>
                                        <input type="radio" value="WP" data-bind="checked:CenterType"
 name="CenterBtType">&nbsp;微信支付（打开“钱包支付”）
                                    </label>&nbsp;&nbsp;
                                </div>
                            </div>
                            <!-- ko if: CenterType() == "CS" -->
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="ui-form-field-label-span">按钮名称</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <input type="text" maxlength="9" data-bind="value:CenterTitle" class
="ui-form-control readLength width-100 " data-maxlength="9">
                                </div>
                            </div>
                            <div class="ui-form-fields grid-nav-bar">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="ui-form-field-label-span">跳转地址</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <input type="text" maxlength="128" data-bind="value:CenterUrl" class
="ui-form-control readLength width-100 " data-maxlength="128">
                                </div>
                            </div>
                            <div class="ui-form-fields grid-nav-bar clear-margin">
                                <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px
;padding-left: 0;">
                                    <span class="ui-form-field-label-span">提示语</span>
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item">
                                    <input type="text" maxlength="12" data-bind="value:CenterSubTitle"
 class="ui-form-control readLength width-100 " data-maxlength="12">
                                </div>
                            </div>
                            <!-- /ko -->
                        </div>
                    </div>


                <!-- ↓↓ 添加自定义入口 ↓↓ -->
                <div class="wrap-title">
                    <span class="wrap-title-cot"><b>添加自定义入口</b></span>
                    <small class="color-normal-gray-810">(最多添加3个)</small>
                </div>
                <div class="ui-shadow padding-base" style="position:relative;">
                     <div class="ui-triangle-left"></div>
                    <!-- ko if: ($root.cusItem().length > 0) -->
                    <div data-bind="foreach:$root.cusItem">
                        <div class="ui-form-horizontal">
                           <div class=" grid-nav-bar ui-form-fields">
                                <div class="ui-form-field-label grid-nav-bar-item clear-padding text-left"
 style="width: 70px;vertical-align: middle;">
                                    入口名称
                                </div>
                                <div class="ui-form-field-cot grid-nav-bar-item" style="width:130px;"
>
                                    <div style="width:130px;">
                                        <input data-bind="value:Name" type="text" class="ui-form-control
 readLength" maxlength="5" placeholder="" style="width:130px">
                                    </div>
                                </div>
                                <div class="grid-nav-bar-item" style="width: 60px;">
                                    链接到
                                </div>
                                <div class="grid-nav-bar-item text-left">
                                    <div class=" clearfix">
                                        <div class="js-link-to link-to selectLink" style="display: inline-block
;">
                                            <span class="ret" style="display: inline-block;"><span class
="cot" data-bind="text:UrlName"></span> </span>
                                        </div>
                                        
                                    </div>
                                </div>
                                <div class="grid-nav-bar-item" style="width:60px;">
                                    <div class="dropdown hover">
                                            <a href="javascript:;"
                                               class="dropdown-toggle shopnav-item-action" data-toggle
="dropdown">修改</a>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'7',event)}">完善资料</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'6',event)}">绑定会员卡</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'8',event)}">我的积分</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'4',event)}">我的优惠券</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'9',event)}">我的订单</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'19',event)}">我的收藏</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'11',event)}">我的地址</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'5',event)}">我的特权</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'12',event)}">我的导购</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'13',event)}">门店活动</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'14',event)}">申请服务</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'15',event)}">待评价</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'16',event)}">附近门店</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'1',event)}">个人资料</a>
                                                </li>
                                                <li>
                                                    <a href="#" data-type="goods" data-value=""
                                                       class="js-modal-goods" data-bind="click:function
(data, event){$root.DropMenuClick(data,'curl',event)}">自定义外链</a>
                                                </li>
                                            </ul>
                                    </div>
                                    <span class="validationMessage" data-bind="validationMessage: Url"
>请选择链接</span>
                                </div>
                               <div class="grid-nav-bar-item" style="width: 145px;padding-left: 10px
;">
                                   <!-- ko if: SortIdx() !=1 -->
                                   <div class="ui-circular operation-icon square-34" data-bind="click
:$root.upItem">
                                       <div class="ui-circular-inner">
                                           <span aria-hidden="true" class="glyphicon glyphicon-arrow-up"
></span>
                                       </div>
                                   </div>
                                   <!-- /ko -->
                                   <!-- ko if: SortIdx() != $root.cusItem().length -->
                                   <div class="ui-circular operation-icon square-34" data-bind="click
:$root.downItem">
                                       <div class="ui-circular-inner">
                                           <span aria-hidden="true" class="glyphicon glyphicon-arrow-down"
></span>
                                       </div>
                                   </div>
                                   <!-- /ko -->
                                   <div class="ui-circular operation-icon square-34" data-bind="click
:$root.removeItem">
                                       <div class="ui-circular-inner">
                                           <span aria-hidden="true" class="glyphicon glyphicon-trash"
></span>
                                       </div>
                                   </div>
                               </div>
                           </div>
                        </div>
                    </div>
                    <!-- /ko -->
                    <!-- ko if: ($root.cusItem().length < 3) -->
                    <p style="margin-top: 10px">
                        <span style="display: block;" class="addItem btn btn-default jsHandle jsHandle-1464867518650"
 data-bind="click:$root.addItem">添加</span>
                    </p>
                    <!-- /ko -->
                </div>

                <!-- ↓↓ 会员卡详情 ↓↓ -->
                <div class="wrap-title">
                    <span class="wrap-title-cot"><b>会员卡详情</b></span>
                </div>
                <div class="ui-shadow padding-base" style="position:relative;" data-bind="with:$root
.cardCfg">
                    <div class="ui-triangle-left"></div>
                    <div class="ui-form-horizontal">
                        <div class="ui-form-fields grid-nav-bar">
                            <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px;padding-left
: 0;">
                                <span class="color-normal-red-500">*</span>
                                <span class="ui-form-field-label-span">会员卡特权</span>
                            </div>
                            <div class="ui-form-field-cot grid-nav-bar-item">
                                <textarea maxlength="3000" class="ui-form-control readLength width-100"
 rows="3" style="min-height:100px;" data-bind="value:Prerogative"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="ui-form-horizontal">
                        <div class="ui-form-fields grid-nav-bar">
                            <div class="ui-form-field-label grid-nav-bar-item" style="width: 95px;padding-left
: 0;">
                                <span class="color-normal-red-500">*</span>
                                <span class="ui-form-field-label-span">使用说明</span>
                            </div>
                            <div class="ui-form-field-cot grid-nav-bar-item">
                                <textarea maxlength="3000" class="ui-form-control readLength width-100"
 rows="5" style="min-height:100px;" data-bind="value:Description"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ui-bottom-fixedbtn ui-shadow-top" >
                    <button class="ui-btn ui-btn-green w-120" href="javascript:void(0)" data-bind="click
:function(data, event){$root.saveData(0)}">保存</button>
                    <button class="ui-btn ui-btn-green" href="javascript:void(0)" data-bind="click:function
(data, event){$root.saveData(1)}">保存并提交至微信</button>
                </div>
            </fieldset>

            </div>
        </div>
    </div>
    <div class="modal fade" id="model_url" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
 aria-hidden="true" style="display: none;">
        <div class="modal-dialog" style="width:600px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button
>
                    <span class="modal-title fz-16" id="myModalLabel">编辑自定义外链</span>
                </div>
                <div class="modal-body" id="" style="max-height:200px; overflow-x:hidden; overflow-y
:auto; scrollbar-base-color:#ff0000">
                    <div class="form-group">
                        <label> 请输入外链地址(地址必须合法)</label>
                        <div>
                            <input type="text" class="form-control readLength" maxlength="128" data-bind
="value:$root.selectedUrl" placeholder="以http://开头">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times-circle"
></i> 关闭</button>
                    <button type="button" class="btn btn-custom-primary" data-bind="click:$root.updateUrl"
><i class="fa fa-check-circle"></i> 确定</button>
                </div>
            </div>

        </div>
    </div>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
			</div>
			<!-- 微信会员卡结束-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
