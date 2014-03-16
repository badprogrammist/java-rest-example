var unitModule = angular.module('unitModule', ['ngRoute', 'ngResource']);

unitModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/unit', {templateUrl: 'partials/unit/list.html', controller: 'unitList'}).
                when('/unit/view/:id', {templateUrl: 'partials/unit/view.html', controller: 'unitView'}).
                when('/unit/edit/:id', {templateUrl: 'partials/unit/edit.html', controller: 'unitEdit'}).
                when('/unit/create', {templateUrl: 'partials/unit/create.html', controller: 'unitCreate'});
    }
]);

unitModule.factory('unitFactory', function($resource) {
    return $resource('unit/:id', {}, {
        query: {method: 'GET', isArray: true},
        save: {method: 'POST', headers: {'Content-Type': 'application/json'}},
        update: {method: 'PUT', headers: {'Content-Type': 'application/json'}, params: {id: '@id'}},
        remove: {method: 'DELETE', params: {id: '@id'}}
    });
});

//unitModule.config(['$httpProvider', function($httpProvider) {
//    $httpProvider.defaults.headers.patch = {
//         'Content-Type': 'application/json;charset=utf-8'
//    };
//}]);

unitModule.controller('unitList', ['$scope', 'unitFactory',
    function($scope, unitFactory) {
        $scope.units = unitFactory.query();
        $scope.remove = function(unit) {
            unit.$remove(function() {
                $scope.units = unitFactory.query();
            });
        };
    }
]);

unitModule.controller('unitView', ['$scope', '$routeParams', 'unitFactory',
    function($scope, $routeParams, unitFactory) {
        $scope.unit = unitFactory.get({id: $routeParams.id});
    }
]);

unitModule.controller('unitCreate', ['$scope', '$location', 'unitFactory',
    function($scope, $location, unitFactory) {
        $scope.unit = new unitFactory();
        $scope.save = function() {
            window.console.log($scope.unit);
            $scope.unit.$save(function() {
                $location.path('/unit');
            });
        };
    }
]);

unitModule.controller('unitEdit',['$scope','$routeParams','$location', 'unitFactory',
    function($scope, $routeParams,$location,unitFactory) {
        $scope.unit = unitFactory.get({id:$routeParams.id});
        $scope.save = function() {
            $scope.unit.$update(function() {
                $location.path('/');
            });
        };
    }
]);


