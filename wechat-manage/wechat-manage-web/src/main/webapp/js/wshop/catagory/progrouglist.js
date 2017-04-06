/**
 * Created by kongqf on 2017/4/1.
 */
$(function () {
    groupList();
    $("#addFun").click("click", function () {
        addFun();
    });
    $("#delFun").click("click", function () {
        delFun();
    });
});

function addFun() {
    pageii = layer.open({
        title: "添加分组",
        type: 2,
        area: ["700px", "90%"],
        content: rootPath + '/category/addUI.shtml'
    });
}

function delFun() {
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
    layer.confirm('是否删除？', function (index) {
        var url = rootPath + '/category/deleteProGroup.shtml';
        var s = CommnUtil.ajax(url, {
            "sid": ids[0]
        }, "json");
        if (s == "success") {

            layer.msg('删除成功');
            groupList();
        } else {
            layer.msg('删除失败');
        }
    });
}

function groupList() {
    var userTable = $('#userList');
    userTable.dataTable().fnClearTable(false);
    userTable.dataTable().fnDestroy();
    userTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/category/findProGroupList.shtml",
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
                    var sid = full["id"];
                    return '<div class="checker"><span><input name="sid" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + sid + '"></span></div>';
                }
            },
            {
                "mDataProp": 'name',
                "sTitle": "分组名称",
                "sWidth": '10%'
            },
            {
                "sTitle": "分组图片",
                "sWidth": '10%',
                "mRender": function (data, type, full) {
                    var picUrl = full["picUrl"];
                    if (picUrl != null && picUrl != '') {
                        return '<img width="64px" height="64px" src="' + picUrl + '">';
                    } else {
                        return '<img width="64px" height="64px">';
                    }
                }
            },
            {
                "sTitle": "状态",
                "sWidth": '10%',
                "mRender": function (data, type, full) {
                    var state = full["state"];
                    if (state != null && state != '' && state == "1") {
                        return '可用';
                    } else {
                        return '禁用';
                    }
                }
            },
            {
                "sTitle": "分组类型",
                "sWidth": '10%',
                "mRender": function (data, type, full) {
                    var type = full["type"];
                    if (type != null && type != '') {
                        if (type == "1") {
                            return '手工分组';
                        } else if (type == "2") {
                            return '按商品类目分组';
                        }else if (type == "3") {
                            return '按发布时间分组';
                        }else if (type == "4") {
                            return '按商品属性分组';
                        }
                    }
                }
            }, {
                "mDataProp": 'updateDate',
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
            }
        ],
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