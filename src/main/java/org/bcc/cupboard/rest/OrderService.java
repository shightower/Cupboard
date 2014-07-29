package org.bcc.cupboard.rest;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.bcc.cupboard.dao.OrderDao;
import org.bcc.cupboard.entity.OrderBean;
import org.bcc.cupboard.entity.jpa.CustomerJpa;
import org.bcc.cupboard.entity.jpa.OrderJpa;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Path("/orders")
public class OrderService {
	private static final Logger Log = Logger.getLogger(OrderService.class);
	private static final int PENDING = 1;
	private static final int NOT_PENDING = 0;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Value("${order.wait.period}")
	private int waitPeriod;
	
	@Value("${order.restriction.days}")
	private String restrictionDays;

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

	@GET
	@Path("pending/all")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response getPending() {
		ResponseBuilder rb = Response.status(Status.OK);
		List<OrderBean> pendingOrders = new ArrayList<OrderBean>();

		try {
			List<OrderJpa> pending = orderDao.getAllPending();

			for(OrderJpa order : pending) {
				OrderBean pendingOrder = new OrderBean(order);
				pendingOrder.setCustomerFirstName(order.getCustomer().getFirstName());
				pendingOrder.setCustomerLastName(order.getCustomer().getLastName());
				pendingOrders.add(pendingOrder);
			}

			EntityWrapper<OrderBean> wrapper = new EntityWrapper<OrderBean>();
			wrapper.setEntities(pendingOrders);
			wrapper.setResultCount(pendingOrders.size());
			rb.entity(wrapper);
		} catch(Exception ex) {
			Log.error("Error retrieving pending orders", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error retriving pending orders, contact technical team");
		}

		return rb.build();
	}

	@GET
	@Path("pending/complete")
	@Produces({MediaType.TEXT_PLAIN})
	public Response completeOrder(@QueryParam("orderNum") int id, @QueryParam("orderWeight") int weight) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			OrderJpa order = orderDao.findById(id);

			if(order != null && order.isPending()) {
				order.setOrderWeight(weight);
				order.setIsPending(NOT_PENDING);
				orderDao.update(order);
				updateCustomerRecord(order.getCustomer());
			} else {
				throw new InvalidParameterException();
			}
			
		} catch(InvalidParameterException ex) {
			Log.error("Invalid Order number, record not found or already completed", ex);
			rb = Response.status(Status.BAD_REQUEST);
			rb.entity("Invalid Order number, record not found or already completed");
		} catch(Exception ex) {
			Log.error("Error completing pending order", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error completing pending order");
		}

		return rb.build();
	}

	@GET
	@Path("pending/tefap/complete")
	@Produces({MediaType.TEXT_PLAIN})
	public Response completeTefapOrder(@QueryParam("orderNum") int id, @QueryParam("tefapCount") int count, @QueryParam("tefapWeight") int weight) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			OrderJpa order = orderDao.findById(id);

			if(order != null && order.isPending()) {
				order.setTefapCount(count);
				order.setOrderWeight(weight);
				order.setIsPending(NOT_PENDING);
				orderDao.update(order);
			} else {
				throw new InvalidParameterException();
			}
			
		} catch(InvalidParameterException ex) {
			Log.error("Invalid TEFAP Order number, record not found or already completed", ex);
			rb = Response.status(Status.BAD_REQUEST);
			rb.entity("Invalid TEFAP Order number, record not found or already completed");
		} catch(Exception ex) {
			Log.error("Error completing pending TEFAP Order", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error completing pending TEFAP Order");
		}

		return rb.build();
	}
	
	@GET
	@Path("pending/remove")
	@Produces({MediaType.TEXT_PLAIN})
	public Response removePendingOrder(@QueryParam("orderNum") int id) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			OrderJpa order = orderDao.findById(id);

			if(order != null && order.isPending()) {
				Log.info("Deleting pending order #"
						+ order.getOrderNum());
				
				orderDao.delete(order);
			} else {
				throw new InvalidParameterException();
			}
			
		} catch(InvalidParameterException ex) {
			Log.error("Invalid Order number, record not found or already completed", ex);
			rb = Response.status(Status.BAD_REQUEST);
			rb.entity("Invalid Order number, record not found or already completed");
		} catch(Exception ex) {
			Log.error("Error removing order", ex);
			rb = Response.status(Status.INTERNAL_SERVER_ERROR);
			rb.entity("Error removing order");
		}

		return rb.build();
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
			List<OrderJpa> pendingOrders = orderDao.getPendingByCustomerAndType(customer, orderType);

			if(pendingOrders != null && pendingOrders.size() > 0) {
				throw new IllegalArgumentException("Customer already has pending order");
			}

			OrderJpa orderJpa = new OrderJpa();
			orderJpa.setIsPending(PENDING);
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
	
	private void updateCustomerRecord(CustomerJpa customer) {
		DateTime currentOrderDate = DateTime.now();
		int day = currentOrderDate.getDayOfWeek();
		boolean isRestrictedDay = false;
		
		String[] restrictedDays = restrictionDays.split(",");
		
		for(String restrictedDay : restrictedDays) {
			if(Long.valueOf(restrictedDay) == day) {
				isRestrictedDay = true;
				break;
			}
		}
		
		int waitingPeriod;
		
		if(isRestrictedDay) {
			waitingPeriod = waitPeriod;
		} else {
			waitingPeriod = 7;
		}
		
		DateTime nextOrderDate = currentOrderDate.plusDays(waitingPeriod);
		
		customer.setNextAvailableDate(nextOrderDate.toDate());
		customerDao.update(customer);
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
