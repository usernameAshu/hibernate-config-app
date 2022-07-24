package com.app.delivery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.delivery.entity.Customer;
import com.app.delivery.service.CustomerService;

@RestController
@RequestMapping("customer/api/v1")
public class CustomerController {
	
	private final CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<List<Customer>>(service.getAllCustomers(), HttpStatus.OK);
	}
	
}
