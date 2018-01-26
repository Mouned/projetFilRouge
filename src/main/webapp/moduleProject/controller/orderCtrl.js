/**
 * 
 */
angular.module('project').controller('orderCtrl', ['$scope', 'orderService', function($scope, orderService) {
	orderService.getOrders().then(function(response) {
		$scope.orderList = response;
	})
}]);