/*
define(function(require) {
    var App = require('https://static.koudaitong.com/v2/www/pages/showcase/feature/app');
    var Backbone = require('https://static.koudaitong.com/v2/vendor/backbone');
    var Router = require('https://static.koudaitong.com/v2/www/pages/showcase/feature/routers/router.js');
    window.router = new Router({
        app: App
    });

    Backbone.history.start();
});
*/
function cfgModel() {
	var data = this;
	data.shopId = "1";
	data.pageName = "usercenter"
	data.html = "";
	data.title = "";
};

function titleData() {
	var data = this;
	data.titleName=$("input[name='title']").attr("value"); 	//页面名称
	data.titleImage=$(".custom-level-img").attr("src");		//开头背景图片
	data.titleShowLevel=$("input[name='show_level']").attr("value"); 	//是否显示等级
	data.titleShowPoint=$("input[name='show_point']").attr("value");	//是否显示积分
};
requirejs.config({
    //默认情况下模块所在目录为js/lib
    baseUrl: 'wpage/js',
    //当模块id前缀为app时，他便由js/app加载模块文件
    //这里设置的路径是相对与baseUrl的，不要包含.js
    paths: {
        //jquery: 'libs/jquery-1.8.3',
        public: 'libs/public',
        tpl:'libs/tpl',
        resig:'libs/resig',
        text:'libs/text'
    }
});

require(['tpl','text!templates/usertenter_init.html'],function (T,html) {
    $(".js-app-main").append('<div class="app-design clearfix">'+html+'</div>');
    
    $(".btn-add").click(function () {
    	//把模块右下角的编辑动作去掉
    	$(".actions").html("");
    	//去掉所有组件编辑状态
		$(".app-field").removeClass("editing");
    	var html = "<div id='title'>";
    	html += $(".app-entry").find("#title").html();
    	html += $(".app-entry").find(".ui-sortable").html();
    	html += "</div>";
    	var cfg = new cfgModel();
    	cfg.html = html;
    	var td = new titleData();
    	cfg.title = td;
    	$.post("wShop/saveUserCenterPage.json", JSON.stringify(cfg), function(data) {
    		//上架后把模块右下角的编辑动作加上
    		require(['tpl','text!templates/actionsS.html'],function (T,html) {
    			$(".actions").append(html);
    		});
    		var data = data;
    		if (data.success == "true") {
    			alert("上架成功！");
    		} else {
    			alert("上架失败!");
    		}
    	}, "json");
    });

    $(".btn-preview").click(function() {
    	//把模块右下角的编辑动作去掉
    	$(".actions").html("");
    	//去掉所有组件编辑状态
		$(".app-field").removeClass("editing");
    	var html = "<div id='title'>";
    	html += $(".app-entry").find("#title").html();
    	html += $(".app-entry").find(".ui-sortable").html();
    	html += "</div>";
    	var cfg = new cfgModel();
    	cfg.html = html;
    	var td = new titleData();
    	cfg.title = td;
    	$.post("wShop/saveUserCenterPage.json", JSON.stringify(cfg), function(data) {
    		require(['tpl','text!templates/actionsS.html'],function (T,html) {
    			$(".actions").append(html);
    		});
    		var data = data;
    		if (data.success == "true") {
    			window.open("wShop/preview.json");
    		} else {
    			alert("预览失败!");
    		}
    	}, "json");
    });
});

//加载头部
require(['tpl', 'text!texts/title.html','text!templates/usercenter_titleS.html'],function (T,html,tits) {
	$(".js-sidebar-region").empty();
	$(".js-sidebar-region").append('<div>'+tits+'</div>');
	
	var titleData = $("#data").html();
	if (titleData) {
		setTitleData(titleData);
		html = tpl(html, {title:$("input[name='title']").attr("value"),color:"#f9f9f9"});
	} else {
		html = tpl(html, {title:"会员主页",color:"#f9f9f9"});
	}
	title = '<div class="app-field clearfix editing" data-field-s="usercenter_title">'+html+'</div>';
	
	$("#usercenter").hide();
	$("#data").hide();
	var html = $("#usercenter").html();
	console.log(html);
	if (html == null || html == "") {
		//之前没保存会员主页，加载默认内容
		require(['tpl','text!texts/usercenter_title.html'],function (T,html) {
			title += "<div id='title'>" + html + "</div>"
			$(".js-config-region").append(title);
		});
	} else {
		//之前已保存， 加载保存内容
		require(['tpl'], function(T) {
			title += html;
			$(".js-config-region").append(title);
		});
		
		//上架后把模块右下角的编辑动作加上
		require(['tpl','text!templates/actionsS.html'],function (T,html) {
			$(".actions").append(html);
			
			//模块点击事件
			$(".app-field").click(function(){
				var x=$(this).offset().top,sname=$(this).data("field-s"),self=$(this);
				$(".app-field").removeClass("editing");
				$(this).addClass("editing").siblings().removeClass("editing");
				$(".app-sidebar").css({top:x+"px"});
				require(['text!templates/'+sname+'S.html'],function(html){
					$(".app-sidebar").show();
					$(".js-sidebar-region").empty();
					$(".js-sidebar-region").append('<div>'+html+'</div>');
				});
				
			});
			$(".actions-wrap .add,.actions-wrap .delete").click(function(){
				var self=$(this).parents(".app-field"),x=self.offset().top;
				$(".app-field").removeClass("editing");
				self.addClass("editing").siblings().removeClass("editing");
				$(".app-sidebar").css({top:x+"px"});
				if($(this).text()=="加内容"){
					require(['text!texts/centerS.html'],function(h){
						$(".app-sidebar").show();
						$(".js-sidebar-region").empty();
						$(".js-sidebar-region").append('<div>'+h+'</div>');
					});
					return false;
				}
				if($(this).text()=="删除"){
					$(".app-sidebar").hide();
					alert("确定删除");
					self.empty();
					return false;
				}
			});
		});
	}
	
	$("input[name='show_level']").click(function() {
		var isLevel = $("input[name='show_level']").attr("value");
		if (isLevel == 1) {
			$("input[name='show_level']").attr("value", 0);
			$("input[name='show_level']").attr("checked", false);
			var isPoint = $("input[name='show_point']").attr("value");
			if (isPoint == 1) {
				$(".custom-level-title").html("你拥有本店积分：888");
			} else {
				$(".custom-level-title-section").hide();
			}
		} else {
			$("input[name='show_level']").attr("value", 1);
			$("input[name='show_level']").attr("checked", true);
			var isPoint = $("input[name='show_point']").attr("value");
			if (isPoint == 1) {
				$(".custom-level-title").html("尊贵的｛会员等级名｝ <br> 你拥有本店积分：888");
			} else {
				$(".custom-level-title-section").show();
				$(".custom-level-title").html("尊贵的｛会员等级名｝");
			}
		}
	});

	$("input[name='show_point']").click(function() {
		var isPoint = $("input[name='show_point']").attr("value");
		if (isPoint == 1) {
			$("input[name='show_point']").attr("value", 0);
			$("input[name='show_point']").attr("checked", false);
			var isLevel = $("input[name='show_level']").attr("value");
			if (isLevel == 1) {
				$(".custom-level-title").html("尊贵的｛会员等级名｝");
			} else {
				$(".custom-level-title-section").hide();
			}
		} else {
			$("input[name='show_point']").attr("value", 1);
			$("input[name='show_point']").attr("checked", true);
			var isLevel = $("input[name='show_level']").attr("value");
			if (isLevel == 1) {
				$(".custom-level-title").html("尊贵的｛会员等级名｝ <br> 你拥有本店积分：888");
			} else {
				$(".custom-level-title-section").show();
				$(".custom-level-title").html("你拥有本店积分：888");
			}
		}
	});
	
	$(".input-xxlarge").focusout(function () {
		var titleName = $(".input-xxlarge").attr("value");
		$("#titleName").html(titleName);
	});
	
	 $(".control-action").click(function() {
    	layer.open({
    		title : "图片素材",
    		type : 2,
    		area : [ "90%", "85%" ],
    		content : 'materialLocal/imageList.shtml'
    	});
    });
	 
});

require(['text!texts/addCenter.html'],function (html) {
	//加载组件页面
	$(".js-add-region").append('<div>'+html+'</div>');
	//点击页面下面每一个组件触发事件
	$(".js-new-field").click(function(){
		//取得每个组件的类型
		var name=$(this).data("field-type");
		require(['text!texts/tpltext/'+name+'.html'],function(html){
			//去掉所有组件编辑状态
			//$(".ui-sortable").find(".app-field").removeClass("editing");
			$(".app-field").removeClass("editing");
			//将当前组件的模版内容加入到编辑页面中
			$(".ui-sortable").append('<div class="app-field clearfix editing" data-field-s="'+name+'">'+html+'</div>');
			//改变右侧窗口的显示位置
			var top=$(".app-fields .editing").offset().top;
			$(".app-sidebar").css({top:top+"px"});
			//加载对应组件的右侧内容并显示
			require(['text!templates/'+name+'S.html'],function(html){
				$(".app-sidebar").show();
				$(".js-sidebar-region").empty();
				$(".js-sidebar-region").append('<div>'+html+'</div>');
			});
			//点击左侧页面的每个组件的事件
			$(".app-field").click(function(){
				//改变右侧窗口的高度
				var x=$(this).offset().top,sname=$(this).data("field-s");
				$(".app-field").removeClass("editing");
				$(this).addClass("editing").siblings().removeClass("editing");
				$(".app-sidebar").css({top:x+"px"});
				//显示对应的右侧模版内容
				require(['text!templates/'+sname+'S.html'],function(html){
					$(".app-sidebar").show();
					$(".js-sidebar-region").empty();
					$(".js-sidebar-region").append('<div>'+html+'</div>');
				});
			});
			//点击左侧浮动按钮的事件（加内容，删除）
			$(".actions-wrap .add,.actions-wrap .delete").click(function(){
				//调整右侧的显示位置
				var self=$(this).parents(".app-field"),x=self.offset().top;
				$(".app-field").removeClass("editing");
				self.addClass("editing").siblings().removeClass("editing");
				$(".app-sidebar").css({top:x+"px"});
				//如果点击的是加内容，则载右侧弹出组件列表
				if($(this).text()=="加内容"){
					require(['text!texts/centerS.html'],function(h){
						$(".app-sidebar").show();
						$(".js-sidebar-region").empty();
						$(".js-sidebar-region").append('<div>'+h+'</div>');
					});
					return false;
				}
				//如果点击的是删除则弹出确认窗口
				if($(this).text()=="删除"){
					$(".app-sidebar").hide();
					alert("确定删除");
					self.empty();
					return false;
				}
			});
			
		});
	});
	//console.log($(".app-preview .app-entry").html());
});

function setTitleData(data) {
	var data = JSON.parse(data);
	$("input[name='title']").attr("value", data.titleName);		//页面名称
	$("#titleName").html(data.titleName);
	$("#custom-level-img").attr("src", data.titleImage);		//开头背景图片
	$("input[name='show_level']").attr("value", data.titleShowLevel); 	//是否显示等级
	if (data.titleShowLevel == 1) {
		$("input[name='show_level']").attr("checked", true);
	} else {
		$("input[name='show_level']").attr("checked", false);
	}
	$("input[name='show_point']").attr("value", data.titleShowPoint);	//是否显示积分
	if (data.titleShowPoint == 1) {
		$("input[name='show_point']").attr("checked", true);
	} else {
		$("input[name='show_point']").attr("checked", false);
	}
}

function imageSwitch(media) {
	/*$("#headPictureUrl").attr("style", "background-size: 100% 100%; background-image:url(\"" + media.localUrl + "\");");
	$("#imgHeadPictureUrl").attr("src", media.localUrl);
	$("#wxHeadPictureUrl").attr("value", media.picUrl);*/
	$("#custom-level-img").attr("src", media.localUrl);
	$(".custom-level-img").attr("src", media.localUrl);
}