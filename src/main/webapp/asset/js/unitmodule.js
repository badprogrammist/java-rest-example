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
        save: {method: 'POST'},
        update: {method: 'PUT', params: {id: '@id'}},
        remove: {method: 'DELETE', params: {id: '@id'}}
    });
});

unitModule.controller('unitList', ['$scope', 'unitFactory',
    function($scope, unitFactory) {
        $scope.units = unitFactory.query();
        $scope.remove = function(unit) {
            unit.$remove(function() {
                $scope.units = unitFactory.query();
            });
        }
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
            unitFactory.save($scope.name,function(){$location.path('/');});
//            $scope.unit.$save(,function() {
//                $location.path('/');
//            });
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


