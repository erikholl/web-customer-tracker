package io.eho.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.eho.springdemo.dao.CustomerDAO;
import io.eho.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the customer DAO
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		
		// get customers from DAO
		List<Customer> customers = customerDAO.getCustomers();
		
		// add customer to the model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}

}