<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/coupon/grp/add.js"></script>
</head>
<body>
<div class="tabbable-line">
    <ul class="nav nav-tabs ">
        <li class="active">
            <a href="#tab_15_1" data-toggle="tab">
                代金券/折扣券/礼品券/邀请券 </a>
        </li>
        <li>
            <a href="#tab_15_2" data-toggle="tab">
                促销券 </a>
        </li>
        <li>
            <a href="#tab_15_3" data-toggle="tab">
                异业券 </a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab_15_1">
            <form id="form" name="form" class="form-horizontal" method="post" action="#">
                <section class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group form-md-line-input">
                            <label class="col-xs-3 control-label text-right"><span style="color:red">*</span>
                                券模板</label>

                            <div class="col-xs-6">
                                <input type="text" class="form-control" name="txtTplName" id="txtTplName">
                            </div>
                            <div class="col-xs-3"></div>
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
                            <label class="col-xs-3 control-label text-right">使用说明</label>

                            <div class="col-xs-6">
                                <textarea class="form-control" rows="3" cols="10" name="txtUseDesc"
                                          id="txtUseDesc"
                                          placeholder="用于向买家展示，如该优惠券不得与其他活动同时使用；节假日不得使用等，回车即可划分段落"></textarea>

                            </div>
                            <div class="col-xs-3"></div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label text-right"><span
                                    style="color:red">* </span>审核人</label>

                            <div class="col-xs-6">
                                <input type="text" class="form-control"
                                       name="txtApprovalUserName" id="txtApprovalUserName">
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
        <div class="tab-pane" id="tab_15_2">
            <form id="form2" name="form2" class="form-horizontal" method="post" action="#">
                <section class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group form-md-line-input">
                            <label class="col-xs-3 control-label text-right"><span style="color:red">*</span>
                                券模板</label>

                            <div class="col-xs-6">
                                <input type="text" class="form-control" name="txtTplName" id="txtTplName">
                            </div>
                            <div class="col-xs-3"></div>
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
                            <label class="col-xs-3 control-label text-right">使用说明</label>

                            <div class="col-xs-6">
                                <textarea class="form-control" rows="3" cols="10" name="txtUseDesc"
                                          id="txtUseDesc"
                                          placeholder="用于向买家展示，如该优惠券不得与其他活动同时使用；节假日不得使用等，回车即可划分段落"></textarea>

                            </div>
                            <div class="col-xs-3"></div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label text-right"><span
                                    style="color:red">* </span>审核人</label>

                            <div class="col-xs-6">
                                <input type="text" class="form-control"
                                       name="txtApprovalUserName" id="txtApprovalUserName">
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
        <div class="tab-pane" id="tab_15_3">33
        </div>
    </div>
</div>
</form>
</body>
</html>