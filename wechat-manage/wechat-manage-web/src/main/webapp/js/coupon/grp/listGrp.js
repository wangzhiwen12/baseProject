/**
 * Created by kongqf on 16-12-15.
 */
/**
 * Created by kongqf on 16-12-8.
 */
$(function () {
    selectCouponType();
    couponTPLList();
    $("#btuConfirm").click("click", function () {
        var ruleTpl = $("input[type='radio']:checked").val();
        if (!!ruleTpl) {
            window.parent.loadCouponName(ruleTpl);
            parent.layer.close(parent.pageii);
            return false;
        }
        else {
            layer.msg("请选择一种券模板!");
            return;
        }
    });
});

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
                "sTitle": '',
                "mDataProp": null,
                "sWidth": "3%",
                "bSortable": false,
                "mRender": function (data, type, full) {
                    var sid = full["sid"];
                    var name = sid + ",," + full["title"];
                    return '<input id="rulesid' + sid + '" type="radio"  name="rulesid" value="' + name + '"/>';
                }
            },
            {
                "mDataProp": 'sid',
                "sTitle": "券ID",
                "sWidth": '10%'
            }, {
                "mDataProp": 'cardType',
                "sTitle": "类型",
                "sWidth": '20%'
            }, {
                "mDataProp": 'title',
                "sTitle": "券模板",
                "sWidth": '20%'
            }, {
                "mDataProp": 'title',
                "sTitle": "券名称"
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
