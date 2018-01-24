angular.module('project').controller('searchCtrl',['$scope','$http','searchService', function ($scope, $http,searchService) {
    $scope.liste = [];
   // var recherche = {};
    
    $scope.pegis = ["3","7","12","16","18"];
    $scope.types = ["Survie", "Stratégie", "Action", "FPS", "Course","Monde ouvert","Découverte","Aventure"];
    
   $scope.search = function(){
	   
	   if($scope.recherche === undefined)
		   return;
	   
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
	   
    	searchService.search($scope.recherche).then(function(data){
    		$scope.liste = data;
    		$scope.recherche = {};
    	});
	 }
}]);