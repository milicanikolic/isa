app.controller('radnikController', function ($scope,$window,$rootScope,radnikService, $http) {
	$scope.radnikOpcije=1;
	
	$scope.initRadnika=function(){
		console.log("init radnika");
		radnikService.initRadnika()
		.success(function(data){
			
			$scope.ulogovan=data;
			console.log($scope.ulogovan);
		})
	}
	
	$scope.prikaziIzmenaRadnika=function(){
		$scope.radnikOpcije=0;
		$scope.menjanjeRadnika=1;
	}
	$scope.izmenaRadnika=function(radnik){
		radnikService.izmeniRadnika($scope.ulogovan.id,radnik)
		.success(function(data){
			console.log("izmenio");
		$scope.radnikOpcije=1;
		$scope.menjanjeRadnika=0;
		})
	}
	
	$scope.prikaziKalendar=function(){
		$window.location.href = '#/kalendar';
	}
	
	$scope.prikaziPonude=function(vrsta){
		if(vrsta=="KONOBAR"){
			$scope.zaKonobara=1;
		}else if(vrsta=="SANKER"){
			$scope.zaSankera=1;
		}else if(vrsta=="KUVAR_ZA_SALATE" || vrsta=="KUVAR_ZA_PECENA_JELA"||vrsta=="KUVAR_ZA_KUVANA_JELA"){
			$scope.zaKuvara=1;
		}
		$window.location.href = '#/pregledPorudzbina';
	}
});