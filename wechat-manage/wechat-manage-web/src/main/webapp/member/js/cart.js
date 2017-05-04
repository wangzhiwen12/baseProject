var rootPath = getContextPath();
$(function () {




    getProduct();

    //删除事件
    var that;
    $('.delete_box').on('click',function(){
        $(this).children('.delete_up').css(
            {
                transition :'all 1s',
                'transformOrigin':"0 5px" ,
                transform :'rotate(-30deg) translateY(2px)'
            }

        )
        $('.jd_win').show();
        that = $(this);
    })

    $('.cancle').on('click',function(){
        $('.jd_win').hide();
        $('.delete_up').css('transform','none')
        console.log("取消");
    })
    $('.submit').on('click',function(){
        $('.jd_win').hide();
        console.log("删除");
        that.parent().parent().parent().parent().remove();
    })
});

//获取商品
function getProduct(){
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + "/cart/showProList.json",
        dataType: "json",
        async: false,
        data: {},
        success: function (data) {
         var   dataresult=data.modelResult.calcResult.markets;
            var p=0;
            if(dataresult!=null){
              //  $(".jd_shop_con *").remove();
                var productList=null;
                for (var i = 0; i < dataresult.length; i++) {
                    for (var j = 0; j < dataresult[i].products.length; j++){
                        product(dataresult[i].products[j],dataresult[i].market);
                      //  productList.add(dataresult[i].products[i]);
                        p++;
                    }
                }
                var sp='<span>合计：'+data.modelResult.calcResult.remainPay+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>件数：'+data.modelResult.calcResult.prodNum+'</span>';
                $("#qujiesuan").append(sp);
                // alert(p);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            messageBox_show("网络异常");
        }
    });
}


//页面打印商品
function product(products,market) {


    var a='<div class="product">'+
    '<div class="check_box">'+
      '<a href="#" class="jd_check_box" checked="true" onclick="productChbClick(this,'+products.sku+');return false;"  myclass="'+market+'" name="'+products.a2c+'"></a>'+
     //   '<input class="jd_check_box" name="cartCheckBox"  isproductchb="true" checked="checked" onclick="productChbClick(this,'+products.sku+');" myclass="'+market+'" canbeselected="yes" />'+
       // ' <input class="jd_check_box" name="cartCheckBox" type="checkbox"/>'+ type="checkbox"
        '</div>'+
    '<div class="shop_info clearfix">'+
        '<a href="#" class="img_box f_left"><img src="'+'http://10.6.100.100/'+products.imgUrl+'" alt=""/></a>'+
        '<div class="info_box">'+
        '<a class="p_name" href="#">'+products.desc+'</a>'+
    '<p class="p_price">&yen;'+products.saleAmount+'</p>'+
    '<div class="p_opition">'+
        '<div class="change_num f_left">'+
        '<span onclick="addQtyValue(-1,'+products.sku+'),'+market+'">-</span>'+
        '<input type="tel" value="'+products.qty+'"/>'+
        '<span onclick="addQtyValue(+1,'+products.sku+'),'+market+'">+</span>'+
        '</div>'+
        '<div class="delete_box f_right">'+
        '<span class="delete_up"></span>'+
        '<span class="delete_down"></span>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'+
       ' </div>';

    $(".jd_shop_con").append(a);
}


/*根据单个复选框的选择情况确定全选复选框是否被选中*/
function selectSingle(){
    var k=0;
    var oInput=document.getElementsByName("cartCheckBox");
    for (var i=0;i<oInput.length;i++){
        if(oInput[i].checked==false){
            k=1;
            break;
        }
    }
    if(k==0){
        document.getElementById("allCheckBox").checked=true;
    }
    else{
        document.getElementById("allCheckBox").checked=false;
    }
}


//修改商品数量（inpug框随便输入数字）
function addQtyValue_onchange(proSku, marketId) {
    var qty = $("#qty"+proSku).val();
    if(qty > 5){
        $("#qty"+proSku).val(5);
    }else if(qty == 0 || qty == ""){
        $("#qty"+proSku).val(1);
    }
    // 发送请求，刷新页面
    var requestStrJson = "";
    var textField = $("input[proChangeSku='" + proSku + "']");
    var preValue = textField.attr("preventValue");
    if (preValue == textField.val()) {

    } else {
        var res = parseInt(textField.val() || 0);
        if (res <= 0) {
            return;
        }
        addMask();
        requestStrJson = '{prosku:"' + proSku + '",' + 'amount:"' + res + '",marketId:"' + marketId + '"}';
        sendDatas(requestStrJson);
    }
}

/**
 * 单个商品选中
 */
function productChbClick(o, prosku) {

   // $(".jd_check_box").toggleClass("jd_check_out");

    addMask();
    var marketId = $(o).attr('myClass');
    $(o).attr('class','jd_check_out');
    if (o.name == false) {
        var myClass = $(o).attr('myclass');
        $("#" + myClass).attr('checked', false);
        $("#allChb").attr('checked', false);
    }
    var isselected = "";
    if (o.name) {
        
        isselected = "1";
    } else {
        isselected = "0";
    }
  //  changeProSelected(prosku, isselected, marketId)
}

//显示加载时友好提示
function addMask() {
    // 显示加载提示
    var mask = $("#mask_alert");
    mask.show();

}

//单个商品的勾选事件
function changeProSelected(prosku, isselected, marketId) {
    addMask();
    // 发送请求，刷新页面
    var params = "productSku=" + prosku + "&" + "isselected=" + isselected + "&marketId=" + marketId;
    $.ajax({
        url : 'cart/delectProduct',
        type : 'post',
        data : params,
        dataType : 'json',
        beforeSend : function() {
        },
        success : function(returnstr) {
            if (!returnstr.success) {
                messageBox_show(returnstr.msg);
            }
            ga('send', 'event', 'selectedProduct', prosku);//ga
            reloadProList();
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            // alert(errorThrown)
        },
        complete : function() {
        }
    });
}