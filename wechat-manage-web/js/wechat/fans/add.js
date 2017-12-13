$(function() {
	$("#submit").click(function () {
		var strs =[]; 
		var group = [];
		var tagCount;
		var oldgroup;
		var count;
		var temp=document.getElementsByName("modules");
		var openid=document.getElementById("openID").value;
        for(var i=0;i<temp.length;i++){
            if(temp[i].checked==true){
            	group.push(temp[i].value);
            }
        }
        if(openid.indexOf(",") > 0){
        	var str =new Array(); 
        	var s = openid + ",";
            str=openid.split(","); //字符分割 
            for(var j=0;j<str.length;j++){ 
            	strs.push(str[j]);
            } 
            oldgroup = "";
            tagCount = document.getElementById("tagCount").value;
            count = parseInt(group.length) + parseInt(tagCount);
        }else{
        	strs.push(openid);
        	oldgroup=document.getElementById("groupList").value;
        	count = group.length;
        }
        if(count > 3){
        	layer.msg("用户最多打3个标签!");
        	return false;
        }
        var odata= JSON.stringify({
	    	"openids" : strs,
	    	"groupids" : group,
	    	"oldgroups" : oldgroup
	    });
		$.ajax({
		    url: rootPath + "/fans/tag.shtml",
		    type: "post",
		    dataType : "json",
		    data: odata,
		    success: function (json, flag) {
		    	if (json == "success") {
					window.parent.tagList();
					window.parent.userList("", "");
					parent.layer.close(parent.pageii);
				} else {
					return false;
				}
		    },
		    error:function(data){
		    	alert(data);
		    }
		});
		
	});
});
