/**
 * Created by kongqf on 16-12-12.
 */
$(function () {
    couponTPLList();
    selectCouponType();
    $("#addFun").click("click", function () {
        addFun();
    });
    $("#search").click("click", function () {
        couponTPLList();
    });
    $("#btnApproval").click("click", function () {
        approvalOperation();
    });
    $("#btnApprovalList").click("click", function () {
        couponAprovalList();
    });
});

function approvalOperation() {
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
    layer.confirm('确认要审批通过？', function (index) {
        var url = rootPath + '/coupongrp/editCouponInfo.shtml';
        var s = CommnUtil.ajax(url, {
            sid: ids[0], couponStatus: 2
        }, "json");
        if (s == "success") {
            layer.msg('审批成功');
            couponAprovalList();
        } else {
            layer.msg('审批失败');
        }
    });
}

function selectCouponType() {
    var url = rootPath + '/dic/queryDicList.shtml';
    var data = CommnUtil.ajax(url, {"key": "coupon_type"}, "json");
    if (data != null) {
        var h = "<option value=''>----券类型----</option>";
        for (var i = 0; i < data.list.length; i++) {
            h += "<option value='" + data.list[i].code + "'>" + data.list[i].name + "</option>";
        }
        $("#selCouponType").html(h);
    }
    else {
        layer.msg("获券类型错误，请联系管理员！");
    }
}

function addFun() {
    pageii = layer.open({
        title: "新增",
        type: 2,
        area: ["700px", "90%"],
        content: rootPath + '/coupongrp/addUI.shtml'
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
        "sAjaxSource": rootPath + "/coupongrp/findCouponInfoByPage.shtml",
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
                    var sid = full["sid"];
                    return '<div class="checker"><span><input name="sid" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + sid + '"></span></div>';
                }
            },
            {
                "mDataProp": 'sid',
                "sTitle": "券ID",
                "sWidth": '5%'
            }, {
                "mDataProp": 'cardType',
                "sTitle": "类型",
                "sWidth": '10%'
            }, {
                "mDataProp": 'title',
                "sTitle": "券模板",
                "sWidth": '10%'
            }, {
                "mDataProp": 'title',
                "sTitle": "名称"
            }, {
                "mDataProp": 'couponStatus',
                "sTitle": "状态",
                "sWidth": '20%'
            }, {
                "mDataProp": 'createUserName',
                "sTitle": "创建人",
                "sWidth": '10%'
            }, {
                "mDataProp": 'updateTime',
                "sTitle": "最后编辑时间",
                "sWidth": '15%',
                "mRender": function (data, type, full) {
                    var time = data;
                    if (time) {
                        return new Date(time).format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return "";
                    }
                }
            }, {
                "mDataProp": 'approvalUsername',
                "sTitle": "审批人",
                "sWidth": '10%'
            }/*,
             {
             "mDataProp": '',
             "sTitle": "操作",
             "sWidth": '10%',
             "mRender": function (data, type, full) {
             var key = buttonMap.keySet();
             var button = "";
             for (var i in key) {
             button += buttonMap.get(key[i]);
             }
             return button;
             }
             }*/],
        "aoColumnDefs": [{
            sDefaultContent: '',
            aTargets: ['_all']
        }],
        "fnServerData": function (sSource, aoData, fnCallback) {
            if (!aoData) {
                aoData = [];
            }
            aoData.push({
                name: "operationType",
                value: $("#operationType").val()
            });
            aoData.push({name: "cardType", value: $("#selCouponType").val()});
            aoData.push({name: "title", value: $("#couponName").val()});
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


function couponAprovalList() {
    var userTable = $('#approvalList');
    userTable.dataTable().fnClearTable(false);
    userTable.dataTable().fnDestroy();
    userTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/coupongrp/findCouponInfoByPage.shtml",
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
                "sTitle": '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#approvalList .checkboxes"></span></div>',
                "mDataProp": null,
                "sWidth": "3%",
                "bSortable": false,
                "mRender": function (data, type, full) {
                    var sid = full["sid"];
                    if (full["couponStatus"] == '拟稿') {
                        return '<div class="checker"><span><input name="sid" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + sid + '"></span></div>';
                    }
                    else {
                        return '<div class="checker"><span><input name="sid" disabled onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + sid + '"></span></div>';
                    }
                }
            },
            {
                "mDataProp": 'sid',
                "sTitle": "券ID",
                "sWidth": '5%'
            }, {
                "mDataProp": 'cardType',
                "sTitle": "类型",
                "sWidth": '10%'
            }, {
                "mDataProp": 'title',
                "sTitle": "券模板",
                "sWidth": '10%'
            }, {
                "mDataProp": 'title',
                "sTitle": "名称",
                "sWidth": '10%'
            }, {
                "mDataProp": 'couponStatus',
                "sTitle": "状态",
                "sWidth": '10%'
            }, {
                "mDataProp": 'createUserName',
                "sTitle": "创建人",
                "sWidth": '10%'
            }, {
                "mDataProp": 'updateTime',
                "sTitle": "最后编辑时间",
                "sWidth": '15%',
                "mRender": function (data, type, full) {
                    var time = data;
                    if (time) {
                        return new Date(time).format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return "";
                    }
                }
            }, {
                "mDataProp": 'approvalUsername',
                "sTitle": "审批人",
                "sWidth": '10%'
            }/*,
             {
             "mDataProp": '',
             "sTitle": "操作",
             "sWidth": '10%',
             "mRender": function (data, type, full) {
             var key = buttonMap.keySet();
             var button = "";
             for (var i in key) {
             button += buttonMap.get(key[i]);
             }
             return button;
             }
             }*/],
        "aoColumnDefs": [{
            sDefaultContent: '',
            aTargets: ['_all']
        }],
        "fnServerData": function (sSource, aoData, fnCallback) {
            if (!aoData) {
                aoData = [];
            }
            aoData.push({
                name: "operationType",
                value: $("#operationType3").val()
            });
            aoData.push({name: "cardType", value: $("#selCouponType").val()});
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