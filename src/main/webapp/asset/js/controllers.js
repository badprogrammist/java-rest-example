/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var userControllersModule = angular.module('userControllersModule',[])

userControllersModule.controller('userList',['$scope', 'userService',
    function($scope,userService) {
        //    $http.get('http://localhost:8080/resttest/user').success(function(data) {
        //        $scope.users = data;
        //        $scope.orderQuery = 'name';
        //    });
        $scope.users = userService.query();
        $scope.orderQuery = 'name';
    }
]);

userControllersModule.controller('userDetail',['$scope','$routeParams', 'userService',
    function($scope, $routeParams,userService) {
        //    $http.get('http://localhost:8080/resttest/user/'+$routeParams.userId).success(function(data) {
        //        $scope.user = data;
        //    });
        $scope.user = userService.get({userId:$routeParams.userId});
    }
]);

