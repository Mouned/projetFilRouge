angular.module('project').controller('searchCtrl',['$scope','$http','searchService', '$uibModal', 'connectionService', '$location', '$cookieStore', function ($scope, $http,searchService, $uibModal, connectionService, $location, $cookieStore) {
    $scope.liste = [];
   // var recherche = {};
    
    $scope.pegis = ["3","7","12","16","18"];
    $scope.types = ["Survie", "Stratégie", "Action", "FPS", "Course","Monde ouvert","Découverte","Aventure"];
    $scope.search = function(){
    	
 	   if($scope.recherche === undefined)
		   $scope.recherche = {titre:'',
			   				   type:'',
			   				   editeur:'',
			   				   pegi:'',
			   				   priceMin:'',
			   				   priceMax:''}; 
 	   else{
	    	if($scope.recherche.titre == undefined){
	    		$scope.recherche.titre = '';
	    	}
	    	if($scope.recherche.type == undefined){
	    		$scope.recherche.type = '';
	    	}
	    	if($scope.recherche.editeur == undefined){
	    		$scope.recherche.editeur = '';
	    	}
	    	if($scope.recherche.pegi == undefined){
	    		$scope.recherche.pegi = '';
	    	}
	    	if($scope.recherche.priceMin === undefined){
	    		$scope.recherche.priceMin = '';
	    	}
	    	if($scope.recherche.priceMax === undefined){
	    		$scope.recherche.priceMax = '';
	    	} 
 	   }
    	searchService.search($scope.recherche).then(function(data){
    		$scope.liste = data;
    		$scope.recherche = {};
    	});
	 }
   
   	$scope.isAdmin = function(){
		return connectionService.isAdmin();
	};
   	
	if(searchService.getList() != undefined){
		$scope.liste = angular.copy(searchService.getList());
		searchService.setList(undefined);
	}
	
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
	            				id : $cookieStore.get('User'),
	            				content : listOfGame
	            		};
	            		$cookieStore.put('Basket',basketUser);
	            	}else{
	            		var store = [$cookieStore.get('Basket').content];
	            		store.push(jeu.id);
	            		$cookieStore.put('Basket', {id : $cookieStore.get('User'), content : store});
	            	}            	
	            }
				$scope.jeu = item;
			}
	    });
		
		modalInstance.result.then(function(){
		}, function(){
		})
	}
	
	
	
	
	
	
	
	
}]);