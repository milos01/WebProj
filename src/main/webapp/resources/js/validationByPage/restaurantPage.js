var myApp = angular.module('qfcApp', ['ui.bootstrap', 'ngRoute']);

myApp.controller('MyCtrl', function($scope,$http, $uibModal,$location) {
	var url = $location.absUrl();
	var res_id = url.slice(-1);
	console.log(res_id);
	return $http({
        method: 'POST',
        url: res_id+'/getRestaurantObj',
        headers: {
        	'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        data:{idd: res_id}
    }).then(function(res){
    	$scope.resName = res.data.name;
    	$scope.resEmail = res.data.email;
    	$scope.resAddress = res.data.address;
    	$scope.resCity = res.data.city;
    	$scope.resPhone = res.data.phone;
//    	$scope.friends.push(res.data);
    	console.log(res.data);
    	
    });
});