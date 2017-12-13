
requirejs.config({
    // 默认情况下模块所在目录为js/lib
    baseUrl: getContextPath() + '/wpage/js',
    // 当模块id前缀为app时，他便由js/app加载模块文件
    // 这里设置的路径是相对与baseUrl的，不要包含.js
    paths: {
        jquery: 'libs/jquery-1.9.1.min',
        public:'libs/public',
        tpl:'libs/tpl',
        resig:'libs/resig',
        text:'libs/text'
    }
});
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
require(['jquery','tpl','text!templates/init.html'],function ($,T,html) {
    $(".js-app-main").append('<div class="app-design clearfix">'+html+'</div>');
});

require(['jquery','tpl','text!templates/wpageTitle.html','text!templates/wpageTitleS.html'],function ($,T,html,tits) {
	var input=document.getElementsByTagName("input");
	var name=null;
	var test=null;
	for( i=0;i<input.length;i++){
		if(!!input[i].attributes.class&&!!input[i].attributes.value){
			if(input[i].attributes.class.value=="wpageTitle"&&!!input[i].attributes.value.value){
				  name=input[i].attributes.class.value;
				  test=input[i].attributes.value.value;
			}
		 }
	 }
	if(test){
		html = tpl(html,{"wpageTitle":test,color:"f9f9f9"});
    	tits=tpl(tits,{"wpageTitle":test});
    }else{
		html = tpl(html,{"wpageTitle":"",color:"f9f9f9"});
    	tits=tpl(tits,{"wpageTitle":""});
    }
    $(".js-config-region").append('<div class="app-field clearfix editing" data-field-s="wpageTitle">'+html+'</div>');
    $(".js-sidebar-region").empty();
    $(".js-sidebar-region").append('<div>'+tits+'</div>');
});


require(['jquery','text!texts/addCenter.html'],function ($,html) {
    $(".js-add-region").append('<div>'+html+'</div>');
    var input=document.getElementsByTagName("input");
    var test,name;
	for( i=0;i<input.length;i++){
		if(!!input[i].attributes.class&&!!input[i].attributes.value){
			if(input[i].attributes.class.value=="notice"&&!!input[i].attributes.value.value){
				  name=input[i].attributes.class.value;
				  test=input[i].attributes.value.value;
			        require(['text!texts/tpltext/'+name+'.html'],function(html){
			        	if(test){
			        		html = tpl(html,{"notice":test});
				        }
			            $(".ui-sortable").find(".app-field").removeClass("editing");
			            $(".ui-sortable").append('<div class="app-field clearfix editing" data-field-s="'+name+'">'+html+'</div>');
			            var top=$(".app-fields .editing").offset().top;
			            $(".app-sidebar").css({top:top+"px"});
			            require(['text!templates/'+name+'S.html'],function(html){
			            	if(name=="notice"){
			            		html = tpl(html,{"notice":test});
			            	}
			                $(".app-sidebar").show();
			                $(".js-sidebar-region").empty();
			                $(".js-sidebar-region").append('<div>'+html+'</div>');
			            });
			});
		}
	}
}
    $(".js-new-field").click(function(){
        var name=$(this).data("field-type");
        var noticeV,wpageTitleV=null;
        var input=document.getElementsByTagName("input");
         	for( i=0;i<input.length;i++){
         		if(!!input[i].attributes.class ){
         			if(input[i].attributes.class.value=="notice"&&input[i].attributes.class.value){
         				noticeV=input[i].attributes.value.value;
         			}else if(input[i].attributes.class.value=="wpageTitle"&&input[i].attributes.class.value){
         				wpageTitleV=input[i].attributes.value.value;
         			}
         		}
         	}
        require(['text!texts/tpltext/'+name+'.html'],function(html){
        	if(name=="notice"){
        		html = tpl(html,{"notice":""});
        	}
            $(".ui-sortable").find(".app-field").removeClass("editing");
            $(".ui-sortable").append('<div class="app-field clearfix editing" data-field-s="'+name+'">'+html+'</div>');
            var top=$(".app-fields .editing").offset().top;
            $(".app-sidebar").css({top:top+"px"});
            require(['text!templates/'+name+'S.html'],function(html){
            	if(name=="notice"){
            		html = tpl(html,{"notice":""});
            	}
                $(".app-sidebar").show();
                $(".js-sidebar-region").empty();
                $(".js-sidebar-region").append('<div>'+html+'</div>');
            });
            $(".app-field").click(function(){
                var x=$(this).offset().top,sname=$(this).data("field-s");
                $(".app-field").removeClass("editing");
                $(this).addClass("editing");
                $(".app-sidebar").css({top:x+"px"});
                require(['text!templates/'+sname+'S.html'],function(html){
                	if(sname=="notice"){
                		html = tpl(html,{"notice":noticeV});
                	}
                	if(sname=="wpageTitle"){
                		html = tpl(html,{"wpageTitle":wpageTitleV});
                	}
                    $(".app-sidebar").show();
                    $(".js-sidebar-region").empty();
                    $(".js-sidebar-region").append('<div>'+html+'</div>');
                });
            });
            $(".actions-wrap .add,.actions-wrap .delete").click(function(){
                var self=$(this).parents(".app-field"),x=self.offset().top;
                $(".app-field").removeClass("editing");
                self.addClass("editing");
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
            // 公告
            if(name="notice"){
            	
            }
        });
    });

    $(".app-field").click(function(){
    	var noticeV,wpageTitleV=null;
        var input=document.getElementsByTagName("input");
         	for( i=0;i<input.length;i++){
         		if(!!input[i].attributes.class ){
         			if(input[i].attributes.class.value=="notice"&&input[i].attributes.class.value){
         				noticeV=input[i].attributes.value.value;
         			}else if(input[i].attributes.class.value=="wpageTitle"&&input[i].attributes.class.value){
         				wpageTitleV=input[i].attributes.value.value;
         			}
         		}
         	}
        var x=$(this).offset().top,sname=$(this).data("field-s");
        $(".app-field").removeClass("editing");
        $(this).addClass("editing");
        $(".app-sidebar").css({top:x+"px"});
        require(['text!templates/'+sname+'S.html'],function(html){
        	if(sname=="notice"){
        		html = tpl(html,{"notice":noticeV});
        	}
        	if(sname=="wpageTitle"){
        		html = tpl(html,{"wpageTitle":wpageTitleV});
        	}
            $(".app-sidebar").show();
            $(".js-sidebar-region").empty();
            $(".js-sidebar-region").append('<div>'+html+'</div>');
        });
    });
    $(".actions-wrap .add,.actions-wrap .delete").click(function(){
        var self=$(this).parents(".app-field"),x=self.offset().top;
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
