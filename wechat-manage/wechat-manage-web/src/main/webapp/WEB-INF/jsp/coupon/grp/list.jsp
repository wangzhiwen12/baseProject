<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/grp/list.js"></script>
<div class="table-responsive">
    <div class="tabbable-line">
        <ul class="nav nav-tabs ">
            <li class="active">
                <a href="#tab_15_1" id="btnCouponList" data-toggle="tab">
                    电子券库 </a>
            </li>
            <li>
                <a href="#tab_15_2" data-toggle="tab">
                    电子卡包 </a>
            </li>
            <li>
                <a href="#tab_15_3" id="btnApprovalList" data-toggle="tab">
                    审批列表 </a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab_15_1">
                <div class="m-b-md" style="padding-left: 15px;">
                    <form class="form-inline" role="form" id="searchForm"
                          name="searchForm">
                        <div class="form-group">
                            <select class="form-control" id="selCouponType" name="selCouponType">
                            </select>
                            <input class="form-control" id="couponName" placeholder="输入券名称/券模板">
                            <input type="hidden" id="operationType" name="operationType" value="0">
                        </div>
                        <a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
                    </form>
                </div>
                <br />
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>电子券管理
                                </div>
                                <div class="actions" id="btnAdd1">
                                    <c:forEach items="${res}" var="key">
                                        ${key.description}
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="userList">
                                    <thead>
                                    <tr role="row">
                                        <th></th>
                                        <th>券ID</th>
                                        <th>类型</th>
                                        <th>券模板</th>
                                        <th>名称</th>
                                        <th>状态</th>
                                        <th>创建人</th>
                                        <th>最后编辑时间</th>
                                        <th>审批人</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="callback_div1" class="table-responsive" style="display: none;">
                    <div id="paging_callback1" class="pagclass"></div>
                </div>
            </div>
            <div class="tab-pane" id="tab_15_2">
                建设中......
            </div>
            <div class="tab-pane" id="tab_15_3">
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>审批管理
                                </div>
                                <div class="actions" id="btnAdd">
                                    <button type="button" id="btnApproval" class="btn btn-danger marR10">审批通过</button>
                                    <input type="hidden" id="operationType3" name="operationType3" value="1">
                                </div>
                            </div>
                            <div class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="approvalList">
                                    <thead>
                                    <tr role="row">
                                        <th></th>
                                        <th>券ID</th>
                                        <th>类型</th>
                                        <th>券模板</th>
                                        <th>名称</th>
                                        <th>状态</th>
                                        <th>创建人</th>
                                        <th>最后编辑时间</th>
                                        <th>审批人</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="callback_div" class="table-responsive" style="display: none;">
                    <div id="paging_callback" class="pagclass"></div>
                </div>
            </div>
        </div>
    </div>
</div>
