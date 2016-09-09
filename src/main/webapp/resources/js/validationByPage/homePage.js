
var myApp = angular.module('qfcApp', ['ui.bootstrap']);


myApp.controller('MyCtrl', function($scope,$http, $uibModal) {
	
	
	return $http({
        method: 'GET',
        url: 'getuser',
        headers: {
            "Content-Type": "application/json"
        },
        data: {}
    }).then(function(res){
        console.log(res.data.id);
        if(res.data.role.id == 1){
        	$("#side-menu").append("<li><a data-toggle='modal' data-target='#registerRestaurantModal'><i class='fa fa-plus'></i><span class='nav-label'>Add restaurant</span></a></li>");
        	
        }
//        $scope.friends = [
//		                    {name:'John', age:25, gender:'boy'},
//		                    {name:'Jessie', age:30, gender:'girl'},
//		                    {name:'Johanna', age:28, gender:'girl'},
//		                    {name:'Joy', age:15, gender:'girl'},
//		                    {name:'Mary', age:28, gender:'girl'},
//		                    {name:'Peter', age:95, gender:'boy'},
//		                    {name:'Sebastian', age:50, gender:'boy'},
//		                    {name:'Erika', age:27, gender:'girl'},
//		                    {name:'Patrick', age:40, gender:'boy'},
//		                    {name:'Samantha', age:60, gender:'girl'}
//		                  ];
    	return $http({
	      method: 'GET',
	      url: 'getusersrestaurant',
	      headers: {
	          "Content-Type": "application/json"
	      },
	      data: {}
	  }).then(function(res){
		  console.log(res);
		  $scope.friends = res.data;
		  
		  
	  });
      
    	
    });
	
	
	
});
myApp.directive('nop', function($compile,$http,$uibModal){
	
	
	return {
		restrict: 'AE',
		scope: {
			friendss: '=',
			idx: '=',
			fs: '='
		},
        link: function(scope, elm, attr){ 
        	console.log("asd");
        	return $http({
                method: 'GET',
                url: 'getuser',
                headers: {
                    "Content-Type": "application/json"
                },
                data: {}
            }).then(function(res){
            	if(res.data.role.id == 1){
            		
                	var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="removeTask('+ scope.idx +')">Delete</button>');
                	var el2 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="open('+scope.idx+')">Update</button>');
                	var el3 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="openEvent('+scope.idx+')">Add event</button>');
                	 $compile(el)(scope);
                	 $compile(el2)(scope);
                	 $compile(el3)(scope);
                	 elm.children().append(el3);
                     elm.children().append(el2);
                     elm.children().append(el);
                     console.log(scope.fs);
//                     angular.element(document.getElementById('deletea')).append($compile(el)(scope));
                     
                	}
            	
            	scope.removeTask = function(idscope){
//            		scope.friendss.splice(idscope,1);
            		
            		scope.friendss.forEach(function(entry) {
            			console.log(idscope +"aa"+entry.id);
            		    if(idscope == entry.id){
            		    	var index = scope.friendss.indexOf(entry);
            		    	scope.friendss.splice(index,1);
            		    }
            		   
            		});
            		
            	};
            	
            	scope.open = function (fs) {
            		scope.friendss.forEach(function(entry) {
            		    if(entry.id == fs){
            		    	
            		    	var modalInstance = $uibModal.open({
                              controller: "ModalInstanceCtrl",
                              templateUrl: 'myModalContent.html',
                              resolve: {
                                    customer: function()
                                    {
                                    	console.log(entry);
                                        return entry;
                                    }
                                }
                            });
            		    }
            		});
            		
            		
            		console.log(fs);
                    

                };
                scope.openEvent = function (fs) {
            		scope.friendss.forEach(function(entry) {
            		    if(entry.id == fs){
            		    	
            		    	var modalInstance = $uibModal.open({
                              controller: "ModalInstanceCtrl2",
                              templateUrl: 'addEventContent.html',
                              resolve: {
                                    customer: function()
                                    {
                                    	console.log(entry);
                                        return entry;
                                    }
                                }
                            });
            		    }
            		});
                };
            });
        	
        }
    }

});
myApp.controller('addResController', function($scope, $http) {
	$scope.submitAddRes = function() {
		return $http({
            method: 'POST',
            url: 'addRestaurant',
            headers: {
            	'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data:{address: $scope.resAddress, city:$scope.resCity,email:$scope.resEmail,name:$scope.resName,phone:$scope.resPhone,pib:$scope.resPib,site:$scope.resSite,ziroRacun:$scope.resAcc,picture:$scope.resPicture}
        }).then(function(res){
        	$scope.friends.push(res.data);
//        	console.log(res.data);
        	console.log($scope.friends);
        });
     };
});

myApp.controller('ModalInstanceCtrl', function ($scope, customer)
		{
		$scope.resAddressU = customer.address;
		$scope.resNameU = customer.name;
		$scope.resAccU = customer.ziroRacun;
		$scope.resEmailU = customer.email;
		$scope.resSiteU = customer.site;
		$scope.resPibU = customer.pib;
		$scope.resPhoneU = customer.phone;
		$scope.resCityU = customer.city;
		$scope.resPictureU = customer.picture;
		$scope.resId = customer;
		$scope.customer = customer;

});


myApp.controller('ModalInstanceCtrl2', function ($scope, customer){
		$scope.restaurantObj = customer;
		$scope.customer = customer;
});

myApp.controller('updateResController', function($scope, $http){
	$scope.submitUpdateRes = function(){
//		return $http({
//            method: 'POST',
////            url: 'updateRestaurant',
//            headers: {
//            	'Accept': 'application/json',
//                'Content-Type': 'application/json' 
//            },
//            data:{address: $scope.resAddress, city:$scope.resCity,email:$scope.resEmail,name:$scope.resName,phone:$scope.resPhone,pib:$scope.resPib,site:$scope.resSite,ziroRacun:$scope.resAcc,picture:$scope.resPicture}
//        }).then(function(res){
//        	$scope.friends.push(res.data);
////        	console.log(res.data);
//        	console.log($scope.friends);
//        });
		$scope.resId.address = $scope.resAddressU;
		$scope.resId.name = $scope.resNameU;
		$scope.resId.ziroRacun = $scope.resAccU;
		$scope.resId.email = $scope.resEmailU;
		$scope.resId.site = $scope.resSiteU;
		$scope.resId.pib = $scope.resPibU;
		$scope.resId.phone = $scope.resPhoneU;
		$scope.resId.city = $scope.resCityU;
		$scope.resId.picture = $scope.resPictureU;
		    	
	}
	
});

myApp.controller('addEventController', function($scope){
	$scope.submitAddEvent = function(){
//		http request for adding new event for this restaurant
//		$scope.restaurantObj.events.push('[{"description":"aaaa"}]');
		console.log($scope.restaurantObj);
	}
});
