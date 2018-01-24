/**
 * Directive for file upload
 */
angular.module('project').directive('fileDirective', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
           var model = $parse(attrs.fileDirective);
           var modelSetter = model.assign;
           
           element.bind('change', function(){
              scope.$apply(function(){
                 modelSetter(scope, element[0].files[0]);
              });
           });
        }
     };
 }]); 