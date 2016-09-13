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
    	
    	$scope.friends = res.data.recensions;
    	
    });
});

//myApp.directive('nop', function($http,$uibModal){
//	return {
//		restrict: 'AE',
////		scope: {
////			friendss: '=',
////			idx: '=',
////			fs: '='
////		},
//        link: function( elm, attr){ 
//        	
//        	return $http({
//                method: 'GET',
//                url: 'getuser',
//                headers: {
//                    "Content-Type": "application/json"
//                },
//                data: {}
//        		}).then(function(res){
////            	if(res.data.role.id == 1){
////            		
////                	var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="removeTask('+ scope.idx +')">Delete</button>');
////                	var el2 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="open('+scope.idx+')">Update</button>');
////                	var el3 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="openEvent('+scope.idx+')">Add event</button>');
////                	var el4 = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="restaurantPage('+scope.idx+')">View</button>');
////                	$compile(el)(scope);
////                	 $compile(el2)(scope);
////                	 $compile(el3)(scope);
////                	 $compile(el4)(scope);
////                	 elm.children().append(el3);
////                     elm.children().append(el2);
////                     elm.children().append(el);
////                     elm.children().append(el4);
////                     
//////                     angular.element(document.getElementById('deletea')).append($compile(el)(scope));
////                     
////                	}
////            	console.log(scope.fs);
//////            	scope.friendss.forEach(function(entry){
////            		if(scope.fs.user != res.data.id){
////            			
////            			scope.fs.events.forEach(function(entry2){
////            				var currDate = new Date();
////            				var k = entry2.date;
//// 
////            				if(k.toString().substring(0, 10) > currDate.getTime().toString().substring(0,10)){
////	            				var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="signEvent('+ entry2.id +')">Go!</button>');
////	                			$compile(el)(scope); 
////	                			var place = elm.find("#eve"+entry2.id);
////	                			console.log(place);
////	                			place.append(el);
////            				}else{
////            					var el = angular.element('<span class="" style="margin-top:10px;color:red;padding:10px" >Passed</span>');
////	                			$compile(el)(scope); 
////	                			var place = elm.find("#eve"+entry2.id);
////	                			place.append(el);
////            				}
//////                			console.log(place.children());
////            			});
//////            			var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px">Go!</button>');
//////            			$compile(el)(scope); 
//////            			elm.children().append(el);
////            		}
//////            		console.log(entry.user);
//////            	});
//////            	if(res.data.id ==  ){
//////            		
//////            	}
////            		scope.restaurantPage= function(id){
////            			window.location = "restaurant/"+id;
////            		}
////            		
////            	scope.signEvent = function(ideve){
////            		alert(ideve);
////            	}
////            	
////            	scope.removeTask = function(idscope){
//////            		scope.friendss.splice(idscope,1);
////            		
////            		scope.friendss.forEach(function(entry) {
////            		
////            		    if(idscope == entry.id){
////            		    	var index = scope.friendss.indexOf(entry);
////            		    	scope.friendss.splice(index,1);
////            		    }
////            		   
////            		});
////            		
////            	};
////            	
////            	scope.open = function (fs) {
////            		scope.friendss.forEach(function(entry) {
////            		    if(entry.id == fs){
////            		    	
////            		    	var modalInstance = $uibModal.open({
////                              controller: "ModalInstanceCtrl",
////                              templateUrl: 'myModalContent.html',
////                              resolve: {
////                                    customer: function()
////                                    {
////                                 
////                                        return entry;
////                                    }
////                                }
////                            });
////            		    }
////            		});
////            		
////            		
////         
////                    
////
////                };
////                scope.openEvent = function (fs) {
////            		scope.friendss.forEach(function(entry) {
////            		    if(entry.id == fs){
////            		    	
////            		    	var modalInstance = $uibModal.open({
////                              controller: "ModalInstanceCtrl2",
////                              templateUrl: 'addEventContent.html',
////                              resolve: {
////                                    customer: function()
////                                    {
////                     
////                                        return entry;
////                                    }
////                                }
////                            });
////            		    }
////            		});
////                };
//            });
//        	
//        }
//    }
//});