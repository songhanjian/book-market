/*
 * angular-ui-bootstrap
 * http://angular-ui.github.io/bootstrap/

 * Version: 0.12.1 - 2015-10-17
 * License: MIT
 */
//angular.module("ui.bootstrap", ["ui.bootstrap.tpls","ui.bootstrap.pagination"]);
//angular.module("ui.bootstrap.tpls", ["template/pagination/pager.html","template/pagination/pagination.html"]);
angular.module('ui.bootstrap.pagination', ["template/pagination/pager.html","template/pagination/pagination.html"])

    .controller('PaginationController', ['$scope', '$attrs', '$parse', function ($scope, $attrs, $parse) {
      var self = this,
          ngModelCtrl = { $setViewValue: angular.noop }, // nullModelCtrl
          setNumPages = $attrs.numPages ? $parse($attrs.numPages).assign : angular.noop;

      this.init = function(ngModelCtrl_, config) {
        ngModelCtrl = ngModelCtrl_;
        this.config = config;

        ngModelCtrl.$render = function() {
          self.render();
        };

        if ($attrs.itemsPerPage) {
          $scope.$parent.$watch($parse($attrs.itemsPerPage), function(n,o) {
            if(n) {
              self.itemsPerPage = parseInt(n, 10);
              $scope.itemPerPage = parseInt(n, 10);
              $scope.totalPages = self.calculateTotalPages();
            }
          });
        } else {
          this.itemsPerPage = config.itemsPerPage;
        }
      };

      this.calculateTotalPages = function() {
        var totalPages = this.itemsPerPage < 1 ? 1 : Math.ceil($scope.totalItems / this.itemsPerPage);
        return Math.max(totalPages || 0, 1);
      };

      this.render = function() {
        if(ngModelCtrl.$viewValue!='')
          $scope.page = parseInt(ngModelCtrl.$viewValue, 10) || 1;
      };

      $scope.selectPage = function(page) {
        if ( $scope.page !== page && page > 0 && page <= $scope.totalPages) {
          ngModelCtrl.$setViewValue(page);
          ngModelCtrl.$render();
        }
      };

      $scope.getText = function( key ) {
        return $scope[key + 'Text'] || self.config[key + 'Text'];
      };
      $scope.noPrevious = function() {
        return $scope.page === 1;
      };
      $scope.noNext = function() {
        return $scope.page === $scope.totalPages;
      };

      $scope.$watch('totalItems', function() {
        $scope.totalPages = self.calculateTotalPages();
      });
      $scope.$watch('totalPages', function(value) {
        setNumPages($scope.$parent, value); // Readonly variable

        if ( $scope.page > value ) {
          $scope.selectPage(value);
        } else {
          ngModelCtrl.$render();
        }
      });

      $scope.checkPage=function(min,max,c) {
        var current = c;
        if (typeof current != 'string' || current.length > 0)
          current = current < min ? min : current > max ? max : current;
        return current;
      };
    }])

.constant('paginationConfig', {
  itemsPerPage: 10,
  boundaryLinks: false,
  directionLinks: true,
  firstText: 'First',
  previousText: 'Previous',
  nextText: 'Next',
  lastText: 'Last',
  rotate: true
})

.directive('pagination', ['$parse', 'paginationConfig', function($parse, paginationConfig) {
  return {
    restrict: 'EA',
    scope: {
      totalItems: '=',
      itemsPerPage:'=',
      pageSizes:'=',
      editPage:'=',
      firstText: '@',
      previousText: '@',
      nextText: '@',
      lastText: '@',
      currentPage:'=ngModel'
    },
    require: ['pagination', '?ngModel'],
    controller: 'PaginationController',
    templateUrl: 'template/pagination/pagination.html',
    replace: true,
    link: function(scope, element, attrs, ctrls) {
      var paginationCtrl = ctrls[0], ngModelCtrl = ctrls[1];

      if (!ngModelCtrl) {
         return; // do nothing if no ng-model
      }
      scope.$watch('itemsPerPage',function(n,o){
        if(n&&n!=o) {
          ngModelCtrl.$setViewValue(0);
          ngModelCtrl.$setViewValue(1);
          ngModelCtrl.$render();
        }
      })

      // Setup configuration parameters
      var maxSize = angular.isDefined(attrs.maxSize) ? scope.$parent.$eval(attrs.maxSize) : paginationConfig.maxSize,
          rotate = angular.isDefined(attrs.rotate) ? scope.$parent.$eval(attrs.rotate) : paginationConfig.rotate;
      scope.boundaryLinks = angular.isDefined(attrs.boundaryLinks) ? scope.$parent.$eval(attrs.boundaryLinks) : paginationConfig.boundaryLinks;
      scope.directionLinks = angular.isDefined(attrs.directionLinks) ? scope.$parent.$eval(attrs.directionLinks) : paginationConfig.directionLinks;

      paginationCtrl.init(ngModelCtrl, paginationConfig);
      if (attrs.maxSize) {
        scope.$parent.$watch($parse(attrs.maxSize), function(value) {
          maxSize = parseInt(value, 10);
          paginationCtrl.render();
        });
      }
      // Create page object used in template
      function makePage(number, text, isActive) {
        return {
          number: number,
          text: text,
          active: isActive
        };
      }

      function getPages(currentPage, totalPages) {
        var pages = [];

        // Default page limits
        var startPage = 1, endPage = totalPages;
        var isMaxSized = ( angular.isDefined(maxSize) && maxSize < totalPages );

        // recompute if maxSize
        if ( isMaxSized ) {
          if ( rotate ) {
            // Current page is displayed in the middle of the visible ones
            startPage = Math.max(currentPage - Math.floor(maxSize/2), 1);
            endPage   = startPage + maxSize - 1;

            // Adjust if limit is exceeded
            if (endPage > totalPages) {
              endPage   = totalPages;
              startPage = endPage - maxSize + 1;
            }
          } else {
            // Visible pages are paginated with maxSize
            startPage = ((Math.ceil(currentPage / maxSize) - 1) * maxSize) + 1;

            // Adjust last page if limit is exceeded
            endPage = Math.min(startPage + maxSize - 1, totalPages);
          }
        }

        // Add page number links
        for (var number = startPage; number <= endPage; number++) {
          var page = makePage(number, number, number === currentPage);
          pages.push(page);
        }

        // Add links to move between page sets
        if ( isMaxSized && ! rotate ) {
          if ( startPage > 1 ) {
            var previousPageSet = makePage(startPage - 1, '...', false);
            pages.unshift(previousPageSet);
          }

          if ( endPage < totalPages ) {
            var nextPageSet = makePage(endPage + 1, '...', false);
            pages.push(nextPageSet);
          }
        }

        return pages;
      }

      var originalRender = paginationCtrl.render;
      paginationCtrl.render = function() {
        originalRender();
        if (scope.page > 0 && scope.page <= scope.totalPages) {
          scope.pages = getPages(scope.page, scope.totalPages);
        }
      };
    }
  };
}])

.constant('pagerConfig', {
  itemsPerPage: 10,
  previousText: '« Previous',
  nextText: 'Next »',
  align: true
})

.directive('pager', ['pagerConfig', function(pagerConfig) {
  return {
    restrict: 'EA',
    scope: {
      totalItems: '=',
      previousText: '@',
      nextText: '@'
    },
    require: ['pager', '?ngModel'],
    controller: 'PaginationController',
    templateUrl: 'template/pagination/pager.html',
    replace: true,
    link: function(scope, element, attrs, ctrls) {
      var paginationCtrl = ctrls[0], ngModelCtrl = ctrls[1];

      if (!ngModelCtrl) {
         return; // do nothing if no ng-model
      }

      scope.align = angular.isDefined(attrs.align) ? scope.$parent.$eval(attrs.align) : pagerConfig.align;
      paginationCtrl.init(ngModelCtrl, pagerConfig);
    }
  };
}]);

angular.module("template/pagination/pager.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("template/pagination/pager.html",
    "<ul class=\"pager\">\n" +
    "  <li ng-class=\"{disabled: noPrevious(), previous: align}\"><a href ng-click=\"selectPage(page - 1)\">{{getText('previous')}}</a></li>\n" +
    "  <li ng-class=\"{disabled: noNext(), next: align}\"><a href ng-click=\"selectPage(page + 1)\">{{getText('next')}}</a></li>\n" +
    "</ul>");
}]);

angular.module("template/pagination/pagination.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("template/pagination/pagination.html",
      "<div class='row'><div class='col-sm-8 text-left'><small class='text-muted inline m-t-sm m-b-sm' ng-show='editPage'>总共{{totalItems}}条数据</small><small class='text-muted inline m-t-sm m-b-sm' ng-show='editPage'>/共{{totalPages}}页</small><small class='mgl15px text-muted inline m-t-sm m-b-sm'>跳至</small><input type='text' style='width: 80px;margin: 0 5px' class='inputSubmit'  ng-model='currentPage' ng-pattern='/^[0-9]+$/' ng-change='selectPage(currentPage=checkPage(1,totalPages,currentPage))' requied><small class='text-muted inline m-t-sm m-b-sm'>页</small><span ng-show='pageSizes'><small class='mgl15px text-muted inline m-t-sm m-b-sm'>每页显示</small><select name='' class='inputSubmit' style='width: 100px;margin: 0 5px;'  ng-model='itemsPerPage'  ng-options='count as count  for count in pageSizes'></select></span><small class='text-muted inline m-t-sm m-b-sm'>条</small></div><div class='col-sm-4 text-right text-center-xs'><ul class='pagination pagination-sm m-t-none m-b-none'><li  ng-if='boundaryLinks' ng-class='{disabled: noPrevious()}'><a href ng-click='selectPage(1)'><i class='fa fa-chevron-left'></i>{{getText('first')}}</a></li><li ng-if='directionLinks' ng-class='{disabled: noPrevious()}'><a href ng-click='selectPage(page - 1)'>{{getText('previous')}}</a></li><li ng-if='page.number==1||page.number==totalPages||(page.number-currentPage)*(page.number-currentPage)<=16' ng-repeat='page in pages track by $index' ng-class='{active: page.active}'><a href  ng-if='page.number==1||page.number==totalPages||(page.number-currentPage)*(page.number-currentPage)<16' ng-click='selectPage(page.number)'>{{page.text}}</a><span ng-if='page.number!=1&&page.number!=totalPages&&(page.number-currentPage)*(page.number-currentPage)==16'>....</span></li><li ng-if='directionLinks' ng-class='{disabled: noNext()}'><a href ng-click='selectPage(page + 1)'>{{getText('next')}}</a></li><li ng-if='boundaryLinks' ng-class='{disabled: noNext()}'><a href ng-click='selectPage(totalPages)'><i class='fa fa-chevron-right'></i>{{getText('last')}}</a></li></ul></div></div>");
}]);

