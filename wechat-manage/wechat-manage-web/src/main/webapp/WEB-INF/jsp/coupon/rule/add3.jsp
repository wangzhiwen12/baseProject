<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/date/bootstrap.min.css">
<style type="text/css">
    .red {
        background: #a30;
    }
</style>
<div class="m-b-md" style="padding-left: 15px;" ng-app="myApp" ng-controller="MainController">
    <div class="portlet light bg-inverse">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-equalizer font-red-sunglo"></i>
                <span class="caption-subject font-red-sunglo bold uppercase">电子券号生成规则</span>
                <span class="caption-helper"></span>
            </div>
            <div class="actions">
            </div>
        </div>
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form name="ruleForm" action="#" method="post" class="form-horizontal"
                  ng-submit="submitForm()">
                <%-- <input type="hidden" value="${coupon.sid}" name="sid"
                        id="sid">--%>
                <div class="form-body">
                    <div class="form-group">
                        <label class="control-label col-md-3">券流水位数</label>

                        <div class="col-sm-4"
                             ng-class="{ 'has-error' : ruleForm.NoLength.$invalid && ruleForm.NoLength.$touched }">
                            <input type="number" class="form-control"
                                   placeholder="请输入券流水位数" ng-model="NoLength" name="NoLength"
                                   required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券号起始位
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券号起始位" name="PrefixStr" id="PrefixStr" value="${coupon.prefixstr}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券号前缀字符
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券号前缀字符" name="StartNo" id="StartNo" value="${coupon.startno}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券号后缀长度
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券号后缀长度" name="SuffixLength" id="SuffixLength"
                                   value="${coupon.suffixlength}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">代金券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" name="colorselector_djq" id="colorselector_djq">
                                </select>
                                <a data-toggle="dropdown" href="#">
                                    <span class="btn-colorselector" style="background-color: rgb(99, 179, 89);"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-caret" role="menu" aria-labelledby="dLabel">
                                    <li><a class="color-btn selected" style="background-color: rgb(99, 179, 89);"
                                           title="#63b359"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(44, 159, 103);" href="#"
                                           title="#2c9f67"></a></li>
                                    <li><a class="color-btn " style="background-color: rgb(80, 159, 201);" href="#"
                                           title="#509fc9"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(88, 133, 207);" href="#"
                                           d title="#5885cf"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(144, 98, 192);" href="#"
                                           title="#9062c0"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(208, 154, 69);" href="#"
                                           title="#d09a45"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(228, 177, 56);" href="#"
                                           title="#e4b138"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(238, 144, 60);" href="#"
                                           title="#ee903c"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(240, 133, 0);" href="#"
                                           title="#f08500"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(169, 217, 45);" href="#"
                                           title="#a9d92d"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(221, 101, 73);" href="#"
                                           title="#dd6549"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(204, 70, 61);" href="#"
                                           title="#cc463d"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(207, 62, 54);" href="#"
                                           title="#cf3e36"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(94, 102, 113);" href="#"
                                           title="#5E6671"></a></li>
                                </ul>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">折扣券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_zkq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">礼品券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_lpq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">邀请券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_yqq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">促销券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_cxq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">异业券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_yyq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">Submit</button>
                                <button type="button" class="btn default">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/rule/add.js"></script>

<script type="text/javascript">
    /*  loadCouponBG("${coupon.djbackground}");*/
</script>