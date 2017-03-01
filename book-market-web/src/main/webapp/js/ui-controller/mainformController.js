/**
 * Created by shj on 16-10-1.
 */
app.controller('mainformController', function ($scope,$state,userSession,detailSession) {

    $scope.init = function () {
        // $scope.change();
    }


    $scope.routeToDetail = function(id){
        detailSession.setBookId(id);
        $state.go("navi.detail");
    }

    // $scope.change =function () {
    //     $scope.$emit('userChange','msg_passed');
    // }
})