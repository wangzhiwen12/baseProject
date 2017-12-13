<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/material/materialList.js"></script>
</head>
<body>
	<div id = "no_key_msg_reply_div">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active" id="textli">
				<a href="#imagediv" data-toggle="tab" id="imageMaterial">
					<span class="glyphicon glyphicon-pencil"></span>图片素材
				</a>
			</li>
			<li id="imageli">
				<a href="#newsdiv" data-toggle="tab" id="newsMaterial">
					<span class="glyphicon glyphicon-picture"></span>图文素材
				</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="imagediv">
				<div id = "imageTable">
					<button type="button" id="imageAdd" onclick="imageAdd();" class="btn btn-primary">新建图片素材</button></th>
					<img alt="" src="file:///home/yedong/02.jpg" />img
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="imageList">
						<thead>
							<tr role="row">
								<th style="display: none;">pic1</th>
								<th style="display: none;">pic2</th>
								<th style="display: none;">pic3</th>
								<th style="display: none;">pic4</th>
								<th style="display: none;">pic5</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="newsdiv">
				<div id = "newsTable">
					<button type="button" id="newsAdd" onclick="newsAdd();" class="btn btn-primary">新建图文素材</button>
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="newsList">
						<thead>
							<tr role="row">
								<th style="display: none;">news1</th>
								<th style="display: none;">news2</th>
								<th style="display: none;">news3</th>
								<th style="display: none;">news4</th>
								<th style="display: none;">news5</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>