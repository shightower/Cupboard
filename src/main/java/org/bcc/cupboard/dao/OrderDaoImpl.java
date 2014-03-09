package org.bcc.cupboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.bcc.cupboard.entity.jpa.OrderJpa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDaoImpl extends BaseDao implements OrderDao {
	private static final Logger Log = Logger.getLogger(OrderDaoImpl.class);
	
	@Override
	public void persist(OrderJpa order) {
		try {
			em.persist(order);
		} catch(Exception ex) {
			Log.error("Error creating new order", ex);
		}

	}

	@Override
	public void update(OrderJpa order) {
		try {
			em.merge(order);
		} catch(Exception ex) {
			Log.error("Error updating Order"+ order.getOrderNum() + ")");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> searchFromTo(String fromDate, String toDate) {
		List<OrderJpa> orders;
		
		try {
			Query query = em.createQuery("from OrderJpa o where o.orderDate between "
					+ fromDate 
					+ " and " 
					+ toDate);
			
			orders = (List<OrderJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving Orders between "
					+ fromDate 
					+ "-" 
					+ toDate);
			orders = new ArrayList<OrderJpa>();
		}
		
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> searchForDay(String day) {
		List<OrderJpa> orders;
		
		try {
			Query query = em.createQuery("from OrderJpa o where o.orderDate ="
					+ day);
			
			orders = (List<OrderJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving Orders on "
					+ day);
			orders = new ArrayList<OrderJpa>();
		}
		
		return orders;
	}

}
