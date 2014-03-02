var userControllersModule = angular.module('userControllersModule',[])

userControllersModule.controller('userList',['$scope', 'userFactory',
    function($scope,userFactory) {
        $scope.users = userFactory.query();
        $scope.orderQuery = 'name';
        $scope.remove = function(user) {
            user.$remove(function() {
                $scope.users = userFactory.query();
            });
        }
    }
]);


userControllersModule.controller('userView',['$scope','$routeParams', 'userFactory',
    function($scope, $routeParams,userFactory) {
        $scope.user = userFactory.get({id:$routeParams.id});
    }
]);

userControllersModule.controller('userCreate',['$scope','$location', 'userFactory',
    function($scope,$location,userFactory) {
        $scope.user = new userFactory();
        $scope.save = function() {
            $scope.user.$save(function() {
                $location.path('/');
            });
        };
    }
]);

userControllersModule.controller('userEdit',['$scope','$routeParams','$location', 'userFactory',
    function($scope, $routeParams,$location,userFactory) {
        $scope.user = userFactory.get({id:$routeParams.id});
        $scope.save = function() {
            $scope.user.$update(function() {
                $location.path('/');
            });
        };
    }
]);


