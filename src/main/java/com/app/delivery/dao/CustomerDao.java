package com.app.delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.delivery.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
