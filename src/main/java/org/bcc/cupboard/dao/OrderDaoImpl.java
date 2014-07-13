package org.bcc.cupboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.jpa.OrderJpa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Qualifier(value = "orderDao")
public class OrderDaoImpl<T extends OrderJpa> extends BaseDao implements OrderDao<T> {
	private static final Logger Log = Logger.getLogger(OrderDaoImpl.class);
	
	@Override
	public void persist(T order) {
		try {
			em.persist(order);
		} catch(Exception ex) {
			Log.error("Error creating new order", ex);
		}

	}

	@Override
	public void update(T order) {
		try {
			em.merge(order);
		} catch(Exception ex) {
			Log.error("Error updating Order"+ order.getOrderNum() + ")");
		}
	}
	
	@Override
	public T findById(Class<T> clazz, long id) {
		T order = null;

		try {
			order = (T) em.find(clazz, id);
		} catch(Exception ex) {
			Log.error("Error searching for Order by Id", ex);
		}
		
		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchFromTo(String fromDate, String toDate, Class<T> clazz) {
		List<T> orders;
		
		try {
			Query query = em.createQuery("from "
					+ clazz.getSimpleName()
					+ " o where o.orderDate between "
					+ fromDate 
					+ " and " 
					+ toDate);
			
			orders = (List<T>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving Orders between "
					+ fromDate 
					+ "-" 
					+ toDate);
			orders = new ArrayList<T>();
		}
		
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchForDay(String day, Class<T> clazz) {
		List<T> orders;
		
		try {
			Query query = em.createQuery("from "
					+ clazz.getSimpleName()
					+ " o where o.orderDate ="
					+ day);
			
			orders = (List<T>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving Orders on "
					+ day);
			orders = new ArrayList<T>();
		}
		
		return orders;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllPending(Class<T> clazz) {
		List<T> pendingOrders;
		
		try {
			Query query = em.createQuery("from "
					+ clazz.getSimpleName()
					+ " p where p.isPending=1");
			pendingOrders = (List<T>) query.getResultList();
			
		} catch(Exception ex) {
			Log.error("Error retrieving pending orders");
			pendingOrders = new ArrayList<T>();
		}
		
		return pendingOrders;
	}
	
	@Override
	public void delete(T order) {
		em.remove(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPendingByCustomerAndType(Customer customer, String type, Class<T> clazz) {
		List<T> pendingOrders;
		
		try {
			Query query = em.createQuery("from "
					+ clazz.getSimpleName()
					+ " p where p.isPending=1 and p.customer="
					+ customer.getId()
					+ " and p.orderType='"
					+ type
					+ "'");
			pendingOrders = (List<T>) query.getResultList();
			
		} catch(Exception ex) {
			Log.error("Error retrieving pending orders");
			pendingOrders = new ArrayList<T>();
		}
		
		return pendingOrders;
	}

}
