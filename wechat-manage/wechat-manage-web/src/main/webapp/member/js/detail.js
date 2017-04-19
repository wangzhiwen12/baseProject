var rootPath = getContextPath();
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
            "skuCode" : "1000000008171"
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
            "skuCode" : "1000000008171"
        },
        success : function(pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;
            $('#pro-price').html(proData.price);
        },
        error : function(XMLHttpRequest, textStatus) {

        }
    });
}