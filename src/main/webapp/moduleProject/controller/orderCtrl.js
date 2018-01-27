/**
 * 
 */
angular.module('project').controller('orderCtrl', ['$scope', 'orderService','$cookieStore', function($scope, orderService,$cookieStore) {
	
	$scope.orderListAdmin = [];
	$scope.orderUser = [];
	
	orderService.getOrders().then(function(response) {
		$scope.orderListAdmin = response;
	})
	
	orderService.getOrderUser().then(function(response){
		$scope.orderUser = response;
	})
	
}]);