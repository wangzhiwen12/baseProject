var id;
var pageii = null;
var buttonMap = new Map();
$(function() {
	userList("", "");
	$('#myFansModal').on('show.bs.modal', function () {
		
		})
		
	$('#tagList tbody tr').live(
			'click',
			function() {
				document.getElementById("accountName").value="";
				
				var tagName = encodeURI($(this).text());
				console.log(tagName);
				userList("", tagName);
				var fansList = document.getElementById("fansList");
				var body = document.getElementById("body");
				body.value = UrlDecode(tagName).substr(0,
						UrlDecode(tagName).indexOf("（", 0))

				if (document.getElementById("newdiv") != null) {
					body.removeChild(document.getElementById("newdiv"));
				}
				var newdiv = document.createElement('div');
				newdiv.id = "newdiv";
				newdiv.innerHTML = body.value + "&nbsp;&nbsp;";
				body.insertBefore(newdiv, body.firstChild);
				var renameButten = document.createElement('a')
				renameButten.id = "rena";
				renameButten.class = "btn btn-primary btn-lg";
				renameButten.innerHTML = "重命名&nbsp;&nbsp;";
				renameButten.dataset.toggle = "modal";
				renameButten.dataset.target = "#myTagModal";

				var removeButten = document.createElement('a');
				removeButten.id = tagName;
				removeButten.href = "javascript:deleteTag()";
				removeButten.innerHTML = "删除&nbsp;&nbsp;";
				removeButten.value = body.value;
				newdiv.appendChild(renameButten);
				newdiv.appendChild(removeButten);
				document.getElementById("TagInput").value = body.value;
			});

	$("#search").click("click", function() {
		var user = encodeURI($("#param").val());
		userList(user);
	});

	$("#reloadTop").click("click", function() {
		var black = document.getElementById("black");
		var fans = document.getElementById("fans");
		black.style.display = "none";
		fans.style.display = "block";
		userList("", "");
	});
	$("#blackTop").click("click", function() {
		var black = document.getElementById("black");
		var fans = document.getElementById("fans");
		black.style.display = "block";
		fans.style.display = "none";
		blackList();
	});

});
function openModal(obj){
	alert(obj);
}
function adjustment(openId){
	  pageii = layer.open({
	        title: "详情",
	        type: 2,
	        area: ["50%", "80%"],
	        content: rootPath + '/member/adjustmentUI.shtml?openid=' + openId
	    });
}

function detailUI(param) {
    pageii = layer.open({
        title: "详情",
        type: 2,
        area: ["95%", "95%"],
        content: rootPath + '/member/detailUI.shtml?openid=' + param
    });
}

function userList(param) {
	var userTable = $('#memberList');
	var param =encodeURI(document.getElementById("param").value);
	userTable.dataTable().fnClearTable(false);
	userTable.dataTable().fnDestroy();
	userTable
			.dataTable({
				"bAutoWidth" : false,
				"bDestory" : true,
				"bFilter" : false,
				"bPaginate" : true,
				"sAjaxSource" : rootPath
						+ "/member/getAllMemberInfo.shtml?param=" + encodeURI(param),
				"bProcessing" : true,
				"searching" : false, // 去掉搜索框
				"bLengthChange" : false,// 是否允许自定义每页显示条数.
				"bServerSide" : true,
				"iDisplayLength" : 10,
				"bSort" : false,
				"oLanguage" : {// 语言设置
					"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
					"sInfoFiltered" : "(总共 _MAX_ 条数据)",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : "前一页",
						"sNext" : "后一页",
						"sLast" : "尾页"
					},
					"sZeroRecords" : "抱歉， 没有找到",
					"sInfoEmpty" : "没有数据",
					"sLoadingRecords" : "加载中...",
					"sProcessing" : "处理中..."
				},
				"aoColumns" : [
						{
							"mDataProp" : 'member_code',
							"sTitle" : "会员卡号",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var id = data;
								if (id) {
									return id;
								} else {
									return "";
								}
							}
						},
						{
							"mDataProp" : 'nickname',
							"sTitle" : "姓名",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var nickname = full["nickname"];
								var openid = full["openid"];
				                return '<a href="javascript:void(0)" id="'+openid+'" onclick="detailUI(this.id);">' + nickname + '</a>';
							}
						},
						{
							"mDataProp" : 'sex',
							"sTitle" : "性别",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var id = data;
								if (id==1) {
									return "先生";
								} else {
									return "小姐";
								}
							}
						},
					
						{
							"mDataProp" : 'mobile',
							"sTitle" : "电话",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var mobile = full["mobile"];
								var openid = full["openid"];
				                return '<a href="javascript:void(0)" id="'+openid+'" onclick="detailUI(this.id);">' + mobile + '</a>';
							}
						},
						{
							"mDataProp" : '',
							"sTitle" : "会员等级",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var id = data;
								if (id) {
									return id;
								} else {
									return "";
								}
							}
						},
						{
							"mDataProp" : 'storeCode',
							"sTitle" : "所属门店",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var id = data;
								if (id) {
									return id;
								} else {
									return "";
								}
							}
						},
						{
							"mDataProp" : '',
							"sTitle" : "专属导购",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var id = data;
								if (id) {
									return id;
								} else {
									return "";
								}
							}
						},
						{
							"mDataProp" : 'openid',
							"sTitle" : "操作",
							"sWidth" : '15%',
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								var id = full["openid"];
								return '<div style="display: inline;"><input type="button" id ="'+id+'" value="拉黑" onclick="openModal(this.id)" class ="btn btn-info marR10" /> <input type="button" id ="'+id+'" value="调整" onclick="adjustment(this.id)" class ="btn btn-default" /></div>';
							}
						}
				],
				"aoColumnDefs" : [ {
					sDefaultContent : '',
					aTargets : [ '_all' ]
				} ],
				"fnServerData" : function(sSource, aoData, fnCallback) {
					if (!aoData) {
						aoData = [];
					}
					$
							.ajax({
								"dataType" : 'json',
								"type" : "post",
								"url" : sSource,
								"data" : aoData,
								"success" : function(json, flag) {
									if (flag && json) {
										fnCallback(json);
									} else {
										userTable.dataTable()
												.fnProcessingIndicator(false);
										bootbox.alert("查询失败，请稍后再试！");
									}
								},
								"timeout" : 15000,
								"error" : function(xhr, textStatus, error) {
									console.log(xhr, textStatus, error);
									if (textStatus === 'timeout') {
										alert('The server took too long to send the data.');
									} else if (textStatus === 'Not Found') {
										alert('The server not found.');
									} else {
										alert('An error occurred on the server. Please try again in a minute.');
									}
									userTable.dataTable()
											.fnProcessingIndicator(false);
								}
							});
				},
				"fnRowCallback" : function(nRow, aData, iDisplayIndex,
						iDisplayIndexFull) {
					// console.log(nRow, aData, iDisplayIndex,
					// iDisplayIndexFull);
				}
			});
}

function checkAll(obj) {
	var checked = $(obj).is(":checked");
	if (checked) {
		$(obj).parents("span").addClass("checked");
	} else {
		$(obj).parents("span").removeClass("checked");
	}
	var set = $(obj).attr("data-set");
	$(set).each(function() {
		if (checked) {
			$(this).parents("span").addClass("checked");
			$(this).attr("checked", true);
			$(this).parents('tr').addClass("active");
		} else {
			$(this).parents("span").removeClass("checked");
			$(this).attr("checked", false);
			$(this).parents('tr').removeClass("active");
		}
	});
	$.uniform.update(set);
}

function checkThis(obj) {
	var checked = $(obj).is(":checked");
	if (checked) {
		$(obj).parents("span").addClass("checked");
		$(obj).attr("checked", true);
		$(obj).parents('tr').addClass("active");
	} else {
		$(obj).parents("span").removeClass("checked");
		$(obj).attr("checked", false);
		$(obj).parents('tr').removeClass("active");
	}
}

function UrlDecode(zipStr) {
	var uzipStr = "";
	for (var i = 0; i < zipStr.length; i++) {
		var chr = zipStr.charAt(i);
		if (chr == "+") {
			uzipStr += " ";
		} else if (chr == "%") {
			var asc = zipStr.substring(i + 1, i + 3);
			if (parseInt("0x" + asc) > 0x7f) {
				uzipStr += decodeURI("%" + asc.toString()
						+ zipStr.substring(i + 3, i + 9).toString());
				i += 8;
			} else {
				uzipStr += AsciiToString(parseInt("0x" + asc));
				i += 2;
			}
		} else {
			uzipStr += chr;
		}
	}

	return uzipStr;
}

function StringToAscii(str) {
	return str.charCodeAt(0).toString(16);
}
function AsciiToString(asccode) {
	return String.fromCharCode(asccode);
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