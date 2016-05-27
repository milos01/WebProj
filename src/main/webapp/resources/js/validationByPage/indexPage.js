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

myApp.directive('emailNotavailable', function($timeout, $q, $http) {
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
                    if(!res.data.success){
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

myApp.directive("passwordVerify", function() {
    return {
        require: "ngModel",
        scope: {
            passwordVerify: '='
        },
        link: function(scope, element, attrs, ctrl) {
            scope.$watch(function() {
                var combined;

                if (scope.passwordVerify || ctrl.$viewValue) {
                    combined = scope.passwordVerify + '_' + ctrl.$viewValue;
                }
                return combined;
            }, function(value) {
                if (value) {
                    ctrl.$parsers.unshift(function(viewValue) {
                        var origin = scope.passwordVerify;
                        if (origin !== viewValue) {
                            ctrl.$setValidity("passwordVerify", false);
                            return undefined;
                        } else {
                            ctrl.$setValidity("passwordVerify", true);
                            return viewValue;
                        }
                    });
                }
            });
        }
    };
});