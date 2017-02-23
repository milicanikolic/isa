app.controller('profilKorisnikaController', function ($scope,$window,$rootScope,gostService, $http) {
	//alert('profilKorisnikaController');
	$scope.korisnik=$rootScope.korisnik;
	console.info($rootScope.korisnik);
	
	$scope.izmeni=function(id){
		console.log("id je: "+id);
		gostService.uzmiGosta(id)
		.success(function(data){
			var izmenaKorisnik=data;
			$rootScope.izmenaKorisnik=izmenaKorisnik;
			console.log(izmenaKorisnik);
        	$window.location.href = '#/izmenaKorisnika';
		})
	}
});