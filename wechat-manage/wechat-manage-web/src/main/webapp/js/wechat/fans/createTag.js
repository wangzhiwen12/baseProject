$(function() {
	$("#addTag").click("click", function () {
		var tagName = document.getElementById("tagName");
		 $.ajax({
             type: "get",
             url: rootPath + "/fans/create_tag.shtml?tagName="+encodeURI(encodeURI(tagName.value)),
             dataType:  'json',
             success: function (result) {
            	 if (result == "success") {
 					window.parent.tagList();
 					window.parent.userList("", "");
 					parent.layer.close(parent.pageii);
 				} else {
 					return false;
 				}
             }
         });
	});
});

	

