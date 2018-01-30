/**
 * Controleur pour la page home
 */
angular.module('project').controller('homeCtrl', ['$scope','$http','searchService', '$uibModal', 'connectionService', '$location', '$cookieStore','productsService', function ($scope, $http,searchService, $uibModal, connectionService, $location, $cookieStore,productsService) {
	console.log('controller for home');
	/**
	 * Showing the games on home page
	 */
	searchService.getAll().then(function(data){
		$scope.list = data;
	});
	
	
	$scope.openModalDetail = function(jeuToEdit){
		var modalInstance = $uibModal.open({
			templateUrl: './html/detailProduct.html',
			resolve : {
				item : jeuToEdit
			},
			controller : function($scope, item, $cookies){
				$scope.addGame = function(jeu, file){
	        		$scope.$close({jeu : angular.copy($scope.jeu), file : $scope.file});
	        	};
	        	$scope.cancel = function() {
	                // Appel à la fonction d'annulation.
	        		modalInstance.dismiss();
	            };
	            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ACHAT => COOKIE
	            $scope.addToBasket = function(jeu){
	            	var basket = $cookieStore.get('Basket');
	            	if(basket == undefined){
	            		var basketUser = {};
	            		var listOfGame = [];
	            		listOfGame.push(jeu.id);
	            		basketUser = {
	            				id : connectionService.getIdUser(),
	            				content : listOfGame
	            		};
	            		$cookieStore.put('Basket',basketUser);
	            		productsService.setLengthList(listOfGame.length);
	            	}else{
	            		var store = [$cookieStore.get('Basket').content];
	            		store.push(jeu.id);
	            		$cookieStore.put('Basket', {id : connectionService.getIdUser(), content : store});
	            		productsService.setLengthList(store.length);
	            	}
	            	modalInstance.dismiss();
	            }
				$scope.jeu = item;
			}
	    });
		
		modalInstance.result.then(function(){
		}, function(){
		})
	}
	
	
	
	
	
}]);