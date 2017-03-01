<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/assets/global/plugins/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/coupon/grp/coupon_detail.js"></script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" action="#">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group form-md-line-input">
                <label class="col-xs-3 control-label text-right">
                    优惠券编码</label>

                <div class="col-xs-6 ">
                    <input type="text" class="form-control"
                           name="CouponCode" id="CouponCode" value="${res.couponCode}" readonly>
                    <input type="hidden" id="CouponQRCode" value="${res.couponQRCode}">
                </div>
                <div class="col-xs-3">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group form-md-line-input">
                <label class="col-xs-3 control-label text-right">
                    优惠券名称</label>

                <div class="col-xs-6 ">
                    <input type="text" class="form-control"
                           name="CouponName" id="CouponName" value="${res.title}" readonly>
                </div>
                <div class="col-xs-3">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">
                    副标题</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="openId" id="openId" value="${res.subTitle}" readonly>
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">
                    可用时间</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="endTimestamp" id="endTimestamp" value="${res.endTimestamp}" readonly>
                </div>
                <div class="col-xs-3"></div>
            </div>
        </div>
        <div class="form-group" >
            <div  class="col-xs-3"></div>
            <div id="code" class="col-xs-6">
            </div>
            <div  class="col-xs-3"></div>
        </div>
        <footer class="panel-footer text-center bg-light lter">
            <button type="button" onclick="javascript:parent.layer.close(parent.pageii);" class="btn btn-default btn-s-xs">取消</button>
            <button type="button" id="btnCouponCode" class="btn btn-success btn-s-xs">编码核销</button>
            <button type="button" id="btnCouponQRCode" class="btn btn-success btn-s-xs">二维码核销</button>
        </footer>
    </section>
</form>
</div>
</form>
</body>
<script type="text/javascript">
   generQRCode("${res.couponQRCode}");
</script>
</html>