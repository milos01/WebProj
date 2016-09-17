var myApp = angular.module('qfcApp', ['ui.bootstrap', 'ngRoute']);

myApp.controller('MyCtrl', function($scope,$http, $uibModal,$location,$compile,$element) {
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
    	console.log(res.data.recensions);
    	
    	$scope.friends = res.data.recensions;
    	var ff = res.data.recensions;
    	var resData = res.data;
    	
    	
    	return $http({
            method: 'GET',
            url: '/springmvc/getuser',
            headers: {
            	
                'Content-Type': 'application/json' 
            },
            data:{}
        }).then(function(res){
        	$scope.userCred = res.data.firstName +" "+ res.data.lastName; 
        	$scope.userRole = res.data.role.roleName;
        	var isEnable = true;
        	var isE = true;
        	var el = angular.element('<form method="post" ng-submit="submitRec()"><textarea class="" style="width:300px" placeholder="your recension..." ng-model="recInput"></textarea><select ng-model="resRate" class="selectpicker"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></select><button type="submit" class="btn btn-primary" style="margin-top:-35px;">Post</button></form>');
		 	$compile(el)($scope);
		 	$element.find("#inputHolder").append(el);
        	res.data.restaurants.forEach(function(entry) {
        		console.log(entry)
        		if(res_id == entry ){
        			
        			$element.find("#inputHolder").html("<p>You are not alowed to comment</p>");
        			
        		}
        	});
        	console.log(res.data.id)
        	if(res.data.id != resData.user){
        	
        		$element.find("#dropzoneFileUpload").hide();
        	}
//        	if(isEnable == true){
//        		alert("uso");
//        		
//        	}else{
//        		$element.find("#inputHolder").html("<p></p>");
//        	}
        	ff.forEach(function(entry){
        		
        		
        		if(isNaN(entry.user)){
        			console.log(res.data.id);
	        		if(entry.user.id == res.data.id){
	        			$element.find("#inputHolder").html("<p></p>");
	        			
	        			
	        		}
        		}else{
        			if(entry.user == res.data.id){
	        			$element.find("#inputHolder").html("<p></p>");
	        			
	        			
	        		}
        		}
        	});
        	
        	
        	$scope.submitRec = function(){
        		console.log($scope.resRate)
        		$http({
        	        method: 'POST',
        	        url: '/springmvc/addRecension',
        	        headers: {
        	        	'Accept': 'application/json',
        	            'Content-Type': 'application/json' 
        	        },
        	        data:{text: $scope.recInput,ocena:$scope.resRate,uid: res.data.id, rid: res_id,datum: new Date()}
        	    }).then(function(res){
        	    	console.log(res.data)
        	    	$scope.friends.push(res.data)
        	    	$element.find("#inputHolder").html("<p></p>");
        	    });
        	}
        	
        	
        });
    	
    });
	
	
});

myApp.directive('nop', function($http,$uibModal,$compile){
	return {
		restrict: 'AE',
		scope: {
			friendss: '=',
//			idx: '=',
			fs: '='
		},
        link: function(scope, elm, attr){ 
        	console.log(scope.fs);
        	return $http({
                method: 'GET',
                url: '/springmvc/getuser',
                headers: { 
                    "Content-Type": "application/json"
                },
                data: {}
        		}).then(function(res){
        		
        		
        		if(isNaN(scope.fs.user)){
        			
        		elm.find("#userDet"+scope.fs.id).append("<div>"+scope.fs.user.firstName+" "+scope.fs.user.lastName+"</div>");
        		elm.find("#userPic"+scope.fs.id).append("<img style='margin-top:-30px' src='../resources/img/"+scope.fs.user.picture+"'>");
            	if(res.data.id == scope.fs.user.id){
            		
                	var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="removeRec('+ scope.fs.id +')">Delete</button>');
//                	var el2 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="open('+scope.idx+')">Update</button>');
//                	var el3 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="openEvent('+scope.idx+')">Add event</button>');
//                	var el4 = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="restaurantPage('+scope.idx+')">View</button>');
                	$compile(el)(scope);
//                	 $compile(el2)(scope);
//                	 $compile(el3)(scope);
//                	 $compile(el4)(scope);
                	elm.find("#deletea"+scope.fs.id).append(el);
                	
//                	 elm.children().append(el3);
//                     elm.children().append(el2);
//                     elm.children().append(el);
//                     elm.children().append(el4);
            
////                     angular.element(document.getElementById('deletea')).append($compile(el)(scope));
//                     
            	
            	}
        		}else{
        			$http({
            	        method: 'POST',
            	        url: '/springmvc/getuser/'+scope.fs.user,
            	        headers: {
            	        	'Accept': 'application/json',
            	            'Content-Type': 'application/json' 
            	        },
            	        data:{}
            	    }).then(function(res){
            	    	console.log(res.data)
            	    	elm.find("#userDet"+scope.fs.id).append("<div>"+res.data.firstName+" "+res.data.lastName+"</div>");
            	    });
        			
        			if(res.data.id == scope.fs.user){
                		
                    	var el = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="removeRec('+ scope.fs.id +')">Delete</button>');
//                    	var el2 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="open('+scope.idx+')">Update</button>');
//                    	var el3 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px" data-toggle="modal" ng-click="openEvent('+scope.idx+')">Add event</button>');
//                    	var el4 = angular.element('<button class="btn btn-danger btn-xs" style="margin-top:10px" ng-click="restaurantPage('+scope.idx+')">View</button>');
                    	$compile(el)(scope);
//                    	 $compile(el2)(scope);
//                    	 $compile(el3)(scope);
//                    	 $compile(el4)(scope);
                    	elm.find("#deletea"+scope.fs.id).append(el);
                    	
//                    	 elm.children().append(el3);
//                         elm.children().append(el2);
//                         elm.children().append(el);
//                         elm.children().append(el4);
//                         
////                         angular.element(document.getElementById('deletea')).append($compile(el)(scope));
//                         
                	
                	}
        		}
        		scope.removeRec = function(recid){
        			scope.friendss.forEach(function(entry) {
                		
            		    if(recid == entry.id){
            		    	
            		    	var index = scope.friendss.indexOf(entry);
            		    	scope.friendss.splice(index,1);
            		    	$http({
                    	        method: 'POST',
                    	        url: '/springmvc/removeRecension',
                    	        
                    	        headers: {
                    	        	'Accept': 'application/json',
                    	            'Content-Type': 'application/json' 
                    	        },
                    	        data: {resid:recid},
                    	    }).then(function(res){
                    	    	
//                    	    	$scope.friends.push(res.data)
                    	    });
            		    }
            		   
            		});
        			
        		}
            });
        	
        }
    }
});