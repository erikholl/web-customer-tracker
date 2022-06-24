package io.eho.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.eho.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// inject the SessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);
		
		// execute query, get results
		List<Customer> customers = query.getResultList();
		
		// return results
		return customers;
	}

}
