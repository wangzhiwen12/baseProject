<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/js/date/bootstrap.min.css">--%>
<style type="text/css">
    .dropdown-colorselector > .dropdown-menu {
        left: -7px;
        max-width: 130px;
        min-width: 130px;
        padding: 4px;
        top: 80%;
    }

    .dropdown-colorselector > .dropdown-menu > li {
        display: block;
        float: left;
        height: 20px;
        margin: 2px;
        width: 20px;
    }

    .dropdown-colorselector > .dropdown-menu > li > .color-btn {
        border-radius: 0;
        display: block;
        height: 20px;
        margin: 0;
        padding: 0;
        position: relative;
        transition: all 0.1s ease 0s;
        width: 20px;
    }

    .dropdown-colorselector > .dropdown-menu > li > .color-btn:hover {
        opacity: 0.8;
        text-decoration: none;
        transform: scale(1.08);
    }

    .dropdown-colorselector > .dropdown-menu > li > .color-btn.selected::after {
        color: #fff;
        content: "";
        display: inline-block;
        font-family: "Glyphicons Halflings";
        font-size: 11px;
        left: 0;
        line-height: 20px;
        position: absolute;
        right: 0;
        text-align: center;
    }

    .btn-colorselector {
        background-color: #ddd;
        border-radius: 0;
        display: inline-block;
        height: 20px;
        vertical-align: middle;
        width: 20px;
    }

    .dropdown-menu.dropdown-caret::before {
        border-bottom: 7px solid rgba(0, 0, 0, 0.2);
        border-left: 7px solid transparent;
        border-right: 7px solid transparent;
        content: "";
        display: inline-block;
        left: 9px;
        position: absolute;
        top: -7px;
    }

    .dropdown-menu.dropdown-caret::after {
        border-bottom: 6px solid #ffffff;
        border-left: 6px solid transparent;
        border-right: 6px solid transparent;
        content: "";
        display: inline-block;
        left: 10px;
        position: absolute;
        top: -6px;
    }

    input[type='radio'] {
        display: none;
    }

    .ng-submitted .ng-invalid, .ng-dirty.ng-invalid:not(form) {
        border: 1px solid red;
    }
</style>
<div class="m-b-md" style="padding-left: 15px;" ng-app="myApp" ng-controller="MainController">
    <div class="portlet light bg-inverse">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-equalizer font-red-sunglo"></i>
                <span class="caption-subject font-red-sunglo bold uppercase">会员卡号生成规则</span>
                <span class="caption-helper"></span>
            </div>
            <div class="actions">
            </div>
        </div>
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form name="ruleForm" action="#" method="post" class="form-horizontal"
                  ng-submit="submitForm()">
                <input type="hidden" ng-model="coupon.sid" name="sid">

                <div class="form-body">
                    <div class="form-group">
                        <label class="control-label col-md-3">会员卡号流水位数</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入会员卡号流水位数" ng-model="coupon.nolength" name="nolength"
                                   required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">会员卡号起始位
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入会员卡号起始位" name="prefixstr" ng-model="coupon.prefixstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">会员卡号前缀字符
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入会员卡号前缀字符" name="startno" ng-model="coupon.startno">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">会员卡号后缀长度
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入会员卡号后缀长度" name="suffixlength" ng-model="coupon.suffixlength">
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" ng-click="submitForm()" class="btn btn-success btn-s-xs"> 保存
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/rule/add.js"></script>