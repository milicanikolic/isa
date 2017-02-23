'use strict';

app.controller('sviGostiController', function ($scope, $http,sviGostiService,$location) {
	sviGostiService.getAll(function(data) {
    			$scope.sviGosti = data;
    			console.info(data);
    			$location.path('/login');
    		});
});