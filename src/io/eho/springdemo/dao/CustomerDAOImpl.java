package io.eho.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.eho.springdemo.entity.Customer;
import io.eho.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject the SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers(int sortField) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// determine sortField
		String sortFieldName = null;

		switch (sortField) {
		case SortUtils.FIRST_NAME:
			sortFieldName = "firstName";
			break;
		case SortUtils.LAST_NAME:
			sortFieldName = "lastName";
			break;
		case SortUtils.EMAIL:
			sortFieldName = "email";
			break;
		default:
			sortFieldName = "lastName";
		}
		
		String queryString = "from Customer order by " + sortFieldName;

		// create query ... sort by last name
		Query<Customer> query = currentSession.createQuery(queryString, Customer.class);

		// execute query, get results
		List<Customer> customers = query.getResultList();

		// return results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer
		currentSession.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(int id) {

		// get the session
		Session currentSession = sessionFactory.getCurrentSession();

		// retrieve the customer from the DB with id
		Customer customer = currentSession.get(Customer.class, id);

		return customer;

	}

	@Override
	public void deleteCustomer(int id) {

		// get the session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete the customer from DB with id
		Query q = currentSession.createQuery("delete from Customer where id=:customerId");
		q.setParameter("customerId", id);
		q.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		// get the session
		Session currentSession = sessionFactory.getCurrentSession();

		// diagnostic println
		System.out.println(searchName);

		// search the customers with searchName in DB
		// ensure if searchName is empty, full list is returned
		Query q = null;

		if (searchName != null && searchName.trim().length() > 0) {
			q = currentSession.createQuery(
					"from Customer where lower(firstName) like :searchName or lower(lastName) like :searchName",
					Customer.class);
			q.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
		} else {
			q = currentSession.createQuery("from Customer", Customer.class);
		}

		List<Customer> customers = q.getResultList();

		return customers;
	}

}
