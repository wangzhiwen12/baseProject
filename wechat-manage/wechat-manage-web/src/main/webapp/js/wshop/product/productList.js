$(function () {
    productList();
});

function productList() {
    var productTable = $('#productList');
    productTable.dataTable().fnClearTable(false);
    productTable.dataTable().fnDestroy();
    productTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": rootPath + "/shoppePro/selectShoppeProductPageByParaFromSearch.shtml",
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
                "mDataProp": 'productCode',
                "sTitle": "专柜商品编码"
            },{
                "mDataProp": 'productName',
                "sTitle": "商品名称"
            },{
                "mDataProp": 'skuCode',
                "sTitle": "SKU编码"
            },{
                "mDataProp": 'storeName',
                "sTitle": "门店"
            },{
                "mDataProp": 'counterName',
                "sTitle": "专柜"
            },{
                "mDataProp": 'suppliers',
                "sTitle": "供应商",
                "mRender" : function (data, type, full) {
                    return data[0].supplierName;
                }
            },{
                "mDataProp": 'storeBrandName',
                "sTitle": "门店品牌"
            },{
                "mDataProp": 'managerCategoryName',
                "sTitle": "管理分类"
            },{
                "mDataProp": 'isSale',
                "sTitle": "状态"
            }],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}