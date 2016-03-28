//var myApp = angular.module('app',[]);
//myApp.controller('LoginController', ['$scope','$http', function($scope, $http) {
//	$scope.loginSubmit_model = function() {
//		var username = $scope.username_model;
//		var password = $scope.password_model;
//		
//		var check;
//		if(!username){
//			
//			$("#username").css("border", "1px solid red");
//			alert("aaa");
//		}else{
//			$("#username").css("border", "1px solid #000");
//		}
//		if(!password){
//			$("#password").css("border", "1px solid red");
//		}else{
//			$("#password").css("border", "1px solid #000");
//		}
//	}
//	
//}]);
if ($("#errorAlert").is(":visible")) { 
	$("#errorAlert").delay(1800).fadeOut(500);
}