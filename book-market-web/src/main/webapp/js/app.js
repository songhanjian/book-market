'use strict';


var app = angular.module('app', [
    //'ngAnimate',
    //'ngCookies',
    //'ngStorage',
    'ui.router',
    'ui.bootstrap',
    'ui.load',
    'ui.jq',
    'ui.validate',
    //'pascalprecht.translate',
    'ngDatetimepicker',
    'ui.bootstrap.pagination',
    'angularFileUpload',
        //'colorpicker.module'
        'ui.bootstrap.tree',
        'plugins-treeview'
    ])
.run(
  [  '$rootScope', '$state', '$stateParams',
    function ($rootScope,   $state,   $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
  ]
)
.config(
  [  '$stateProvider', '$urlRouterProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ($stateProvider,   $urlRouterProvider,   $controllerProvider,   $compileProvider,   $filterProvider,   $provide) {

        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;
        //
        // $urlRouterProvider
        //     .otherwise('/adBase/init');
        // $stateProvider
        //     .state('adBase', {
        //         url: '/adBase/init',
        //         templateUrl: 'html/adBase.html',
        //         controller : "adBaseController"
        //
        //     })
        //     .state('adSource', {
        //         url: '/adSource/init',
        //         templateUrl: 'html/adSource.html',
        //         controller : "adSourceController"
        //     })
        //     .state('addSource', {
        //         url: '/ad/adSource/detail',
        //         templateUrl: 'html/adSourceDetail.html',
        //         controller : "adSourceDetailController"
        //     })




            //广告配置
            // .state('setting', {
            //     url: '/ad/setting',
            //     templateUrl: 'html/setting.html',
            //     controller : 'adSettingControlller'
            // })
        $urlRouterProvider
            .otherwise('/navi/mainform');

        $stateProvider
            //主页

            .state('navi',{
                abstract:true,
                url: '/navi',
                templateUrl:'navi.htm',
                controller: 'naviController'
                }
            )

            .state('navi.mainform', {
                url: '/mainform',
                templateUrl: 'mainform.htm',
                controller : 'mainformController'
            })
            .state('navi.myaccount', {
                url: '/myaccount',
                templateUrl: 'myaccount.htm',
                controller: 'loginController'
            })
            // about
            .state('navi.about', {
                url: '/about',
                templateUrl: 'about.htm'
            })
            // 查询+弹窗
            .state('navi.cart', {
                url: '/cart',
                templateUrl: 'cart.htm',
                controller: 'cartController'
            })
            // 进程
            .state('navi.category', {
                url: '/category',
                templateUrl: 'category.htm',
                controller:'categoryController'
            })
            // 树
            .state('contact', {
                url: 'contact',
                templateUrl: 'contact.htm'
            })
            // 特殊切换卡
            .state('navi.detail', {
                url: '/detail',
                templateUrl: 'details.htm',
                controller: 'detailsController'
            })

            // 登录 注册 忘记密码
            .state('index', {
                url: 'index',
                template: 'index.htm'
            })

            .state('navi.register', {
                url: '/register',
                templateUrl: 'register.htm',
                controller: 'registerController'
            })
            .state('special', {
                url: '/special',
                templateUrl: 'specials.htm'
            })



    }
  ]
)



/**
 * jQuery plugin config use ui-jq directive , config the js and css files that required
 * key: function name of the jQuery plugin
 * value: array of the css js file located
 */
.constant('JQ_CONFIG', {
    easyPieChart:   ['js/jquery/charts/easypiechart/jquery.easy-pie-chart.js'],
    sparkline:      ['js/jquery/charts/sparkline/jquery.sparkline.min.js'],
    plot:           ['js/jquery/charts/flot/jquery.flot.min.js',
                        'js/jquery/charts/flot/jquery.flot.resize.js',
                        'js/jquery/charts/flot/jquery.flot.tooltip.min.js',
                        'js/jquery/charts/flot/jquery.flot.spline.js',
                        'js/jquery/charts/flot/jquery.flot.orderBars.js',
                        'js/jquery/charts/flot/jquery.flot.pie.min.js'],
    slimScroll:     ['js/jquery/slimscroll/jquery.slimscroll.min.js'],
    sortable:       ['js/jquery/sortable/jquery.sortable.js'],
    nestable:       ['js/jquery/nestable/jquery.nestable.js',
                        'js/jquery/nestable/nestable.css'],
    filestyle:      ['js/jquery/file/bootstrap-filestyle.min.js'],
    slider:         ['js/jquery/slider/bootstrap-slider.js',
                        'js/jquery/slider/slider.css'],
    chosen:         ['js/jquery/chosen/chosen.jquery.min.js',
                        'js/jquery/chosen/chosen.css'],
    TouchSpin:      ['js/jquery/spinner/jquery.bootstrap-touchspin.min.js',
                        'js/jquery/spinner/jquery.bootstrap-touchspin.css'],
    wysiwyg:        ['js/jquery/wysiwyg/bootstrap-wysiwyg.js',
                        'js/jquery/wysiwyg/jquery.hotkeys.js'],
    dataTable:      ['js/jquery/datatables/jquery.dataTables.min.js',
                        'js/jquery/datatables/dataTables.bootstrap.js',
                        'js/jquery/datatables/dataTables.bootstrap.css'],
    vectorMap:      ['js/jquery/jvectormap/jquery-jvectormap.min.js',
                        'js/jquery/jvectormap/jquery-jvectormap-world-mill-en.js',
                        'js/jquery/jvectormap/jquery-jvectormap-us-aea-en.js',
                        'js/jquery/jvectormap/jquery-jvectormap.css'],
    footable:       ['js/jquery/footable/footable.all.min.js',
                        'js/jquery/footable/footable.core.css']
    }
)


.constant('MODULE_CONFIG', {
    select2:        ['js/jquery/select2/select2.css',
                        'js/jquery/select2/select2-bootstrap.css',
                        'js/jquery/select2/select2.min.js',
                        'js/modules/ui-select2.js']
    }
)
;

