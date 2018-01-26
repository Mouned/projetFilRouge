angular.module('project').controller('basketCtrl', ['connectionService', '$scope', 'searchService', '$location', '$cookies', 'productsService', function(connectionService, $scope, searchService, $location, $cookies, productsService){
	if($cookies.get('panier')){
		var monPanier = $cookies.get('panier').substring(1).split(',');
		
		var test = productsService.getOne(1);
		for(var i = 1; i<=monPanier.length; i++){
			//productsService.getOne(i);
			console.log(i);
			
		}
	}
	
}]);