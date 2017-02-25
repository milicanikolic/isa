app.controller('adminController', function ($scope,$window,$rootScope,adminService, $http) {
	//alert("adminControler");
	

	
	$scope.uzmiAdmina=function(){
		adminService.uzmiAdmina($scope.ulogovani)
		.success(function(data){
			console.info("uzmilogovanog iz admin kont");
			$scope.admin=data;
		})
		.error(function (error) {  
	         console.log("usao u error");
	     });
	}
	$scope.uzmiRestorane=function(){
		adminService.uzmiRestorane()
		.success(function(data){
			$scope.restorani=data;
		})
		.error(function (error) {  
	         console.log("usao u error");
	     });
	};
	
	$scope.uzmiUlogovanog=function(){

		adminService.uzmiUlogovanog()
		.success(function(data){
			console.info("uzmi log")
			$scope.ulogovani=data;
			$scope.admin=$scope.uzmiAdmina();
			$scope.uzmiRestorane();
		})
		.error(function (error) {  
	         console.log("usao u error");
	     });
	}
	$scope.regMenadzerRestorana=function(menadzerRestorana, imeRestorana){
		adminService.regMenadzerRestorana(menadzerRestorana,imeRestorana)
		.success(function(data){
			console.info("reg mendz restor")
			$scope.menadzerRestorana={};
			$scope.imeRestorana={};
			
		})
	}
	
	$scope.dodajRestoran=function(restoran){
		adminService.dodajRestoran(restoran)
		.success(function(data){
			console.info("reg mendz restor");
			$scope.restoran={};
			
		})
	}
	
	$scope.dodajAdmina=function(noviAdmin){
		adminService.dodajAdmina(noviAdmin)
		.success(function(data){
			console.info("reg mendz restor");
			$scope.noviAdmin={};
			
			
		})
	}
	
	
});