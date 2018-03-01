package com.elevatorms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elevatorms.consumer.EMSConsumerFacade;
import com.elevatorms.model.Response;


/**
 * The service class which for EMS, the logic and rules are defined here.
 * The EMSConsumerFacade is injected here to interect with the elevator HW API
 * 
 * @author ranjana
 * TODO- Handle the exception and provide error handling
 *
 */
@Component
public class EMSServiceImpl implements EMSService {

	@Autowired
	EMSConsumerFacade emsConsumerFacade;
	
	@Override
	public Response moveUp(int floorNumber) {
		Response response = null;
		
		int elevatorOnePos =  emsConsumerFacade.getElevatorPosition(1);
		int elevatorTwoPos = emsConsumerFacade.getElevatorPosition(2);
		
		int gapElevatorOne = Math.abs(elevatorOnePos - floorNumber);
		int gapElevatorTwo = Math.abs(elevatorTwoPos - floorNumber);
		
	
		if(gapElevatorOne<gapElevatorTwo) {
			response = moveElevator(floorNumber, elevatorOnePos, gapElevatorOne, 1);
			
		}else {
			response = moveElevator(floorNumber, elevatorTwoPos, gapElevatorTwo, 2);
		}
		
		
		return response;
	}
	
	@Override
	public Response moveDown(int floorNumber) {
		Response response = null;
		
		int elevatorOnePos =  emsConsumerFacade.getElevatorPosition(1);
		int elevatorTwoPos = emsConsumerFacade.getElevatorPosition(2);
		
		int gapElevatorOne = Math.abs(elevatorOnePos - floorNumber);
		int gapElevatorTwo = Math.abs(elevatorTwoPos - floorNumber);
		

		
		if(gapElevatorOne<gapElevatorTwo) {
			response = moveElevator(floorNumber, elevatorOnePos, gapElevatorOne, 1);
		}else {
			response = moveElevator(floorNumber, elevatorTwoPos, gapElevatorTwo, 2);
		}
		
		
		return response;
	}

	private Response moveElevator(int floorNumber, int elevatorPos, int gapElevator, int elevatorId) {
		Response response = null;
		if(floorNumber>elevatorPos) {
			response = emsConsumerFacade.moveUp(floorNumber, elevatorPos, gapElevator, elevatorId);
		}else if(floorNumber<elevatorPos) {
			response = emsConsumerFacade.moveDown(floorNumber, elevatorPos, gapElevator, elevatorId);
		}else {
			//Open the door without moving
			response = new Response();
			response.setCurrentFloor(elevatorPos);
			response.setElevatorId(elevatorId);
			response.setResponseTxt("The Elevator "+elevatorId+" is already in the "+elevatorPos+" open the door.");
		}
		return response;
	}
	
	@Override
	public String stopElevator(int elevatorId) {
		emsConsumerFacade.stopElevator(elevatorId);
		return null;
	}

	@Override
	public Response pressButton(int elevatorId, int destFloor, int currentFloor) {

		Response response = null;
		int gapElevatorOne = Math.abs(currentFloor - destFloor);
		if (currentFloor > destFloor) {
			// move down;
			response = emsConsumerFacade.moveDown(currentFloor, currentFloor, gapElevatorOne, elevatorId);
		} else if (currentFloor < destFloor) {
			// move up
			response = emsConsumerFacade.moveUp(currentFloor, currentFloor, gapElevatorOne, elevatorId);
		} else {
			// open
			response = new Response();
			response.setResponseTxt("The Elevator "+elevatorId+" is already in the "+destFloor+" Open the door.");
			response.setCurrentFloor(currentFloor);
			response.setElevatorId(elevatorId);
			
		}

		return response;
	}

	@Override
	public String getEMSLogs() {
		return emsConsumerFacade.loadEMSLogs();
	}

	 
}
