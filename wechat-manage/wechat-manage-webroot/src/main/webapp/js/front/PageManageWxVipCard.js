    var page = {};
    
    var baseUrl = "wechatpage/";
   
    function cfgModel(data) {
        var self = this;
        self.Id = ko.observable(data.Id);
        self.PageName = ko.observable(data.PageName || "品牌会员卡").extend({ required: { params: true, message: "页面名称不能为空" } });;//页面名称
        self.HeadPictureUrl = ko.observable(data.HeadPictureUrl);//头部图片地址
        self.FullHeadPictureUrl = ko.observable(data.FullHeadPictureUrl || "https://static.ezrpro.com/wx/imgs/vipcarddef.png");//头部图片地址
        self.IsShowBrandCode = ko.observable(data.IsShowBrandCode);//是否显示品牌会员卡
        self.IsShowOnlineCode = ko.observable(data.IsShowOnlineCode);//是否显示线上会员卡
        self.PageUrl = ko.observable(data.PageUrl);
    }

    ObjectViewModel();
    function ObjectViewModel() {
        var self = this;

        self.loadData = function() {
            $.post(baseUrl + "getVipCardCfg.json", function (data) {
            	var data = data.data;
            	$("#headName").text(data.headName);
            	$("#headline").attr("value", data.headName);
            	$("#headPictureUrl").attr("style", "background-size: 100% 100%; background-image:url(\"" + data.headPictureUrl + "\");");
            	$("#imgHeadPictureUrl").attr("src", data.headPictureUrl);
            	$("#wxHeadPictureUrl").attr("value", data.wxHeadPictureUrl);
            	if (data.isShowBrandCode == "true") {
            		$("#isShowBrandCode").attr("checked", data.isShowBrandCode);
            	}
            	if (data.isShowOnlineCode == "true") {
            		$("#isShowOnlineCode").attr("checked", data.isShowOnlineCode);
            	}
            }, "json");
        };

        function selectPicCallback(data) {
            if (data.length > 0) {
                self.cardCfg().HeadPictureUrl(data[0].FilePath);
                self.cardCfg().FullHeadPictureUrl(data[0].FullFilePath);
            }
        };
        
        self.loadData();
    }
    
    $("#selectPic").click(function() {
    	layer.open({
    		title : "图片素材",
    		type : 2,
    		area : [ "90%", "85%" ],
    		content : contextPath+'/materialLocal/imageList.shtml'
    	});
    });

    
    
    function imageSwitch(media) {
    	$("#headPictureUrl").attr("style", "background-size: 100% 100%; background-image:url(\"" + media.localUrl + "\");");
    	$("#imgHeadPictureUrl").attr("src", media.localUrl);
    	$("#wxHeadPictureUrl").attr("value", media.picUrl);
    }
    
    /**
     * 发布按钮
     */
    $("#saveData").click(function () {
    	var headName = $("#headline").attr("value");
    	var headPictureUrl = $("#imgHeadPictureUrl").attr("src");
    	var wxHeadPictureUrl = $("#wxHeadPictureUrl").attr("value");
    	if (wxHeadPictureUrl == "" || wxHeadPictureUrl == null) {
    		alert("请选择背景图片");
    		return;
    	}
		var isShowBrandCode = $("#isShowBrandCode").attr("checked");
		if ("checked" == isShowBrandCode) {
			isShowBrandCode = true;
		} else {
			isShowBrandCode = false;
		}
		var isShowOnlineCode = $("#isShowOnlineCode").attr("checked");
		if ("checked" == isShowOnlineCode) {
			isShowOnlineCode = true;
		} else {
			isShowOnlineCode = false;
		}
		var data = {headName:headName, headPictureUrl:headPictureUrl, wxHeadPictureUrl:wxHeadPictureUrl, brandCodeStatus:isShowBrandCode, onlineCodeStatus:isShowOnlineCode}
    	$.post(baseUrl + "saveVipCardCfg.json", ko.toJS(data), function (result) {
    		 if (result.success) {
                 alert("发布成功！");
             } else {
                 alert("发布失败！");
             }
    	}, "json");
    });

    page.run = function() {
        ko.applyBindings(new ObjectViewModel(), document.getElementById('div_main2'));
        new readLength("readLength");
    }
