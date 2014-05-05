package org.bcc.cupboard.dao;

import java.util.List;

import org.bcc.cupboard.entity.jpa.CustomerJpa;

public interface CustomerDao {

	public CustomerJpa persist(CustomerJpa customer);
	public CustomerJpa update(CustomerJpa customer);
	public List<CustomerJpa> findByName(String firstName, String lastName);
	public List<CustomerJpa> findByFirstName(String lastName);
	public List<CustomerJpa> findByLastName(String lastName);
	public List<CustomerJpa> findAll();
	public CustomerJpa findById(long id);
	public void delete(CustomerJpa customer);
}
