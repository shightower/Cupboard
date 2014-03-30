package org.bcc.cupboard.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	
	@GET
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response addCustomer(@QueryParam("customer") CustomerBean customer) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = new CustomerJpa(customer);
			customerDao.persist(customerJpa);
		} catch(Exception ex) {
			Log.error(ex);
		}
		
		rb.entity("Successfully Added New Customer");
		return rb.build();
	}
	
	@GET
	@Path("search")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response getCustomer(@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName) {
		ResponseBuilder rb = Response.status(Status.OK);
		Customer customer = null;
		Customer cusRecord = customerDao.findByName(firstName, lastName);
		
		if(cusRecord != null) {
			customer = new CustomerBean(cusRecord);
		} else {
			customer = new CustomerBean();
		}
		
		rb.entity(customer);
		return rb.build();
		
	}
}