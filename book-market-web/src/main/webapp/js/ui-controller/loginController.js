
/**
 * Created by shj on 16-9-27.
 */
app.controller('loginController', function ($scope, loginService,userSession,cartSession) {




    $scope.$on('quitLogin',function (event,msg) {
        $scope.userClear();
        $scope.isLogin = false;
    })

    $scope.init = function () {
        if(userSession.isLogin()){
            $scope.user = userSession.getUser();
        }else {
            var user = {};
            user.username = null;
            user.password = null;
            $scope.user = user;

            $scope.isLogin = userSession.isLogin();
            $scope.loginMesg = '';
        }


    }

    $scope.userClear = function () {
        $scope.user.username = null;
        $scope.user.password = null;
    }

    $scope.login = function () {
        loginService.login($scope.user,function (result) {
            $scope.ajaxRollback(result,function () {
                userSession.setUser(result.data.user);
                cartSession.setCart(result.data.cart);
                $scope.userChange(result.data.user);
                $scope.isLogin = true;
                $scope.loginMesg = '';
                alert("login success");

            },function () {
                alert(result.data);
                $scope.isLogin = false;
            })
        })
    }


    $scope.userChange = function (userData) {
        $scope.$emit('userChange',userData);
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




})