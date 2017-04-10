$(function () {
    selectStoreSid();
    selectSupplierByShop();
    productList();


    $('#supplier_select').change(function(){
        selectShoppeByShopAndSupplier();
    });
});


function reset(){
    $("#shoppeProName_input").val("");
    $("#saleStatus_select").val("");
    $('input').val("");
    productList();
}

//获取门店SID
function selectStoreSid(){
    $.ajax({
        type : "post",
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        url : rootPath + "/shoppePro/selectStoreSid.shtml",
        dataType : "json",
        async : false,
        success : function(orgSid) {
            var orgSid = '<input type="hidden" id="input_store_sid" value="' + orgSid + '">';
            $('#storeSid').append(orgSid);
            return;
        },
        error : function(XMLHttpRequest, textStatus) {
            var sstatus =  XMLHttpRequest.getResponseHeader("sessionStatus");
            if(sstatus != "sessionOut"){
                $("#warning2Body").text("系统错误!");
                $("#warning2").show();
            }
            if(sstatus=="sessionOut"){
                $("#warning3").css('display','block');
            }
        }
    });
}

//根据门店和供应商查询专柜
function selectShoppeByShopAndSupplier(){
    $('#shoppe_select').removeAttr("disabled");//

    $('#shoppe_select').html("<option value=''>全部</option>");
    $.ajax({
        type : "post",
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        url : rootPath + "/shoppePro/findShoppeList.shtml",
        dataType : "json",
        async : false,
        data : {
            "shopSid" : $('#input_store_sid').val(),
            "supplySid" : $('#supplier_select').val()
        },
        success : function(response) {
            alert(response);
            response = JSON.parse(response);
            var result = response.list;
            if(typeof(result) != "undefined"){
                for (var i = 0; i < result.length; i++) {
                    var ele = result[i];
                    var option = $("<option value='" + ele.shoppeCode + "'>" + ele.shoppeName + "</option>");
                    option.appendTo($("#shoppe_select"));
                }
            }
            return;
        },
        error : function(XMLHttpRequest, textStatus) {
            var sstatus =  XMLHttpRequest.getResponseHeader("sessionStatus");
            if(sstatus != "sessionOut"){
                $("#warning2Body").text("系统错误!");
                $("#warning2").show();
            }
            if(sstatus=="sessionOut"){
                $("#warning3").css('display','block');
            }
        }
    });
}

//根据门店查询供应商
function selectSupplierByShop(){
    $('#supplier_select').html("<option selected='selected' value='' code=''>全部</option>");
    var organizationCode = $('#input_store_sid').val();
    $.ajax({
        type : "post",
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        url : rootPath + "/shoppePro/findListSupplier.shtml",
        dataType : "json",
        async : false,
        data : "shopCode=" + organizationCode,
        success : function(response) {
            response = JSON.parse(response);
            var result = response.list;
            if(typeof(result) != "undefined"){
                for (var i = 0; i < result.length; i++) {
                    var ele = result[i];
                    var option = $("<option value='" + ele.sid + "' code='" + ele.supplyCode + "'>"
                        + ele.supplyName + "</option>");
                    option.appendTo($("#supplier_select"));
                }
            }
            return;
        },
        error : function(XMLHttpRequest, textStatus) {
            var sstatus =  XMLHttpRequest.getResponseHeader("sessionStatus");
            if(sstatus != "sessionOut"){
                $("#model-body-warning").html("<div class='alert alert-error fade in'><i class='fa-fw fa fa-times'></i><strong>系统出错!</strong></div>");
                $("#modal-warning").attr({"style" : "display:block;","aria-hidden" : "false","class" : "modal modal-message modal-error"});
            }
            if(sstatus=="sessionOut"){
                $("#warning3").css('display','block');
            }
        }
    });
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
                "mDataProp": null,
                "sTitle": "选择",
                "mRender" : function (data, type, full) {
                    return '' +
                    '<input type="checkbox" id="'+data.productCode+'" value="'+data.productCode+'" >';
                }
            },{
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