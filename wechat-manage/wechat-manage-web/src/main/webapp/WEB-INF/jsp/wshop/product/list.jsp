<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wshop/product/productList.js"></script>
<div class="table-responsive">
    <div class="tabbable-line">
        <ul class="nav nav-tabs ">
            <li class="active"><a href="#tab_15_1" data-toggle="tab"> 商品列表 </a></li>
            <li><a href="#tab_15_2" data-toggle="tab" onclick="toProPage('2')"> 商品分组</a></li>
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
                                    <i class="fa fa-weixin"></i>商品管理
                                </div>
                                <div class="actions" id="btnAdd">
                                    <button type="button" id="exprotCategory" class="btn btn-primary marR10">导入分类</button>
                                </div>
                            </div>
                            <div id="productTable" class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="productList">
                                    <thead>
                                        <tr role="row">
                                            <th>专柜商品编码</th>
                                            <th>商品名称</th>
                                            <th>SKU编码</th>
                                            <th>门店</th>
                                            <th>专柜</th>
                                            <th>供应商</th>
                                            <th>门店品牌</th>
                                            <th>管理分类</th>
                                            <th>状态</th>
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
