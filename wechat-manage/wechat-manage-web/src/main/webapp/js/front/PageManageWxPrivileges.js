

/*
 * page.run = function() { ko.applyBindings(new ObjectViewModel(), document
 * .getElementById('div_main2')); };
 */

    var page = {};
    
    var baseUrl = "wechatpage/";
    ObjectViewModel();
    function ObjectViewModel() {
        var self = this;
        var umEditor;
        $("#defaultWxHeadHtml").hide();
        $("#wxHeadHtml").hide();
        $("#brandLogo").attr("src", "./images/wfjcard.jpg")
        self.editContent = function () {
            self.loadPrivilege();
        };
        self.deleteContent = function () {
            umEditor.setContent("");
        };
        self.setUEditor = function (content) {
            if (!umEditor) {
                UE.delEditor('myContentEditor');
                umEditor = UE.getEditor('myContentEditor');
            }
            umEditor.ready(function () {
                umEditor.setContent(content);
            });
        };
        self.loadPrivilege = function () {
           $.post(baseUrl + "getPrivilege.json", function (data) {
                if (data) {
                	var data = data.data;
                    self.setUEditor(data.privilege);
                    //self.PageUrl(data.PageUrl);
                    if (data.wxHeadHtml){
                    	$("#wxHeadHtml").append(data.wxHeadHtml);
                    	self.ShowHead(data.showHead, true);
                    } else {
                    	self.ShowHead(data.showHead, false);
                    }
                }
            }, "json");
        };
        
        self.loadBrandInfo = function () {
            $.post((baseUrl + "GetBrandInfo"), function (data) {
                if (data) {
                    self.brandName(data.Name);
                    self.brandLogo(data.LogoFullUrl);
                }
            });
        };
       //  self.loadBrandInfo();
        self.loadPrivilege();
    
    }

    self.ShowHead = function(data1, data2) {
    	if (data2) {
			var showHeadId = "wxHeadHtml";
		} else {
			var showHeadId = "defaultWxHeadHtml";
		}
    	//开关为开的时候显示头信息
    	var state = false;
    	if (data1 == 2) {
    		$("#" + showHeadId).show();
    		$('#showHead').attr("value", "2");
    		state = true;
    	} else {
    		$('#showHead').attr("value", "1");
    	}
    	$('#showHead').bootstrapSwitch({
			onText : "启动",
			offText : "关闭",
			size : "mini",
			state : state,
			onSwitchChange : function(event, state) {
				if (state) {
					$("#" + showHeadId).show();
					$('#showHead').attr("value", "2");
				} else {
					$("#" + showHeadId).hide();
					$('#showHead').attr("value", "1");
				}
			}
		});
    };
    
    function imageSwitch(media) {
	        var htmlVal = "<img style='max-width:100%' tag='' src='" + media.localUrl + "'>";
	        if (!umEditor) {
                UE.delEditor('myContentEditor');
                umEditor = UE.getEditor('myContentEditor');
            }
	        umEditor.ready(function () {
	        	var content = umEditor.getContent();
	        	//content = content.substring(0, content.length-4);
	        	content += htmlVal;
	        	//content += "</p>";
                umEditor.setContent(content);
            });
    };
    
    function alertPrivilege() {
    	if (!umEditor) {
            umEditor = UE.getEditor('myContentEditor');
        }
    	var content = null;
    	umEditor.ready(function () {
    		content = umEditor.getContent();
    	});
        var showHead = $('#showHead').attr("value");
        var data = { privilege: content, showHead: showHead };
        $.post(baseUrl + "alertPrivilege.json", ko.toJS(data), function (result) {
            if (result.success) {
                alert("发布成功！");
            } else {
                alert("发布失败！");
            }
        }, "json");
    };
    /*page.run = function() {
        ko.applyBindings(new ObjectViewModel(), document.getElementById('div_main2'));
    };*/
