<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/front/template.js"></script>
    <style type="text/css">
        .wrapper {
            height: 100%;
            margin: 0 auto;
            width: 100%;
        }

        .bottom {
            height: 100%;
            /*overflow: auto;*/
            padding-top: 50px;
        }

        .appphone_header {
            background-image: url("../images/app/app_01.png");
            height: 91px;
            width: 361px;
        }

        .appphone_container {
            background-image: url("../images/app/app_02.png");
            min-height: 473px;
            position: relative;
            width: 361px;
        }

        .appphone_btn {
            background-image: url("../images/app/app_btn.png");
            height: 186px;
            left: -1px;
            position: absolute;
            width: 6px;
        }

        .appphone_main {
            margin-left: 23px;
            width: 316px;
        }

        .appphone_bottom {
            background-image: url("../images/app/app_03.png");
            height: 91px;
            width: 361px;
        }

        .appphone_top {
            background-image: url("../images/app/ceremony_top.png");
            background-repeat: no-repeat;
            color: #fff;
            height: 61px;
            padding-top: 30px;
            position: relative;
            text-align: center;
        }

        .appphone_banner {
            height: 158px;
            width: 316px;
        }

        .appphone_content_nav {
            background-color: #fff;
            padding: 10px;
        }

        .appphone_content_navitem:not(:last-child) {
            border-bottom: 1px solid #d9d9d9;
            margin-bottom: 10px;
            padding-bottom: 10px;
        }

        .cmpMenu, .cmpMenu2{
           padding-left: 0px;

        }

        .cmpFooter {
            /* width: 100%; */
            bottom: 0;
            position: absolute;
            width: 318px;
        }

        .cmpTab {
            display: table;
            width: 100%;
            height: 40px;
            border-top: 1px solid #CCCCCC;
        }

        .cmpTab-cell {
            display: table-cell;
            vertical-align: middle;
            text-align: center;
            background: #fff;
        }

        .cmpTab .oShow {
            width: 40px;
            border-right: 1px solid #CCCCCC;
            /*float: left;*/
        }

        .cmpMenu .cmpMenuL1 {
            position: relative;
            /*width: 100%;*/
            float: left;
            text-align: center;
            border-right: 1px solid #ccc;
            height: 39px;
            padding: 8px;
            cursor: pointer;
        }

        .ol, ul {
            list-style: none
        }

        ul, li {
            list-style: none;
        }

        .cmpMenu .cmpMenuL1 .cmpMenuL1con {
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }

        .cmpMenuL1 li:last-child {
            border-bottom: 1px solid #ccc !important;
        }

        .cmpMenuL1 li {
            border-top: 1px solid #ccc;
        }

        .cmpMenu .cmpMenuL1 ul {
            position: absolute;
            bottom: 49px;
            left: 0;
            z-index: 12;
            /*padding: 5px 8px;*/
            text-align: ceneter;
            border-radius: 5px;
            font-size: 13px;
            line-height: 41px;
            border: 1px solid #ccc;
            box-shadow: 0 0 3px #fff inset;
            background-color: #fafafa;
            width: 100%;
            max-width: 138px;
            display: none;
        }

        .cmpFooter .cmpMenu .on ul {
            display: block;
        }

        .cmpMenu .cmpMenuL1 ul:after {
            display: block;
            height: 6px;
            content: "";
            /*background: url("static/image/dn.gif") no-repeat center;*/
            position: absolute;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div id="div_container" class="container">
        <div class="row">
            <div class="col-md-10 content-wrapper">
                <div class="content">
                    <div id="div_main" class="main-content">
                        <div class="appphone_details_left">
                            <div class="appphone_header"></div>
                            <div class="appphone_container">
                                <div class="appphone_btn"></div>
                                <div class="appphone_main">
                                    <div class="appphone_top">
                                        <span class="apptop_tittle" data-bind="text:brandName">北京市百货大楼</span>
                                    </div>
                                    <div class="appphone_content">
                                        <div class="cmpFooter">
                                            <div class="cmpTab">
                                                <div class="cmpTab-cell oShow">
                                                    <img src="http://static.ezrpro.com/assets/img/jpn.png"
                                                         style="height:38px; width:40px;">
                                                </div>
                                                <div class="cmpTab-cell showMenu">
                                                    <ul class="cmpMenu">
                                                        <%--<li class="cmpMenuL1 " style="width: 50%;">
                                                            <div class="cmpMenuL1con">
                                                                <img src="http://static.ezrpro.com/assets/img/textmenu.png"
                                                                     style="height:15px;">
                                                                <span>VIP</span>
                                                            </div>
                                                            <ul class="cmpMenu2">
                                                                <li class="cmpMenuL2">电子会员卡</li>
                                                                <li class="cmpMenuL2">会员首页</li>
                                                                <li class="cmpMenuL2">年终回馈</li>
                                                            </ul>
                                                        </li>
                                                        <li class="cmpMenuL1 on" style="width: 50%;">
                                                            <div class="cmpMenuL1con">
                                                                <img src="http://static.ezrpro.com/assets/img/textmenu.png"
                                                                     style="height:15px;">
                                                                <span>精彩活动</span>
                                                            </div>
                                                            <ul>
                                                                <li class="cmpMenuL2">靓照打印</li>
                                                            </ul>
                                                        </li>--%>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="appphone_bottom"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script id="test" type="text/html">
    {{each children as menu i}}
    <li class="cmpMenuL1" style="width: {{100/children.length}}%;">
        <div class="cmpMenuL1con">
            <img src="http://static.ezrpro.com/assets/img/textmenu.png" style="height:15px;">
            <span>{{menu.text}} </span>
        </div>
        {{if menu.children.length>0}}
        <ul class="cmpMenu2">
            {{each menu.children as subMenu j}}
            <li class="cmpMenuL2">{{subMenu.text}}</li>
            {{/each}}
        </ul>
        {{/if}}
    </li>
    {{/each}}
</script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            type: "post",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: rootPath + "/menu/getMenus.shtml",
            async: false,
            dataType: "json",
            success: function (response) {
                console.log(response);
                var html = template('test', response);
                $(".cmpMenu").html(html);
//                document.getElementById('cmpMenu').innerHTML = html;
            }
        });

        $(".cmpMenuL1").click(function (e) {
            $(".cmpMenuL1").each(function(){
                $(this).removeClass("on");
            })
            $(this).addClass("on");
            stopPropagation(e);
        });
        $(document).click(function(){
            $(".cmpMenuL1").each(function(){
                $(this).removeClass("on");
            })
        });
    });

    function stopPropagation(e) {
        if (e.stopPropagation)
            e.stopPropagation();
        else
            e.cancelBubble = true;
    }
</script>
</body>
</html>