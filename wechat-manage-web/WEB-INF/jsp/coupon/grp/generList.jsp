<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/grp/generlist.js"></script>
<div class="table-responsive">
    <div class="m-b-md" style="padding-left: 15px;">
        <form class="form-inline" role="form" id="searchForm"
              name="searchForm">
            <div class="form-group">
                <input class="form-control" id="storeCode" placeholder="输入券门店编码" value="21011">
                <input class="form-control" id="openId" placeholder="输入券OpenID" value="10000001">
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
                        <i class="fa fa-weixin"></i>优惠券
                    </div>
                    <div class="actions" id="btnAdd1">
                        <button type="button" id="btnGener" class="btn btn-primary marR10">领券</button>
                        <button type="button" id="btnCouponDetail" class="btn btn-primary marR10">详情</button>
                        <%--<button type="button" id="btnCouponUse" class="btn btn-primary marR10">核销</button>--%>
                    </div>
                </div>
                <div class="portlet-body">
                    <table class="table table-striped table-bordered table-hover dataTable no-footer"
                           id="userList">
                        <thead>
                        <tr role="row">
                            <th></th>
                            <th>券编号</th>
                            <th>类型</th>
                            <th>券名称</th>
                            <th>副标题</th>
                            <th>状态</th>
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
