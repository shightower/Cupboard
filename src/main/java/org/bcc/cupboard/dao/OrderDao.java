package org.bcc.cupboard.dao;

import java.util.List;

import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.jpa.OrderJpa;

public interface OrderDao<T extends OrderJpa>  {
	public void persist(T order);
	public void update(T order);
	public List<T> searchFromTo(String from, String to, Class<T> clazz);
	public List<T> searchForDay(String day, Class<T> clazz);
	public void delete(T order);
	public T findById(Class<T> clazz, long id);
	public List<T> getAllPending(Class<T> clazz);
	public List<T> getPendingByCustomerAndType(Customer customer, String type, Class<T> clazz);
}
