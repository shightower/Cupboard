package org.bcc.cupboard.dao;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public OrderJpa findById(long id) {
		OrderJpa order = null;

		try {
			order = (OrderJpa) em.find(OrderJpa.class, id);
		} catch(Exception ex) {
			Log.error("Error searching for Order by Id", ex);
		}

		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> searchFromTo(String fromDate, String toDate) {
		List<OrderJpa> orders;

		try {
			Query query = em.createQuery("from "
					+ OrderJpa.class.getSimpleName()
					+ " o where o.orderDate between "
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
			Query query = em.createQuery("from "
					+ OrderJpa.class.getSimpleName()
					+ " o where o.orderDate ="
					+ day);

			orders = (List<OrderJpa>) query.getResultList();
		} catch(Exception ex) {
			Log.error("Error retrieving Orders on "
					+ day);
			orders = new ArrayList<OrderJpa>();
		}

		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> getAllPending() {
		List<OrderJpa> pendingOrders;

		try {
			Query query = em.createQuery("from "
					+ OrderJpa.class.getSimpleName()
					+ " p where p.isPending=1 order by p.id asc");
			pendingOrders = (List<OrderJpa>) query.getResultList();

		} catch(Exception ex) {
			Log.error("Error retrieving pending orders");
			pendingOrders = new ArrayList<OrderJpa>();
		}

		return pendingOrders;
	}

	@Override
	public void delete(OrderJpa order) {
		Query query = em.createQuery("delete from "
				+ OrderJpa.class.getSimpleName()
				+ " a where a.orderNum="
				+ order.getOrderNum());

		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> getPendingByCustomerAndType(Customer customer, String type) {
		List<OrderJpa> pendingOrders;

		try {
			Query query = em.createQuery("from "
					+ OrderJpa.class.getSimpleName()
					+ " p where p.isPending=1 and p.customer="
					+ customer.getId()
					+ " and p.orderType='"
					+ type
					+ "'");
			pendingOrders = (List<OrderJpa>) query.getResultList();

		} catch(Exception ex) {
			Log.error("Error retrieving pending orders");
			pendingOrders = new ArrayList<OrderJpa>();
		}

		return pendingOrders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> generateOrderReport(Date startDate, Date endDate) {
		List<OrderJpa> orders = null;

		try {
			Query query = em.createQuery("from " 
					+ OrderJpa.class.getSimpleName() 
					+ " p where p.isPending=0 and p.orderType='Regular' and p.orderDate between :startDate and :endDate");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			orders = (List<OrderJpa>) query.getResultList();

		} catch(Exception ex) {
			Log.error("Error generating order reports", ex);
			orders = new ArrayList<OrderJpa>();
		}
		
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderJpa> generateTefapReport(Date startDate, Date endDate) {
		List<OrderJpa> tefapOrders = null;

		try {
			Query query = em.createQuery("from " 
					+ OrderJpa.class.getSimpleName() 
					+ " p where p.isPending=0 and p.orderType=TEFAP and p.orderDate between " 
					+ startDate 
					+ " and  " 
					+ endDate);
			tefapOrders = query.getResultList();
		} catch(Exception ex) {
			Log.error("Error generating order reports", ex);
			tefapOrders = new ArrayList<OrderJpa>();
		}
		
		return tefapOrders;
	}

}
