//单独验证某一个input  class="checkpass"
jQuery.validator.addMethod("checkacc", function(value, element) {
	return this.optional(element)
			|| ((value.length <= 30) && (value.length >= 3));
}, "账号由3至30位字符组合构成");
$(function() {

	$("#editgroup").click("click", function () {
		editFun();
	});

});

function editFun() {
	var organizationName=$("#organizationName").val();
	var sid=$("#sid").val();
	if(!((organizationName.length <= 30) && (organizationName.length >= 3))){
		layer.msg('集团名称由3至30位字符组合构成');
	   return;
    }

	var chkObjs=null;
	var obj=document.getElementsByName("radioid")
	for (var i=0;i<obj.length;i++){ //遍历Radio
		if(obj[i].checked){
			chkObjs=obj[i].value;
		}
	}



	$.ajax({
		type: "post",
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		url: rootPath+ '/group/edit.json',
		dataType: "json",
		data: "organizationName="+organizationName+"&sid="+sid+"&chkObjs="+chkObjs,
		success: function (response) {
			if(response.code=="0"){
				layer.confirm('修改成功!是否关闭窗口?', function(index) {
					window.parent.userList();
					parent.layer.close(parent.pageii);
					return false;
				});
			}else {
				layer.msg('修改失败！');
			}
		},
		error: function (response) {
			layer.msg('error！');
		}
	});




}

