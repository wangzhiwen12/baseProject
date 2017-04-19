var rootPath = getContextPath();
var sku_code;
$(function () {
    getProYeInfoBySpuCode();
    selectStockAndPriceByProDetail();

});
function getProYeInfoBySpuCode(){
    $.ajax({
        type : "post",
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        url : rootPath + "/shoppePro/getProYeInfoBySpuCode.shtml",
        dataType : "json",
        async : false,
        data : {
            "skuCode" : "1000000106754"
        },
        success : function(pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;
            var picList = proData.stanPicList;
            for(var i = 0; i < picList.length ; i++ ){
                if( picList[i].picStan == '1000*1000'){
                    var proPicList =  picList[i].picList;
                    for( var j = 0 ; j < proPicList.length ; j++){
                        var pic = '<div class="swiper-slide">' +
                            '<img style="width: 320px;height: 320px;" src="http://10.6.100.100/' + proPicList[j].picUrl + '" alt="">' +
                            '</div>';
                        $('#pic-list-div').append(pic);
                    }
                }
            }
            $('#pro-name').html(proData.skuName);
            $('#pro-short-desc').html(proData.shortDesc);
            $('#pro-original-price').html(proData.originalPrice);
            $('#pro-stan-color').html(proData.colorName + "  ,  " + proData.stanName + "  ,  1件");
            var colorList = proData.colorList;
            for(var i = 0; i<colorList.length; i ++){
                var color = '<li data-aid="'+colorList[i].colorName+'"><a href="javascript:;" title="'+colorList[i].colorName+'"><img src="http://10.6.100.100/'+colorList[i].thumbnailUrl+'" alt="'+colorList[i].colorName+'" /></a><i></i></li>';
                $('.sys_spec_img').append(color);
            }
            var stanList = proData.stanNewList;
            for(var i = 0; i<stanList.length; i ++){
                var stan = '<li data-aid="'+stanList[i].stanName+'"><a href="javascript:;" title="S">'+stanList[i].stanName+'</a><i></i></li>';
                $('.sys_spec_text').append(stan);
            }
            var skuList = proData.skuList;
            var proJson = "";
            for(var i = 0; i < skuList.length; i++){
                if(i == 0){
                    proJson = '"'+skuList[i].colorName+'_'+skuList[i].stanName+'":"'+skuList[i].skuCode+'"';
                }else{
                    proJson = proJson + ',"'+skuList[i].colorName+'_'+skuList[i].stanName+'":"'+skuList[i].skuCode+'"';
                }
            }
            proJson = "{" + proJson + "}";
            JSON.stringify(proJson);
            sku_code = JSON.parse(proJson);
        },
        error : function(XMLHttpRequest, textStatus) {

        }
    });
}

function selectStockAndPriceByProDetail(){
    $.ajax({
        type : "post",
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        url : rootPath + "/shoppePro/selectStockAndPriceByProDetail.shtml",
        dataType : "json",
        async : false,
        data : {
            "skuCode" : "1000000106754"
        },
        success : function(pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;
            $('.sys_item_price').html(proData.price);
            $('.sys_item_mktprice').html(proData.price);
            $('#pro-price').html(proData.price);
        },
        error : function(XMLHttpRequest, textStatus) {

        }
    });
}