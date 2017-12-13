$(function(){
	massManagerList();
});

$('#btn_1').click(function (){
	var content = $('#textmsg').val();
	var mediaId = $('#mediaId').val();
	var msgType = $('#msgType').val();
	var groups = $('#wrap input[name="user_groups"]:checked ').val();
	alert(content + "," + mediaId + "," + msgType + "," + groups);
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    data:{
	    	"content": content,
	    	'mediaId': mediaId,
	    	'groups': groups,
	    	'msgType': msgType
	    },
	    url: '${pageContext.request.contextPath}/msgMass/msgMassSend.shtml',
	    success: function(re) {
    		if(re == 'success'){
    			alert("发送成功");
    		}
	    }
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
$('#material_get').click(function(){
	pageii = layer.open({
        title: "图片素材",
        type: 2,
        area: ["90%", "85%"],
        content: rootPath + '/materialLocal/imageList.shtml'
    });
});
$('#textInput').click(function (){
	$('#msgType').attr("value",'text');
	$('#yl_news *').remove();
	$('#yl_img *').remove();
});
$('#imageInput').click(function (){
	$('#msgType').attr("value",'image');
	$('#yl_news *').remove();
	$('#textmsg').attr("value",'');
});
$('#newsInput').click(function (){
	$('#msgType').attr("value",'news');
	$('#textmsg').attr("value",'');
	$('#yl_img *').remove();
});

$('#send_btn').click(function(){
	$('#yes_msg_mass_div').hide();
	$('#no_msg_mass_div').show();
});

function massManagerList() {
    var massManagerTable = $('#massManagerList');
    massManagerTable.dataTable().fnClearTable(false);
    massManagerTable.dataTable().fnDestroy();
    massManagerTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/msgMass/getMsgMass.shtml",
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
                "mDataProp": 'msgId',
                "sTitle": "消息编号",
                "sWidth": '25%'
            }, {
                "mDataProp": 'createTime',
                "sTitle": "发送时间",
                "sWidth": '25%'
            }, {
                "mDataProp": 'msgType',
                "sTitle": "消息类型",
                "sWidth": '25%',
                "mRender": function (data, type, full) {
                    var msgType = data;//(0文本,1图片,2语音,3视频,4音频,5图文)
                    var msgTypeView = "";
                    if (msgType == 'text') {
                    	msgTypeView = "文字";
                    } else if (msgType == 'image') {
                    	msgTypeView = "图片";
                    } else if (msgType == 'news') {
                    	msgTypeView = "图文";
                    }
                    return msgTypeView;
                }
            },{
                "mDataProp": null,
                "sTitle": "操作",
                "sWidth": '25%',
                "mRender": function (data, type, full) {
                	return '<button type="button" onclick = "getMass(' + data.sid + ');" class="btn btn-link">详情</button>';
                }
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}
function getMass(sid){
	$("#mass_sid").attr("value",sid);
	pageii = layer.open({
        title: "群发详情",
        type: 2,
        area: ["90%", "85%"],
        content: rootPath + '/msgMass/massInfo.shtml'
    });
}