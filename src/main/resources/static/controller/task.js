var module = angular.module("task", ['angularInlineEdit', 'ngMessages']);

function taskController ($scope, taskSrv) {
    $scope.updateTaskName = function(newValue, task) {
        task.name = newValue;
        taskSrv.edit(task);
    };
    $scope.updateTaskDescription = function(newValue, task) {
        task.description = newValue;
        taskSrv.edit(task);
    };
    $scope.submit = function () {
        if(!$scope.name || !$scope.description) {
            return;
        }
        taskSrv.create($scope.name, $scope.description).then(function (result) {
                $scope.tasks[$scope.tasks.length] = result.data;
                $scope.name = null;
                $scope.description = null;
            }
        )
    };
    $scope.delete = function (taskId) {
        taskSrv.delete(taskId).then(function (result) {
            $scope.tasks = result.data;
        });
    };
    $scope.completed = function(index) {
        taskSrv.completed($scope.tasks[index]).then(function (result) {
                $scope.tasks[index] = result.data;
            }
        );
    };
    taskSrv.loadTasks().then(function (result) {
        $scope.tasks = result.data;
    });

}

module.service('taskSrv', function($http) {
    this.create = function (name, description) {
        return $http.post("/create", {'name' : name, 'description' : description});
    };
    this.edit = function (task) {
        return $http.put("/edit", {'id' : task.id, 'name' : task.name, 'description' : task.description});
    };
    this.completed = function (task) {
        return $http.get("/completed/" + task.id);
    };
    this.delete = function (id) {
        return $http.delete("/delete/" + id);
    }
    this.loadTasks = function () {
        return $http.get("/tasks");
    };
});

var controller = module.controller("taskController", taskController);