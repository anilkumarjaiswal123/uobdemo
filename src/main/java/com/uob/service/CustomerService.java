package com.uob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uob.Entity.Customer;
import com.uob.dao.ICustomerDAO;

@Service
public class CustomerService  implements ICustomerService{

	@Autowired
	private ICustomerDAO customersDAO;
	
	@Override
	public List<Customer> getAllCustomers(){
		
		return customersDAO.getAllCustomers();
	}
}
