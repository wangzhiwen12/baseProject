<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--文本导航-->
<script type="text/html" id="text-navigation">

    <div class="common text-navigation common-drag" id="text-navigation_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <!--<span class="edit">编辑</span>-->
                <!--<span class="insert-common">加内容</span>-->
                <!--<span class="_del" data-scope="text-navigation_{{temp}}">删除</span>-->

                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="text-navigation_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>

            </div>
        </div>
        <div class="views">
            <!-- <div way-repeat="text-navigation_{{temp}}.list">
                <a href="" class="grid-nav-bar" style="background: #ccc; height: 32px;" way-data="link">
                    <span class="grid-nav-bar-item" style="width: 32px; background: #f44336">
                        <img style="width:32px; height: 32px; " class="img-responsive" way-data="picture"
                             way-default="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png">
                    </span>
                    <span class="grid-nav-bar-item text-left" style="background: #fff" way-data="title">
                        导航名称
                    </span>
                </a>
            </div> -->
            <div class="weui_cells weui_cells_access w-text-nav">
                <div class="list" data-repeat="text-navigation_{{temp}}.list">
                    <a class="weui_cell repeat" href="personal_infor.html" data-tgt="href" data-scope="text-navigation_{{temp}}.list.link">
                        <div class="weui_cell_hd">
                            <img src="${pageContext.request.contextPath}/images/page/icon/ceremony06.png" alt="" class="ezp-cell-icon" class="img-responsive" data-scope="text-navigation_{{temp}}.list.picture"
                                 data-tgt="src">
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p data-scope="text-navigation_{{temp}}.list.title" data-tgt="cot">导航名称</p>
                        </div>
                        <div class="weui_cell_ft"></div>
                    </a>
                </div>
            </div>
        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="text-navigation_{{temp}}">删除</p> -->
            <form role="form" class="form-horizontal clone">

                <div data-repeat="text-navigation_{{temp}}.list">

                    <div class="grid-nav-bar list repeat" style="margin-bottom: 20px;">
                        <div class="grid-nav-bar-item selectPicWrap" style="width: 72px;">

                            <input type="text" class="form-control link-name operate wdn" placeholder="链接名称" autocomplete="off" data-scope="text-navigation_{{temp}}.list.linkname" data-tgt = "vl" >

                            <input type="text" class="form-control link-img plug-in operate picIpt wdn" placeholder="图片链接" autocomplete="off" data-scope="text-navigation_{{temp}}.list.picture" data-tgt = "vl" data-plug = "SelectPic" >

                        </div>
                        <div class="grid-nav-bar-item" style="">
                            <div>
                                <div class="ui-form-horizontal">
                                    <div class="ui-form-fields grid-nav-bar">
                                        <div class="ui-form-field-label grid-nav-bar-item">
                                            <span class="ui-form-field-label-span">导航名称：</span>
                                        </div>
                                        <div class="ui-form-field-cot grid-nav-bar-item">
                                            <input  size="12"  class="ui-form-control width-100 operate" type="text" data-tgt = "vl" data-scope="text-navigation_{{temp}}.list.title">
                                        </div>
                                    </div>
                                </div>
                                <div class="ui-form-horizontal">
                                    <div class="ui-form-fields grid-nav-bar">
                                        <div class="ui-form-field-label grid-nav-bar-item">
                                            <span class="ui-form-field-label-span">导航链接：</span>
                                        </div>
                                        <div class="ui-form-field-cot grid-nav-bar-item">
                                            <input class="plug-in operate com-slt" data-tgt = "vl" data-plug = "Slt"  size="12" class="ui-form-control width-100" data-scope="text-navigation_{{temp}}.list.link" type="text">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="grid-nav-bar-item">
                            <!--<span class="handler" data-action-scope ="text-navigation_{{temp}}.list.insert" >添加</span>-->
                            <!--<span class="handler" data-action-scope ="text-navigation_{{temp}}.list.remove" >删除</span>-->

                            <span class="ctlEditer">

                                <a href="javascript:;" class="handler _insert-common" data-action-scope ="text-navigation_{{temp}}.list.insert" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                                </a>
                                <a href="javascript:;" class="handler" data-action-scope ="text-navigation_{{temp}}.list.remove" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                                </a>

                            </span>


                        </div>
                    </div>

                </div>

                <button type="button" class="ui-btn ui-btn-default handler" style="width: 100%" data-action-scope ="text-navigation_{{temp}}.list.add"> 添加 </button>
            </form>
        </div>
    </div>
</script>

<!--图片导航-->
<script type="text/html" id="img-navigation">
    <div class="common img-navigation common-drag" id="img-navigation_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <!--<span class="edit">编辑</span>-->
                <!--<span class="insert-common">加内容</span>-->
                <!--<span class="_del" data-scope="img-navigation_{{temp}}">删除</span>-->

                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="img-navigation_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>


            </div>
        </div>
        <div class="views">

            <div class="w-img-nav">
                <div class="list">
					<span data-repeat="img-navigation_{{temp}}.list">

			    		<a href="javascript:;" class="w-img-nav-item repeat" data-tgt="href" data-scope="img-navigation_{{temp}}.list.link" way-data="link">
				            <div class="w-imgNav_icon">
				                <img src="${pageContext.request.contextPath}/images/page/icon/ceremony06.png" alt="" class="ezp-cell-icon" class="img-responsive" data-scope="img-navigation_{{temp}}.list.picture" data-tgt="src">
				            </div>
				            <p class="w-imgNav_label" data-scope="img-navigation_{{temp}}.list.title" data-tgt="cot">导航名称</p>
				        </a>

					</span>
                </div>
            </div>

        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="text-navigation_{{temp}}">删除</p> -->
            <form role="form" class="form-horizontal clone"  >

                <div data-repeat="img-navigation_{{temp}}.list">
                    <div class="grid-nav-bar list repeat" style="margin-bottom: 20px;">

                        <div class="grid-nav-bar-item selectPicWrap" style="width: 72px;">

                            <input type="text" class="form-control link-name operate wdn" placeholder="链接名称" autocomplete="off" data-scope="img-navigation_{{temp}}.list.linkname" data-tgt = "vl" >

                            <input type="text" class="form-control link-img plug-in operate picIpt wdn" placeholder="图片链接" autocomplete="off" data-scope="img-navigation_{{temp}}.list.picture" data-tgt = "vl" data-plug = "SelectPic" >
                        </div>
                        <div class="grid-nav-bar-item" style="">
                            <div>
                                <div class="ui-form-horizontal">
                                    <div class="ui-form-fields grid-nav-bar">
                                        <div class="ui-form-field-label grid-nav-bar-item">
                                            <span class="ui-form-field-label-span">导航名称：</span>
                                        </div>
                                        <div class="ui-form-field-cot grid-nav-bar-item">
                                            <input  size="12" class="ui-form-control width-100 operate" type="text" data-tgt = "vl" data-scope="img-navigation_{{temp}}.list.title">
                                        </div>
                                    </div>
                                </div>
                                <div class="ui-form-horizontal">
                                    <div class="ui-form-fields grid-nav-bar">
                                        <div class="ui-form-field-label grid-nav-bar-item">
                                            <span class="ui-form-field-label-span">导航链接：</span>
                                        </div>
                                        <div class="ui-form-field-cot grid-nav-bar-item">
                                            <input class="plug-in operate com-slt" data-tgt = "vl" data-plug = "Slt"  size="12" class="ui-form-control width-100" data-scope="img-navigation_{{temp}}.list.link" type="text">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="grid-nav-bar-item">
                            <!--<span class="handler" data-action-scope ="img-navigation_{{temp}}.list.insert" data-limit="4" >添加</span>-->
                            <!--<span class="handler" data-action-scope ="img-navigation_{{temp}}.list.remove" >删除</span>-->

                            <span class="ctlEditer">
                                <a href="javascript:;" class="handler _insert-common" data-action-scope ="img-navigation_{{temp}}.list.insert" data-limit="4"  data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                                </a>
                                <a href="javascript:;" class="handler" data-action-scope ="img-navigation_{{temp}}.list.remove" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                                 </a>
                             </span>
                        </div>
                    </div>
                </div>

                <button type="button" class="ui-btn ui-btn-default handler" style="width: 100%" data-limit="4" data-action-scope ="img-navigation_{{temp}}.list.add"> 添加 </button>

            </form>
        </div>
    </div>
</script>

<!-- 标题 -->
<script type="text/html" id="title_navigation">
    <div class="common title_navigation common-drag" way-scope="title_navigation_{{temp}}" id="title_navigation_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <!--<span class="edit">编辑</span>-->
                <!--<span class="insert-common">加内容</span>-->
                <!--<span class="_del" data-scope="title_navigation_{{temp}}">删除</span>-->
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="title_navigation_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>
            </div>
        </div>
        <div class="views">
            <div class="w-tit">
                <div class="list">
                    <div class="w-tit-item repeat" data-scope="title_navigation_{{temp}}.list.title" data-tgt="cot">标题标题</div>
                </div>
            </div>
        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="text-navigation_{{temp}}">删除</p> -->
            <form role="form" class="form-horizontal clone">
                <div class="grid-nav-bar list" way-repeat="title_navigation_{{temp}}.list" style="margin-bottom: 20px;">
                    <div class="grid-nav-bar-item repeat" style="">
                        <div>
                            <div class="ui-form-horizontal">
                                <div class="ui-form-fields grid-nav-bar">
                                    <div class="ui-form-field-label grid-nav-bar-item">
                                        <span class="ui-form-field-label-span">标题：</span>
                                    </div>
                                    <div class="ui-form-field-cot grid-nav-bar-item">
                                        <input  size="12" id="title" class="ui-form-control width-100 operate" data-tgt="vl" type="text" data-scope="title_navigation_{{temp}}.list.title">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

</script>

<!-- 商城订单 -->
<script type="text/html" id="mall-orders">
    <div class="common mall-orders common-drag" id="mall-orders_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <!--<span class="edit">编辑</span>-->
                <!--<span class="insert-common">加内容</span>-->
                <!--<span class="_del" data-scope="mall-orders_{{temp}}">删除</span>-->
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="mall-orders_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>
            </div>
        </div>
        <div class="views">

            <div class="mall-order">

                <div class="weui_cells weui_cells_access">
                    <a class="weui_cell" href="/mall/pro/myorder/{品牌}">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>商城订单</p>
                        </div>
                        <div class="weui_cell_ft">查看全部订单</div>
                    </a>
                </div>
                <div class="my-order-act">
                    <div class="weui_tabbar my-top-bar">
                            <a href="/mall/pro/myorder/{品牌}?status=2" class="weui_tabbar_item">
                                <div class="weui_tabbar_icon">
                                    <img src="${pageContext.request.contextPath}/images/page/wx/w_my_bar1.png" alt="">
                                    <span id="waitPayCount_span" class="unread-number" style="display:none">0</span>
                                </div>
                                <p class="weui_tabbar_label">待付款</p>
                            </a>
                            <a href="/mall/pro/myorder/{品牌}?status=3" class="weui_tabbar_item">
                                <div class="weui_tabbar_icon">
                                    <img src="${pageContext.request.contextPath}/images/page/wx/w_my_bar2.png" alt="">
                                    <span id="waitSendCount_span" class="unread-number" style="display:none">0</span>
                                </div>
                                <p class="weui_tabbar_label">待发货</p>
                            </a>
                            <a href="/mall/pro/myorder/{品牌}?status=4" class="weui_tabbar_item">
                                <div class="weui_tabbar_icon">
                                    <img src="${pageContext.request.contextPath}/images/page/wx/w_my_bar3.png" alt="">
                                    <span id="hasSendCount_span" class="unread-number" style="display:none">0</span>
                                </div>
                                <p class="weui_tabbar_label">待收货</p>
                            </a>
                            <a href="/mall/pro/myorder/{品牌}?status=5" class="weui_tabbar_item">
                                <div class="weui_tabbar_icon">
                                    <img src="${pageContext.request.contextPath}/images/page/wx/w_my_bar4.png" alt="">
                                    <span id="finishedCount_span" class="unread-number" style="display:none">0</span>
                                </div>
                                <p class="weui_tabbar_label">待评价</p>
                            </a>
                            <a href="/mall/pro/myorderrt/{品牌}" class="weui_tabbar_item">
                                <div class="weui_tabbar_icon">
                                    <img src="${pageContext.request.contextPath}/images/page/wx/w_my_bar5.png" alt="">
                                    <span id="refCount_span" class="unread-number" style="display:none">0</span>
                                </div>
                                <p class="weui_tabbar_label">退款</p>
                            </a>
                    </div>
                </div>

           </div>





        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="text-navigation_{{temp}}">删除</p> -->
            <p>商城订单展现</p>
        </div>
    </div>
</script>

<!-- 头部 -->
<script type="text/html" id="comheader">

    <div class="common comheader" id="comheader_{{temp}}">
        <div class="handle"></div>
        <div class="views">
            <div class="wx_title" data-scope="comheader_{{temp}}.title" data-tgt="cot">页面标题</div>
            <div  data-scope="comheader_{{temp}}.template" data-tgt="cls">

                <div id="wxHeadSpanHasBind">
                   
                    <div class="my-top  _hide show-style-2">
                        <div class="my-top-ac1" style="  no-repeat; background-size:100% auto;"  data-scope="comheader_{{temp}}.img" data-tgt="bg">
                                <div class="my-top-logo">
                                    <span>
                                        <img class="BrandLogo" alt="">
                                    </span>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="header_{{temp}}">删除</p> -->
            <form role="form" class="form-horizontal">
                <div class="grid-nav-bar" style="margin-bottom: 20px;">
                    <div class="grid-nav-bar-item" style="">
                        <div>
							<div class="ui-form-horizontal">
                                <div class="ui-form-fields grid-nav-bar">
                                    <div class="ui-form-field-label grid-nav-bar-item">
                                        <span class="ui-form-field-label-span">页面名称：</span>
                                    </div>
                                    <div class="ui-form-field-cot grid-nav-bar-item">
                                        <input size="12"  id ="wpageTitle" class="ui-form-control width-60 operate" type="text" data-scope="comheader_{{temp}}.title" data-tgt="vl">
                                    </div>
                                </div>
                            </div>
                            <div class="ui-form-horizontal">
                                <div class="ui-form-fields grid-nav-bar">
                                    <div class="ui-form-field-label grid-nav-bar-item">
                                        <span class="ui-form-field-label-span">页面描述：</span>
                                    </div>
                                    <div class="ui-form-field-cot grid-nav-bar-item">
                                        <input size="12" id ="translation" class="ui-form-control height-50px width-100 operate" type="text" data-scope="" data-tgt="vl">
                                    </div>
                                </div>
                            </div>
                            <div class="ui-form-horizontal">
                                <div class="ui-form-fields grid-nav-bar">
                                    <div class="ui-form-field-label grid-nav-bar-item">
                                        <span class="ui-form-field-label-span">分享LOGO：</span>
                                    </div>
                                    <div class="ui-form-field-cot grid-nav-bar-item selectPicWrap">
                                        <input  size="12" class="ui-form-control width-100 operate picIpt wdn" type="text" data-scope="comheader_{{temp}}.img" data-tgt="vl">
                                        <img   alt=""  data-tgt="src" style="height: 96px; width: 192px;">
                                        <a href="javascript:;" class="select-pic">修改背景图</a>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</script>

<!-- 图片 -->
<script type="text/html" id="picture-navigation">
    <div class="common picture-navigation common-drag" id="picture-navigation_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <!--<span class="edit">编辑</span>-->
                <!--<span class="insert-common">加内容</span>-->
                <!--<span class="_del" data-scope="picture-navigation_{{temp}}">删除</span>-->
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="picture-navigation_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>
            </div>
        </div>
        <div class="views">
            <div class="w-tit">
                <div class="list">
                    <span class="repeat">
                        <img src="${pageContext.request.contextPath}/images/page/icon/ceremony06.png" style="width:100%; height: auto; display: block; " class="img-responsive" data-scope="picture-navigation_{{temp}}.list.picture" data-tgt="src">
                    </span>
                </div>
            </div>
        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="text-navigation_{{temp}}">删除</p> -->
            <form role="form" class="form-horizontal clone">
                <div class="grid-nav-bar list" style="margin-bottom: 20px;" >
                    <div class="grid-nav-bar-item repeat" style="" >
                        <div>
                            <div class="ui-form-horizontal">
                                <div class="ui-form-field-cot grid-nav-bar-item selectPicWrap" style="text-align: left">
                                    <input  size="12" class="ui-form-control width-100 operate picIpt wdn" type="text" data-scope="picture-navigation_{{temp}}.list.picture" data-tgt="vl">
                                    <img src="${pageContext.request.contextPath}/images/page/icon/ceremony06.png" style="width: 372px; max-height: 372px; display: block; " class="img-responsive" data-scope="picture-navigation_{{temp}}.list.picture" data-tgt="src">
                                    <a href="javascript:;" class="select-pic">修改背景图</a>
                                    <p>
                                        图片宽度建议 640 像素，高度任意尺寸不匹配时，<br>图片将被压缩或拉伸心铺满画面
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        </form>
    </div>
    </div>
</script>

<!-- 辅助空白 -->
<script type="text/html" id="uxiliary-blank">
    <div class="common uxiliary-blank common-drag" id="uxiliary-blank_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <!--<span class="edit">编辑</span>-->
                <!--<span class="insert-common">加内容</span>-->
                <!--<span class="_del" data-scope="uxiliary-blank_{{temp}}">删除</span>-->
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="uxiliary-blank_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>
            </div>
        </div>
        <div class="views">
            <div class="w-blank" data-scope="uxiliary-blank_{{temp}}.height" data-tgt="hg" style="height:32px;"></div>
        </div>
        <div class="ctl ui-shadow">
            <!-- <p class="text-right" way-action-remove="text-navigation_{{temp}}">删除</p> -->
            <form role="form" class="form-horizontal clone">
                <div class="grid-nav-bar list" style="margin-bottom: 20px;">
                    <div class="grid-nav-bar-item" style="">
                        <div>
                            <div class="ui-form-horizontal">
                                <div class="ui-form-fields grid-nav-bar">
                                    <div class="ui-form-field-label grid-nav-bar-item">
                                        <span class="ui-form-field-label-span">空白高度：</span>
                                    </div>
                                    <div class="ui-form-field-cot grid-nav-bar-item">
                                        <input  size="12" class="ui-form-control width-100 operate plug-in wdn" type="text" data-plug="RangeSlider"  data-tgt="vl" style="height:32px" data-scope="uxiliary-blank_{{temp}}.height">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- <div class="grid-nav-bar-item">
                                    <span way-action-insert="title_navigation_{{temp}}.list.$$key">添加</span>
                                    <span way-action-remove="title_navigation_{{temp}}.list.$$key" way-persistent>删除</span>
                                </div> -->
                </div>
                <!-- <button type="button" class="ui-btn ui-btn-default" style="width: 100%" way-action-push="title_navigation_{{temp}}.list"> 添加 </button> -->
            </form>
        </div>
    </div>
</script>
<!-- 商品 -->
<script type="text/html" id="add_goods">
    <div class="common add_goods common-drag" id="add_goods_{{temp}}">
        <div class="handle">
            <div class="handle-editer text-right">
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 edit" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="editNode jsHandle jsHandle-1465287830321 insert-common _insert-common" data-toggle="tooltip" data-placement="top" title="" data-original-title="加内容">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img01.png">
                </a>
                <a href="javascript:;" class="deleteNode jsHandle jsHandle-1465287830321 _del" data-scope="img-navigation_{{temp}}" data-toggle="tooltip" data-placement="top" title="" data-original-title="删除">
                    <img src="${pageContext.request.contextPath}/images/page/icon/channelpage_img03.png">
                </a>


            </div>
        </div>
        <div class="views">

            <div class="w-goods-nav">
                <div class="list">
						<ul data-repeat="add_goods_{{temp}}.list" class="find-goods-ul clearfix" id="add_goods-wrapper">

			 <li class="goods-li">
                <a class="goods-a" href="http://www.baidu.com">
				<input type="hidden" id="de" value ="default"/>
                    <div class="goods-product">
                        <div class="goods-product-img"><img src="member/css/img/1.jpg">
                        </div>
                        <div class="goods-product-text">【正版授权小黄人】MEE 梦洁家纺出品床上四件套卡通纯棉被套新品欧美风 Jerry 1.5m(200*230)cm</div>
                        <p class="goods-product-price">￥499.00</p>
                    </div>
                </a>
            </li>
					</ul>
                </div>
            </div>

        </div>
        <div class="ctl ui-shadow">
            <form role="form" class="form-horizontal clone"  >
·                    <div class="grid-nav-bar list repeat" style="margin-bottom: 20px;">

                        <div class="grid-nav-bar-item selectPicWrap"  id="add_goodsS"  style="width: 72px; ">
                            <input type="text" class="form-control link-name operate wdn" placeholder="商品名称" autocomplete="off" data-scope="img-navigation_{{temp}}.list.linkname" data-tgt = "vl" >

                            <input type="text" class="form-control link-img plug-in operate picIpt wdn" placeholder="图片链接" autocomplete="off" data-scope="add_goods_{{temp}}.list.picture" data-tgt = "vl" data-plug = "SelectPic" >
                        </div>
                        <div class="grid-nav-bar-item" style="">
                            
                        </div>
                       
                    </div>
            </form>
        </div>
    </div>
</script>
