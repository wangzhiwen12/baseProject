$(function () {
    $("#form").validate({
        submitHandler: function (form) {//必须写在验证前面，否则无法ajax提交
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/category/addgroup.shtml',
                dataType: "json",
                data: $("#form").serialize(),
                success: function (response) {
                    if (response == "success") {
                        layer.confirm('添加成功!是否关闭窗口?', function (index) {
                            window.parent.groupList();
                            parent.layer.close(parent.pageii);
                            return false;
                        });
                        $("#form")[0].reset();
                    } else {
                        layer.alert(response, 3);
                    }
                },
                error: function () {
                }
            });
        },
        rules: {
            "groupname": {
                required: true
            },
            "groupPic": {
                required: true
            },
            "Type": {
                required: true
            }
        },
        messages: {
            "groupname": {
                required: "请输入分组名称"
            },
            "groupPic": {
                required: "请选择封面图片"
            },
            "Type": {
                required: "请选择分组类型"
            }
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

    $('#btnSelectPicUrl').click(function () {
        pageii = layer.open({
            title: "图片素材",
            type: 2,
            area: ["100%", "100%"],
            content: rootPath + '/materialLocal/imageList.shtml'
        });
    });

});

function imageSwitch(media) {
    $("#groupPic").attr("src", media.localUrl);
    $("#groupPicUrl").val(media.localUrl);
}