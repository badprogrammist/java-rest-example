//angular.module('user', ['userServicesModule']).
//        config(['$routeProvider', function($routeProvider) {
//        $routeProvider.
//                when('/user', {templateUrl: 'partial/list.html', controller: userList}).
//                when('/user/:userId', {templateUrl: 'partial/detail.html', controller: userDetail}).
//                otherwise({redirectTo: '/user'});
//    }]);

var userModule = angular.module('userModule', ['ngRoute','userServicesModule','userControllersModule']);

userModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/user', {templateUrl: 'userpartials/list.html', controller: 'userList'}).
                when('/user/view/:id', {templateUrl: 'userpartials/view.html', controller: 'userView'}).
                when('/user/edit/:id', {templateUrl: 'userpartials/edit.html', controller: 'userEdit' }).
                when('/user/create', {templateUrl: 'userpartials/create.html', controller: 'userCreate'}).
                otherwise({redirectTo: 'user/'});
    }
]);

