app.factory('radnikService',['$http', function($http){
	var radnik={};
	
	radnik.initRadnika=function(){
		return $http.get('gost/uzmiUlogovanog');
	}
	  
	radnik.izmeniRadnika=function(id,radnik){
		return $http.put('radnik/izmeniRadnika/'+id,radnik);
	}
	return radnik;
}]);