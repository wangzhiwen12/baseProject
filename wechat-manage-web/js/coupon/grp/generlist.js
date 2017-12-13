/**
 * Created by kongqf on 16-12-12.
 */
var id = null;
$(function () {
    couponTPLList();
    $("#btnGener").click("click", function () {
        addFun();
    });
    $("#search").click("click", function () {
        couponTPLList();
    });
    $("#btnCouponDetail").click("click", function () {
        detail();
    });
});


function addFun() {
    pageii = layer.open({
        title: "领券优惠券",
        type: 2,
        area: ["600px", "80%"],
        content: rootPath + '/coupongrp/addgener.shtml'
    });
}

function detail() {
    var ids = [];
    $("input.checkboxes[name='sid']:checkbox").each(function () {
        if ($(this).attr("checked")) {
            ids.push($(this).val());
        }
    });
    if (ids.length > 1 || ids == "") {
        layer.msg("只能选中一个");
        return;
    }

    pageii = layer.open({
        title: "优惠券详情",
        type: 2,
        area: ["600px", "80%"],
        content: rootPath + '/couponMember/coupondetail.shtml?cardId=' + ids[0]
    });
}


function couponTPLList() {
    var userTable = $('#userList');
    userTable.dataTable().fnClearTable(false);
    userTable.dataTable().fnDestroy();
    userTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/couponMember/findCouponMember.shtml",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "bServerSide": true,
        "iDisplayLength": 10,
        "bSort": false,
        "oLanguage": {//语言设置
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoFiltered": "(总共 _MAX_ 条数据)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sZeroRecords": "抱歉， 没有找到",
            "sInfoEmpty": "没有数据",
            "sLoadingRecords": "加载中...",
            "sProcessing": "处理中..."
        },
        "aoColumns": [
            {
                "sTitle": '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#userList .checkboxes"></span></div>',
                "mDataProp": null,
                "sWidth": "3%",
                "bSortable": false,
                "mRender": function (data, type, full) {
                    var sid = full["cardSid"];
                    return '<div class="checker"><span><input name="sid" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + sid + '"></span></div>';
                }
            },
            {
                "mDataProp": 'cardSid',
                "sTitle": "券ID",
                "sWidth": '10%'
            }, {
                "mDataProp": 'cardType',
                "sTitle": "类型",
                "sWidth": '20%'
            }, {
                "mDataProp": 'title',
                "sTitle": "券名称",
                "sWidth": '20%'
            }, {
                "mDataProp": 'subTitle',
                "sTitle": "副标题"
            }, {
                "mDataProp": '',
                "sTitle": "状态",
                "mRender": function (data, type, full) {
                    var useState = full["useState"];
                    if (useState == '0') {
                        return "未使用";
                    } else if (useState == '1') {
                        return "已使用";
                    }
                }
            }],
        "aoColumnDefs": [{
            sDefaultContent: '',
            aTargets: ['_all']
        }],
        "fnServerData": function (sSource, aoData, fnCallback) {
            if (!aoData) {
                aoData = [];
            }
            aoData.push({
                name: "openId",
                value: $("#openId").val()
            });
            aoData.push({name: "storeCode", value: $("#storeCode").val()});
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": sSource,
                "data": aoData,
                "success": function (json, flag) {
                    if (flag && json) {
                        fnCallback(json);
                    } else {
                        userTable.dataTable().fnProcessingIndicator(false);
                        bootbox.alert("查询失败，请稍后再试！");
                    }
                },
                "timeout": 15000,
                "error": function (xhr, textStatus, error) {
                    console.log(xhr, textStatus, error);
                    if (textStatus === 'timeout') {
                        alert('The server took too long to send the data.');
                    } else if (textStatus === 'Not Found') {
                        alert('The server not found.');
                    } else {
                        alert('An error occurred on the server. Please try again in a minute.');
                    }
                    userTable.dataTable().fnProcessingIndicator(false);
                }
            });
        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
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
    $(set).each(function () {
            if (checked) {
                var isdisabled = $(this).attr("disabled");
                if (isdisabled != 'disabled') {
                    $(this).parents("span").addClass("checked");

                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                }
            }
            else {
                $(this).parents("span").removeClass("checked");
                $(this).attr("checked", false);
                $(this).parents('tr').removeClass("active");
            }
        }
    )
    ;
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