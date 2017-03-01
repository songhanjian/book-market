/**
 * Created by shj on 16-9-30.
 */
app.controller('registerController', function ($scope, loginService,userSession,$state,cssConstants) {


    $scope.init = function () {
        $scope.user = userSession.getEmptyUser();
        $scope.isLogin = userSession.isLogin();
        $scope.registerMesg = '';
        $scope.buttonInvalid = cssConstants.BUTTON_DISABLED;
        $scope.buttonValid = cssConstants.BUTTON_VALID;
    }


    $scope.accountCommit = function (valid) {
        if(!valid){
            $scope.registerMesg = 'register message incomplete or invalid,please checkout again';
        }
        else {
            $scope.registerMesg = '';
            loginService.register($scope.user,function (result) {
                $scope.ajaxRollback(result,function () {
                    $scope.isRigesterSuccess = true;
                    $scope.registerMesg = '';
                    alert('register success,please go to login');
                    $state.go('navi.myaccount');
                },function () {
                    $scope.isRigesterSuccess = false;
                    $scope.registerMesg = result.data;
                })
            })
        }


    }
    $scope.ajaxRollback = function (result,success,failed,otherwise) {
            if(result.code=='0'){
                if($.isFunction(success)){
                    success();
                }

            }else if(result.code=='-1'){
                if($.isFunction(failed)){
                    failed();
                }

            }else {
                if($.isFunction(otherwise)){
                    otherwise(params);
                }

            }

        }
}
)