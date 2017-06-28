var umEditor;

function Model(m, guid, nm) {
    this.nm = nm;
    this.m = nm ? nm : {};
    this._m = m;
    this._guid = guid;
    this.addItem = {};
    this.init();
}

Model.prototype = {
    init: function () {
        if (!this.nm) {
            this.gMod();
        }
    },
    gMod: function () {
        var that = this,
            _m = that._m,
            guid = this._guid;
        $.each(_m, function (k, v) {
            if (v["isMult"] && v["isMult"] === true) {
                var a = [],
                    _guid = that._guid,
                    oldCot = v["cot"];
                for (var i = 0; i < oldCot.length; i++) {
                    var cguid = (new Date()).getTime() + i,
                        o = {};
                    o[_guid + "-" + cguid] = oldCot[i];
                    a.push(o);
                }
                v["cot"] = a;
            }
        });
        this.m[guid] = this._m;
    },
    add: function (field, idx) {
        var guid = this._guid;
        var ModTpl = this.m[guid][field]["ModTpl"];
        var node = JSON.stringify(ModTpl);
        var node = JSON.parse(node),
            guid = this._guid,
            listNode = this.m[guid][field]["cot"],
            cguid = (new Date()).getTime();
        var o = {};
        o[guid + "-" + cguid] = node;
        if (idx || idx === 0) {
            listNode.splice((idx + 1), 0, o);
        } else {
            listNode.push(o);
        }
        this.addItem = o;
    },
    del: function (idx) {
    }
}

function Views(m, guid, tgt) {
    var guid = guid,
        tgt = tgt;
    m = m;

    this._m = m[guid];
    this._guid = guid;
    this.tpl = m[guid]["tpl"];
    var tpl = this.tpl;

    //this.vTpl = $("#v-" + guid).length > 0 ? $("#v-" + guid) : $(".tpl[data-tpl='" + tpl + "'][data-type='views']").clone().removeClass("tpl").addClass("nodeItem").attr("id", ("v-" + guid));
    //this.cTpl = $(".tpl[data-tpl='" + tpl + "'][data-type='controller']").clone().removeClass("tpl").addClass("nodeItem").attr("id", ("c-" + guid));

    ////this.vTgt = (tgt && tgt.length) > 0 ? $(tgt[0]).find(tgt[2]) : $("#viewsTgt");
    ////this.cTgt = (tgt && tgt.length) > 0 ? $(tgt[1]).find(tgt[2]) : $("#controllerTgt");

    ////插入对象节点
    //var tgt = m[guid]["list"]["tgt"] ? m[guid]["list"]["tgt"] : "";
    //this.vTgt = (tgt && tgt.length) > 0 ? $(tgt[0]).find(tgt[2]) : $("#viewsTgt");
    //this.cTgt = (tgt && tgt.length) > 0 ? $(tgt[1]).find(tgt[2]) : $("#controllerTgt");

    if (m[guid]["list"]["global"] && m[guid]["list"]["global"] === true) {
        this.vTpl = $(".phoneViewsInner").attr("id", ("v-" + guid));
    } else {
        this.vTpl = $("#v-" + guid).length > 0 ? $("#v-" + guid) : $(".tpl[data-tpl='" + tpl + "'][data-type='views']").clone().removeClass("tpl").addClass("nodeItem").attr("id", ("v-" + guid));
    }
    this.cTpl = $(".tpl[data-tpl='" + tpl + "'][data-type='controller']").clone().removeClass("tpl").addClass("nodeItem").attr("id", ("c-" + guid));
    //this.vTgt = (tgt && tgt.length) > 0 ? $(tgt[0]).find(tgt[2]) : $("#viewsTgt");
    //this.cTgt = (tgt && tgt.length) > 0 ? $(tgt[1]).find(tgt[2]) : $("#controllerTgt");
    //插入对象节点
    var tgt = m[guid]["list"]["tgt"] ? m[guid]["list"]["tgt"] : "";
    this.vTgt = (tgt && tgt.length) > 0 ? $(tgt[0]).find(tgt[2]) : $("#viewsTgt");
    this.cTgt = (tgt && tgt.length) > 0 ? $(tgt[1]).find(tgt[2]) : $("#controllerTgt");

    //区分对象操作节点

    this.vTpl.find(".jsHandle").addClass("jsHandle-" + guid);
    this.cTpl.find(".jsHandle").addClass("jsHandle-" + guid);

    this.init();

    this.tgt = tgt;

    //显示端控制点
    //编辑，添加节点，向上，向下
    this.viewsHandel = this.vTpl.find(".handel");
    this.viewsEditNode = this.vTpl.find(".editNode");
    this.viewsAddNode = this.vTpl.find(".addNode");
    this.viewsdeleteNode = this.vTpl.find(".deleteNode");
    this.viewsGoUP = this.vTpl.find(".goUP");
    this.viewsGoDown = this.vTpl.find(".goDown");
    //控制端显示点
    //添加，删除，向上，向下
    this.conAddItem = this.cTpl.find(".addItem");
    this.condeleteItem = this.cTpl.find(".deleteItem");
    this.viewsGoUP = this.cTpl.find(".goUP");
    this.viewsGoDown = this.cTpl.find(".goDown");

}

Views.prototype = {
    init: function () {
        this.showDom();
        if (!$("#v-" + this._guid).length > 0) {
            this.vTgt.append(this.vTpl);
        }
        this.cTgt.append(this.cTpl);

        //加一层
        //this.vTgt.append('<div class="wclear"></div>');
        //this.cTgt.append('<div class="wclear"></div>');
        if (this.tgt) {
            this.cTgt.css("height", 700);
        }

        this.showCurrent(this._guid);
    },
    //t 判断是 v 或 c
    showNode: function (tpl, v, guid, t) {
        var that = this,
            t = t;
        $.each(v, function (k1, v1) {
            var ctpl = tpl.find("[data-field-key='" + k1 + "']"),
                arg = ctpl.data("field-show");
            ctpl.data("guid", guid);

            //20151023 区别嵌套内组件
            //ctpl.attr("id",(t + "_" + guid + "_" + k1) );
            ctpl.attr("id", (t + "_" + guid + "_" + k1)).addClass("comp_" + that._guid);

            var v1 = v1,
                k1 = k1,
                vv = v1["v"];
            if (t === "c") {
                var handle = v1["handle"];
                if (handle) {
                    var handleName = v1["handle"]["name"],
                        handleArg = v1["handle"]["arg"];

                    //20150917
                    //Array
                    if (Object.prototype.toString.call(handleName) === '[object Array]') {
                        for (var i = 0; i < handleName.length; i++) {
                            if (util[handleName[i]]) {
                                that[k1 + i + "-handle"] = new util[handleName[i]](ctpl, handleArg)
                            }
                        }
                    } else {
                        if (util[handleName]) {
                            that[k1 + "-handle"] = new util[handleName](ctpl, handleArg)
                        }
                    }
                }
            }
            util.showTgt(ctpl, arg, vv);
        });
    },
    showFieldNode: function (tpl, vTpl, cTpl, k, o, idx) {
        var that = this;
        wctpl = cTpl.find("." + k),
            wvtpl = vTpl.find("." + k),
            ntpl = tpl + "-" + k,
            _idx = idx;
        $.each(o, function (k1, v1) {
            var nvTpl = $(".tpl[data-tpl='" + ntpl + "'][data-type='views']").clone().removeClass("tpl"),
                ncTpl = $(".tpl[data-tpl='" + ntpl + "'][data-type='controller']").clone().removeClass("tpl"),
                idx = (new Date()).getTime();
            that.showNode(ncTpl, v1, k1, "c");
            that.showNode(nvTpl, v1, k1, "v");
            if (_idx || _idx === 0) {
                wctpl.children().eq(_idx).after(ncTpl);
                wvtpl.children().eq(_idx).after(nvTpl)
            } else {
                wctpl.append(ncTpl);
                wvtpl.append(nvTpl);
            }


            ncTpl.addClass("c_" + ntpl);
            ncTpl.attr("id", ("c_" + ntpl + "-" + idx));
            nvTpl.addClass("v_" + ntpl);
            nvTpl.attr("id", ("v_" + ntpl + "-" + idx));


            //20151027
            //区分组件
            ncTpl.find(".jsHandle").addClass("jsHandle-" + that._guid);
            nvTpl.find(".jsHandle").addClass("jsHandle-" + that._guid);
            ///////////////////////////////////////////////////////////////

        })
    },
    showDom: function () {
        var that = this,
            m = that._m,
            guid = that._guid,
            tpl = that.tpl,
            vTpl = that.vTpl,
            cTpl = that.cTpl;
        $.each(m, function (k, v) {
            if (typeof v === "object") {
                var a = v["cot"],
                    k = k;
                for (var i = 0; i < a.length; i++) {
                    var o = a[i];
                    that.showFieldNode(tpl, vTpl, cTpl, k, o);
                }
            }
        })
    },
    add: function (field, idx, o) {
        var that = this,
            tpl = that.tpl,
            vTpl = that.vTpl,
            cTpl = that.cTpl,
            k = field;
        var o = o;
        that.showFieldNode(tpl, vTpl, cTpl, k, o, idx);
    },
    del: function (idx) {
    },
    showCurrent: function (guid, b) {
        var guid = guid;
        var c = $("#controllerTgt"),
            v = $("#viewsTgt");
        c.children().removeClass("cur");
        v.children().removeClass("cur");
        if (guid) {
            $("#v-" + guid).addClass("cur");
            $("#c-" + guid).addClass("cur");
        } else {
            c.children().first().addClass("cur");
            v.children().first().addClass("cur");
        }
        var st = v.children(".cur").offset(),
        //top = st.top,
            top = st ? st.top : 0;
        c.children(".cur").css("margin-top", (top - 170) + "px");
        //c.children(".cur").css("top", top + "px");
        //添加富文本编辑器
        //2015.11.12
        var richTextIpt = $(".cur").find(".richTextIpt");
        if (richTextIpt.length > 0) {

            richTextIpt.after($("#myContentEditor"));
            if (umEditor) {
                umEditor.reset();
                setTimeout(function () {
                    umEditor.setContent(richTextIpt.val());
                }, 200);
            }
        }

    }
}

function Controller(m, guid, max, nm, tgt) {
    this._guid = guid;
    this._m = m;
    this._max = max;
    this._nm = nm;
    this.Model = new Model(m, guid, nm, tgt);
    var vm = this.Model.m;
    this.Views = new Views(vm, guid, tgt);
    this.callback = {};
    this.init();
}

Controller.prototype = {
    init: function () {
        this.bindEvent();
        this.handleShow("vShow");
        this.handleShow();
    },
    bindEvent: function () {
        var that = this;
        var guid = this._guid;
        //20151023 区分嵌套组件
        //this.Views.cTpl.on("change", ".iData", function(){
        this.Views.cTpl.on("change", (".iData.comp_" + guid), function () {
            var _$this = $(this),
                v = _$this.val(),
                gid = _$this.attr("id").split("_"),
                guid = gid[2] ? (gid[1] + "_" + gid[2]) : gid[1],
                field = _$this.data("field-key"),
                d = $("#v_" + guid),
                arg = d.data("field-show");
            util.showTgt(d, arg, v);
            var pid = gid[1].split("-")[0],
                cid = gid[1];
            var a = util.setObjVal(that.Model.m[pid]["list"]["cot"], cid);
            a[gid[2]]["v"] = v;
        })
        //显示层
        this.Views.vTpl.on("click", ".editNode", function () {
        })
        this.Views.vTpl.on("click", ".addNode", function () {
            alert("添加内容")
        })
        this.Views.vTpl.on("click", (".deleteNode"), function (e) {
            //e.stopPropagation();
            $(".wPopup").remove();
            $(".triggerwPopup").removeClass("triggerwPopup");
            $(this).addClass("triggerwPopup");

            var _$this = $(this);
            var fn1 = function (value) {
                that.isDeleteNode = true;
            }
            var fn2 = function (value) {
            }
            var o = {
                tger: _$this,
                inner: "确定要删除吗？",
                value: ["externalLinks", "ret"],
                determineFn: fn1,
                cancelFn: fn2
            }
            if (that.isDeleteNode && that.isDeleteNode === true) {
                var _$this = $(this),
                    item = _$this.parents(".nodeItem"),
                    gId = item.attr("id").split("-")[1],
                    _$thispb = item.prev().length > 0 ? item.prev().attr("id").split("-")[1] : null,
                    _$thispn = item.next().length > 0 ? item.next().attr("id").split("-")[1] : null,
                    guid = _$thispb ? _$thispb : (_$thispn ? _$thispn : null);
                var b = guid;
                that.Views.showCurrent(guid, b);
                item.remove();
                $("#c-" + gId).remove();
                var idx = util.idxObj(M, gId);
                M.splice(idx, 1);
                that.isDeleteNode = false;
            } else {
                that.delPop = that.delPop ? that.delPop.init() : new util.pop(o);
            }
        })
        this.Views.vTpl.on("click", (".goUP"), function () {
            var _$this = $(this),
                item = _$this.parents(".nodeItem"),
                idx = item.index();
            util.domSort(item, -1);
            util.modSort(M, idx, -1);
            that.handleShow("vShow");
        })
        this.Views.vTpl.on("click", (".goDown"), function () {
            var _$this = $(this),
                item = _$this.parents(".nodeItem"),
                idx = item.index();
            util.domSort(item, 1);
            util.modSort(M, idx, 1);
            that.handleShow("vShow");
        })
        this.Views.cTpl.on("click", (".addItem"), function () {
            var _$this = $(this),
                field = _$this.parents(".list-warp").find(".list").children().eq(0).attr("id").split("_")[1].split("-")[1];
            that.add(field);
            if (that.callback["bindEvent"] && that.callback["bindEvent"]["addItem"]) {
                that.callback["bindEvent"]["addItem"](_$this);
            }
            that.handleShow();
        })
        this.Views.cTpl.on("click", (".insertItem"), function () {
            var _$this = $(this),
                item = _$this.parents(".cItemsList"),
                field = _$this.parents(".cItemsList").attr("id").split("_")[1].split("-")[1],
                d = _$this.parents(".nodeItem").find(".addItem.btn"),
                idx = item.index();
            that.add(field, idx);
            if (that.callback["bindEvent"] && that.callback["bindEvent"]["addItem"]) {
                that.callback["bindEvent"]["addItem"](d);
            }
            that.handleShow();
        })
        this.Views.cTpl.on("click", (".deleteItem"), function () {
            var _$this = $(this),
                item = _$this.parents(".cItemsList"),
                d = _$this.parents(".nodeItem").find(".addItem.btn");
            that.cControl(item);
            if (that.callback["bindEvent"] && that.callback["bindEvent"]["deleteItem"]) {
                that.callback["bindEvent"]["deleteItem"](d);
            }
            that.handleShow();
        })
        this.Views.cTpl.on("click", (".goUP"), function () {
            var _$this = $(this),
                item = _$this.parents(".cItemsList");
            that.cControl(item, -1);
            that.handleShow();
        })
        this.Views.cTpl.on("click", (".goDown2"), function () {
            var _$this = $(this),
                item = _$this.parents(".cItemsList");
            that.cControl(item, 1);
            that.handleShow();
        })
        //点击显示编辑
        this.Views.vTpl.on("click", ".handel", function (e) {

            e.preventDefault();
            e.stopPropagation();
            that.Views.showCurrent(that._guid);

        })
    },
    handleShow: function (vShow) {
        var controlNode = vShow ? this.Views.vTpl.parent().find(".nodeItem") : this.Views.cTpl.find(".cItemsList"),
            controlHandel = vShow ? this.Views.vTpl.parent().find(".handel") : this.Views.cTpl.find(".handel"),
            controlNodeFirst = vShow ? controlNode.eq(1) : controlNode.first(),
            controlNodeLast = controlNode.last();
        controlHandel.find(".wdn").removeClass("wdn");
        controlNodeFirst.find(".deleteItem").addClass("wdn");
        controlNodeFirst.find(".goUP").addClass("wdn");
        controlNodeLast.find(".goDown").addClass("wdn");
        if (controlNode.length === (vShow ? 2 : 1)) {
            controlNodeFirst.find(".deleteItem").addClass("wdn");
        } else {
            controlNodeFirst.find(".deleteItem").removeClass("wdn");
        }
        //限制个数
        if (this._max && controlNode.length === this._max) {
            controlHandel.find(".insertItem").addClass("wdn");
        } else {
            controlHandel.find(".insertItem").removeClass("wdn");
        }
    },
    add: function (field, idx) {
        this.Model.add(field, idx);
        var o = this.Model.addItem;
        this.Views.add(field, idx, o);
    },
    cControl: function (item, g) {
        var that = this;
        idx = item.index(),
            gId = item.attr("id").split("_"),
            vId = "v_" + gId[1],
            guid = that._guid,
            field = gId[1].split("-")[1],
            vItem = $("#" + vId),
            a = that.Model.m[guid][field]["cot"];
        if (g) {
            util.domSort(vItem, g)
            util.domSort(item, g)
        } else {
            item.remove();
            vItem.remove();
        }
        util.modSort(a, idx, g);
    }
}