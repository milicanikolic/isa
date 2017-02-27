app.controller('profilKorisnikaController', function ($scope,$window,$rootScope,gostService, $http) {
	$scope.korisnik=$rootScope.korisnik;
	//console.info($rootScope.korisnik);
	
	$scope.uzmiUlogovanog=function(){
		gostService.uzmiUlogovanog()
		.success(function(data){
			$scope.ulogovani=data;
		})
	};
	
	$scope.izmeni=function(){
		$scope.izmenaGosta=1;
		/*gostService.uzmiGosta(id)
		.success(function(data){
			var izmenaKorisnik=data;
			$rootScope.izmenaKorisnik=izmenaKorisnik;
			console.log(izmenaKorisnik);
        	$window.location.href = '#/izmenaKorisnika';
		})*/
	};
	$scope.izmeniGosta=function(izmenjenGost){
		gostService.izmeniGosta(izmenjenGost,$scope.ulogovani.id)
		.success(function(data){
			$scope.izmenaGosta=0;
		})
	};
	$scope.sviRestorani=function(){
		console.log("uzmi sve restorane");
		gostService.sviRestorani()
		.success(function(data){
			$scope.restorani=data;

		})
	};
	
	$scope.prikaziRestoran=function(idRestoran){
		console.log("prikazi Restoran"+idRestoran);
		gostService.postaviRestoran(idRestoran)
		.success(function(data){
			$scope.izabraniRestoran=data;
			console.log($scope.izabraniRestoran);
			$window.location.href = '#/profilRestorana';

		})
	};
	
	$scope.dodavanjePr=function(){
		$scope.dodavanjeP=1;
	};
	$scope.pretrazi=function(pretraga){
		gostService.pretrazi(pretraga)
		.success(function(data){
			$scope.rezultatPretrage=data;
		})
	}
	
});