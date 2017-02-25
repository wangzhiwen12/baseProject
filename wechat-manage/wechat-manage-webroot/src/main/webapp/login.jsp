<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" %>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 3.3.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/global/plugins/uniform/css/uniform.default.css"
          rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${pageContext.servletContext.contextPath }/assets/admin/pages/css/login.css" rel="stylesheet"
          type="text/css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="${pageContext.servletContext.contextPath }/assets/global/css/components.css" id="style_components"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/global/css/plugins.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/admin/layout/css/layout.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet"
          type="text/css" id="style_color"/>
    <link href="${pageContext.servletContext.contextPath }/assets/admin/layout/css/custom.css" rel="stylesheet"
          type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
    <a href="index.html">
        <img src="${pageContext.servletContext.contextPath }/assets/admin/layout/img/logo-big.png" alt=""/>
    </a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="${pageContext.servletContext.contextPath }/login.shtml" method="post">
        <h3 class="form-title">登录</h3>

        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
			<span>
			请输入账号和密码. </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">账号</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off"
                   placeholder="Username" name="username"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
                   placeholder="Password" name="password"/>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-success uppercase">登录</button>
            <label class="rememberme check">
                <input type="checkbox" name="remember" value="1"/>十天内免登陆 </label>
            <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="index.html" method="post">
        <h3>Forget Password ?</h3>

        <p>
            Enter your e-mail address below to reset your password.
        </p>

        <div class="form-group">
            <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email"
                   name="email"/>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn btn-default">Back</button>
            <button type="submit" class="btn btn-success uppercase pull-right">Submit</button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
</div>
<div class="copyright">
    2014 © Metronic. Admin Dashboard Template.
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery-migrate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery.blockui.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery.cokie.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/uniform/jquery.uniform.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
        type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.servletContext.contextPath }/assets/global/scripts/metronic.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/admin/layout/scripts/layout.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/admin/layout/scripts/demo.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/admin/pages/scripts/login.js"
        type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Login.init();
        Demo.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>