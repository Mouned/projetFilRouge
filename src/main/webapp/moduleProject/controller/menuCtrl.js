/**
 * Controller for menu
 */
angular.module('project').controller('menuCtrl', ['connectionService', '$scope', 'searchService', '$location','$cookies','$cookieStore', 'productsService','$route', function(connectionService, $scope, searchService, $location,$cookies,$cookieStore, productsService,$route){
	
//	menuService.getUser().then(function(data){
//		$scope.user = data;
//	});
	
////////////////////////////////////////////////////Cookie to store the session user////////////////////////	


	connectionService.getUserDetails().then(function(){
	});
	
	$scope.welcomeUser = function(){
		return connectionService.getLogin();
	}
	

////////////////////////////////////////////////////Cookie to store the basket//////////////
	$scope.$watch(function(){
		if($cookieStore.get('Basket') !== undefined){
			console.log('entrée ici');	
			$scope.panier = productsService.getLengthList();
		}else{
			$scope.panier = 0;
		}
	})
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	$scope.isAuthenticate = function(){
		return connectionService.isAuth();
	}
	
	$scope.isAdmin = function(){
		return connectionService.isAdmin();
	}
	
	$scope.deconnect = function(){
		connectionService.deconnectUser().then(function(){
			connectionService.clearUser();
			$route.reload();
		});
	}
	
	$scope.quickResearch = function(gameInfo) {
		console.log($location.path());
		searchService.quickSearch(gameInfo).then(function(response) {
			searchService.setList(response);
			$location.path('/research');
		});
	}
	
	
	
	
//	$scope.panier = 0;
//	
//	$scope.$watch(function(){
//		console.log('aaaa');
//		if($cookieStore.get('Basket')){
//			var monPanier = $cookieStore.get('Basket').substring(1).split(','); //supression de la premiere virgule pour TABLEAU PROPRE
//			$scope.panier = monPanier.length;
//			console.log('****************************************************************', $scope.panier);
//		}
//	})
}]);