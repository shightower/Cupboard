package org.bcc.cupboard.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.bcc.cupboard.dao.CustomerDao;
import org.bcc.cupboard.dao.OrderDao;
import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.NonTefapOrderBean;
import org.bcc.cupboard.entity.TefapBean;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.bcc.cupboard.entity.jpa.NonTefapOrderJpa;
import org.bcc.cupboard.entity.jpa.TefapJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/orders")
public class OrderService {
	private static final Logger Log = Logger.getLogger(OrderService.class);
	
	@Autowired
	private OrderDao<NonTefapOrderJpa> nonTefapDao;
	
	@Autowired
	private OrderDao<TefapJpa> tefapDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@POST
	@Path("new/nonTefap")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response createNewNonTefapOrder(@QueryParam("order") NonTefapOrderBean order,
			@QueryParam("customer") Customer customer) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = customerDao.findById(customer.getId());
			NonTefapOrderJpa orderJpa = new NonTefapOrderJpa(order);
			orderJpa.setCustomer(customerJpa);
			nonTefapDao.persist(orderJpa);
			rb.entity("Successfully created new order");
		}  catch(Exception ex) {
			Log.error("Error creating new order", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error creating new order, please contact support team");
		}
		return rb.build();
	}
	
	@POST
	@Path("new/tefap")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response createNewTefapOrder(@QueryParam("order") TefapBean order,
			@QueryParam("customer") Customer customer) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = customerDao.findById(customer.getId());
			TefapJpa orderJpa = new TefapJpa(order);
			orderJpa.setCustomer(customerJpa);
			tefapDao.persist(orderJpa);
			rb.entity("Successfully created new order");
		}  catch(Exception ex) {
			Log.error("Error creating new order", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error creating new order, please contact support team");
		}
		return rb.build();
	}
}
