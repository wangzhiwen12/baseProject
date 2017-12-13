/*交易报表核对页面数据动态生成*/
$(function(){

	var localhost = "http://localhost/tradeReportCheck_end/";        // 路径
	var documentBox = $(document);

	var searchBtn = $("#searchBtn");                             // 搜索按钮

	var popBg = $('.iframe_box')                                 // 弹出窗口背景
	var popLoading = $('.popLoading')                            // 加载等候效果盒子

	// 退出系统
	$(".btnBox").delegate(".exitBtn", "click", function(event){
		event.stopPropagation;
		var exitSystem = "true";
		//console.log(exitSystem);
		closePop(".infoUp", ".iframe_box");
	});

	/* table 隔行换色 */
	function tableColor(){
		var tr = $(".tradeInfoTable tbody tr:odd");
		tr.addClass("tr_bg");
	}
	// tableColor();

	$('.scrollToTop').click(function(){
		$('body,html').animate({"scrollTop":0},200);
	});

	// 获取当前日期
	var mydate = new Date();
	var yearNow = mydate.getFullYear();        // 获得当前年份
	var monthNow = mydate.getMonth() + 1;      // 获得当前月份
	var dayNow = mydate.getDate();             // 获得当前日期
	var hourNow = mydate.getHours();           // 获得当前时间
	var minutesNow = mydate.getMinutes();      // 获得当前时间
	var timeInputEnd = yearNow + "-" + compare(monthNow) + "-" + compare(dayNow) + " " + compare(hourNow) + ":" + compare(minutesNow);
	var timeInputStart = yearNow + "-" + compare(monthNow) + "-" + compare(dayNow) + " " + "00:00";
	// console.log(timeInput);
	// console.log(timeInputStart);
	$("#start_date").val(timeInputStart);
	$("#end_date").val(timeInputEnd);

	// 获取昨日日期
	var preYear = "";
	var preMonth = "";
	var preDate = "";
	var preDateAll = "";
	var timeInputStartYesterday = "";
	var timeInputEndYesterday = "";
	if(mydate.getDate() == 1){
		preMonth = monthNow - 1;
		if(preMonth == 0){
			preYear = yearNow - 1;                // 获得上一年
			preMonth = 12;
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			timeInputStartYesterday = preYear + "-" + compare(preMonth) + compare(preDateAll) + " " + "00:00";
			timeInputEndYesterday = preYear + "-" + compare(preMonth) + compare(preDateAll) + " " + "24:00";
		}else{
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			timeInputStartYesterday = yearNow + "-" + compare(preMonth) + "-" + compare(preDateAll) + " " + "00:00";
			timeInputEndYesterday = yearNow + "-" + compare(preMonth) + "-" + compare(preDateAll) + " " + "24:00"
		}		
	}else{
		timeInputEndYesterday = yearNow + "-" + compare(monthNow) + "-" + compare(dayNow-1) + " " + "24:00";
	 	timeInputStartYesterday = yearNow + "-" + compare(monthNow) + "-" + compare(dayNow-1) + " " + "00:00";
	}
	
	//console.log(timeInputStartYesterday);
	//console.log(timeInputEndYesterday);
	
	// 获取最近7天初始日期
	var timeInputStartLatest7Days = "";
	if(mydate.getDate() < 7){
		//console.log("0");
		preMonth = monthNow - 1;
		if(preMonth == 0){
			preYear = yearNow - 1;                // 获得上一年
			preMonth = 12;
			preDate = 31 - Math.abs(mydate.getDate() - 7);
			timeInputStartLatest7Days = preYear + "-" + compare(preMonth) + "-" + compare(preDate) + " " + "00:00";
		}else{
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			preDate = preDateAll - Math.abs(mydate.getDate() - 7);
			timeInputStartLatest7Days = yearNow + "-" + compare(preMonth) + "-" + compare(preDate) + " " + "00:00";
		}

	}else if(mydate.getDate() == 7){
		preMonth = monthNow - 1;
		if(preMonth == 0){
			preYear = yearNow - 1;                // 获得上一年
			preMonth = 12;
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			// preDate = 31 - Math.abs(mydate.getDate() - 7);
			timeInputStartLatest7Days = preYear + "-" + compare(preMonth) + "-" + compare(preDateAll) + " " + "00:00";
		}else{
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			timeInputStartLatest7Days = yearNow + "-" + compare(preMonth) + "-" + compare(preDateAll) + " " + "00:00";
		}
		
	}else{
		// console.log("1");
		timeInputStartLatest7Days = yearNow + "-" + compare(monthNow) + "-" + compare(dayNow-7) + " " + "00:00";
	}	
	// console.log(timeInputStartLatest7Days);
	// console.log(timeInputEnd);
	
	// 最近30天
	var timeInputStartLatest30Days = "";
	if(mydate.getDate() < 30){
		preMonth = monthNow - 1;
		if(preMonth == 0){                        // 判断是否是1月
			preYear = yearNow - 1;                // 获得上一年
			preMonth = 12;
			preDate = 31 - Math.abs(mydate.getDate() - 30);
			timeInputStartLatest30Days = preYear + "-" + compare(preMonth) + "-" + compare(preDate) + " " + "00:00";                      
		}else{
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			preDate = preDateAll - Math.abs(mydate.getDate() - 30);
			timeInputStartLatest30Days = yearNow + "-" + compare(preMonth) + "-" + compare(preDate) + " " + "00:00";
		}
		// console.log(preDate);
		// console.log(preMonth);
		// console.log(preDateAll);
		
	}else if(mydate.getDate() == 30){
		preMonth = monthNow - 1;
		if(preMonth == 0){
			preYear = yearNow - 1;                // 获得上一年
			preMonth = 12;
			preDate = 31 - Math.abs(mydate.getDate() - 30);
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			timeInputStartLatest30Days = preYear + "-" + compare(preMonth) + "-" + compare(preDateAll) + " " + "00:00";	
		}else{
			preDateAll = new Date(yearNow,preMonth,0).getDate();   // 获取上一月的天数
			timeInputStartLatest30Days = yearNow + "-" + compare(preMonth) + "-" + compare(preDateAll) + " " + "00:00";
		}
		
	}else{
		timeInputStartLatest30Days = yearNow + "-" + compare(monthNow) + "-" + compare(dayNow-30) + " " + "00:00";
	}
	
	// console.log(timeInputStartLatest30Days);
	
	
	// 开始时间
	var start_date = $("#start_date").val();
	// console.log(start_date);
	
	// 结束时间
	var end_date = $("#end_date").val();
	// console.log(end_date);
	
	// 订单详细信息切换
	var total_status = $(".selectActive").text();
	var total_statusPK = "";

	// 列表顺序排列
	var order = $(".icon_down").attr("name");
	// console.log(order);
	
	// 跳转页码
	var over_page = $("#over_page").val();

	var sendInitData = "";
	var searchSendInfo = "";


	var pageListIN = $(".pageListIN");                           // 分页
	var nowPageNumber = 1;                                       // 当前页
	var overPageNumber = '';
	sendInitData = {
		"start_date":start_date,
		"end_date":end_date,
		"min_fee":"",
		"max_fee":"",
		"total_type":"",
		"buyer_account":"",
		"goods_title":"",
		"total_status":total_status,
		"total_statusPK":"",
		"over_page":over_page,
		"order":order
	};
	console.log(sendInitData);

	// 加载 数据库提供的 input 控件 以及数据列表
	loadingSearchData(sendInitData);
	loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
	closeLoading('white', 'loadingBigl');
	
	pageListIN.undelegate(".homePage", "click").delegate(".homePage", "click", function(){
		$("#over_page").val("");
		nowPageNumber = 1;
		
		// console.log(nowPageNumber);
		
		loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');

		// 数据库管理页面 初始数据
		
		over_page = nowPageNumber;
		submitData();
		loadingSearchData(sendInitData);
		console.log(sendInitData);
		
		closeLoading('white', 'loadingBigl');
		
	});

	pageListIN.undelegate(".prePage", "click").delegate(".prePage", "click", function(){
		
		$("#over_page").val("");
		if(nowPageNumber > 1){
			nowPageNumber -= 1;
		}else{
			nowPageNumber = 1;
		}
		
		// console.log(nowPageNumber);
		
		loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');

		// 数据库管理页面 初始数据
		over_page = nowPageNumber;
		submitData();
		loadingSearchData(sendInitData);
		closeLoading('white', 'loadingBigl');
		
	});

	pageListIN.undelegate(".nextPage", "click").delegate(".nextPage", "click", function(){
		
		$("#over_page").val("");
		if(nowPageNumber < overPageNumber){
			nowPageNumber += 1;
		}else{
			nowPageNumber = overPageNumber;
		}
		
		// console.log(nowPageNumber);
		
		loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');

		// 数据库管理页面 初始数据
		over_page = nowPageNumber;
		loadingSearchData(sendInitData);
		
		closeLoading('white', 'loadingBigl');
		
	});

	pageListIN.undelegate(".overPage", "click").delegate(".overPage", "click", function(){
		
		$("#over_page").val("");
		nowPageNumber = overPageNumber;
		
		// console.log(nowPageNumber);
		
		loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');

		// 数据库管理页面 初始数据
		over_page = nowPageNumber;
		loadingSearchData(sendInitData);
		
		closeLoading('white', 'loadingBigl');
	});

	// 提交数据
	function submitData(){

		// 开始时间
		var start_date = $("#start_date").val();
		// console.log(start_date);
	
		// 结束时间
		var end_date = $("#end_date").val();
		// console.log(end_date);

		// 交易范围 最小金额
		var min_fee = $("#min_fee").val();
		// console.log(min_fee);

		// 交易范围 最大金额
		var max_fee = $("#max_fee").val();
		// console.log(max_fee);

		// 买家支付信息
		var total_type = $('select[name="total_type"]').find("option:selected").val();
		var buyer_account = $("#buyer_account").val();
		// console.log(total_type);
		// console.log(buyer_account);
		
		// 商品名称
		var goods_title = $("#goods_title").val();
		// console.log(goods_title);
		
		searchSendInfo = {
			"start_date":start_date,
			"end_date":end_date,
			"min_fee":min_fee,
			"max_fee":max_fee,
			"total_type":total_type,
			"buyer_account":buyer_account,
			"goods_title":goods_title,
			"total_status":total_status,
			"total_statusPK":total_statusPK,
			"over_page":over_page,
			"order":order
		};
		// console.log(searchSendInfo);
		
	}

	// 搜索事件
	searchBtn.off("click").on("click",function(){
		
		$(".addTxt").text("");

		submitData();

		loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');

		loadingSearchData(searchSendInfo);

		closeLoading('white', 'loadingBigl');

		pageListIN.undelegate(".homePage", "click").delegate(".homePage", "click", function(){
			
			$("#over_page").val("");
			nowPageNumber = 1;
			
			loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
	
			// 数据库管理页面 初始数据
			over_page = nowPageNumber;
			submitData();
			loadingSearchData(searchSendInfo);
			
			closeLoading('white', 'loadingBigl');			
		});

		pageListIN.undelegate(".prePage", "click").delegate(".prePage", "click", function(){
			
			$("#over_page").val("");
			if(nowPageNumber > 1){
				nowPageNumber -= 1;
			}else{
				nowPageNumber = 1;
			}
			
			loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
	
			// 数据库管理页面 初始数据
			over_page = nowPageNumber;
			submitData();
			loadingSearchData(searchSendInfo);
			closeLoading('white', 'loadingBigl');
			
		});

		pageListIN.undelegate(".nextPage", "click").delegate(".nextPage", "click", function(){

			$("#over_page").val("");
			if(nowPageNumber < overPageNumber){
				nowPageNumber += 1;
			}else{
				nowPageNumber = overPageNumber;
			}
			// console.log(nowPageNumber);
			loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
			// 加载点击搜索后的数据
			over_page = nowPageNumber;
			submitData();
			loadingSearchData(searchSendInfo);
			
			closeLoading('white', 'loadingBigl');
			
		});

		pageListIN.undelegate(".overPage", "click").delegate(".overPage", "click", function(){
			
			$("#over_page").val("");
			nowPageNumber = overPageNumber;
			
			loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
	
			// 加载点击搜索后的数据
			over_page = nowPageNumber;
			submitData();
			loadingSearchData(searchSendInfo);
			
			closeLoading('white', 'loadingBigl');
		});
	})

	// 详细搜索
	var searchDetail = $(".searchDetail span");
	searchDetail.each(function(){
		$(this).off("click").on("click",function(){
			$(this).addClass('selectActive').siblings().removeClass('selectActive');
			total_status = $(this).text();
			total_statusPK = $(this).attr("alt");
			if(total_status == "全部"){
				total_statusPK = '';
			}
			submitData();
			loadingSearchData(searchSendInfo);
			console.log(searchSendInfo);
			// console.log($(this).text());
			// console.log($(this).attr("alt"));
		})
	})

	// 排序查找
	var icon_up = $(".icon_up");
	var icon_down = $(".icon_down");
	icon_up.off("click").on("click",function(){
		$(this).css("background","url(images/icon_up.png)");
		icon_down.css("background","url(images/icon_down_gray.png)");
		order = icon_up.attr("name");
		 submitData();
		loadingSearchData(searchSendInfo);
		console.log(searchSendInfo);
	});
	icon_down.off("click").on("click",function(){
		$(this).css("background","url(images/icon_down.png)");
		icon_up.css("background","url(images/icon_up_gray.png)");
		order = icon_down.attr("name");
		 submitData();
		loadingSearchData(searchSendInfo);
		console.log(searchSendInfo);
	});

	// 分页中的跳转
	// var pointer = $(".pointer");
	// // console.log("OK");
	// pointer.on("click",function(){
	// 	// console.log("OK");
	// 	over_page = $("#over_page").val();
	// 	// console.log(over_page);
		
	// 	if(over_page < 0 || over_page == 0){
	// 		errorsAll('您输入的页码有误，请重新输入')
	// 	}else if(over_page == ''){
	// 		errorsAll('请您输入页码！');
	// 	}else if(over_page > totalNumber){
	// 		// console.log(totalNumber);
	// 		errorsAll('您输入的页码超过最大页数，请重新输入');
	// 	}else if(isNaN(over_page)){
	// 		errorsAll('您输入的页码格式错误，请重新输入');
	// 	}
	// 	submitData();
	// })

	function showIsRefund(refundFee){
		try{
			var refundFeeFloat=parseFloat(refundFee);
			if(refundFeeFloat>0){
				return true;
			}
			return false;
		}catch(e){
			return false;
		}
	}
	
	// 异步加载搜索后的数据
	function loadingSearchData(sendSearchDate){

		$(".orderInfo").show();
		$(".tradeInfo").show();
		$(".searchDetail").show();
		$(".moban_fenye").show();
		$(".scrollToTop").show();
		var js_fee_sum = $("#js_fee_sum");                 // 订单金额注入接口
		var js_trading_times = $("#js_trading_times");     // 订单数量注入接口
		var js_sucess_fee_sum = $("#js_sucess_fee_sum");   // 成功订单金额注入接口
		var js_sucess_times = $("#js_sucess_times");       // 成功订单数量注入接口
		var js_refund_fee_sum = $("#js_refund_fee_sum");   // 退款订单金额注入接口
		var js_refund_times = $("#js_refund_times");       // 退款订单数量注入接口
		var js_service_fee = $("#js_service_fee");         // 服务费注入接口

		var js_tableInfoList = $("#js_tableInfoList");     // 订单信息注入接口

		var addHtmlPage = '';

		// 请求数据
		$.post(localhost + "data-service/tableInfoList.json",sendSearchDate,function(json){

			try{
				// 获取订单信息数据
				var fee_sum = json.stat.fee_sum;
				var trading_times = json.stat.trading_times;
				var sucess_fee_sum = json.stat.sucess_fee_sum;
				var sucess_times = json.stat.sucess_times;
				var refund_fee_sum = json.stat.refund_fee_sum;
				var refund_times = json.stat.refund_times;
				var service_fee = json.stat.service_fee;
	
				// 信息注入
				js_fee_sum.html(fee_sum);
				js_trading_times.html(trading_times);
				js_sucess_fee_sum.html(sucess_fee_sum);
				js_sucess_times.html(sucess_times);
				js_refund_fee_sum.html(refund_fee_sum);
				js_refund_times.html(refund_times);
				js_service_fee.html(service_fee);
			}catch(e){
				
			}

			// 获取订单信息列表
			var items = json.items;
			// console.log(items);

			// 设置添加内容盒子
			var addHtml_infoList = '';
			var addHtml_isNull = '';
			
			var addHtml_payType='';

			// 循环设定变量
			var i = 0;

			for(;i<items.length;i++){

				var trade_refund_amount = items[i].tradeRefundAmount;
				// console.log(trade_refund_amount);
				if(trade_refund_amount>0){
					addHtml_isNull="有退款";
				}else{
					addHtml_isNull='';
				}
				if("ALIPAYBILL"==items[i].paytype){
					addHtml_payType="支付宝";
				}else if("WEBCHATBILL"==items[i].paytype){
					addHtml_payType='微信';
				}

				addHtml_infoList += '<tr>' + 
										 '<td>' + '<span>'+ items[i].trans_date.split(" ")[0] +'</span>'+'<br/>'+'<span class="cGray">'+ items[i].transDate.split(" ")[1] +'</span>' + '</td>' + 
										 '<td>' + items[i].merchantOutOrderNo + '</td>' + 
										 '<td>' + items[i].goodsTitle + '</td>' +
										 '<td class="textAlignR paddingR20">' + items[i].totalFee +'</td>' + 
										 '<td class="paddingR35">' + '<span>'+ items[i].subTransCodeMsg +'</span>'+'<br/>'+'<span class="cGray">'+ addHtml_isNull +'</span>'+'</td>' + 
										 '<td>' + '<span>'+ items[i].buyerAccount +'</span>'+'<br/>'+'<span class="cGray">'+ addHtml_payType+'</span>' + '</td>' +
										 '<td class="textAlignR fontWeight700">' + items[i].tradeRefundAmount +'</td>' + 
									'</tr>';
			}

			// 判断后注入到页面
			if(items.length != 0){
				// console.log(addHtml_infoList);
				js_tableInfoList.html("");
				js_tableInfoList.append(addHtml_infoList);
			}else{
				// js_tableInfoList.html("");
				// js_tableInfoList.append('<tr><td colspan="6" style="height:30px;line-height:30px;"> 没有符合当前查询条件的内容！</td></tr>');
				$(".orderInfo").hide();
				$(".tradeInfo").hide();
				$(".searchDetail").hide();
				$(".moban_fenye").hide();
				$(".scrollToTop").hide();
				$(".addTxts").remove();
				$(".main").append('<p class="addTxts" style="height:30px;line-height:30px;text-align:center;padding-top:40px;">没有符合当前查询条件的内容！</p>');
			}

			tableColor();

			totalNumber = json.totalPage;
			// console.log(totalNumber);
			nowPage = json.currentPage;
			nowPageNumber = nowPage;
			overPageNumber = totalNumber;
			
			pageListIN.html("");
			
			if(totalNumber >= 2){
				addHtmlPage += '<a class="moban_fenye_a homePage" href="javascript:void(0);">首页</a>' +
							   '<a class="moban_fenye_a1 prePage" href="javascript:void(0);"> &lt; </a>' +
							   '<span class="moban_fenye_span">' + nowPage + '/' + totalNumber + '页</span>' +
							   '<a class="moban_fenye_a2 nextPage" href="javascript:void(0);"> &gt; </a>' +
							   '<a class="moban_fenye_a overPage" href="javascript:void(0);">尾页</a>';								
			}

			pageListIN.html(addHtmlPage);

			// 分页中的跳转
			var pointer = $(".pointer");
			// console.log("OK");
			pointer.off("click").on("click",function(){
				// console.log("OK");
				over_page = $("#over_page").val();
				// console.log(over_page);
				// 如果输入的是浮点型数值将自动转换成整型
				var intOverPage = over_page.split(".")[0];   
				$("#over_page").val(intOverPage);
				// 把当前页设置为input中的页数
				var int_over_page = parseInt(over_page);
				nowPageNumber = int_over_page;
				over_page = int_over_page;
				// console.log(int_over_page);
				// console.log(over_page);
				
				if(int_over_page < 0 ){
					// console.log(totalNumber);
					errorsAll('您输入的页码有误，请重新输入');
				}else if(int_over_page == 0){
					errorsAll('输入的页码不能为0，请重新输入');
				}else if(int_over_page > totalNumber){
					errorsAll('输入的页码大于总页数，请重新输入');
				}else if(isNaN(int_over_page)){
					// alert("不是数字");
					// console.log("不是数字");
					errorsAll('请您输入与页码相关的数字');
				}
				submitData();
				loadingSearchData(searchSendInfo);
				console.log(searchSendInfo);
			})

		},"json");
		/***  程序员操作 结束   ***/
	}

	// 时间段切换
	var tabSpan = $(".tabSpan span");
	tabSpan.each(function(){
		$(this).click(function(){
			$(this).addClass("active").siblings().removeClass("active");
			// submitData();
			if($(this).text() == "今日"){
				$("#start_date").val(timeInputStart);
				$("#end_date").val(timeInputEnd);
				start_date = timeInputStart;
				end_date = timeInputEnd;
				// console.log($("#start_date").val());
				// console.log($("#end_date").val());
				submitData();
			}else if($(this).text() == "昨日"){
				$("#start_date").val(timeInputStartYesterday);
				$("#end_date").val(timeInputEndYesterday);
				start_date = timeInputStartYesterday;
				end_date = timeInputEndYesterday;
				// console.log($("#start_date").val());
				// console.log($("#end_date").val());
				submitData();
			}else if($(this).text() == "最近7天"){
				$("#start_date").val(timeInputStartLatest7Days);
				$("#end_date").val(timeInputEnd);
				start_date = timeInputStartLatest7Days;
				end_date = timeInputEnd;
				// console.log($("#start_date").val());
				// console.log($("#end_date").val());
				submitData();
			}else if($(this).text() == "最近30天"){
				$("#start_date").val(timeInputStartLatest30Days);
				$("#end_date").val(timeInputEnd);
				start_date = timeInputStartLatest30Days;
				end_date = timeInputEnd;
				// console.log($("#start_date").val());
				// console.log($("#end_date").val());
				submitData();
			}
		})
	});

	//获取当前年月日
	function compare(n){
		if(n<10)
		{
			return '0'+n;
		}
		else
		{
			return n;
		}
	};

	// 报错提示功能
		
	function errorsAll(messge){
		var popUp_p = $(".popUp_p");
		var popUp_head = $(".popUp_head");
		showPop(".infoUp", ".iframe_box");
		popUp_p.text("");
		popUp_head.find("span").text("");
		popUp_p.html("<b>" + messge + "！</b>");
		popUp_head.find("span").text("错误");
		$(".btnBox").hide();
	}

	/* loading 效果功能 */
	function loadingShow (bgColor, AjaxStyle, message){
		var ajaxShow;
		AjaxStyle == "" ? ajaxShow = "" : ajaxShow = AjaxStyle; 
		popBg.addClass(bgColor);
		popLoading.addClass(AjaxStyle);
		popLoading.html("");
		popLoading.append('<p class="loading_info">' + message + '</p>');
		showPop("."+AjaxStyle,".iframe_box");
	}
	
	function closeLoading (bgColor, AjaxStyle){
		popBg.css("display","none");
		popLoading.css("display","none");
		popBg.removeClass(bgColor);
		popLoading.removeClass(AjaxStyle);
		popLoading.html("");
		closePop("."+AjaxStyle,".iframe_box");
	}

	
});