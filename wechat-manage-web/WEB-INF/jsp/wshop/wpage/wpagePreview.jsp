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
         微页面预览</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/chosen.jquery.20150826.min.css"
          onerror="_cdnFallback(this)" media="screen">
    <!-- ▼ Common CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/jquery.ui.20151022.min.css" onerror="_cdnFallback(this)"
          media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/bootstrap_140705.min.css"
          onerror="_cdnFallback(this)" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/usercenter_preview.css"
          onerror="_cdnFallback(this)" media="screen">
    <!-- ▲ Common CSS -->
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/wpage/js/libs/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/wshop/wpage/wpageOperation.js"></script>
    
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
	            	<div class="app-design clearfix">
			           	<div class="app-preview">
							<div class="app-entry">
								<div class="app-config js-config-region">${html}</div>
							</div>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<jsp:include page="../shopnav/shopnav.jsp"></jsp:include>
</body>
</html>