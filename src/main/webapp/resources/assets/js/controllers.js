var appControllers = angular.module('appControllers', []);


appControllers.controller("contactController", ['$scope', '$http', function ($scope, $http) {


    $scope.refresh  = function () {
        $http.get("/contact")
            .then(function (response) {
                $scope.contacts = response.data;
            });
    };

    $scope.refresh();

    $scope.addContact = function () {
        $http.post('/contact',{"name":$scope.name,  "phone":$scope.phone, "email":$scope.email }).
            success(function (data, status, headers, config) {
                $scope.name = '';
                $scope.phone = '';
                $scope.email = '';
                $scope.refresh();
            }).
            error(function (data, status, headers, config) {
                alert("Exception details: " + JSON.stringify({data: data}));
            });
    };

    $scope.delContact = function(){

    }
}]);