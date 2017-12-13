<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/coupon/tpl/add.js"></script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" action="#">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group form-md-line-input">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span> 券类型</label>

                <div class="col-xs-6">
                    <select class="form-control" id="selCouponType" name="selCouponType">
                    </select>
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span> 券面值</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="txtCouponValue" id="txtCouponValue">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">使用门槛</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control" name="txtCouponPriceLimit" id="txtCouponPriceLimit">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right" >品牌券模板</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="txtCouponName" id="txtCouponName">
                </div>
                <div class="col-xs-3"></div>
            </div>
            
            
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">券流水位数</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                                   placeholder="请输入券流水位数" name="noLength" id="noLength" >
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">券号起始位</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                                   placeholder="请输入券号起始位" name="prefixStr" id="prefixStr" >
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">券号前缀字符</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                                   placeholder="请输入券号前缀字符" name="startNo" id="startNo" >
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">券号后缀长度</label>

                <div class="col-xs-6">
                     <input type="text" class="form-control"
                                   placeholder="请输入券号后缀长度" name="suffixLength" id="suffixLength" >
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">背景颜色</label>

                <div class="col-xs-6">
                	<select class="form-control" id="cpbackground" name="cpbackground" onchange="selChange();" >
	                	<option id="option1" selected="selected" value="1">请选择卡券背景颜色</option>
	                	<c:forEach items="${bgList}" var="bg">
	                		<option style="background: ${bg.name };" value="${bg.name }"></option>
	                	</c:forEach>
                    </select>
                </div>
                <div class="col-xs-3"></div>
            </div>
            
        </div>
        <footer class="panel-footer text-center bg-light lter">
            <button type="button" onclick="javascript:parent.layer.close(parent.pageii);" class="btn btn-default btn-s-xs">取消</button>
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
</body>
<script type="text/javascript">
	function selChange(){
		$("#option1").remove();
	    $("#cpbackground").css("background-color",$("#cpbackground").val());
	}
</script>
</html>