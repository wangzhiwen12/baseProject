;(function (root, factory) {

	if (typeof define === "function" && define.amd) {
		define(factory);
	} else if (typeof exports === "object") {
		module.exports = factory();
	} else {
		root.scope = factory();
	}

}(this, function(){
	"use strict";
	var util = {};
	util.showTgt = function(d, v, index) {
			
		var d = $('[data-scope="'+ d +'"]'),
			vl = v,
			index = index;
		d.each(function(){
			
			var d; 
			if( index >= 0 ){
				if ( $(this).closest('.repeat').index() == index ){
					d = $(this);
				}else{
					return;
				}
			}else{
				d = $(this);
			}
			var tgt = d.data('tgt');

	        switch (tgt) {
	            case "cot":
	                if (vl.length > 0) {
	                    d.html(vl)
	                } 
	                break;
	            case "href":
	                d.attr("href", vl)
	                break;
	            case "vl":
	                d.val(vl);
	                break;
	            case "src":
	                //20160406
	                if (vl.length > 3) {
	                    d.attr("src", vl).removeClass("wdn");
	                } else {
	                    d.addClass("wdn");
	                }
	                break;
	            case "cls":
	                d.attr("class", "");
	                d.addClass(vl);
	                break;
	            case "hg":
	                d.css("height", (vl + "px"));
	                break;
	            case "bg":
	                d.css("background-image", 'url(' + vl + ')');
	                break;
	            case "data":
	                d.attr("data-" + arg2, vl);
	                break;
	            case "checked":
	                var name = d.attr("name");
	                $('[value = "'+ vl +'"]').prop("checked", true);
	                break;
	            case "datalist":
	                if (v) {
	                    d.data("list", v);
	                    var v = JSON.parse(v)
	                    var s = "";
	                    for (var i = 0; i < v.length; i++) {
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
		})
    }

    //图片组件
    util.commons = (function(){

    	var obj = {};
		function SelectPic(ipt){
			var scopeData = ipt.attr("data-scope");
			var src = ipt.val().length ? ipt.val() : 'http://static.ezrpro.com/assets/icon/ceremony06.png';
			this._ipt = ipt;
			this.warp = $('<div class="select-pic-common">' +
					'<p>' +
						'<span class="img-warp">' +
							'<img style="width:72px; height: 72px;" class="selected" data-tgt="src" src="'+ src +'" data-scope="'+ scopeData +'">' +
							'<span class="clearPic"><span> </span></span>' +
						'</span>' +
					'</p>' +
					'<p>' +
						'<a href="javascript:;" class="select-pic">选择图片</a>' +
					'</p>' +
				'</div>');
			this._del = this.warp.find('.clearPic');
			this._selectPic =  this.warp.find('.selected');
			this.init();
		}

		SelectPic.prototype = {
			init: function(){
				this._ipt.after(this.warp);
				this.bindEvent();
			},
			bindEvent: function(){
				var self = this;
				this._del.click(function(){
					self._ipt.val(' ').change();
				})
			}
		}

		function Slt(ipt){

	        this._ipt = ipt;
	        // 存储图片
	        this._pic = this._ipt.closest(".list.repeat").find(".link-img");
	        // 存储名字
	        this._name = this._ipt.closest(".list.repeat").find(".link-name");
	        this._ipt.after(this.selectLink());
	        this.warp = this._ipt.siblings('.selectLink');
	        this.ret = this.warp.find(".ret").find(".cot");
	        this.retWarp = this.warp.find(".ret");
	        this.contralItems =  this.warp.find(".contralItems");
	        this.contralPop =  this.warp.find(".bindPop");
	        this.btnShow = this.warp.find(".btnShow");
	        this.cancel = this.warp.find(".ret").find(".cancle");
	        this.init();

	    }
	    Slt.prototype = {
	        init:function(){
	            this.showRet(this._ipt.val());
	            this.showDom();
	        },
	        selectLink:function(){
	        	var html = scope.template('selectLink');
	        	return html;
	        },
	        showRet:function(arg){
	            if(arg){
	                this.btnShow.text("修改");
	                this.cancel.html("X");

	                this.retWarp.css("display", "inline-block");
	            }else{
	                this.ret.text(" ");
	                this.cancel.html("");
	                this.btnShow.text("设置链接");
	                this.retWarp.css("display", "none");
	            }
	        },
	        showDom:function(){
	            var warp = this.warp,
	                ret = this.ret;
	            this._ipt.after(warp);
	            if(this._name.val()){
	                ret.html( this._name.val() );
	            }
	            this.bindEvent();
	        },
	        bindEvent:function(){
	            var that = this,
	                contralItems = this.contralItems,
	                ret = this.ret;
	            contralItems.click(function(){
	                var _$this = $(this),
	                    s = _$this.data("show"),
	                    imgSrc = _$this.data("src"),
	                    v = _$this.data("value");

	                that._pic.val(imgSrc).change();
	                that._name.val(s).change();
	                that._ipt.val(v).change();
	                ret.html(s);
	                that.showRet(true);
	            })

	            this.cancel.click(function(){
	                that.showRet(false);
	            })

	            //在定义菜单
	            this.contralPop.click(function () {
	                var __$this = $(this),
	                    _$this = that._ipt,
	                    ret = that.ret,
	                    imgSrc = __$this.data("src"),
	                    s = __$this.data("show");
	                    $(".wPopup").remove();
	                    $(".triggerwPopup").removeClass("triggerwPopup");
	                    $(this).addClass("triggerwPopup");

	                var fn1 = function(value){
	                    that._ipt.val(value["externalLinks"]).change();
	                    that._pic.val(imgSrc).change();
	                	that._name.val(s).change();
	                    ret.html(s + value["externalLinks"]);
	                    that.showRet(true);
	                }
	                var fn2 = function(value){
	                }
	                var o = {
	                    tger :_$this,
	                    inner: "<input type='text' class='iData externalLinks vali' data-valiitems='isEmpty isLink' data-alertmsg='链接不能为空 请正确填写链接' data-alerttype='[1,1]'/>",
	                    value:["externalLinks","ret"],
	                    determineFn:fn1,
	                    cancelFn:fn2
	                }
	                var c = new Pop(o);
	            })
	        }
	    }

		function Pop(arg){
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
	            width =  this.tger.width(),
	            top = this.tger.offset().top + height + 5,
	            left = this.tger.offset().left;

	        this.DomX = left;
	        this.DomY = top;

	        this._value = arg["value"];

	        this.init();
	    }

	    Pop.prototype = {
	        init:function(){
	            this.showDom();
	            this.bindEvent();
	        },
	        showDom:function(){
	            var x = this.DomX,
	            y = this.DomY;
	            this.wrap.css({"top": (y + "px") , "left": ( x  + "px") });
	            this.cot.append(this.inner);
	            $("body").append(this.wrap);
	        },
	        bindEvent:function(){
	            var that = this;
	            //20150918验证
	            //var yz = new VerifyCollection("", that.cot);
	            this.determineBtn.click(function () {
	                //yz.through = true;
	                // if (yz.gThrough()) {
	                    that.determineFn(that.value());
	                    that.tger.trigger("click");
	                    that.clearDom();
	                // }
	            })
	            this.cancelBtn.click(function(){
	                that.cancelFn(that.value());
	                that.clearDom();
	            })
	        },
	        value: function(){
	            var o = {},
	                _value = this._value;
	            for(var i = 0; i < _value.length; i++){
	                var v = this.wrap.find(".iData." + _value[i]).val() ? this.wrap.find(".iData." + _value[i]).val() : "";
	                o[_value[i]] = v;
	            }
	            return o;
	        },
	        clearDom: function(){
	            this.wrap.remove();
	        }
	    }

	    // 滑动
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
	            if (down && e.pageX >= Math.round(rangeLeft + rangeWidth/100 * 10) && e.pageX <= (rangeLeft + rangeWidth)) {
	                dragger.css("left", (e.pageX - rangeLeft - draggerWidth + 'px'))
	                if (typeof onDrag == "function") onDrag(Math.round(((e.pageX - rangeLeft) / rangeWidth) * 100));
	            }
	        }
	    }

		function RangeSlider(ipt) {
	        this.ipt = ipt;
	        // this._arg = arg;
	        // this.arg = arg[0] ? arg[0] : 0;
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
	            var init = that.ipt.val();
	            rangeSlider(this.slider, function (value) {
	                that.ipt.val(value).change();
	                that.ret.html(that.ipt.val());
	                that.progressBar.css("width", (value + "%"));
	                // that._arg[0] = value;
	            }, init);
	        }
	    }

	    function SwiperOptions(listener){
	    	this.listener = listener;
	    	this.scope = scope.data[this.listener.data('listener')];
	    	this.obj;
	    	this.backup = Object.create(this.scope);
	    	this.count = 0;
	    	this.init();

	    }
	    SwiperOptions.prototype = {
	    	init: function(){
	    		var self = this;
	    		self.state();
	    		self.domState();
	    		self.bindEvent();
	    	},
	    	state: function(){
	    		var self = this,
	    			checkeds = this.listener.find("input:checked");
	    		self.obj = {};
	    		checkeds.each(function(){
	    			var _$this = $(this),
	    				name = _$this.data('name');
	    			self.obj[name] = _$this.val();
	    		})	    		
	    	},
	    	createTplDom: function(tpl){
	    		var _scope = this.listener.data('listener');
    			this.scope['temp'] = _scope;
    			//重绘 DOM view
    			var html = scope.template(tpl, this.scope);
    			//直截走 DOM
    			$( '#' + _scope ).find('.views').html(html);
	    	},
	    	domState: function(){

	    		if(!this.obj['mode']) return;
	    		var value = this.obj['mode'];

	    		if(this.backup['mode'] !== this.obj['mode'] || this.count === 0 ){

		    		if( this.obj['mode'] === 'isSwiper' ){

		    			this.listener.find('.wdn').removeClass('wdn');
		    			this.listener.find('[value="separate-small"]').parent().addClass('wdn');
		    			this.listener.find('[value="separate-larger"]').prop('checked', true);
		    			//更新 Dom
		    			//更新 Data
		    			this.scope['size'] = 'separate-larger';
		    			$('pre').html(JSON.stringify(scope.data, undefined, 2));
		    			this.createTplDom('isSwiper');		    			

		    		}else{
		    			this.listener.find('.wdn').removeClass('wdn');
		    			this.listener.find('[data-name="auto"]').closest('.ui-form-horizontal').addClass('wdn');
		    			this.createTplDom('notSwiper');
		    		}
		    		this.count++;
		    		this.backup['mode'] = this.obj['mode'];

	    		}

	    	},
	    	bindEvent: function(){
	    		var self = this;
	    		this.listener.find('input').change(function(){

	    			self.state();
	    			self.domState();

	    		})
	    	},
	    	//方法到 DOM 上执行
	    	change: function(){
	    		this.domState();
	    	}
	    }

	    return function(ipt){

		    obj.Slt = function(){
		    	return new Slt(ipt);
		    }
		    obj.SelectPic = function(){
		    	return new SelectPic(ipt);
		    }
		    obj.RangeSlider = function(){
		    	return new RangeSlider(ipt);
		    }
		    obj.SwiperOptions = function(){
		    	return new SwiperOptions(ipt);
		    }
			return obj;

	    }

    })();

	// 扩展方法
	// DOM  bindEvent
	var Scope = function(tgt, data){
		this.data = data || {};
		this.clones = {};
		this.warps = {};
		this.tgt = tgt;
		this.init();
	}

	Scope.prototype = {

		init: function(){
			this.eles();
			// this.bindEvent();
		},
		//克隆对象集合
		refactoringData: function(data){

			// selector => obj
			// Dom 不存在的状态下
			var data = data ? data : this.data,
				ret = {},
				self = this;
		    function _each(data, selector, isArray, __obj){
		    	var selector = selector;

			    $.each(data, function(key, value){

		    		var _selector = selector ? (selector + '.' + key) : key;

					if(Object.prototype.toString.call(value) === "[object Array]"){

						var i = 0;
						ret[_selector] = ret[_selector] === undefined  ? [] : ret[_selector];	

						//----------====重复的项====-----------//
						var repeats = $('[data-repeat="'+ _selector +'"]');
						while(value[i]){		
							var _obj = {}
							if( Object.prototype.toString.call(value[i]) === '[object Object]' ){

								repeats.each(function(index){

									self.clones[ _selector+ '-' + index ] = self.clones[ _selector+ '-' + index ] !== undefined ? self.clones[ _selector+ '-' + index ] : $(this).children().clone();
									self.warps[ _selector+ '-' + index] = self.warps[ _selector+ '-' + index] !== undefined ? self.warps[ _selector+ '-' + index ] : $(this).addClass('repeat-wrapper').html(' ');																	
									self.warps[_selector+ '-' + index].append(self.clones[_selector + '-' + index].clone());
									
								})
								ret[_selector].push(_obj);
								_each(value[i], _selector, true, ret[_selector][i]);

							} 
							i++;
						}

					}else if( Object.prototype.toString.call(value) === '[object Object]' ){
						_each(value, _selector);
					}else{
						if(isArray){
							var a = _selector.split('.');
							a.pop();
							//var obj = {};
							__obj[_selector] = value;
							//ret[a.join('.')].push(obj);
						}else{
							ret[_selector] = value;
						}
					};
				})
		    }

			_each(data);
			return ret;
		},
		eles: function(data, index){
			var data = this.refactoringData(data);
			function _each(data, index){
				$.each(data, function(key, value){
					if(Object.prototype.toString.call(value) === "[object Array]"){
						var i = 0;
						while(value[i]){
							_each(value[i], i);
							i++;
						}
					}else{
						util.showTgt(key, value, index)
					}
				})
			}
			_each(data, index);
		},
		set: function(selector, value, index){
			var selector = selector,
				value = value,
				index = index;
			// selector => obj
			// Data => DOM
			var pop = selector.split('.').pop();
			util.showTgt(selector, value, index);
			// Data => Data
			this.scope(selector, index)[pop] = value;
		},
		get: function(scope, index){
			var scope = scope,
				index = index !== undefined ? index : null,
				selector = index ? $('[data-scope='+ scope +']').get(index) : $('[data-scope='+ scope +']');
			return selector;
		},
		scope: function(selector, index){

			// img-navigation_1474182215962.list
			// input change
			var selector = selector.split('.'),
				index = index >= 0 ? index : undefined,
				obj = this.data;
				selector.pop();

				var length = selector.length;

				for(var i = 0; i < selector.length; i++){
					obj = obj[selector[i]];
				}

				return obj = index >= 0 ? obj[index] : obj;

		},
		sort: function(scope, oldIndex, newIndex){
			// Dom
			var d = $('.views').find('[data-repeat="'+ scope +'"]'),
				moveChild = d.children().eq(oldIndex);

			//向前
			//向后
			console.log(newIndex)
			console.log(oldIndex)
			if(newIndex > oldIndex){
				d.children().eq(newIndex).after(moveChild);
			}else{
				d.children().eq(newIndex).before(moveChild);
			}

			var _scope = scope.split('.').pop();
			var a = this.scope(scope)[_scope];
			a.splice(newIndex, 0, a.splice(oldIndex, 1)[0]);

		},
		repeat: function(){
			// 存储一个副本
			// this.clone['img-navigation_1474182215962.list'];
		},
		bindEvent: function(){
			var self = this;
			$(this.tgt).on('click', '.handler', function(){
				var _$this = $(this),
					handler = _$this.data('action'),
					scope = _$this.data('action-scope'),
					limit = _$this.data('limit'),
					index = _$this.closest('.repeat').index(),
					tpl = _$this.data('action-template');
				self.processor(scope, index, limit, tpl);
				self.extendDom();
			})

			$(this.tgt).on('change', 'input.operate', function(){
				var _$this = $(this),
					value = _$this.attr('type') === 'radio' ? $('input.operate[name=' + _$this.attr('name') + ']:checked').val() : _$this.val(),
					scope = _$this.data('scope'),
					index = _$this.closest('.repeat').index() >= 0 ? _$this.closest('.repeat').index() : undefined;
					self.set(scope, value, index);
					$('pre').html(JSON.stringify(self.data, undefined, 2));

			})

		},
		remove: function(scope, index){

		},
		processor: function(scope, index, limit, tpl){
			// Dom
			// data
			var self = this,
				scope = scope,
				index = index,
				limit = limit,
				tpl = tpl;

			var _selector = scope.split('.'),
				action = _selector.pop();

			_selector = _selector.join('.');
			var arr = self.scope(scope);

			var repeatDoms = function(action, index){

				var action = action,
					index = index,
					i = 0;

				while(self.warps[ _selector+ '-' + i]){

					switch(action){
						case 'add':
							self.warps[ _selector+ '-' + i].append(self.clones[ _selector+ '-' + i].clone());
						break;
						case 'remove':
							self.warps[ _selector+ '-' + i].children().eq(index).remove();
						break;
						case 'insert':
							self.warps[ _selector+ '-' + i].children().eq(index).after(self.clones[ _selector+ '-' + i].clone());
						break;
					}

					i++;
				}

			}

			switch(action){
				case 'add':
				if(limit && limit === arr.length){
					alert('最多'+ limit +'项');
					return false;
				} 
				repeatDoms('add', index);
				arr.push({});
				break;
				case 'remove':
				// Dom
				if( arr.length === 1 ){
					alert('至少 1 项');
					return false;
				}
				repeatDoms('remove', index);
				// Data
				arr.splice(index, 1);
				break;
				case 'insert':
				// Dom
				if(limit && limit === arr.length){
					alert('最多'+ limit +'项');
					return false
				};
				repeatDoms('insert', index);
				// Data
				arr.splice((index + 1), 0, {});
				break;
			}

			//处理模板
			var tplFn = function(tpl, tgt, data){

				var _data = self.scope(data),
					tpl = _data[tpl.split('.').pop()];

				var html = scope.template(tpl, _data);
    			//直截走 DOM
    			$( '#' + tgt ).html(html);

			};

			if(tpl){
				tpl = tpl.split(',');
				tplFn(tpl[0], tpl[1], tpl[2]);
			}
		},
		add: function(scope){

			console.time('add');
			var self = this;
			//Dom
			// this.get(scope).append(clone);		

			var _selector = scope.split('.');
			_selector.pop();
			_selector = _selector.join('.');

			var i = 0;
			while(self.warps[ _selector+ '-' + i]){
				self.warps[ _selector+ '-' + i].append(self.clones[ _selector+ '-' + i].clone());
				i++;
			}

			//Data
			self.scope(scope).push({});
		},
		insert: function(scope, oldIndex, newIndex){},

		_delete: function(scope){},
		_add: function(scope){},
		_push: function(scope){}
	}

	var _data = {}
	var scope = new Scope('#P', _data);

	//DOM 扩展
	Scope.prototype.extendDom = function(){
		var plugIns = $('input.plug-in:not(.plug-ined)');
		plugIns.each(function(){
			var _$this = $(this),
				plugIn = _$this.data('plug');

			if(!plugIn) return;
			plugIn = util.commons(_$this)[plugIn]();
			_$this.addClass('plug-ined');
		})

		var listeners = $('.listener:not(.listened)');
		listeners.each(function(){
			var _$this = $(this);
			util.commons(_$this).SwiperOptions();
			_$this.addClass('listened');

		})
	}
	return scope;

}));