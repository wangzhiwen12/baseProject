<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/material/newsJs.js"></script>
</head>
<body>
	<div>
		<input type="hidden" id="type" value="${type}" />
		<div id="imageDiv">
		
		</div>
		<div>
			<button type="button" class="btn btn-success" id="yes_btn">确定</button>
		</div>
	</div>
</body>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script type="text/javascript">
	var start = 0;
	var limit = 20;
	$(function (){
		imageList();
	});
	$(window).scroll(function () {
	    var scrollTop = $(this).scrollTop();
	    var scrollHeight = $(document).height();
	    var windowHeight = $(this).height();
	    if (scrollTop + windowHeight == scrollHeight) {
	    	start = start + limit;
	    	limit = limit + limit;
	    	imageList();
	    	
	  	//此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
	
		//var page = Number($("#redgiftNextPage").attr('currentpage')) + 1;
		//redgiftList(page);
		//$("#redgiftNextPage").attr('currentpage', page + 1);
	    }
	});
	
	function imageList(){
		$.ajax({
			type : "POST",
			dataType: "JSON",
			data : {
				'start':start,
				'limit':limit,
				'material_type':'image'
			},
			url : "${pageContext.request.contextPath}/materialLocal/getMaterialList.shtml",
			success: function (imgList) {
				var list = imgList;
				for(var i =0;i<list.length;i++){
					var media = JSON.stringify(list[i]);
					var img = 
					'<div id="wrap" style="float: left;width:20%;text-align: center;">' + 
					'<img alt="" src="' + list[i].localUrl +'" style="height: 140px;width: 140px;">' +
					'<input type="hidden" id="'+list[i].mediaId+'" value=\''+ media +'\' >' +  
					//'<input type="hidden" id="' + list[i].mediaId + '" value="' + list[i].localUrl +'" />' +
					//'<input type="hidden" id="local' + list[i].mediaId + '" value="' + list[i].imageName +'" />' +
					//'<input type="hidden" id="picUrl' + list[i].mediaId + '" value="' + list[i].picUrl +'" />' +
					'<br>' +
					'<input type="radio" value="' + list[i].mediaId + '" name="image" />' +
					'</div>';
					$("#imageDiv").append(img);
				} 
	        }
		});
	}

	$('#yes_btn').click(function(){
		var mediaId = $('#wrap input[name="image"]:checked ').val();
		if(mediaId != null){
			var media = $('#' + mediaId).val();
			var type = $('#type').val();
			if(type == 1){
				parent.imageSwitch1(JSON.parse(media));
			}else{
				parent.imageSwitch(JSON.parse(media));
			}
		}
		//　关闭弹出页
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
</script>

</html>