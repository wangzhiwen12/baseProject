$(function(){
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    url: rootPath + '/boundController/getInfoByStroeCode.shtml',
	    success: function(wxpageBound) {
    		if(wxpageBound != null){
    			$('#WxHeadHtml *').remove();
    			$('#WxHomeHtml *').remove();
    			var wxHeadHtml = wxpageBound.wxHeadHtml;
    			var wxHomeHtml = wxpageBound.wxHomeHtml;
    			$('#WxHeadHtml').append(wxHeadHtml);
    			$('#WxHomeHtml').append(wxHomeHtml);
    			//绑定事件
    			$("#isDisabled").on("click", function(){
    				if(this.checked == true){
    					$('#activity').css("display","none");
    					$('#isDisabled').attr("checked","");
    				}else{
    					$('#activity').css("display","");
    					$('#isDisabled').removeAttr("checked");
    				}
    			});
    			$('#save-btn').on("click", function(){
    				var wxHeadHtml = $('#WxHeadHtml').html();
    				var wxHomeHtml = $('#WxHomeHtml').html();
    				$.ajax({
    				    type: 'post',
    				    dataType: "json",
    				    data : {
    				    	'wxHeadHtml' : wxHeadHtml,
    				    	'wxHomeHtml' : wxHomeHtml
    				    },
    				    url: rootPath + '/boundController/insertSelective.shtml',
    				    success: function(wxpageBound) {
    			    		alert("success");
    				    }
    				});
    			});
    			$('#checkBindMoblieCode').on("click", function(){
    				if(this.checked == true){
    					$('#phone-yzm').css("display","");
    					$('#checkBindMoblieCode').attr("checked","");
    				}else{
    					$('#phone-yzm').css("display","none");
    					$('#checkBindMoblieCode').removeAttr("checked");
    				}
    			});
    			$('#checkboxBirthday').on("click", function(){
    				if(this.checked == true){
    					$('#phone-sr').css("display","");
    					$('#checkboxBirthday').attr("checked","");
    				}else{
    					$('#phone-sr').css("display","none");
    					$('#checkboxBirthday').removeAttr("checked");
    				}
    			});
    			$('#checkboxCode').on("click", function(){
    				if(this.checked == true){
    					$('#phone-hykh').css("display","");
    					$('#checkboxCode').attr("checked","");
    				}else{
    					$('#phone-hykh').css("display","none");
    					$('#checkboxCode').removeAttr("checked");
    				}
    			});
    			$('#checkboxPwd').on("click", function(){
    				if(this.checked == true){
    					$('#phone-pwd').css("display","");
    					$('#checkboxPwd').attr("checked","");
    				}else{
    					$('#phone-pwd').css("display","none");
    					$('#checkboxPwd').removeAttr("checked");
    				}
    			});
    			$('#checkboxIdCardNo').on("click", function(){
    				if(this.checked == true){
    					$('#card-sfzh').css("display","");
    					$('#checkboxIdCardNo').attr("checked","");
    				}else{
    					$('#card-sfzh').css("display","none");
    					$('#checkboxIdCardNo').removeAttr("checked");
    				}
    			});
    			$('#checkboxType1').on("click", function(){
    				if(this.checked == true){
    					$('#phone_binding').css("display","");
    					$('#ForPhone').css("display","");
    					$('#li-phone').css("display","");
    					$('#checkboxType1').attr("checked","");
    				}else{
    					$('#checkboxType1').removeAttr("checked");
    					$('#phone_binding').css("display","none");
    					$('#ForPhone').css("display","none");
    					$('#li-phone').css("display","none");
    					$('#li-phone').removeClass();
    					$('#ForPhone').attr("class","tab-pane fade");
    					$('#ForIdCardNo').attr("class","tab-pane fade active in");
    					$('#li-card').attr("class","active");
    				}
    			});
    			$('#checkboxType2').on("click", function(){
    				if(this.checked == true){
    					$('#card_binding').css("display","");
    					$('#ForIdCardNo').css("display","");
    					$('#li-card').css("display","");
    					$('#checkboxType2').attr("checked","");
    				}else{
    					$('#checkboxType2').removeAttr("checked");
    					$('#card_binding').css("display","none");
    					$('#ForIdCardNo').css("display","none");
    					$('#li-card').css("display","none");
    					
    					$('#li-card').removeClass();
    					$('#ForIdCardNo').attr("class","tab-pane fade");
    					$('#ForPhone').attr("class","tab-pane fade active in");
    					$('#li-phone').attr("class","active");
    				}
    			});
    		}
	    }
	});
});

$('#save-btn').click(function (){
	var wxHeadHtml = $('#WxHeadHtml').html();
	var wxHomeHtml = $('#WxHomeHtml').html();
	$.ajax({
	    type: 'post',
	    dataType: "json",
	    data : {
	    	'wxHeadHtml' : wxHeadHtml,
	    	'wxHomeHtml' : wxHomeHtml
	    },
	    url: rootPath + '/boundController/insertSelective.shtml',
	    success: function(wxpageBound) {
    		alert("success");
	    }
	});
});

$('#isDisabled').click(function (){
	if(this.checked == true){
		$('#activity').css("display","none");
		$('#isDisabled').attr("checked","");
	}else{
		$('#activity').css("display","");
		$('#isDisabled').removeAttr("checked");
	}
});
$('#checkBindMoblieCode').click(function (){
	if(this.checked == true){
		$('#phone-yzm').css("display","");
		$('#checkBindMoblieCode').attr("checked","");
	}else{
		$('#phone-yzm').css("display","none");
		$('#checkBindMoblieCode').removeAttr("checked");
	}
});
$('#checkboxBirthday').click(function (){
	if(this.checked == true){
		$('#phone-sr').css("display","");
		$('#checkboxBirthday').attr("checked","");
	}else{
		$('#phone-sr').css("display","none");
		$('#checkboxBirthday').removeAttr("checked");
	}
});
$('#checkboxCode').click(function (){
	if(this.checked == true){
		$('#phone-hykh').css("display","");
		$('#checkboxCode').attr("checked","");
	}else{
		$('#phone-hykh').css("display","none");
		$('#checkboxCode').removeAttr("checked");
	}
});
$('#checkboxPwd').click(function (){
	if(this.checked == true){
		$('#phone-pwd').css("display","");
		$('#checkboxPwd').attr("checked","");
	}else{
		$('#phone-pwd').css("display","none");
		$('#checkboxPwd').removeAttr("checked");
	}
});
$('#checkboxIdCardNo').click(function (){
	if(this.checked == true){
		$('#card-sfzh').css("display","");
		$('#checkboxIdCardNo').attr("checked","");
	}else{
		$('#card-sfzh').css("display","none");
		$('#checkboxIdCardNo').removeAttr("checked");
	}
});

//
$('#checkboxType1').click(function (){
	if(this.checked == true){
		$('#phone_binding').css("display","");
		$('#ForPhone').css("display","");
		$('#li-phone').css("display","");
		$('#checkboxType1').attr("checked","");
	}else{
		$('#checkboxType1').removeAttr("checked");
		$('#phone_binding').css("display","none");
		$('#ForPhone').css("display","none");
		$('#li-phone').css("display","none");
		$('#li-phone').removeClass();
		$('#ForPhone').attr("class","tab-pane fade");
		$('#ForIdCardNo').attr("class","tab-pane fade active in");
		$('#li-card').attr("class","active");
	}
});



//
$('#checkboxType2').click(function (){
	if(this.checked == true){
		$('#card_binding').css("display","");
		$('#ForIdCardNo').css("display","");
		$('#li-card').css("display","");
		$('#checkboxType2').attr("checked","");
	}else{
		$('#checkboxType2').removeAttr("checked");
		$('#card_binding').css("display","none");
		$('#ForIdCardNo').css("display","none");
		$('#li-card').css("display","none");
		
		$('#li-card').removeClass();
		$('#ForIdCardNo').attr("class","tab-pane fade");
		$('#ForPhone').attr("class","tab-pane fade active in");
		$('#li-phone').attr("class","active");
	}
});

