<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
   <%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/wshop/shopnav/pc_9aa97995f0.css"
          onerror="_cdnFallback(this)" media="screen"> --%>
    <!-- ▲ Common CSS -->
    <!-- ▼ App CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/wshop/shopnav/shopnav_cb5ddd6637.css"
          onerror="_cdnFallback(this)" media="screen">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/front/template.js"></script>
    <style type="text/css">
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
    </style>
</head>
<body>
<div id="shop-nav" style="display: block;">
    <div class="js-navmenu js-footer-auto-ele shop-nav nav-menu nav-menu-1 has-menu-3">
        <div class="nav-special-item">
            <a href="https://h5.youzan.com/v2/showcase/homepage?kdt_id=15106416" class="home">主页</a>
        </div>
        <div class="nav-items-wrap">
        </div>
    </div>
</div>
</body>
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
        <!-- 子菜单 -->
        {{if menu.second.length>0}}
        <div class="submenu js-submenu" style="opacity: 1; right: auto;">
            <span class="arrow before-arrow" style="left: 38px; right: auto;"></span>
            <span class="arrow after-arrow" style="left: 38px; right: auto;"></span>

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
<script type="text/javascript">
    $(function () {
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
                    $(".nav-items-wrap").html(template('preview', response));
                }
                $(".js-mainmenu").click(function (e) {
                    $(".js-submenu").hide();
                    var Y = $(this).offset().left;
                    var w = $(this).width();
                    $(this).parent().find(".js-submenu").css({"display": "block", "left": w / 2 + Y - 43});
                    stopPropagation(e);
                });
                $(document).click(function(){
                    $(".js-submenu").hide();
                });
            }
        });
    });
    function stopPropagation(e) {
        if (e.stopPropagation)
            e.stopPropagation();
        else
            e.cancelBubble = true;
    }

</script>
</html>
