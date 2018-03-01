angular.module("app").service("elevatorService", function($http, $q) {


	var endPoint = "http://localhost:8080/elevatorms-1.0/";
	
	this.loadEmsLogs = function() {
		var defer;
		return defer = $q.defer(), $http.get(endPoint + 'ems/getEMSLogs/').then(function(response) {
				return response.data, defer.resolve(response.data);
			}), defer.promise;
	}

	this.moveUp = function(id) {
		var defer;
		return defer = $q.defer(), $http.put(endPoint + 'ems/moveUp/'+ id)
				.success(function(data) {
					
					defer.resolve(data);
				}).error(function(data) {
			}), defer.promise;
	}
	
	this.moveDown = function(id) {
		var defer;
		return defer = $q.defer(), $http.put(endPoint + 'ems/moveDown/'+ id)
				.success(function(data) {
					
					defer.resolve(data);
				}).error(function(data) {
			}), defer.promise;
	}
	
	this.pressButton = function(destFloor, currentFloor, elevatorId) {
		var defer;
		return defer = $q.defer(), $http.put(endPoint + 'ems/pressButton/'+ elevatorId+'/'+destFloor+'/'+currentFloor)
				.success(function(data) {
					
					defer.resolve(data);
				}).error(function(data) {
			}), defer.promise;
	}
});