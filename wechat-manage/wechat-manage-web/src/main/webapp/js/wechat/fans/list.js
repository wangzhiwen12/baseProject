var id;
var pageii = null;
var buttonMap = new Map();
$(function() {
	tagList();
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
		var user = encodeURI($("#accountName").val());
		userList(user, "");
	});
	
	$("#callback_test").click("click", function() {
		paging_callback();
	});
	$("#addFun").click("click", function() {
		addFun();
	});
	
	$("#editFun").click("click", function() {
		editFun();
	});

	$("#delAccount").click("click", function() {
		delAccount();
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
	$("#delFun").click("click", function() {
		shielding();
	});
	$("#removeBlack").click("click", function() {
		removeBlack();
	});

});
function openModal(obj){
document.getElementById("openid").value=obj;
$("#myFansModal").modal("show");


//隐藏
//$('#loginModal').modal('hide');
}

function reName() {
	var oldTagName = document.getElementById("body");
	var newTagName = document.getElementById("TagInput");
	if (oldTagName == newTagName) {
		layer.msg("不能修改为相同的标签名");
	} else {
		$.ajax({
			type : "get",
			url : rootPath + "/fans/renameTag.shtml?newTagName="
					+ encodeURI(encodeURI(newTagName.value)) + "&oldTagName=" + encodeURI(encodeURI(oldTagName.value)),
			dataType : 'json',

			success : function(result) {
				if (result == "success") {
					layer.msg("修改成功");
				} else {
					layer.msg("修改失败,错误信息：" + result);
				}
				if (document.getElementById("newdiv") != null) {
					document.getElementById("body").removeChild(document.getElementById("newdiv"));
				}
				$('#myTagModal').modal('hide');
				document.getElementById("TagInput").value="";
				userList("", "");
				tagList();
			}
		});
	}
}

function reMarks() {
	var newRemarks = document.getElementById("FansInput").value;
	var openid =document.getElementById("openid").value;
	// blNikename
	if (newRemarks.indexOf("（") > 0 || newRemarks.indexOf("）") > 0) {
		layer.msg("不允许设置带括号的备注名");
	} else {
		$.ajax({
			type : "get",
			url : encodeURI(rootPath + "/fans/modifyRemark.shtml?newRemarks="
					+ newRemarks+"&openid="+openid ),
			dataType : 'json',

			success : function(result) {
				if (result == "success") {
					layer.msg("修改成功");
				} else {
					layer.msg("修改失败,错误信息：" + result);
				}
				document.getElementById("FansInput").value="";
				document.getElementById("openid").value="";
				$('#myFansModal').modal('hide');
				userList("", "");
				tagList();
			}
		});
	}
}
function modalClose() {
	$('#myTagModal').modal('hide');
	$('#myFansModal').modal('hide');
	document.getElementById("FansInput").value="";
	document.getElementById("TagInput").value="";
}
function deleteTag() {
	if (window.confirm('你确定要删除标签吗？')) {
		$.ajax({
			type : "get",
			url : rootPath + "/fans/deleteTag.shtml?tagName="
					+ encodeURI(encodeURI(document.getElementById("body").value)),
			dataType : 'json',
			success : function(result) {
				if(result=="success"){
					layer.msg("删除成功");
				}else{
					layer.msg("删除失败，错误代码："+result);
				}
				document.getElementById("body").removeChild(
						document.getElementById("newdiv"));
				userList("", "");
				tagList();
			}
		});

	} else {
		return false;
	}
}
function editFun() {
	pageii = layer.open({
		title : "新建标签",
		type : 2,
		area : [ "40%", "30%" ],
		content : rootPath + '/fans/createTag.shtml'
	});
}
function addFun() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function() {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length < 0 || ids == "") {
		layer.msg("至少选中一个");
		return;
	}
	pageii = layer.open({
		title : "打标签",
		type : 2,
		area : [ "40%", "30%" ],
		content : rootPath + '/fans/addTag.shtml?id=' + ids
	});
}

function shielding() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function() {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length < 0 || ids == "") {
		layer.msg("至少选中一个");
		return;
	}
	var odata = JSON.stringify({
		"openids" : ids,
	});
	$.ajax({
		type : "post",
		url : rootPath + "/fans/shielding.shtml",
		dataType : 'json',
		data : odata,
		success : function(result, flag) {
			if (result.equals("success")) {
				layer.msg("拉黑成功");
			}
		}
	});
}

function removeBlack() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function() {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length < 0 || ids == "") {
		layer.msg("至少选中一个");
		return;
	}
	var odata = JSON.stringify({
		"openids" : ids,
	});
	$.ajax({
		type : "post",
		url : rootPath + "/fans/removeBlack.shtml",
		dataType : 'json',
		data : odata,
		success : function(result, flag) {
			layer.msg("移出没名单成功");
		}
	});
}

function userList(userName, tagName) {
	var userTable = $('#fansList');
	if (document.getElementById("newdiv") != null) {
		document.getElementById("body").removeChild(document.getElementById("newdiv"));
	}
	userTable.dataTable().fnClearTable(false);
	userTable.dataTable().fnDestroy();
	userTable
			.dataTable({
				"bAutoWidth" : false,
				"bDestory" : true,
				"bFilter" : false,
				"bPaginate" : true,
				"sAjaxSource" : encodeURI(rootPath
						+ "/fans/getAllFans.shtml?tagName=" + tagName
						+ "&userName=" + userName),
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
							"sTitle" : '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#fansList .checkboxes"></span></div>',
							"mDataProp" : 'openid',
							"sWidth" : "3%",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								var id = full["openid"];
								return '<div class="checker"><span><input name="id" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="'
										+ id + '"></span></div>';
							}
						},
						{
							"mDataProp" : 'nickname',
							"sTitle" : "用户",
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
							"mDataProp" : 'tagid_list',
							"sTitle" : "标签",
							"sWidth" : '10%'
						},
						{
							"mDataProp" : 'openid',
							"sTitle" : "修改备注",
							"sWidth" : '10%',
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								var id = full["openid"];
								return '<div><span><input type="button" id ="'+id+'" value="点击修改备注" onclick="openModal(this.id)" class ="btn btn-info marR10" /></span></div>';
							}
						}
				/*
				 * , { "mDataProp": '', "sTitle": "操作", "sWidth": '10%',
				 * "mRender": function (data, type, full) { var key =
				 * buttonMap.keySet(); var button = ""; for(var i in key){
				 * button += buttonMap.get(key[i]); } return button; } }
				 */],
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

function blackList() {
	var userTable = $('#blackList');
	userTable.dataTable().fnClearTable(false);
	userTable.dataTable().fnDestroy();
	userTable
			.dataTable({
				"bAutoWidth" : false,
				"bDestory" : true,
				"bFilter" : false,
				"bPaginate" : true,
				"sAjaxSource" : rootPath + "/fans/getBlackList.shtml",
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
							"sTitle" : '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#fansList .checkboxes"></span></div>',
							"mDataProp" : 'openid',
							"sWidth" : "3%",
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								var id = full["openid"];
								return '<div class="checker"><span><input name="id" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="'
										+ id + '"></span></div>';
							}
						},
						{
							"mDataProp" : 'nickname',
							"sTitle" : "用户",
							"sWidth" : '10%',
							"mRender" : function(data, type, full) {
								var id = data;
								if (id) {
									return id
											+ '<input data-provide="typeahead"  id="blNikename" style="display:none;" value='
											+ id + ' >';
								} else {
									return "";
								}
							}
						},
						{
							"mDataProp" : 'openid',
							"sTitle" : "修改备注",
							"sWidth" : '10%',
							"bSortable" : false,
							"mRender" : function(data, type, full) {
								var id = full["openid"];
								return '<div><span><input type="button" id ="'+id+'" value="点击修改备注" onclick="openModal(this.id)" class ="btn btn-info marR10" /></span></div>';
							}
						} ],
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
								"type" : "get",
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

function tagList() {
	var userTable = $('#tagList');
	userTable.dataTable().fnClearTable(false);
	userTable.dataTable().fnDestroy();
	userTable
			.dataTable({
				"bAutoWidth" : false,
				"bDestory" : true,
				"bFilter" : false,
				"bPaginate" : true,
				"sAjaxSource" : rootPath + "/fans/getAllTags.shtml",
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
				"aoColumns" : [ {
					"mDataProp" : 'name',
					"sTitle" : "标签",
					"sWidth" : '10%',
					"mRender" : function(data, type, full) {
						var id = data;
						if (id) {
							return id;
						} else {
							return "";
						}
					}
				} ],
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
								"type" : "get",
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