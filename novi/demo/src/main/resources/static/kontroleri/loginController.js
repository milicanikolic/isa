app.controller('loginController', function ($scope,$rootScope,$window, $http,gostService) {
	//alert("ssssss");
	$scope.login = function(gost){
//		alert("ssssss");
        //console.log(gost);
	var ulogovani= gostService.login(gost)
	.success(function(data){
		var logovani=data;

		$rootScope.korisnik=data;
		if(logovani!=null){
			if(logovani.vrstaKorisnika=="GOST"){
				$window.location.href = '#/profilKorisnika';
			}if(logovani.vrstaKorisnika=="KONOBAR"){
				aletr("konobar");
			}
			if(logovani.vrstaKorisnika=="ADMIN"){
				$window.location.href = '#/admin';
			}
		}else{
			alert("pogresno logovanje");
		}
	})
	 .error(function (error) {
         $scope.status = 'Uneti podaci nisu tacni: ' + error.message;
         console.log("usao u error");
     });
	
    };
});

