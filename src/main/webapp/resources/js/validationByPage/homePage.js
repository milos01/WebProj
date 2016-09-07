var myApp = angular.module('qfcApp', []);


myApp.controller('MyCtrl', function($scope,$http) {
	
	
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
myApp.directive('nop', function($compile,$http){
	
	
	return {
		restrict: 'AE',
		scope: {
			friendss: '=',
			idx: '='
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
                	var el2 = angular.element('<button class="btn btn-info btn-xs" style="margin-top:10px;margin-right:5px">Update</button>');
                	 $compile(el)(scope);
                	 $compile(el2)(scope);
                     elm.children().append(el2);
                     elm.children().append(el);
//                     angular.element(document.getElementById('deletea')).append($compile(el)(scope));
                     
                	}
            	
            	scope.removeTask = function(idscope){
//            		scope.friendss.splice(idscope,1);
            		var br = 1;
            		scope.friendss.forEach(function(entry) {
            			console.log(idscope +"aa"+entry.id);
            		    if(idscope == entry.id){
            		    	var index = scope.friendss.indexOf(entry);
            		    	scope.friendss.splice(index,1);
            		    }
            		    br++;
            		});
            		
            	};
            });
        	
        }
    }

});
myApp.controller('addResController', function($scope) {
	$scope.submitAddRes = function() {
		alert('radi');
     };
});