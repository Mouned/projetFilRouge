angular.module('project').service('basketService', ['$http', function($http) {
	
	this.createOrder = function(id, listOfGame){
		var priceTotal = 0.0;
		var order = {};
		var today = new Date();
		var numberOrder = undefined;
		var dateOrder = undefined;
		var listProducts = [];
		for(var key in listOfGame){
			priceTotal+=listOfGame[key].price;
			listProducts.push(listOfGame[key].id);
		}
		var month = today.getMonth()+1;
		if(month < 10){
			month='0'+month;
		}
		console.log(month);
		dateOrder = today.getFullYear() + '-' + month + '-' + today.getDate();
		numberOrder = Date.now() +'-'+ id;
		order = {
				id:null,
				orderDate: dateOrder,
				orderNumber : numberOrder,
				totalPrice : priceTotal 
		};
		return $http.post('/api/orders/create',order,{params : {user : id, 'products[]':listProducts}})
					.then(function(response){
						
		});
	};
}]);