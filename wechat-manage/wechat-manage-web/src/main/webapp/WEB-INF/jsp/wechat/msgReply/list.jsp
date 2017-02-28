<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/msgReply/list.js"></script>
</head>
<body>
	<div>
		<div class="btn-group">
			<button id="subscribe_btn" type="button" class="btn btn-default" onclick="sdk_btn('subscribe');">被添加自动回复</button>
			<button id="dis_reply_btn" type="button" class="btn btn-default" onclick="sdk_btn('disReply');">消息自动回复</button>
			<button id="key_reply_btn" type="button" class="btn btn-default" onclick="sdk_btn('ruleKey');">关键字自动回复</button>
		</div>
		<hr>
		<div id="yes_key_msg_reply_div" style="display: none;" >
			<button type="button" class="btn btn-default btn-sm" id="addRule" >
				<span class="glyphicon glyphicon-plus"></span>新建规则
	        </button>
	        <div id = "replyTable">
				<table
					class="table table-striped table-bordered table-hover dataTable no-footer"
					id="replyManagerList">
					<thead>
						<tr role="row">
							<th>规则名称</th>
							<th>关键字</th>
							<th>回复类型</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="ruleDiv" style="display : none;">
			<hr>
			<div class="input-group">
				<span class="input-group-addon">规则名</span>
				<input type="text" class="form-control" id="rule_name" style="width:500px;">
			</div><br>
			<div class="input-group">
				<span class="input-group-addon">关键字</span>
				<input type="text" class="form-control" id="msg_key" style="width:500px;">
			</div><br>
		</div>
		<div id = "no_key_msg_reply_div">
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
						<button id="add_Image" class="btn btn-default" type="button">上传图片</button>
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
				<input type="hidden" id="msgType" name="msgType">
				<input type="hidden" id="eventType" name="eventType" value="subscribe" >
			</div>
		</div>
	</div>
	<div>
		<div style=" position: fixed;bottom: 0px;margin-bottom: 100px;">
			<button id="submit_btn" type="button" class="btn btn-success">保存</button>
		</div>
	</div>
</body>
</html>