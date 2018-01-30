angular.module('project').controller('userCtrl', ['orderService', 'searchUserService', '$scope', '$uibModal','connectionService', '$location', function(orderService, searchUserService, $scope, $uibModal,connectionService,$location){
	if(!connectionService.isAuth()) $location.path('/sign');
	connectionService.getUserDetails().then(function(userDetails){

		orderService.getOrderUser(connectionService.getIdUser()).then(function(response){
			$scope.orders = response;
		});
			
		searchUserService.getUserById(connectionService.getIdUser()).then(function(response){
				$scope.user = response;
		});
		
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