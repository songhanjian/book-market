/**
 * Created by shj on 16-10-3.
 */
app.controller('categoryController', function ($state,$scope, bookService,userSession,detailSession) {
    $scope.init = function () {
        $scope.books = {};
        $scope.tagwords = null;
        $scope.pageCount = 0;
        $scope.currentPage = 1;
        $scope.page = {};
        $scope.getBooks(null,$scope.currentPage);
    }

    $scope.getBooks = function (tagwords,pageNum) {

            var temp = {};
            temp.tagwords = tagwords;
            temp.pageNum = pageNum;
            $scope.currentPage = pageNum;

            bookService.getBookByTag(temp,function (result) {
                $scope.ajaxRollback(result,function () {
                    $scope.books = result.data.books;
                    $scope.pageCount = result.data.pageCount;
                    $scope.page = $scope.pageInit($scope.pageCount);
                },function () {
                    alert("sys err");
                    $state.go('navi.mainform');
                })
            })



    }


    $scope.pageInit = function (pageCount) {
        var page = {};
        page.startPermit = true;
        page.endPermit = true;
        var currentPages = [];
        var pageStart = 0;
        if($scope.currentPage%5==0){
            pageStart = $scope.currentPage-4;
        }else {
            pageStart = $scope.currentPage-$scope.currentPage%5+1;
        }
        var limit = pageCount-pageStart>4?5:pageCount-pageStart+1;
        for(var i=0;i<limit;i++){
            var temp = {};
            temp.value = pageStart+i;
            if(temp.value!=$scope.currentPage){
                temp.isCurrent = false;
            }else {
                temp.isCurrent = true;
            }
            currentPages.push(temp);
        }
        if(pageStart==1){
            page.startPermit = false;
        }
        if(pageStart+5>pageCount){
            page.endPermit = false;
        }
        page.currentPages = currentPages;
        return page;


    }

    $scope.routeToDetail = function (id) {
        detailSession.setBookId(id);
        $state.go("navi.detail",{},{reload:true});
    }
    
    $scope.lastPages = function () {
        if($scope.page!=null){
            if($scope.page.currentPages!=null){
                var pageStart = $scope.page.currentPages[0].value-5;
                for(var i=0;i<5;i++){
                    $scope.page.currentPages[i].value = pageStart+i;
                    $scope.page.currentPages[i].isCurrent = false;
                }
                $scope.page.currentPages[4].isCurrent=true;
                if(pageStart==0){
                    page.startPermit = false;
                }

                    page.endPermit = true;
                    $scope.currentPage =   $scope.page.currentPages[4].value;

            }

        }
    }


    $scope.nextPages = function () {
        if($scope.page!=null){
            if($scope.page.currentPages!=null){
                var pageStart = $scope.page.currentPages[0].value+5;
                var limit = pageCount-pageStart>4?5:pageCount-pageStart+1;
                var currentPageTemp = [];
                for(var i=0;i<limit;i++){
                    var temp = {};
                    temp.value = pageStart+i;
                    temp.isCurrent = false;
                    currentPageTemp.push(temp);
                }
                $scope.page.currentPages = currentPageTemp;
                $scope.page.currentPages[0].isCurrent=true;

                    page.startPermit = true;

                if(pageStart+5>pageCount){
                    page.endPermit = false;
                }
                $scope.currentPage =   $scope.page.currentPages[0].value;

            }

        }
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