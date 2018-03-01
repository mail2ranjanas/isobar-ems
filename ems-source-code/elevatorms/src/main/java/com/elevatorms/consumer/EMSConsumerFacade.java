package com.elevatorms.consumer;

import org.springframework.stereotype.Component;
import org.test.elevator.api.ElevatorFacade;
import org.test.elevator.api.ElevatorFactory;

import com.elevatorms.model.Response;
import com.elevatorms.util.Util;

/**
 * This class is a facade class which interect with the third party API (Elevator Hardware).
 * All lookups for third party API is implemented here.
 * 
 * @author ranjana
 *
 */
@Component
public class EMSConsumerFacade {
	
	private static ElevatorFacade elevatorOne;
	
	private static ElevatorFacade elevatorTwo;
	
	private EMSConsumerCallback callbackOne;
	private EMSConsumerCallback callbackTwo;
	private Response responseObj;
	
	public EMSConsumerFacade() {
		try{
        	callbackOne  = new EMSConsumerCallback(1);
        	elevatorOne = ElevatorFactory.getElevatorFacade(1, callbackOne);
        	
        	callbackTwo  = new EMSConsumerCallback(2);
        	elevatorTwo = ElevatorFactory.getElevatorFacade(2, callbackTwo);
        	responseObj = new Response();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instances");
        }
	}
	
	public static ElevatorFacade getElevatorFacade_1(){
        return elevatorOne;
    }
	
	public static ElevatorFacade getElevatorFacade_2(){
        return elevatorTwo;
    }
	
	/**
	 * Logic for calling third party API for move the elevator up.
	 * This simulated how many floors which elevator needs to be moved
	 * 
	 * @param floorNumber
	 * @param elevatorPos
	 * @param numberOfFloors
	 * @param elevatorId
	 * @return
	 */
	public Response moveUp(int floorNumber, int elevatorPos, int numberOfFloors, int elevatorId) {
		String responseTxt = null;
		int currentFloor = 0;
		StringBuffer response = new StringBuffer();
		unlockBreaks(elevatorId);
		
		for(int x=0 ; x<numberOfFloors; x++) {
			if(elevatorId ==1) {
				elevatorOne.moveUpOneFloor();
			}else {
				elevatorTwo.moveUpOneFloor();
			}
		}
		
		lockBreaks(elevatorId);
		
		currentFloor = getCurrentFloor(elevatorId);
		
		responseObj.setElevatorId(elevatorId);
		responseObj.setCurrentFloor(currentFloor);
		responseTxt = Util.getInstance().getResponseTxt(elevatorId, currentFloor);
		responseObj.setResponseTxt(responseTxt);
		return responseObj;
		
	}
	
	/**
	 * Logic for calling third party API for move the elevator down.
	 * This simulated how many floors which elevator needs to be moved
	 * 
	 * @param floorNumber
	 * @param elevatorPos
	 * @param numberOfFloors
	 * @param elevatorId
	 * @return
	 */
	public Response moveDown(int floorNumber, int elevatorPos, int numberOfFloors, int elevatorId) {
		String responseTxt = null;
		int currentFloor = 0;
		unlockBreaks(elevatorId);
		
		for(int x=0 ; x<numberOfFloors; x++) {
			if(elevatorId ==1) {
				elevatorOne.moveDownOneFloor();
			}else {
				elevatorTwo.moveDownOneFloor();
			}
		}
		
		lockBreaks(elevatorId);
		
		currentFloor = getCurrentFloor(elevatorId);
		responseObj.setElevatorId(elevatorId);
		responseObj.setCurrentFloor(currentFloor);
		responseTxt = Util.getInstance().getResponseTxt(elevatorId, currentFloor);
		responseObj.setResponseTxt(responseTxt);
		return responseObj;
		
	}
	
	public int getCurrentFloor(int elevatorId) {
		int currentFloor;
		if(elevatorId ==1) {
			currentFloor =  callbackOne.getCurrentFloor();
		}else {
			currentFloor =  callbackTwo.getCurrentFloor();
		}
		
		return currentFloor;
	}
	
	/**
	 * This method calls the third party API to lock the breaks
	 * @param elevatorId
	 */
	public String stopElevator(int elevatorId) {
		if(elevatorId ==1) {
			elevatorOne.lockBreaks();
		}else {
			elevatorTwo.lockBreaks();
		}
		return "stopElevator";
	}

	/**
	 * This method calls the third party API to unlock the breaks
	 * @param elevatorId
	 */
	private void unlockBreaks(int elevatorId) {
		if(elevatorId ==1) {
			elevatorOne.unlockBreaks();
		}else {
			elevatorTwo.unlockBreaks();
		}
	}
	
	private void lockBreaks(int elevatorId) {
		if(elevatorId ==1) {
			elevatorOne.lockBreaks();
		}else {
			elevatorTwo.lockBreaks();
		}
	}
	
	public int getElevatorPosition(int elevator) {
		int elevatorPosition = 0;
		if(elevator == 1) {
			elevatorPosition = callbackOne.getCurrentFloor();
		}
		if(elevator == 2) {
			elevatorPosition = callbackTwo.getCurrentFloor();
		}
		
		return elevatorPosition;
		
	}
	
	public String loadEMSLogs() {
		StringBuffer emsLogs = new StringBuffer();
		emsLogs.append(callbackOne.getElevatorLog());
		emsLogs.append(callbackTwo.getElevatorLog());
		
		return emsLogs.toString();
		
	}

}
