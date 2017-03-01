app.controller('profilKorisnikaController', function ($scope,$window,$rootScope,gostService, $http) {
	$scope.korisnik=$rootScope.korisnik;
	//console.info($rootScope.korisnik);
	
	$scope.zahteviZaPrijateljstvo=function(){
		console.log("uzao u zahteve za prinat;wjsmd");
		gostService.zahteviZaPrijateljstvo($scope.ulogovani.id)
		.success(function(data){
			$scope.imaZahteva=1;
			$scope.zahtevi=data;
		})
		.error(function (error) {
	         $scope.imaZahteva=0;
	         $scope.zahtevi={};
	     });
	};
	
	$scope.uzmiUlogovanog=function(){
		gostService.uzmiUlogovanog()
		.success(function(data){
			$scope.ulogovani=data;
			$scope.zahteviZaPrijateljstvo();
			$scope.uzmiPrijatelje();
		})
		gostService.uzmiIstoriju($scope.ulogovani.id)
		.success(function(data){
			$scope.istorija=data;
		})
	};
	
	$scope.izmeni=function(){
		$scope.izmenaGosta=1;
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
	$scope.pretrazi=function(pretraga,id){
		gostService.pretrazi(pretraga,id)
		.success(function(data){
			$scope.rezultatPretrage=data;
			console.log("gotov");
			console.log($scope.rezultatPretrage);
		})
	}
	$scope.dodajPrijatelja=function(idPrijatelja, idUlogovanog){
		gostService.dodajPrijatelja(idPrijatelja,idUlogovanog)
		.success(function(data){
			console.log("dodao Prijatelja")
		})
	}
	$scope.prikaziZahteve=function(){
		$scope.prikaziPrijatelje=1;
	}
	
	$scope.prihvatiPrijatelja=function(idZahteva){
		gostService.prihvatiPrijatelja(idZahteva,$scope.ulogovani.id)
		.success(function(data){
			$scope.zahteviZaPrijateljstvo();
		})
	};
	
	$scope.odbijPrijatelja=function(idZahteva){
		gostService.odbijPrijatelja(idZahteva,$scope.ulogovani.id)
		.success(function(data){
			$scope.zahteviZaPrijateljstvo();
		})
	};
	
	$scope.uzmiPrijatelje=function(){
		console.log("uzmima prijatelje");
		gostService.uzmiPrijatelje($scope.ulogovani.id)
		.success(function(data){
			$scope.prijatelji=data;
			console.log($scope.prijatelji);
		})
	}
	
	
});