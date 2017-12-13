<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf"%>
    <script type="text/javascript" src="${ctx}/js/wechat/fans/createTag.js">
    
    /* <script type="text/javascript" src="${ctx}/js/wechat/fans/createTag.js"> */

    </script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" >
    <section class="panel panel-default">
        <div class="panel-body">
           <div>
				<span id="modulelist"> 
				<table class="list" width="100%">
						<tbody>
							<c:forEach items="${groups}" var="item" step="3"
								varStatus="status">
								<tr style="text-align: left;">
									<c:forEach items="${groups}" var="module"
										begin="${status.index}" end="${status.index+2}">

										<c:if test="${not empty module.id}">
												<td><input type="checkbox" name="modules" value="${module.id}"
													/>
												${module.name}</td>
										</c:if>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table> 
					<div>
							<input id="tagName" type="text" name="tagName" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-success btn-s-xs" id="addTag">新建</button>
							<!-- <button class="btn btn-success btn-s-xs" id="submit">提交</button> -->
					</div>
				</span>
			</div>
        </div>
         </section>
</form>
<script type="text/javascript">
</script>
<%-- <script type="text/javascript"
        src="${ctx}/notebook/notebook_files/bootstrap-filestyle.min.js"></script> --%>
</body>
</html>