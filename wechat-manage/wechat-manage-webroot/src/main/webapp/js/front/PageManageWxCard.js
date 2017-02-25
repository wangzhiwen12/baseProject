 var baseUrl = "page/";
   
    function cfgModel(data) {
        var self = this;
        self.Id = ko.observable(data.Id);
        self.CardId = ko.observable(data.CardId);
        self.Status = ko.observable(data.Status);
        self.StatusMsg = ko.observable(data.StatusMsg);
        self.CardBrandName = ko.observable(data.CardBrandName).extend({ required: { params: true, message: "会员卡标题不能为空" } });;
        self.CardLogoPic = ko.observable(data.CardLogoPic).extend({ required: { params: true, message: "请选择LOGO图片" } });
        self.FullCardLogoPic = ko.observable(data.FullCardLogoPic);
        self.CardTitle = ko.observable(data.CardTitle || "微信会员卡").extend({ required: { params: true, message: "会员卡副标题不能为空" } });//会员卡标题
        self.CodeType = ko.observable(data.CodeType || "CODE_TYPE_QRCODE");//会员卡展示类型
        self.CoverType = ko.observable(data.CoverType || "CL");//卡券封面背景类型（PC：图片 CL：颜色）
        self.CoverPic = ko.observable(data.CoverPic).extend({ required: { params: true, message: "请选择背景图片", onlyIf: function () { return self.CoverType() === 'PC'; } } });;//卡封面图片地址(ezp自己的图片)
        self.FullCoverPic = ko.observable(data.FullCoverPic);//卡封面图片地址(ezp自己的图片)
        self.CoverWxPic = ko.observable(data.CoverWxPic);//卡封面图片地址(微信图片，微信不允许外部引用)
        self.CoverColor = ko.observable(data.CoverColor || "#5885cf");//卡颜色(CoverType为1才有)
        self.IsBonusDiscount = ko.observable(data.IsBonusDiscount || false);//是否积分优惠
        self.IsCouponDiscount = ko.observable(data.IsCouponDiscount || false);//是否折扣优惠
        self.BonusRuleObj = ko.observable(data.BonusRuleObj);//积分规则JSON字符串,用于微信买单
        self.Discount = ko.observable(data.Discount);//折扣，该会员卡享受的折扣优惠,填10就是九折。  
        
        self.CardNoType = ko.observable(data.CardNoType || 'CODE');//卡号类型
        self.CenterTitle = ko.observable(data.CenterTitle);//激活后中间按钮标题
        self.CenterType = ko.observable(data.CenterType || "CS");//激活后中间按钮标题
        self.CenterSubTitle = ko.observable(data.CenterSubTitle);//激活后中间按钮标题下提示语
        self.CenterUrl = ko.observable(data.CenterUrl).extend({ required: { params: true, message: "地址不能为空", onlyIf: function () { return self.CenterTitle() && self.CenterType() == 'CS'; } }, pattern: { params: /^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&#=]*)?$/i, message: "链接地址不合法" } });//激活后中间按钮地址
        self.SupplyBonus = ko.observable(data.SupplyBonus);//显示积分
        self.SupplyCoupon = ko.observable(data.SupplyCoupon);//显示券
        self.SupplyGrade = ko.observable(data.SupplyGrade);//显示等级
        self.SupplyBalance = ko.observable(data.SupplyBalance);//显示余额
        self.BalanceUrl = ko.observable(data.BalanceUrl).extend({ required: { params: true, message: "地址不能为空", onlyIf: function () { return self.SupplyBalance(); } }, pattern: { params: /^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&#=]*)?$/i, message: "链接地址不合法" } });;//余额地址
        self.BalanceName = ko.observable(data.BalanceName).extend({ required: { params: true, message: "名称不能为空", onlyIf: function () { return self.SupplyBalance(); } }});//显示名称

        self.Prerogative = ko.observable(data.Prerogative).extend({ required: { params: true, message: "特权内容不能为空" } });//特权内容
        self.Description = ko.observable(data.Description).extend({ required: { params: true, message: "使用说明不能为空" } });//使用说明
        self.CustomCell1Obj = ko.observable(data.CustomCell1Obj);//自定义会员信息类目json字符串，会员卡激活后显示，
        self.Notice = ko.observable(data.Notice).extend({ required: { params: true, message: "卡券使用提醒不能为空" } });//卡券使用提醒，字数上限为16个汉字
        self.CustomUrlName = ko.observable(data.CustomUrlName);//自定义跳转外链的入口名字
        self.CustomUrl = ko.observable(data.CustomUrl);//自定义跳转的URL
        self.CustomUrlSubTitle = ko.observable(data.CustomUrlSubTitle);//显示在入口右侧的提示语
        self.PromotionUrlName = ko.observable(data.PromotionUrlName);//营销场景的自定义入口名称
        self.PromotionUrl = ko.observable(data.PromotionUrl);//入口跳转外链的地址链接
        self.PromotionUrlSubTitle = ko.observable(data.PromotionUrlSubTitle);//显示在营销入口右侧的提示语
        self.BrandName = ko.observable(data.BrandName);//品牌名称
        self.WxHeadImg = ko.observable(data.WxHeadImg || "http://static.ezrpro.com/wx/imgs/vipcarddef.png");//微信头像

        self.StatusTag = ko.observable(0);
        
        self.IsWxVipCard = ko.observable(data.IsWxVipCard);
        self.PageUrl = ko.observable(data.WxCardUrl);

    }

    function bonusRuleModel(data) {
        var self = this;
        self.CostMoneyUnit = ko.observable(data.CostMoneyUnit);
        self.IncreaseBonus = ko.observable(data.IncreaseBonus);
        self.CostBonusUnit = ko.observable(data.CostBonusUnit);
        self.ReduceMoney = ko.observable(data.ReduceMoney);
    }

    function customCell1Model(data) {
        var self = this;
        self.Name = ko.observable(data.Name);
        self.Tips = ko.observable(data.Tips);
        self.Url = ko.observable(data.Url);
        self.SortIdx = ko.observable(data.SortIdx);
    }
    
    function customModel(data) {
        var self = this;
        self.Name = ko.observable(data.Name).extend({ required: { params: true, message: "名称不能为空" } });
        self.Tips = ko.observable(data.Tips);
        self.Url = ko.observable(data.Url).extend({ required: { params: true, message: "请选择链接" } });
        self.UrlName = ko.observable(data.UrlName || "请选择链接");
        self.SortIdx = ko.observable(data.SortIdx);
        self.errors = ko.validation.group(self);
    }

    function ObjectViewModel() {
        var self = this;
        self.cardCfg = ko.validatedObservable(new cfgModel({ IsBonusDiscount: true, IsCouponDiscount: true, BonusRuleObj: new bonusRuleModel({}), CustomCell1Obj: new customCell1Model({})}));
        self.cusItem = ko.observableArray([]);
        self.selectedUrl = ko.observable("").extend({ required: { params: true, message: "自定义外链不能为空" }, pattern: { params: /^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&#=]*)?$/i, message: "链接地址不合法" } });

        self.loadData = function() {
        	$.post("wechatpage/" + "getWxCardInfo.json",
            function (result) {
        		var data =result.data;
                if (data) {
                    var model = new cfgModel(data);
                    model.StatusTag(data.Status);
                    var bonusRule = new bonusRuleModel({});
                    if (data.BonusRuleObj) {
                        bonusRule = new bonusRuleModel(data.BonusRuleObj);
                    }
                    model.BonusRuleObj(bonusRule);
                    var customCell1 = new customCell1Model({});
                    if (data.CustomCell1Obj) {
                        customCell1 = new customCell1Model(data.CustomCell1Obj);
                    }
                    model.CustomCell1Obj(customCell1);
                    if (data.CustomUrl) {
                        self.cusItem.push(new customModel({ Name: data.CustomUrlName, Url: data.CustomUrl, Tips: data.CustomUrlSubTitle, UrlName: getUrlNameByLink(data.CustomUrl),SortIdx:1 }));
                    }
                    if (customCell1.Url()) {
                        self.cusItem.push(new customModel({ Name: customCell1.Name(), Url: customCell1.Url(), Tips: customCell1.Tips(), UrlName: getUrlNameByLink(customCell1.Url()), SortIdx: 2 }));
                    }
                    if (data.PromotionUrl) {
                        self.cusItem.push(new customModel({ Name: data.PromotionUrlName, Url: data.PromotionUrl, Tips: data.PromotionUrlSubTitle, UrlName: getUrlNameByLink(data.PromotionUrl), SortIdx: 3 }));
                    }
                    self.cardCfg(model);
                    $('#colorselector').colorselector();
                    new readLength("readLength");
                    $(".popovers").popover({
                        container: "body",
                        placement: 'left',
                        trigger: "click",
                        html: true
                    });
                } 
            },"json");
        };

        function getUrlNameByLink(link) {
            var urlName = "";
            switch (link) {
                case '7':
                    urlName = "完善个人资料";
                    break;
                case '6':
                    urlName = "绑定实体会员卡";
                    break;
                case '8':
                    urlName = "我的积分";
                    break;
                case '4':
                    urlName = "我的优惠券";
                    break;
                case '9':
                    urlName = "我的订单";
                    break;
                case '10':
                    urlName = "我的收藏";
                    break;
                case '11':
                    urlName = "我的地址";
                    break;
                case '5':
                    urlName = "我的特权";
                    break;
                case '12':
                    urlName = "我的专属导购";
                    break;
                case '13':
                    urlName = "门店活动";
                    break;
                case '14':
                    urlName = "申请服务";
                    break;
                case '15':
                    urlName = "待评价";
                    break;
                case '16':
                    urlName = "附近门店";
                    break;
                case '1':
                    urlName = "个人资料";
                    break;
                default:
                    urlName = "外链:" +link;
                    break;
            }
            return urlName;
        }
        self.costMoneyUnitChange = function (type) {
            var tempNum = self.cardCfg().BonusRuleObj().CostMoneyUnit();
            var isN = isNaN(tempNum);
            if (isN) {
                self.cardCfg().BonusRuleObj().CostMoneyUnit("");
            }
        };
        self.increaseBonusChange = function () {
            var tempNum = self.cardCfg().BonusRuleObj().IncreaseBonus();
            var isN = isNaN(tempNum);
            if (isN) {
                self.cardCfg().BonusRuleObj().IncreaseBonus("");
            }
        };
        self.costBonusUnitChange = function () {
            var tempNum = self.cardCfg().BonusRuleObj().CostBonusUnit();
            var isN = isNaN(tempNum);
            if (isN) {
                self.cardCfg().BonusRuleObj().CostBonusUnit("");
            }
        };
        self.reduceMoneyChange = function () {
            var tempNum = self.cardCfg().BonusRuleObj().ReduceMoney();
            var isN = isNaN(tempNum);
            if (isN) {
                self.cardCfg().BonusRuleObj().ReduceMoney("");
            }
        };
        self.discountMoneyChange = function () {
            var tempNum = self.cardCfg().Discount();
            var isN = isNaN(tempNum);
            if (isN) {
                self.cardCfg().Discount("");
            }
        };
        self.addItem = function () {
            var model = new customModel({ Name: '自定义入口'});
            self.cusItem.push(model);
            $.each(self.cusItem(), function (i, v) {
                v.SortIdx(i + 1);
            });
        };
        self.removeItem = function (data) {
            self.cusItem.remove(data);
            $.each(self.cusItem(), function(i,v) {
                v.SortIdx(i + 1);
            });
        };
        self.upItem = function(data) {
            var idx = data.SortIdx();
            var oldDataName = data.Name();
            var oldDataTips = data.Tips();
            var oldDataUrl = data.Url();
            var oldDataUrlName = data.UrlName();
            var pre = getItem(idx - 1);
            data.Name(pre.Name());
            data.Tips(pre.Tips());
            data.Url(pre.Url());
            data.UrlName(pre.UrlName());
            
            pre.Name(oldDataName);
            pre.Tips(oldDataTips);
            pre.Url(oldDataUrl);
            pre.UrlName(oldDataUrlName);
        };
        
        self.downItem = function (data) {
            var idx = data.SortIdx();
            var oldDataName = data.Name();
            var oldDataTips = data.Tips();
            var oldDataUrl = data.Url();
            var oldDataUrlName = data.UrlName();
            var last = getItem(idx + 1);
            data.Name(last.Name());
            data.Tips(last.Tips());
            data.Url(last.Url());
            data.UrlName(last.UrlName());

            last.Name(oldDataName);
            last.Tips(oldDataTips);
            last.Url(oldDataUrl);
            last.UrlName(oldDataUrlName);
        };

        function getItem(idx) {
            var item;
            $.each(self.cusItem(), function(i, v) {
                if (idx - 1 == i) {
                    item = v;
                    return false;
                }
            });
            return item;
        }
        self.DropMenuClick = function (data, value,event) {
            if(value == 'curl')//自定义外链
            {
                self.popCustomUrlShow(data);
            }
            else
            {
                data.Url(value);
                var name = event.target.innerText;
                data.Name(name);
                data.UrlName(name);
            }
        }
        var updateUrlData;
        self.popCustomUrlShow = function (data) {
            self.selectedUrl("");
            if(data.Url() && data.Url().indexOf('http') >= 0)
            {
                self.selectedUrl(data.Url());
            }
            updateUrlData = data;
            $('#model_url').modal('show');
        };
        self.updateUrl = function(){
            if (!self.selectedUrl.isValid()) {
                self.selectedUrlError.showAllMessages();
                return;
            }
            updateUrlData.Url(self.selectedUrl());
            updateUrlData.UrlName("外链："+self.selectedUrl());
            $('#model_url').modal('hide');
        };
        
        self.selectPic = function () {
        	layer.open({
					title : "图片素材",
					type : 2,
					area : [ "90%", "85%" ],
					content : contextPath+'/materialLocal/imageList.shtml'
				});
        };
        self.selectCoverPic = function () {
        	layer.open({
				title : "图片素材",
				type : 2,
				area : [ "90%", "85%" ],
				content : contextPath+'/materialLocal/imageList.shtml?type=1',
			});
        };
        self.saveData = function (type) {
            if (!self.cardCfg.isValid()) {
                self.cardCfg.errors.showAllMessages();
                return;
            }
            var errorCount = 0;
            ko.utils.arrayForEach(self.cusItem(), function (item) {
                item.errors.showAllMessages();
                errorCount = errorCount + item.errors().length;
            });
            if (errorCount > 0) return;
            self.cardCfg().Status(type);
            //如果是保存并提交至微信
            if (type == 1) {
                layer.confirm(
                		'选择提交到微信将由微信进行审核，审核过程中不能修改！审核完成之后即可生效，是否继续操作？',
                		function(index){
                			save();
                		}
                );
            } else {
                save();
            }
        };

        function save() {
            self.cardCfg().CustomUrlName("");
            self.cardCfg().CustomUrl("");
            self.cardCfg().CustomUrlSubTitle("");
            self.cardCfg().PromotionUrlName("");
            self.cardCfg().PromotionUrl("");
            self.cardCfg().PromotionUrlSubTitle("");
            self.cardCfg().CustomCell1Obj({});

            $.each(self.cusItem(), function (i, v) {
                if (i == 0) {
                    self.cardCfg().CustomUrlName(v.Name());
                    self.cardCfg().CustomUrl(v.Url());
                    self.cardCfg().CustomUrlSubTitle(v.Tips());
                }
                else if (i == 1) {
                    var cell = new customCell1Model(v);
                    self.cardCfg().CustomCell1Obj(cell);
                } else {
                    self.cardCfg().PromotionUrlName(v.Name());
                    self.cardCfg().PromotionUrl(v.Url());
                    self.cardCfg().PromotionUrlSubTitle(v.Tips());
                }
            });
            alert(ko.toJSON(self.cardCfg()));
            $.post("wechatpage/" + "CreateWXCard.json", ko.toJSON(self.cardCfg()),function (result) {
                if (!result.IsError) {
                    layer.msg("保存成功！");
                    self.cardCfg().Id(result.Id);
                    self.cardCfg().StatusTag(result.Status);
                } else {
                	 layer.msg("保存失败:" + result.ErrorMsg);
                }
            },"json");
        }
        self.initUploader = function () {
            var $list = $('#fileList'),
            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 100 * ratio,
            thumbnailHeight = 100 * ratio,

            // Web Uploader实例
            uploader1;

            // 初始化Web Uploader
            uploader1 = WebUploader.create({

                // 自动上传。
                auto: true,

                // swf文件路径
                swf: 'http://static.ezrpro.com/assets/js/upload/Uploader.swf',

                // 文件接收服务端。
                server: EZP.env.CrmApi + EZP.env.CrmPrefix +'Home/Upload/PostWxImage',

                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: {
                    id: '#filePicker',
                    multiple: false,
                },

                // 只允许选择文件，可选。
                accept: {
                    title: 'Images',
                    extensions: 'jpg,jpeg,png,gif',
                    mimeTypes: 'image/bmp,image/png,image/jpeg,image/jpg,image/gif'
                },
                compress: {
                    //width: 120,
                    //height: 120,

                    // 图片质量，只有type为`image/jpeg`的时候才有效。
                    quality: 90,

                    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
                    allowMagnify: false,

                    // 是否允许裁剪。
                    crop: false,

                    // 是否保留头部meta信息。
                    preserveHeaders: true,

                    // 如果发现压缩后文件大小比原来还大，则使用原来图片
                    // 此属性可能会影响图片自动纠正功能
                    noCompressIfLarger: false,

                    // 单位字节，如果图片大小小于此值，不会采用压缩。
                    compressSize: 0
                }
            });

            // 当有文件添加进来的时候
            uploader1.on('fileQueued', function (file) {
                var $li = $(
                        '<div id="' + file.id + '" class="file-item thumbnail">' +
                            '<img>' +
                            '<div class="info">' + file.name + '</div>' +
                        '</div>'
                        ),
                    $img = $li.find('img');

                $list.html($li);

                // 创建缩略图
                uploader1.makeThumb(file, function (error, src) {
                    if (error) {
                        $img.replaceWith('<span>不能预览</span>');
                        return;
                    }

                    $img.attr('src', src);
                    //self.cardCfg().FullCoverPic(src);
                }, thumbnailWidth, thumbnailHeight);
            });
            // 修改后图片上传前，尝试将图片压缩到200 * 200
            //uploader.option('compress', {
            //    width: 640,
            //    height: 320
            //});
            // 文件上传过程中创建进度条实时显示。
            uploader1.on('uploadProgress', function (file, percentage) {
                var $li = $('#' + file.id),
                    $percent = $li.find('.progress span');

                // 避免重复创建
                if (!$percent.length) {
                    $percent = $('<p class="progress"><span></span></p>')
                            .appendTo($li)
                            .find('span');
                }

                $percent.css('width', percentage * 100 + '%');
            });

            // 文件上传成功，给item添加成功class, 用样式标记上传成功。
            uploader1.on('uploadSuccess', function (file, reason) {
                if (reason.IsError) {
                    var $li = $('#' + file.id),
                    $error = $li.find('div.error')
                    if (!$error.length) {
                        $error = $('<div class="error"></div>').appendTo($li);
                    }
                    $error.text('上传失败');
                    uploader1.removeFile(file);
                } else {
                    $('#' + file.id).addClass('upload-state-done');
                    uploader1.removeFile(file);
                    self.cardCfg().CoverPic(reason.Data.path);
                    self.cardCfg().FullCoverPic(reason.Data.thumbnailfull);
                    self.cardCfg().CoverWxPic(reason.Data.pathfull);
                }
            });

            // 文件上传失败，现实上传出错。
            uploader1.on('uploadError', function (file) {
                var $li = $('#' + file.id),
                    $error = $li.find('div.error');

                // 避免重复创建
                if (!$error.length) {
                    $error = $('<div class="error"></div>').appendTo($li);
                }

                $error.text('上传失败');
                uploader1.removeFile(file);
            });

            // 完成上传完了，成功或者失败，先删除进度条。
            uploader1.on('uploadComplete', function (file) {
                $('#' + file.id).find('.progress').remove();
            });
        };
        self.loadData();
        //self.initUploader();
    }

    var ovm = new ObjectViewModel();
     ko.applyBindings(ovm, document.getElementById('div_main2'));
     //选择图片之后的回调函数
     function imageSwitch(media) {
         if (media) {
        	 ovm.cardCfg().CardLogoPic(media.localUrl);
        	 ovm.cardCfg().FullCardLogoPic(media.picUrl);
         }
     }
     
   //选择图片之后的回调函数
     function imageSwitch1(media) {
         if (media) {
        	 ovm.cardCfg().CoverPic(media.localUrl);
        	 ovm.cardCfg().FullCoverPic(media.picUrl);
         }
     }
     new readLength("readLength");
   