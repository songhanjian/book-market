/**
 * Created by shj on 16-9-27.
 */
app.service('loginService',function($http,routeUtil){
    var loginService = {};

    var LOGIN_ACTION = 'account/login';
    var REGISTER_ACTION = 'account/register';


    loginService.login = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(LOGIN_ACTION, params,success, failed);
    };

    loginService.register = function ( params, success, failed) {
        //$scope.companyTest = 5;
        this.postUrl(REGISTER_ACTION, params,success, failed);
    };



    loginService.postUrl = function (url, params, success, failed,prefix) {
        routeUtil.postRequest(url,params,function (response, status, headers, config, result) {
            success(response, status, headers, config, result);
        }, function (err) {
            if (failed) {
                failed(err);
            }
        },prefix);

    };

    return loginService;
});
