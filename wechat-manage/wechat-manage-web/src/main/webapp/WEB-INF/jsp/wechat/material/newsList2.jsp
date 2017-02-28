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
		<div id="newsDiv">
			
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
		newsList();
	});
	$(window).scroll(function () {
	    var scrollTop = $(this).scrollTop();
	    var scrollHeight = $(document).height();
	    var windowHeight = $(this).height();
	    if (scrollTop + windowHeight == scrollHeight) {
	    	start = start + limit;
	    	limit = limit + limit;
	    	newsList();
	    	
	  	//此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
	
		//var page = Number($("#redgiftNextPage").attr('currentpage')) + 1;
		//redgiftList(page);
		//$("#redgiftNextPage").attr('currentpage', page + 1);
	    }
	});
	
	function newsList(){
		$.ajax({
			type : "POST",
			dataType: "JSON",
			data : {
				'start':start,
				'limit':limit,
				'material_type':'news'
			},
			url : "${pageContext.request.contextPath}/materialLocal/getMaterialList.shtml",
			success: function (newsList) {
				var list = newsList;
				for(var i =0;i<list.length;i++){
					var news = 
					'<div style="float: left;width:20%;text-align: center;">' + 
               		'<span>'+list[i].updateTime+'</span>' +
               		'<hr>' +
               		'<div style="text-align: center;">' +
               		'<span>'+list[i].title+'</span><br>' +
               		'<a href="'+list[i].picUrl+'"><img src="'+list[i].localUrl+'" style="height: 150px;width: 85%;" class="img-thumbnail"></a><br>' +
					'<br>' +
					'<input type="radio" value="' + list[i].mediaId + '" name="image" />' +
               		'<input type="hidden" id="mediaId" value="' + list[i].mediaId +'" />' +
               		'<input type="hidden" id="title'+list[i].mediaId+'" value="' + list[i].title +'" />' +
               		'<input type="hidden" id="url'+list[i].mediaId+'" value="' + list[i].localUrl +'" />' +
               		'</div>' +
               		'</div>';
					$("#newsDiv").append(news);
				} 
	        }
		});
	}

	$('#yes_btn').click(function(){
		var mediaId = $('input[name="image"]:checked ').val();
		if(mediaId != null){
			parent.$("#mediaId").attr("value",mediaId);
			parent.$('#yl_news *').remove();
			var news = 
				'<div style="float: left;width:20%;text-align: center;">' + 
           		'<div style="text-align: center;">' +
           		'<span>'+$('#title' + mediaId).val()+'</span><br>' +
           		'<img src="'+$('#url' + mediaId).val()+'" style="height: 150px;width: 85%;" class="img-thumbnail"><br>' +
				'<br>' +
           		'</div>' +
           		'</div>';
				
			parent.$('#yl_news').append(news);
		}
		//　关闭弹出页
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
</script>

</html>