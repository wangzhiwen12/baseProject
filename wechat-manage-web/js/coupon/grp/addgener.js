/**
 * Created by kongqf on 16-12-12.
 */
$(function () {
    $("#form").validate({
        submitHandler: function (form) {//必须写在验证前面，否则无法ajax提交
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/couponMember/addCouponMember.shtml',
                dataType: "json",
                data: $("#form").serialize(),
                success: function (response) {
                    if (response == "success") {
                        layer.confirm('添加成功!是否关闭窗口?', function (index) {
                            window.parent.couponTPLList();
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
            "CouponName": {
                required: true
            },
            "openId": {
                required: true
            }
        },
        messages: {
            "CouponName": {
                required: "请选择需要领券的电子券"
            },
            "openId": {
                required: "请输入OpenID"
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
    $("#btnSearchTPL").click("click", function () {
        searchGrp();
    });
});

function loadCouponName(couponObj) {
    var arr = couponObj.split(',,');
    if (!!arr) {
        $("#couponId").val(arr[0]);
        $("#CouponName").val(arr[1]);
    }
}

function searchGrp() {
    pageii = layer.open({
        title: "券模板",
        type: 2,
        area: ["600px", "90%"],
        content: rootPath + '/coupongrp/listGrp.shtml'
    });
}
