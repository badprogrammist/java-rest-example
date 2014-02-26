
var userServicesModule = angular.module('userServicesModule', ['ngResource']);

userServicesModule.factory('userService', function($resource) {
    return $resource('user/:userId', {}, {
        query: {method: 'GET', params: {userId: ''}, isArray: true}
    });
});

