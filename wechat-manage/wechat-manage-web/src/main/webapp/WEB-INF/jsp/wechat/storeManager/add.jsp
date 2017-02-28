<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/wechat/storeManager/add.js">
    </script>
    <style type="text/css">
        .col-sm-3 {
            width: 15%;
            float: left;
        }

        .col-sm-9 {
            width: 85%;
            float: left;
        }

        label[class^="btn btn-default"] {
            margin-top: -4px;
        }
    </style>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post"
      action="${ctx}/storeManager/addStore.shtml">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">门店编码</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入门店编码" name="storeCode" id="storeCode">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">门店名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息）" name="businessName" id="businessName">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">分店名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）" name="branchName" id="branchName">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">省</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入省"
                           name="province" id="province">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">市</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入市"
                           name="city" id="city">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">区</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入区"
                           name="district" id="district">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">街道地址</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入街道地址（不要填写省市信息）"
                           name="address" id="address">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">电话</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入电话（纯数字，区号、分机号均由“-”隔开）"
                           name="telephone" id="telephone">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">门店类型</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入门店类型(不同级分类用“,”隔开，如：美食,川菜,火锅。)"
                           name="categories" id="categories">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">坐标类型</label>
                <div class="col-sm-9">
                    <select id="offsetType" name="offsetType">
                        <option value="1" selected="selected">火星坐标</option>
                        <option value="2">sogou经纬度</option>
                        <option value="3">百度经纬度</option>
                        <option value="4">mapbar经纬度</option>
                        <option value="5">GPS坐标</option>
                        <option value="6">sogou墨卡托坐标</option>
                    </select>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">经度</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入经度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）"
                           name="longitude" id="longitude">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">纬度</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入纬度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）"
                           name="latitude" id="latitude">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">特色服务</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入特色服务"
                           name="special" id="special">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">营业时间</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入营业时间（24 小时制表示，用“-”连接，如8:00-20:00）"
                           name="openTime" id="openTime">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">人均价格</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入人均价格（大于0 的整数）"
                           name="avgPrice" id="avgPrice">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">商户简介</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入商户简介"
                           name="introduction" id="introduction">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">推荐品</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入推荐品"
                           name="recommend" id="recommend">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">所属集团</label>

                <div class="col-sm-9">
                    <select id="groupId" name="groupId" class="form-control m-b"
                            tabindex="-1">
                    </select>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">APPID</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入appid" name="appid" id="appid">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">APPSECRET</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入appsecret" name="appsecret" id="appsecret">
                </div>
            </div>
           <div class="line line-dashed line-lg pull-in"></div>
			<div class="ui-form-horizontal">
				<div class="ui-form-fields grid-nav-bar">
					<div class="ui-form-field-label grid-nav-bar-item">
						<span class="ui-form-field-label-span">背景图片：</span>
					</div>
					<div id ="imgp" class="ui-form-field-cot grid-nav-bar-item selectPicWrap">
						 <img id ="img" src="${store.localImgUrl}"
							alt="" data-scope="comheader_{{temp}}.img" data-tgt="src"
							style="height: 96px; width: 192px;"> <a href="javascript:;"
							onclick="selectImgUrl()">修改背景图</a>
					</div>
				</div>
			</div>
			<div class="form-group" style="display: none;">
				<label class="col-sm-3 control-label">本地图片地址</label>

				<div class="col-sm-3">
					<input type="text" class="form-control" placeholder=""
						name="localImgUrl" id="localImgUrl" value="${store.localImgUrl}">
				</div>
			</div>
			<div class="form-group" style="display: none;">
				<label class="col-sm-3 control-label">微信图片地址</label>

				<div class="col-sm-3">
					<input type="text" class="form-control" placeholder=""
						name="wechatImgUrl" id="wechatImgUrl"
						value="${store.wechatImgUrl}">
				</div>
			</div>
        </div>
        <footer class="panel-footer text-right bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
<script type="text/javascript">
//选择图片
function selectImgUrl() {
	pageii = layer.open({
		title : "图片素材",
		type : 2,
		area : [ "100%", "100%" ],
		content : '${pageContext.request.contextPath}/materialLocal/imageList.shtml'
	});
}

function imageSwitch(media) {
	$('#imgp *').remove();
	var wechatImgUrl = document.getElementById("wechatImgUrl");
	var localImgUrl = document.getElementById("localImgUrl");
	var img = '<img style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;" src="'+ media.localUrl　+'" />';
	var a ='<a href="javascript:;" onclick="selectImgUrl()">修改背景图</a>';
	$('#imgp').append(img);
	$('#imgp').append(a);
	wechatImgUrl.value = media.picUrl;
	localImgUrl.value = media.localUrl;
}
</script>
</body>
</html>