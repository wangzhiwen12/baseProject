// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9 
function cfgModel() {
	var data = this;
	data.shopId = "1";
	data.pageName = "xx";
	data.html = "";
	data.id="";
		
};
function preview(){
	    	//把模块右下角的编辑动作去掉
	    	$(".actions").html("");
	    	//去掉所有组件编辑状态
			$(".app-field").removeClass("editing");
			var id =null ;
			var input=document.getElementsByTagName("input");
			for( i=0;i<input.length;i++){
    			if(!!input[i].attributes.class&&input[i].attributes.value){
    				if(input[i].attributes.class.value=="pageId"&&!!input[i].attributes.value.value){
    					id=input[i].attributes.value.value;
    				}
    			}
    		}
	    	var html = $(".app-preview>.app-entry>.app-fields").html();
	    	console.log(html);
	    	var cfg = new cfgModel();
	    	cfg.html = html;
	    	cfg.id=id;
	    	$.post("previewTPage.shtml", JSON.stringify(cfg), function(data) {
	    		require(['tpl','text!templates/actionsS.html'],function (T,html) {
	    			$(".actions").append(html);
	    		});
	    		var data = data;
	    		if (data.success == "true") {
	    			window.open("preview.shtml?id="+id);
	    		} else {
	    			alert("预览失败!");
	    		}
	    	}, "json");
}

function OnInput(event) {
	if(event.target.attributes.class.value=="notice"){
		$(".custom-notice-scroll>span").html(event.target.value);
		if(event.target.value==""){
			$(".custom-notice-scroll>span").html("公告：请填写内容，如果过长，将会在手机上滚动显示");
		}
		 var input=document.getElementsByTagName("input");
		 	for( i=0;i<input.length;i++){
				if(!!input[i].attributes.class){
					if(input[i].attributes.class.value=="notice"){
						input[i].attributes.value.value=event.target.value;
					}
				}
			}
	}else if(event.target.attributes.class.value=="wpageTitleS"){
		$("h1>span").html(event.target.value);
		if(event.target.value==""){
			$("h1>span").html("请输入微页面名称");
		}
		var input=document.getElementsByTagName("input");
		 	for( i=0;i<input.length;i++){
				if(!!input[i].attributes.class){
					if(input[i].attributes.class.value=="wpageTitle"){
						input[i].attributes.value.value=event.target.value;
					}
				}
			}
	}
}
// Internet Explorer
function OnPropChanged(event) {
	$(".custom-notice-scroll>span").html(event.target.value);
	if(event.target.value==""){
		$(".custom-notice-scroll>span").html("公告：请填写内容，如果过长，将会在手机上滚动显示");
	}
}
function upload() {
	var pageId,notice,wpageTile;
	 var input=document.getElementsByTagName("input");
	 	for( i=0;i<input.length;i++){
			if(!!input[i].attributes.class&&!!input[i].attributes.value){
				if(input[i].attributes.class.value=="pageId"&&!!input[i].attributes.value.value){
					pageId =input[i].attributes.value.value;
				}
			}
			if(!!input[i].attributes.class){
				if(input[i].attributes.class.value=="notice"){
					notice =input[i].attributes.value.value;
				}
			}
			if(!!input[i].attributes.class){
				if(input[i].attributes.class.value=="wpageTitle"){
					wpageTile =input[i].attributes.value.value;
				}
			}
		}
	var tpageEdit =JSON.stringify({
		"notice" : notice,
		"wpageTitle":wpageTile
	});
	var odata = JSON.stringify({
		"param" : tpageEdit,
		"status" : 1,
		"id":pageId
	});
	$.ajax({
		url : getContextPath() + "/wechatShopPage/saveDraft.shtml",
		type : "post",
		dataType : "json",
		data : odata,
		success : function(json) {
			if (json == "success") {
				layer.confirm('保存成功!是否关闭窗口?', function(index) {
					parent.layer.close(parent.pageii);
					parent.location.reload();
				});
			} else {
				layer.confirm('添加失败!是否关闭窗口?', function(index) {
					parent.layer.close(parent.pageii);
					parent.location.reload();
				});
			}
		}
	})
}
function uploadDraft() {
	var pageId,notice,wpageTile;
	 var input=document.getElementsByTagName("input");
	 	for( i=0;i<input.length;i++){
			if(!!input[i].attributes.class&&!!input[i].attributes.value){
				if(input[i].attributes.class.value=="pageId"&&!!input[i].attributes.value.value){
					pageId =input[i].attributes.value.value;
				}
			}
			if(!!input[i].attributes.class&&!!input[i].attributes.value){
				if(input[i].attributes.class.value=="notice"){
					notice =input[i].attributes.value.value;
				}
			}
			if (!!input[i].attributes.class&&!!input[i].attributes.value){
				if(input[i].attributes.class.value=="wpageTitle"){
					wpageTile =input[i].attributes.value.value;
				}
			}
		}
	var tpageEdit =JSON.stringify({
		"notice" : notice,
		"wpageTitle":wpageTile
	});
	var odata = JSON.stringify({
		"param" : tpageEdit,
		"status" : 1,
		"id":pageId
	});
	console.log(odata);
 	$.ajax({
		type : "post",
		url : getContextPath() + "/wechatShopPage/updateDraft.shtml",
		dataType : 'json',
		data : odata,
		success : function(result) {
			if (result == "success") {
				layer.confirm('保存成功!是否关闭窗口?', function(index) {
					parent.layer.close(parent.pageii);
					parent.location.reload();
				});
			} else {
				layer.confirm('保存成功!是否关闭窗口?', function(index) {
					parent.layer.close(parent.pageii);
					parent.location.reload();
				});
			}
		}
	});
}
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
