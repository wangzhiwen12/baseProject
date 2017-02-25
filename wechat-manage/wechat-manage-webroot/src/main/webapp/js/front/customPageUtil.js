var util = (function () {
    function objLg(o) {
        var lg = 0;
        for (prop in o) {
            lg++
        }
        return lg;
    }
    function cloneObj(o) {
        var o = o,
            co = {};
        for (prop in o) {
            co[prop] = o[prop]
        }
        return co;
    }

    function getObjName(o) {
        for (prop in o) {
            return prop;
        }
    }

    function setObjVal(a, k, v) {
        for (var i = 0; i < a.length; i++) {
            if (a[i][k]) {
                return a[i][k]
            }
        }
    }
    //indexOf
    function idxObj(a, k) {
        for (var i = 0; i < a.length; i++) {
            if (a[i][k]) {
                return i
            }
        }
    }
    function showTgt(d, arg, v) {
        //console.log(arg)

        var arg1 = arg ? arg.split("-")[0] : "";
        var arg2 = arg ? arg.split("-")[1] : "";
        switch (arg1) {
            case "cot":
                //d.html(v)
                if (v.length > 0) {
                    if (d.children(".showCot").length > 0) {
                        d.children(".showCot").css("display", "inline-block").html(v);
                        d.children(".emptyShow").css("display", "none");
                    } else {
                        d.html(v)
                    }
                } else {
                    if (d.children(".showCot").length > 0) {
                        d.children(".showCot").css("display", "none").html(v);
                        d.children(".emptyShow").css("display", "inline-block");
                    } else {
                        d.html(v)
                    }
                }
                break;
            case "href":
                d.attr("href", v)
                break;
            case "vl":
                d.val(v);
                break;
            case "src":
                //20160406
                if (v.length > 3) {
                    d.attr("src", v).removeClass("wdn");
                } else {
                    d.addClass("wdn");
                }
                break;
            case "cls":
                d.attr("class", "");
                d.addClass(v);
                break;
            case "hg":
                d.css("height", (v + "px"));
                break;
            case "bg":
                d.css("background", v);
                break;
            case "data":
                d.attr("data-" + arg2, v);
                break;

            case "datalist":
                if (v) {

                    d.data("list", v);
                    var v = JSON.parse(v)
                    console.log(v);
                    var s = "";
                    for (i = 0; i < v.length; i++) {
                        var sn = "<a href='" + v[i]["url"] + "' class='list'>";
                        $.each(v[i], function (k, v) {
                            if (k !== "url") {
                                sn += "<span class='" + k + "'>" + v + "</span>";
                            }
                        })
                        sn += "</span>";
                        s += sn;
                    }
                    d.html(s);

                }
                break;
        }
    }
    //排序
    //  1.向上
    // -1. 向下
    function modSort(a, idx, g) {
        var a = a,
        //微商城 + 1
            idx = idx,
            getIdx = idx + g;
        var item = a[idx];
        a.splice(idx, 1);
        if (g || g === 0) {
            a.splice(getIdx, 0, item)
        }
    }

    function domSort(d, g) {
        var idx = d.index(),
            gt = d.parent().children().eq(idx + g);
        if (g < 0) {
            gt.before(d);
        }
        if (g > 0) {
            gt.after(d);
        }
    }

    function rad(ipt, arg, cb) {
        var ipt = ipt;
        this._ipt = ipt;
        this._views = ipt.data("hand-views").split(" ");
        this._value = ipt.data("hand-value").split(" ");
        this._name = ipt.attr("id");
        this._arg = arg;
        this.handle = $("<span></span>");
        this.init();
    }
    rad.prototype = {
        init: function () {
            this.showDom();
            this.bindEvent();
        },
        showDom: function () {
            var views = this._views,
                value = this._value,
                name = this._name,
                arg = this._arg,
                s = "";
            for (var i = 0; i < views.length; i++) {
                var checked = value[i] === arg[0] ? "checked" : "";
                s = $("<span class='handle'><input id='" + name + "-" + i + "' name='" + name + "' type='radio' value='" + value[i] + "'" + checked + "/><lable for='" + name + "-" + i + "'>" + views[i] + "</lable></span>");
                this.handle.append(s);
            }
            this._ipt.after(this.handle);
        },
        bindEvent: function () {
            var that = this,
                name = this._name;
            this.handle = this.handle.find("input");
            this._ipt.click(function () {
                alert();
                //console.log(that.handle)
            })
            this.handle.change(function () {
                var v = $("[name='" + name + "']:checked").val();
                that._ipt.val(v).change();
                that._arg[0] = v;
                //console.log($("[name='"+ name +"']:checked"));
            })
        }
    }
    function ckbox(ipt, arg, cb) {
        var ipt = ipt;
        this._ipt = ipt;
        this._views = ipt.data("hand-views");
        this._value = ipt.data("hand-value").split(" ");
        this._name = ipt.attr("id");
        this._arg = arg;
        this.handle = $("<span></span>");
        var handContrl = ipt.data("hand-contrl");
        if (handContrl) {
            this.handContrl = ipt.parents(".cItemsList").find("[data-contrl='" + handContrl + "']");
        }
        //console.log(this.handContrl)
        this.init();
    }

    ckbox.prototype = {
        init: function () {
            this.showDom();
            this.bindEvent();
        },
        showDom: function () {
            var views = this._views,
                value = this._value,
                name = this._name,
                arg = this._arg,
                s = "";
            if (this.handContrl) {
                this.handContrl.attr("class", "").addClass(arg[0]);
            }
            //for(var i = 0; i < views.length; i++){
            var checked = value[0] === arg[0] ? "" : "checked";
            s = $("<span class='handle'><input id='" + name + "-" + 0 + "' name='" + name + "' type='checkbox' value=''" + checked + "/><lable for='" + name + "-" + 0 + "'>" + views + "</lable></span>");
            this.handle.append(s);
            //}
            this._ipt.after(this.handle);
        },
        bindEvent: function () {
            var that = this,
                name = this._name;
            this.handle = this.handle.find("input");
            this._ipt.click(function () {
                alert();
                //console.log(that.handle)
            })
            this.handle.change(function () {
                var v = $("[name='" + name + "']:checked").val();
                that._ipt.val(v).change();
                that._arg[0] = v;
                //console.log($("[name='"+ name +"']:checked"));
                var _$this = $(this);
                var v = "";
                if (_$this.is(":checked")) {
                    v = that._value[1];
                }
                else {
                    v = that._value[0];
                }
                if (that.handContrl) {
                    that.handContrl.attr("class", "").addClass(v);
                }
                that._ipt.val(v).change();
                that._arg[0] = v;
            })
        }
    }


    function slt(ipt, arg, cb) {


        this._ipt = ipt;

        //this._arg = arg;
        if (arg.length === 1) {
            arg.push("");
        }
        this._arg = arg;

        this.warp = $(".tpl[data-tpl='selectLink']").clone().removeClass("tpl");

        this.ret = this.warp.find(".ret").find(".cot");
        this.retWarp = this.warp.find(".ret");

        this.contralItems = this.warp.find(".contralItems");
        this.contralPop = this.warp.find(".bindPop");

        this.btnShow = this.warp.find(".btnShow");
        this.cancel = this.warp.find(".ret").find(".cancle");

        //20150906
        //增加 icon
        this.imgIpt = this._ipt.parents(".cItemsList").find("input[data-field-key='img']");


        this.lastItem = arg.length > 1 ? (arg.length - 1) : null;

        this.init();

    }
    slt.prototype = {
        init: function () {
            //alert();
            //console.log(this.warp)
            this.showRet(this._arg.length)
            this.showDom();
        },
        showRet: function (arg) {
            if (arg) {
                this.btnShow.html("修改");
                this.cancel.html("X");
                this.retWarp.css("display", "inline-block");
            } else {
                this.ret.html("");
                this.cancel.html("");
                this.btnShow.html("设置链接");
                this._ipt.val("").change();
                this.retWarp.css("display", "none");
            }
        },
        showDom: function () {
            var warp = this.warp,
                arg = this._arg,
                ret = this.ret;
            this._ipt.after(warp);
            if (arg) {

                ret.html(arg[0]);
            }
            //20160325
            if (arg.length > 0 && arg[1].length > 0) {
                var vid = this._ipt.attr("id").split("_");
                vid.splice(0, 1, "v");
                //$("#" + vid.join("_")).addClass(arg[1]);
                //console.log(this._ipt);
                this._ipt.addClass("ctl-wszl");
            }
            this.bindEvent();
        },
        bindEvent: function () {
            var that = this,
                contralItems = this.contralItems,
                ret = this.ret;
            //20151204
            that._ipt.change(function () {
                if ($(this).val().length > 0) {
                    var s = $(this).data("show");
                    that._arg[0] = s;
                    ret.html(s);
                    that.showRet(true);
                } else {
                    that._arg[0] = "";
                    that._arg.splice(0, 1);
                }
            })
            contralItems.click(function () {
                //var _$this = $(this),
                //    arg = that._arg,
                //    s = _$this.data("show"),
                //    imgSrc = _$this.data("src"),
                //    v = _$this.data("value");
                //that._ipt.val(v).change();
                //that.imgIpt.val(imgSrc).change();
                ////var s2 = "<img src='"+ imgSrc +"'>" + s;
                //ret.html(s);
                //
                //arg[0] = s;
                //that.showRet(true);
                ////console.log(arg);

                var vid = that._ipt.attr("id").split("_");
                vid.splice(0, 1, "v");
                if ($(this).hasClass("wszl")) {
                    $("#" + vid.join("_")).parent().addClass("wszlitem");
                    if (that.lastItem) {
                        that._arg[that.lastItem] = "wszlitem";
                    }
                } else {
                    $("#" + vid.join("_")).parent().removeClass("wszlitem");
                    if (that.lastItem) {
                        that._arg[that.lastItem] = "";
                    }
                }

                //20151204
                if (!$(this).hasClass("addpro")) {
                    var _$this = $(this),
                        arg = that._arg,
                        s = _$this.data("show"),
                        imgSrc = _$this.data("src"),
                        v = _$this.data("value");
                    that._ipt.val(v).data("show", s).change();
                    if (imgSrc.length > 0) {
                        that.imgIpt.val(imgSrc).change();
                        var s2 = "<img src='" + imgSrc + "'>" + s;
                    }
                    ret.html(s);

                    arg[0] = s;
                    that.showRet(true);
                    //console.log(arg);
                }
            })

            this.cancel.click(function () {
                that.showRet(false);
            })

            //在定义菜单
            this.contralPop.click(function () {
                var __$this = $(this),
                    _$this = that._ipt,
                    arg = that._arg,
                    ret = that.ret,
                    imgSrc = __$this.data("src"),
                    s = __$this.data("show");

                $(".wPopup").remove();
                $(".triggerwPopup").removeClass("triggerwPopup");
                $(this).addClass("triggerwPopup");

                var fn1 = function (value) {
                    that._ipt.val(value["externalLinks"]).change();
                    //console.log(s)
                    //console.log(imgSrc)
                    //console.log(that.imgIpt)
                    if (imgSrc.length > 0) {
                        that.imgIpt.val(imgSrc).change();
                    }
                    ret.html(s + value["externalLinks"]);
                    arg[0] = s + value["externalLinks"];

                    //console.log(arg);
                    that.showRet(true);
                }
                var fn2 = function (value) {
                }
                var o = {
                    tger: _$this,
                    inner: "<input type='text' class='iData externalLinks vali' data-valiitems='isEmpty isLink' data-alertmsg='链接不能为空 请正确填写链接' data-alerttype='[1,1]'/>",
                    value: ["externalLinks", "ret"],
                    determineFn: fn1,
                    cancelFn: fn2
                }
                var c = new util.pop(o);
            })

        }
    }

    function sld(ipt, arg, cb) {
        this._wrap = $("<div><div class='cot'></div><div class='ret'><span></span>象素</div></div>")
        this.wrap = this._wrap.find(".cot");
        this.showRet = this._wrap.find(".ret > span");
        this._arg = arg[0] ? arg[0] : 0;
        this._ipt = ipt;
        this.init();
    }
    sld.prototype = {
        init: function () {
            var wrap = this.wrap,
                showRet = this.showRet,
                arg = this._arg,
                ipt = this._ipt;
            ipt.after(this._wrap);
            this.bindEvent();
        },
        bindEvent: function () {
            var wrap = this.wrap,
                showRet = this.showRet,
                arg = this._arg,
                ipt = this._ipt;

            //wrap.slider({
            //    //orientation: "vertical",
            //    range: "min",
            //    min: 0,
            //    max: 100,
            //    value: arg,
            //    slide: function( event, ui ) {
            //        ipt.val( ui.value).change();
            //        arg[0] = ui.value;
            //        showRet.html(ui.value);
            //    }
            //});
            //ipt.val( wrap.slider( "value" ) );
            //showRet.html(wrap.slider( "value" ));
        }
    }


    function pop(arg) {
        var arg = arg;
        this._arg = arg;
        this.wrap = $("<div class='wPopup'><div class='wPopupCot'></div><div class='wPopupBtn'><a href='javascript:void(0)' class='determine'>确定</a><a href='javascript:void(0)' class='cancel'>关闭</a></div></div>");

        this.cot = this.wrap.find(".wPopupCot");
        this.determineBtn = this.wrap.find(".determine");
        this.cancelBtn = this.wrap.find(".cancel");

        this.inner = arg["inner"];
        this.determineFn = arg["determineFn"];
        this.cancelFn = arg["cancelFn"];

        this.tger = arg["tger"];
        var height = this.tger.height(),
            width = this.tger.width(),
            top = this.tger.offset().top + height + 5,
            left = this.tger.offset().left;

        this.DomX = left;
        this.DomY = top;

        this._value = arg["value"];

        this.init();
    }
    pop.prototype = {
        init: function () {
            this.showDom();
            this.bindEvent();
        },
        showDom: function () {
            var x = this.DomX;
            y = this.DomY;
            this.wrap.css({ "top": (y + "px"), "left": (x + "px") });
            this.cot.append(this.inner);
            $("body").append(this.wrap);
        },
        bindEvent: function () {
            var that = this;
            //20150918验证
            var yz = new VerifyCollection("", that.cot);
            this.determineBtn.click(function () {
                yz.through = true;
                if (yz.gThrough()) {
                    that.determineFn(that.value());
                    that.tger.trigger("click");
                    that.clearDom();
                }

            })
            this.cancelBtn.click(function () {
                that.cancelFn(that.value());
                that.clearDom();
            })
        },
        value: function () {
            var o = {},
                _value = this._value;
            for (var i = 0; i < _value.length; i++) {
                var v = this.wrap.find(".iData." + _value[i]).val() ? this.wrap.find(".iData." + _value[i]).val() : "";
                o[_value[i]] = v;
            }
            return o;
        },
        clearDom: function () {
            this.wrap.remove();
        }
    }


    //radio
    function rado(ipt, arg, cb) {
        this._ipt = ipt;
        this._arg = arg;

        //cls

        this.warp = $(".tpl[data-tpl='" + this._arg[0] + "']").clone().removeClass("tpl");
        this.rad = this.warp.find("input")

        //this.ret = this.warp.find(".ret").find(".cot");
        //this.contralItems =  this.warp.find(".contralItems");
        //this.contralPop =  this.warp.find(".bindPop");

        //this.btnShow = this.warp.find(".btnShow");
        //this.cancel = this.warp.find(".ret").find(".cancle");

        this.init();

    }
    rado.prototype = {
        init: function () {
            //alert();
            //console.log(this.warp)
            this.showRet(this._arg)
            this.showDom();
        },
        showRet: function (arg) {
            var cRad = this.warp.find("input[value='" + arg[1] + "']");
            var v = cRad.data("value");
            var mv = cRad.val();
            cRad.prop("checked", true);
            this._ipt.val(v).change();
            arg[1] = mv;
        },
        showDom: function () {
            var warp = this.warp,
                arg = this._arg,
                ret = this.ret;
            this._ipt.after(warp);
            //if(arg){
            //    ret.html(arg[0]);
            //}
            this.bindEvent();
        },
        bindEvent: function () {
            var that = this;
            this.rad.change(function () {
                //alert()
                var v = that.warp.find("input:checked").data("value");
                var v2 = that.warp.find("input:checked").val()
                that._ipt.val(v).change();
                that._arg[1] = v2;
                //console.log(that._arg)
            })
        }
    }

    //20150916
    function VerifyCollection(tget, tgets) {
        var __this = this;
        //this.valitgets = typeof tget === "string" ? $(tget) : tget.find("input.vali");
        this.valitgets = tget ? tget : tgets.find("input.vali");
        //console.log(this.valitgets)
        if (this.valitgets.length > 1) {
            for (var i = 0; i < this.valitgets.lengh; i++) {
                if (this.valitgets.eq(i).siblings(".msg").length === 0) {
                    this.valitgets.eq(i).after("<div class='msg'></div>")
                }
            }
        } else {
            // this.valitgets.after("<div class='msg'></div>");
            this.valitgets.parent().append("<div class='msg'></div>");
        }
        this.valitgets.after(this.msg);
        this.through = true;
        this.valitgets.change(function () {
            var _this = $(this);
            __this.vali(_this);
        })
    }
    VerifyCollection.prototype = {
        init: function () {
        },
        gThrough: function () {
            //console.log(this.valitgets);
            for (var i = 0, lgs = this.valitgets.length; i < lgs; i++) {
                this.vali(this.valitgets.eq(i));
                if (!this.through) {
                    //                        break;
                }
            }
            return this.through;
        },
        inputClear: function () {
            this.valitgets.val("");
        },
        vali: function (item) {
            var valiItems = typeof item.data("valiitems") === "string" ? item.data("valiitems").split(" ") : item.data("valiitems");
            var msg = typeof item.data("alertmsg") === "string" ? item.data("alertmsg").split(" ") : item.data("alertmsg");
            var alertType = typeof item.data("alerttype") === "string" ? item.data("alerttype").split(" ") : item.data("alerttype");
            var valiVal = item.val();
            if (valiItems) {

                for (var i = 0; i < valiItems.length; i++) {
                    if (valiItems[i] == "isEmpty") {
                        if (!valiVal) {
                            //                            this.alt(alertType[i], msg[i]);
                            item.siblings(".msg").addClass("error").html(msg[i]);
                            this.through = false;
                            break;
                        } else {
                            //                            this.alt("", "");
                            item.siblings(".msg").removeClass("error").html("");
                        }
                    }
                    if (valiItems[i] == "isPhone") {
                        if (!this.isPhone(valiVal)) {
                            //                            this.alt(alertType[i], msg[i]);
                            item.siblings(".msg").addClass("error").html(msg[i]);
                            this.through = false;
                            break;
                        } else {
                            //                            this.alt("", "");
                            item.siblings(".msg").removeClass("error").html("");
                        }
                    }
                    if (valiItems[i] == "isLink") {
                        if (!this.isLink(valiVal)) {
                            item.siblings(".msg").addClass("error").html(msg[i]);
                            this.through = false;
                            break;
                        } else {
                            item.siblings(".msg").removeClass("error").html("");
                        }
                    }
                }
            }
        },
        multVali: function () {
        },
        isPhone: function (s) {
            var regs = /^0{0,1}(13[0-9]|15[7-9]|150|153|156|18[3-9])[0-9]{8}$/g;
            return regs.test(s);
        },
        isEmpty: function (s) {
            return s;
        },
        isLink: function (s) {
            var regs = /(ftp|https?):\/\/[^ "]+$/;
            return regs.test(s);
        },
        alt: function (tp, msg) {
            this.msg.html(msg)
        },
        ret: function () {
        }
    }

    //显示图片
    function ShowNodeImg(ipt, arg) {
        this.ipt = ipt;
        this.arg = arg;
        this.sImgWrap = ipt.siblings(".showNodeImg");

        //20160406 删除
        this.del = $("<span class='del'> X </span>");
        this.sImgWrap.after(this.del);

        this.init();
    }
    ShowNodeImg.prototype = {
        init: function () {
            this.itemImg();
            this.bindEvent();
        },
        bindEvent: function () {
            var that = this;
            this.ipt.change(function () {
                that.arg[0] = that.ipt.val();
                that.itemImg();
            });
            //20160406 删除
            this.del.click(function () {
                that.ipt.val(" ").change();
            })
        },
        itemImg: function () {
            //var imgSrc = this.ipt.val() ;
            var imgSrc = this.arg[0];
            if (imgSrc) {
                this.sImgWrap.html("<img src='" + imgSrc + "' >");
            }
        }
    }


    //拖拽
    function rangeSlider(sld, onDrag, df) {
        var
            range = sld,
            dragger = range.children("span"),
            ret = range.find("b"),
            progressBar = range.find("i"),
            draggerWidth = 10,
            down = false,
            rangeWidth, rangeLeft;
        dragger.css({ "width": (draggerWidth + 'px'), "left": ((df - 2) + '%'), "margin-left": ((draggerWidth / 2) + 'px') });
        ret.html(df);
        progressBar.css("width", (df + "%"));
        range.bind("mousedown", function (e) {
            rangeWidth = $(this).width();
            rangeLeft = $(this).offset().left;
            //rangeLeft = df;
            down = true;
            updateDragger(e);
            return false;
        });
        document.addEventListener("mousemove", function (e) {
            updateDragger(e);
        });
        document.addEventListener("mouseup", function () {
            down = false;
        });
        function updateDragger(e) {
            if (down && e.pageX >= rangeLeft && e.pageX <= (rangeLeft + rangeWidth)) {
                dragger.css("left", (e.pageX - rangeLeft - draggerWidth + 'px'))
                if (typeof onDrag == "function") onDrag(Math.round(((e.pageX - rangeLeft) / rangeWidth) * 100));
            }
        }
    }

    function RangeSlider(ipt, arg, cb) {
        this.ipt = ipt;
        this._arg = arg;
        this.arg = arg[0] ? arg[0] : 0;
        this.slider = $('<div class="range-slider">' +
            '<span></span><i></i>' +
            '<p><b></b>像素</p>' +
            '</div>');
        this.controlPoints = this.slider.children("span");
        this.ret = this.slider.find("b");
        this.progressBar = this.slider.find("i");
        this.ipt.after(this.slider);
        //this.controlPoints.css("left", this.ipt.val());
        this.init();
    }
    RangeSlider.prototype = {
        init: function () {
            var that = this;
            rangeSlider(this.slider, function (value) {
                that.ipt.val(value).change();
                that.ret.html(value);
                that.progressBar.css("width", (value + "%"));
                that._arg[0] = value;
            }, this.arg);
        }
    }

    //2015-11-11 add
    var ListStyleShowData = {
        "larger": {
            "checkedValue": "larger",
            "listStyle": ["cardStyle", "minimalistStyle"],
            "items": {
                "cardStyle": ["showBuyBtn", "showGoodsName", "showGoodsDescription", "showGoodsPrice"],
                "minimalistStyle": ["showGoodsName", "showGoodsDescription", "showGoodsPrice"]
            }
        },
        "smaller": {
            "checkedValue": "smaller",
            "listStyle": ["cardStyle", "waterfallFlow", "minimalistStyle", "sales"],
            "items": {
                "cardStyle": ["showBuyBtn", "showGoodsName", "showGoodsPrice"],
                "waterfallFlow": ["showBuyBtn", "showGoodsName", "showGoodsPrice"],
                "minimalistStyle": ["showGoodsPrice"],
                "sales": []
            }
        },
        "largeTwoSmaller": {
            "checkedValue": "largeTwoSmaller",
            "listStyle": ["cardStyle", "minimalistStyle"],
            "items": {
                "cardStyle": ["showBuyBtn", "showGoodsName", "showGoodsPrice"],
                "minimalistStyle": ["showGoodsName", "showGoodsPrice"]
            }
        },
        "detailedList": {
            "checkedValue": "detailedList",
            "listStyle": ["cardStyle", "minimalistStyle"],
            "items": {
                "cardStyle": ["showBuyBtn"],
                "minimalistStyle": ["showBuyBtn"]
            }
        }
    }
    //20151020
    function ListStyle(ipt, arg, cb, fields, fieldsDom) {

        this._ipt = ipt;
        this._arg = arg;

        this.chked = arg;

        this.fields = fields ? fields : ListStyleShowData;
        this.fieldsDoms = fieldsDom ? $(fieldsDom).clone() : $(".wGoodsListForm.tpl").clone().removeClass("tpl");

        this._ipt.after(this.fieldsDoms);

        this.fieldsDom = this.fieldsDoms.find(".fields");
        this.listLayout = this.chked["listLayout"];
        this.listStyle = this.chked["listStyle"];

        //划分两个区间
        this.ret = arg;
        this.retCls = ""
        this.init();

    }
    ListStyle.prototype = {

        init: function () {
            this.showFields();
            this.showCheckedFields();
            this.bindEvent();

            this._ipt.val(this.retCls);

        },
        bindEvent: function () {
            var that = this;
            this.fieldsDom.change(function () {
                that.getCheckedValue();
                that.showFields();
                that.showCheckedFields();
                that.getCheckedValue();
                that.getCls();

                that._ipt.val(that.retCls).change();
            })
        },
        showFields: function () {
            var chked = this.chked;
            var listLayout = chked["listLayout"],
                listStyleChked = chked["listStyle"];

            //显示二级
            var listStyle = this.fields[listLayout]["listStyle"];

            var listBuyStyle = chked["listBuyStyle"];

            //显示三级
            var listStyleItems = this.fields[listLayout]["items"][listStyleChked];

            var allShowFields = listStyle.concat(listStyleItems).concat(listBuyStyle);


            this.fieldsDom.parent().addClass("wdn");
            this.showField(allShowFields);

        },
        showCheckedFields: function (chked) {

            var itemsShow = this.chked["itemsShow"],
                listLayout = this.chked["listLayout"],
                listStyle = this.chked["listStyle"],
                listBuyStyle = this.chked["listBuyStyle"],
                listLayoutDom = this.fieldsDoms.find(".fields[value=" + listLayout + "]"),
                listStyleDom = this.fieldsDoms.find(".fields[value=" + listStyle + "]");

            listLayoutDom.prop("checked", true);
            listStyleDom.prop("checked", true);
            this.fieldsDoms.find(".fields[value=" + listBuyStyle + "]").prop("checked", true);
            this.showCheckedField(itemsShow);
            //在计算一次值
            //this.getCheckedValue();
        },
        showField: function (fld) {
            for (var i = 0; i < fld.length; i++) {
                this.fieldsDoms.find(".fields[value=" + fld[i] + "]").parent().removeClass("wdn");
            }
        },
        showCheckedField: function (fld) {
            $(".showChild").removeClass("showChild");
            for (var i = 0; i < fld.length; i++) {
                this.fieldsDoms.find(".fields[value=" + fld[i] + "]").prop("checked", true);
                if (fld[i] == "showBuyBtn") {
                    this.fieldsDoms.find(".fields[value='showBuyBtn']").parent().addClass("showChild");
                }
            }
        },
        getCheckedValue: function () {

            var checkedItems = this.fieldsDoms.find(".fields:checked");
            var chked = this.chked;
            chked["itemsShow"] = [];

            for (var i = 0; i < checkedItems.length; i++) {
                if (checkedItems.eq(i).is(":visible")) {
                    var value = checkedItems.eq(i).val(),
                        name = checkedItems.eq(i).attr("name");
                    if (chked[name]) {
                        chked[name] = value;
                    }
                    else {
                        chked["itemsShow"].push(value);
                    }
                }
            }

            var listLayout = chked["listLayout"],
                listStyleChked = chked["listStyle"];

            //显示二级
            var listStyle = this.fields[listLayout]["listStyle"];
            if (!this.hasItem(listStyleChked, listStyle)) {
                chked["listStyle"] = "cardStyle";
            }
        },
        hasItem: function (item, ary) {
            var ret = false;
            for (var i = 0; i < ary.length; i++) {
                if (ary[i] == item) {
                    ret = true;
                    break;
                }
            }
            return ret;
        },
        changeField: function () {
        },
        getCls: function () {
            var cls = [];
            cls = cls.concat(this.chked["itemsShow"]);

            cls.push(this.chked["listLayout"]);
            cls.push(this.chked["listStyle"]);
            cls.push(this.chked["listBuyStyle"]);
            this.retCls = cls.join(" ");
        }

    }

    //轮播样式选择
    function addAdvertisingStyle(ipt, arg, cb) {
        this.compDom = $(".addAdvertisingStyle.tpl").clone().removeClass("tpl");
        this.ipts = this.compDom.find("input");

        this._arg = arg;
        this._ipt = ipt;
        this.ret = [];

        this.init();
    }
    addAdvertisingStyle.prototype = {
        init: function () {
            this.showDom();
            this.setValue();
            this._ipt.after(this.compDom);
            this.bindEvent();
        },
        bindEvent: function () {
            var that = this;
            this.ipts.change(function () {
                that.isShow();
                that.setValue();
            })
        },
        setValue: function () {
            this.getStyle();
            var ret = this.ret.join("-");
            this._ipt.val(ret).change();
            this._arg["addAdvertisingStyle"] = this.ret;
        },
        showDom: function () {
            var sltCss = this._arg["addAdvertisingStyle"];
            this.compDom.find("input[value='" + sltCss[0] + "']").prop("checked", true);
            this.compDom.find("input[value='" + sltCss[1] + "']").prop("checked", true);
            this.isShow();
        },
        isShow: function () {
            if (this.compDom.find("input[value='folding']").is(":checked")) {
                this.compDom.find("input[value='larger']").prop("checked", true);
                this.compDom.find("input[value='insets']").parent().addClass("wdn");
            } else {
                this.compDom.find("input[value='insets']").parent().removeClass("wdn");
            }
        },
        getStyle: function () {
            var a = [];
            var chked = this.compDom.find("input:checked");
            for (var i = 0; i < chked.length; i++) {
                a.push(chked.eq(i).val());
            }
            this.ret = a;
        }
    }

    //橱窗样式选择
    function CompRadio(ipt, arg, cb) {
        this._ipt = ipt;
        this._arg = arg;
        this._cb = cb;
        var tpl = this._arg["compRadio"]["tpl"];
        this.compDom = $(tpl).clone().removeClass("tpl");
        this.ipts = this.compDom.find("input[type='radio']");

        this.init();
    }
    CompRadio.prototype = {
        init: function () {
            //alert()
            this._ipt.after(this.compDom);
            this.showDom();

            if (this._arg["compRadio"]["fn"]) {
                //this._arg["compRadio"]["fn"](this._ipt, this._arg["compRadio"]);
                this.fn(this._ipt, this._arg["compRadio"]);
            }
            this.bindEvent();
        },
        bindEvent: function () {
            var that = this;
            this.ipts.change(function () {
                var val = that.compDom.find("input[type='radio']:checked").val();
                that._ipt.val(val).change();
                that._arg["compRadio"]["ched"] = val;
                if (that._arg["compRadio"]["fn"]) {
                    //that._arg["compRadio"]["fn"](that._ipt, that._arg["compRadio"]);
                    that.fn(that._ipt, that._arg["compRadio"]);
                }
            })
        },
        showDom: function () {
            this.compDom.find("input[value='" + this._arg["compRadio"]["ched"] + "']").prop("checked", true);
        },
        fn: function (t, v) {

            var wrap = t.parents(".radWarp");
            wrap.find(".rad").addClass("wdn");
            if (v["ched"] === "traditional") {
                wrap.find(".rad1").removeClass("wdn")
            }
            if (v["ched"] === "weChatStyle") {
                wrap.find(".rad2").removeClass("wdn")
            }
        }
    }


    //20151112 添加商品
    //var goods = [];
    //显示 views 商品
    //显示 ctl 商品
    function chooseGoods(ipt, arg, cb) {
        this._ipt = ipt;
        //this._arg = JSON.parse(arg["goods"]);
        this._arg = arg;
        //this.goodsMod = {
        //    "ProId": "1",
        //    "ProName": "商品名称",
        //    //"goodsIntroduction": "商品描述商品描述",
        //    "ProPictureUrl": "",
        //    "ProSalePrice": "99",
        //    "ProRetailPrice": "199",
        //    "ProUrl": ""
        //};
        this.goodsViewsTpl = '<a href="ProUrl" class="product" id="ProId"><div class="item">' +
            '<div class="item-inner">' +
            '<span class="item-img">' +
            '<img src="ProPictureUrl" alt=""/>' +
            '<span class="item-larger-minimalist-show item-fields minimalist">' +
            '<b>' +
            '<span class="limit-length item-fields minimalist-showGoodsName-fields">ProName</span>' +
            '</b>' +
            '<b><span class="item-fields minimalist-showGoodsPrice-fields">￥ProSalePrice</span></b>' +
            '</span>' +
            '</span>' +
            '<span class="item-title wrap-fill-left item-fields showGoodsName-fields">ProName</span>' +
                //'<span class="item-showGoodsDescription wrap-fill-left item-fields showGoodsDescription-fields">此处显示商品描述</span>' +
            '<span class="item-price wrap-fill-left item-fields showGoodsPrice-fields">￥ProSalePrice <span class="item-original-price-add1">￥ProRetailPrice</span></span>' +
            '<span class="item-original-price wrap-fill-left item-fields originalPrice-fields">原价 ProRetailPrice</span>' +
            '<span class="item-buy item-fields showBuyBtn-fields">加入购物车</span>' +
            '<span class="item-snap-buy item-fields snapUp-fields">我要抢购</span>' +
            '</div>' +
            '</div></a>';
        this.goodCtlTpl = '<span class="imgNode"><img src="ProPictureUrl" alt=""/><i class="del">X</i></span>';
        this.goodsViews = $("<div></div>");
        this.goodCtls = $("<div class='goodsCtlWarp'></div>");
        this.init();
    }
    chooseGoods.prototype = {
        init: function () {
            //console.log(this._arg);
            //this._ipt.data("args", this._arg)
            //this.showHtml();
            //this.bindEvent();

            var goods = this._arg["goods"];
            if (goods.length > 0) {
                this._ipt.data("args", this._arg["goods"])
                this.showHtml();
            }
            this.bindEvent();
            //this.setAjaxData();
        },
        bindEvent: function () {
            var me = this;
            var dels = this.goodCtls.find(".del");
            //me._ipt.data("args", me._arg);
            me._ipt.data("args", me._arg["goods"]);
            me._ipt.data("proid", me._arg["proid"]);
            me._ipt.data("brandid", me._arg["brandid"]);

            console.log("xxxx")
            console.log(me._arg);

            me._ipt.change(function () {
                me.generateHtml();
                me.appendHtml();
                me._arg["goods"] = me._ipt.data("args");

                //

                me._arg["proid"] = me._ipt.data("proid");
                me._arg["brandid"] = me._ipt.data("brandid");

                var cid = $(this).closest(".list").children().attr("id").split("_")[1];
                var goodsViewsWarp = $("#v_" + cid).find(".proinfo");
                goodsViewsWarp.addClass("hello").attr("data-proid", JSON.stringify(me._ipt.data("proid"))).attr("data-brandid", me._ipt.data("brandid"));

                //me.setAjaxData();

            });
            this.goodCtls.on("click", ".del", function () {
                var _this = $(this),
                    idx = _this.parents(".imgNode").index();
                //var nargs = me._arg["goods"].splice((idx - 1), idx);
                me._arg["goods"].splice(idx, 1);
                //me.showHtml();
                //me._ipt.data("args", me._arg).change()
                me._arg["proid"].splice(idx, 1);

                me._ipt.data("args", me._arg["goods"]).attr("data-proid", me._arg["proid"]).change();
            })
        },
        generateHtml: function () {
            var
                goods = this._ipt.data("args"),
                sgvt = this.goodsViewsTpl,
                sgct = this.goodCtlTpl;
            var sgcts = "",
                sgvts = "";
            for (var i = 0; i < goods.length; i++) {
                var s1 = sgvt,
                    s2 = sgct;
                $.each(goods[i], function (k, v) {
                    //console.log(k)
                    var pattern1 = new RegExp("\\" + k + "", "g");
                    s1 = s1.replace(pattern1, v);
                    s2 = s2.replace(pattern1, v);
                })
                sgcts += s2;
                sgvts += s1;
            }
            this.goodsViews.html(sgvts);
            this.goodCtls.html(sgcts);
        },
        appendHtml: function () {
            //console.log("--------------20151204 change 显示默认状态值----------------");
            var h = this.goodsViews.html()
            this._ipt.val(h.length > 10 ? h : "");
            //this._ipt.val(this.goodsViews.html());
            this._ipt.after(this.goodCtls);
        },
        appendGoods: function () {
        },
        showHtml: function () {
            this.generateHtml();
            this.appendHtml();
        },
        setAjaxData: function () {
            var ipt = this._ipt;

            console.log(ipt);

            var cid = ipt.closest(".list").children().attr("id").split("_")[1];
            var goodsCtlWarp = ipt.next().data("ajaxguid", cid).addClass("goodsCtlWarp");
            var goodsViewsWarp = $("#v_" + cid).data("ajaxguid", cid).addClass("goodsViewsWarp");

            goodsCtlWarp.data("proid", me._ipt.data("proid")).data("brandid", me._ipt.data("brandid"));
            goodsViewsWarp.data("proid", me._ipt.data("proid")).data("brandid", me._ipt.data("brandid"));
        }
    }

    function GoodsGroup(ipt, arg, cb) {

        this._ipt = ipt;
        this._iptId = ipt.attr("id");
        this._arg = arg;

        this.handleWarp = $("<span class='goodsGroupViews'><span class='goodsGroupCot' style='display:none'></span><a href='javascript:;' class='goodsGroupHandle addprogroup'>选择商品分组</a></span>");

        this.handleWarpCot = this.handleWarp.find(".goodsGroupCot");
        this.handleWarpHandle = this.handleWarp.find(".goodsGroupHandle");

        this.init();

    }
    GoodsGroup.prototype = {

        init: function () {

            var o = this._arg["goodsgroup"];

            if (o.length > 0) {
                this.iptHandle(o);
            }

            this._ipt.after(this.handleWarp);

            this.bindEvent();
        },
        bindEvent: function () {
            var me = this;
            this._ipt.change(function () {

                var o = $(this).val();
                me._arg["goodsgroup"] = o;

                me.iptHandle(o);
            })
        },
        iptHandle: function (o) {
            var o = JSON.parse(o);
            var o = o;
            var text = o["text"];
            if (text) {
                this.handleWarpCot.css("display", "inline-block").html("商品标签 | " + text);
                this.handleWarpHandle.html("修改");
            } else {
                this.handleWarpCot.css("display", "none").html("");
                this.handleWarpHandle.html("选择商品分组");
            }
        }

    }

    //优惠券
    function Coupons(ipt, arg, cb) {
        console.log("Coupons");
        this.ipt = ipt;
        this.arg = arg;

        this.couponsWarp = this.ipt.siblings(".couponslistwarp");
        this.add = this.ipt.siblings(".addcoupon");
        this.init();
    }
    Coupons.prototype = {
        init: function () {
            this.showCoupons();
            this.bindEvent();
        },
        bindEvent: function () {
            var self = this;
            this.ipt.change(function () {
                var v = $(this).val(),
                    d = JSON.parse(v);
                self.arg["coupons"] = v;
                self.showCoupons(d);
            })
            this.couponsWarp.on("click", ".del", function () {
                var cur = $(this).parents(".list"),
                    curIdx = cur.index();
                var d = JSON.parse(self.arg["coupons"]);
                d.splice(curIdx, 1);
                console.log(d);
                self.ipt.val(JSON.stringify(d)).change();
                $(this).parents(".list").remove();
            })
        },
        showCoupons: function (d) {
            if (d && d.length > 0 || this.arg["coupons"]) {
                var d = d ? d : JSON.parse(this.arg["coupons"]);
                var s = "";
                for (i = 0; i < d.length; i++) {
                    var sn = "<span class='list'>";
                    $.each(d[i], function (k, v) {
                        if (k !== "url") {
                            sn += "<span class='" + k + "'>" + v + "</span>";
                        }
                    })
                    sn += "<span class='del'>删除</span></span>";
                    s += sn;
                }
                this.couponsWarp.html(s);
                this.showAdd();
            }
        },
        showAdd: function () {
            var d = JSON.parse(this.arg["coupons"]);
            if (d.length >= 3) {
                this.add.css("display", "none");
            } else {
                this.add.css("display", "block");
            }
        }
    }

    var o = {};
    //公用
    o.setObjVal = setObjVal;
    o.idxObj = idxObj;

    o.cloneObj = cloneObj;

    o.getObjName = getObjName;
    //计算对象长度
    o.objLg = objLg;
    //输出展示内容
    o.showTgt = showTgt;
    o.modSort = modSort;
    o.domSort = domSort;
    o.rad = rad;
    o.ckbox = ckbox;
    o.slt = slt;
    o.sld = sld;
    o.pop = pop;

    o.rado = rado;

    //20150916
    o.VerifyCollection = VerifyCollection;
    o.ShowNodeImg = ShowNodeImg;
    o.RangeSlider = RangeSlider;

    //2015-11-11 add
    o.ListStyle = ListStyle;
    //20151028
    o.addAdvertisingStyle = addAdvertisingStyle;
    o.CompRadio = CompRadio;
    o.chooseGoods = chooseGoods;
    o.GoodsGroup = GoodsGroup;

    o.Coupons = Coupons;


    return o;

})()