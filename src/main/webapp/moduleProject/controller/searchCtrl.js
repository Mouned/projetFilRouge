angular.module('project').controller('searchCtrl',['$scope','$http','searchService', '$uibModal', 'connectionService', '$location', '$cookies', function ($scope, $http,searchService, $uibModal, connectionService, $location, $cookies) {
    $scope.liste = [];
   // var recherche = {};
    
    $scope.pegis = ["3","7","12","16","18"];
    $scope.types = ["Survie", "Stratégie", "Action", "FPS", "Course","Monde ouvert","Découverte","Aventure"];
    console.log($location.path());
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
		console.log($scope.liste);
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
	            	//Gestion par id de jeux
	            	var idGame = jeu.id;
	            	var otherGame = [$cookies.get('panier')];
	            	otherGame.push(idGame);
	            	console.log($cookies.get('panier'));
	            	
	            	var cookiePanier = $cookies.get('panier');
	            	var jeuChoice = idGame;
	            	// Setting a cookie
	            	$cookies.put('panier', otherGame);
	            	
	            	console.log('Mes cookies', $cookies.getAll());
	            	console.log('Mon panier', $cookies.get('panier'));
	            	
	            	console.log('#######################');
	            	
	            	console.log($cookies.get('panier').length);
	            	//var notJson = angular.fromJson(jeuString);
	            	//console.log('NOT JSON ',notJson);
	            }
				$scope.jeu = item;
			}
	    });
		
		modalInstance.result.then(function(){
		}, function(){
		})
	}
	
	
	
	
	
	
	
	
}]);