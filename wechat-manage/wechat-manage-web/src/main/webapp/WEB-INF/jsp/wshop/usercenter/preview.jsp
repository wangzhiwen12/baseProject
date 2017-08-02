<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">

    <title id="js-meta-title">
          会员主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/usercenter_preview.css"
          onerror="_cdnFallback(this)" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/bootstrap.min.css"
          onerror="_cdnFallback(this)" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/style.css"
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
	
	<div class="container v-floor">
        <div class="row">
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/wpage/css/img/n-find.png"></a></div>
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/wpage/css/img/n-catergry.png"></a></div>
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/wpage/css/img/n-me.png"></a></div>
        </div>
    </div>
</body>
</html>