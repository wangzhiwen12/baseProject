var pageii = null;
$('#material_get').click(function(){
	pageii = layer.open({
        title: "图片素材",
        type: 2,
        area: ["90%", "85%"],
        content: rootPath + '/materialLocal/imageList.shtml'
    });
});

$('#news_get').click(function(){
	pageii = layer.open({
        title: "图文素材",
        type: 2,
        area: ["90%", "85%"],
        content: rootPath + '/materialLocal/newsList.shtml'
    });
});

$('#add_Image').click(function(){
	pageii = layer.open({
        title: "图片上传",
        type: 2,
        area: ["50%", "50%"],
        content: rootPath + '/materialLocal/imageInsert.shtml'
    });
});
$(function(){
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    url: rootPath + '/msgReply/initMsgReply.shtml',
	    success: function(msgReplyList) {
    		var list = msgReplyList;
    		for(var i =0;i<list.length;i++){
    			if(list[i].eventType == 'subscribe'){
    				dataInit(list[i]);
    			}
    		}
	    }
	});
});

$('#textInput').click(function(){
	$('#msgType').attr("value",'text');
});
$('#imageInput').click(function(){
	$('#msgType').attr("value",'image');
});
$('#newsInput').click(function(){
	$('#msgType').attr("value",'news');
});

$('#submit_btn').click(function(){
	var eventType = $('#eventType').val();//属于某种回复　关注/无关键字/关键字回复
	if(eventType == 'subscribe'){
		subscribe(eventType);
	}else if(eventType == 'disReply'){
		subscribe(eventType);
	}else if(eventType == 'ruleKey'){
		ruleKey(eventType);
	}
});

$('#addRule').click(function(){
	ruleSwitch();
});

function delRule(ruleSid){
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    data:{
	    	'sid' : ruleSid
	    },
	    url: rootPath + '/msgReply/del.shtml',
	    success: function() {
	    	alert("删除成功");
	    	replyManagerList();
	    }
	});
}

function ruleSwitch(){
	$('#ruleDiv').show();
	$('#replyTable').hide();
	$('#no_key_msg_reply_div').show();
	$('#textmsg').attr("value",null);
	$('#msgType').attr("value",'text');
	$('#yl_img *').remove();
	$("#imageli").removeClass();
	$('#yl_news *').remove();
	$("#newsli").removeClass();
	$("#textli").attr("class",'active');
	$("#imagediv").attr("class",'tab-pane fade');
	$("#newsdiv").attr("class",'tab-pane fade');
	$("#textdiv").attr("class",'tab-pane fade in active');
}

//eventType = subscribe/disReply/ruleKey
function ruleKey(eventType){
	var msgType = $('#msgType').val();//回复类型　文本/图片
	var content = null;
	var mediaId = null;
	if(msgType == 'image' || msgType == 'news'){
		mediaId = $('#mediaId').val();	
	}
	if(msgType == 'text'){
		content = $('#textmsg').val();	
	}
	var ruleName = $('#rule_name').val();
	var msgKey = $('#msg_key').val();
	//关注时自动回复
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    data:{
	    	'eventType' : eventType,
	    	'content' : content,
	    	'mediaId' : mediaId,
	    	'msgType' : msgType,
	    	'ruleName': ruleName,
	    	'msgKey': msgKey
	    },
	    url: rootPath + '/msgReply/msgReplyInsertOrUpdate.shtml',
	    success: function(paramMap) {
    		alert('保存成功');
    		sdk_btn('ruleKey');
    		replyManagerList();
	    }
	});
}


function subscribe(eventType){
	var msgType = $('#msgType').val();//回复类型　文本/图片
	var content = null;
	var mediaId = null;
	if(msgType == 'image' || msgType == 'news'){
		mediaId = $('#mediaId').val();	
	}
	if(msgType == 'text'){
		content = $('#textmsg').val();	
	}
	//关注时自动回复
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    data:{
	    	'eventType' : eventType,
	    	'content' : content,
	    	'mediaId' : mediaId,
	    	'msgType' : msgType
	    },
	    url: rootPath + '/msgReply/msgReplyInsertOrUpdate.shtml',
	    success: function() {
    		alert('保存成功');
	    }
	});
}

function msg_btn_change(eventType){
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    data:{
	    	'eventType' : eventType
	    },
	    url: rootPath + '/msgReply/initMsgReply.shtml',
	    success: function(msgReplyList) {
    		var list = msgReplyList;
    		for(var i =0;i<list.length;i++){
    			if(list[i].eventType == 'subscribe'){
    				dataInit(list[i]);
    			}
    			if(list[i].eventType == 'disReply'){
    				dataInit(list[i]);
    			}
    		}
	    }
	});
}

function sdk_btn(eventType){
	//<button id="subscribe_btn" type="button" onclick="sdk_btn('subscribe');">被添加自动回复</button>
	//<button id="dis_reply_btn" type="button" onclick="sdk_btn('disReply');">消息自动回复</button>
	//<button id="key_reply_btn" type="button" onclick="sdk_btn('ruleKey');">关键字自动回复</button>
	$('#eventType').attr("value",eventType);
	$('#ruleDiv').hide();
	if(eventType == 'subscribe' || eventType == 'disReply'){
		$('#no_key_msg_reply_div').show();
		$('#yes_key_msg_reply_div').hide();
		msg_btn_change(eventType);
	}else if(eventType == 'ruleKey'){
		$('#no_key_msg_reply_div').hide();
		$('#yes_key_msg_reply_div').show();
		$('#replyTable').show();
		replyManagerList();
	}
}

function divSwitch(media){
	//$('#mediaId').attr("value",media.mediaId);
	$('#yl_img *').remove();
	var imgUrl = '<input id="imageUrl" type="hidden" value="'+ media.url +'" />';
	var img = '<img style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;" src="'+ media.url +'" />';
	$('#yl_img').append(imgUrl);
	$('#yl_img').append(img);
}

function dataInit(msgData){
	if(msgData.msgType == '0'){//文本
		$('#textmsg').attr("value",msgData.content);
		$('#msgType').attr("value",'text');
		
		$('#yl_img *').remove();
		$("#imageli").removeClass();
		$('#yl_news *').remove();
		$("#newsli").removeClass();
		
		$("#textli").attr("class",'active');
		$("#imagediv").attr("class",'tab-pane fade');
		$("#newsdiv").attr("class",'tab-pane fade');
		$("#textdiv").attr("class",'tab-pane fade in active');
	}
	if(msgData.msgType == '1'){//图片
		$("#textli").removeClass();
		$('#msgType').attr("value",'image');
		
		$('#textmsg').attr("value",'');
		$('#yl_news *').remove();
		$("#newsli").removeClass();
		
		$("#imageli").attr("class",'active');
		$("#newsdiv").attr("class",'tab-pane fade');
		$("#textdiv").attr("class",'tab-pane fade');
		$("#imagediv").attr("class",'tab-pane fade in active');
		$('#mediaId').attr("value",msgData.mediaId);
		$('#yl_img *').remove(); //'+ msgData.picUrl +'
		var img = '<img style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;" src="'+msgData.picLocalUrl+'" />';
		$('#yl_img').append(img);
	}
	if(msgData.msgType == '5'){//图文
		$("#textli").removeClass();
		$('#msgType').attr("value",'news');

		$('#textmsg').attr("value",'');
		$('#yl_img *').remove();
		$("#imageli").removeClass();
		
		
		$("#newsli").attr("class",'active');
		$("#imagediv").attr("class",'tab-pane fade');
		$("#textdiv").attr("class",'tab-pane fade');
		$("#newsdiv").attr("class",'tab-pane fade in active');
		$('#mediaId').attr("value",msgData.mediaId);
		$('#yl_news *').remove(); //'+ msgData.picUrl +'
		var news = 
			'<div style="float: left;width:20%;text-align: center;">' + 
       		'<div style="text-align: center;">' +
       		'<span>'+msgData.title+'</span><br>' +
       		'<a href="'+ msgData.url +'"><img src="'+msgData.picLocalUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
			'<br>' +
       		'</div>' +
       		'</div>';
		$('#yl_news').append(news);
	}
}

function replyManagerList() {
    var replyManagerTable = $('#replyManagerList');
    replyManagerTable.dataTable().fnClearTable(false);
    replyManagerTable.dataTable().fnDestroy();
    replyManagerTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/msgReply/getMsgReply.shtml",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "bServerSide": true,
        "iDisplayLength": 10,
        "bSort": false,
        "oLanguage": {//语言设置
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoFiltered": "(总共 _MAX_ 条数据)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sZeroRecords": "抱歉， 没有找到",
            "sInfoEmpty": "没有数据",
            "sLoadingRecords": "加载中...",
            "sProcessing": "处理中..."
        },
        "aoColumns": [
            {
                "mDataProp": 'ruleName',
                "sTitle": "规则名称",
                "sWidth": '25%'
            }, {
                "mDataProp": 'msgKey',
                "sTitle": "关键字",
                "sWidth": '25%'
            }, {
                "mDataProp": 'msgType',
                "sTitle": "回复类型",
                "sWidth": '25%',
                "mRender": function (data, type, full) {
                    var msgType = data;//(0文本,1图片,2语音,3视频,4音频,5图文)
                    var msgTypeView = "";
                    if (msgType == 0) {
                    	msgTypeView = "文字";
                    } else if (msgType == 1) {
                    	msgTypeView = "图片";
                    } else if (msgType == 2) {
                    	msgTypeView = "语音";
                    } else if (msgType == 3) {
                    	msgTypeView = "视频";
                    } else if (msgType == 4) {
                    	msgTypeView = "音频";
                    } else if (msgType == 5) {
                    	msgTypeView = "图文";
                    }
                    return msgTypeView;
                }
            },{
                "mDataProp": null,
                "sTitle": "操作",
                "sWidth": '25%',
                "mRender": function (data, type, full) {
                	return '<button type="button" onclick = "delRule(' + data.sid + ');" class="btn btn-link">删除</button>';
                }
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}
