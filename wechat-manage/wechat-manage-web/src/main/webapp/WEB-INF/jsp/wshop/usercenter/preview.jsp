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
	<script src="${pageContext.request.contextPath}/member/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/member/js/public.js" type="text/javascript"></script>
    
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
	<a id="ljjh" href="#">立即激活</a>
</body>
<script type="text/javascript">
	var rootPath = getContextPath();
	var openId = getUrlDataByKey("openId");
	var storeCode = getUrlDataByKey("storeCode");
	var appId = getUrlDataByKey("appId");
	$(function(){
		$.ajax({
			type: "post",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			url: rootPath + "/common/getCurMemberInfo_1.json",
			async: false,
			data: {
				"storeCode": storeCode,
				"openid": openId,
				"pageType": 1,
				"appId": appId
			},
			dataType: "json",
			success: function (memberInfoVo) {
				console.info("memberInfoVo =" + memberInfoVo);
				if(memberInfoVo.memberCode == null){
					console.info("无会员编码，需注册。");
					cardUrl();
				}
			}
		});
	})

	//卡包链接
	function cardUrl() {
		$.ajax({
			type: "post",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			url: rootPath + "/appAccountInfo/findAppAccountInfoByPara.json",
			async: false,
			data: {
				"storecode": storeCode
			},
			dataType: "json",
			success: function (response) {
				response = JSON.parse(response);
				if (!!response && response.success) {
//              	$(".actPhone2").attr("href", response.list[0].cardUrl);
//              	$(".actPhone3").attr("href", "http://10.6.2.49:8080/notebook/member/userRegister3.html");
					$('#ljjh').attr("href",response.list[0].cardUrl);
				} else {

				}
			}
		});
	}
</script>
</html>