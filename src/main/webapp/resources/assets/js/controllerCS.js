var appControllers = angular.module('appControllers', []);


appControllers.controller("contactController", ['$scope', '$http', function ($scope, $http) {
    $scope.selectedContact = "No selected contact";
    $scope.selectedContactIndex = null;

    $scope.selectedContactName = "No selected contact";
    $scope.selectedContactLastName = "No selected contact";
    $scope.selectedContactBirthDate = "No selected contact";

    var getBirthDateStr = function(index){
        var bd = $scope.contacts[index].birthDate;
        var str = "";
        if (bd[2].toString().length === 1) {
            str += "0"
        }
        str += bd[2] + "/";
        if (bd[1].toString().length === 1) {
            str += "0"
        }
        str += bd[1] + "/" + bd[0];

        return str;
    };

    var HideAll = function(){
        $scope.elem1 = false;
        $scope.elem2 = false;
        $scope.elem3 = false;
        $scope.elem4 = false;
        $scope.elem5 = false;
        $scope.elem6 = false;
        $scope.elem7 = false;
        $scope.elem8 = false;
        $scope.elem9 = false;
        $scope.elem10 = false;
    };

    HideAll();
    $scope.elem2 = true;

    $scope.showAddNewUser = function(){
        HideAll();
        $scope.elem1 = true;
    };

    $scope.showAllUsers = function(){
        HideAll();
        $scope.elem2 = true;
    };

    $scope.showUserInfo = function(){
        HideAll();
        $scope.elem3 = true;
    };

    $scope.showFriends = function(){
        HideAll();
        $scope.getContactFriends();
        $scope.elem4 = true;
    };

    $scope.showPlaces = function(){
        HideAll();
        $scope.getContactPlaces();
        $scope.elem5 = true;
    };

    $scope.showHobbies = function(){
        HideAll();
        $scope.elem6 = true;
    };

    $scope.showContactsWidthHobby = function(){
        HideAll();
        $scope.elem7 = true;
    };

    $scope.showContactsForPlace = function(){
        HideAll();
        $scope.elem8 = true;
    };

    $scope.showConversation = function(){
        HideAll();
        $scope.elem9 = true;
    };

    $scope.showContactConversation = function(){
        $scope.elem10 = true;
    };
// User -----------------------------------------------------------------------------------------------------------

    $scope.refresh  = function () {
        $http.get("/contact")
            .then(function (response) {
                $scope.contacts = response.data;
                console.log($scope.contacts);
            });
    };

    $scope.refresh();

    $scope.addContact = function () {
        if($scope.day.toString().length === 1){$scope.day = "0" + $scope.day;}
        if($scope.month.toString().length === 1){$scope.month = "0" + $scope.month;}

        var str = $scope.day + "/" + $scope.month + "/" + $scope.year;//  31/05/1985

        $http.post('/contact',{"firstName":$scope.firstName,  "lastName":$scope.lastName, "birthDate":str }).
            success(function (data, status, headers, config) {
                $scope.firstName = '';
                $scope.lastName = '';
                $scope.day = '';
                $scope.month = '';
                $scope.year = '';
                $scope.refresh();
            }).
            error(function (data, status, headers, config) {
                alert("Exception details: " + JSON.stringify({data: data}));
            });
    };

    $scope.selectContact = function(index){
        $scope.selectedContact = $scope.contacts[index].firstName + "   " + $scope.contacts[index].lastName;
        $scope.selectedContactIndex = index;

        $scope.getContactHobbies();
        $scope.showUserInfo();
    };

    $scope.delContact = function(index){
        var str = getBirthDateStr(index);

        $http.post('/contact/delete',{"firstName":$scope.contacts[index].firstName, "lastName":$scope.contacts[index].lastName, "birthDate":str }).
            success(function (data, status, headers, config) {
                $scope.refresh();
            }).
            error(function (data, status, headers, config) {
                alert("Exception details: " + JSON.stringify({data: data}));
            });
    };

// User -----------------------------------------------------------------------------------------------------------
// Hobby -----------------------------------------------------------------------------------------------------------

    $scope.getAllHobbies = function(){
        $http.get('/contact/getAllHobbies').
            then(function(responce){
                $scope.hobbies = responce.data;
            });
    };

    $scope.getAllHobbies();


    $scope.addNewHobby = function() {
        console.log("addNewHobby");
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/contact/addNewHobby', {"title": $scope.hobbyTitle, "description": $scope.hobbyDescription,
                "firstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                "lastName": $scope.contacts[$scope.selectedContactIndex].lastName, "birthDate": str }
            ).
                success(function (data, status, headers, config) {
                    $scope.hobbyTitle = '';
                    $scope.hobbyDescription = '';

                    $scope.getAllHobbies();
                    $scope.getContactHobbies();
                }).
                error(function (data, status, headers, config) {
                    alert("Exception details: " + JSON.stringify({data: data}));
                });
        }
    };

    $scope.addExistHobby = function(index) {
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/contact/addExistHobby', {
                "firstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                "lastName": $scope.contacts[$scope.selectedContactIndex].lastName,
                "birthDate": str, "title": $scope.hobbies[index].title, "description": $scope.hobbies[index].description }
            ).
                success(function (data, status, headers, config) {
                    $scope.getContactHobbies();
                }).
                error(function (data, status, headers, config) {
                    alert("Exception details: " + JSON.stringify({data: data}));
                });
        }
    };

    $scope.getContactHobbies = function(){
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/contact/getContactHobbies', {
                "firstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                "lastName": $scope.contacts[$scope.selectedContactIndex].lastName, "birthDate": str }
            ).
                then(function(responce){
                    $scope.contactHobbies = responce.data;
                });
        }
    };

    $scope.getAllContactsWidthHobby = function(index){
        $http.post('/contact/getAllContactsWidthHobby', {"title": $scope.hobbies[index].title, "description": $scope.hobbies[index].description }
        ).
            then(function (responce) {
                $scope.contactsWidthHobby = responce.data;
                console.log($scope.contactsWidthHobby);
                $scope.showContactsWidthHobby();
            });
    };

// Hobby -----------------------------------------------------------------------------------------------------------
// Friend ----------------------------------------------------------------------------------------------------------

    $scope.getContactFriends = function(){
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/contact/getContactFriends', {
                    "firstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                    "lastName": $scope.contacts[$scope.selectedContactIndex].lastName, "birthDate": str }
            ).
                then(function(responce){
                    $scope.contactFriends = responce.data;
                });
        }
    };

    $scope.addFriendship = function(index){
        if ($scope.selectedContactIndex !== null) {
            var str1 = getBirthDateStr($scope.selectedContactIndex);
            var str2 = getBirthDateStr(index);

            $http.post('/contact/addFriendship', {
                    "c1FirstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                    "c1LastName": $scope.contacts[$scope.selectedContactIndex].lastName, "c1BirthDate": str1,

                    "c2FirstName": $scope.contacts[index].firstName, "c2LastName": $scope.contacts[index].lastName, "c2BirthDate": str2 }
            ).
                success(function (data, status, headers, config) {
                    $scope.getContactFriends();
                    //$scope.contactFriends.add({"firstName": $scope.contacts[index].firstName, "lastName": $scope.contacts[index].lastName, "birthDate": str2});
                }).
                error(function (data, status, headers, config) {
                    alert("Exception details: " + JSON.stringify({data: data}));
                });
        }
    };

// Friend ----------------------------------------------------------------------------------------------------------
// Place ----------------------------------------------------------------------------------------------------------

    $scope.getAllPlaces = function(){
        $http.get('/place/getAll').
            then(function(responce){
                $scope.places = responce.data;
            });
    };

    $scope.getAllPlaces();


    $scope.addNewPlace = function() {
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/place/addNew', {"firstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                    "lastName": $scope.contacts[$scope.selectedContactIndex].lastName, "birthDate": str,
                    "title": $scope.placeTitle, "description": $scope.placeDescription, "latitude": $scope.latitude,  "longitude": $scope.longitude }
            ).
                success(function (data, status, headers, config) {
                    $scope.placeTitle = '';
                    $scope.placeDescription = '';
                    $scope.latitude = '';
                    $scope.longitude = '';

                    $scope.getAllPlaces();
                    $scope.getContactPlaces();
                }).
                error(function (data, status, headers, config) {
                    alert("Exception details: " + JSON.stringify({data: data}));
                });
        }
    };

    $scope.addExistPlace = function(index) {
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/place/addExist', {
                    "firstName": $scope.contacts[$scope.selectedContactIndex].firstName, "lastName": $scope.contacts[$scope.selectedContactIndex].lastName, "birthDate": str,
                    "title": $scope.places[index].title, "description": $scope.places[index].description, "latitude": $scope.places[index].latitude,  "longitude": $scope.places[index].longitude }
            ).
                success(function (data, status, headers, config) {
                    $scope.getContactPlaces();
                }).
                error(function (data, status, headers, config) {
                    alert("Exception details: " + JSON.stringify({data: data}));
                });
        }
    };

    $scope.getContactPlaces = function(){
        if ($scope.selectedContactIndex !== null) {
            var str = getBirthDateStr($scope.selectedContactIndex);

            $http.post('/place/getContact', {
                    "firstName": $scope.contacts[$scope.selectedContactIndex].firstName,
                    "lastName": $scope.contacts[$scope.selectedContactIndex].lastName, "birthDate": str }
            ).
                then(function(responce){
                    $scope.contactPlaces = responce.data;

                });
        }
    };

    $scope.getAllContactsForPlace = function(index){
        $http.post('/place/getAllContactsFor', { "title": $scope.places[index].title, "description": $scope.places[index].description, "latitude": $scope.places[index].latitude,  "longitude": $scope.places[index].longitude }
        ).
            then(function (responce) {
                $scope.contactsForPlace = responce.data;
                $scope.showContactsForPlace();
            });
    };

// Place ----------------------------------------------------------------------------------------------------------
// Conversation ----------------------------------------------------------------------------------------------------------

    $scope.getConversation = function(index){
        $scope.conversationContactIndex = index;
        var str1 = getBirthDateStr($scope.selectedContactIndex);
        var str2 = getBirthDateStr(index);

        $http.post('/message/get', { "c1FirstName": $scope.contacts[$scope.selectedContactIndex].firstName, "c1LastName": $scope.contacts[$scope.selectedContactIndex].lastName, "c1BirthDate": str1,
                "c2FirstName": $scope.contacts[index].firstName, "c2LastName": $scope.contacts[index].lastName, "c2BirthDate": str2 }
        ).
            then(function (responce) {
                $scope.conversation = responce.data;
                $scope.showContactConversation();
            });
    };

    $scope.addMessage = function(){
        var str1 = getBirthDateStr($scope.selectedContactIndex);
        var str2 = getBirthDateStr($scope.conversationContactIndex);
        $http.post('/message/store', { "c1FirstName": $scope.contacts[$scope.selectedContactIndex].firstName, "c1LastName": $scope.contacts[$scope.selectedContactIndex].lastName, "c1BirthDate": str1,
                "c2FirstName": $scope.contacts[$scope.conversationContactIndex].firstName, "c2LastName": $scope.contacts[$scope.conversationContactIndex].lastName, "c2BirthDate": str2,
                "content":$scope.message }
        ).
            success(function (data, status, headers, config) {
                $scope.message = '';
                $scope.getConversation($scope.conversationContactIndex);
            }).
            error(function (data, status, headers, config) {
                alert("Exception details: " + JSON.stringify({data: data}));
            });
    };

// Conversation ----------------------------------------------------------------------------------------------------------

}]);