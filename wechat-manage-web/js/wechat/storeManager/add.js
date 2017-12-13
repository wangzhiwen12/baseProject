//单独验证某一个input  class="checkpass"
jQuery.validator.addMethod("checkRole", function (value, element) {
    return this.optional(element) || ((value.length <= 10) && (value.length >= 3));
}, "角色名由3至10位字符组合构成");
$(function () {
    $("form").validate({
        submitHandler: function (form) {//必须写在验证前面，否则无法ajax提交
            ly.ajaxSubmit(form, {//验证新增是否成功
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data == "success") {
                        layer.confirm('添加成功!是否关闭窗口?', function (index) {
                            window.parent.storeManagerList();
                            parent.layer.close(parent.pageii);
                            return false;
                        });
                        $("#form")[0].reset();
                    } else {
                        layer.msg('添加失败！');
                    }
                }
            });
        },
        errorPlacement: function (error, element) {//自定义提示错误位置
            $(".l_err").css('display', 'block');
            //element.css('border','3px solid #FFCCCC');
            $(".l_err").html(error.html());
        },
        success: function (label) {//验证通过后
            $(".l_err").css('display', 'none');
        }
    });
    
    var url = rootPath + '/storeManager/findGroupInfoList2.shtml';
    var data = CommnUtil.ajax(url, null, "json");
    if (data != null) {
        var h = "<option value='0'></option>";
        for (var i = 0; i < data.list.length; i++) {
            h += "<option value='" + data.list[i].organizationCode + "'>" + data.list[i].organizationName + "</option>";
        }
        $("#groupId").html(h);
    } else {
        layer.msg("获取菜单信息错误，请联系管理员！");
    }
});

