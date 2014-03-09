package org.bcc.cupboard.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.bcc.cupboard.dao.CustomerDao;
import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/cupboard/user")
public class UserService {
	private static final Logger Log = Logger.getLogger(UserService.class);
	
	@Autowired
	CustomerDao customerDao;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response addCustomer(Customer customer) {
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
}