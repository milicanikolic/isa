app.controller('registracijaController', function ($scope, $http,$window,gostService) {


	$scope.registruj = function(gost){
		
        var reg=gostService.registruj(gost)
        .success(function(data){
        	var registrovan=data;
        	console.log(registrovan);
        	$window.location.href = '#/login';
        });
        
	}
	
	
});
