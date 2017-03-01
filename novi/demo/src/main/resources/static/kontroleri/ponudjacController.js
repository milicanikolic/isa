app.controller('ponudjacController', function ($scope, $http,$window,ponudjacService) {
	
	$scope.ponudjacMeni=1;
	$scope.prikazPonuda=0;
	
	ponudjacService.initPonudjac()
	.success(function(data){
		$scope.ulogovan=data;
		console.log($scope.ulogovan);
})
	
	$scope.initPonudjac=function(){
		ponudjacService.initPonudjac()
		.success(function(data){
			$scope.ulogovan=data;
	})
	}
	$scope.prikaziMenjanje=function(){
		$scope.menjanjePonudjaca=1;
		$scope.ponudjacMeni=0;
		$scope.prikazPonuda=0;
	}
	$scope.izmeniPonudjaca=function(ulogovan){
		console.log(ulogovan);
		ponudjacService.izmenaPonudjaca($scope.ulogovan.id,ulogovan)
		.success(function(data){
			$scope.ulogovan=data;
			$scope.menjanjePonudjaca=0;
			$scope.ponudjacMeni=1;
			$scope.greskaIzmenaPonudjaca=0;
		})
		.error(function(data){
			$scope.greskaIzmenaPonudjaca=1;
		})
	};
	$scope.otvoriAktivne=function(){
		$scope.ponudjacMeni=0;
		$scope.prikazPonuda=1;
		ponudjacService.prikaziPonude($scope.ulogovan.menadzerRestorana.id)
		.success(function(data){
			$scope.aktivnePonude=data;
			console.log($scope.aktivnePonude);
		})
	}
	
	$scope.prikaziDodavanjePonudeP=function(id){
		$scope.prikazPonuda=0;
		$scope.dodavanjePonudePonudjac=1;
		$scope.ponudaNaKojuSeDodaje=id
	}
	
	$scope.dodajPonuduP=function(ponudaP){
		ponudjacService.dodajPonuduP($scope.ulogovan.id,$scope.ponudaNaKojuSeDodaje,ponudaP)
		.success(function(data){
			$scope.prikazPonuda=1;
			$scope.dodavanjePonudePonudjac=0;
			$scope.ponudaNaKojuSeDodaje={};
		})
	}
	$scope.prikazIstorije=function(){
		$scope.uzmiIstoriju();
		$window.location.href = '#/prikazIstorijePonuda';
		
		
	}
	$scope.uzmiIstoriju=function(){
		$scope.initPonudjac();
		console.log($scope.ulogovan);
		
		ponudjacService.istorijaPonuda($scope.ulogovan.id)
		.success(function(data){
			$scope.istorija=data;
			console.log($scope.istorija);
		})
	}

});