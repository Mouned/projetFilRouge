/**
 * 
 */
angular.module('project').controller('orderCtrl', ['$scope', 'orderService','connectionService', function($scope, orderService,connectionService) {
	
	$scope.orderListAdmin = [];
	$scope.orderUser = [];
	
	orderService.getOrders().then(function(response) {
		$scope.orderListAdmin = response;
	});
	connectionService.getUserDetails().then(function(userDetails){
		orderService.getOrderUser(userDetails.id).then(function(response){
			$scope.orderUser = response;
		});
	})
}]);