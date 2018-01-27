angular.module('project').controller('userCtrl', ['orderService', 'searchService', '$scope', '$uibModal','$cookieStore', function(orderService, searchService, $scope, $uibModal,$cookieStore){
	
	
	orderService.getOrderUser($cookieStore.get('User')).then(function(response){
		$scope.orders = response;
	});
	
	searchService.getUserById($cookieStore.get('User')).then(function(response){
		$scope.user = response;
	});
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////MODAL DETAIL User
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