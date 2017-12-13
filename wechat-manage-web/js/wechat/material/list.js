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
		url : rootPath + "/materialLocal/del.shtml",
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
        content: rootPath + '/materialLocal/imageInsert.shtml'
    });
}
function newsAdd(){
	window.open(rootPath + '/materialLocal/newsInsert.shtml');
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
        "sAjaxSource": rootPath + "/materialLocal/getImageList.shtml",
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
                	return '<img src="' + data.material_1.localUrl + '" style="height: 140px;width: 140px;">' + 
                	'<br>'+
					'<label>'+data.material_1.imageName+'</label>'+
					'<br>'+
					'<button type="button" class="btn btn-danger" onclick="del(\''+data.material_1.mediaId+'\');">删除</button>';
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic2",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_2 != null){
                		return '<img src="' + data.material_2.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.material_2.imageName+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.material_2.material_id+'\');">删除</button>';                		
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic3",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_3 != null){
                		return '<img src="' + data.material_3.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.material_3.imageName+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.material_3.material_id+'\');">删除</button>';                		
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic4",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_4 != null){
                		return '<img src="' + data.material_4.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.material_4.imageName+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.material_4.material_id+'\');">删除</button>';                		
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "pic5",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_5 != null){
                		return '<img src="' + data.material_5.localUrl + '" style="height: 140px;width: 140px;">' + 
                		'<br>'+
                		'<label>'+data.material_5.imageName+'</label>'+
                		'<br>'+
                		'<button type="button" class="btn btn-danger" onclick="del(\''+data.material_5.material_id+'\');">删除</button>';                		
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
        "sAjaxSource": rootPath + "/materialLocal/getNewsList.shtml",
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
                	if(data.material_1 != null){
                		return '<div>' + 
                		'<span>'+data.material_1.updateTime+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.material_1.title+'</span><br>' +
                		'<a href="'+data.material_1.picUrl+'"><img src="'+data.material_1.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news2",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_2 != null){
                		return '<div>' + 
                		'<span>'+data.material_2.updateTime+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.material_2.title+'</span><br>' +
                		'<a href="'+data.material_2.picUrl+'"><img src="'+data.material_2.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news3",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_3 != null){
                		return '<div>' + 
                		'<span>'+data.material_3.updateTime+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.material_3.title+'</span><br>' +
                		'<a href="'+data.material_3.picUrl+'"><img src="'+data.material_3.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news4",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_4 != null){
                		return '<div>' + 
                		'<span>'+data.material_4.updateTime+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.material_4.title+'</span><br>' +
                		'<a href="'+data.material_4.picUrl+'"><img src="'+data.material_4.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
                		'</div>' +
                		'</div>';
                	}
                }
            }, {
            	"mDataProp": null,
                "sTitle": "news5",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	if(data.material_5 != null){
                		return '<div>' + 
                		'<span>'+data.material_5.updateTime+'</span>' +
                		'<hr>' +
                		'<div style="text-align: center;">' +
                		'<span>'+data.material_5.title+'</span><br>' +
                		'<a href="'+data.material_5.picUrl+'"><img src="'+data.material_5.localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
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