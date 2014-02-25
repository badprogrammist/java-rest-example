/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Hello($scope, $http) {
    $http.get('http://localhost:8080/resttest/user/2').
        success(function(data) {
            $scope.user = data;
        });
}
