UE.registerUI('simpleupload', function (editor, uiName) {
    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
    //editor.registerCommand(uiName, {
    //    execCommand: function () {
    //        alert('execCommand:' + uiName)
    //    }
    //});
    //创建一个button
    var btn = new UE.ui.Button({
        //按钮的名字
        name: uiName,
        //提示
        title: '图片',
        //添加额外样式，指定icon图标，这里默认使用一个重复的icon
        cssRules: '',
        //点击时执行的命令
        onclick: function () {
        	layer.open({
					title : "图片素材",
					type : 2,
					area : [ "90%", "85%" ],
					content : contextPath+'/materialLocal/imageList.shtml'
			});
        }
    });
    
    //当点到编辑内容上时，按钮要做的状态反射
    editor.addListener('selectionchange', function () {
        //var state = editor.queryCommandState(uiName);
        //if (state == -1) {
        //    btn.setDisabled(true);
        //    btn.setChecked(false);
        //} else {
        //    btn.setDisabled(false);
        //    btn.setChecked(state);
        //}
    });
    //因为你是添加button,所以需要返回这个button
    return btn;
},[22]);