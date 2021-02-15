package com.uob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uob.Entity.Customer;
import com.uob.service.ICustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		System.out.println("Hiiiiiiiiiiiiiii");
		List<Customer> list = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
	
}
