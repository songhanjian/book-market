/**
 * Created by shj on 16-10-4.
 */
app.service('cartService',function($http,routeUtil){
    var cartService = {};

    var ADD_TO_CART='cart/addToCart';
    var GET_CART='cart/getCart';
    var UPDATE_CART='cart/updateCart';
    var PAY_OFF='cart/payoff';
    var CART_BOOK_REL_COVERT = 'cart/cartBookRelCovert';
    var GET_CART_BOOK_REL_DTO = 'cart/getCartBookRelDto';
    var DELETE_CART_BOOK_REL_DTO = 'cart/deleteCart';
    cartService.addToCart = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(ADD_TO_CART, params,success, failed);
    };
    cartService.getCart = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(GET_CART, params,success, failed);
    };
    cartService.updateCart = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(UPDATE_CART, params,success, failed);
    };
    cartService.payoff = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(PAY_OFF, params,success, failed);
    };
    cartService.cartBookRelCovert = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(CART_BOOK_REL_COVERT, params,success, failed);
    };
    cartService.getCartBookRelDto = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(GET_CART_BOOK_REL_DTO, params,success, failed);
    };
    cartService.deleteCart = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(DELETE_CART_BOOK_REL_DTO, params,success, failed);
    };

    cartService.postUrl = function (url, params, success, failed,prefix) {
        routeUtil.postRequest(url,params,function (response, status, headers, config, result) {
            success(response, status, headers, config, result);
        }, function (err) {
            if (failed) {
                failed(err);
            }
        },prefix);

    };

    return cartService;
})