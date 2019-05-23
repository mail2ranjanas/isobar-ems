The Elevator Management System (EMS) is centralised web based system which interacts with third party hardware API.

The EMS has following features and functionalities:
===================================================
	1) GUI screen to press elevator buttons
	2) Simulate elevator functions
	3) Handle elevator events and redirect them to third party hardware system
	4) Receive events from third party hardware system
	5) Display the elevator movements
	
Assumptions : 
=============
	1) Once the GUI loads-up, select the floor number which passenger is waiting for the lift. 
	    For example, passenger is in 5 floor (passenger floor) and waiting to catch a lift to 1 floor (destination floor). 
	   (Until passenger selects the passenger floor, all the buttons are disabled)
	   
	2) Once user selects the passenger floor, he can select move-up or move-down buttons.
	
	3) Once move-up or move-down buttons are clicked, the available and closest lift will be arrived to the passenger floor.
	
	4) Once lift is arrived to the passenger floor, the message is displayed on the console.
	
	5) Once lift is arrived to the passenger floor, passenger can select the destination floor which passenger intended to go.
		For example, assume passenger floor is 5 and destination floor is 1, the lift is moving down four levels and all movement
		events are captured and displayed.
	
	6) All the lift movement events are captured from the third party API, and display them in the console.

The EMS is developed using Java and following Open Source technologies/frameworks
=================================================================================
	1) Spring Boot 
	2) REST API
	3) Log4j
	4) AngularJS
	5) HTML/CSS
	6) Maven

Deployment 
===========
The elevatorms-1.0.war file needs to be deployed on Tomcat 8 or any other Java compliant web server.

Please load the following URL on browser : http://localhost:8080/elevatorms-1.0/#/

If tomcat is running on different port other than 8080, 
                         please update the port in ElevatorService.js (..\Tomcat 8.5\webapps\elevatorms-1.0\WEB-INF\classes\static\js\service\ElevatorService.js) file (after war file get extracted).

Source Code:
===========
The source code is available in "elevatorms".
This can be imported to the eclipse IDE and run as in the embedded Tomcat server

Maven build command is necessary:
mvn package -Dmaven.test.skip=true

TODOS
============
1) Handle validations and exceptions
2) Sort the events/notifications

GUI
=============
The following screenshot shows the EMS GUI.


Copy rights 
Ranjana 2018,





