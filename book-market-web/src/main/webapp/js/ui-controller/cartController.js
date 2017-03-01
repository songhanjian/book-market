/**
 * Created by shj on 16-10-4.
 */
app.controller('cartController', function ($state,$scope, bookService,userSession,detailSession,cartService,cartSession) {
    $scope.init = function () {
        if(!userSession.isLogin()){
            alert("no account detected,please go to login first");
            $state.go('navi.myaccount');
        }else {
            $scope.cart = cartSession.getCart();
            $scope.getCartBookRelDto(cartSession.getId());
            $scope.currentTotalPrice = 0;
        }

    }



    $scope.cartBookRelCovert = function (param) {
        cartService.cartBookRelCovert(param,function (result) {
            $scope.ajaxRollback(result,function () {
                cartSession.updateCartBookRels(result.data);
                $scope.cart.cartBookRels = cartSession.getCart().cartBookRels;
                $scope.totalPrice = cartSession.getTotalPrice();
                $scope.cartReLoad();
            },function () {
                alert(result.data);
                $state.go('navi.mainform');

            })
        })
    }

    $scope.getCartBookRelDto = function (param) {
        cartService.getCartBookRelDto(param,function (result) {
            $scope.ajaxRollback(result,function () {
                cartSession.updateCartBookRels(result.data);
                $scope.cart.cartBookRels = cartSession.getCart().cartBookRels;
                $scope.totalPrice = cartSession.getTotalPrice();
                $scope.cartReLoad();
            },function () {
                alert(result.data);
                $state.go('navi.mainform');

            })
        })
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


    $scope.cartReLoad = function () {
        $scope.$emit('cartChange','cartChange');
    }


    $scope.cartBookRelChosen = function (param) {
        if(param.isChosen){
            param.isChosen=false;
            $scope.currentTotalPrice = $scope.currentTotalPrice-param.totalPrice;
        }else {
            param.isChosen=true;
            $scope.currentTotalPrice = $scope.currentTotalPrice+param.totalPrice;
        }

    }

    $scope.addCount = function (param) {
        var totalTemp= param.totalPrice;
        if(param.count==99){
            param.count=99;
        }else {
            param.count = param.count+1;
        }
        param.totalPrice = param.bookPrice*param.count;
        if(param.isChosen){
            $scope.currentTotalPrice = $scope.currentTotalPrice-totalTemp+param.totalPrice;
        }
    }

    $scope.subCount = function (param) {
        var totalTemp= param.totalPrice;
       if(param.count==0){
            param.count=0;
        }else {
            param.count = param.count-1;
        }
        param.totalPrice = param.bookPrice*param.count;
        if(param.isChosen){
            $scope.currentTotalPrice = $scope.currentTotalPrice-totalTemp+param.totalPrice;
        }
    }

    $scope.saveCart=function () {
        cartService.updateCart($scope.cart.cartBookRels,function (result) {
            $scope.ajaxRollback(result,function () {
                alert(result.data);
                $scope.init();
            },function () {
                alert(result.data);
            })
        })
    }


    $scope.deleteCart = function () {
        cartService.deleteCart($scope.cart.cartBookRels,function (result) {
            $scope.ajaxRollback(result,function () {
                alert(result.data);
                $scope.init();
            },function () {
                alert(result.data);
            })
        })
    }

    $scope.payoff = function () {
        cartService.payoff($scope.cart.cartBookRels,function (result) {
            $scope.ajaxRollback(result,function () {
                var payPrice = 0;
                angular.forEach($scope.cart.cartBookRels,function (e) {
                    if(e.isChosen){
                        payPrice+=e.totalPrice;
                    }
                })


                alert("totalPrice  "+payPrice+","+result.data);
                $scope.init();
            },function () {
                alert(result.data);
            })
        })
    }

})