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
            model.$setValidity('emailAvailable', false);
            if(res.data.success){
                // console.log(!res.data.success);
                model.$setValidity('emailAvailable', true);
            }else{
                // model.$setValidity('emailAvailable', true);
            }
        }); 
      };
    }
  } 
});