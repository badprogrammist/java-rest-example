
var userServicesModule = angular.module('userServicesModule', ['ngResource']);

//userServicesModule.factory('usersService', function($resource) {
//    return $resource('user/:id', {id:'@id'}, {
//        query: {method: 'GET', isArray: true},
//    });
//});


userServicesModule.factory('userFactory', function ($resource) {
    return $resource('user/:id', {}, {
        query: { method: 'GET', isArray: true },
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@id'} },
        remove: { method: 'DELETE', params: {id: '@id'} }
    })
});

