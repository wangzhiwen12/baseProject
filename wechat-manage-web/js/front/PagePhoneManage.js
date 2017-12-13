//初始化方法
var ncity = 0;
var nbirthday = 0
var nname = 0;
var ngender = 0;
var dtitle = "";
var dimg = "";
var ddescription = "";// 页面底部说明
var dagreement = ""; // 用户协议
var dphoneno = "0";
var curPicSel;
function imageSwitch(media) {
	$('#yl_img *').remove();
	var img = '<div id="wrap" style="background-size: 100% 100%; height: 158px;">'
		+ '<img alt="" src="'
		+ media.localUrl
		+ '" style="height:100%;width:100%;">'
		+ '<input type="hidden" id="'
		+ media.mediaId
		+ '" value=\''
		+ media + '\' >' + '<br>' + '</div>';
	$("#yl_img").append(img);
	dimg = media.localUrl;
	$('#material_get *').remove();
	var imgs = '<img  width="140" height="70" src="' + media.localUrl + '">'
	$("#material_get").append(imgs);
}
$(function() {


	$("#adddoc").click(function() {
		showdiv();
		showdtable();
	});
	$("#btnclose").click(function() {
		showdiv();
		showdtable();
	});
	$("#btnadd").click(function() {
		addpage();
	});
	$("#activePhone").click(function() {
		saveInfo();
	});

	function saveInfo() {
		var dtitle = $("#idtitle").val();
		var dcity = "";
		var dbirthday = "";
		var dname = "";
		var dgender = "";
		var img = "";
		var ddescription = "";// 页面底部说明
		var dagreement = ""; // 用户协议
		if (document.getElementById("ycity").checked) {
			dcity = 1;
		} else {

		}
		if (document.getElementById("ybirthday").checked) {
			dbirthday = 1;
		}
		if (document.getElementById("yname").checked) {
			dname = 1;
		}
		if (document.getElementById("ygender").checked) {
			dgender = 1;
		}
		var ue = UE.getEditor("myEditorUserAgreement",{UEDITOR_HOME_URL:"./wfj-ueditor/"});
		var dd = ue.getContent();
    	//ue.render("agreementContentEditor");
		if(document.getElementById("agreementContentEditor").innerHTML != null){
			dagreement = dd;
		}
		var ue2 = UE.getEditor("myEditor",{UEDITOR_HOME_URL:"./wfj-ueditor/"});
		var dd2 = ue2.getContent();
    	//ue2.render("descriptionContentEditor");
		if(document.getElementById("descriptionContentEditor").innerHTML != null){
			ddescription = dd2;
		}
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : rootPath + '/registered/phoneRegistered.shtml',
			dataType : "json",
			data : "ncity=" + ncity + "&nbirthday=" + nbirthday + "&nname="
			+ nname + "&ngender=" + ngender + "&dtitle=" + dtitle
			+ "&dimg=" + dimg + "&ddescription=" + ddescription
			+ "&dagreement=" + dagreement + "&dphoneno=" + dphoneno,
			success : function(response) {
				alert("保存成功！")
			},
			error : function() {
				alert("error");
			}
		});

	}

	$('#material_get').click(function() {

		pageii = layer.open({
			title : "图片素材",
			type : 2,
			area : [ "50%", "85%" ],
			content : rootPath + '/materialLocal/imageList.shtml'
		});
	});

	function addpage() {
		$('#titledesc *').remove();
		$("#titledesc").append('<span class="apptop_tittle">'+$("#idtitle").val()+'</span>');


		if (document.getElementById("ycity").checked) {
			if (ncity == 0) {
				ncity = 1;
				$("#city")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">所在城市</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
			}
		} else {
			ncity = 0;
			$('#city *').remove();
		}
		if (document.getElementById("ybirthday").checked) {
			if (nbirthday == 0) {
				nbirthday = 1;
				$("#birthday")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">生日</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
			}
		} else {
			nbirthday = 0;
			$('#birthday *').remove();
		}

		if (document.getElementById("yname").checked) {
			if (nname == 0) {
				nname = 1;
				$("#name")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">姓名</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
			}
		} else {
			nname = 0;
			$('#name *').remove();
		}

		if (document.getElementById("ygender").checked) {
			if (ngender == 0) {
				ngender = 1;
				$("#gender")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">性别</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
			}
		} else {
			ngender = 0;
			$('#gender *').remove();
		}
	}

	function showdiv() {

		/*
		 *
		 * 原理相同只是写法不同，判断show的display是不是none 是则设为block显示不是则设为none隐藏
		 *
		 */

		document.getElementById('aaa').style.display = document
			.getElementById('aaa').style.display == "none" ? "block"
			: "none";

	}

	function showdtable() {

		/*
		 *
		 * 原理相同只是写法不同，判断show的display是不是none 是则设为block显示不是则设为none隐藏
		 *
		 */

		document.getElementById('bbb').style.display = document
			.getElementById('bbb').style.display == "none" ? "block"
			: "none";

	}
	initialization();
//	ObjectViewModel();

//	var umEditor2;
//	umEditor2 = UE.getEditor('descriptionContentEditor');
//	var ddd = umEditor2.getContent();alert("ddd"+ddd);
//	umEditor2.render("descriptionContentEditor");
});

function ObjectViewModel() {
	var self = this;
	var umEditor;
	self.editContent = function() {
		self.loadPrivilege();
	};
	self.deleteContent = function() {
		umEditor.setContent("");
	};
//	self.setUEditor = function(content) {
//		if (!umEditor) {
//			UE.delEditor('agreementContentEditor');
//			umEditor = UE.getEditor('agreementContentEditor');
//		}
//		umEditor.ready(function() {
//			umEditor.setContent(content);
//		});
//	};
//	self.loadPrivilege = function() {
//		$.post(baseUrl + "getPrivilege.json", function(data) {
//			if (data) {
//				var data = data.data;
//				self.setUEditor(data.privilege);
//			}
//		}, "json");
//	};

//	self.loadBrandInfo = function() {
//		$.post((baseUrl + "GetBrandInfo"), function(data) {
//			if (data) {
//				self.brandName(data.Name);
//				self.brandLogo(data.LogoFullUrl);
//			}
//		});
//	};

//	self.loadPrivilege();

}

function initialization() {
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		url : rootPath + '/registered/selectPhoneRegistered.shtml',
		dataType : "json",
		data : "ncity=" + ncity,
		success : function(response) {

			ncity = response.list[0].city;
			nbirthday =response.list[0].birthday;
			nname = response.list[0].name;
			ngender =response.list[0].gender;

			$('#titledesc *').remove();
			$("#titledesc").append('<span class="apptop_tittle">'+response.list[0].title+'</span>');

			$("#idtitle").val(response.list[0].title);
			$('#yl_img *').remove();
			var img = '<div id="wrap" style="background-size: 100% 100%; height: 158px;">'
				+ '<img alt="" src="'
				+ response.list[0].backImage
				+ '" style="height:100%;width:100%;">'
				+ '<input type="hidden" id="'
				+ response.list[0].backImage
				+ '" value=\''
				+ response.list[0] + '\' >' + '<br>' + '</div>';
			$("#yl_img").append(img);
			dimg = response.list[0].backImage;
			$('#material_get *').remove();
			var imgs = '<img  width="140" height="70" src="'
				+ response.list[0].backImage + '">'
			$("#material_get").append(imgs);

			if (response.list[0].city == 1) {
				$("#city")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">所在城市</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
				document.getElementById("ycity").checked=true;

			}
			if (response.list[0].birthday == 1) {
				$("#birthday")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">生日</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
				document.getElementById("ybirthday").checked=true;
			}

			if (response.list[0].name == 1) {
				$("#name")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">姓名</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
				document.getElementById("yname").checked=true;
			}
			if (response.list[0].gender == 1) {
				$("#gender")
					.append(
						'<label class="ui-form-field mg-40"><span class="ui-form-field-inner"><b data-bind="text:FieldName">性别</b><span class="ui-input-operation"><span class="ui-input-operation-cell"></span></span></span></label>');
				document.getElementById("ygender").checked=true;
			}
		},
		error : function() {
			alert("error1");
		}
	});

}