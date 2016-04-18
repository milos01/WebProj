var myApp = angular.module('App',[]);
myApp.controller('updateUserController', ['$scope','$http', function($scope, $http) {
	
}]);

if ($("#errorAlert").is(":visible")) { 
	$("#errorAlert").delay(1800).fadeOut(500);
}

$("#tokenButton").click(function(){
	$("#expiredToken").fadeOut("fast");
});