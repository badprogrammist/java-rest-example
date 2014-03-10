var securityModule = angular.module('securityModule', ['ngRoute', 'ngCookies']);

securityModule.config(['$routeProvider', '$locationProvider', '$httpProvider',
    function($routeProvider, $locationProvider, $httpProvider) {
        $routeProvider.
                when('/security/login', {templateUrl: 'partials/security/login.html', controller: 'securityController'}).
                when('/security/registration', {templateUrl: 'partials/security/registration.html', controller: 'securityController'}).
                when('/security/success', {templateUrl: 'partials/security/registration_success.html'}).
                when('/security/fail', {templateUrl: 'partials/security/registration_fail.html'}).
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

securityModule.controller('securityController', ['$scope', '$rootScope', '$location', '$http', '$cookieStore', 'securityService',
    function($scope, $rootScope, $location, $http, $cookieStore, securityService) {
        $scope.login = function() {
            securityService.authenticate({username: $scope.username, password: $scope.password}, addUserTransferInCookie);
        };
        $scope.register = function() {
            securityService.register({fullname: $scope.fullname, username: $scope.username, password: $scope.password}, function(success){
                if(success) {
                    $location.path("/security/success");
                } else {
                    $location.path("/security/fail");
                }
            });
        };
        function addUserTransferInCookie(userTransfer) {
            $rootScope.currentUser = userTransfer;
            $http.defaults.headers.common['X-Auth-Token'] = userTransfer.token;
            $cookieStore.put('currentUser', userTransfer);
            $location.path("/");
        }

    }
]);

securityModule.factory('securityService', function($resource) {
    return $resource('security/:action', {}, {
        authenticate: {
            method: 'POST',
            params: {'action': 'authenticate'}
        },
        register: {
            method: 'POST',
            params: {'action': 'register'}
        }
    });
});


