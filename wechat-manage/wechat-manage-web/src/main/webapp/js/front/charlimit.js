//function readLength(cls) {
//    this.ds = $("." + cls);
//    // d.wrap("<div class='readLengthWarp'></div>")
//    this.init();
//}
//readLength.prototype = {
//    init: function() {
//        this.generateViews();
//        this.bindEvent();
//    },
//    generateViews: function() {
//        var ds = this.ds;
//        for (var i = 0; i < ds.length; i++) {
//            var d = ds.eq(i),
//                maxLg = d.attr("maxlength"),
//                name = d.prop("tagName"),
//                curLength = d.val().length,
//                wd = d.width();
//            if (!d.hasClass("exlimit")) {
//                d.addClass("exlimit");
//                d.wrap("<div class='readLengthWarp  type" + name + "' style=''></div>");
//                d.parent().append('<span class="status"> <span class="curLength">' + curLength + '</span> / <span class="totalLength">' + maxLg + '</span> </span>');
//            }
//        }
//    },
//    bindEvent: function() {
//        var bind_name = 'input';
//        if (navigator.userAgent.indexOf("MSIE") != -1) {
//            bind_name = 'propertychange';
//        }
//        this.ds.bind(bind_name, function() {
//            var _this = $(this);
//            var curLength = _this.val().length;
//            _this.parent().find(".curLength").html(curLength);
//        });
//        this.ds.change(function() {
//            var _this = $(this);
//            var curLength = _this.val().length;
//            _this.parent().find(".curLength").html(curLength);
//        });
//    }
//};

// function readLength(cls) {
//     this.ds = $("." + cls);
//     // d.wrap("<div class='readLengthWarp'></div>")
//     this.init();
// }
// readLength.prototype = {
//     init: function() {
//         this.generateViews();
//         this.bindEvent();
//     },
//     generateViews: function() {
//         var ds = this.ds;
//         for (var i = 0; i < ds.length; i++) {
//             var d = ds.eq(i),
//                 maxLg = d.attr("maxlength"),
//                 name = d.prop("tagName"),
//                 curLength = d.val().length,
//                 wd = d.width();
//             if (!d.hasClass("exlimit")) {
//                 d.addClass("exlimit");
//                 d.wrap("<div class='readLengthWarp  type" + name + "' style=''></div>");
//                 d.parent().append('<span class="status"> <span class="curLength">' + curLength + '</span> / <span class="totalLength">' + maxLg + '</span> </span>');
//             }
//         }
//     },
//     bindEvent: function() {
//         var bind_name = 'input';
//         if (navigator.userAgent.indexOf("MSIE") != -1) {
//             bind_name = 'propertychange';
//         }
//         this.ds.bind(bind_name, function() {
//             var _this = $(this);
//             var curLength = _this.val().length;
//             _this.parent().find(".curLength").html(curLength);
//         });
//         this.ds.change(function() {
//             var _this = $(this);
//             var curLength = _this.val().length;
//             _this.parent().find(".curLength").html(curLength);
//         });
//     }
// };



function readLength(cls){
    this.ds = $("." + cls);
    // d.wrap("<div class='readLengthWarp'></div>")
    this.init();
}
readLength.prototype = {
    init:function(){
        this.generateViews();
        this.bindEvent();
    },
    generateViews:function(){
        var ds = this.ds;
        for(var i = 0; i < ds.length; i++){
            var d = ds.eq(i),
                maxLg = d.attr("maxlength"),
                name = d.prop("tagName"),
                curLength = d.hasClass("getRealLength")?this.getRealLength(d.val()):d.val().length,
                wd = d.width();
            d.attr("data-maxlength", maxLg);
            d.wrap("<div class='readLengthWarp type"+name +"'></div>");
            d.parent().append('<span class="status"> <span class="curLength">'+ curLength +'</span> / <span class="totalLength">'+ maxLg +'</span> </span>');
        }
    },
    bindEvent:function(){
        var bind_name = 'input';
        var self = this;
        if (navigator.userAgent.indexOf("MSIE") != -1){
            bind_name = 'propertychange';
        }
        this.ds.bind(bind_name, function(){
            var _this = $(this);
            var maxLength = _this.data("maxlength");
            var curLength = _this.hasClass("getRealLength")?self.getRealLength(_this.val()):_this.val().length;

            if(_this.hasClass("getRealLength")){
                _this.removeAttr("maxlength");
            }
            if( curLength <= maxLength ){
                _this.parent().find(".curLength").html(curLength);
            }else{
                _this.parent().find(".curLength").html(maxLength);
                _this.val(_this.val().substring(0, _this.val().length - 1));
            }

        });
    },
    getRealLength:function(str){
        var length = str.length;
        var realLength = 0;
        for (var i = 0; i < length; i++) {
            charCode = str.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) {
                realLength += 1;
            } else {
                realLength += 2;
            }
        }
        return realLength;
    }
}