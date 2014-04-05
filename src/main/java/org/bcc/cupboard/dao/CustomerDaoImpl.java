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

	@Override
	public CustomerJpa findByName(String fName, String lName) {
		CustomerJpa customer = null;
		
		if(fName != null && lName != null) {
			try {
				Query query = em.createQuery("from CustomerJpa c where c.firstName = '"
						+ fName 
						+ "' and c.lastName = '" 
						+ lName
						+ "'");
				
				customer = (CustomerJpa) query.getSingleResult();
			} catch(Exception ex) {
				Log.error("Error while retrieving Customer("
						+ fName 
						+ " " 
						+ lName 
						+ ")", ex);
			}
		}
		
		return customer;
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

}
