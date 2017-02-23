var myApp = angular.module('myApp', ['provaService']);

myApp.controller('StudentController', function ($scope, StudentDataOp) {
    $scope.status;
    $scope.students;
    getStudents();

    function getStudents() {
        StudentDataOp.getStudents()
            .success(function (studs) {
                $scope.students = studs;
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
            });
    }

    $scope.addStudent = function () {

        var stud = {
            ID: 145,
            FirstName: $scope.fname,
            LastName: $scope.lname
        };
        StudentDataOp.addStudent(stud)
            .success(function () {
                $scope.status = 'Inserted Student! Refreshing Student list.';
                $scope.students.push(stud);
            }).
            error(function (error) {
                $scope.status = 'Unable to insert Student: ' + error.message;
            });
    };
});