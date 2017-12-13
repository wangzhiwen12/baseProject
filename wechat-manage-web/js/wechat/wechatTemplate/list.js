$(function() {
	getWeChatTemplateType();
});

function getWeChatTemplateType() {
	$.ajax({
				type : "post",
				url : rootPath + '/wechatTemplate/getWechatType.shtml',
				dataType : "json",
				success : function(data) {
					if (data.success == 'true') {
						var list = data.list;
						for (var i = 0; i < list.length; i++) { //遍历标题
							var sid = list[i].sid;
							var info = '<div class="row" id="type'+sid+'">';
							info += '<div class="note note-success divcss5">'+ list[i].typeName + '</div>';
							info += '	</div>';
							$("#div_body").append(info);
							var template = list[i].template;
							
							for (var j = 0; j < template.length; j++) { //遍历内容
								
								var templateId = template[j].storeTemplateId;
								var templateNo = (templateId == null)?"":templateId;
								if(templateId != null && templateId.length>=15){
									templateNo = templateId.substring(10,0)+"...";
								}
								var	infos = '<div class="col-md-4 ui-grid-cards-item" id="">';
								infos += '	<div class="portlet box blue-hoki">';
								infos += '		<div class="portlet-title">';
								infos += '			<div class="caption">';
								infos += template[j].templateName;
								infos += '			</div>';
								infos += '			<div class="actions">';
								infos += '				<div class="">';
								infos += '					<input class="switch-state" type="checkbox" id="'
									+ template[j].infoSid +'">';
								infos += '				</div>';
								infos += '			</div>';
								infos += '		</div>';
								infos += '		<div class="portlet-body">';
								infos += '			<div class="ui-panel-body clear-padding">';
								infos += '				<div';
								infos += '					style="height: 200px; border-bottom: 2px dashed #e5e6e6; position: relative;"';
								infos += '					class="ezp-card-bodyfixed">';
								infos += '					<div class="ezp-card-body clearfix">';
								infos += '						<div class="col-md-12 no-padding">';
								infos += '							' + template[j].templateContent + '';
								infos += '						</div>';
								infos += '					</div>';
								infos += '			</div>';
								infos += '		<div class="ezp-card-body clearfix"';
								infos += '		style="height: 100px; position: relative;">';
								infos += '	<div class="row">';
								infos += '	<div';
								infos += '		class="ezp-c-gray ezp-leftfixedname col-md-3 no-padding divcss4">模板编号';
								infos += '	</div>';
								infos += '	<div>' + template[j].wxTemplateNo + '</div>';
								infos += '		</div>';
								infos += '		<div class="row">';
								infos += '				<div';
								infos += '				class="ezp-c-gray ezp-leftfixedname col-md-3 no-padding divcss4">模板名称';
								infos += '				</div>';
								infos += '					<div>' + template[j].wxTemplateName
								+ '</div>';
								infos += '				</div>';
								infos += '				<div class="row">';
								infos += '					<div';
								infos += '		class="ezp-c-gray ezp-leftfixedname col-md-3 no-padding divcss4">模板ID</div>';
								infos += '							<div>';
								infos += '			<div onclick="setFormData(\''+template[j].storeTemplateId+'\',\''+template[j].infoSid+'\')"><span id="sid'+template[j].infoSid+'">' + templateNo
								+ '</span> &nbsp;';
								infos += '							<a class="config"  href="#portlet-config11" data-toggle="modal" data-original-title="">修改 </a></div>';
								infos += '<input type="hidden" id="wx'+template[j].infoSid+'" name="hiddenText" value="'+template[j].storeTemplateId+'"/>';
								infos += '					</div>';
								infos += '		</div>';
								infos += '						</div>';
								infos += '				</div>';
								infos += '					</div>';
								infos += '				</div>';
								infos += '		</div>';
								
								$("#type"+sid).append(infos);
								 
								var status = false;
								if(template[j].openStatus == 1){ //openStatus 1：开   0关
									status = true;
								}
								$('#' + template[j].infoSid).bootstrapSwitch({
									onText : "启动",
									offText : "关闭",
									size : "mini",
									state : status,
									onSwitchChange : function(event, state) {
										if (state == true) {
											$(this).val("1");
											var infoSid = this.id;
											var status = $(this).val();
											$.ajax({
												type : "post",
												url : rootPath + '/wechatTemplate/updateTemplateState.shtml',
												dataType : "json",
												data : {"infoSid" :infoSid,"status":status},
												success : function(data) {
													
												},
												error:function(){
													
												}
											});
										} else {
											$(this).val("0");
											var infoSid = this.id;
											var status = $(this).val();
											$.ajax({
												type : "post",
												url : rootPath + '/wechatTemplate/updateTemplateState.shtml',
												dataType : "json",
												data : {"infoSid" :infoSid,"status":status},
												success : function(data) {
													
												},
												error:function(){
													
												}
											});
										}
									}
								});
								if(templateId == null || templateId.length<=0 || templateId == ""){
									$('#' + template[j].infoSid).bootstrapSwitch("readonly",true);
								}
								
								
//								if(template[j].openStatus == 1){ //openStatus 1：开   0关
//									$('#' + template[j].infoSid).bootstrapSwitch({
//										onText : "启动",
//										offText : "关闭",
//										size : "mini",
//										state : true,
//										onSwitchChange : function(event, state) {
//											if (state == true) {
//												$(this).val("1");
//												var infoSid = this.id;
//												var status = $(this).val();
//												$.ajax({
//													type : "post",
//													url : rootPath + '/wechatTemplate/updateTemplateState.shtml',
//													dataType : "json",
//													data : {"infoSid" :infoSid,"status":status},
//													success : function(data) {
//														
//													},
//													error:function(){
//														
//													}
//												});
//											} else {
//												$(this).val("0");
//												var infoSid = this.id;
//												var status = $(this).val();
//												$.ajax({
//													type : "post",
//													url : rootPath + '/wechatTemplate/updateTemplateState.shtml',
//													dataType : "json",
//													data : {"infoSid" :infoSid,"status":status},
//													success : function(data) {
//														
//													},
//													error:function(){
//														
//													}
//												});
//											}
//										}
//									});
//								}else{
//										$('#' + template[j].infoSid).bootstrapSwitch({
//											onText : "启动",
//											offText : "关闭",
//											size : "mini",
//											state : false,
//											onSwitchChange : function(event, state) {
//												if (state == true) {
//													$(this).val("1");
//													var infoSid = this.id;
//													var status = $(this).val();
//													$.ajax({
//														type : "post",
//														url : rootPath + '/wechatTemplate/updateTemplateState.shtml',
//														dataType : "json",
//														data : {"infoSid" :infoSid,"status":status},
//														success : function(data) {
//															
//														},
//														error:function(){
//															
//														}
//													});
//												} else {
//													$(this).val("0");
//													var infoSid = this.id;
//													var status = $(this).val();
//													$.ajax({
//														type : "post",
//														url : rootPath + '/wechatTemplate/updateTemplateState.shtml',
//														dataType : "json",
//														data : {"infoSid" :infoSid,"status":status},
//														success : function(data) {
//															
//														},
//														error:function(){
//															
//														}
//													});
//												}
//											}
//										});
//										if(templateId == null || templateId.length<=0 || templateId == ""){
//											$('#' + template[j].infoSid).bootstrapSwitch("readonly",true);
//										}
//								}
							}
						}
					}
				},
				error : function() {
					alert("失败");
				}
			});
}

var url = document.location;
__ctxPath =url.origin;
$("#save").click(function(){
	var storeTemplateId = $("#storeTemplateId").val();//获取保存的模板id
	$("#storeTemplateId_form").val($("#storeTemplateId").val());//将获取模板的id存入form表单中
	var wxTemplateNo_form = $("#wxTemplateNo_form").val();//
	var sidInfo = $("#infoSid_form").val();
	var param=$("#data_form").serialize();
	$.ajax({
		type : "post",
		url : rootPath + '/wechatTemplate/updateTemplateId.shtml',
		dataType : "json",
		data :param,
		success : function(data) {
			if(data.success == true){
				alert("修改成功");
				//$("#loadhtml").load(__ctxPath+"/notebook/wechatTemplate/list.shtml");
				var templateNo = (storeTemplateId == null)?"":storeTemplateId;
				if(storeTemplateId != null && storeTemplateId.length>=15){
					templateNo = storeTemplateId.substring(10,0)+"...";
					$("#sid"+wxTemplateNo_form).html(templateNo);
				}else{
					$("#sid"+wxTemplateNo_form).html(templateNo);
				}
				$("#wx"+wxTemplateNo_form).val((storeTemplateId == null)?"":storeTemplateId);
				$('#' + sidInfo).bootstrapSwitch("readonly",false);
				if(storeTemplateId.length == 0 || storeTemplateId == null ){
					$('#' + sidInfo).bootstrapSwitch("state", false);
					$('#' + sidInfo).bootstrapSwitch("readonly",true);
				}
			}else{
				alert("修改失败,请重新操作");
			}
		},
		error:function(){
			
		}
	});
});
function setFormData(storeTemplateId,infoSid){
	$("#wxTemplateNo_form").val(infoSid); 
	$("#infoSid_form").val(infoSid);
	//var values = isNull(storeTemplateId);
	var wxtext = $("#wx"+infoSid).val();
	var values = isNull(wxtext);
	$("#storeTemplateId").val(values);
}
function isNull(data){ 
	return (data == "" || data == undefined || data == null || data == "null") ? "" : data; 
	}
