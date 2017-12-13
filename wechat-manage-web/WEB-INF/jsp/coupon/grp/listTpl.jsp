<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common/common.jspf" %>
<link href="${ctx}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"
      rel="stylesheet" type="text/css"/>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/datatables/all.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/coupon/grp/listTpl.js"></script>
<div class="m-b-md" style="padding-left: 15px;">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group col-xs-3">
            <select class="form-control" id="selCouponType" name="selCouponType">
            </select>
        </div>
        <div class="form-group col-xs-5">
            <input class="form-control" id="name" placeholder="输入券模板名称">
        </div>
        <div class="form-group col-xs-3">
            <a href="javascript:void(0)" class="btn btn-success" id="search">查询</a>
        </div>
    </form>
</div>
<div class="table-responsive">
    <div class="col-md-12">
        <div class="portlet box blue-hoki">
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                       id="userList">
                    <thead>
                    <tr role="row">
                        <th></th>
                        <th>券类型</th>
                        <th>品牌券模板</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<footer class="panel-footer text-center bg-light lter">
    <button type="button" id="btuConfirm" class="btn btn-success btn-s-xs">确定</button>
</footer>


