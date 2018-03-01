angular.module("app").controller("ElevatorController", function($scope, $interval, elevatorService) {


	$scope.editMode = false;
	$scope.isBtnDisbled = true;
	$scope.passengerFloor = [1,2,3,4,5,6];
	$scope.emsResponse;
	$scope.theTime;
	$scope.elevatorId;
	$scope.currentFloor;

	$interval(function () {
	      //$scope.theTime = new Date().toLocaleTimeString();
		   elevatorService.loadEmsLogs().then(function(response) {
				//$scope.theTime = '';
				$scope.theTime = response;
	       });
	}, 1000);

	$scope.edit = function(id) {
		$scope.editMode = true;
		elevatorService.get(id).then(function(value) {
			$scope.newcontact = value;
		});
	}

	$scope.moveUp = function(id) {
		elevatorService.moveUp(id).then(function(response) {
			$scope.emsResponse = response.responseTxt;
			$scope.elevatorId = response.elevatorId;
			$scope.currentFloor = response.currentFloor;
			$scope.isBtnDisbled=false;
		});

	}
	
	$scope.moveDown = function(id) {
		elevatorService.moveDown(id).then(function(response) {
			$scope.emsResponse = response.responseTxt;
			$scope.elevatorId = response.elevatorId;
			$scope.currentFloor = response.currentFloor;
			$scope.isBtnDisbled=false;
		});
	}
	
	$scope.pressButton = function(destFloor, currentFloor, elevatorId) {
		elevatorService.pressButton(destFloor, currentFloor, elevatorId).then(function(response) {
			$scope.emsResponse = response.responseTxt;
			$scope.elevatorId = response.elevatorId;
			$scope.currentFloor = response.currentFloor;
			$scope.isBtnDisbled=false;
		});
	}
})