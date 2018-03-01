package com.elevatorms.service;

import com.elevatorms.model.Response;

public interface EMSService {
	
	public Response moveUp(int floorNumber);
	
	public Response moveDown(int floorNumber);
	
	public String stopElevator(int elevatorId);
	
	public Response pressButton(int elevatorId, int destFloor, int currentFloor);
	
	public String getEMSLogs();

}
