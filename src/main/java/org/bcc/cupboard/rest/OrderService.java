package org.bcc.cupboard.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.bcc.cupboard.dao.CustomerDao;
import org.bcc.cupboard.dao.OrderDao;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.bcc.cupboard.entity.jpa.OrderJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/orders")
public class OrderService {
	private static final Logger Log = Logger.getLogger(OrderService.class);
	private static final int PENDING_VALUE = 1;
		
	@Autowired
	private OrderDao<OrderJpa> orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@GET
	@Path("new")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response createOrder(@QueryParam("customerId") int customerId ) {
		return createNewOrder(customerId, "Regular");
	}
	
	@GET
	@Path("tefap/new")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response createTefapOrder(@QueryParam("customerId") int customerId ) {
		return createNewOrder(customerId, "TEFAP");
	}
			
	private Response createNewOrder(int customerId, String orderType) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customer = customerDao.findById(customerId);
			
			// do not continue if we can't fine the customer
			if(customer == null) {
				throw new IllegalArgumentException("Invalid Customer Id");
			}
			
			// user can have only one pending order per type
			List<OrderJpa> pendingOrders = orderDao.getPendingByCustomerAndType(customer, orderType, OrderJpa.class);
			
			if(pendingOrders != null && pendingOrders.size() > 0) {
				throw new IllegalArgumentException("Customer already has pending order");
			}
			
			OrderJpa orderJpa = new OrderJpa();
			orderJpa.setIsPending(PENDING_VALUE);
			orderJpa.setCustomer(customer);
			orderJpa.setOrderDate(new Date());
			orderJpa.setOrderType(orderType);
			orderDao.persist(orderJpa);
			rb.entity("Successfully created new order");
		} catch(IllegalArgumentException ex) {
			Log.error("Error creating new order", ex);
			rb = Response.status(Status.BAD_REQUEST);
			rb.entity(ex.getMessage());
		}		
		catch(Exception ex) {
			Log.error("Error creating new order", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error creating new order, please contact support team");
		}
		
		return rb.build();
	}
	
//	@GET
//	@Path("pending")
//	public Response getPendingOrders() {
//		ResponseBuilder rb = Response.status(Status.OK);
//		
//		try {
//			List<NonTefapOrderJpa> pendingNonTefaps = nonTefapDao.getAllPending(NonTefapOrderJpa.class);
//			List<NonTefapOrderBean> orderBeans = new ArrayList<NonTefapOrderBean>();
//			
//			for(NonTefapOrderJpa jpa : pendingNonTefaps) {
//				NonTefapOrderBean bean = new NonTefapOrderBean(jpa);
//				bean.setCustomerFirstName(jpa.getCustomer().getFirstName());
//				bean.setCustomerLastName(jpa.getCustomer().getLastName());
//				orderBeans.add(bean);
//			}
//			
//			EntityWrapper<NonTefapOrderBean> wrapper = new EntityWrapper<NonTefapOrderBean>(orderBeans);
//			rb.entity(wrapper);
//		} catch(Exception ex) {
//			Log.error("Error retrieving pending orders", ex);
//			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
//			rb.entity("Error retrieving orders, please contact support team");
//		}
//		
//		return rb.build();
//	}
//	
//	@POST
//	@Path("new/nonTefap")
//	@Consumes({MediaType.APPLICATION_JSON})
//	public Response createNewNonTefapOrder(@QueryParam("order") NonTefapOrderBean order,
//			@QueryParam("customer") CustomerBean customer) {
//		ResponseBuilder rb = Response.status(Status.OK);
//		
//		try {
//			CustomerJpa customerJpa = customerDao.findById(customer.getId());
//			NonTefapOrderJpa orderJpa = new NonTefapOrderJpa(order);
//			orderJpa.setCustomer(customerJpa);
//			nonTefapDao.persist(orderJpa);
//			rb.entity("Successfully created new order");
//		}  catch(Exception ex) {
//			Log.error("Error creating new order", ex);
//			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
//			rb.entity("Error creating new order, please contact support team");
//		}
//		return rb.build();
//	}
//	
//	@POST
//	@Path("new/tefap")
//	@Consumes({MediaType.APPLICATION_JSON})
//	public Response createNewTefapOrder(@QueryParam("order") TefapBean order,
//			@QueryParam("customer") CustomerBean customer) {
//		ResponseBuilder rb = Response.status(Status.OK);
//		
//		try {
//			CustomerJpa customerJpa = customerDao.findById(customer.getId());
//			TefapJpa orderJpa = new TefapJpa(order);
//			orderJpa.setCustomer(customerJpa);
//			tefapDao.persist(orderJpa);
//			rb.entity("Successfully created new order");
//		}  catch(Exception ex) {
//			Log.error("Error creating new order", ex);
//			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
//			rb.entity("Error creating new order, please contact support team");
//		}
//		return rb.build();
//	}
}
