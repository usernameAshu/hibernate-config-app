package com.app.delivery.dao;

import java.util.List;

import com.app.delivery.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int id);
	
	public void deleteCustomer(int id);
	
}