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
          会员主页</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/wpage/css/usercenter_preview.css"
          onerror="_cdnFallback(this)" media="screen">
    <link href="${pageContext.request.contextPath}/member/css/css/style.css"
		rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/member/css/css/bootstrap.min.css"
		rel="stylesheet" />
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
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/member/css/img/n-find.png"></a></div>
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/member/css/img/n-catergry.png"></a></div>
            <div class="col-xs-4 v-floor-nav"><a id="mine" href="${pageContext.request.contextPath}/wShop/preview.shtml"><img src="${pageContext.request.contextPath}/member/css/img/n-me.png"></a></div>
        </div>
    </div>
</body>
<script type="text/javascript">
	var rootPath = getContextPath();
	var openId = getUrlDataByKey("openId");
	var storeCode = getUrlDataByKey("storeCode");
	$("#myId").attr("href",${pageContext.request.contextPath}"+/wShop/preview.shtml?openId"+openId+"&storeCode="+storeCode);
</script>
</html>