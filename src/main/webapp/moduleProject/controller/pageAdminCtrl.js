/**
 * Controller for page admin
 */
angular.module('project').controller('pageAdminCtrl', ['connectionService', '$scope', '$uibModal', 'searchService', 'productsService', function(connectionService, $scope, $uibModal, searchService, productsService){
	$scope.liste = [];
	/**
	 * Show all games when loading the page
	 */	
	searchService.getAll().then(function(response) {
		searchService.getAll().then(function(response) {
			$scope.liste = response;
		});
	});
	
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
				console.log($scope.liste);
			});
	    }, function () {
	    });
	};
	
	//MODAL EDIT
	$scope.openModalEdit = function(jeuToEdit){
		
		// Controller + Template de la modale
		var modalInstance = $uibModal.open({
			templateUrl: './html/editProduct.html',
			resolve : {
				item : jeuToEdit
			},
			controller : function($scope, item, productsService){
	        	$scope.addGame = function(jeu, file){
	        		$scope.$close({jeu : angular.copy($scope.jeu), file : $scope.file});
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
			productsService.addGame(response.jeu,response.file).then(function(data){
				$scope.liste = data;
				console.log($scope.liste);
			});
	    }, function () {
	    });
	};
	
	//MODAL DETAIL
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
				$scope.jeu = item;
			}
	    });

//	    modalInstance.result.then(function (response) {
//			productsService.addGame(response.jeu,response.file).then(function(data){
//				$scope.liste = data;
//				console.log($scope.liste);
//			});
//	    }, function () {
//	    });
	}
	
	/**
	 * To know if the user is admin
	 */
	$scope.isAdmin = function(){
		return connectionService.isAdmin();
	};
}]);