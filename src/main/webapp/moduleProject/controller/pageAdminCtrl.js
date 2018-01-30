/**
 * Controller for page admin
 */
angular.module('project').controller('pageAdminCtrl', ['connectionService', '$scope', '$uibModal', 'searchService', 'productsService','pageUserService', '$location', function(connectionService, $scope, $uibModal, searchService, productsService,pageUserService,$location){
	if(!connectionService.isAdmin()) $location.path('/home');
	$scope.liste = [];
	$scope.member = [];
	/**
	 * Show all games when loading the page
	 */	
	searchService.getAll().then(function(response) {
		searchService.getAll().then(function(response) {
			console.log(connectionService.isAdmin());
			$scope.liste = response;
		});
	});
	
	/*
	 * Show all member
	 */
	
	pageUserService.getAll().then(function(response){
		
		$scope.member = response;
	});
	
	/*
	 * Test Value CheckBox 
	 */
	
	$scope.isChecked = function(checkboxValue){
		//console.log(checkboxValue);
		return checkboxValue;
	}
	
	$scope.deleteProduct = function(id){
		if(confirm("Etes vous sûr de vouloir supprimer ce jeu ?")){
			productsService.deleteGame(id).then(function(response){
				$scope.liste = response;
			});
		}
	}
	
	$scope.deleteUser = function(id){
		if(confirm("Etes vous sûr de vouloir supprimer cet utilisateur ?")){
			productsService.deleteUser(id).then(function(response){
				$scope.member = response;
			});
		}
	}
	
	$scope.openModal = function(){
		
		// Controller + Template de la modale
		var modalInstance = $uibModal.open({
			templateUrl: './html/formAddProduct.html',
			controller : function($scope,productsService){
	        	$scope.addGame = function(jeu, file){
	        		$scope.$close({jeu : angular.copy($scope.jeu), file : $scope.file});
	        	};
	        	$scope.pegis = [];
	        	$scope.types = ["Survie", "Stratégie", "Action", "FPS", "Course","Monde ouvert","Découverte","Aventure"];
	        	productsService.getPegis().then(function(response) {
	        		$scope.pegis = response;
	        	});
			}
	    });

	    modalInstance.result.then(function (response) {
			productsService.addGame(response.jeu,response.file).then(function(data){
				$scope.liste = data;
				//console.log($scope.liste);
			});
	    }, function () {
	    });
	};
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// MODAL EDIT
	$scope.openModalEdit = function(jeuToEdit){
		
		// Controller + Template de la modale
		var modalInstance = $uibModal.open({
			templateUrl: './html/editProduct.html',
			resolve : {
				item :  angular.copy(jeuToEdit)
			},
			controller : function($scope, item, productsService){
	        	$scope.updateGame = function(jeu, file){
	        		$scope.$close({jeu : angular.copy($scope.jeu), file : $scope.file});
	        	};
	        	$scope.cancel = function() {
	                // Appel à la fonction d'annulation.
	        		modalInstance.dismiss();
	            };
	        	$scope.jeu = angular.copy(item);
	        	$scope.pegis = [];
	        	$scope.types = ["Survie", "Stratégie", "Action", "FPS", "Course","Monde ouvert","Découverte","Aventure"];
	        	productsService.getPegis().then(function(response) {
	        		$scope.pegis = response;
	        	});
			}
	    });

	    modalInstance.result.then(function (response) {
			productsService.updateGame(response.jeu,response.file).then(function(data){
				$scope.liste = data;
				//console.log($scope.liste);
			});
	    }, function () {
	    });
	};
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////MODAL DETAIL
	$scope.openModalDetail = function(jeuToEdit){
		var modalInstance = $uibModal.open({
			templateUrl: './html/detailProduct.html',
			resolve : {
				item : jeuToEdit
			},
			controller : function($scope, item){
				$scope.addGame = function(jeu, file){
	        		$scope.$close({jeu : angular.copy($scope.jeu), file : $scope.file});
	        	};
	        	$scope.cancel = function() {
	                // Appel à la fonction d'annulation.
	        		modalInstance.dismiss();
	            };
				$scope.jeu = item;
				
			}
	    });
		modalInstance.result.then(function(){
		}, function(){
		})
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////MODAL DETAIL User
	$scope.openModalDetailUser = function(user){
		var modalInstance = $uibModal.open({
			templateUrl: './html/detailUser.html',
			resolve : {
				item : user
			},
			controller : function($scope, item){
				$scope.addGame = function(user){
	        		$scope.$close({user : angular.copy($scope.user)});
	        	};
	        	$scope.cancel = function() {
	                // Appel à la fonction d'annulation.
	        		modalInstance.dismiss();
	            };
				$scope.user = item;
				
			}
	    });
		modalInstance.result.then(function(){
		}, function(){
		})
	}
	
	/**
	 * To know if the user is admin
	 */
	$scope.isAdmin = function(){
		return connectionService.isAdmin();
	};
	
	/**
	 * To disable or enable a game
	 */
	$scope.disableOrEnable = function(id) {
		return productsService.disableOrEnableGame(id).then(function(response) {
			return searchService.getAll().then(function(data){
				return data;
    		});
		});
	};
	
}]);