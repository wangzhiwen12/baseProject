var _disposeOldData = (function (root, factory) {
    if (typeof define === "function" && define.amd) {
        define(factory);
    } else if (typeof exports === "object") {
        module.exports = factory();
    } else {
        root._disposeOldData = factory();
    }
}(this, function(){

    var _items = {};
    var testRetObj = {
        "title": "title",
        "card": "card",
        "link":"link",
        "img1": "img1",
        "changeTpl": "style"
    }
    function getValue(obj, cloneRet, ret){
        $.each(obj, function(k, v){
            if( k == "tpl"){
                ret['tpl'] = v;
            }
            if( k == "cot" ){
                ret[ret['tpl'] + '_' + (new Date()).getTime()] = v;
                return false;
            }
            if( cloneRet[k] ){
                //ret[ cloneRet[k] ] = cloneRet[k] == 'link' ? v['handle']['arg'] : v['v'];
                if(cloneRet[k] == 'link' && v['handle']['arg']){
                    //閫夋嫨涓嬫媺鑿滃崟鍒濆
                    ret['linkname'] = v['handle']['arg'][0];
                    ret[ cloneRet[k] ] = v['v'];
                }else if( k === 'changehg'){
                    ret[ cloneRet[k] ] = parseInt(v['v']/2);
                }else{
                    ret[ cloneRet[k] ] = v['v'];
                }
            }
            if(Object.prototype.toString.call(v) === "[object Object]"){
                getValue(v, cloneRet, ret);
            }
        })
    }
    function getItems(obj, cloneRet, ret){

        if(Object.prototype.toString.call(ret) === "[object Array]"){
            var _ret = ret;
            var ret = {};
        }

        $.each(obj, function(k, v){
            if(cloneRet[k]){
                ret[cloneRet[k]] = v;
            }
            if(Object.prototype.toString.call(v) === "[object Object]"){
                getValue(v, cloneRet, ret);
            }
        })

        if(_ret){
            _ret.push(ret);
        }
    }
    function getItemsObj(data, cloneRet, ret, isMult){
        if(isMult){
            for( var i = 0; i < data.length; i++ ){
                getItems(data[i], cloneRet, ret);
            }
        }else{
            //getValue(data[0], testRetObj, a);
            getItems(data[0], cloneRet, ret)
        }
    }

    return function(data){
        var a = {};
        var timer;
        var counting;
        var length = lengthDb = data.length;
        var i = 0;
        ;(function count() {
            if ( i < length ) {
                _items.counting = true;
                getValue(data[i], testRetObj, a);
                timer = setTimeout(function () { count() }, 3);
            }
            else {
                _items.counting = false;
                clearTimeout(timer);
                var items = _items['items'] = {};
                $.each(a, function(k, v){
                    var _k = k;
                    var k = k.split('_')[0];
                    var temp = _k.split('_')[1];
                    var data = v;
                    switch(k){
                        case 'nodeHead':
                            var cloneRet = {
                                "template": "template",
                                "card": "cardno",
                                "title": "title",
                                "img1": "img",
                                "changeTpl": "template"
                            };
                            var _k = 'comheader_default';
                            items[_k] = {};
                            getItemsObj(data, cloneRet, items[_k], false);
                            break;
                        case 'nodeNavTextTpl':
                            var cloneRet = {
                                "title": "title",
                                "img": "picture",
                                "link": "link"
                            };
                            var _k = 'text-navigation_' + temp;
                            items[_k] = {};
                            items[_k]['list'] = [];
                            getItemsObj(data, cloneRet, items[_k]['list'], true);
                            break;
                        case 'nodeNavImgTpl':
                            var cloneRet = {
                                "title": "title",
                                "img": "picture",
                                "link": "link"
                            };
                            var _k = 'img-navigation_' + temp;
                            items[_k] = {};
                            items[_k]['list'] = [];
                            getItemsObj(data, cloneRet, items[_k]['list'], true);
                            break;
                        case 'nodePadding':
                            // var _k = 'uxiliary-blank_' + temp;
                            // items[_k] = {};
                            // items[_k]['height'] = '100';
                            // break;
                            var cloneRet = {
                                "changehg": "height"
                            };
                            var _k = 'uxiliary-blank_' + temp;
                            items[_k] = {};
                            getItemsObj(data, cloneRet, items[_k], false);
                            break;

                        case 'nodeNavTitleTpl':
                            var cloneRet = {
                                "title": "title"
                            };
                            var _k = 'title-navigation_' + temp;
                            items[_k] = {};
                            items[_k]['list'] = [];
                            getItemsObj(data, cloneRet, items[_k]['list'], true);
                            break;
                        case 'nodeImgsTpl':
                            var cloneRet = {
                                "img": "picture",
                                "link": "link"
                            };
                            var _k = 'picture-navigation_' + temp;
                            items[_k] = {};
                            items[_k]['list'] = [];
                            getItemsObj(data, cloneRet, items[_k]['list'], true);
                            break;
                        case 'nodeOrderTpl':
                            var _k = 'mall-orders_' + temp;
                            items[_k] = {};
                            items[_k]['title'] = 'ordertemp';
                            break;
                    }
                });
            }
            i++;
        })();
        return _items;
    }
}));