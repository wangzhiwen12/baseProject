/**
 * Created by kongqf on 2017/3/30.
 */
$(function () {
    $("#exprotCategory").click("click", function () {// 下移
        exporCategroy();
    });
});


function exporCategroy() {
    layer.confirm('确定要导入分类吗？', function (index) {
        var url = rootPath + '/menu/deleteEntity.shtml';
        var s = CommnUtil.ajax(url, {
            ids: ids.join()
        }, "json");
        if (s == "success") {
            layer.msg('导入成功');
            refreshTree();
        } else {
            layer.msg('导入失败');
        }
    });
}