package org.bcc.cupboard.dao;

import java.util.List;

import org.bcc.cupboard.entity.jpa.OrderJpa;

public interface OrderDao {
	public void persist(OrderJpa order);
	public void update(OrderJpa order);
	public List<OrderJpa> searchFromTo(String from, String to);
	public List<OrderJpa> searchForDay(String day);
}
