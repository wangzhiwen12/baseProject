<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<link type="text/css" href="${ctx}/js/coupon/member/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/js/coupon/member/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<%-- <script type="text/javascript" src="${ctx}/js/coupon/member/js/jquery-1.7.1.min.js"></script> --%>
<script type="text/javascript" src="${ctx}/js/coupon/member/js/browser.js"></script>
<script type="text/javascript" src="${ctx}/js/coupon/member/js/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/coupon/member/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${ctx}/js/coupon/member/js/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx}/js/coupon/member/membet_info_edit.js"></script>
<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/member/editMemberInfo.shtml">
		<input type="hidden" class="form-control checkacc"
			value="${member.sid}" name="sid" id="id">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">姓名</label>
				<div class="col-sm-9"  style="width:30%">
					<input type="text" class="form-control checkacc"
						name="nickname" id=nickname
						value="${member.nickname}"  >
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			  <div class="form-group">
                <label class="col-sm-3 control-label">性别</label>

                <div class="col-sm-9"  style="width:15%">
                    <select id="sex" name="sex" class="form-control m-b"
                            tabindex="-1">
                            
                    </select>
                </div>
            </div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">生日</label>
				<div class="col-sm-9"  style="width:30%">
					<input type="text" class="ui_timepicker"
						 name="brithday" id="brithday"
						value="${member.brithday}"  >
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号</label>
				<div class="col-sm-9"  style="width:30%">
					<input type="text" class="form-control checkacc"
						 name="mobile" id="mobile"
						value="${member.mobile}"  >
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">备注</label>
				<div class="col-sm-3" style="width:80%">
					<input type="text" class="form-control checkacc"
						 name="remarks" id="remarks"
						value="${member.modifyRemarks}">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
		</div>
		<div style="padding-left: 80%;">
				<button type="submit"  class="btn btn-success btn-s-xs">提交</button>
		</div>
	</form>
	<script type='text/javascript'>
	 $(function(){
		 
		 $(".ui_timepicker").datetimepicker({
	            showSecond: true,
	            timeFormat: 'hh:mm:ss',
	            stepHour: 1,
	            stepMinute: 1,
	            stepSecond: 1
        })
		 
		 var sex="${member.sex}";
		 var h;
		    if (sex != null) {
		        if (parseInt(sex, 10) == 1) {
		                h += "<option value='" + 1+ "' selected='selected'>"
		                    + " 男  </option>";
		                h += "<option value='" + 2+ "'>" + "女</option>";
		            } else {
		            	h += "<option value='" + 1+ "' '>"
		                    + " 男  </option>";
		                h += "<option value='" + 2+ "'>selected='selected" + "女</option>";
		            }
		        $("#sex").html(h);
		    } else {
		        bootbox.alert("获取性别信息错误，请联系管理员！");
		    }
		}); 
	</script>
</body>
</html>