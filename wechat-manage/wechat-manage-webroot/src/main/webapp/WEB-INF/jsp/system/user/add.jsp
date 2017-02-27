<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf"%>
    <script type="text/javascript" src="${ctx}/js/system/user/add.js">

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
    <script type="text/javascript">
        $(document).ready(function() {
            $("#groupsForSelect").dblclick(function() {
                selected();
            });
            $("#selectGroups").dblclick(function() {
                unselected();
            });
        });
        function selected() {
            var selOpt = $("#groupsForSelect option:selected");

            selOpt.remove();
            var selObj = $("#selectGroups");
            selObj.append(selOpt);

            var selOpt = $("#selectGroups")[0];
            ids = "";
            for (var i = 0; i < selOpt.length; i++) {
                ids += (selOpt[i].value  + ",");
            }

            if (ids != "") {
                ids = ids.substring(0, ids.length - 1);
            }
            $('#txtGroupsSelect').val(ids);
        }

        function selectedAll() {
            var selOpt = $("#groupsForSelect option");

            selOpt.remove();
            var selObj = $("#selectGroups");
            selObj.append(selOpt);

            var selOpt = $("#selectGroups")[0];
            ids = "";
            for (var i = 0; i < selOpt.length; i++) {
                ids += (selOpt[i].value  + ",");
            }

            if (ids != "") {
                ids = ids.substring(0, ids.length - 1);
            }
            $('#txtGroupsSelect').val(ids);
        }

        function unselected() {
            var selOpt = $("#selectGroups option:selected");
            selOpt.remove();
            var selObj = $("#groupsForSelect");
            selObj.append(selOpt);

            var selOpt = $("#selectGroups")[0];
            ids = "";
            for (var i = 0; i < selOpt.length; i++) {
                ids += (selOpt[i].value + ",");
            }

            if (ids != "") {
                ids = ids.substring(0, ids.length - 1);
            }
            $('#txtGroupsSelect').val(ids);
        }

        function unselectedAll() {
            var selOpt = $("#selectGroups option");
            selOpt.remove();
            var selObj = $("#groupsForSelect");
            selObj.append(selOpt);

            $('#txtGroupsSelect').val("");
        }
    </script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post"
      action="${ctx}/user/addEntity.shtml">
    <section class="panel panel-default">
        <div class="panel-body">

            <div class="form-group">
                <div class="col-sm-3">
                    <label class="control-label">集团</label>
                </div>
                <div class="col-sm-9">
                    <%-- name="offsetType"--%>
                    <select id="groupType" name="userFormMap.organization_code"   class="table-group-action-input form-control input-inline input-small input-sm checkaccgg">
                    </select>
                </div>

            </div>
            <div class="form-group">
                <div class="col-sm-3">
                    <label class="control-label">门店</label>
                </div>
                <div class="col-sm-9">
                    <%--name="offsetType"--%>
                    <select id="storeType" name="userFormMap.store_code" class="table-group-action-input form-control input-inline input-small input-sm checkatt">
                    </select>
                </div>
            </div>
            <input type="hidden" class="form-control"  name="userFormMap.organization_name" id="organization_name">
            <input type="hidden" class="form-control"  name="userFormMap.business_name" id="business_name">
            <div class="form-group">
                <div class="col-sm-3">
                    <label class="control-label">用户名</label>
                </div>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           placeholder="请输入用户名" name="userFormMap.userName" id="userName">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">账号</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入账号" name="userFormMap.accountName" id="accountName">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">密码</label>
                <div class="col-sm-9" style="color: red;">
                    默认密码为:123456789
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <%-- 加载角色--%>
            <%--<div class="form-group" id="selRole" data-url="/role/selRole.shtml"></div>--%>

            <%--start==================--%>
            <div class="form-group" id="selRole">
                <div class="form-group">
                    <input id="txtGroupsSelect" type="hidden" value="${txtRoleSelect}"
                           name="txtGroupsSelect" />
                    <label for="host" class="col-sm-3 control-label">角色</label>
                    <div class="col-sm-9">
                        <table class="tweenBoxTable" name="groups_tweenbox"
                               id="groups_tweenbox" cellspacing="0" cellpadding="0">
                            <tbody>
                            <tr>
                                <td>已角色</td>
                                <td></td>
                                <td>可角色</td>
                            </tr>
                            <tr>
                                <td><select id="selectGroups" multiple="multiple"
                                            class="input-large" name="selectGroups"
                                            style="height: 150px; width: 150px">
                                    <c:forEach items="${userRole}" var="key">
                                        <option value="${key.id}">${key.name}</option>
                                    </c:forEach>
                                </select></td>
                                <td align="center">
                                    <div style="margin-left: 5px; margin-right: 5px">
                                        <button onclick="selectedAll()" class="btn btn-primary"
                                                type="button" style="width: 50px;" title="全选">&lt;&lt;</button>
                                    </div>
                                    <div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
                                        <button onclick="selected()" class="btn btn-primary"
                                                type="button" style="width: 50px;" title="选择">&lt;</button>
                                    </div>
                                    <div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
                                        <button onclick="unselected()" class="btn btn-primary"
                                                type="button" style="width: 50px;" title="取消">&gt;</button>
                                    </div>
                                    <div style="margin-left: 5px; margin-right: 5px; margin-top: 5px">
                                        <button onclick="unselectedAll()" class="btn btn-primary"
                                                type="button" style="width: 50px;" title="全取消">&gt;&gt;</button>
                                    </div>
                                </td>
                                <td><select id="groupsForSelect"
                                            multiple="multiple" class="input-large"
                                            style="height: 150px; width: 150px">
                                    <c:forEach items="${role}" var="key">
                                        <option value="${key.id}">${key.name}</option>
                                    </c:forEach>
                                </select></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%--end ======================--%>

            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">是否禁用</label>
                <div class="col-sm-9">
                    <div class="btn-group m-r">
                        <button data-toggle="dropdown"
                                class="btn btn-sm btn-default dropdown-toggle">
                            <span class="dropdown-label">是</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu dropdown-select">
                            <li class=""><a href="#"><input type="radio"
                                                            name="userFormMap.locked" value="0" checked="checked">是</a></li>
                            <li class="active"><a href="#"><input type="radio"
                                                                  name="userFormMap.locked" value="1">否</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">描述</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入账号描述"
                           name="userFormMap.description" id="description">
                </div>
            </div>
        </div>
        <footer class="panel-footer text-right bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer> </section>
</form>
<script type="text/javascript">
    onloadurl();
</script>
</body>
</html>