var myApp = angular.module('qfcApp', []);
myApp.controller('loginController', function($scope) {
    
});

myApp.directive('emailAvailable', function($timeout, $q, $http) {
  return {
    restrict: 'AE',
    require: 'ngModel',
    link: function(scope, elm, attr, model) { 
      model.$asyncValidators.usernameExists = function() { 
        return $http({
            method: 'POST',
            url: 'findUser',
            data: { userId: model.$viewValue}
        }).then(function(res){+
            $timeout(function(){
//                model.$setValidity('usernameExists', !!res.data); 
            }, 2000);
//            model.$setValidity('usernameExists', true);
            if(res.data.success){
                console.log("radi");
                model.$setValidity('usernameExists', false);
            }
        }); 
        
        
      };
    }
  } 
});