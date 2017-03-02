<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/grp/listrep.js"></script>
<div class="table-responsive">
    <div class="m-b-md" style="padding-left: 15px;">
        <form class="form-inline" role="form" id="searchForm"
              name="searchForm">
            <div class="form-group">
                <select class="form-control" id="selCouponType" name="selCouponType">
                </select>
                <input class="form-control" id="name" placeholder="输入券名称/券模板">
                <input type="hidden" id="operationType" name="operationType" value="0">
            </div>
            <a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
        </form>
    </div>
    <br/>
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
                            <th>名称</th>
                            <th>发送数量</th>
                            <th>核销数量</th>
                            <th>核销率</th>
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
</div>
</div>
