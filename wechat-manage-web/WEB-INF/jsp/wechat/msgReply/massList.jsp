<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/msgReply/massList.js"></script>
</head>
<body>
	<div id="yes_msg_mass_div" >
		<button type="button" class="btn btn-default btn-sm" id="send_btn" >
			<span class="glyphicon glyphicon-plus"></span>发送消息
        </button>
        <input type="hidden" id="mass_sid" />
        <div id = "massTable">
			<table
				class="table table-striped table-bordered table-hover dataTable no-footer"
				id="massManagerList">
				<thead>
					<tr role="row">
						<th>消息编号</th>
						<th>发送时间</th>
						<th>消息类型</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="no_msg_mass_div" style="display: none;" >
		<div id="wrap">
			已入会会员：<input type="radio" name="user_groups" value="0" checked /><br>
			未入会粉丝：<input type="radio" name="user_groups" value="1" /><br>
			Everybody：<input type="radio" name="user_groups" value="2" /><br>
		</div>
		<div id = "no_key_msg_reply_div" style="margin-top: 30px;">
			<ul id="myTab" class="nav nav-tabs">
				<li class="active" id="textli">
					<a href="#textdiv" data-toggle="tab" id="textInput">
						<span class="glyphicon glyphicon-pencil"></span>文字
					</a>
				</li>
				<li id="imageli">
					<a href="#imagediv" data-toggle="tab" id="imageInput">
						<span class="glyphicon glyphicon-picture"></span>图片
					</a>
				</li>
				<li id="newsli">
					<a href="#newsdiv" data-toggle="tab" id="newsInput">
						<span class="glyphicon glyphicon-picture"></span>图文
					</a>
				</li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="textdiv">
					<textarea id="textmsg" rows="10" cols="80"></textarea>
				</div>
				<div class="tab-pane fade" id="imagediv">
					<div class="btn-group">
						<button id="material_get" class="btn btn-default" type="button">素材中选择</button>
					</div>
					<div id="yl_img">
					</div>
				</div>
				<div class="tab-pane fade" id="newsdiv">
					<div class="btn-group">
						<button id="news_get" class="btn btn-default" type="button">素材中选择</button>
					</div>
					<div id="yl_news">
					</div>
				</div>
				<input id="mediaId" type="hidden" />
				<input id="msgType" type="hidden" value="text" />
			</div>
		</div>
		<div style="margin-top: 10%;">
			<button id="btn_1" type="button">发送</button>
		</div>
	</div>
</body>
<script type="text/javascript">
function imageSwitch(media){
	$('#yl_img *').remove();
	$("#mediaId").attr("value",media.mediaId);
	var img = '<img style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;" src="'+ media.localUrl　+'" />';
	var imgUrl = '<input id="imageUrl" type="hidden" value="'+ media.imageName +'" />';
	var localUrl = '<input id="localUrl" name="localUrl" type="hidden" value="'+ media.localUrl +'" />';
	$('#yl_img').append(img);
	$('#yl_img').append(imgUrl);
	$('#yl_img').append(localUrl);
}
</script>
</html>