package com.elevatorms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elevatorms.model.Response;
import com.elevatorms.service.EMSService;

/**
 * The REST service to handle the Elevator console user actions.
 * All the REST API calls from Angular front-end are handled here.
 * 
 * @author Ranjana
 *
 */
@RestController
public class EMSController {
	
	@Autowired
	private EMSService emsService;
	
	/**
	 * Receive the log files from the HW API and send them to the console
	 * @return
	 */
	@GetMapping("/ems/getEMSLogs")
	public String getEMSLogs() {
		return emsService.getEMSLogs();
	}
    
	/**
	 * Stop the lift
	 * @param elevatorId
	 * @return
	 */
    @RequestMapping(value = "/ems/{elevatorId}", method = RequestMethod.PUT)
    public String stop(@PathVariable Integer elevatorId) {
    	emsService.stopElevator(elevatorId);
    	return "success";
    	
    }
    
    /**
     * Handle the press button user action.
     * 
     * @param elevatorId - elevator which user has clicked the btn
     * @param destFloor  - Destination floor which user intend to go 
     * @param currentFloor - Passenger current floor
     * @return
     */
	@RequestMapping(value = "/ems/pressButton/{elevatorId}/{destFloor}/{currentFloor}", method = RequestMethod.PUT)
	public Response pressButton(@PathVariable Integer elevatorId, @PathVariable Integer destFloor,
			@PathVariable Integer currentFloor) {
		Response response = emsService.pressButton(elevatorId, destFloor, currentFloor);
		return response;

	}
	
	/**
	 * Handle the move up elevator button, 
	 * @param floorNumber - Passenger floor which user is waiting to catch the lift
	 * @return
	 */
    @RequestMapping(value = "/ems/moveUp/{floorNumber}", method = RequestMethod.PUT)
    public ResponseEntity moveUp(@PathVariable Integer floorNumber) {
    	Response response = null;
    	response = emsService.moveUp(floorNumber);
    	//System.out.println(response);
    	return new ResponseEntity(response, HttpStatus.OK);
    	
    }
    
	/**
	 * Handle the move down elevator button, 
	 * @param floorNumber - Passenger floor which user is waiting to catch the lift
	 * @return
	 */
    @RequestMapping(value = "/ems/moveDown/{floorNumber}", method = RequestMethod.PUT)
    public ResponseEntity moveDown(@PathVariable Integer floorNumber) {
    	Response response = null;
    	response = emsService.moveDown(floorNumber);
    	return new ResponseEntity(response, HttpStatus.OK);
    	
    }
}
