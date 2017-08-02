<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/global/plugins/icheck/demo/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/wshop/wpage/productList.js"></script>
<style type="text/css">
.clearfix:after {
	visibility: hidden;
	display: block;
	font-size: 0;
	content: " ";
	clear: both;
	height: 0;
}

.clearfix {
	*zoom: 1;
}

.listInfo {
	list-style: none;
	margin: 0;
	padding: 0;
}

.listInfo li {
	float: left;
	height: 30px;
	overflow: hidden;
	margin: 10px 15px 10px 0;
}

.listInfo li span {
	display: block;
	float: left;
	width: 100px;
	text-align: right;
}

input {
	-web-kit-appearance: none;
	-moz-appearance: none;
	border-radius: 4px;
	border: 1px solid #c8cccf;
	color: #6a6f77;
	outline: 0;
}
</style>
<div class="table-responsive">
	<div class="col-md-12">
		<div class="portlet box blue-hoki">
			<!-- BEGIN FORM-->
			<form action="#" class="form-inline"">
				<div class="form-body">
					<div class="table-toolbar">

						<ul class="listInfo clearfix">
							<li><span>专柜商品编码：</span> <input type="text"
								id="shoppeProSid_input" style="width: 91px;" maxlength="20" />
								~ <input type="text" id="maxShoppeProSid_input"
								style="width: 92px;" maxlength="20" /></li>

							<li><span>商品名称：</span> <input type="text"
								id="shoppeProName_input" style="width: 200px;" maxlength="20" /></li>

							<li style="float: right"><a class="btn btn-default shiny"
								onclick="productList();">查询</a> &nbsp;&nbsp; <a
								class="btn btn-default shiny" onclick="reset();">重置</a></li>
						</ul>
					</div>
				</div>
			</form>
		</div>

		<div class="portlet box blue-hoki">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>选择商品
				</div>
			</div>
			<div id="productTable" class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover dataTable no-footer"
					id="productList">
					<thead>
						<tr role="row">
							<th>选择</th>
							<th>商品名称</th>
							<th>商品货号</th>
							<th>所属分类</th>
							<th>创建时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<div>
	<button type="button" class="btn btn-success" id="yes_btn">确定</button>
</div>
<script type="text/javascript">
	$('#yes_btn').click(
			function() {
				var proSids = []
				proSids = getSelectPro() + "";
				if(proSids!=null&&proSids.length!=0){
					
					var regS = new RegExp(",", "g");
					var ids = proSids.replace(regS, ";");
					parent.$('#proCodes').val=ids;
					var goodsHTML="",goodsHTMLS="";
					console.log(ids);
					$.ajax({
						url : getRootPath() + "/shoppePro/getProListByGroupId.json?proCodes=" + ids,
						type : "get",
						dataType : "json",
						success : function(json, flag) {
						if(parent.document.getElementById("de").value=="default"){
							parent.$('#add_goods-wrapper *').remove();
							var input=' <input type="hidden" id="de" value ="pro"/>';
							parent.$('#add_goods-wrapper ').append(input);
							
						}else{
							
						}
							var jsonObj = eval('(' + json + ')');
							for ( var p in jsonObj) {
								if(p=="data"){
									for(var i=0;i<jsonObj[p].length;i++){
										console.log(jsonObj[p][i]);
										goodsHTML+=' <li class="goods-li"><a href="${pageContext.request.contextPath}/member/detail/detail.html?skuCode='+jsonObj[p][i].skuCode+'" class="goods-a"><div class="goods-product"><div class="goods-product-img">';
										goodsHTML+='<img src="http://img.wfjimg.com/'+jsonObj[p][i].picUrl+'">';
										goodsHTML+='</div><div class="goods-product-text">'+jsonObj[p][i].proName+'</div>';
										goodsHTML+='<p class="goods-product-text" >'+jsonObj[p][i].price+'</p></a></li>';
										goodsHTMLS+='<div class="select-pic-common"><p><span class="img-warp">' ;
										goodsHTMLS+='<img style="width:72px; height: 72px; display:block;" class="selected" data-tgt="src" src="http://img.wfjimg.com/'+ jsonObj[p][i].picUrl +'" data-scope="add_goods_'+jsonObj[p][i].skuCode+'.list.picture">' ;
										goodsHTMLS+='<span class="clearPic"><span> </span></span></span></p></div>';
									}
								}
							}
							
							parent.$('#add_goods-wrapper').append(goodsHTML,null);
							parent.$('#add_goodsS').append(goodsHTMLS);
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						},
						error : function(data) {
						}
					});
				}else{
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
</script>