<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/PagePhoneManage.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/layer-v1.9.2/layer/layer.js"></script>

<%-- <script type="text/javascript"
        src="${pageContext.request.contextPath}/js/front/ueditor.config.js"></script> --%>
<%-- <script type="text/javascript"
        src="${pageContext.request.contextPath}/js/front/ueditor.uemy.js"></script> --%>
<%-- <script type="text/javascript"
        src="${pageContext.request.contextPath}/js/front/ueditor.zh-cn.js"></script> --%>
<%-- <script type="text/javascript"
        src="${pageContext.request.contextPath}/js/front/ueditor.addimagedialog.js"></script> --%>

<script type="text/javascript" charset="utf-8" src="wfj-ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="wfj-ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="wfj-ueditor/lang/zh-cn/zh-cn.js"></script>
       <link rel="stylesheet" href="wfj-ueditor/themes/default/css/ueditor.css">


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
            <li class="active"><a href="#tab_15_6" data-toggle="tab" onclick="toPage('4')"> 激活手机</a></li>
            <li><a href="#tab_15_7" data-toggle="tab" onclick="toPage('5')"> 邀请有礼</a></li>
            <li><a href="#tab_15_8" data-toggle="tab" onclick="toPage('6')"> 品牌会员卡</a></li>
            <li><a href="#tab_15_9" data-toggle="tab"> 微信会员卡</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab_15_91">
                <div class="col-md-12 content-wrapper">
                    <div class="content">
                        <div class="main-content" id="div_main">
                            <div id="div_main2">
                                <!-- 微信会员卡开始-->
                                <div class="tab-pane" id="tab_15_9">
                                    <div style="margin-bottom: 45px;" id="1" class="app-design clearfix without-add-region row" >
                                        <div class="">
                                            <!-- ko if: ($root.cardCfg().IsWxVipCard()) -->

                                            <!-- ko if: ($root.cardCfg().Id() == 0) -->
                                            <%--  <span>如需使用微信券卡，请记得先在 <span class="ezp-c-yellow">微信公众平台 - 券卡功能</span> 中申请开通会员卡哦！</span>--%>
                                            <!-- /ko -->
                                            <!-- ko if: ($root.cardCfg().Id() > 0) -->
                                            <!-- ko if: ($root.cardCfg().StatusTag() == 0) -->
                                            <div class="ui-prompt margin-bottom-base ui-shadow-bottom">
                                                <%--<div class="grid-nav-bar">--%>
                                                <%--&lt;%&ndash;<div class="grid-nav-bar-item">会员卡状态：编辑中</div>&ndash;%&gt;--%>
                                                <%--</div>--%>
                                            </div>
                                            <!-- /ko -->
                                            <!-- ko if: ($root.cardCfg().StatusTag() == 1) -->
                                            <%--<div class="ui-prompt margin-bottom-base ui-shadow-bottom">--%>
                                            <%--<div class="grid-nav-bar">--%>
                                            <%--&lt;%&ndash;<div class="grid-nav-bar-item">会员卡状态：微信正在进行审核处理，审核一般在1-3个工作日，审核期间不允许修改会员卡</div>&ndash;%&gt;--%>

                                            <%--</div>--%>
                                            <%--</div>--%>
                                            <!-- /ko -->
                                            <!-- ko if: ($root.cardCfg().StatusTag() == 2) -->
                                            <!-- <div class="bg-primary" style="margin-bottom: 24px; padding: 7px; text-align: center
                                ; background: #5cb85c !important; ">
                                                会员卡状态：会员卡已生效，可正常使用
                                            </div> -->
                                            <%--<div class="ui-instructions  margin-bottom-base ui-shadow-bottom">--%>
                                            <%--<div class="grid-nav-bar">--%>
                                            <%--<div class="grid-nav-bar-item">会员卡状态：会员卡已生效，可正常使用； 领卡地址：<a href="javascript:void--%>
                                            <%--(0)" class="popovers" data-toggle="popover" data-original-title="" data-bind="attr:{'data-content':$root--%>
                                            <%--.cardCfg().PageUrl}">点击查看</a>--%>
                                            <%--</div>--%>
                                            <%--</div>--%>
                                            <%--</div>--%>
                                            <!-- /ko -->
                                            <!-- ko if: ($root.cardCfg().StatusTag() == 3) -->
                                            <%--<div class="ui-warning  margin-bottom-base ui-shadow-bottom">--%>
                                            <%--<div class="grid-nav-bar">--%>
                                            <%--&lt;%&ndash;<div class="grid-nav-bar-item">会员卡状态：<span data-bind="text:$root.cardCfg().StatusMsg" ></span>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                            <%--</div>--%>
                                            <%--</div>--%>
                                            <!-- /ko -->
                                            <!-- /ko -->
                                            <!-- /ko -->

                                            <!-- ko if: (!$root.cardCfg().IsWxVipCard()) -->
                                            <%--<span>如需使用微信券卡，请先联系管理员开通微信会员卡功能哦！</span>--%>
                                            <!-- /ko -->
                                        </div>


                                        <div class="grid-nav-bar">
                                            <div class="grid-nav-bar-item text-top" style="width: 361px;padding-right: 30px;" >
                                                <div class="appphone_header"></div>
                                                <div class="appphone_container">
                                                    <div class="appphone_btn"></div>
                                                    <div class="appphone_main">
                                                        <div class="appphone_top" id="titledesc">
                                                            <span class="apptop_tittle">会员注册</span>
                                                        </div>

                                                        <%----%>
                                                        <div class="appphone_banner" style="padding:10px;">
                                                            <div id="yl_img"  style="background-size: 100% 100%; height: 158px;">

                                                            </div>



                                                            <%--所在城市--%>
                                                            <div id="city" class="appphone_content text-left">

                                                            </div>

                                                            <%--手机号码--%>
                                                            <div class="appphone_content text-left" data-bind="template: {name: 'menu_view_fields', foreach: registerFields}">
                                                                <label class="ui-form-field mg-40">
                                     <span class="ui-form-field-inner">
                                            <b data-bind="text:FieldName">手机号码</b>
                                         <span class="ui-input-operation">
                                             <span class="ui-input-operation-cell">
                                             </span>
                                             </span>
                                                         </span>
                                                                </label>
                                                            </div>

                                                            <%--生日--%>
                                                            <div id="birthday" class="appphone_content text-left ">

                                                            </div>
                                                            <%--姓名--%>
                                                            <div id="name" class="appphone_content text-left">

                                                            </div>
                                                            <%--性别--%>
                                                            <div id="gender" class="appphone_content text-left">

                                                            </div>








                                                            <div style="margin-top: 10px">
                                                                <button data-bind="" class="btn " type="button" style="width: 100%; background: #2b323a;color: white"> 立即领取会员卡</button>
                                                            </div>
                                                            <div style="margin-top: 10px" class="footerview" data-bind="html:$root.registerCfg().FooterContent"></div>
                                                        </div>


                                                    </div>
                                                </div>
                                                <div class="appphone_bottom"></div>
                                            </div>
                                            <%--中间 右部区域 start ------------------------ --%>


                                            <div class="grid-nav-bar-item text-left text-top" style="padding-top: 85px;">
                                                <div class="ui-shadow padding-base" style="position:relative;">
                                                    <div class="ui-triangle-left"></div>
                                                    <div class="ui-form-horizontal">
                                                        <div class="ui-form-fields grid-nav-bar">
                                                            <div class="ui-form-field-label grid-nav-bar-item" style="width: 80px;padding-left: 0;">
                                                                <!-- <span class="color-normal-red-500">*</span> -->
                                                                <span class="ui-form-field-label-span">标题</span>
                                                            </div>
                                                            <div class="ui-form-field-cot grid-nav-bar-item">
                                                                <div class="readLengthWarp typeINPUT">
                                                                    <input id="idtitle" type="text" class="ui-form-control readLength width-100" maxlength="6" placeholder=""  data-maxlength="6"><span class="status"> <span class="curLength">4</span> / <span class="totalLength">6</span> </span></div><span class="validationMessage" style="display: none;"></span>
                                                                <span class="validationMessage" data-bind="validationMessage: $root.registerCfg().Title" style="display: none;"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="ui-form-horizontal">
                                                        <div class="ui-form-fields grid-nav-bar">
                                                            <div class="ui-form-field-label grid-nav-bar-item" style="width: 80px;padding-left: 0;">
                                                                <span class="ui-form-field-label-span">背景图片</span>
                                                            </div>
                                                            <div id="material_get" class="ui-form-field-cot grid-nav-bar-item" style="width: 140px;cursor: pointer;padding-left: 10px;" data-bind="click:selectPic">

                                                            </div>
                                                            <div class="grid-nav-bar-item text-left text-top" style="padding-top: 5px;">
                        <span class="color-normal-gray-810">
                            建议尺寸：640×320像素
                        </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="wrap-title">
                                                    <span class="wrap-title-cot"><b>编辑资料</b></span>
                                                </div>
                                                <div id="aaa" class="ui-shadow padding-base" style="position: relative; display: none;" data-bind="style:{display:selectFieldsDisplay}">
                                                    <div class="ui-triangle-left"></div>
                                                    <!-- Default panel contents -->
                                                    <!--<div class="panel-heading">选择资料项（最多选择7个） </div>-->
                                                    <div id="div11" class="ui-panel-body" data-bind="template: {name: 'menu_register_fields', foreach: selectFields}">
                                                        <div class="input-group" style="margin-bottom: 20px">
                                                            <label class="form-control" data-bind="text:FieldName,attr:{for:FieldName}" >所在城市</label>
        <span class="input-group-addon">
            <input  type="checkbox"  id="ycity">
        </span>
                                                        </div>

                                                        <div class="input-group" style="margin-bottom: 20px">
                                                            <label class="form-control" data-bind="text:FieldName,attr:{for:FieldName}" for="手机号码">手机号码</label>
        <span class="input-group-addon">
            <input  type="checkbox"  id="手机号码" disabled="" checked="true">
        </span>
                                                        </div>

                                                        <div class="input-group" style="margin-bottom: 20px">
                                                            <label class="form-control" data-bind="checked:IsSelected,attr:{id:FieldName},enable:IsEnabled" >生日</label>
        <span class="input-group-addon">
            <input type="checkbox" id="ybirthday">
        </span>
                                                        </div>

                                                        <div class="input-group" style="margin-bottom: 20px">
                                                            <label class="form-control" >姓名</label>
        <span class="input-group-addon">
            <input type="checkbox" name="r" id="yname">
        </span>
                                                        </div>

                                                        <div class="input-group" style="margin-bottom: 20px">
                                                            <label class="form-control" data-bind="text:FieldName,attr:{for:FieldName}" >性别</label>
        <span class="input-group-addon">
            <input type="checkbox" name="r"  id="ygender">
        </span>
                                                        </div>
                                                    </div>
                                                    <div class="text-center">
                                                        <button id="btnadd" class="ui-btn ui-btn-green w-120" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-bind="click:$root.updateRegisters">
                                                            确定
                                                        </button>
                                                        <button id="btnclose" class="ui-btn-default ui-btn w-120" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-bind="click:$root.closeField">
                                                            关闭
                                                        </button>
                                                    </div>
                                                    <p></p>
                                                </div>


                                                <div id="bbb" class="ui-shadow padding-base" style="position: relative; display: block;" data-bind="style:{display:registerDisplay}">
                                                    <div class="ui-triangle-left"></div>
                                                    <table class="ui-table ui-striped-rows">
                                                        <thead class="text-center">
                                                        <tr>
                                                            <th>导航名称</th>
                                                            <th>排序</th>
                                                            <th>允许为空</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody data-bind="template: {name: 'menu_edit_fields', foreach: registerFields}" class="text-center">
                                                        <tr>
                                                            <td data-bind="text:FieldName">手机号码</td>
                                                            <td>
                                                                <div class="ui-circular operation-icon square-34" data-bind="style:{visibility:UpDownVisibility},click:$root.fieldUpDown" style="visibility: hidden;">
                                                                    <div class="ui-circular-inner">
                                                                        <span class="glyphicon glyphicon-arrow-up"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="ui-circular operation-icon square-34" data-bind="style:{visibility:DownVisibility},click:$root.fieldDown" style="visibility: hidden;">
                                                                    <div class="ui-circular-inner">
                                                                        <span class="glyphicon glyphicon-arrow-down"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="ui-circular operation-icon square-34" data-bind="style:{visibility:DeleteVisibility},click:$root.deleteField" style="visibility: hidden;">
                                                                    <div class="ui-circular-inner">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="control-inline ui-onoffswitch ui-onoffswitch-sm">
                                                                    <input type="checkbox" class="onoffswitch-checkbox" name="onoffswitch" data-bind="{checked:CanEmpty,attr:{id:'checkbox'+$index()},click:$root.editTemp  }" id="checkbox0">
                                                                    <label data-bind="attr:{for:'checkbox'+$index()}" class="onoffswitch-label" for="checkbox0">
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
                                                    <div class="ui-warning cursor-p " data-bind="click:$root.addField" style="background: #ddefc9;">
                                                        <div class="grid-nav-bar">
                                                            <div id="adddoc" class="grid-nav-bar-item color-light-green-500">添加资料项</div>
                                                        </div>
                                                    </div>








                                                </div>
                                                <%--33333--%>
                                                <div class="wrap-title">
                                                    <span class="wrap-title-cot"><b>页面底部说明</b>（若使用微信原生激活，该说明不展示）</span>
                                                </div>
                                                <div class="ui-shadow padding-base" style="position: relative;">
                                                    <div class="ui-triangle-left"></div>
                                                    <div id="myEditor" class="edui-default" style="width: 100%;">
                                                        <script type="text/plain" id="descriptionContentEditor"
                                                                style="width:100%; min-height: 200px;"></script>
                                                         <script type="text/javascript">
                                                        	var ue2 = UE.getEditor("myEditor",{UEDITOR_HOME_URL:"./wfj-ueditor/",toolbars:[['fullscreen', 'source', 'undo', 'redo', '|', 'bold',
                                            	                                                                            				'italic', 'underline', 'removeformat', 'blockquote', '|',
                                            	                                                                            				'forecolor', 'backcolor', 'justifyleft', 'justifycenter',
                                            	                                                                            				'justifyright', 'justifyjustify', 'insertorderedlist',
                                            	                                                                            				'insertunorderedlist', '|', 'fontfamily', 'fontsize',
                                            	                                                                            				'emotion', '|', 'link', '|', 'horizontal', 'inserttable',
                                            	                                                                            				'deletetable', 'insertparagraphbeforetable', 'insertrow',
                                            	                                                                            				'deleterow', 'insertcol', 'deletecol', 'mergecells',
                                            	                                                                            				'mergeright', 'mergedown', 'splittocells', 'splittorows',
                                            	                                                                            				'splittocols', '|', 'drafts']]});
                                                        </script>
                                                    </div>

                                                </div>
                                                <!--<div class="wrap-title">
                                                    <span class="wrap-title-cot"><b>提示绑定手机的特权说明</b></span>
                                                </div>
                                                <div class="ui-shadow padding-base" style="position:relative;">
                                                    <div class="ui-triangle-left"></div>
                                                    <script type="text/plain" id="myEditorPrivilege" style="width: 100%;height:130px;">
                                                    </script>
                                                </div>-->
                                                <div class="wrap-title">
                                                    <span class="wrap-title-cot"><b>用户协议</b></span>
                                                </div>
                                                <div class="ui-shadow padding-base" style="position: relative;">
                                                    <div class="ui-triangle-left"></div>
                                                    <div id="myEditorUserAgreement" class="edui-default" style="width: 100%;">
                                                        <script type="text/plain" id="agreementContentEditor" name="agreementContentEditor"
                                                                style="width: 100%; min-height: 200px;"></script>
                                                        <script type="text/javascript">
                                                        	var ue = UE.getEditor("myEditorUserAgreement",{UEDITOR_HOME_URL:"./wfj-ueditor/",toolbars:[['fullscreen', 'source', 'undo', 'redo', '|', 'bold',
                                                        	                                                                            				'italic', 'underline', 'removeformat', 'blockquote', '|',
                                                        	                                                                            				'forecolor', 'backcolor', 'justifyleft', 'justifycenter',
                                                        	                                                                            				'justifyright', 'justifyjustify', 'insertorderedlist',
                                                        	                                                                            				'insertunorderedlist', '|', 'fontfamily', 'fontsize',
                                                        	                                                                            				'emotion', '|', 'link', '|', 'horizontal', 'inserttable',
                                                        	                                                                            				'deletetable', 'insertparagraphbeforetable', 'insertrow',
                                                        	                                                                            				'deleterow', 'insertcol', 'deletecol', 'mergecells',
                                                        	                                                                            				'mergeright', 'mergedown', 'splittocells', 'splittorows',
                                                        	                                                                            				'splittocols', '|', 'drafts']]});
                                                        </script>
                                                    </div>





                                                    <%--中间 右部区域 end ---------------------------- --%>
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

                                        </div>
                                        <!-- 微信会员卡结束-->
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="ui-bottom-fixedbtn ui-shadow-top">
                            <button class="ui-btn ui-btn-green w-120"
                                    id="activePhone">发布</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>