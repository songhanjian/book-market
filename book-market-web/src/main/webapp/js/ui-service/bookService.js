/**
 * Created by shj on 16-10-2.
 */
app.service('bookService',function($http,routeUtil){
    var bookService = {};

    var GET_BOOK_BY_ID = 'book/detail';

    var GET_BOOM_BY_TAG = 'book/tag';

    bookService.getBookById = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(GET_BOOK_BY_ID, params,success, failed);
    };

    bookService.getBookByTag = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(GET_BOOM_BY_TAG, params,success, failed);
    };

    bookService.postUrl = function (url, params, success, failed,prefix) {
        routeUtil.postRequest(url,params,function (response, status, headers, config, result) {
            success(response, status, headers, config, result);
        }, function (err) {
            if (failed) {
                failed(err);
            }
        },prefix);

    };

    return bookService;


})