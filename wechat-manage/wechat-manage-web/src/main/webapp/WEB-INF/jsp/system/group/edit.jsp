<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf"%>
    <script type="text/javascript" src="${ctx}/js/system/group/edit.js">

    </script>
    <style type="text/css">
        .col-sm-3 {
            width: 15%;
            float: left;
            text-align: right;
        }

        .col-sm-9 {
            width: 85%;
            float: left;
            text-align: left;
        }

        label[class^="btn btn-default"] {
            margin-top: -4px;
        }
    </style>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post"
      action="${ctx}/user/addEntity.shtml">
    <section class="panel panel-default">
        <div class="panel-body">




            <div class="form-group">
                <div class="col-sm-3">
                    <label class="control-label">集团名称</label>
                </div>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"  value="${list.organizationName}"  id="organizationName">

                </div>
                <input type="hidden"  value="${list.sid}"  id="sid"  >
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">集团编码</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           value="${list.organizationCode}" id="organizationCode" disabled=true>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">是否禁用</label>
                <div class="col-sm-9">
                    <div class="btn-group m-r">
                        <button data-toggle="dropdown"
                                class="btn btn-sm btn-default dropdown-toggle">



                            <c:choose>
                                <c:when test="${list.organizationStatus==0}">    <!--如果 -->
                                    <span class="dropdown-label">否</span> <span class="caret"></span>
                                </c:when>

                                <c:otherwise>  <!--否则 -->
                                    <span class="dropdown-label">是</span> <span class="caret"></span>
                                </c:otherwise>
                            </c:choose>


                        </button>
                        <ul class="dropdown-menu dropdown-select">
                            <li class=""><a href="#"><input name="radioid" type="radio"
                                                            name="userFormMap.locked" value="1" checked="checked">是</a></li>
                            <li class="active"><a href="#"><input  name="radioid" type="radio"
                                                                  name="userFormMap.locked" value="0">否</a></li>
                        </ul>
                    </div>
                </div>
            </div>


        </div>
        <footer class="panel-footer text-right bg-light lter">
            <button type="button"  id="editgroup" class="btn btn-success btn-s-xs">提交</button>
        </footer> </section>
</form>
<script type="text/javascript">
    onloadurl();
</script>
</body>
</html>