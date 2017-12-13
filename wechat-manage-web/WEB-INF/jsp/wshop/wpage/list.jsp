<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wshop/wpage/list.js"></script>

<style>

</style>
<script type="text/javascript">

</script>
<div class="table-responsive">
    <div class="tabbable-line">
        <ul class="nav nav-tabs ">
            <li class="active">
                <a href="#tab_15_1" id="btnCouponList" data-toggle="tab">
                    微页面 </a>
            </li>
            
            <li>
                <a href="#tab_15_3" id="btnApprovalList" data-toggle="tab">
                    微页面草稿 </a>
            </li>
        </ul>
        <div class="tab-content">
        <div style="padding-left:15px;">
           <input id="add_wpage" class="btn btn-info marR10"  value="新建微页面"  type="button">
        </div>
         &nbsp;
            <div class="tab-pane active" id="tab_15_1">
                
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>微页面
                                </div>
                                
                            </div>
                            <div class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="userList">
                                    <thead>
                                    <tr role="row">
                                        <th>标题</th>
                                        <th>创建时间</th>
                                        <th>商品数</th>
                                        <th>浏览UV/PV</th>
                                        <th>到店UV/PV</th>
                                        <th>序号</th>
                                        <th>操作</th>
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
            
            
            <div class="tab-pane" id="tab_15_3">
                <div class="table-responsive">
                    <div class="col-md-12">
                        <div class="portlet box blue-hoki">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-weixin"></i>微页面草稿
                                </div>
                                
                            </div>
                            <div class="portlet-body">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                       id="approvalList">
                                    <thead>
                                    <tr role="row">
                                        <th>标题</th>
                                        <th>创建时间</th>
                                        <th>商品数</th>
                                        <th>浏览UV/PV</th>
                                        <th>到店UV/PV</th>
                                        <th>序号</th>
                                        <th>操作</th> 	
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="callback_div" class="table-responsive" style="display: none;">
                    <div id="paging_callback" class="pagclass"></div>
                </div>
            </div>
        </div>
    </div>
</div>
