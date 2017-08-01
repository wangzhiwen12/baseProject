/**
 * Created by kongqf on 2017/7/27.
 */
var rootPath = getContextPath();
var skuCode = getUrlDataByKey("skuCode");
var sku_code;

$(function () {
    getProYeInfoBySpuCode();
    var swiper = new Swiper('.swiper-container', {
        //pagination: '.swiper-pagination',
        //slidesPerView: 1.5,
        centeredSlides: true,
        paginationClickable: true,
        spaceBetween: 20
    });

    var swiper = new Swiper('.swiper-container2', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        paginationBulletRender: function (swiper, index, className) {
            var data = {0: "商品详情", 1: "商品参数", 2: "商品评价"};
            return '<span class="' + className + ' v-detail' + '">' + data[index] + '</span>';
        }
    });

   /* $(document).on('click', '.jsPopup', function () {
        $.popup('.popup');
    });*/

});

function getProYeInfoBySpuCode() {
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + "/shoppePro/getProYeInfoBySpuCode.shtml",
        dataType: "json",
        async: false,
        data: {
            "skuCode": skuCode
        },
        success: function (pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;
            var picList = proData.stanPicList;
            for (var i = 0; i < picList.length; i++) {
                if (picList[i].picStan == '1000*1000') {
                    var imageTmpl = '{{each picList as pic i}}<div class="swiper-slide"><img src="http://img.wfjimg.com/{{pic.picUrl}}" alt="" width="100%"/></div>{{/each}}'
                    var render = template.compile(imageTmpl);
                    $('.js-slide').append(render(picList[i]));
                    break;
                }
            }
            $(".goods-product-text").text(proData.skuName);
            $('.big-price').html(proData.originalPrice);
            $('.jsStanColor').html(proData.colorName + "  ,  " + proData.stanName + "  ,  1件");
            console.log(proData.skuName);
            console.log(proData.originalPrice);
        },
        error: function (XMLHttpRequest, textStatus) {

        }
    });
}



(function () {
    window.inputNumber = function (el) {
        var min = el.attr('min') || false;
        var max = el.attr('max') || false;
        var els = {};
        els.dec = el.prev();
        els.inc = el.next();
        el.each(function () {
            init($(this));
        });

        function init(el) {
            els.dec.on('click', decrement);
            els.inc.on('click', increment);
            function decrement() {
                var value = el[0].value;
                value--;
                if (!min || value >= min) {
                    el[0].value = value;
                }
            }

            function increment() {
                var value = el[0].value;
                value++;
                if (!max || value <= max) {
                    el[0].value = value++;
                }
            }
        }
    }
})();