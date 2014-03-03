var securityModule = angular.module('securityModule', ['ngRoute', 'ngCookies']);

securityModule.config(['$routeProvider', '$locationProvider', '$httpProvider',
    function($routeProvider, $locationProvider, $httpProvider) {
        $routeProvider.
                when('/security/login', {templateUrl: 'partials/security/login.html', controller: 'securityLoginController'}).
                otherwise({redirectTo: '/'});

        var securityInterceptor = function($rootScope, $q, $location) {
            function success(response) {
                return response;
            }

            function error(response) {
                var status = response.status;
                var config = response.config;
                var method = config.method;
                var url = config.url;

                if (status == 401) {
                    $location.path("/security/login");
                }
                return $q.reject(response);
            }

            return function(promise) {
                return promise.then(success, error);
            };
        };

        $httpProvider.responseInterceptors.push(securityInterceptor);
    }
]);

securityModule.run(function($rootScope, $http, $location, $cookieStore) {
    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function() {
        delete $rootScope.error;
    });

    $rootScope.hasRole = function(role) {
        if ($rootScope.currentUser === undefined) {
            return false;
        }
        if ($rootScope.currentUser.roles[role] === undefined) {
            return false;
        }
        return $rootScope.currentUser.roles[role];
    };

    $rootScope.logout = function() {
        delete $rootScope.currentUser;
        delete $http.defaults.headers.common['X-Auth-Token'];
        $cookieStore.remove('currentUser');
        $location.path("/security/login");
    };

    /* Try getting valid user from cookie or go to login page */
    var originalPath = $location.path();
    $location.path("/security/login");
    var currentUser = $cookieStore.get('currentUser');
    if (currentUser !== undefined) {
        $rootScope.currentUser = currentUser;
        $http.defaults.headers.common['X-Auth-Token'] = currentUser.token;

        $location.path(originalPath);
    }
});

securityModule.controller('securityLoginController', ['$scope', '$rootScope', '$location', '$http', '$cookieStore', 'securityService',
    function($scope, $rootScope, $location, $http, $cookieStore, securityService) {
        $scope.login = function() {
            securityService.authenticate({username: $scope.username, password: $scope.password}, function(user) {
                $rootScope.currentUser = user;
                $http.defaults.headers.common['X-Auth-Token'] = user.token;
                $cookieStore.put('currentUser', user);
                $location.path("/");
            });
        };
    }
]);

securityModule.factory('securityService', function($resource) {
    return $resource('security/:action', {}, {
        authenticate: {
            method: 'POST',
            params: {'action': 'authenticate'}
        },
    });
});


