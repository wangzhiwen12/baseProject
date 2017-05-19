//基础路径配置
var baseUrl = "wechatShopPage/";
var curPicSel;
// ajax调后台获取收据的封装
function cfgModel(data) {
	var self = this;
//	self.Id = data.Id;// 系统编号
//	self.LogoFullUrl = data.LogoFullUrl;// 页面地址
//	self.WxHeadHtml = data.WxHeadHtml;// 微信头部动态html内容
//	self.WxHeadHtmlM = data.WxHeadHtmlM;// 微信头部动态html对象, 无内容
	self.WxHomeHtmlM = data.WxHomeHtmlM;// 微信头部动态html对象
//	self.IsNew = data.IsNew;
}
function imageSwitch(media) {
	curPicSel.closest(".selectPicWrap").find("input.picIpt")
			.val(media.localUrl).change();
}
// 20160906 点击新增
var byId = function(id) {
	return document.getElementById(id);
}
// 拖拽
function addSortable() {
	var sortable = Sortable.create(byId('P'), {
		draggable : ".common-drag",
		onStart : function(/** Event */
		evt) {
			evt.oldIndex; // element index within parent
			$(evt.item).siblings().removeClass('cur');
			$(evt.item).addClass('cur');
		},
		onEnd : function(/** Event */
		evt) {
			sortCommon();
		},
		handle : '.handle'
	});
}

// 返回 keys
function keys(obj) {
	var a = [];
	for (key in obj) {
		key.push(a);
	}
	return a;
}

// new
var _data = {};
function adjustIdx(a, idx, tgt) {
	a.splice(tgt, 0, a.splice(idx, 1)[0]);
	return a;
}

// 组件 排序后 => data
function sortCommon() {
	var doms = $('.common');
	// var ls = JSON.parse(localStorage.getItem('way'));
	var ls = scope.data;
	var o = {};
	var a = [];
	doms.each(function() {
		a.push($(this).attr("id"));
	});

	for (var i = 0; i < a.length; i++) {
		var item = ls[a[i]];
		o[a[i]] = item;
	}

	scope.data = o;
	_M['actual'] = scope.data;
	$('pre').html(JSON.stringify(_M, undefined, 2));
	return JSON.stringify(_M);
}

function addDrag() {

	$('#P').on('mousedown', '.repeat', function() {
		var idx = $(this).index();
		$(this).data("oldindex", idx);
	});

	[].forEach.call(byId('P').getElementsByClassName('repeat-wrapper'),
			function(el) {
				Sortable.create(el,
						{
							group : 'photo',
							animation : 150,
							onStart : function(/** Event */
							evt) {
								// evt.oldIndex = $(evt.item).index(); //
								// element index within parent
								// $(evt.item).data("oldindex", evt.oldIndex);
								evt.oldIndex = $(evt.item).data("oldindex");
							},
							onEnd : function(/** Event */
							evt) {
								var _scope = $(evt.target).data('repeat');
								idx = $(evt.item).data("oldindex"),
								// tgt = evt.newIndex,
								tgt = $(evt.item).index();
								scope.sort(_scope, idx, tgt);
								$('pre').html(
										JSON
												.stringify(scope.data,
														undefined, 2));
							}
						});
			});
}

// 添加模块
function creatCommon(temp, data, insert) {
	var data = data, obj = {};
	var timestamp = new Date().getTime();
	var html = template(temp, {
		'temp' : timestamp
	});
	var cur = $('.common.cur');

	// 插入当前模块
	$('.common').removeClass('cur');
	if (insert) {
		cur.after(html);
		$('.common').eq(insert).addClass('cur');
		$('.insertCommon').addClass('wdn');
	} else {
		$('#P').append(html);
		$('.common:last').addClass('cur');
	}

	if (data) {
		// scope.set((temp + '_' + timestamp), data);
		data = obj[(temp + '_' + timestamp)] = _data[(temp + '_' + timestamp)] = scope.data[(temp
				+ '_' + timestamp)] = data;
		scope.eles(obj);
	}

	scope.extendDom();
	// 写入拖拽
	addDrag()
	$('[data-toggle="tooltip"]').tooltip();
	// sortCommon();
}

M = {}, _M = {};
/**
 * 通过后台获取配置数据，利用前台模板引擎加载页面
 */
function loadCfgData() {

	var wPageId;
	var input = document.getElementsByTagName("input");
	for (i = 0; i < input.length; i++) {
		if (!!input[i].attributes
				&& !!input[i].attributes.value) {
			 if (input[i].className== "wPageId") {
				wPageId = input[i].attributes.value.value;
			}
		}
	}
	$.post( baseUrl + "getWPageInfo.shtml", wPageId,
			function(data) {
				if (data) {
					console.log(data);
				
					$('#P').append(data);
				}else{
					M = _M['actual'] = {
							"comheader__default" : {
								"title" : "页面名称"
							}
						}
					var tpls = [];
					for (key in M) {
						tpls.push(key);
					}
					var html0,html1,html2;
					for (var i = 0; i < tpls.length; i++) {
						(function(i) {
							var a = tpls[i].split('__');
							console.log(a);
							if(a[0]==="comheader"){
								 html0 = template(a[0], {
									'temp' : a[1]
								});
							}
							else{
								 html2 += template(a[0], {
										'temp' : a[1]
									});
							}
							
						})(i)
					}
					$('#P').append(html0+html1);
				}
					$(".common").removeClass("cur");
					$('.common:first').addClass('cur');
					// 初始化 scope
					scope.data = M;
					scope.clones = [];
					scope.warps = [];

					scope.eles();
					scope.tgt = $('#P');
					// 扩展方法
					scope.template = template;
					scope._Sortable = Sortable;
					scope.bindEvent();
					scope.extendDom();

					// 添加拖拽
					addSortable();
					addDrag();

					// bootstrap tooltip
					$('[data-toggle="tooltip"]').tooltip();
					// 设定相关参数
					$("#pageurl").text(data.PageUrl);
					// 品牌 logo
					$(".BrandLogo").attr("src", data.LogoFullUrl);
					$(".BrandName").val(data.BrandName).change();
					// 20160325 wszl
					$(".ctl-wszl").each(
							function() {
								var _$this = $(this);
								var vid = _$this.attr("id").split("_");
								vid.splice(0, 1, "v");
								$("#" + vid.join("_")).parent()
										.addClass("wszlitem");
							})
			}, "json");
}
loadCfgData();

$('.add-common')
		.click(
				function() {
					var _$this = $(this), data = _$this.data('type'), insert = _$this
							.data('insert'), insert = insert ? $('.common.cur')
							.index() + 1 : 0;

					switch (data) {

					case 'addgoods':
						var temp = 'add_goods', data = {
							"list" : [ {}, {}, {}, {} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;
					case 'textnavigation':
						var temp = 'text-navigation', data = {
							"list" : [ {} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'imgnavigation':
						var temp = 'img-navigation', data = {
							list : [ {}, {}, {}, {} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'titlenavigation':
						var temp = 'title_navigation', data = {
							'list' : [ {} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'mallorders':
						var temp = 'mall-orders', data = {
							'title' : 'mall-orders'
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'header':
						var temp = 'header', data = {
							template : "head-style-1"
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'uxiliaryblank':
						var temp = 'uxiliary-blank', data = {
							'height' : '32'
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'picturenavigation':
						var temp = 'picture-navigation', data = {
							'list' : [ {} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'showcase':
						var temp = 'show-case', data = {
							style : "normal clearfix",
							list : [ {
								"link" : "#",
								"title" : "美妆"
							}, {
								"link" : "#",
								"title" : "进口食品"
							}, {
								"link" : "#",
								"title" : "护肤"
							} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'mallindex':
						var temp = 'mall-index', data = {
							title : "进入店铺"
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'notice':
						var temp = 'notice', data = {
							"title" : "标题"
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'search':
						var temp = 'search', data = {
							"title" : "商品搜索"
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'richtext':
						var temp = 'rich-text', data = {
							"content" : "富文本内容"
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					case 'swiper':
						var temp = 'swiper', data = {
							mode : "notSwiper",
							list : [ {
								"link" : "#",
								"title" : "美妆"
							}, {
								"link" : "#",
								"title" : "进口食品"
							}, {
								"link" : "#",
								"title" : "护肤"
							}, {
								"link" : "#",
								"title" : "家具"
							} ]
						}, insert = insert
						creatCommon(temp, data, insert)
						break;

					}
				})

// loaded
$(function() {

	$('.dropdown-toggle').dropdown();
	// input
	$('#p-data').on('mousedown', 'input', function() {
		$(this).closest('.repeat').attr("draggable", false);
	});

	$('body').on('click', '.common', function() {
		$('.common').removeClass('cur');
		$(this).addClass('cur');
	});

	// 插入
	$('#p-data').on('click', '.insert-common', function(e) {
		e.stopPropagation();
		$('.common').removeClass('cur');
		$(this).closest('.common').addClass('cur');
		var top = $(e.target).offset().top - 130;
		left = $(e.target).offset().left - 130;
		$('.insertCommon').removeClass('wdn').css({
			'left' : left,
			'top' : top
		});
	});

	// 删除
	$('#p-data').on('click', '._del', function(e) {
		e.stopPropagation();
		var _scope = $(this).data('scope');
		delete scope.data[_scope];
		$('.common').removeClass('cur');
		var common = $(this).closest('.common');
		if (common.next('.common').length) {
			common.next('.common').addClass('cur');
		} else {
			common.prev('.common').addClass('cur');
		}
		$('pre').html(JSON.stringify(scope.data, undefined, 2));
		common.remove();
	});
});

// 去弹窗
var domHasClass = function(element, cls) {
	return (' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
};

function isClickPop(dm) {
	var ret = false;
	while (dm.parentNode) {
		if (domHasClass(dm, "wPopup")) {
			ret = true;
			break;
		}
		dm = dm.parentNode;
	}
	return ret;
}

$("#controllerTgt").on(
		"click",
		function(e) {
			var dm = e.target;
			if (!domHasClass(dm, "triggerwPopup") && $(".wPopup").length > 0
					&& !isClickPop(dm)) {
				$(".wPopup").remove();
			}
		});

$('body').on('click', function() {
	if ($('.insertCommon:visible').length > 0) {
		$('.insertCommon').addClass('wdn');
	}
});

// 去弹窗 end

$(".yl").click(function() {
	var ret = document.getElementById("viewsTgt").innerHTML;
	document.getElementById("Ret").value = ret;
})
$(".medit").click(function() {
	var sm = JSON.stringify(M)
	var ret = document.getElementById("viewsTgt").innerHTML;
	document.getElementById("Ret0").value = ret;
	document.getElementById("edit").value = sm;
});
$("#P").on('click', '.select-pic', function() {

	curPicSel = $(this);
	layer.open({
		title : "图片素材",
		type : 2,
		area : [ "90%", "85%" ],
		content : contextPath + '/materialLocal/imageList.shtml'
	});

});
$("#P").on('click', '.select-pro', function() {

	curPicSel = $(this);
	layer.open({
		title : "商品列表",
		type : 2,
		area : [ "90%", "85%" ],
		content : contextPath + '/wechatShopPage/proList.shtml'
	});
});

$("#save")
		.click(
				function() {
					var wPageId, wPageTitle="";
					var title,proCodes="default";
					var input = document.getElementsByTagName("input");
					wPageTitle=$('.wx_title').html();
					
					for (i = 0; i < input.length; i++) {
						if (!!input[i].attributes
								&& !!input[i].attributes.value) {
							 if (input[i].className== "wPageId") {
								wPageId = input[i].attributes.value.value;
							}
						}
					}
					for(i=0;i<$('#P>.common').length+1;i++){
						if($('#P>.common>.views>div')[i].getAttribute("class")=="w-goods-nav"){
							
							for(n=0;n<$('#P>.common>.views a.w-goods-nav-item').length;n++){
								var url=$('#P>.common>.views a.w-goods-nav-item')[n].getAttribute("href");
								proCodes += url.substring(url.indexOf("=")+1,url.length)+";";
							}
						}else if($('#P>.common>.views>div')[i].getAttribute("class")=="w-tit"){
							title=$('#P>.common>.views .w-tit-item').html();
						}
					}
					var htmlS = $("#P").html();
					//把模块右下角的编辑动作去掉
					$(".handle").html("");
					$(".head-style-1").html("");
					$("ui-form-horizontal").html("");
					$(".ui-shadow").html("");
				
					$(".img-navigation>div").removeClass("handle");
					//去掉所有组件编辑状态
					$(".common").removeClass("cur");
					
					var html = $("#P").html();
					var data = JSON.stringify({
						"wPageTitle" : wPageTitle,
						"proCodes":proCodes,
						"title":title
					});
					var odata = JSON.stringify({
						"param" : data,
						"status" : 1,
						"id":wPageId,
						"html":html,
						"htmlS":htmlS
					});
					$.ajax({
						url : getContextPath() + "/wechatShopPage/saveWPage.shtml",
						type : "post",
						dataType : "json",
						data : odata,
						success : function(json) {
							if (json == "success") {
								layer.msg('保存成功');
									var tb = $("#loadhtml");
							   	    tb.html(CommnUtil.loadingImg());
							   	    tb.load(rootPath + '/wechatShopPage/list.shtml');
							} else {
								layer.msg('保存失败');
								var tb = $("#loadhtml");
								 tb.html(CommnUtil.loadingImg());
								  tb.load(rootPath + '/wechatShopPage/list.shtml');
							}
						}
					});
				});
$("#saveDraft")
.click(
		function() {
			var wPageId, wPageTitle="";
			var title,proCodes="default";
			var input = document.getElementsByTagName("input");
			wPageTitle=$('.wx_title').html();
			
			for (i = 0; i < input.length; i++) {
				if (!!input[i].attributes
						&& !!input[i].attributes.value) {
					 if (input[i].className== "wPageId") {
						wPageId = input[i].attributes.value.value;
					}
				}
			}
			for(i=0;i<$('#P>.common').length+1;i++){
				if($('#P>.common>.views>div')[i].getAttribute("class")=="w-goods-nav"){
					
					for(n=0;n<$('#P>.common>.views a.w-goods-nav-item').length;n++){
						var url=$('#P>.common>.views a.w-goods-nav-item')[n].getAttribute("href");
						proCodes += url.substring(url.indexOf("=")+1,url.length)+";";
					}
				}else if($('#P>.common>.views>div')[i].getAttribute("class")=="w-tit"){
					title=$('#P>.common>.views .w-tit-item').html();
				}
			}
			var htmlS = $("#P").html();
			//把模块右下角的编辑动作去掉
			$(".handle").html("");
			$(".head-style-1").html("");
			$("ui-form-horizontal").html("");
			$(".ui-shadow").html("");
		
			$(".img-navigation>div").removeClass("handle");
			//去掉所有组件编辑状态
			$(".common").removeClass("cur");
			
			var html = $("#P").html();
			var data = JSON.stringify({
				"wPageTitle" : wPageTitle,
				"proCodes":proCodes,
				"title":title
			});
			var odata = JSON.stringify({
				"param" : data,
				"status" : 0,
				"id":wPageId,
				"html":html,
				"htmlS":htmlS
			});
			$.ajax({
				url : getContextPath() + "/wechatShopPage/updateDraft.shtml",
				type : "post",
				dataType : "json",
				data : odata,
				success : function(json) {
					if (json == "success") {
						layer.msg('保存成功');
							var tb = $("#loadhtml");
					   	    tb.html(CommnUtil.loadingImg());
					   	    tb.load(rootPath + '/wechatShopPage/list.shtml');
					} else {
						layer.msg('保存失败');
						var tb = $("#loadhtml");
						 tb.html(CommnUtil.loadingImg());
						  tb.load(rootPath + '/wechatShopPage/list.shtml');
					}
				}
			});
		});
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
