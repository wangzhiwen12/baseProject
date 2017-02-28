<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建图文素材</title>
<script src="http://cdn.gbtags.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.gbtags.com/twitter-bootstrap/3.2.0/js/bootstrap.js"></script>
<script src="http://cdn.gbtags.com/summernote/0.5.2/summernote.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/layer-v1.9.2/layer/layer.js"></script>
<style type="text/css">
	@import url('http://cdn.gbtags.com/twitter-bootstrap/3.2.0/css/bootstrap.css');
    @import url('http://cdn.gbtags.com/font-awesome/4.1.0/css/font-awesome.min.css');
    @import url('http://cdn.gbtags.com/summernote/0.5.2/summernote.css');
</style>
<style type="text/css">
table {
	overflow: hidden;
}

tr {
	overflow: hidden;
	height: 50px;
}

.inputBorder {
	border: 1px;
	background: transparent;
	border-style: none;
}
.width {
	width:50%;
}
.zd {
	position:fixed; 
	_position:absolute;
	_top:expression(eval(document.documentElement.scrollTop));
	z-index:1000;
	top:0;
}
.zhenzhao{
	background-color: #000000; 
	filter:alpha(opacity=10);
 	-moz-opacity:0.5;
 	opacity:0.5;
 	position:absolute;
}
.multi-media-li{
	
}
.Absolute-Center {  
  margin: auto;  
  position: absolute;  
  top: 0; left: 0; bottom: 0; right: 0;  
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table style="margin-left: 5%;margin-top: 5%;">
		<tr>
			<td style="width: 20%;text-align: center;">
				<div class="zd">
					<h4>图文信息预览</h4>
					<div style="width: 210px; height: 142px; border: 2px solid #00CC33; margin-left: 35px;margin-top: 50px;">
						<div id="yl_img">
							<div id="image_sample" style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;background-color: #DDDDDD">
								<span class="glyphicon glyphicon-picture" style="font-size: 35px; margin-top: 42.5px; color: #808080"></span>
							</div>
						</div>
						<div style="width: 206px;height: 40px;">
							<div class="zhenzhao" style="width: 188px;height: 30px;margin-left: 8px;margin-top: -30px;text-align: left;">
								<span id="fontbt" style="font-size: 20px;color: #FFFFFF">标题</span>
							</div>
							<input id="mediaId" type="hidden" />
						</div>
					</div>
				</div>
			</td>
			<td style="width: 50%;">
				<form
					action="${pageContext.request.contextPath}/resources/addEntity.shtml"
					method="post" enctype="multipart/form-data">
					<div class="input-group input-group-lg">
						<input id="title" type="text" class="form-control inputBorder" style="width:600px;margin-bottom: 5px;"
							placeholder="请在这里输入标题"> <br>
					</div>
					<div class="input-group input-group-sm">
						<input id="author" type="text" class="form-control inputBorder" style="width:600px;"
							placeholder="请输入作者">
					</div>
					<hr>
					<div id="editor">
					</div>
					<input type="checkbox" id="checkbox1" />原文链接
					<br>
					<input id="content_source_url" type="text" class="width" style="display:none;" />
					<br>
					<hr>
					<label>封面</label>&nbsp;&nbsp;<label style="color: #808080">大图片建议尺寸：900像素 * 500像素</label>
					<br>
					<button id="material_upload" type="button" class="btn btn-primary">上传图片</button>
					<button id="material_get" type="button" class="btn btn-primary">从图片库选择</button>
					<br><br>
					<label>摘要</label>&nbsp;&nbsp;<label style="color: #808080">选填，如果不填写会默认抓取正文前54个字</label>
					<br>
					<textarea id="digest" rows="8" cols="73"></textarea>
					<br><br><br><br>
					<div style="text-align: center;">
						<button id="news_add_btn" type="button" class="btn btn-success" style="margin-right: 30px;">保存</button>
						<button id="close_btn" type="button" class="btn btn-danger" style="margin-left: 30px;">取消</button>
					</div>
				</form>
			</td>
			<td style="width: 20%;text-align: center;">
				<div class="zd" style="text-align: center;">
					<h4>多媒体</h4>
					<div>
						<ul class="list-unstyled">
							<li>
								<a href="#" class="btn btn-info btn-lg" style="width: 200px;margin-top: 20px;">
									<span class="glyphicon glyphicon-picture"></span> Picture
								</a>
							</li>
							<li>
        						<a href="#" class="btn btn-info btn-lg" style="width: 200px;margin-top: 20px;">
									<span class="glyphicon glyphicon-facetime-video"></span> Video
								</a>
							</li>
							<li>
								<a href="#" class="btn btn-info btn-lg" style="width: 200px;margin-top: 20px;">
									<span class="glyphicon glyphicon-music"></span> Music
								</a>
							</li>
						</ul>
					</div>
				</div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
$('#news_add_btn').click(
		function() {
			var title = $('#title').val();// 标题
			var thumb_media_id = $('#mediaId').val();// 图文消息的封面图片素材id（必须是永久mediaID）
			var author = $('#author').val();// 作者
			var digest = $('#digest').val();// 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
			var show_cover_pic = 1;// 是否显示封面，0 为false，即不显示，1 为true，即显示
			var content = $('#editor').code();// 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
			var content_source_url = $('#content_source_url').val();// 图文消息的原文地址，即点击“阅读原文”后的URL
			var url = $('#imageUrl').val();
			$.ajax({
				type : 'post',
				dataType : "json",
				data : {
					'title' : title,
					'thumb_media_id' : thumb_media_id,
					'author' : author,
					'digest' : digest,
					'show_cover_pic' : show_cover_pic,
					'content' : content,
					'content_source_url' : content_source_url,
					'url' : url
				},
				url : '${pageContext.request.contextPath}/material/articleAdd.shtml',
				success : function(paramMap) {
					if (paramMap.success = 'success') {
						alert("添加成功");
					}
				}
			});

		});

function divSwitch(media) {
	$("#mediaId").attr("value", media.media_id);
	$('#yl_img *').remove();
	var img = '<img style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;" src="'
			+ media.localUrl + '" />';
	var imgUrl = '<input id="imageUrl" type="hidden" value="' + media.url
			+ '" />';
	$('#yl_img').append(img);
	$('#yl_img').append(imgUrl);
}
$(function() {
	var pageii = null;
	$('#material_upload').click(function() {
		pageii = layer.open({
					title : "上传图片",
					type : 2,
					area : [ "50%", "50%" ],
					content : '${pageContext.request.contextPath}/material/imageAdd.shtml'
				});
	});
	$('#material_get').click(function() {
		pageii = layer.open({
					title : "图片素材",
					type : 2,
					area : [ "90%", "85%" ],
					content : '${pageContext.request.contextPath}/material/imageList.shtml'
				});
	});
	$('#editor').summernote({
		toolbar : [
				['style',[ 'bold', 'italic', 'underline','clear' ] ],
				['font',[ 'strikethrough', 'superscript','subscript' ] ],
				[ 'fontsize', [ 'fontsize' ] ],
				[ 'color', [ 'color' ] ],
				[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
				[ 'height', [ 'height' ] ] ],
		width : 600,
		height : 300, // set editor height
		minHeight : null, // set minimum height of editor
		maxHeight : null, // set maximum height of editor
		focus : true
	// set focus to editable area after initializing summernote
	});
	$('#close_btn').click(function() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
	$('#title').bind('input propertychange', function() {
		$('#fontbt').css("font-size", '20px');
		$('#fontbt').html($('#title').val());
	});
	$('#checkbox1').click(function() {
		var check = $('#checkbox1').is(':checked');
		if (check == true) {
			$('#content_source_url').show();
		} else {
			$('#content_source_url').hide();
		}
	});
});
</script>
</html>