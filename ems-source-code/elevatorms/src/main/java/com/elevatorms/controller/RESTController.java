package com.elevatorms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please ignore this file
 * written to test the REST service
 * @author ranjana
 *
 */
@RestController
public class RESTController {
	@GetMapping("/customers")
	public String getCustomers() {
		return "Hello Server";
	}
}
