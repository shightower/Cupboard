package org.bcc.cupboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerDaoImpl extends BaseDao implements CustomerDao {
	private static final Logger Log = Logger.getLogger(CustomerDaoImpl.class);
	
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
	public CustomerJpa findByName(String firstName, String lastName) {
		CustomerJpa customer = null;
		
		if(firstName != null && lastName != null) {
			Query query = em.createQuery("from CustomerJpa c where c.firstName = "
					+ firstName 
					+ " and c.lastName = " 
					+ lastName );
			
			customer = (CustomerJpa) query.getSingleResult();
		}
		
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerJpa> findAll() {
		List<CustomerJpa> customers;
		
		try {
			Query query = em.createQuery("from CustomerJpa c order by c.lastName asc", CustomerJpa.class);
			customers = (List<CustomerJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving all Customers from DB", ex);
			customers  = new ArrayList<CustomerJpa>();
		}
		
		return customers;
	}

}
