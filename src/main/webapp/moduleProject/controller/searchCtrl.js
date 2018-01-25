angular.module('project').controller('searchCtrl',['$scope','$http','searchService', 'connectionService', function ($scope, $http,searchService, connectionService) {
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
		console.log($scope.liste);
		searchService.setList(undefined);
	}
}]);