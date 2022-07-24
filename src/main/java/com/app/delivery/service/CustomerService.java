package com.app.delivery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.delivery.dao.CustomerDao;
import com.app.delivery.entity.Customer;

@Service
public class CustomerService {
	
	private final CustomerDao customerDao;

	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Transactional
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}
}
