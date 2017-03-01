/**
 * Created by shj on 16-10-1.
 */
app.controller('naviController',function ($scope,$state,userSession,cartSession) {

    $scope.$on('userChange',function (event,msg) {
        $scope.setUser(msg);
        $scope.isLogin = true;
    })
    $scope.$on('cartChange',function (event,msg) {
        $scope.cart = cartSession.getCart();
        $scope.totalPrice = cartSession.getTotalPrice();
    })

    $scope.init = function () {
        $scope.isLogin = userSession.isLogin();
        $scope.user = userSession.getUser();
        $scope.cart = cartSession.getCart();
        $scope.totalPrice = cartSession.getTotalPrice();
    }

    $scope.setUser = function (user) {
        if((user!=undefined)||(user!=null)){
            $scope.user = user;
            $scope.cart = cartSession.getCart();
            $scope.totalPrice = cartSession.getTotalPrice();
        }
    }

    $scope.quitLogin = function () {
        userSession.userClear();
        $scope.$broadcast('quitLogin','quit');
        $scope.isLogin = false;
    }


    $scope.goToCart = function () {
        if(!userSession.isLogin()){
            alert("no account detected,please go to login");
            $state.go('navi.myaccount');
        }else {
            $state.go('navi.cart',{},{reload:true});
        }
    }


})