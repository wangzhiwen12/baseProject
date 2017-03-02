<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta name="renderer" content="webkit">
<meta name="referrer" content="always">

<meta name="format-detection" content="telephone=no">
<meta name="viewport"
      content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">


<title id="js-meta-title">微页面 - 王府井测试用店铺</title>
<meta name="keywords" content="有赞,微信商城,粉丝营销,微信商城运营"/>
<meta name="description" content="有赞是帮助商家在微信上搭建微信商城的平台，提供店铺、商品、订单、物流、消息和客户的管理模块，同时还提供丰富的营销应用和活动插件。"/>
<link rel="stylesheet" href="wpage/css/chosen.jquery.20150826.min.css"
      onerror="_cdnFallback(this)" media="screen">
<!-- ▼ Common CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" onerror="_cdnFallback(this)"
      media="screen">
<link rel="stylesheet" href="wpage/css/jquery.ui.20151022.min.css" onerror="_cdnFallback(this)"
      media="screen">
<link rel="stylesheet" href="wpage/css/bootstrap_140705.min.css"
      onerror="_cdnFallback(this)" media="screen">
<link rel="stylesheet" href="wpage/css/pc_b2c9bdf473.css"
      onerror="_cdnFallback(this)" media="screen">
<style>
.divcss5 {
	margin-left: 12px;
	width: 98%;
}
.divcss4 {
	margin-left: 15px;
}

.ui-grid-cards-item {
	float: left;
	padding: 10px;
	width: 33.3333%;
}

.col-md-3 {
    width: 2%;
}


</style>
<!-- ▲ Common CSS -->

<!-- ▼ App CSS -->
<link rel="stylesheet"
      href="https://b.yzcdn.cn/v2/build_css/stylesheets/www/pages/pc/showcase/feature_49b2879fa4.css"
      onerror="_cdnFallback(this)" media="screen">
<!-- ▲ App CSS -->

<div class="app">
    <div class="app-inner clearfix">
        <div class="app-init-container">
            <div class="app__content js-app-main">
            </div>
        </div>
    </div>
</div>
<span id="usercenter">${html}</span>
<span id="data">${title}</span>
<script src="wpage/js/require.js" defer async="true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/usercenter/main.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/usercenter/user_center.js"></script> --%>
