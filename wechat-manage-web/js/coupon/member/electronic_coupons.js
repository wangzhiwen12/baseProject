

function couponsList(){
		var userTable = $('#couponsList');
		userTable.dataTable().fnClearTable(false);
		userTable.dataTable().fnDestroy();
		userTable
				.dataTable({
					"bAutoWidth" : false,
					"bDestory" : true,
					"bFilter" : false,
					"bPaginate" : true,
					"sAjaxSource" : encodeURI(rootPath
							+ "/member/getAllMemberInfo.shtml?param=" ),
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
									if (nickname) {
					                    return '<a href="javascript:void(0)" onclick="detailUI(' + openid + ');">' + nickname + '</a>';
									} else {
										return "";
									}
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
									if (mobile) {
					                    return '<a href="javascript:void(0)" onclick="detailUI(' + openid + ');">' + mobile + '</a>';
									} else {
										return "";
									}
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
								"sWidth" : '10%',
								"bSortable" : false,
								"mRender" : function(data, type, full) {
									var id = full["openid"];
									return '<div><span><input type="button" id ="'+id+'" value="添加至黑名单" onclick="openModal(this.id)" class ="btn btn-info marR10" /></span></div>';
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