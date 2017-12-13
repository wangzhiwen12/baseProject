$(function () {
    productList();

    $('#supplier_select').click(function(){
        selectSupplierByShop();
    });

    $('#supplier_select').change(function(){
        selectShoppeByShopAndSupplier();
    });

    $("#btngroup").click("click", function (e) {
        popFun();

    });
});

function popFun() {
    var ids = [];
    $("input.checkboxes[name='sid']:checkbox").each(function () {
        if ($(this).attr("checked")) {
            ids.push($(this).val());
        }
    });
    if (ids.length ==0 || ids == "") {
        layer.msg("请选择商品");
        return;
    }
    pageii = layer.open({
        title: "商品分组",
        type: 2,
        area: ["700px", "90%"],
        content: getRootPath() + '/category/progrouppoplist.shtml'
    });
}
function getSelectPro() {
    var ids = [];
    $("input.checkboxes[name='sid']:checkbox").each(function () {
        if ($(this).attr("checked")) {
            ids.push($(this).val());
        }
    });
    return ids;
}

function reset(){
    $("#shoppeProName_input").val("");
    $("#saleStatus_select").val("");
    $('input').val("");
    productList();
}
function getRootPath(){  
	var curWwwPath = window.document.location.href;  
	var pathName = window.document.location.pathname;  
	var pos = curWwwPath.indexOf(pathName);  
    var localhostPath = curWwwPath.substring(0, pos);  
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
    return(localhostPath + projectName);  
}  
function productList() {
    var productTable = $('#productList');
    productTable.dataTable().fnClearTable(false);
    productTable.dataTable().fnDestroy();
    productTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": getRootPath() + "/shoppePro/selectShoppeProductPageByParaFromSearch.shtml",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "bServerSide": true,
        "iDisplayLength": 10,
        "bSort": false,
        "columnDefs" : [ {
            "defaultContent" : "",
            "targets" : "_all"
        } ],
        "oLanguage": {// 语言设置
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
                "mDataProp": null,
                "sTitle": "选择",
                "mRender" : function (data, type, full) {
                    return '' +
                    '<input type="checkbox" name="sid" class="checkboxes" value="'+data.productCode+'" >';
                }
            },{
                "mDataProp": 'productName',
                "sTitle": "商品名称"
            },{
                "mDataProp": 'productCode',
                "sTitle": "商品货号"
            },{
                "mDataProp": 'managerCategoryName',
                "sTitle": "所属分类"
            },{
                "mDataProp": 'cdate',
                "sTitle": "创建时间"
            }
        ],
        "fnServerData": function (sSource, aoData, fnCallback) {
            if (!aoData) {
                aoData = [];
            }
            aoData.push({
                name: "minShoppeProSid",
                value: $("#shoppeProSid_input").val()
            });
            aoData.push({
                name: "maxShoppeProSid",
                value: $("#maxShoppeProSid_input").val()
            });
            aoData.push({
                name: "minProductDetailSid",
                value: $("#skuCode_input").val()
            });
            aoData.push({
                name: "maxProductDetailSid",
                value: $("#maxSkuCode_input").val()
            });
            aoData.push({
                name: "shoppeProName",
                value: $("#shoppeProName_input").val()
            });
            aoData.push({
                name: "saleStatus",
                value: $("#saleStatus_select option:selected").val()
            });
            aoData.push({
                name: "supplySid",
                value: $("#supplier_select option:selected").val()
            });
            aoData.push({
                name: "shoppeSid",
                value: $("#shoppe_select option:selected").val()
            });
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": sSource,
                "data": aoData,
                "success": function (json, flag) {
                    if (flag && json) {
                        fnCallback(json);
                    } else {
                        productTable.dataTable().fnProcessingIndicator(false);
                        bootbox.alert("查询失败，请稍后再试！");
                    }
                },
                "timeout": 15000
            });
        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}