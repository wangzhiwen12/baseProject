/**
 * Created by kongqf on 16-12-12.
 */
$(function () {
    $("#form").validate({
        submitHandler: function (form) {//必须写在验证前面，否则无法ajax提交
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/coupongrp/addCouponInfo.shtml',
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
            "txtTplName2": {
                required: true
            },
            "txtCouponName": {
                required: true
            },
            "txtApprovalUserName2": {
                required: true
            }
        },
        messages: {
            "txtTplName2": {
                required: "请选择券模板"
            },
            "txtCouponName": {
                required: "请输入券名称"
            },
            "txtApprovalUserName2": {
                required: "请输选择审批人"
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
        searchTPL();
    });
    $("#btnUser").click("click", function () {
        searchUser();
    });

    $('#qBeginTime').datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        showMeridian: true,
        pickerPosition: "bottom-left",
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,
        minView: 2,
        startDate: new Date()
    }).on('changeDate', function (e) {
        var startTime = e.date;
        $('#qEndTime').datetimepicker('setStartDate', startTime);
    });
    $('#qEndTime').datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        showMeridian: true,
        pickerPosition: "bottom-left",
        language: 'zh-CN',
        startView: 2,
        minView: 2,
        startDate: new Date()
    }).on('changeDate', function (e) {
        var endTime = e.date;
        $('#qBeginTime').datetimepicker('setEndDate', endTime);
    });
});

function couponTplName(couponObj) {
    var arr = couponObj.split(',,');
    if (!!arr) {
        $("#txtTplName").val(arr[0]);
        $("#txtTplName2").val(arr[1]);
    }
}

function selUser(user) {
    var arr = user.split(',,');
    if (!!arr) {
        $("#txtApprovalUserName").val(arr[0]);
        $("#txtApprovalUserName2").val(arr[1]);
    }
}


function searchTPL() {
    pageii = layer.open({
        title: "券模板",
        type: 2,
        area: ["600px", "90%"],
        content: rootPath + '/coupongrp/listTpl.shtml'
    });
}

function searchUser() {
    pageii = layer.open({
        title: "选择审核人",
        type: 2,
        area: ["600px", "90%"],
        content: rootPath + '/coupongrp/userList.shtml'
    });
}