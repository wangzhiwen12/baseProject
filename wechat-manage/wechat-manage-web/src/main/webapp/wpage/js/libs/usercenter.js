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
};
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
	html = tpl(html, {title:"会员主页",color:"#f9f9f9"});
	title = '<div class="app-field clearfix editing" data-field-s="usercenter_title">'+html+'</div>';
	
	$("#usercenter").hide();
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
		require([], function() {
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
	
	$(".js-sidebar-region").empty();
	$(".js-sidebar-region").append('<div>'+tits+'</div>');
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
