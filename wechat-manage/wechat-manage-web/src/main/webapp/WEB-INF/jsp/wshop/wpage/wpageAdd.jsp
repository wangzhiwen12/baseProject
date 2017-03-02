<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="renderer" content="webkit">
    <meta name="referrer" content="always">

    <meta name="format-detection" content="telephone=no">
    <meta name="viewport"
          content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">


    <title id="js-meta-title">
        微页面 - 王府井测试用店铺</title>

    <link rel="icon" href="https://b.yzcdn.cn/v2/image/yz_fc.ico"/>

    <meta name="keywords" content="有赞,微信商城,粉丝营销,微信商城运营"/>
    <meta name="description" content="有赞是帮助商家在微信上搭建微信商城的平台，提供店铺、商品、订单、物流、消息和客户的管理模块，同时还提供丰富的营销应用和活动插件。"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/chosen.jquery.20150826.min.css"
          onerror="_cdnFallback(this)" media="screen">
    <!-- ▼ Common CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/jquery.ui.20151022.min.css" onerror="_cdnFallback(this)"
          media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/bootstrap_140705.min.css"
          onerror="_cdnFallback(this)" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/pc_b2c9bdf473.css"
          onerror="_cdnFallback(this)" media="screen">
    <!-- ▲ Common CSS -->


    <!-- ▼ App CSS -->
    <link rel="stylesheet"
          href="https://b.yzcdn.cn/v2/build_css/stylesheets/www/pages/pc/showcase/feature_49b2879fa4.css"
          onerror="_cdnFallback(this)" media="screen">
    <!-- ▲ App CSS -->
    
</head>
<body>
<div class="app">
    <div class="app-inner clearfix">
        <div class="app-init-container">
            <div class="app__content js-app-main">
            <input class="notice" value="${notice}"  type="hidden"/>
			<input class="pageId" value="${pageId}"  type="hidden"/>
			<input class="wpageTitle" value="${wpageTitle}"  type="hidden"/>
            </div>
        </div>
    </div>
</div>
<input clss="notice" value="${notice}"  type="hidden"/>
<input clss="pageId" value="${pageId}"  type="hidden"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/layer-v1.9.2/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wshop/wpage/wpageOperation.js" ></script>
<script src="${pageContext.request.contextPath}/wpage/js/require.js" data-main="${pageContext.request.contextPath}/wpage/js/editMain"></script>
</body>
</html>