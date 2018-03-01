package com.elevatorms.model;

/**
 * Domin/dto object to send information for the evevator movements
 * @author Ranjana
 *
 */
public class Response {

	private Integer elevatorId;
	private Integer currentFloor;
	private String responseTxt;

	public Response(int elevatorId, Integer currentFloor, String responseTxt) {
		this.elevatorId = elevatorId;
		this.currentFloor = currentFloor;
		this.responseTxt = responseTxt;
	}

	public Response() {
	}

	public Integer getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(Integer elevatorId) {
		this.elevatorId = elevatorId;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}

	public String getResponseTxt() {
		return responseTxt;
	}

	public void setResponseTxt(String responseTxt) {
		this.responseTxt = responseTxt;
	}
}
