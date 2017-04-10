<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <link href="${ctx}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"
          rel="stylesheet" type="text/css"/>
    <script src="${ctx}/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script src="${ctx}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"
            type="text/javascript"></script>
    <script src="${ctx}/assets/global/plugins/datatables/all.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/wshop/catagory/progrougpoplist.js"></script>
</head>
<body>
<div class="table-responsive">
    <div class="col-md-12">
        <div class="portlet box blue-hoki">
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                       id="userList">
                    <thead>
                    <tr role="row">
                        <th></th>
                        <th>分组名称</th>
                        <th>分组类型</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <footer class="panel-footer text-center bg-light lter">
                <button type="button" onclick="javascript:parent.layer.close(parent.pageii);"
                        class="btn btn-default btn-s-xs">取消
                </button>
                <button id="btnSubmit" type="submit" class="btn btn-success btn-s-xs">提交</button>
            </footer>
        </div>

    </div>
</div>
</body>
</html>