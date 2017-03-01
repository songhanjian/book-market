/**
 * Created by Roy on 16/8/3.
 * version:2.0
 */
angular.module('plugins-treeview',['treeviewTpl1','treeviewTpl2'])
    .directive('treeView',[function(){
        return {
            restrict: 'E',
            templateUrl: '/treeView.html',
            scope: {
                treeData: '=',
                canChecked: '=',
                textField: '@',
                valueField:'@',
                itemTemplateUrl: '@',
                ngModel:'='
            },


            controller:['$scope','$filter', function($scope,$filter){
                var _isArray=angular.isArray;
                var _forEach=angular.forEach;
                $scope.itemExpended = function(item, $event){
                    item.$$isExpend = ! item.$$isExpend;
                    $event.stopPropagation();
                };

                $scope.getItemIcon = function(item){
                    var isLeaf = $scope.isLeaf(item);

                    if(isLeaf){
//                                    return 'fa fa-leaf';
                        return 'fa fa-plus opacity0';
                    }
                    return item.$$isExpend ? 'fa fa-minus': 'fa fa-plus';
                };

                $scope.isLeaf = function(item){
                    return !item.children || !item.children.length;
                };

                //模拟点击元素
                checkThis=function(event){
                    event.target.parentNode.children[1].click();
                }

                $scope.itemClicked = function(item){
                    $scope.checkAllData(item);
                    $scope.ngModel=[];
                    $scope.computeData($scope.treeData);
                    $scope.ngModel=$filter('orderBy')($scope.ngModel);
                    //($scope[callback] || angular.noop)({
                    //    $item:item,
                    //    $event:$event
                    //});

                };

                $scope.checkAllData=function(item) {
                    var selected = item.selected;
                    if (_isArray(item.children) && item.children.length > 0) {
                        _forEach(item.children, function (i) {
                            i.selected = selected;
                            $scope.checkAllData(i);
                        })
                    }
                }
                $scope.computeData=function(datas){
                    var selectedNum=0;
                    if(_isArray(datas)) {
                        _forEach(datas, function (data) {
                            if (_isArray(data.children)) {
                                data.selectedNum = $scope.computeData(data.children);
                                data.selected=data.selectedNum>0;
                            }
                            if (data.selected) {
                                selectedNum++;
                                $scope.ngModel.push(data[$scope.valueField]-0);
                            }
                        })
                    }
                    return selectedNum;
                }
                $scope.$watch('treeData',function(n,o){
                    if(!o&&n!=o){
                        $scope.computeData($scope.treeData);
                        $scope.ngModel=$filter('orderBy')($scope.ngModel);
                    }
                })
            }]
        };
    }]);
angular.module('treeviewTpl1',[]).run(["$templateCache",function($templateCache){
    $templateCache.put('/treeView.html','<ul class="tree-view"><li ng-repeat="item in treeData" ng-include="itemTemplateUrl || \'/treeItem.html\'"></li></ul>')
}])
angular.module('treeviewTpl2',[]).run(["$templateCache",function($templateCache){
    $templateCache.put('/treeItem.html','<i ng-click="itemExpended(item, $event);" class="{{getItemIcon(item)}}"></i> <input type="checkbox" ng-model="item.selected" class="check-box" ng-if="canChecked" ng-change="itemClicked(item)"> <span class="text-field" onclick="checkThis(event)">{{item[textField]}}<span ng-if="item.children.length>0&&item.selected" ng-bind="\'(\'+item.selectedNum+\'/\'+item.children.length+\')\'"></span></span> <ul ng-if="!isLeaf(item)" ng-show="item.$$isExpend"> <li ng-repeat="item in item.children" ng-include="itemTemplateUrl || \'/treeItem.html\'"> </li> </ul>')
}])
document.write('<style>.tree-view li{list-style: none;}.tree-view .opacity0{opacity: 0;}</style>')