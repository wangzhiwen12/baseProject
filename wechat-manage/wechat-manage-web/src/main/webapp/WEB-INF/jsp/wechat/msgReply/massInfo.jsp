<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/notebook/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"
          rel="stylesheet" type="text/css"/>
<link href="/notebook/js/date/bootstrap.min.css" rel="stylesheet">
<link href="/notebook/js/date/font-awesome.min.css" rel="stylesheet">
</head>
<body>
	<div id="mass_info_div">
		<label>发送内容</label>
	</div>
	<div id = "memberTable">
		<label>发送用户列表</label>
			<table
				class="table table-striped table-bordered table-hover dataTable no-footer"
				id="memberManagerList">
				<thead>
					<tr role="row">
						<th>门店编码</th>
						<th>openId</th>
						<th>用户昵称</th>
						<th>性别</th>
					</tr>
				</thead>
			</table>
		</div>
</body>
<script src="/notebook/assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
<script src="/notebook/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"
		type="text/javascript"></script>
<script src="/notebook/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"
		type="text/javascript"></script>
<script src="/notebook/assets/global/plugins/datatables/all.min.js"
		type="text/javascript"></script>
<script src="/notebook/assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
<script type="text/javascript">
var massSid = parent.$('#mass_sid').val();
$(function(){
	$.ajax({
        "dataType": 'json',
        "type": "POST",
        "url": '${pageContext.request.contextPath}/msgMass/getMsgMassInfo.shtml',
        "data": {
        	'msgSid' : massSid
        },
        "success": function (massInfo) {
			var mass = null;
			if(massInfo.msgType == 'text'){
				mass = '<p>'+massInfo.content+'</p>';
			}else if(massInfo.msgType == 'image'){
				mass = '<img src="' + massInfo.imgUrl + '" />'
			}else if(massInfo.msgType == 'news'){
				mass = '<p>'+massInfo.title+'</p><br><img src="' + massInfo.imgUrl + '" />'
			}
			$('#mass_info_div').append(mass);
        }
	});
	memberManagerList();
});
function memberManagerList() {
    var memberManagerTable = $('#memberManagerList');
    memberManagerTable.dataTable().fnClearTable(false);
    memberManagerTable.dataTable().fnDestroy();
    memberManagerTable.dataTable({
        "bAutoWidth": false,
        "bDestory": true,
        "bFilter": false,
        "bPaginate": true,
        "sAjaxSource": "${pageContext.request.contextPath}/memberInfo/getMsgMember.shtml",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "bServerSide": true,
        "iDisplayLength": 10,
        "bSort": false,
        "oLanguage": {//语言设置
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoFiltered": "(总共 _MAX_ 条数据)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sZeroRecords": "抱歉， 没有找到",
            "sInfoEmpty": "没有数据",
            "sLoadingRecords": "加载中...",
            "sProcessing": "处理中..."
        },
        "aoColumns": [
            {
                "mDataProp": 'storeCode',
                "sTitle": "门店编码",
                "sWidth": '25%'
            }, {
                "mDataProp": 'openid',
                "sTitle": "openId",
                "sWidth": '25%'
            }, {
            	"mDataProp": 'nickname',
                "sTitle": "用户昵称",
                "sWidth": '25%'
            }, {
                "mDataProp": 'sex',
                "sTitle": "性别",
                "sWidth": '25%',
                "mRender": function (data, type, full) {
                    var sex = data;//(0文本,1图片,2语音,3视频,4音频,5图文)
                    var sexView = "";
                    if (sex == 1) {
                    	sexView = "男";
                    } else if (sex == 2) {
                    	sexView = "女";
                    }
                    return sexView;
                }
            }],
            "aoColumnDefs": [{
                sDefaultContent: '',
                aTargets: ['_all']
            }],
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "POST",
                    "url": sSource,
                    "data": {
                    	'massSid' : massSid
                    },
                    "success": function (json, flag) {
                        if (flag && json) {
                            fnCallback(json);
                        } else {
                            userTable.dataTable().fnProcessingIndicator(false);
                            bootbox.alert("查询失败，请稍后再试！");
                        }
                    },
                    "timeout": 15000,
                    "error": function (xhr, textStatus, error) {
                        console.log(xhr, textStatus, error);
                        if (textStatus === 'timeout') {
                            alert('The server took too long to send the data.');
                        } else if (textStatus === 'Not Found') {
                            alert('The server not found.');
                        } else {
                            alert('An error occurred on the server. Please try again in a minute.');
                        }
                        userTable.dataTable().fnProcessingIndicator(false);
                    }
                });
            },
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            //console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
        }
    });
}
</script>
</html>