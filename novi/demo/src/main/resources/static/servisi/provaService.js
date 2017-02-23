var StudentService = angular.module('StudentService', [])
StudentService.factory('StudentDataOp', ['$http', function ($http) {

    var urlBase = 'http://localhost:2307/Service1.svc';
    var StudentDataOp = {};

    StudentDataOp.getStudents = function () {
        return $http.get(urlBase+'/GetStudents');
    };

    StudentDataOp.addStudent = function (stud) {
        return $http.post(urlBase + '/AddStudent', stud);
    };
    return StudentDataOp;

}]);