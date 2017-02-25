app.controller('profilKorisnikaController', function ($scope,$window,$rootScope,gostService, $http) {
	$scope.korisnik=$rootScope.korisnik;
	//console.info($rootScope.korisnik);
	
	$scope.uzmiUlogovanog=function(){
		gostService.uzmiUlogovanog()
		.success(function(data){
			$scope.ulogovani=data;
		})
	}
	
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
	$scope.sviRestorani=function(){
		console.log("uzmi sve restorane");
		gostService.sviRestorani()
		.success(function(data){
			$scope.restorani=data;

		})
	}
	
	$scope.prikaziRestoran=function(idRestoran){
		console.log("prikazi Restoran"+idRestoran);
		gostService.uzmiRestoran(idRestoran)
		.success(function(data){
			$scope.izabraniRestoran=data;
			console.log($scope.izabraniRestoran);
			$window.location.href = '#/profilRestorana';

		})
	}
	
});