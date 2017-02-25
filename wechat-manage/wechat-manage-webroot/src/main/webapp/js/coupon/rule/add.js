angular.module('myApp', [])
    .controller('MainController', function ($scope, $http) {
        $scope.coupon = {}
        $http.get(rootPath + '/couponrule/queryCouponRuleInfo.shtml').then(function (res) {
            var r = angular.fromJson(res);
            if (!!r && r.status == 200 && !!r.data && !!r.data.success) {
                $scope.coupon = r.data.data;
            }
        })
        $scope.frm = {
            colorlist: [],
            djqselected: null,
            zkqselected: null,
            lpqselected: null,
            yqqselected: null,
            cxqselected: null,
            yyqselected: null
        }
        $scope.$watch('frm.djqselected', function (nv, ov) {
            if (nv == ov) return;
            console.log("监听 djqselected");
        })
        $http.post(rootPath + '/dic/queryDicList.shtml', {key: "coupon_bg"}).then(function (res) {
            var r = angular.fromJson(res);
            if (!!r && r.status == 200 && !!r.data && !!r.data.success) {
                $scope.frm.colorlist = r.data.list;
            }
        })
        $scope.submitForm = function () {
            $http({
                method: 'POST',
                url: rootPath + '/couponrule/addEntity2.shtml',
                data: $scope.coupon
                //,headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).then(function (res) {
                var r = angular.fromJson(res);
                if (!!r && r.status == 200 && !!r.data && r.data == "success") {
                    layer.alert('添加成功！', 3);
                } else {
                    layer.alert('添加失败！', 3);
                }
            });
        }
    });