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
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    
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
	<p style="margin-top: 10px;">
		<p class="actPhone">
			立即激活手机号
		</p>
	</p>
</body>
<script type="text/javascript">
	var rootPath = getContextPath();
	var openId = getUrlDataByKey("openId");
	var storeCode = getUrlDataByKey("storeCode");
	var appId = getUrlDataByKey("appId");
	var url = "";
	$(function(){
		url = location.href.split('#')[0];
		initializationConfig();
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
				if(memberInfoVo.memberCode == null){
					console.info("无会员编码，需注册。");
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

	function initializationConfig() {
//        var appId = "wx7aec942c6742752d";
		//获取 config 参数
		$.ajax({
			async: false,
			type: "post",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			url: rootPath + '/wechat/getSing2.json',
			dataType: "json",
			data: {
				"url": url,
				"storePara": appId
			},
			success: function (response) {
				var timestamp = response.data.obj.timestamp;
				var nonceStr = response.data.obj.nonceStr;
				var signature = response.data.obj.signature;

				//js sdk 初始化
				// 开启debug  上线后关调debug（debug: true为开启，debug: false 关闭）
				wx.config({
					debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
					appId: appId, // 必填，公众号的唯一标识  wx871d0104ae72e615
					timestamp: timestamp, // 必填，生成签名的时间戳
					nonceStr: nonceStr, // 必填，生成签名的随机串
					signature: signature,// 必填，签名，见附录1
					jsApiList: ['addCard'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				// config 初始化 走 wx.ready 方法
				wx.ready(function () {
					// 12.1 添加卡券
					document.querySelector('.actPhone').onclick = function () {
						var cards = "";
						var card = "";
						//获取 cardExt
						$.ajax({
							type: "post",
							async: false,
							contentType: "application/x-www-form-urlencoded;charset=utf-8",
							url: rootPath + '/wechat/getCartSing2.shtml',
							dataType: "json",
							data: "storePara=" + appId,
							success: function (response) {
//                                alert(response.success == "true");
								if (response.success == "true") {
									cards = response.data.cardExts;
									card = response.data.cards;
//                                    alert("appId++++++:" + appId);
//                                    alert("card++++++:" + card);
//                                    alert("cardExt++++++:" + cards);
									wx.addCard({
										cardList: [
											{
												cardId: card,
												cardExt: cards
											}
										],
										success: function (res) {
//                                alert('已添加卡券：' + JSON.stringify(res.cardList));
										},
										cancel: function (res) {
//                                alert('res' + JSON.stringify(res))
										}
									});
								}

							},
							error: function (response) {
								alert("add error");
							}
						});
					}

					//
				});
				wx.error(function (res) {
					// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					alert('错误：' + res);
				});
			},
			error: function (jqXHR, textStatus, errorThrown) {
				;
				alert("初始化方法error");
			}
		});
	}
</script>
</html>