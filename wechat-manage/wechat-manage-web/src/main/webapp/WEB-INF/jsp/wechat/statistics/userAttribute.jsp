<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta charset="utf-8">
	<%--<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>--%>
	<%--<script src="${pageContext.servletContext.contextPath }/js/highcharts.js" 	type="text/javascript"></script>--%>
	<%--<script src="http://code.highcharts.com/highcharts.js"></script>--%>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="http://code.highcharts.com/maps/highmaps.js"></script>
	<script src="http://code.highcharts.com/maps/modules/data.js"></script>
	<script src="http://code.highcharts.com/maps/modules/drilldown.js" ></script>
	<script src="http://sandbox.runjs.cn/uploads/rs/228/zroo4bdf/cn-china-by-peng8.js" ></script>

<title>微信公众平台</title>
<link 	href="https://res.wx.qq.com/mpres/htmledition/images/favicon218877.ico" 	rel="Shortcut Icon">
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" 	href="https://res.wx.qq.com/mpres/htmledition/style/base/layout_head318f29.css">
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" 	href="https://res.wx.qq.com/mpres/htmledition/style/base/base324410.css">
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" 	href="https://res.wx.qq.com/mpres/htmledition/style/base/lib318f29.css">
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" 	href="https://res.wx.qq.com/mpres/htmledition/style/page/stat/stat_overview2f3a2e.css">
<style type="text/css">
.map_wrap {
	line-height: normal;
}

div {
	margin: 0;
	padding: 0;
}
.loading {
	margin-top: 10em;
	text-align: center;
	color: gray;
}
</style>
<link rel="stylesheet" type="text/css"
	href="https://res.wx.qq.com/c/=/mpres/htmledition/style/widget/pagination218878.css,/mpres/htmledition/style/biz_web/widget/date_range218878.css,/mpres/htmledition/style/biz_web/widget/dropdown2f12f7.css">
<style></style>
</head>
<body class="zh_CN screen_small">


<script type="text/javascript">
	//	饼图js  语言
	//	$(function () {
	//		Highcharts.chart('containers', {
	//			chart: {
	//				plotBackgroundColor: null,
	//				plotBorderWidth: null,
	//				plotShadow: false,
	//				type: 'pie'
	//			},
	//			title: {
	//				text: '语言分布'
	//			},
	//			tooltip: {
	//				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	//			},
	//			plotOptions: {
	//				pie: {
	//					allowPointSelect: true,
	//					cursor: 'pointer',
	//					dataLabels: {
	//						enabled: true,
	//						format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	//						style: {
	//							color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	//						}
	//					}
	//				}
	//			},
	//			series: [{
	//				name: ' ',
	//				colorByPoint: true,
	//				data: [{
	//					name: '英语',
	//					y: 56.33
	//				}, {
	//					name: '汉语',
	//					y: 24.03,
	//					sliced: true,
	//					selected: true
	//				}]
	//			}]
	//		});
	//	});
	//
	//
	//	// 男女条型图
	//	$(function () {
	//		Highcharts.chart('contai_nannv', {
	//			chart: {
	//				type: 'bar'
	//			},
	//			title: {
	//				text: ' '
	//			},
	//			subtitle: {
	//				text: ' '
	//			},
	//			xAxis: {
	//				categories: ['性别分布'],
	//				title: {
	//					text: ' '
	//				}
	//			},
	//			yAxis: {
	//				min: 0,
	//				title: {
	//					text: ' ',
	//					align: 'high'
	//				},
	//				labels: {
	//					overflow: 'justify'
	//				}
	//			},
	//			tooltip: {
	//				valueSuffix: '  '
	//			},
	//			plotOptions: {
	//				bar: {
	//					dataLabels: {
	//						enabled: true
	//					}
	//				}
	//			},
	//			legend: {
	//				layout: 'vertical',
	//				align: 'right',
	//				verticalAlign: 'top',
	//				x: -40,
	//				y: 80,
	//				floating: true,
	//				borderWidth: 1,
	//				backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
	//				shadow: true
	//			},
	//			credits: {
	//				enabled: false
	//			},
	//			series: [ {
	//				name: '男',
	//				data: [133]
	//			}, {
	//				name: '女',
	//				data: [1052]
	//			}]
	//		});
	//	});

	// 地图js
	$(function () {
//		Highcharts.setOptions({
//			lang:{
//				drillUpText:"返回 > {series.name}"
//			}
//		});
		var data=null;
//		var data = Highcharts.geojson(Highcharts.maps['countries/cn/custom/cn-all-china']),small = $('#maptiss').width() < 400;
		// 给城市设置随机数据
//		$.each(data, function (i) {
//			if(""!=this.properties['drill-key'] && this.properties['drill-key']!="undefined"){
//				console.log("=============start=================");
//			this.drilldown = this.properties['drill-key'];
//				console.log("this.drilldown:"+this.properties['drill-key']);
//			this.value = i;
//				console.log("this.value:"+i);
//				console.log("=============end==========================");
//			}else{
//				alert(this.properties['drill-key']);
//			}
		});
		//初始化地图
		$('#containermap').highcharts('Map', {
			chart : {
				events: {
					drilldown: function (e) {
						if (!e.seriesOptions) {
							var chart = this;
							var cname=e.point.properties["cn-name"];
						}
						this.setTitle(null, { text: cname });
					},
					drillup: function () {
						this.setTitle(null, { text: 'china' });
					}
				}
			},
			credits:{
				href:"... ",
				text:"..."
			},
			title : {
				text : 'highmap_china'
			},
			subtitle: {
				text: 'china',
				floating: true,
				align: 'right',
				y: 50,
				style: {
					fontSize: '16px'
				}
			},
			legend: small ? {} : {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
			},
			tooltip:{
				pointFormat:"{point.properties.cn-name}:{point.value}"
			},
			colorAxis: {
				min: 20,//渐变最小值
				minColor: '#E8E8DB',
				maxColor: '#FA8608'
			},
			mapNavigation: {
				enabled: true,
				buttonOptions: {
					verticalAlign: 'bottom'
				}
			},
			plotOptions: {
				map: {
					states: {
						hover: {
							color: '#EEDD66'
						}
					}
				}
			},
			series : [{
				data : data,
				name: ' ',
				dataLabels: {
					enabled: true,
					format: '{point.properties.cn-name}'
				}
			}],
			drilldown: {
				activeDataLabelStyle: {color: '#FFFFFF',textDecoration: 'none',textShadow: '0 0 3px #000000'},
				drillUpButton: {relativeTo: 'spacingBox',position: {x: 0,y: 60}}}});});




	//	$(function(){
	//    	$("#user_stat").click(function(){
	//    		$.ajax({
	//        		url : "/notebook/statistics/UserAnalysis.json",
	//        		type : "POST",
	//        		dataType : "html",
	//        		success : function(response){
	//        			$("#footer").hide();
	//        			$("#body").html(response);
	//        		}
	//
	//        	});
	//    	});
	//    });




</script>


	<div class="head" id="header">
	</div>
	<div id="body" class="body page_stat page_user">
		<div id="js_container_box" class="container_box cell_layout side_l">
			<div class="col_main">
				<script type="text/javascript" id="js_data">
</script>
				<div class="main_hd">
					<h2>用户分析</h2>
					<div class="title_tab" id="js_topTab">
						<ul class="tab_navs title_tab" data-index="0">


							<li data-index="0" class="tab_nav first js_top"
								id="user_stat"><a>用户增长</a></li>



							<li data-index="1" class="tab_nav  js_top selected"
								id="user_attr"><a>用户属性</a></li>


						</ul>
					</div>
				</div>
				<div class="main_bd user_analysis">
					<div class="page_msg mini top_interval">
						<div class="inner group" id="no_data_prompt" style="">
							<span class="msg_icon_wrp"> <i class="icon_msg_mini info"></i>
							</span>
							<div class="msg_content">
								<p>暂无数据可能因为昨日数据暂未处理完成，请稍等片刻后再查看</p>
							</div>
						</div>
					</div>
					<div class="wrp_overview">
						<div class="info_box">
							<div class="inner">
								<div class="info_hd append_ask">
									<h4>用户属性</h4>
									<div class="ext_info help" id="js_ask">
										<i class="icon_msg_mini ask"></i>
										<div class="help_content">
											<i class="dropdown_arrow out"></i> <i
												class="dropdown_arrow in"></i>
											<dl class="help-change-list" id="pop_items_info">
												<dt>性别</dt>
												<dd>用户的性别分为男、女和其他</dd>
												<dt>省份、城市</dt>
												<dd>地域分布情况</dd>
												<dt>语言</dt>
												<dd>语言分布情况</dd>
												<dt>请注意</dt>
												<dd>用户总数根据昨日数据计算，而用户管理模块根据当前实时数据计算，两者可能不一致</dd>
											</dl>
											<div class="help-change-footer" id="footer_items_info">
												<span class="wechat_data_range">数据从2013年7月1日开始统计。</span>由于服务器缓存，以及指标计算方法和统计时间的差异，数据可能出现微小误差，一般在1%以内。
											</div>
										</div>
									</div>
								</div>
								<div class="info_bd">
									<div class="sub_title">性别分布</div>
									<div class="sub_content nodata">
										<div id="js_sex_bar">
											<%--<h4 class="js_no_data">暂无数据</h4>--%>
											<%--加载性别--%>
												<div id="contai_nannv" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
										</div>
									</div>
									<div class="sub_title">语言分布</div>
									<div class="sub_content nodata">
										<div id="js_language_bar">
											<%--<h4 class="js_no_data">暂无数据</h4>--%>
												<div id="containers" style="width: 550px; height: 400px; margin: 0 auto">11111111</div>
										</div>
									</div>
									<div class="sub_title">省份分布</div>
									<div class="sub_content clear_sub_content" id="js_provinces">
										<div class="page_msg mini">
											<div class="inner group">
												<span class="msg_icon_wrp"> <i
													class="icon_msg_mini info"></i>
												</span>
												<div class="msg_content">

													<p>2015年9月对微信用户的地理位置数据进行了优化，带来了省份和城市分布数据的变化。</p>
												</div>
											</div>
										</div>
										<div class="col_no_data" style="display: none;">暂无数据</div>
										<div class="map_wrap">
											<div class="chart_wrap">
												<div
													style="margin-top: 0px; position: relative; width: 400px; height: 300px; overflow: hidden;"
													id="js_chart4provinces" class="chart4provinces">
													<div class="svggroup" id="containermap">


													</div>
												</div>
											</div>
											<div class="map_patch" id="maptiss"></div>
											<div id="js_bar4provinces" class="bar4provinces" style="">
												<div class="colors_bar"></div>
												<span class="max">0</span> <span class="min">0</span>
											</div>
										</div>
										<div class="line_wrp_table">
											<div class="table_wrp">
												<table class="table">
													<colgroup span="3">
														<col class="City">
														<col class="UserMount">
														<col class="Percent" style="width: 55%;">
													</colgroup>
													<thead class="thead">
														<tr>
															<th name="Province" class="table_cell">省份</th>
															<th name="Province" class="table_cell tr rank">用户数 <span
																class="icon_rank rank_area"> <i
																	class="arrow arrow_up"></i><i class="arrow arrow_down"></i>
															</span>
															</th>
															<th name="Percent" class="table_cell tr"></th>
														</tr>
													</thead>
													<tbody class="tbody">

														<tr class="empty_item">
															<td class="empty_tips" colspan="10">暂无数据</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div class="turn_page tr" id="js_province_pager"></div>
										</div>
									</div>
									<div class="sub_title">
										<span>城市分布</span>
										<div class="select_box dropdown_menu sub_tit_dropdown"
											id="js_provinces_dropdown"></div>
									</div>
									<div class="sub_content" id="js_cities">
										<div class="table_wrp">
											<table class="table">
												<colgroup span="3">
													<col class="City">
													<col class="UserMount">
													<col class="Percent" style="width: 70%;">
												</colgroup>
												<thead class="thead">
													<tr>
														<th name="City" class="table_cell">城市</th>
														<th name="City" class="table_cell tr rank">用户数 <span
															class="icon_rank rank_area"> <i
																class="arrow arrow_up"></i><i class="arrow arrow_down"></i>
														</span>
														</th>
														<th name="Percent" class="table_cell tl"></th>
													</tr>
												</thead>
												<tbody class="tbody">
													<tr class="empty_item">
														<td class="empty_tips" colspan="10">暂无数据</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="turn_page tr" id="js_cities_pager"></div>
									</div>
									<div class="sub_title">终端分布</div>
									<div class="sub_content nodata">
										<div id="js_endpoint_bar">
											<h4 class="js_no_data">暂无数据</h4>
										</div>
									</div>
									<div class="sub_title">机型分布 TOP10</div>
									<div class="sub_content clear_sub_content" id="js_types">
										<div class="page_msg mini">
											<div class="inner group">
												<span class="msg_icon_wrp"> <i
													class="icon_msg_mini info"></i>
												</span>
												<div class="msg_content">
													<p>由于Android机型的版本编码复杂多变，无法合并Android机型的不同版本，请知悉。</p>
												</div>
											</div>
										</div>
										<div class="chart_phones">
											<div id="js_types_dist" style="height: 500px;" class="nodata">
												<h4></h4>
											</div>
										</div>
										<div class="table_wrp line_wrp_table_phone">
											<table class="table">
												<colgroup span="3">
													<col class="City" style="width: 290x;">
													<col class="UserMount">
													<col class="Percent" style="width: 30%;">
												</colgroup>
												<thead class="thead">
													<tr>
														<th name="City" class="table_cell">机型</th>
														<th name="City" class="table_cell tr rank">用户数 <span
															class="icon_rank rank_area"> <i
																class="arrow arrow_up"></i><i class="arrow arrow_down"></i>
														</span>
														</th>
														<th name="Percent" class="table_cell tl"></th>
													</tr>
												</thead>
												<tbody class="tbody">
													<tr class="empty_item">
														<td class="empty_tips" colspan="10">暂无数据</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="info_box" id="js_table">
							<div class="inner">
								<div class="info_hd tabbar">
									<div>
										<div class="info_hd">
											<strong class="lable time_lable">属性分布表</strong>
											<div class="tabs">



												<a class="first current" href="javascript:;">性别</a> <a
													href="javascript:;">语言</a> <a href="javascript:;">省份</a> <a
													href="javascript:;">城市</a> <a href="javascript:;">终端</a> <a
													class="last" href="javascript:;">机型</a>



											</div>
										</div>


										<div></div>
									</div>
								</div>
								<div class="info_bd">
									<div class="sub_title">详细数据</div>
									<div class="sub_content">
										<div class="table_wrp">
											<table class="table" cellspacing="0">
												<thead class="thead">
													<tr>
														<th class="table_cell tl" id="js_table_type">性别</th>
														<th class="table_cell rank_area tr rank">用户数 <span
															class="icon_rank"> <i class="arrow arrow_up"></i><i
																class="arrow arrow_down"></i>
														</span>
														</th>
														<th class="table_cell tr rank_area last_child no_extra">
															占比</th>
													</tr>
												</thead>
												<tbody class="tbody">
													<tr class="empty_item">
														<td class="empty_tips" colspan="10">暂无数据</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="turn_page tr" id="js_table_pager"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="wrp_loading">
							<div class="stat_loading">
								<i class="icon_loading"></i><span>数据加载中...</span>
							</div>
							<div class="mask"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="faq">
			<ul class="links">
				<li class="links_item"><a
					href="http://kf.qq.com/faq/120911VrYVrA1509086vyumm.html"
					target="_blank">客服中心</a></li>
				<li class="links_item"><a
					href="/acct/infringement?action=getmanual&amp;t=infringement/manual&amp;type=1&amp;lang=zh_CN&amp;token=1811274571"
					target="_blank">侵权投诉</a></li>
			</ul>
			<p class="tail">反馈官号weixingongzhong</p>
		</div>
	</div>








	<script type="text/javascript">

</script>

	<script type="text/html" id="js_faqscene_tpl">
&lt;div class="faqscene" id="js_faqscene_p"&gt;
&lt;div class="faqscene_inner"&gt;
    &lt;div class="faqscene_name js_faq_trigger"&gt;常见问题&lt;/div&gt;
    &lt;div class="faqscene_panel js_faq_main_panel"&gt;
        &lt;a href="###" class="faqscene_close"&gt;x&lt;/a&gt;
        &lt;div class="faqscene_hd"&gt;{data.question}&lt;/div&gt;
        &lt;div class="faqscene_bd"&gt;
            &lt;div class="faqscene_tabs"&gt;
                &lt;div class="faqscene_tab_hd"&gt;
                    &lt;ul class="js_faq_class1"&gt;
                        {if data.guide_list.length&gt;2}
                        {each data.guide_list as guide i}
                        {if guide}
                        &lt;li data-report-id="{guide.report_id}"&gt;&lt;a {if i==0}class="active"{/if} href="javascript:;"&gt;{guide.itemname}&lt;/a&gt;&lt;/li&gt;
                        {/if}

                        {/each}
                        {/if}
                    &lt;/ul&gt;
                &lt;/div&gt;
                {if data.guide_list.length&gt;1}
                {/if}
                {each data.guide_list as guide i}
                {if guide}
                &lt;div class="faqscene_tab_bd js_faqscene_content"&gt;
                    &lt;ul&gt;
                        {each guide.subitems as subitem i}
                        {if subitem}
                        &lt;li&gt;&lt;a target="_blank" href="{subitem.kf_url}"&gt;{subitem.title}&lt;/a&gt;&lt;/li&gt;
                        {/if}
                        {/each}
                    &lt;/ul&gt;
                &lt;/div&gt;
                {/if}
                {/each}
            &lt;/div&gt;
        &lt;/div&gt;
        &lt;div class="faqscene_ft"&gt;&lt;a href="{data.more_help.kf_url}" target="_blank"&gt;{data.more_help.title}&lt;/a&gt;&lt;/div&gt;
    &lt;/div&gt;
&lt;/div&gt;
&lt;/div&gt;
</script>

	<div class="scale_tips" id="zoom_tips" style="display: none;">
		<div class="scale_tips_inner">
			<i class="icon_scale_tips"></i>
			<p class="scale_tips_content">
				<span id="zoom_msg"></span><a href="javascript:;" id="zoom_prompt">不再提示</a>
			</p>
		</div>
	</div>
	<div
		style="position: absolute; right: 0px; bottom: 0px; visibility: visible;">
		<object type="application/x-shockwave-flash" 	data="https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/zoom230dc3.swf" id="ZoomFlash" width="10" height="10">
			<param name="movie" 	value="https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/zoom230dc3.swf">
			<param name="allowscriptaccess" value="always">
			<param name="wmode" value="transparent">
			<param name="scale" value="noScale">
		</object>
	</div>
</body>
</html>