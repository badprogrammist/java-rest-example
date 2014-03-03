var userModule = angular.module('userModule', ['ngRoute', 'ngResource']);

userModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/user', {templateUrl: 'partials/user/list.html', controller: 'userList'}).
                when('/user/view/:id', {templateUrl: 'partials/user/view.html', controller: 'userView'}).
                when('/user/edit/:id', {templateUrl: 'partials/user/edit.html', controller: 'userEdit'}).
                when('/user/create', {templateUrl: 'partials/user/create.html', controller: 'userCreate'});
    }
]);

userModule.factory('userFactory', function($resource) {
    return $resource('user/:id', {}, {
        query: {method: 'GET', isArray: true},
        save: {method: 'POST'},
        update: {method: 'PUT', params: {id: '@id'}},
        remove: {method: 'DELETE', params: {id: '@id'}}
    });
});

userModule.controller('userList', ['$scope', 'userFactory',
    function($scope, userFactory) {
        $scope.users = userFactory.query();
        $scope.orderQuery = 'username';
        $scope.remove = function(user) {
            user.$remove(function() {
                $scope.users = userFactory.query();
            });
        }
    }
]);

userModule.controller('userView', ['$scope', '$routeParams', 'userFactory',
    function($scope, $routeParams, userFactory) {
        $scope.user = userFactory.get({id: $routeParams.id});
    }
]);

userModule.controller('userCreate', ['$scope', '$location', 'userFactory',
    function($scope, $location, userFactory) {
        $scope.user = new userFactory();
        $scope.save = function() {
            $scope.user.$save(function() {
                $location.path('/');
            });
        };
    }
]);

userModule.controller('userEdit',['$scope','$routeParams','$location', 'userFactory',
    function($scope, $routeParams,$location,userFactory) {
        $scope.user = userFactory.get({id:$routeParams.id});
        $scope.save = function() {
            $scope.user.$update(function() {
                $location.path('/');
            });
        };
    }
]);


