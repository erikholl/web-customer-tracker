package io.eho.springdemo.dao;

import java.util.List;

import io.eho.springdemo.entity.Customer;

public interface CustomerDAO {
	
	List<Customer> getCustomers(int sortField);

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void deleteCustomer(int id);

	List<Customer> searchCustomers(String searchName);

}
