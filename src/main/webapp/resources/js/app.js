var myApp = angular.module('App',[]);
myApp.controller('addFriendController', ['$scope','$http', function($scope, $http) {

}]);

$("#friendsSearch").keyup(function(){
	$("#friendsSearch").focus(
		    function(){
		        $(this).css('border-bottom', 'red');
	});
    var friendsSearch = $(this).val();
    var ime = "Micko Mickovic"
    if(ime.match(friendsSearch)){
    	$(this).css("border-bottom","1px solid red");
    }else{
    	$(this).css("border-bottom","none");
    }
});

if ($("#errorAlert").is(":visible")) { 
	$("#errorAlert").delay(1800).fadeOut(500);
}

$("#tokenButton").click(function(){
	$("#expiredToken").fadeOut("fast");
});