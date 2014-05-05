package org.bcc.cupboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends BaseDao implements CustomerDao {
	private static final Logger Log = Logger.getLogger(CustomerDaoImpl.class);
	
	@Override
	public CustomerJpa findById(long id) {
		CustomerJpa customer = null;
		try {
			customer = (CustomerJpa) em.find(CustomerJpa.class, id);
		} catch(Exception ex) {
			Log.error("Error finding user by id", ex);
		}
		
		return customer;
	}
	
	@Override
	public CustomerJpa persist(CustomerJpa customer) {
		try {
			em.persist(customer);
		} catch(Exception ex) {
			Log.error("Error persisting new customer", ex);
		}
		
		return customer;
	}

	@Override
	public CustomerJpa update(CustomerJpa customer) {
		try {
			em.merge(customer);
		} catch(Exception ex) {
			Log.error("Error updating Customer(" + customer.getId() + ")");
		}
		
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerJpa> findByName(String fName, String lName) {
		List<CustomerJpa> customers = new ArrayList<CustomerJpa>();
		
		try {
			Query query = em.createQuery("from CustomerJpa c where c.firstName = '"
					+ fName 
					+ "' and c.lastName = '" 
					+ lName
					+ "'");
			
			customers = (List<CustomerJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error while retrieving Customer("
					+ fName 
					+ " " 
					+ lName 
					+ ")", ex);
		}
		
		return customers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerJpa> findByFirstName(String fName) {
		List<CustomerJpa> customers = new ArrayList<CustomerJpa>();
		
		try {
			Query query = em.createQuery("from CustomerJpa c where c.firstName = '"
					+ fName
					+ "'");
			
			customers = (List<CustomerJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error while retrieving Customer("
					+ fName 
					+ ")", ex);
		}
		
		return customers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerJpa> findByLastName(String lName) {
		List<CustomerJpa> customers = new ArrayList<CustomerJpa>();
		
		try {
			Query query = em.createQuery("from CustomerJpa c where c.lastName = '" 
					+ lName
					+ "'");
			
			customers = (List<CustomerJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error while retrieving Customer("
					+ lName 
					+ ")", ex);
		}
		
		return customers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerJpa> findAll() {
		List<CustomerJpa> customers;
		
		try {
			Query query = em.createQuery("from CustomerJpa c order by c.lastName asc");
			customers = (List<CustomerJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving all Customers from DB", ex);
			customers  = new ArrayList<CustomerJpa>();
		}
		
		return customers;
	}
	
	
	public void delete(CustomerJpa customer) {
		em.remove(customer);
	}

}
