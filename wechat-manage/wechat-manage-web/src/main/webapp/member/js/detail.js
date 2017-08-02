var rootPath = getContextPath();
var skuCode = getUrlDataByKey("skuCode");
var sku_code;
$(function () {
    $(document).on('click', '.create-popup', function () {
        $.popup('.popup');
    });
    inputNumber($('.input-number'));

    getProYeInfoBySpuCode();
    selectStockAndPriceByProDetail();

    //商品规格选择
    $(".sys_item_spec .sys_item_specpara").each(function () {
        var i = $(this);
        var p = i.find("ul>li");
        p.click(function () {
            if (!!$(this).hasClass("selected")) {
                $(this).removeClass("selected");
                i.removeAttr("data-attrval");
            } else {
                $(this).addClass("selected").siblings("li").removeClass("selected");
                i.attr("data-attrval", $(this).attr("data-aid"))
            }
            getattrprice() //输出价格

        })
    });


    $(".input-number-decrement,.input-number-increment").click(function () {
        var title = '';
        $(".sys_item_spec .sys_item_specpara").each(function () {
            title += $(this).attr("data-attrval") + " ,";
        });
        title += $(".input-number").val() + "件";
        $('#pro-stan-color').html(title);
    });
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
            console.log(JSON.stringify(proData));
            var picList = proData.stanPicList;
            for (var i = 0; i < picList.length; i++) {
                if (picList[i].picStan == '1000*1000') {
                    var imageTmpl = '{{each picList as pic i}}<div class="swiper-slide"><img src="http://img.wfjimg.com/{{pic.picUrl}}" alt="" width="100%"/></div>{{/each}}';
                    var detailTmpl = '{{each picList as pic i}}<img src="http://img.wfjimg.com/{{pic.picUrl}}" width="100%">{{/each}}';
                    var render = template.compile(imageTmpl);
                    var detailRender = template.compile(detailTmpl);
                    $('#pic-list-div').append(render(picList[i]));
                    $('.jsProDetail').append(detailRender(picList[i]));
                    break;
                }
            }
            $('#pro-name').html(proData.shortDesc);
            // $('#pro-short-desc').html(proData.shortDesc);
            // $('#pro-original-price').html(proData.originalPrice);
            $('#pro-stan-color').html(proData.colorName + "  ,  " + proData.stanName + "  ,  1件");

            var colorTmpl = '{{each colorList as cl i}}<li data-aid="{{cl.colorName}}"><a href="javascript:;" title="{{cl.colorName}}"><img src="http://img.wfjimg.com/{{cl.thumbnailUrl}}" alt="{{cl.colorName}}" /></a><i></i></li>{{/each}}';
            var colorRender = template.compile(colorTmpl);
            $('.sys_spec_img').append(colorRender(proData));

            var stanTmpl = '{{each stanNewList as stan i}}<li data-aid="{{stan.stanName}}"><a href="javascript:;" title="{{stan.stanName}}">{{stan.stanName}}</a><i></i></li>{{/each}}';
            var stanRender = template.compile(stanTmpl);
            $('.sys_spec_text').append(stanRender(proData));

            var colorList = proData.colorList;
            var stanList = proData.stanNewList;
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
            // JSON.stringify(proJson);
            sku_code = JSON.parse(proJson);

            $(".sys_item_spec .sys_item_specpara .sys_spec_img li").each(function () {
                var colorName = $(this).attr("data-aid");
                $(this).click(function () {
                    colorItem(colorList, colorName);
                });

                if (!!colorName && colorName != null && colorName == proData.colorName) {
                    $(this).addClass("selected").siblings("li").removeClass("selected");
                    $(this).parent().parent().parent().attr("data-attrval", $(this).attr("data-aid"))
                    $("#orderbuynoww").removeClass("buynowactive"); //没有选择尺码和颜色 立即购买按钮置灰色 外立即购买
                    $("#orderbuynown").removeClass("buynowactive"); //没有选择尺码和颜色 立即购买按钮置灰色 选择尺码和颜色里边立即购买
                }
            });
            $(".sys_item_spec .sys_item_specpara .sys_spec_text li").each(function () {
                var stanName = $(this).attr("data-aid");
                $(this).click(function () {
                    stanItem(stanList, stanName);
                });

                if (!!stanName && stanName != null && stanName == proData.stanName) {
                    $(this).addClass("selected").siblings("li").removeClass("selected");
                    $(this).parent().parent().parent().attr("data-attrval", $(this).attr("data-aid"))
                    $("#orderbuynoww").removeClass("buynowactive"); //没有选择尺码和颜色 立即购买按钮置灰色 外立即购买
                    $("#orderbuynown").removeClass("buynowactive"); //没有选择尺码和颜色 立即购买按钮置灰色 选择尺码和颜色里边立即购买
                }
            });
        },
        error: function (XMLHttpRequest, textStatus) {

        }
    });
}

function colorItem(colorList, colorName) {
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
}
function stanItem(stanList, stanName) {
    $.each(stanList, function (index, content) {
        if (!!stanName && stanName != null && stanName == content.stanName) {
            $(".sys_item_spec .sys_item_specpara .sys_spec_img li").each(function () {
                var licolor = $(this);
                var licolorname = licolor.attr("data-aid");
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
}

function selectStockAndPriceByProDetail() {
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + "/shoppePro/selectStockAndPriceByProDetail.shtml",
        dataType: "json",
        async: false,
        data: {
            "skuCode": skuCode
        },
        success: function (pro) {
            var proYe = JSON.parse(pro);
            var proData = proYe.data;

            var proPrice = "￥" + proData.price;
            $('.sys_item_price').html(proData.price);
            // $('.sys_item_mktprice').html(proPrice);
            $('#pro-price').html(proPrice);
        },
        error: function (XMLHttpRequest, textStatus) {

        }
    });
}

//获取对应属性的价格
function getattrprice() {
    var defaultstats = true;
    var _val = '';
    var _resp = {
        mktprice: ".sys_item_mktprice",
        price: ".sys_item_price"
    }  //输出对应的class
    $(".sys_item_spec .sys_item_specpara").each(function () {
        var i = $(this);
        var v = i.attr("data-attrval");
        if (!v) {
            defaultstats = false;
        } else {
            _val += _val != "" ? "_" : "";
            _val += v;
        }
    })
    if (!!defaultstats) {
        $.ajax({
            type: "post",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: rootPath + "/shoppePro/selectStockAndPriceByProDetail.shtml",
            dataType: "json",
            async: false,
            data: {
                "skuCode": sku_code[_val]
            },
            success: function (pro) {
                var proYe = JSON.parse(pro);
                var proData = proYe.data;
                var proPrice = "￥" + proData.price;
                $('#pro-price').html(proPrice);
                $('.sys_item_price').html(proPrice);

                _mktprice = proData.prvarice;
                _price = proData.price;  //价格
                var color_stan = _val.split("_");
                var proNum = $(".input-number").val();

                $('#pro-stan-color').html(color_stan[0] + "  ,  " + color_stan[1] + "  ,  " + proNum + "件");
                $('#spsid').attr('value', proData.spsid);
                //商品码    sku_code[_val];
                //购买数量  input-number
                //商品名称  id pro-name
                //商品在·图片 sys_spec_img  selected
                // 价格     _price
                //尺码   sys_spec_text
                order(sku_code[_val], _price, proData.spsid);
            },
            error: function (XMLHttpRequest, textStatus) {

            }
        });
    } else {
        _mktprice = $('.sys_item_price').text();
        _price = $('.sys_item_price').text();
    }
    //输出价格
    $(_resp.mktprice).text(_mktprice);  ///其中的math.round为截取小数点位数
    $(_resp.price).text(_price);
}
function order(productCode, price, productCode) {
    $("#orderbuynoww").removeClass("buynowactive"); //没有选择尺码和颜色 立即购买按钮置灰色 外立即购买
    $("#orderbuynown").removeClass("buynowactive"); //没有选择尺码和颜色 立即购买按钮置灰色 选择尺码和颜色里边立即购买
    //商品码    sku_code[_val];
    //购买数量  input-number
    //商品名称  id pro-name
    //商品在·图片 sys_spec_img  selected
    //价格     _price
    //尺码   sys_spec_text
    var _productCode = productCode;
    var _productPrice = price;
    var _productNum = $(".input-number").val();
    // var _productName = document.getElementById('pro-name').innerText;
    var _productName = encodeURI(document.getElementById('pro-name').innerText);// window.location.assign();
    var _productUrl = $(".sys_item_spec .sys_item_specpara .sys_spec_img .selected img").attr("src");
    var _productSize = $(".sys_item_spec .sys_item_specpara .sys_spec_text .selected a").html();
    var _productColer = encodeURI($(".sys_item_spec .sys_item_specpara .sys_spec_img .selected a").attr("title"));
    console.log("商品码 :" + _productCode);
    console.log("价格 :" + _productPrice);
    console.log("购买数量 :" + _productNum);
    console.log("商品名称 :" + _productName);
    console.log("图片 :" + _productUrl);
    console.log("尺码 :" + _productSize);
    console.log("颜色 :" + _productColer);
    var ahref = rootPath + "/member/order/ordersubmission.html?productCode=" + _productCode + "&productPrice=" + _productPrice + "&productNum=" + _productNum + "&productName=" + _productName + "&productUrl=" + _productUrl + "&productSize=" + _productSize + "&productColor=" + _productColer;
    document.getElementById("orderbuynoww").href = ahref;
    document.getElementById("orderbuynown").href = ahref;
    //document.getElementById("buy-now").arrt("href",ahref);

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