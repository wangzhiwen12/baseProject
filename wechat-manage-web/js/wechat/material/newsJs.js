var rootPath = "/notebook";
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
			alert("aaa");
			$.ajax({
				type : 'post',
				dataType : "json",
				data : {
					'title' : title,
					'thumb_media_id' : thymb_media_id,
					'author' : author,
					'digest' : digest,
					'show_cover_pic' : show_cover_pic,
					'content' : content,
					'content_source_url' : content_source_url,
					'url' : url
				},
				url : rootPath + '/material/articleAdd.shtml',
				success : function(paramMap) {
					alert("aaa");
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
			+ media.url + '" />';
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
					area : [ "50%", "30%" ],
					content : rootPath + '/material/imageAdd.shtml'
				});
	});
	$('#material_get').click(function() {
		pageii = layer.open({
					title : "图片素材",
					type : 2,
					area : [ "90%", "85%" ],
					content : rootPath + '/material/imageList.shtml'
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