<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <link href="${ctx}/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
          rel="stylesheet"/>
    <script type="text/javascript"
            src="${ctx}/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="${ctx}/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/coupon/grp/add.js"></script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" action="#">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group form-md-line-input">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span>
                    券模板</label>

                <div class="col-xs-6 ">
                    <input type="text" class="form-control" name="txtTplName2" id="txtTplName2" readonly>
                    <input type="hidden" name="txtTplName" id="txtTplName">
                </div>
                <div class="col-xs-3">
                    <button id="btnSearchTPL" type="button" class="btn btn-success">查询</button>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span>
                    券名称</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="txtCouponName" id="txtCouponName">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">副标题</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control" name="txtSubtitle"
                           id="txtSubtitle">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">卡券总数</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control" name="quantity"
                           id="quantity">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">每人次可领数量</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control" name="getLimit"
                           id="getLimit">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">适用系统</label>

                <div class="col-xs-6">
                    <select class="form-control" id="selSys" name="selSys">
                        <option value="pos">pos</option>
                    </select>
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">有效期</label>

                <div class="col-xs-6">
                    <div class="input-daterange input-group" id="datepicker">
                        <input class="form_datetime form-control" type="text" name="couponExpStart" id="qBeginTime">
                        <span class="input-group-addon">至</span>
                        <input class="form_datetime form-control" type="text" name="couponExpEnd" id="qEndTime">
                    </div>
                </div>
                <div class="col-xs-3">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">使用说明</label>

                <div class="col-xs-6">
                      <textarea class="form-control" rows="3" cols="10" name="txtUseDesc"
                                id="txtUseDesc" placeholder="用于向买家展示，如该优惠券不得与其他活动同时使用；节假日不得使用等，回车即可划分段落"></textarea>
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">选择封面</label>
                <button id="material_get" type="button" class="btn btn-primary">从图片库选择</button>
                <div id="yl_img" style="text-align: center;">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right"><span
                        style="color:red">* </span>审核人</label>

                <div class="col-xs-6">
                    <div class="input-group">
                        <input type="hidden" name="txtApprovalUserName" id="txtApprovalUserName">
                        <input type="text" class="form-control"
                               name="txtApprovalUserName2" id="txtApprovalUserName2" readonly>
                        <span class="input-group-addon"><a class="btn-link" id="btnUser"><i class="fa fa-user"></i></a></span>
                    </div>
                </div>

                <div class="col-xs-3"></div>
            </div>
        </div>
        <footer class="panel-footer text-center bg-light lter">
            <button type="button" onclick="javascript:parent.layer.close(parent.pageii);"
                    class="btn btn-default btn-s-xs">取消
            </button>
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
</div>
</form>
</body>
<script type="text/javascript">
    $('#material_get').click(function () {
        pageii = layer.open({
            title: "图片素材",
            type: 2,
            area: ["100%", "100%"],
            content: '${pageContext.request.contextPath}/materialLocal/imageList.shtml'
        });
    });
    function imageSwitch(media) {
        $('#yl_img *').remove();
        $("#mediaId").attr("value", media.mediaId);
        var img = '<img style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;" src="' + media.localUrl + '" />';
        var imgUrl = '<input id="imageUrl" type="hidden" value="' + media.imageName + '" />';
        var localUrl = '<input id="localUrl" name="localUrl" type="hidden" value="' + media.localUrl + '" />';
        $('#yl_img').append(img);
        $('#yl_img').append(imgUrl);
        $('#yl_img').append(localUrl);
    }
</script>
</html>