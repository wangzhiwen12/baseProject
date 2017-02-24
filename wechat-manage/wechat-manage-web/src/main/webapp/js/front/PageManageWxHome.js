//基础路径配置
var baseUrl = "wechatpage/";
var curPicSel;
//ajax调后台获取收据的封装
function cfgModel(data) {
    var self = this;
    self.Id = data.Id;//系统编号
    self.LogoFullUrl = data.LogoFullUrl;//页面地址
    self.WxHeadHtml = data.WxHeadHtml;//微信头部动态html内容
    self.WxHeadHtmlM = data.WxHeadHtmlM;//微信头部动态html对象, 无内容
    self.WxHomeHtmlM = data.WxHomeHtmlM;//微信头部动态html对象
    self.IsNew = data.IsNew;
}
function imageSwitch(media){
	curPicSel.closest(".selectPicWrap").find("input.picIpt").val(media.localUrl).change();
}
// 20160906 点击新增
var byId = function (id) { return document.getElementById(id); }
//拖拽
function addSortable(){
    var sortable = Sortable.create(byId('P'), {
        draggable: ".common-drag",
        onStart: function (/**Event*/evt) {
            evt.oldIndex;  // element index within parent
            $(evt.item).siblings().removeClass('cur');
            $(evt.item).addClass('cur');
        },
        onEnd: function (/**Event*/evt) {
            sortCommon();
        },
        handle: '.handle'
    });
}

//返回 keys
function keys(obj){
    var a = [];
    for( key in obj ){
        key.push(a);
    }
    return a;
}

//new
var _data = {};
function adjustIdx(a, idx, tgt){
    a.splice(tgt, 0, a.splice(idx, 1)[0]);
    return a;
}

//组件 排序后 => data
function sortCommon(){
    var doms = $('.common');
    //		var ls = JSON.parse(localStorage.getItem('way'));
    var ls = scope.data;
    var o = {};
    var a = [];
    doms.each(function(){
        a.push($(this).attr("id"));
    });

    for(var i = 0; i < a.length; i++){
        var item = ls[a[i]];
        o[a[i]] = item;
    }

    scope.data = o;
    _M['actual'] = scope.data;
    $('pre').html(JSON.stringify(_M, undefined, 2));
    return JSON.stringify(_M);
}

function addDrag(){

    $('#P').on('mousedown', '.repeat', function(){
            var idx = $(this).index();
            $(this).data("oldindex", idx);
    });

    [].forEach.call(byId('P').getElementsByClassName('repeat-wrapper'), function (el){
        Sortable.create(el, {
            group: 'photo',
            animation: 150,
            onStart: function (/**Event*/evt) {
                // evt.oldIndex = $(evt.item).index();  // element index within parent
                // $(evt.item).data("oldindex", evt.oldIndex);
                evt.oldIndex = $(evt.item).data("oldindex");
            },
            onEnd: function (/**Event*/evt) {
                var _scope = $(evt.target).data('repeat');
                idx = $(evt.item).data("oldindex"),
                    //tgt = evt.newIndex,
                    tgt = $(evt.item).index();
                scope.sort( _scope, idx, tgt );
                $('pre').html(JSON.stringify(scope.data, undefined, 2));
            }
        });
    });
}

//添加模块
function creatCommon(temp, data, insert){
    var data = data,
        obj = {};
    var timestamp = new Date().getTime();
    var html = template(temp, {'temp':timestamp});
    var cur = $('.common.cur');

    //插入当前模块
    $('.common').removeClass('cur');
    if(insert){
        cur.after(html);
        $('.common').eq(insert).addClass('cur');
        $('.insertCommon').addClass('wdn');
    }else{
        $('#P').append(html);
        $('.common:last').addClass('cur');
    }

    if( data ){
        //scope.set((temp + '_' + timestamp), data);
        data = obj[(temp + '_' + timestamp)] = _data[(temp + '_' + timestamp)] = scope.data[(temp + '_' + timestamp)] = data;
        scope.eles(obj);
    }

    scope.extendDom();
    //写入拖拽
    addDrag()
    $('[data-toggle="tooltip"]').tooltip();
    //sortCommon();
}

M = {},
_M = {};
/**
 * 通过后台获取配置数据，利用前台模板引擎加载页面
 */
function loadCfgData() {
	$.post(baseUrl + "getMemberHomeInfo.json",
    function (data) {		
		var data  = data.data;
		cfg = new cfgModel(data);
        if (data && data.WxHomeHtmlM) {
            M = JSON.parse(cfg.WxHomeHtmlM);
        }
        var oldData;
        if(M === undefined || Object.keys(M).length === 0 ){
            M = _M['actual'] = {
                "comheader_default": {
                    "title": "页面名称",
                    "cardno": "{会员卡号}",
                   "img": data.LogoFullUrl,
                    "template": "head-style-1"
                }
            }
        }else{
            //备份一下旧数据
            // 处理数据
            if(!M['actual']){
                _M['backup'] = M;
                oldData = true;
                var t = _disposeOldData(M);
                var _timer;
                ;(function count() {
                    if ( t.counting ) {
                        _timer = setTimeout(function () { count() }, 7)
                    }
                    else {
                        M = _M['actual'] = t.items;
                        //数据默认没选头部模板
                        if(M['comheader_default']['template'] === undefined || M['comheader_default']['template'] === 'changeTpl'){
                            M['comheader_default']['template'] = 'head-style-1';
                        }
                        console.log(M);
                        fn();
                        clearTimeout(_timer);
                    }
                })();
            }else{
                _M = M;
                M = M['actual'];
            }
        }

        // 临时加
        if( M['comheader_default'] === undefined ){
            M['comheader_default'] = {
                "title": "页面名称",
                "cardno": "{会员卡号}",
                "img": data.LogoFullUrl,
                "template": "head-style-1"
            }
        }

        function fn(){
            var tpls = [];
            for(key in M){
                tpls.push(key);
            }
            for( var i = 0; i < tpls.length; i++ ){
                (function(i){
                    var a = tpls[i].split('_');
                    // console.log(a[0]);
                    var html = template(a[0], {'temp':a[1]});
                    // console.log(html);
                    $('#P').append(html);
                })(i)
            }
            $('.common:first').addClass('cur');
            // 初始化 scope
            scope.data = M;
            scope.clones = [];
            scope.warps = [];

            scope.eles();
            scope.tgt = $('#P');
            //扩展方法
            scope.template = template;
            scope._Sortable = Sortable;
            scope.bindEvent();
            scope.extendDom();

            //添加拖拽
            addSortable();
            addDrag();

            //bootstrap tooltip
            $('[data-toggle="tooltip"]').tooltip();
            // 设定相关参数
            $("#pageurl").text(data.PageUrl);
            //品牌 logo
            $(".BrandLogo").attr("src", data.LogoFullUrl);
            $(".BrandName").val(data.BrandName).change();
            //20160325 wszl
            $(".ctl-wszl").each(function () {
                var _$this = $(this);
                var vid = _$this.attr("id").split("_");
                vid.splice(0, 1, "v");
                $("#" + vid.join("_")).parent().addClass("wszlitem");
            })
        }
        if(oldData === undefined ){
            fn();
        }
    },"json");
}
loadCfgData();


$('.add-common').click(function(){
    var _$this = $(this),
        data = _$this.data('type'),
        insert = _$this.data('insert'),
        insert = insert ? $('.common.cur').index() + 1 : 0;

    switch(data){

        case 'textnavigation':
            var temp = 'text-navigation',
                data = {
                    "list": [
                        {}
                    ]
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'imgnavigation':
            var temp = 'img-navigation',
                data = {list:[
                    {},
                    {},
                    {},
                    {}
                ]},
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'titlenavigation':
            var temp = 'title-navigation',
                data = {'list':[{}]},
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'mallorders':
            var temp = 'mall-orders',
                data = {
                    'title':'mall-orders'
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'header':
            var temp = 'header',
                data = {
                    template: "head-style-1"
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'uxiliaryblank':
            var temp = 'uxiliary-blank',
                data = {
                    'height':'32'
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'picturenavigation':
            var temp = 'picture-navigation',
                data = {'list':[{}]},
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'showcase':
            var temp = 'show-case',
                data = {
                    style:"normal clearfix",
                    list:[
                        {
                            "link": "#",
                            "title": "美妆"
                        },
                        {
                            "link": "#",
                            "title": "进口食品"
                        },
                        {
                            "link": "#",
                            "title": "护肤"
                        }
                    ]},
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'mallindex':
            var temp = 'mall-index',
                data = {
                    title:"进入店铺"
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'notice':
            var temp = 'notice',
                data = {
                    "title":"标题"
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'search':
            var temp = 'search',
                data = {
                    "title":"商品搜索"
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'richtext':
            var temp = 'rich-text',
                data = {
                    "content":"富文本内容"
                },
                insert = insert
            creatCommon(temp, data, insert)
            break;

        case 'swiper':
            var temp = 'swiper',
                data = {
                    mode: "notSwiper",
                    list:[
                        {
                            "link": "#",
                            "title": "美妆"
                        },
                        {
                            "link": "#",
                            "title": "进口食品"
                        },
                        {
                            "link": "#",
                            "title": "护肤"
                        },
                        {
                            "link": "#",
                            "title": "家具"
                        }
                    ]},
                insert = insert
            creatCommon(temp, data, insert)
            break;

    }
})

 //loaded
            $(function(){
            	
            	 $('.dropdown-toggle').dropdown();
                //input
                $('#p-data').on('mousedown', 'input', function(){
                    console.log('input');
                    $(this).closest('.repeat').attr("draggable", false);
                });

                $('body').on('click', '.common',function(){
                    $('.common').removeClass('cur');
                    $(this).addClass('cur');
                });

                //插入
                $('#p-data').on('click', '.insert-common',function(e){
                    e.stopPropagation();
                    $('.common').removeClass('cur');
                    $(this).closest('.common').addClass('cur');
                    var top = $(e.target).offset().top -130;
                    left = $(e.target).offset().left - 130;
                    $('.insertCommon').removeClass('wdn').css({'left':left, 'top':top});
                });

                //删除
                $('#p-data').on('click', '._del',function(e){
                    e.stopPropagation();
                    var _scope = $(this).data('scope');
                    delete scope.data[_scope];
                    $('.common').removeClass('cur');
                    var common = $(this).closest('.common');
                    if(common.next('.common').length){
                        common.next('.common').addClass('cur');
                    }else{
                        common.prev('.common').addClass('cur');
                    }
                    $('pre').html(JSON.stringify(scope.data, undefined, 2));
                    common.remove();
                });
            });

            //去弹窗
            var domHasClass = function (element, cls) {
                return (' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
            };

            function isClickPop(dm) {
                var ret = false;
                while (dm.parentNode) {
                    if (domHasClass(dm, "wPopup")) {
                        ret = true;
                        break;
                    }
                    dm = dm.parentNode;
                }
                return ret;
            }

            $("#controllerTgt").on("click", function (e) {
                var dm = e.target;
                if (!domHasClass(dm, "triggerwPopup") && $(".wPopup").length > 0 && !isClickPop(dm)) {
                    $(".wPopup").remove();
                }
            });

            $('body').on('click', function(){
                if($('.insertCommon:visible').length > 0 ){
                    $('.insertCommon').addClass('wdn');
                }
            });

            //去弹窗 end

            $(".yl").click(function () {
                var ret = document.getElementById("viewsTgt").innerHTML;
                document.getElementById("Ret").value = ret;
            })
            $(".medit").click(function () {
                var sm = JSON.stringify(M)
                var ret = document.getElementById("viewsTgt").innerHTML;
                document.getElementById("Ret0").value = ret;
                document.getElementById("edit").value = sm;
            });
            $("#P").on('click', '.select-pic', function(){
                /*var _$this = $(this);
                new CusPicture(WebUploader, selectPicCallback, 1,'crm','api/crm');
                function selectPicCallback(data) {
                    if (data.length > 0) {
                        _$this.closest(".selectPicWrap").find("input.picIpt").val(data[0].FullFilePath).change();
                    }
                }*/
            	curPicSel = $(this);
            	layer.open({
 					title : "图片素材",
 					type : 2,
 					area : [ "90%", "85%" ],
 					content : contextPath+'/materialLocal/imageList.shtml'
 				});

            });
            
            
            $("#save").click(function () {
                var yz = new util.VerifyCollection("", $("#controllerTgt"));
                yz.through = true;

                if (yz.gThrough()) {

                    //var headHtml = $("#viewsTgt").children().first().html();
                    var headHtml = $(".common.comheader").find(".views").html();

                    //var homeHtml = $("#home").html();
                    var commons = $('.common');
                    var html = "";
                    commons.each(function(){
                        var _this = $(this),
                            _html = _this.find('.views').html();
                        html += _html;
                    });

                    var homeHtml = html;
                    // var homeHtmlM = JSON.stringify(M);
                    //头部
                    cfg.WxHeadHtml = headHtml;
                    //微信 html
                    cfg.WxHomeHtml = homeHtml;
                    //配置页数据
                    //cfg.WxHomeHtmlM = homeHtmlM;
                    cfg.WxHomeHtmlM = sortCommon();
                    //新版页面
                    cfg.IsNew = true;
                    
                    //alert(JSON.stringify(cfg));
                    //M = null;
                    console.log(JSON.stringify(cfg));
                    $.post(
                    	"wechatpage/addMemberHome.shtml",
                    	JSON.stringify(cfg),
                    	function(data){
                    		if(data.success=="true"){
                    			layer.msg("发布成功!");
                                //msg.ok("发布成功！");
                    		}else{
                    			layer.msg("发布失败:" + data.data.errorMsg);
                    		}
                    	},"json"
                    );
                }else {
                    //显示报错项目
                    $(".nodeItem").removeClass("cur");
                    var cid = $(".msg.error").eq(0).parents(".nodeItem").attr("id").split("-");
                    $("#c-" + cid[1]).addClass("cur");
                    $("#v-" + cid[1]).addClass("cur");
                }

            });
