<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/wshop/catagory/progrougadd.js"></script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" action="#">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group form-md-line-input">
                <label class="col-xs-2 control-label text-right">上级分组</label>
                <div class="col-xs-9">
                    <label id="groupparentname">/</label>
                </div>
                <div class="col-xs-1"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-2 control-label text-right">分组名称</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control"
                           name="groupname" id="groupname">
                </div>
                <div class="col-xs-1"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-2 control-label text-right">分组图片</label>
                <div class="col-xs-9">
                    <div class="grid-nav-bar">
                        <div style="padding-right: 10px; width: 64px;height: 64px;
                        display: table-cell;text-align: center; vertical-align:middle;">
                            <img id="groupPic" name="groupPic" class="square-64" width="64px" height="64px">
                            <input type="hidden" id="groupPicUrl" name="groupPicUrl">
                        </div>
                        <div id="selectPic"
                             style="display: table-cell; vertical-align:middle;text-align: left !important;">
                            <label>封面(<span style="color: grey">大图片建议尺寸：255像素*255像素</span>)</label><br>
                            <button type="button" class="btn btn-custom-primary" id="btnSelectPicUrl"><i
                                    class="fa fa-check-circle"></i> 选择图片
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-xs-1"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-2 control-label text-right">分组类型</label>

                <div class="col-xs-9">
                    <label>
                        <input name="Type" type="radio" value="1">
                        <span><i></i>手工分组</span>
                    </label>
                    <label>
                        <input name="Type" type="radio" value="2">
                        <span><i></i>按商品类目分组</span>
                    </label>
                    <label>
                        <input name="Type" type="radio" value="3">
                        <span><i></i>按发布时间分组</span>
                    </label>
                    <label>
                        <input name="Type" type="radio" value="4">
                        <span><i></i>按商品属性分组</span>
                    </label>
                </div>
                <div class="col-xs-1"></div>
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
</body>
</html>