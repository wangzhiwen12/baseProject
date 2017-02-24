$(function(){
	imageList();
});

function divSwitch(material){
	imageList();
}

$('#newsMaterial').click(function(){
	newsList();
})
$('#imageMaterial').click(function(){
	imageList();
})

function del(mediaId){
	$.ajax({
		type : "POST",
		dataType: "json",
		contentType : 'application/json',
		url : rootPath + "/material/del.shtml",
		data : {
			'mediaId' : mediaId
		},
		success: function () {
			alert("删除成功");
			divSwitch();
        }
	});
}

var pageii = null;
function imageAdd(){
	pageii = layer.open({
        title: "新增图片素材",
        type: 2,
        area: ["50%", "50%"],
        content: rootPath + '/material/imageAdd.shtml'
    });
}
function newsAdd(){
	window.open(rootPath + '/material/newsAdd.shtml');
}

function imageList() {
    var imageTable = $('#imageList');
    imageTable.dataTable().fnClearTable(false);
    imageTable.dataTable().fnDestroy();
    imageTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/material/getImageMaterial.shtml",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "bServerSide": true,
        "iDisplayLength": 10,
        "bSort": false,
		"columnDefs" : [ {
			"defaultContent" : "",
			"targets" : "_all"
		} ],
        "oLanguage": {// 语言设置
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
                "mDataProp": null,
                "sTitle": "pic1",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	return '<img src="' + data.media_1.localUrl + '" style="height: 140px;width: 140px;">' + 
                	'<br>'+
					'<label>'+data.media_1.name+'</label>'+
					'<br>'+
					'<button type="button" class="btn btn-danger" onclick="del(\''+data.media_1.media_id+'\');">删除</button>';
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic2",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.media_2 != null){
                		return '<img src="' + data.media_2.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.media_2.name+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.media_2.media_id+'\');">删除</button>';                		
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic3",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.media_3 != null){
                		return '<img src="' + data.media_3.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.media_3.name+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.media_3.media_id+'\');">删除</button>';                		
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic4",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.media_4 != null){
                		return '<img src="' + data.media_4.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.media_4.name+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.media_4.media_id+'\');">删除</button>';                		
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic5",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.media_5 != null){
                		return '<img src="' + data.media_5.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.media_5.name+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.media_5.media_id+'\');">删除</button>';                		
                	}
                }
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}


function newsList() {
    var newsTable = $('#newsList');
    newsTable.dataTable().fnClearTable(false);
    newsTable.dataTable().fnDestroy();
    newsTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/material/getNewsMaterial.shtml",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "bServerSide": true,
        "iDisplayLength": 10,
        "bSort": false,
		        "columnDefs" : [ {
			"defaultContent" : "",
			"targets" : "_all"
		} ],
        "oLanguage": {// 语言设置
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
                "mDataProp": null,
                "sTitle": "news1",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.article_1 != null){
                		return '<div>' + 
                		'<span>'+data.article_1.update_time+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.article_1.title+'</span><br>' +
                		'<a href="'+data.article_1.url+'"><img src="'+data.article_1.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'<span>'+data.article_1.content+'</span>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news2",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.article_2 != null){
                		return '<div>' + 
                		'<span>'+data.article_2.update_time+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.article_2.title+'</span><br>' +
                		'<a href="'+data.article_2.url+'"><img src="'+data.article_2.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'<span>'+data.article_2.content+'</span>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news3",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.article_3 != null){
                		return '<div>' + 
                		'<span>'+data.article_3.update_time+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.article_3.title+'</span><br>' +
                		'<a href="'+data.article_3.url+'"><img src="'+data.article_3.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'<span>'+data.article_3.content+'</span>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news4",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.article_4 != null){
                		return '<div>' + 
                		'<span>'+data.article_4.update_time+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.article_4.title+'</span><br>' +
                		'<a href="'+data.article_4.url+'"><img src="'+data.article_4.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'<span>'+data.article_4.content+'</span>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news5",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.article_5 != null){
                		return '<div>' + 
                		'<span>'+data.article_5.update_time+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.article_5.title+'</span><br>' +
                		'<a href="'+data.article_5.url+'"><img src="'+data.article_5.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'<span>'+data.article_5.content+'</span>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}