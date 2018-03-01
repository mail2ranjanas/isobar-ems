package com.elevatorms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.elevator.api.ElevatorCallback;

import com.elevatorms.util.Util;

/**
 * The implementation class for the call back from elevator hardware
 * The logs from the elevator hardware is collected here.
 * The logs are sent to the frontend.
 * 
 * @author ranjana
 *
 */
public class EMSConsumerCallback implements ElevatorCallback{

	private StringBuilder elevatorLog = null;
	private Integer currtntFloor = 0;
	private int elevatorNo=0;
	
	private static final Logger logger = LoggerFactory.getLogger(EMSConsumerCallback.class);	
	
	public EMSConsumerCallback(int elevatorNo) {
		super();
		this.elevatorNo = elevatorNo;
		if(elevatorLog == null)
			elevatorLog = new StringBuilder();
	}

	@Override
	public void elevatorArrived(int floor) {
		currtntFloor = floor;
		System.out.println("EMSConsumerCallback "+currtntFloor);
		String hwLogs = Util.getInstance().getHWLogs(elevatorNo, floor);
		elevatorLog.append(hwLogs);
		elevatorLog.append("\n");
		logger.debug("Elivator has arrived : "+floor);
	}
	
	public int getCurrentFloor() {
		return currtntFloor;
	}

	public StringBuilder getElevatorLog() {
		return elevatorLog;
	}

}
