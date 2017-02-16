'use strict';

app.controller('sviGostiController', function ($scope, $http,sviGostiService) {
	sviGostiService.getAll(function(data) {
    			$scope.sviGosti = data;
    		});
});