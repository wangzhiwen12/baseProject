<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <link href="${ctx}/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/coupon/grp/addgener.js"></script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" action="#">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group form-md-line-input">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span>
                    电子优惠券</label>

                <div class="col-xs-6 ">
                    <input type="text" class="form-control"
                           name="CouponName" id="CouponName" readonly>
                    <input type="hidden" name="couponId" id="couponId">
                </div>
                <div class="col-xs-3">
                    <button id="btnSearchTPL" type="button" class="btn btn-success">查询</button>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span>
                    OPENID</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="openId" id="openId">
                </div>
                <div class="col-xs-3"></div>
            </div>

        </div>
        <footer class="panel-footer text-center bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
</div>
</form>
</body>
</html>