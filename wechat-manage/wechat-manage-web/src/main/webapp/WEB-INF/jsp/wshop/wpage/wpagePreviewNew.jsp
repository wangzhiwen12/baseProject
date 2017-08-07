<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<head>
    <link href="${pageContext.request.contextPath}/member/css/css/style.css"
	rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/member/css/css/bootstrap.min.css"
	rel="stylesheet" />
    
    <title>微商城首页</title>
</head>
<body>
    <div class="v-notice"><span>公告：</span>在这里，你可以了解王府井全渠道及零售行业各种市场动态</div>
    <div class="v-commodityList">
        ${html}
    </div>
   
    <div class="footer-copyright">Copyright &copy; 2004-2017 王府井集团 版权所有</div>
    <div class="container v-floor">
        <div class="row">
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/member/css/img/n-find.png"></a></div>
            <div class="col-xs-4 v-floor-nav"><a href="#"><img src="${pageContext.request.contextPath}/member/css/img/n-catergry.png"></a></div>
            <div class="col-xs-4 v-floor-nav"><a id="myId" href="${pageContext.request.contextPath}/wShop/preview.shtml?openId='+${openId}+'&storeCode='+${storeCode}"><img src="${pageContext.request.contextPath}/member/css/img/n-me.png"></a></div>
        </div>
    </div>
</body>
<script type="text/javascript">
    var rootPath = getContextPath();
</script>
</html>