
var myApp = angular.module('qfcApp', ['ui.bootstrap', 'ngRoute']);

myApp.config(
	    function($routeProvider) {
	        $routeProvider.
	          
	            when('/springmvc/home/restaurant', 
	                {   
	                    controller:'resView', 
	                    templateUrl: 'restaurant.jsp'
	                }
	            ).
	            otherwise({redirectTo:'/'});
	    }
);
myApp.controller('resView', function($scope, $routeParams){
	var param1 = $routeParams.res_id;
	console.log(param1);
});
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
                	var el4 = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="restaurantPage('+scope.idx+')">View</button>');
                	$compile(el)(scope);
                	 $compile(el2)(scope);
                	 $compile(el3)(scope);
                	 $compile(el4)(scope);
                	 elm.children().append(el3);
                     elm.children().append(el2);
                     elm.children().append(el);
                     elm.children().append(el4);
                     
//                     angular.element(document.getElementById('deletea')).append($compile(el)(scope));
                     
                	}
            	console.log(scope.fs);
//            	scope.friendss.forEach(function(entry){
            	console.log(scope.fs.user)
            	console.log(res.data.id)
            		if(scope.fs.user != res.data.id){
            			console.log("aaaaaaaaa");
            			scope.fs.events.forEach(function(entry2){
            				var currDate = new Date();
            				var k = entry2.date;
 
            				if(k.toString().substring(0, 10) > currDate.getTime().toString().substring(0,10)){
            					
	            				var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="signEvent('+ entry2.id +')">Go!</button>');
	                			$compile(el)(scope); 
	                			var place = elm.find("#eve"+entry2.id);
	                			console.log(place);
	                			place.append(el);
            				}else{
            					var el = angular.element('<span class="" style="margin-top:10px;color:red;padding:10px" >Passed</span>');
	                			$compile(el)(scope); 
	                			var place = elm.find("#eve"+entry2.id);
	                			place.append(el);
            				}
//                			console.log(place.children());
            			});
//            			var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px">Go!</button>');
//            			$compile(el)(scope); 
//            			elm.children().append(el);
            		}
//            		console.log(entry.user);
//            	});
//            	if(res.data.id ==  ){
//            		
//            	}
            		scope.restaurantPage= function(id){
            			window.location = "restaurant/"+id;
            		}
            		
            	scope.signEvent = function(ideve){
            		alert(ideve);
            	}
            	
            	scope.removeTask = function(idscope){
//            		scope.friendss.splice(idscope,1);
            		
            		scope.friendss.forEach(function(entry) {
            		
            		    if(idscope == entry.id){
            		    	var index = scope.friendss.indexOf(entry);
            		    	scope.friendss.splice(index,1);
            		    	$http({
                    	        method: 'POST',
                    	        url: '/springmvc/removeRestaurant',
                    	        
                    	        headers: {
                    	        	'Accept': 'application/json',
                    	            'Content-Type': 'application/json' 
                    	        },
                    	        data: {resid:idscope},
                    	    }).then(function(res){
                    	    	
//                    	    	$scope.friends.push(res.data)
                    	    });
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
                                 
                                        return entry;
                                    }
                                }
                            });
            		    }
            		});
            		
            		
         
                    

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
		$scope.restaurantId = customer.id;
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

myApp.controller('addEventController', function($scope, $http){
	$scope.submitAddEvent = function(){
		return $http({
            method: 'POST',
            url: 'addEvent',
            headers: {
            	'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data:{description: $scope.eventDescription, exdate:$scope.eventDate,picture:$scope.eventPicture,resid:$scope.restaurantId}
        }).then(function(res){
        	$scope.restaurantObj.events.push(res.data);
        
        });
//		http request for adding new event for this restaurantF
//		$scope.restaurantObj.events.push('[{"description":"aaaa"}]');
//		console.log($scope.restaurantObj);
	}
});

myApp.directive('eveDir', function(){
	return {
	    template: '<div>{{flavor}}</div>',
	    link: function(scope, element, attrs) {
	    	console.log("aaaaaaaaaaaa");
	    }
	  };
});

