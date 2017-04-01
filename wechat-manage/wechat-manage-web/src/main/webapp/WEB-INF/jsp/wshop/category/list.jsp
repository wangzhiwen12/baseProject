<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<div class="table-responsive">
    <div class="tabbable-line">
        <ul class="nav nav-tabs ">
            <li><a href="#tab_15_1" data-toggle="tab" onclick="toProPage('1')"> 商品列表 </a></li>
            <li><a href="#tab_15_2" data-toggle="tab" onclick="toProPage('2')"> 商品分组</a></li>
            <li class="active"><a href="#tab_15_4" data-toggle="tab"> 商品分类导入 </a></li>
            <li><a href="#tab_15_5" data-toggle="tab" onclick="toProPage('4')"> 商品导入</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab_15_4">
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>分类管理
                                </div>
                                <div class="actions" id="btnAdd">
                                    <button type="button" id="exprotCategory" class="btn btn-primary marR10">导入分类</button>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="userList">
                                    <thead>
                                    <tr role="row">
                                        <th></th>
                                        <th>模板ID</th>
                                        <th>券类型</th>
                                        <th>券面值</th>
                                        <th>品牌券模板</th>
                                        <th>使用门槛</th>
                                        <th>创建人</th>
                                        <th>创建时间</th>
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
