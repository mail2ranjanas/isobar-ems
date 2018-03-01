package com.elevatorms.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private static final Util instance = new Util();

	private Util() {
	};
	
	private String getCurrentTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getResponseTxt(int elevatorId, int currentFloor) {
		StringBuffer responseTxt = new StringBuffer();
		responseTxt.append(getCurrentTimeStamp());
		responseTxt.append(" : ");
		responseTxt.append("The lift "+elevatorId+" has moved to "+currentFloor+ " Floor, to going up");
		return responseTxt.toString();
		
	}
	
	public String getHWLogs(int elevatorId, int currentFloor) {
		StringBuffer responseTxt = new StringBuffer();
		responseTxt.append(getCurrentTimeStamp());
		responseTxt.append(" : ");
		responseTxt.append("The lift "+elevatorId+" has arrived to "+currentFloor+ " floor");
		return responseTxt.toString();
		
	}

	public static Util getInstance() {
		return instance;
	}

}
