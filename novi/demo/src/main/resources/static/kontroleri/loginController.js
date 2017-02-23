app.controller('loginController', function ($scope,$rootScope,$window, $http,gostService) {
	//alert("ssssss");
	$scope.login = function(gost){
//		alert("ssssss");
        //console.log(gost);
	var ulogovani= gostService.login(gost)
	.success(function(data){
		console.log("usao u success");
		$rootScope.korisnik=data;
		console.log($rootScope.korisnik);
		$window.location.href = '#/profilKorisnika';
	})
	 .error(function (error) {
         $scope.status = 'Uneti podaci nisu tacni: ' + error.message;
         console.log("usao u error");
     });
	
    };
});

