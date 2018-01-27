/**
 * Controller for menu
 */
angular.module('project').controller('menuCtrl', ['connectionService', '$scope', 'searchService', '$location', '$cookies','$cookieStore', function(connectionService, $scope, searchService, $location, $cookies,$cookieStore){
	
//	menuService.getUser().then(function(data){
//		$scope.user = data;
//	});
	
////////////////////////////////////////////////////Cookie to store the session user////////////////////////	
	var userId = $cookieStore.get('User');
	var superUser = $cookieStore.get('Superuser');
	
	if(userId != undefined){
		if(superUser != undefined){
			connectionService.setIsAuth(true);
			connectionService.setIsAdmin(superUser);
		}
	}
	
	$scope.welcomeUser = function(){
		return $cookieStore.get('Login');
	}
	
	$scope.isAuthenticate = function(){
		return connectionService.isAuth();
	}
	
	$scope.isAdmin = function(){
		return connectionService.isAdmin();
	}
	
	$scope.deconnect = function(){
		connectionService.deconnectUser().then(function(){});
	}
	
	$scope.quickResearch = function(gameInfo) {
		console.log($location.path());
		searchService.quickSearch(gameInfo).then(function(response) {
			searchService.setList(response);
			$location.path('/research');
		});
	}
	
	//SUPPRESSION COOKIE "PANIER" LORS DU REFRESH
	window.onbeforeunload = function(){
		console.log('coucou...');
		console.log($cookies.get('panier'));
		$cookies.remove('panier');
	}
	
	
	$scope.panier = 0;
	
	$scope.$watch(function(){
		if($cookies.get('panier')){
			var monPanier = $cookies.get('panier').substring(1).split(','); //supression de la premiere virgule pour TABLEAU PROPRE
			$scope.panier = monPanier.length;
			//console.log('****************************************************************', $scope.panier);
		}
	})
}]);