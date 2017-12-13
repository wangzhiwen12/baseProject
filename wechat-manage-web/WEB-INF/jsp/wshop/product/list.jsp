<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/wfjUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wshop/product/productList.js"></script>
<style type="text/css">
    .clearfix:after {
        visibility: hidden;
        display: block;
        font-size: 0;
        content: " ";
        clear: both;
        height: 0;
    }
    .clearfix {
        *zoom: 1;
    }
    .listInfo {
        list-style: none;
        margin:0;
        padding:0;
    }
    .listInfo li {
        float: left;
        height:30px;
        overflow: hidden;
        margin:10px 15px 10px 0;
    }
    .listInfo li span {
        display:block;
        float:left;
        width:100px;
        text-align:right;
    }
    input {
        -web-kit-appearance:none;
        -moz-appearance: none;
        border-radius:4px;
        border:1px solid #c8cccf;
        color:#6a6f77;
        outline:0;
    }
    select {
        border-radius:4px;
        border:1px solid #c8cccf;
        color:#6a6f77;
        outline:0;
    }
</style>
<div class="table-responsive">
    <div class="tabbable-line">
        <ul class="nav nav-tabs ">
            <li class="active"><a href="#tab_15_1" data-toggle="tab"> 商品列表 </a></li>
            <li><a href="#tab_15_2" data-toggle="tab" onclick="toProPage('2')"> 商品分组</a></li>
            <%--<li><a href="#tab_15_4" data-toggle="tab" onclick="toProPage('3')"> 商品分类导入 </a></li>
            <li><a href="#tab_15_5" data-toggle="tab" onclick="toProPage('4')"> 商品导入</a></li>--%>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab_15_4">
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-globe"></i>商品管理
                                </div>
                                <div class="tools"><a href="javascript:" class="collapse"></a></div>
                            </div>
                            <div id="storeSid">

                            </div>
                            <div class="portlet-body form">
                                <!-- BEGIN FORM-->
                                <form action="#" class="form-horizontal">
                                    <div class="form-body">
                                        <div class="table-toolbar">

                                            <ul class="listInfo clearfix">
                                                <li>
                                                    <span>专柜商品编码：</span>
                                                    <input type="text" id="shoppeProSid_input" style="width: 91px;" maxlength="20" />
                                                    ~
                                                    <input type="text" id="maxShoppeProSid_input" style="width: 92px;" maxlength="20" />
                                                </li>
                                                <li>
                                                    <span>SKU编码：</span>
                                                    <input type="text" id="skuCode_input" style="width: 91px;" maxlength="20" />
                                                    ~
                                                    <input type="text" id="maxSkuCode_input" style="width: 92px;" maxlength="20" />
                                                </li>
                                                <li>
                                                    <span>商品名称：</span>
                                                    <input type="text" id="shoppeProName_input" style="width: 200px;" maxlength="20" />
                                                </li>
                                                <li>
                                                    <span>是否可售：</span>
                                                    <select id="saleStatus_select" style="width: 200px;">
                                                        <option value="">全部</option>
                                                        <option value="Y">可售</option>
                                                        <option value="N">不可售</option>
                                                    </select>
                                                </li>
                                                <li>
                                                    <span>供应商：</span>
                                                    <select id="supplier_select" style="width: 200px;">
                                                        <option value="">全部</option>
                                                    </select>
                                                </li>
                                                <li>
                                                    <span>专柜名称：</span>
                                                    <select id="shoppe_select" style="width: 200px;" disabled>
                                                        <option value="">全部</option>
                                                    </select>
                                                </li>
                                                <li style="float: right">
                                                    <a class="btn btn-default shiny" onclick="productList();">查询</a>
                                                    &nbsp;&nbsp;
                                                    <a class="btn btn-default shiny" onclick="reset();">重置</a>
                                                </li>
                                            </ul>

                                        </div>
                                    </div>
                                </form>
                                <!-- END FORM-->
                            </div>
                        </div>

                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>商品列表
                                </div>

                                <div class="btn-group" style="float:right;">
                                    <a id="btngroup" class="btn btn-danger marR10" href="javascript:;">
                                        手工分组
                                    </a>
                                </div>

                            </div>
                            <div id="productTable" class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="productList">
                                    <thead>
                                        <tr role="row">
                                            <th>选择</th>
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
