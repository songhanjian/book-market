/**
 * Created by shj on 16-10-2.
 */
app.controller('detailsController', function ($state,$scope, loginService,userSession,detailSession,bookService,cartService,cartSession) {

    $scope.init = function () {
        $scope.book = {};
        $scope.briefDesc = "";
        $scope.tab1 = true;
        $scope.tab2 = false;
        $scope.bookRelate = {};
        if(detailSession.getBookId()==null){
            alert("sys err");
            $state.go('navi.mainform');
        }else {
            $scope.getBook(detailSession.getBookId());
        }



    }

    $scope.getBook = function (id) {
        var map = {};
        map.id = id;
        bookService.getBookById(map,function (result) {
            $scope.ajaxRollback(result,function () {
                $scope.book = result.data.book;
                $scope.bookRelate = result.data.bookRelate;
                if($scope.book.desc!==null){
                    if($scope.book.desc.length>200){
                        $scope.briefDesc = $scope.book.desc.substr(0,500);
                        $scope.briefDesc = $scope.briefDesc+"...";
                    }
                }
            },function () {
                alert(result.data);
                $state.go('navi.mainform');
            })
        })
    }

    $scope.routeToDetail = function (id) {
        detailSession.setBookId(id);
        $state.go("navi.detail",{},{reload:true});
    }


    $scope.addToCart =function() {
        if(!userSession.isLogin()){
            alert("no account detected,please login first");
            $state.go('navi.myaccount');
        }else {
            var cartBookRel={};
            cartBookRel.cartId = cartSession.getId();
            cartBookRel.bookId = $scope.book.id;
            cartBookRel.bookName = $scope.book.name;
            cartBookRel.bookPrice = $scope.book.price;
            cartBookRel.count = 1;
            cartBookRel.totalPrice = $scope.book.price;
            cartBookRel.isDelete = 0;
            cartService.addToCart(cartBookRel,function (result) {
                $scope.ajaxRollback(result,function () {
                    cartSession.updateCartBookRel(result.data);
                    $scope.cartChange();
                    alert("add to cart success");
                },function () {
                    alert(result.data);
                })

            })
        }

    }

    $scope.cartChange = function () {
        $scope.$emit('cartChange',"cartChange");
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