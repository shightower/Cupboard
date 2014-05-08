package org.bcc.cupboard.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.bcc.cupboard.dao.CustomerDao;
import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.CustomerBean;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/customer")
public class CustomerService {
	private static final Logger Log = Logger.getLogger(CustomerService.class);
	
	@Autowired
	CustomerDao customerDao;
	
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response addCustomer(CustomerBean customer) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = new CustomerJpa(customer);
			customerDao.persist(customerJpa);
			rb.entity("Successfully Added New Customer");
		} catch(Exception ex) {
			Log.error(ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unable to add Customer, notify Customer Support");
		}		
		
		return rb.build();
	}
	
	@GET
	@Path("search")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response getCustomer(@QueryParam("firstName") @DefaultValue("") String firstName,
			@QueryParam("lastName") @DefaultValue("") String lastName) {
		ResponseBuilder rb = Response.status(Status.OK);
		EntityWrapper<CustomerBean> wrapper = new EntityWrapper<CustomerBean>();
		List<CustomerBean> customers = new ArrayList<CustomerBean>();
		List<? extends Customer> cusRecords = new ArrayList<Customer>();
		
		firstName = firstName.trim();
		lastName = lastName.trim();
		
		if(!firstName.isEmpty() && !lastName.isEmpty()) {
			cusRecords = customerDao.findByName(firstName, lastName);
		} else if(!firstName.isEmpty()) {
			cusRecords = customerDao.findByFirstName(firstName);
		} else if(!lastName.isEmpty()) {
			cusRecords = customerDao.findByLastName(lastName);
		}
		
		for(Customer customer : cusRecords) {
			CustomerBean bean = new CustomerBean(customer);
			customers.add(bean);
		}
		
		wrapper.setEntities(customers);
		wrapper.setResultCount(customers.size());
		rb.entity(wrapper);
		return rb.build();		
	}
	
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updateCustomer(CustomerBean customer) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = customerDao.findById(customer.getId());
			
			if(customerJpa != null) {
				customerDao.update(customerJpa);
				rb.entity("Successfully Added New Customer");
			}
		} catch(Exception ex) {
			Log.error(ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unable to update Customer, notify Customer Support");
		}
		
		return rb.build();
	}
	
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response removeCustomer(CustomerBean customer) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = customerDao.findById(customer.getId());
			
			if(customerJpa != null) {
				customerDao.delete(customerJpa);
			} 
		} catch(Exception ex) {
			Log.error(ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Unable to remove Customer, notify Customer Support");
		}
		
		return rb.build();
	}
}