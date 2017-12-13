var pageii = null;
$(function () {
    couponTPLList();
   
    $("#btnCouponList").click("click", function () {
    	couponTPLList();
    });
    $("#btnApprovalList").click("click", function () {
        couponAprovalList();
    });
    $("#add_wpage").click("click", function () {
    	 var tb = $("#loadhtml");
    	    tb.html(CommnUtil.loadingImg());
    	    tb.load(rootPath + '/wechatShopPage/add_wpageUI.shtml');
    });
    $("#back").click("click", function () {
   	 var tb = $("#loadhtml");
   	    tb.html(CommnUtil.loadingImg());
   	    tb.load(rootPath + '/wechatShopPage/list.shtml');
   });
});

function wPageEdit(id){
	 var tb = $("#loadhtml");
	    tb.html(CommnUtil.loadingImg());
	    tb.load(rootPath + '/wechatShopPage/edit_wpageUI.shtml?id='+id);
	    couponTPLList();
    	couponAprovalList();
}

function couponTPLList() {
    var userTable = $('#userList');
    userTable.dataTable().fnClearTable(false);
    userTable.dataTable().fnDestroy();
    userTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/wechatShopPage/findByPage.shtml",
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
                "mDataProp": 'wpageTitle',
                "sTitle": "标题",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                    var title = data;
                    var sid = full["sid"]
                    if (title) {
                        return '<a class="" onclick="previewN(\''+sid+'\');" href="javascript:void(0);">'+title+'</a>';
                    } else {
                        return "";
                    }
                }
            }, {
                "mDataProp": 'createTime',
                "sTitle": "创建时间",
                "sWidth": '19%',
                "mRender": function (data, type, full) {
                    var time = data.time;
                    if (time) {
                        return new Date(time).format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return "";
                    }
                }
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "商品数",
                "sWidth": '7%'
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "浏览UV/PV",
                "sWidth": '7%'
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "到店UV/PV",
                "sWidth": '7%'
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "序号",
                "sWidth": '10%',
                "mRender": function (data, type, full) {
                	var sid = full["sid"];//主键
                	var createUser = full["createUser"];//用户名
                	return '<span id="a'+sid+'">'+data+'</span>&nbsp;<a class="" href="javascript:void(0);" onclick="js_update(\''+sid+'\','+createUser+');">修改</a>';
                }
            }, {
               // "mDataProp": 'sid',
                "sTitle": "操作",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	var sid = full["sid"];//主键
                	var isHome = full["isHome"];//是否是主页
                	var pageLink = full["pageLink"];//链接
                    var strs1= new Array(); 
                    strs1=pageLink.split("/");
                    var strs2= new Array(); 
                    strs2=strs1[strs1.length-1].split(".");
                    var arr = strs2[0];
                	var createUser = full["createUser"];//用户名
                	var status = full["status"];//草稿
                	if(isHome == 0){ //非主页
                		var srt = '<a class="" href="javascript:void(0);" onclick="js_copyFile(\''+sid+'\','+createUser+',\''+arr+'\','+status+');">复制</a>'; 
                		srt += '<span class="">-</span>';
                		srt += '<a class="" data-order-state="0" data-app-id="0" onclick="wPageEdit(\''+sid+'\')" href="javascript:void(0);">编辑</a>';
                		srt += '<span>-</span>';
                		srt += '<a class="" href="javascript:void(0);" onclick="js_del(\''+sid+'\','+createUser+',\''+arr+'\','+status+');">删除</a>';
                		srt += '<span>-</span>';
                		srt += '<a href="javascript:void(0)" id="'+pageLink+'" onclick="js_method(this.id);">  链接 </a>';
                		srt += '<span>-</span>';
                		srt += '<a class="" href="javascript:void(0);" onclick="js_updateHomePage(\''+sid+'\','+createUser+','+status+');" data-order-state="0" data-app-id="0">设为主页</a>';
                		return srt;
                	}else{
                		var srt = '<a class="" href="javascript:void(0);" onclick="js_copyFile(\''+sid+'\','+createUser+',\''+arr+'\','+status+');">复制</a>'; 
                		srt += '<span class="">-</span>';
                		srt += '<a class="" data-order-state="0" data-app-id="0" onclick="wPageEdit(\''+sid+'\')" href="javascript:void(0);">编辑</a>';
                		srt += '<span>-</span>';
                		srt += '<a href="javascript:void(0)" id="'+pageLink+'" onclick="js_method(this.id);">  链接 </a>';
                		srt += '<span>-</span>';
                		srt += '<span style="color:#BABABA">店铺主页</span>';
            			
            		return srt;
                	}
                }
                
            }],
        "aoColumnDefs": [{
            sDefaultContent: '',
            aTargets: ['_all']
        }],
        "fnServerData": function (sSource, aoData, fnCallback) {
            if (!aoData) {
                aoData = [];
            }
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": sSource,
                "data": aoData,
                "success": function (json, flag) {
                    if (flag && json) {
                        fnCallback(json);
                    } else {
                        userTable.dataTable().fnProcessingIndicator(false);
                        bootbox.alert("查询失败，请稍后再试！");
                    }
                },
                "timeout": 15000,
                "error": function (xhr, textStatus, error) {
                    console.log(xhr, textStatus, error);
                    if (textStatus === 'timeout') {
                        alert('The server took too long to send the data.');
                    } else if (textStatus === 'Not Found') {
                        alert('The server not found.');
                    } else {
                        alert('An error occurred on the server. Please try again in a minute.');
                    }
                    userTable.dataTable().fnProcessingIndicator(false);
                }
            });
        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}
//草稿页面
function couponAprovalList() {
    var userTable = $('#approvalList');
    userTable.dataTable().fnClearTable(false);
    userTable.dataTable().fnDestroy();
    userTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/wechatShopPage/findByPage2.shtml",
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
                "mDataProp": 'wpageTitle',
                "sTitle": "标题",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                    var title = data;
                    var sid = full["sid"]
                    if (title) {
                        return '<a class=""  onclick="previewN(\''+sid+'\');" href="javascript:void(0);">'+title+'</a>';
                    } else {
                        return "";
                    }
                }
            }, {
                "mDataProp": 'createTime',
                "sTitle": "创建时间",
                "sWidth": '19%',
                "mRender": function (data, type, full) {
                    var time = data.time;
                    if (time) {
                        return new Date(time).format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return "";
                    }
                }
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "商品数",
                "sWidth": '7%'
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "浏览UV/PV",
                "sWidth": '7%'
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "到店UV/PV",
                "sWidth": '7%'
            }, {
                "mDataProp": 'seqNo',
                "sTitle": "序号",
                "sWidth": '10%',
                "mRender": function (data, type, full) {
                	var sid = full["sid"];//主键
                	var createUser = full["createUser"];//用户名
                	return '<span id="a'+sid+'">'+data+'</span>&nbsp;<a class="" href="javascript:void(0);" onclick="js_update(\''+sid+'\','+createUser+');">修改</a>';
                }
            }, {
               // "mDataProp": 'sid',
                "sTitle": "操作",
                "sWidth": '20%',
                "mRender": function (data, type, full) {
                	var sid = full["sid"];//主键
                	var isHome = full["isHome"];//是否是主页
                	var pageLink = full["pageLink"];//链接
                	 var strs1= new Array(); 
                     strs1=pageLink.split("/");
                     var strs2= new Array(); 
                     strs2=strs1[strs1.length-1].split(".");
                     var arr = strs2[0];
                	var createUser = full["createUser"];//用户名
                	var status = full["status"];//草稿
                		var srt = '<a class="" href="javascript:void(0);" onclick="js_copyFile(\''+sid+'\','+createUser+',\''+arr+'\','+status+');">复制</a>'; 
                			srt += '<span class="">-</span>';
                		    srt += '<a class="" data-order-state="0" data-app-id="0" onclick="wPageEdit(\''+sid+'\')" href="javascript:void(0);">编辑</a>';
                			srt += '<span>-</span>';
                			srt += '<a class="" href="javascript:void(0);" onclick="js_del(\''+sid+'\','+createUser+',\''+arr+'\','+status+');">删除</a>';
                			srt += '<span>-</span>';
                		    srt += '<a href="javascript:void(0)" id="'+pageLink+'" onclick="js_method(this.id);">  链接 </a>';
                		    srt += '<span>-</span>';
                			srt += '<a class="" href="javascript:void(0);" onclick="js_updateHomePage(\''+sid+'\','+createUser+','+status+');" data-order-state="0" data-app-id="0">设为主页</a>';
                		return srt;
                }
                
            }],
        "aoColumnDefs": [{
            sDefaultContent: '',
            aTargets: ['_all']
        }],
        "fnServerData": function (sSource, aoData, fnCallback) {
            if (!aoData) {
                aoData = [];
            }
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": sSource,
                "data": aoData,
                "success": function (json, flag) {
                    if (flag && json) {
                        fnCallback(json);
                    } else {
                        userTable.dataTable().fnProcessingIndicator(false);
                        bootbox.alert("查询失败，请稍后再试！");
                    }
                },
                "timeout": 15000,
                "error": function (xhr, textStatus, error) {
                    console.log(xhr, textStatus, error);
                    if (textStatus === 'timeout') {
                        alert('The server took too long to send the data.');
                    } else if (textStatus === 'Not Found') {
                        alert('The server not found.');
                    } else {
                        alert('An error occurred on the server. Please try again in a minute.');
                    }
                    userTable.dataTable().fnProcessingIndicator(false);
                }
            });
        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}
//查看链接
function js_method(data){
	        
	pageii = layer.open({
		  type: 1 //Page层类型
		  ,area: ['450px', '150px']
		  ,title: '微页面链接'
		  ,shade: 0.6 //遮罩透明度
		  ,maxmin: false //允许全屏最小化
		  ,anim: 1 //0-6的动画形式，-1不开启
		  ,content: '<div align="center" style="padding:35px;">'+data+'</div>'
		});
}
//删除页面
function js_del(sid,user,pageLink,status){
	pageii = layer.confirm('确定删除？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				type : "post",
				url : rootPath + '/wechatShopPage/delPage.shtml',
				dataType : "json",
				data:{"sid":sid,"createUser":user,"pageLink":pageLink},
				success : function(data) {
					layer.msg('删除成功', {
						icon: 1,
						time:800
					});
			//	$("#loadhtml").load(rootPath+"/wechatShopPage/list.shtml");
					if(status == 1){
						$("#tab_15_1").tab('show');
						couponTPLList();
					}else{
						$("#tab_15_3").tab('show');
						couponAprovalList();
					}
				},
				error : function() {
					alert("失败");
				}
			});
		}, function(){
		 // layer.msg('删除失败', {
		  //  time: 2000, //20s后自动关闭
		  //  btn: ['明白了', '知道了']
		 // });
		});
}
function previewN(id){
	window.open(rootPath+"/wechatShopPage/preview.shtml?id="+id);
}
//更改序号
function js_update(sid,user){
	var bsid = $("#a"+sid).html();
	layer.config({
        extend: 'extend/layer.ext.js'
    });
	pageii = layer.prompt({
    	    value:bsid,
            title: '输入要修改的序号(数字)，并确认',
            formType: 0
        }, function (value, index, elem) {
            if (value) {
            	$.ajax({
    				type : "post",
    				url : rootPath + '/wechatShopPage/updatePageSeqNo.shtml',
    				dataType : "json",
    				data:{"sid":sid,"createUser":user,"seqNo":value},
    				success : function(data) {
    					layer.close();
    					layer.msg('修改成功', {
    						icon: 1,
    						time:800
    					});
    					$("#a"+sid).html(value);
    			//	$("#loadhtml").load(rootPath+"/wechatShopPage/list.shtml");
    					if(status == 1){
    						$("#tab_15_1").tab('show');
    						couponTPLList();
    					}else{
    						$("#tab_15_3").tab('show');
    						couponAprovalList();
    					}
    				},
    				error : function() {
    					alert("失败");
    				}
    			});
              //  layer.msg('修改成功',{time:800});
            };
        });
}
//设为主页
function js_updateHomePage(sid,user,status){
	pageii = layer.confirm('确定设为主页？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				type : "post",
				url : rootPath + '/wechatShopPage/updateHomePage.shtml',
				dataType : "json",
				data:{"sid":sid,"createUser":user},
				success : function(data) {
					layer.msg('设置成功', {
						icon: 1,
						time:800
					});
				//$("#loadhtml").load(rootPath+"/wechatShopPage/list.shtml");
					if(status == 1){
						$("#tab_15_1").tab('show');
						couponTPLList();
					}else{
						$("#tab_15_3").tab('show');
						couponAprovalList();
					}
				},
				error : function() {
					alert("失败");
				}
			});
		}, function(){
		 // layer.msg('删除失败', {
		  //  time: 2000, //20s后自动关闭
		  //  btn: ['明白了', '知道了']
		 // });
		});
}
//复制网页
function js_copyFile(sid,user,pageLink,status){
	
	pageii = layer.confirm('确定复制此页面？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				type : "post",
				url : rootPath + '/wechatShopPage/copyFile.shtml',
				dataType : "json",
				data:{"sid":sid,"createUser":user,"pageLink":pageLink},
				success : function(data) {
					layer.msg('复制成功', {
						icon: 1,
						time:800
					});
			//	$("#loadhtml").load(rootPath+"/wechatShopPage/list.shtml");
					if(status == 1){
						$("#tab_15_1").tab('show');
						couponTPLList();
					}else{
						$("#tab_15_3").tab('show');
						couponAprovalList();
					}
				},
				error : function() {
					alert("失败");
				}
			});
		}, function(){
		 // layer.msg('删除失败', {
		  //  time: 2000, //20s后自动关闭
		  //  btn: ['明白了', '知道了']
		 // });
		});
}
function keyUp(e) {
	var currKey = 0, e = e || event;
	currKey = e.keyCode || e.which || e.charCode;
	 switch (currKey) {
     case 27:
    	 parent.layer.close(parent.pageii);
         break;
     default:
         break;
	 }
}
document.onkeyup = keyUp;
