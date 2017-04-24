var rootPath = getContextPath();
var sku_code;
$(function () {
    getProYeInfoBySpuCode();
    selectStockAndPriceByProDetail();

});
function getProYeInfoBySpuCode() {
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + "/shoppePro/getProYeInfoBySpuCode.shtml",
        dataType: "json",
        async: false,
        data: {
            "skuCode": "1000000106687"
        },
        success: function (pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;
            var picList = proData.stanPicList;
            for (var i = 0; i < picList.length; i++) {
                if (picList[i].picStan == '1000*1000') {
                    var proPicList = picList[i].picList;
                    for (var j = 0; j < proPicList.length; j++) {
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
            for (var i = 0; i < colorList.length; i++) {
                var color = '<li data-aid="' + colorList[i].colorName + '"><a href="javascript:;" title="' + colorList[i].colorName + '"><img src="http://10.6.100.100/' + colorList[i].thumbnailUrl + '" alt="' + colorList[i].colorName + '" /></a><i></i></li>';
                $('.sys_spec_img').append(color);
            }
            var stanList = proData.stanNewList;
            for (var i = 0; i < stanList.length; i++) {
                var stan = '<li data-aid="' + stanList[i].stanName + '"><a href="javascript:;" title="S">' + stanList[i].stanName + '</a><i></i></li>';
                $('.sys_spec_text').append(stan);
            }
            var skuList = proData.skuList;
            var proJson = "";
            for (var i = 0; i < skuList.length; i++) {
                if (i == 0) {
                    proJson = '"' + skuList[i].colorName + '_' + skuList[i].stanName + '":"' + skuList[i].skuCode + '"';
                } else {
                    proJson = proJson + ',"' + skuList[i].colorName + '_' + skuList[i].stanName + '":"' + skuList[i].skuCode + '"';
                }
            }
            proJson = "{" + proJson + "}";
            JSON.stringify(proJson);
            sku_code = JSON.parse(proJson);
            console.log(proData);
            $(".sys_item_spec .sys_item_specpara .sys_spec_img").each(function () {
                var i = $(this);
                var p = i.find("ul>li");
                p.click(function () {
                    var colorName = $(this).attr("data-aid");
                    $.each(colorList, function (index, content) {
                        if (!!colorName && colorName != null && colorName == content.colorName) {
                            $(".sys_item_spec .sys_item_specpara .sys_spec_text li").each(function () {
                                var listan = $(this);
                                var listanname = listan.attr("data-aid");

                                var flag = false;
                                $.each(content.stanList, function (index1, stanList) {
                                    if (listanname == stanList.stanName) {
                                        flag = true
                                    }
                                });
                                if (!flag) {
                                    listan.find("a").addClass("disabled");
                                    listan.unbind();
                                    listan.removeClass("selected");
                                }
                                else {
                                    if (listan.find("a").hasClass("disabled")) {

                                        listan.find("a").removeClass("disabled");
                                        listan.click(function () {

                                            if (!!$(this).hasClass("selected")) {
                                                $(this).removeClass("selected");
                                                i.removeAttr("data-attrval");
                                            } else {
                                                $(this).addClass("selected").siblings("li").removeClass("selected");
                                                i.attr("data-attrval", $(this).attr("data-aid"))
                                            }
                                            getattrprice() //输出价格
                                        });
                                    }
                                }
                            })
                        }
                    });
                })
            })
            $(".sys_item_spec .sys_item_specpara .sys_spec_text").each(function () {
                var i = $(this);
                var p = i.find("ul>li");
                p.click(function () {
                    var stanName = $(this).attr("data-aid");
                    console.log(stanName);
                    $.each(stanList, function (index, content) {
                        if (!!stanName && stanName != null && stanName == content.stanName) {
                            console.log(index+":"+content.stanName);
                            $(".sys_item_spec .sys_item_specpara .sys_spec_img li").each(function () {
                                var licolor = $(this);
                                var licolorname = licolor.attr("data-aid");
                                console.log("licolorname:",licolorname);
                                var flag = false;
                                $.each(content.colorNewList, function (index1, colorList) {
                                    if (licolorname == colorList.colorName) {
                                        flag = true
                                    }
                                });
                                if (!flag) {
                                    licolor.find("a").addClass("disabled");
                                    licolor.unbind();
                                    licolor.removeClass("selected");
                                }
                                else {
                                    if (licolor.find("a").hasClass("disabled")) {
                                        licolor.find("a").removeClass("disabled");
                                        licolor.click(function () {
                                            console.log("click","22222222222");
                                            if (!!$(this).hasClass("selected")) {
                                                $(this).removeClass("selected");
                                                i.removeAttr("data-attrval");
                                            } else {
                                                $(this).addClass("selected").siblings("li").removeClass("selected");
                                                i.attr("data-attrval", $(this).attr("data-aid"))
                                            }
                                            getattrprice() //输出价格
                                        });
                                    }
                                }
                            });
                        }
                    });
                })
            })
        },
        error: function (XMLHttpRequest, textStatus) {

        }
    });
}

function selectStockAndPriceByProDetail() {
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + "/shoppePro/selectStockAndPriceByProDetail.shtml",
        dataType: "json",
        async: false,
        data: {
            "skuCode": "1000000106754"
        },
        success: function (pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;
            $('.sys_item_price').html(proData.price);
            $('.sys_item_mktprice').html(proData.price);
            $('#pro-price').html(proData.price);
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