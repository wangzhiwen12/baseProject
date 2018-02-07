//单独验证某一个input  class="checkpass"
jQuery.validator.addMethod("checkacc", function(value, element) {
	return this.optional(element)
			|| ((value.length <= 30) && (value.length >= 3));
}, "账号由3至30位字符组合构成");
$(function() {

	$("#addgroup").click("click", function () {
		addFun();
	});

});

function addFun() {
	var organizationName = $("#organizationName").val();
	var organizationCode = $("#organizationCode").val();
	if (!((organizationName.length <= 30) && (organizationName.length >= 3))) {
		layer.msg('集团名称由3至30位字符组合构成');
		return;
	}
	if (!((organizationCode.length <= 30) && (organizationCode.length >= 3))) {
		layer.msg('集团编码由3至30位字符组合构成');
		return;
	}
	var chkObjs = null;
	var obj = document.getElementsByName("radioid");
	for (var i = 0; i < obj.length; i++) { //遍历Radio
		if (obj[i].checked) {
			chkObjs = obj[i].value;
		}
	}


	$.ajax({
		type: "post",
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		url: rootPath + '/group/save.json',
		dataType: "json",
		data: "organizationName=" + organizationName + "&organizationCode=" + organizationCode + "&chkObjs=" + chkObjs,
		success: function (response) {
			if (response.code == "0") {
				layer.confirm('添加成功!是否关闭窗口?', function(index) {
					window.parent.userList();
					parent.layer.close(parent.pageii);
					return false;
				});
			} else {
				layer.msg('添加失败');
			}
		},
		error: function () {
			layer.msg('error');
		}
	});
}

