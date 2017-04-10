<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wshop/catagory/progrouglist.js"></script>
<style type="text/css">
    .fa {
        display: inline-block;
        font: normal normal normal 14px/1 FontAwesome;
        font-size: inherit;
        text-rendering: auto;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        transform: translate(0, 0);
    }

    .ezp-c-green {
        color: #8bc34a;
    }

    .category-title {
        /*padding-left: 20px;*/
        position: relative;
        /*z-index: 1;*/
        color: #000000;
    }

    .fa-square-o:before {
        content: "\f096";
    }

    .fa-check-square:before {
        content: "\f14a";
    }
</style>
<div class="table-responsive">
    <div class="tabbable-line">
        <ul class="nav nav-tabs ">
            <li><a href="#tab_15_1" data-toggle="tab" onclick="toProPage('1')"> 商品列表 </a></li>
            <li class="active"><a href="#tab_15_2" data-toggle="tab"> 商品分组</a></li>
            <li><a href="#tab_15_4" data-toggle="tab" onclick="toProPage('3')"> 商品分类导入 </a></li>
            <li><a href="#tab_15_5" data-toggle="tab" onclick="toProPage('4')"> 商品导入</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab_15_4">
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">

                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>商品分组
                                </div>

                                <div class="actions" id="btnAdd">
                                    <div class="btn-group">
                                        <a id="btngroup" class="btn btn-danger marR10" href="javascript:;">
                                            手工分组
                                        </a>
                                        <ul id="popTip" class="dropdown-menu" role="menu">
                                            <li>
                                                <a href="javascript:;">
                                                    <i class="icon-user"></i> New User </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <i class="icon-present"></i> New Event <span
                                                        class="badge badge-success">4</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <i class="icon-basket"></i> New order </a>
                                            </li>
                                            <li class="divider">
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <i class="icon-flag"></i> Pending Orders <span
                                                        class="badge badge-danger">4</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <i class="icon-users"></i> Pending Users <span
                                                        class="badge badge-warning">12</span>
                                                </a>
                                            </li>
                                            <li>
                                                <%--<i class="fa fa-check-square ezp-c-green"></i>--%>
                                                <i class="fa fa-square-o ezp-c-green"></i>
                                                <span class="category-title">333</span>
                                            </li>
                                        </ul>
                                    </div>
                                    <button type="button" id="addFun" class="btn btn-primary marR10">新增</button>
                                    <button type="button" id="editFun" class="btn btn-info marR10">编辑</button>
                                    <button type="button" id="delFun" class="btn btn-danger marR10">删除</button>


                                </div>
                            </div>
                            <div class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="userList">
                                    <thead>
                                    <tr role="row">
                                        <th></th>
                                        <th>分组名称</th>
                                        <th>分组图片</th>
                                        <th>状态</th>
                                        <th>创建时间</th>
                                        <th>分组类型</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
