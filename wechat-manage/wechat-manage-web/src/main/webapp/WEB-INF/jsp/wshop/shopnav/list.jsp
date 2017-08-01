<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ▼ Common CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/wshop/shopnav/pc_9aa97995f0.css"
      onerror="_cdnFallback(this)" media="screen">
<!-- ▲ Common CSS -->
<!-- ▼ App CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/wshop/shopnav/shopnav_cb5ddd6637.css"
      onerror="_cdnFallback(this)" media="screen">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/template.js"></script>
<!-- ▲ App CSS -->
<style type="text/css">
    /*.page-content-wrapper .page-content {
        margin-left: 235px;
        margin-top: 0px;
        min-height: 600px;
        padding: 25px 20px 10px 20px;
    }*/

    .edit-shopnav-on-page .checkbox.inline {
        margin-right: 26px;
        margin-left: 0 !important;
    }

    .home {
        background: rgba(0, 0, 0, 0) url("${pageContext.request.contextPath}/images/page/icon/ico_home.png") no-repeat scroll center center / 24px 24px;
        color: #333;
        display: inline-block;
        font-size: 14px;
        font-weight: bold;
        height: 40px;
        text-indent: -10000px;
        vertical-align: top;
        width: 43px;
    }

    .arrow-weixin {
        background: url("${pageContext.request.contextPath}/images/page/icon/textmenuicon.png") no-repeat scroll 0 0;
        width: 15px;
        height: 15px;
        background-size: 15px 15px;
        vertical-align: text-bottom;
    }

    .app-design .app-preview .app-config .app-field h1 {
        padding: 18px 60px 0 60px;
        height: 64px;
        line-height: 46px;
        font-size: 16px;
        color: #fff;
        font-weight: bold;
        text-align: center;
        background: url("${pageContext.request.contextPath}/images/page/icon/titlebar.png") no-repeat;
    }

    .ui-shadow-top {
        box-shadow: 0 -2px 2px 0 rgba(0, 0, 0, 0.1);
    }

    .ui-bottom-fixedbtn {
        background: #fff none repeat scroll 0 0;
        bottom: 0;
        left: 235px;
        padding: 10px 0;
        position: fixed;
        right: 0;
        text-align: center;
        z-index: 999;
    }
</style>
<!-- ▼ Main container -->
<%--<div id="app-container" class="container">--%>
<%--    <div id="app-third-sidebar" class="js-app-third-sidebar">
        <div class="zent-breadcrumb js-page-breadcrumb">
            <span>店铺导航</span>
        </div>
    </div>--%>
<%-- <div id="app-container-top"></div>
 <div class="app">--%>
<div class="app-inner clearfix" style="margin: 0;">
    <div class="app-init-container">
        <div class="app__content js-app-main">
            <div class="app-design clearfix without-add-region">
                <div class="widget-app-board ui-box">
                    <div class="widget-app-board-info">
                        <h3>店铺导航</h3>

                        <div>
                            <p>店铺的各个页面可以通过导航串联起来。通过精心设置的导航，方便买家在栏目间快速切换，引导买家前往您期望的页面。</p>

                            <p><a href="http://kdt.im/JP9vu8XPp" target="_blank">查看【店铺导航】介绍及设置教程</a></p>
                        </div>
                    </div>
                    <div class="widget-app-board-control">
                        <label class="js-switch ui-switcher pull-right"></label>
                    </div>
                </div>
                <div class="app-preview">
                    <div class="app-header"></div>
                    <div class="app-entry">
                        <div class="app-config js-config-region">
                            <div class="app-field clearfix editing"><h1>
                                <span>[页面标题]</span>
                            </h1>

                                <div class="preview-nav-menu">
                                    <div class="js-navmenu nav-show nav-menu-1 nav-menu has-menu-3 ">
                                        <div class="nav-special-item">
                                            <a href="javascript:;" class="home">主页</a>
                                        </div>
                                        <div class="js-nav-preview-region nav-items-wrap">
                                            <div class="nav-items">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="app-fields js-fields-region">
                            <div id="18598085" class="app-fields ui-sortable"></div>
                        </div>
                    </div>
                    <div class="js-add-region">
                        <div></div>
                    </div>
                </div>
                <div class="app-sidebar" style="margin-top: 129px;">
                    <div class="arrow"></div>
                    <div class="app-sidebar-inner js-sidebar-region">
                        <div>
                            <form class="form-horizontal edit-shopnav" novalidate="">
                                <div class="edit-shopnav-on-page">
                                    <%-- <div>将导航应用在以下页面：</div>
                                     <div>
                                         <label class="checkbox inline">
                                             <input name="on_page" value="1" checked="checked" type="checkbox">店铺主页
                                         </label>
                                         <label class="checkbox inline">
                                             <input name="on_page" value="4" checked="checked" type="checkbox">会员主页
                                         </label>
                                         <label class="checkbox inline">
                                             <input name="on_page" value="3" checked="checked" type="checkbox">微页面及分类
                                         </label>
                                         <label class="checkbox inline">
                                             <input name="on_page" value="6" checked="checked" type="checkbox">商品分组
                                         </label>
                                         <label class="checkbox inline">
                                             <input name="on_page" value="7" checked="checked" type="checkbox">商品搜索
                                         </label>
                                     </div>--%>
                                </div>
                                <div class="edit-shopnav-header clearfix">
                                    <span>当前模版：</span>
                                    <span>微信公众号自定义菜单样式</span>
                                    <a href="javascript:;"
                                       class="zent-btn zent-btn-primary pull-right js-select-nav-style">修改模版</a>
                                </div>
                                <div class="js-main-icon-setting main-icon-setting">
                                </div>
                                <div class="js-nav-region clearfix">
                                    <ul class="choices ui-sortable">
                                    </ul>
                                </div>
                                <p class="add-shopnav js-add-nav" style="display: block;">+ 添加一级导航</p>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="app-actions" style="display: block; bottom: 0px;">
                    <%-- <div class="form-actions text-center">
                         <button class="zent-btn zent-btn-primary js-btn-save">保存</button>
                         <button class="zent-btn zent-btn-primary js-btn-preview">预览</button>
                     </div>--%>
                </div>
            </div>
        </div>
    </div>
    <div class="notify-bar js-notify animated hinge" style="">
        <div class="ui-bottom-fixedbtn ui-shadow-top">
            <button class="btn btn-primary js-btn-save">保存</button>
            <button class="btn btn-primary js-btn-preview">预览</button>
        </div>
    </div>
</div>
<%-- </div>--%>
<%--</div>--%>
<div id="title-region"></div>
<!-- ▲ Main container -->
<script id="menu1" type="text/html">
    {{each data as menu i}}
    <li class="choice">
        <div class="first-nav">
            <h3>一级导航</h3>

            <div class="js-first-nav-item-meta-region">
                <div>
                    <div class="shopnav-item">
                        <div class="shopnav-item-title">
                            <span class="js-edit-title">{{menu.title}}</span>
                        </div>
                        <div class="shopnav-item-link">
                            <span class="pull-left shopnav-item-split">|</span>
                            {{if menu.second.length>0}}
                            <span class="pull-left c-gray">使用二级导航后主链接已失效。</span>
                            {{/if}}
                            {{if menu.second.length==0}}
                            <span class="pull-left">链接：</span>

                            <div class="pull-left">
                                <div class=" clearfix">
                                    <div class="pull-left js-link-to link-to">
                                        <a href="{{menu.linkUrl}}"
                                           target="_blank"
                                           class="new-window link-to-title">
                                            [{{menu.title}}]
                                        </a>
                                    </div>
                                    <div class="dropdown hover pull-right">
                                        <a class="dropdown-toggle  shopnav-item-action"
                                           href="javascript:;">修改</a>
                                        <ul class="dropdown-menu open">
                                            <li><a class="js-modal-magazine"
                                                   data-type="feature"
                                                   href="javascript:;">微页面及分类</a>
                                            </li>
                                            <li><a class="js-modal-goods"
                                                   data-type="goods"
                                                   href="javascript:;">商品及分类</a>
                                            </li>

                                            <li><a class="js-modal-activity"
                                                   data-type="activity"
                                                   href="javascript:;">营销活动</a>
                                            </li>
                                            <li><a class="js-modal-survey"
                                                   data-type="survey"
                                                   href="javascript:;">投票调查</a>
                                            </li>
                                            <li><a class="js-modal-homepage"
                                                   data-type="homepage"
                                                   href="javascript:;">店铺主页</a>
                                            </li>
                                            <li>
                                                <a class="js-modal-usercenter"
                                                   data-type="usercenter"
                                                   href="javascript:;">会员主页</a>
                                            </li>
                                            <li><a class="js-modal-cart"
                                                   data-type="cart"
                                                   href="javascript:;">购物车</a>
                                            </li>
                                            <li><a class="js-modal-allgoods"
                                                   data-type="allgoods"
                                                   href="javascript:;">全部商品</a>
                                            </li>
                                            <li><a class="js-contact-link"
                                                   data-type="contact"
                                                   href="javascript:;">在线客服</a>
                                            </li>
                                            <li>
                                                <a class="js-pointsstore-link"
                                                   data-type="pointsstore"
                                                   href="javascript:;">积分商城</a>
                                            </li>
                                            <li><a class="js-modal-links"
                                                   data-type="link"
                                                   href="javascript:;">自定义外链</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            {{/if}}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="second-nav" data-first-nav-index="0">
            <h4>二级导航</h4>

            <div class="actions">
                <span class="action delete close-modal" title="删除">×</span>
            </div>
            <div class="js-second-nav-region">
                <ul class="choices ui-sortable">
                    {{each menu.second as subMenu j}}
                    <li class="choice">
                        <div class="shopnav-item">
                            <div class="actions">
                                <span class="action delete close-modal" title="删除">×</span>
                            </div>
                            <div class="shopnav-item-title">
                                <span class="js-edit-title">{{subMenu.title}}</span>
                            </div>
                            <div class="shopnav-item-link">
                                <span class="pull-left shopnav-item-split">|</span>
                                <span class="pull-left">链接：</span>

                                <div class="pull-left">
                                    <div class="dropdown hover">
                                        <a class="dropdown-toggle js-link-to" href="{{subMenu.linkUrl}}">[{{subMenu.title}}]</a>
                                        <ul class="dropdown-menu">
                                            <li><a class="js-modal-magazine" data-type="feature" href="javascript:;">微页面及分类</a>
                                            </li>
                                            <li><a class="js-modal-goods" data-type="goods"
                                                   href="javascript:;">商品及分类</a></li>
                                            <li><a class="js-modal-activity" data-type="activity" href="javascript:;">营销活动</a>
                                            </li>
                                            <li><a class="js-modal-survey" data-type="survey"
                                                   href="javascript:;">投票调查</a></li>
                                            <li><a class="js-modal-homepage" data-type="homepage" href="javascript:;">店铺主页</a>
                                            </li>
                                            <li><a class="js-modal-usercenter" data-type="usercenter"
                                                   href="javascript:;">会员主页</a></li>
                                            <li><a class="js-modal-cart" data-type="cart" href="javascript:;">购物车</a>
                                            </li>
                                            <li><a class="js-modal-allgoods" data-type="allgoods" href="javascript:;">全部商品</a>
                                            </li>
                                            <li><a class="js-contact-link" data-type="contact"
                                                   href="javascript:;">在线客服</a></li>
                                            <li><a class="js-pointsstore-link" data-type="pointsstore"
                                                   href="javascript:;">积分商城</a></li>
                                            <li><a class="js-modal-links" data-type="link" href="javascript:;">自定义外链</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    {{/each}}
                </ul>
            </div>
            <p class="add-shopnav add-second-shopnav js-add-second-nav"
               style="display: block;">+ 添加二级导航</p>
        </div>
    </li>
    {{/each}}
</script>
<script id="menu2" type="text/html">
    <li class="choice">
        <div class="shopnav-item">
            <div class="actions">
                <span class="action delete close-modal" title="删除">×</span>
            </div>
            <div class="shopnav-item-title">
                <span class="js-edit-title">标题</span>
            </div>
            <div class="shopnav-item-link">
                <span class="pull-left shopnav-item-split">|</span>
                <span class="pull-left">链接：</span>

                <div class="pull-left">
                    <div class="dropdown hover">
                        <a class="dropdown-toggle js-link-to" href="javascript:;;">选择链接页面</a>
                        <ul class="dropdown-menu">
                            <li><a class="js-modal-magazine" data-type="feature" href="javascript:;">微页面及分类</a></li>
                            <li><a class="js-modal-goods" data-type="goods" href="javascript:;">商品及分类</a></li>
                            <li><a class="js-modal-activity" data-type="activity" href="javascript:;">营销活动</a></li>
                            <li><a class="js-modal-survey" data-type="survey" href="javascript:;">投票调查</a></li>
                            <li><a class="js-modal-homepage" data-type="homepage" href="javascript:;">店铺主页</a></li>
                            <li><a class="js-modal-usercenter" data-type="usercenter" href="javascript:;">会员主页</a></li>
                            <li><a class="js-modal-cart" data-type="cart" href="javascript:;">购物车</a></li>
                            <li><a class="js-modal-allgoods" data-type="allgoods" href="javascript:;">全部商品</a></li>
                            <li><a class="js-contact-link" data-type="contact" href="javascript:;">在线客服</a></li>
                            <li><a class="js-pointsstore-link" data-type="pointsstore" href="javascript:;">积分商城</a></li>
                            <li><a class="js-modal-links" data-type="link" href="javascript:;">自定义外链</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </li>
</script>
<script id="menuTmp" type="text/html">
    <li class="choice">
        <div class="first-nav">
            <h3>一级导航</h3>

            <div class="js-first-nav-item-meta-region">
                <div>
                    <div class="shopnav-item">
                        <div class="shopnav-item-title">
                            <span class="js-edit-title">标题</span>
                        </div>
                        <div class="shopnav-item-link">
                            <span class="pull-left shopnav-item-split">|</span>
                            <span class="pull-left">链接：</span>

                            <div class="pull-left">
                                <div class=" clearfix">
                                    <div class="pull-left js-link-to link-to">
                                        <a href="javascript:;;"
                                           target="_blank"
                                           class="new-window link-to-title">
                                            选择链接页面
                                        </a>
                                    </div>
                                    <div class="dropdown hover pull-right">
                                        <a class="dropdown-toggle  shopnav-item-action"
                                           href="javascript:;">修改</a>
                                        <ul class="dropdown-menu open">
                                            <li><a class="js-modal-magazine"
                                                   data-type="feature"
                                                   href="javascript:;">微页面及分类</a>
                                            </li>
                                            <li><a class="js-modal-goods"
                                                   data-type="goods"
                                                   href="javascript:;">商品及分类</a>
                                            </li>

                                            <li><a class="js-modal-activity"
                                                   data-type="activity"
                                                   href="javascript:;">营销活动</a>
                                            </li>
                                            <li><a class="js-modal-survey"
                                                   data-type="survey"
                                                   href="javascript:;">投票调查</a>
                                            </li>
                                            <li><a class="js-modal-homepage"
                                                   data-type="homepage"
                                                   href="javascript:;">店铺主页</a>
                                            </li>
                                            <li>
                                                <a class="js-modal-usercenter"
                                                   data-type="usercenter"
                                                   href="javascript:;">会员主页</a>
                                            </li>
                                            <li><a class="js-modal-cart"
                                                   data-type="cart"
                                                   href="javascript:;">购物车</a>
                                            </li>
                                            <li><a class="js-modal-allgoods"
                                                   data-type="allgoods"
                                                   href="javascript:;">全部商品</a>
                                            </li>
                                            <li><a class="js-contact-link"
                                                   data-type="contact"
                                                   href="javascript:;">在线客服</a>
                                            </li>
                                            <li>
                                                <a class="js-pointsstore-link"
                                                   data-type="pointsstore"
                                                   href="javascript:;">积分商城</a>
                                            </li>
                                            <li><a class="js-modal-links"
                                                   data-type="link"
                                                   href="javascript:;">自定义外链</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="second-nav" data-first-nav-index="0">
            <h4>二级导航</h4>

            <div class="actions">
                <span class="action delete close-modal" title="删除">×</span>
            </div>
            <div class="js-second-nav-region">
                <ul class="choices ui-sortable">
                </ul>
            </div>
            <p class="add-shopnav add-second-shopnav js-add-second-nav"
               style="display: block;">+ 添加二级导航</p>
        </div>
    </li>
</script>
<script id="preview" type="text/html">
    {{each data as menu i}}
    <div class="nav-item" style="width: {{100/data.length}}%;">
        {{if menu.second.length==0}}
        <a class="mainmenu js-mainmenu"
           href="{{menu.linkUrl}}"
           target="_blank">
            <span class="mainmenu-txt">
                <i class=""></i>
                {{menu.title}}</span>
        </a>
        {{/if}}
        {{if menu.second.length>0}}
        <a class="mainmenu js-mainmenu"
           target="_blank">
            <span class="mainmenu-txt">
                <i class="arrow-weixin"></i>
                {{menu.title}}</span>
        </a>
        {{/if}}
        {{if menu.second.length>0}}
        <div class="submenu js-submenu" style="{{if i==0}}left:44px; right: auto;{{/if}}
          {{if i==1}}left:136px; right: auto;{{/if}}
          {{if i==2}}left:auto; right: 8px;{{/if}} display: none;">
            <span class="arrow before-arrow" style="left: 42px; right: auto;"></span>
            <span class="arrow after-arrow" style="left: 42px; right: auto;"></span>

            <div class="js-nav-2nd-region">
                <ul>
                    {{each menu.second as subMenu j}}
                    <li>
                        <a href="{{subMenu.linkUrl}}" target="_blank">{{subMenu.title}}</a>
                    </li>
                    {{/each}}
                </ul>
            </div>
        </div>
        {{/if}}
    </div>
    {{/each}}
</script>
<script id="titleTmp" type="text/html">
    <div class="ui-popover top-center" style="top: {{top}}px; left: {{left}}px;">
        <div class="ui-popover-inner">
            <span></span>
            <input class="input-medium js-name-input" type="text" value="{{title}}" placeholder="" maxlength="20"
                   style="margin-bottom: 0;">
            <a href="javascript:;" class="zent-btn zent-btn-primary js-save" style="margin-left: 20px;">确定</a>
            <a href="javascript:;" class="zent-btn js-cancel">取消</a>
        </div>
        <div class="arrow"></div>
    </div>
</script>
<script id="shopnavOnPage" type="text/html">
    <div>将导航应用在以下页面：</div>
    <div>
        {{each data as nav i}}
        <label class="checkbox inline">
            <input name="on_page" value="{{nav.sid}}" type="checkbox" {{if nav.shopId==1}} checked="checked" {{/if}}
            {{nav.pageTypeName}}
        </label>
        {{/each}}
    </div>
</script>
<script type="text/javascript">
    $(function () {
        var data = {};
        $.ajax({
            type: "post",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: rootPath + '/tNav/selectByShopId.shtml',
            dataType: "json",
            data: {
                'shopId': "1"
            },
            success: function (response) {
                console.log("response", response);
                if (response.success) {
                    console.log(response);
                    $(".nav-items").html(template('preview', response));
                    $(".js-mainmenu").click(function (e) {
                        $(".js-submenu").hide();
                        $(this).parent().find(".js-submenu").show();
                        stopPropagation(e);
                    });

                    $(document).click(function () {
                        $(".js-submenu").hide();
                    });

                    var html = template('menu1', response);
                    $(".js-nav-region ul").eq(0).append(html);
                    var liCount = $(".js-first-nav-item-meta-region").length;
                    if (liCount == 3) {
                        $(".js-add-nav").addClass("hide");
                    }


                    $(".js-add-nav").click(function () {
                        var html = template('menuTmp', data);
                        $(".js-nav-region ul").eq(0).append(html);
                        var liCount = $(".js-first-nav-item-meta-region").length;
                        if (liCount == 3) {
                            $(this).addClass("hide");
                        }
                        $(".close-modal").click(function () {
                            $(this).closest('.choice').remove();
                            var liCount = $(".js-first-nav-item-meta-region").length;
                            $(".js-add-nav").removeClass("hide");
                        });
                        $(".js-nav-region ul").eq(0).children("li:last-child").find(".first-nav .dropdown-menu li a").click(function () {
                            $(this).closest(".shopnav-item").find(".link-to-title").text("[" + $(this).text() + "]");
                            $(this).closest(".shopnav-item").find(".js-edit-title").text($(this).text());
                        });
                        $(".js-nav-region ul").eq(0).children("li:last-child").find(".js-add-second-nav").click(function () {
                            var html = template('menu2', data);
                            $(this).parent(".second-nav").find("ul").append(html);
                            $(".close-modal").click(function () {
                                delSubMenu($(this));
                                $(this).closest('.choice').remove();
                            })
                            $(this).parent(".second-nav").find(".dropdown-menu li a").click(function () {
                                $(this).closest(".shopnav-item").find(".js-link-to").text("[" + $(this).text() + "]");
                            });
                            addSubMenu($(this));
                        });
                        $(".nav-items").html(template('preview', JSON.parse(JSON.stringify(getMenuData()))));
                        $(".js-mainmenu").click(function () {
                            $(".js-submenu").css("display", "none");
                            $(this).parent().find(".js-submenu").css({"display": "block"});
                        });
                    });

                    $(".js-add-second-nav").click(function () {
                        var html = template('menu2', data);
                        $(this).parent(".second-nav").find("ul").append(html);
                        $(".close-modal").click(function () {
                            delSubMenu($(this));
                            $(this).closest('.choice').remove();

                        })
                        $(this).parent(".second-nav").find(".dropdown-menu li a").click(function () {
                            $(this).closest(".dropdown").find(".dropdown-toggle").text("[" + $(this).text() + "]");
                        });
                        addSubMenu($(this));

                        $(this).parent(".second-nav").find(".js-edit-title").click(function () {
                            JsEditTitle($(this));
                        });
                    });

                    $(".close-modal").click(function () {
                        $(this).closest('.choice').remove();
                        var liCount = $(".js-first-nav-item-meta-region").length;
                        $(".js-add-nav").removeClass("hide");
                        $(".nav-items").html(template('preview', JSON.parse(JSON.stringify(getMenuData()))));
                    });

                    $(".first-nav .dropdown-menu li a").click(function () {
                        $(this).closest(".shopnav-item").find(".link-to-title").text("[" + $(this).text() + "]");
                        $(this).closest(".shopnav-item").find(".js-edit-title").text($(this).text());
                    });
                    $(".second-nav").find(".dropdown-menu li a").click(function () {
                        $(this).closest(".dropdown").find(".dropdown-toggle").text("[" + $(this).text() + "]");
                        $(this).closest(".shopnav-item").find(".js-edit-title").text($(this).text());
                    });
                    $(".js-edit-title").click(function () {
                        JsEditTitle($(this));
                    });

                } else {
                }
            },
            error: function () {
            }
        });

        $.ajax({
            type: "post",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: rootPath + '/tNav/shopConfigByShopId.shtml',
            dataType: "json",
            data: {
                'shopId': "1"
            },
            success: function (response) {
                if (response.success) {
                    var configValue = response.data[0].configValue;
                    if (!!configValue && configValue != null && configValue != '' && configValue == "1") {
                        $(".js-switch").addClass("ui-switcher-on");
                    }
                    else {
                        $(".js-switch").addClass("ui-switcher-off");
                    }
                } else {
                }
            },
            error: function () {
            }
        });
        $.ajax({
            type: "post",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: rootPath + '/tNav/navRelByShopId.shtml',
            dataType: "json",
            data: {
                'shopId': "1"
            },
            success: function (response) {
                console.log("chk:", response);
                if (response.success) {
                    $(".edit-shopnav-on-page").html(template('shopnavOnPage', response));
                } else {
                }
            },
            error: function () {
            }
        });
        $(".js-btn-save").click(function () {
            var oUl = $(".js-nav-region ul>li");
            var olis = oUl.children();
            var menuArr = new Array();
            $(".first-nav").each(function () {
                var submenuArr = new Array();
                $(this).parent().find(".second-nav ul").eq(0).children("li").each(function () {
                    var submenu = getsubMenuJsonData($(this));
                    submenuArr.push(submenu);
                });
                var menu = getMenuJsonData($(this), submenuArr);
                menuArr.push(menu);
            });
            var configValue = "0";
            if ($(".js-switch").hasClass("ui-switcher-on")) {
                configValue = "1";
            }
            var configArr = new Array();
            var chkArr = new Array();
            var config = {
                "configKey": "shopNav",
                "configValue": configValue,
                "shopId": "1"
            };
            configArr.push(config);
            var arrChk = $("input[name='on_page'][checked]");

            $(arrChk).each(function () {
                var chk = {
                    "shopId": "1",
                    "pageTypeSid": this.value
                };
                chkArr.push(chk);
            });
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/tNav/insertSelective.shtml',
                dataType: "json",
                data: {
                    'str': JSON.stringify(menuArr),
                    'config': JSON.stringify(configArr),
                    'navrel': JSON.stringify(chkArr)
                },
                success: function (response) {
                    console.log("response", response);
                    if (response == "success") {
                        layer.alert("保存成功");
                    } else {
                    }
                },
                error: function () {
                }
            });
        });

        $(".js-switch").click(function () {
            $(this).toggleClass("ui-switcher-on");
            $(this).toggleClass("ui-switcher-off");
        });

        $(".js-btn-preview").click("click", function () {
            previewFun();
        });
    });

    function JsEditTitle(obj) {
        var X = $(obj).offset().top;
        var Y = $(obj).offset().left;

        var titleData = {
            "top": X - 15,
            "left": Y - 150,
            "title": $(obj).text()
        };

        $("#title-region").append(template('titleTmp', titleData));
        console.log($("#title-region").children("div:last-child"));

        $("#title-region").children("div:last-child").find(".js-cancel").click(function () {
            $(this).closest(".ui-popover").remove();
        });
        $("#title-region").children("div:last-child").find(".js-save").click(function () {
            $(obj).text($(this).closest(".ui-popover").find(".js-name-input").val());
            $(this).closest(".ui-popover").remove();
        });
    }

    function addSubMenu(object) {
        $(object).closest(".choice").find(".first-nav").find(".shopnav-item-link span").eq(1).text("使用二级导航后主链接已失效");
        $(object).closest(".choice").find(".first-nav").find(".shopnav-item-link span").eq(1).addClass("c-gray");
        $(object).closest(".choice").find(".first-nav").find(".shopnav-item-link div").eq(0).addClass("hide");
    }
    function delSubMenu(object) {
        var subli = $(object).closest(".js-second-nav-region ul").eq(0).children("li").length;
        if (subli == 1) {
            $(object).closest(".second-nav").siblings(".first-nav").find(".shopnav-item-link span").eq(1).text("链接:");
            $(object).closest(".second-nav").siblings(".first-nav").find(".shopnav-item-link span").eq(1).removeClass("c-gray");
            $(object).closest(".second-nav").siblings(".first-nav").find(".shopnav-item-link div").eq(0).removeClass("hide");
        }
    }
    function getMenuData() {
        var menuArr = new Array();
        $(".first-nav").each(function () {
            var submenuArr = new Array();
            $(this).parent().find(".second-nav ul").eq(0).children("li").each(function () {
                var submenu = getsubMenuJsonData($(this));
                submenuArr.push(submenu);
            });
            var menu = getMenuJsonData($(this), submenuArr);
            menuArr.push(menu);
        });
        var data = {"data": menuArr};
        return data;
    }

    function getMenuJsonData(object, subMenu) {
        var menu = {
            "shopSid": "1",
            "title": $(object).find(".js-edit-title").text(),
            "linkUrl": $(object).find(".js-link-to a").attr("href"),
            "second": subMenu
        };
        return menu;
    }

    function getsubMenuJsonData(object) {
        var url = $(object).find(".js-link-to a").attr("href");
        console.log("url", url);
        if (url == 'javascript:;' || typeof(url) == 'undefined') {
            url = "http://";
        }
        var menu = {
            "shopSid": "1",
            "title": $(object).find(".shopnav-item-title span").text(),
            "linkUrl": url,
        };
        return menu;
    }

    function previewFun() {
        pageii = layer.open({
            title: "导航预览",
            type: 2,
            area: ["600px", "80%"],
            content: rootPath + '/wshopnav/navpreview.shtml'
        });

    }

    function stopPropagation(e) {
        if (e.stopPropagation)
            e.stopPropagation();
        else
            e.cancelBubble = true;
    }

</script>