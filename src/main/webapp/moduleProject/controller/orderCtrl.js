/**
 * 
 */
angular.module('project').controller('orderCtrl', ['$scope', 'orderService','connectionService', '$location', '$uibModal', function($scope, orderService,connectionService,$location,$uibModal) {
	if(!connectionService.isAuth()) $location.path('/sign');
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
	
	$scope.openModalDetail= function(products){
		var modalInstance = $uibModal.open({
			templateUrl: './html/detailProducts.html',
			controller : function($scope){
	        	$scope.close = function() {
	                // Appel à la fonction d'annulation.
	        		modalInstance.dismiss();
	            };
				$scope.products = products;
			}
	    });
		modalInstance.result.then(function(){
		}, function(){
		})
	}
}]);