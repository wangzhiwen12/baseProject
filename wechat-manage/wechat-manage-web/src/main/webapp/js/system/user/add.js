jQuery.validator.addMethod("checkaccgg", function(value, element) {
	return this.optional(element)
		|| ((value.length <= 30) && (value.length >= 3));
}, "请选择集团");
jQuery.validator.addMethod("checkatt", function(value, element) {
	return this.optional(element)
		|| ((value.length <= 30) && (value.length >= 3));
}, "请选择门店");
//单独验证某一个input  class="checkpass"
jQuery.validator.addMethod("checkacc", function(value, element) {
	return this.optional(element)
		|| ((value.length <= 30) && (value.length >= 3));
}, "账号由3至30位字符组合构成");

var resultstore="";

$(function() {
	//获取集团列表
	var val="";
	$.ajax({
		type: "post",
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		url: rootPath + '/group/getGroupList.json',
		dataType: "json",
		data: "",
		success: function (response) {
			if (response.code == "0") {
				var soption = $("#groupType");//列表框id
				//转成Json对象
				var result = eval(response.obj);
				val=result;
				//循环遍历 下拉框绑定
				$(result).each(function (key) {
				//第一种方法

				var opt = $("<option></option>").text(result[key].organization_name).val(result[key].organization_code);
					soption.append(opt);
				});
			} else {
				layer.msg('加载集团失败');
			}
		},
		error: function () {
			layer.msg('error');
		}
	});




	//下拉出发事件
	$("#groupType").bind("change",function(){
		if($(this).val()==0){
			return;
		}
		else{
			var code=$(this).val();
			for(var item in val) {
				if(val[item].organizationCode==code){
					$("#organization_name").val(val[item].organizationName);
				}
			}
			getStore($(this).val());
		}
	});
	//下拉出发事件
	$("#storeType").bind("change",function(){
		if($(this).val()==0){
			return;
		}
		else{
			var code=$(this).val();
			for(var item in resultstore) {
				if(resultstore[item].storeCode==code){
					$("#business_name").val(resultstore[item].businessName);
				}
			}
			$.ajax({
				type: "post",
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
				url: rootPath + '/role/selRoleuser.shtml',
				dataType: "json",
				data: "storeCode="+code,
				success: function (response) {
					var result = eval(response);
					var gr = $("#groupsForSelect");//列表框id
					$("#groupsForSelect").empty();
					$("#selectGroups").empty();
					$(result).each(function (key) {
						var op = $("<option></option>").text(result[key].name).val(result[key].id);
						gr.append(op);
					});
				}

			});
		/*	$("#selRole").load(rootPath+"/system/user/roleSelect.jsp");*/

			/*document.getElementById("#selRole").innerHTML="<iframe src='roleSelect.jsp' width='' height=''></iframe>"*/






		}
	});






	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('添加成功!是否关闭窗口?', function(index) {
							window.parent.userList();
							parent.layer.close(parent.pageii);
							return false;
						});
						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		rules : {
			"userFormMap.accountName" : {
				required : true,
				remote : { // 异步验证是否存在
					type : "POST",
					url : 'isExist.shtml',
					data : {
						name : function() {
							return $("#groupType").val(),$("#storeType").val(),$("#accountName").val();
						}
					}
				}
			}
		},
		messages : {
			"userFormMap.accountName" : {
				required : "请输入账号",
				remote : "该账号已经存在"
			}
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			// element.css('border','3px solid #FFCCCC');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
});


function getStore(groupsid) {
	$.ajax({
		type: "post",
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		url: rootPath + '/storeManager/getStoreInfoBygroup.json',
		dataType: "json",
		data: "groupId="+groupsid,
		success: function (response) {
			if (response.code == "0") {
				var soption = $("#storeType");//列表框id
				//方法1：添加默认节点
				// soption.append("<option value='-1'>--请选择--</option>");
				//转成Json对象
				resultstore = eval(response.obj);
				$("#storeType").empty();
				$("#business_name").val(resultstore[0].businessName);
				//循环遍历 下拉框绑定
				$(resultstore).each(function (key) {
					//第一种方法
					var opt = $("<option></option>").text(resultstore[key].businessName).val(resultstore[key].storeCode);
					soption.append(opt);
				});
			} else {
				layer.msg('加载门店失败');
			}
		},
		error: function () {
			layer.msg('error');
		}
	});

}



