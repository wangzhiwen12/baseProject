/**
 * Created by kongqf on 16-12-12.
 */
$(function () {
    $("#btnCouponCode").click("click", function () {
        var url = rootPath + '/couponMember/useCouponMember.shtml';
        var data = CommnUtil.ajax(url, {couponCode: $("#CouponCode").val()}, "json");
        if (!!data) {
            console.log("data:", data);
        }
        else {
        }
    });
    $("#btnCouponQRCode").click("click", function () {
        var url = rootPath + '/couponMember/useCouponMember.shtml';
        var data = CommnUtil.ajax(url, {couponQRCode: $("#CouponQRCode").val()}, "json");
        if (!!data) {
            console.log("data:", data);
        }
        else {
        }
    })

})

function generQRCode(obj) {
    var str = toUtf8(obj);
    $("#code").qrcode({
        render: "table",
        width: 200,
        height: 200,
        text: str
    });
}

function toUtf8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for (i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        }
    }
    return out;
}

