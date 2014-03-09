package org.bcc.cupboard.dao;

import java.util.List;

import org.bcc.cupboard.entity.jpa.CustomerJpa;

public interface CustomerDao {

	public CustomerJpa persist(CustomerJpa customer);
	public CustomerJpa update(CustomerJpa customer);
	public CustomerJpa findByName(String firstName, String lastName);
	public List<CustomerJpa> findAll();
}
