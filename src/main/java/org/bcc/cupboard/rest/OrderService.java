package org.bcc.cupboard.rest;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sum;
import static ch.lambdaj.group.Groups.by;
import static ch.lambdaj.group.Groups.group;

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
import org.bcc.cupboard.rest.dto.BccServiceReportDto;
import org.bcc.cupboard.rest.dto.EthnicityReportDto;
import org.bcc.cupboard.rest.dto.ReportDto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.lambdaj.group.Group;

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
	public Response completeOrder(@QueryParam("orderNumber") int orderNumber, @QueryParam("orderWeight") int weight) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			OrderJpa order = orderDao.findById(orderNumber);

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
	public Response completeTefapOrder(@QueryParam("orderNumber") int orderNumber, @QueryParam("tefapCount") int count, @QueryParam("tefapWeight") int weight) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			OrderJpa order = orderDao.findById(orderNumber);

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
	public Response removePendingOrder(@QueryParam("orderNumber") int orderNumber) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			OrderJpa order = orderDao.findById(orderNumber);

			if(order != null && order.isPending()) {
				Log.info("Deleting pending order #"
						+ order.getOrderNumber());
				
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
	
	@GET
	@Path("report/regular")
	@Produces({MediaType.APPLICATION_JSON})
	public Response generateRegularOrderReport(@QueryParam("startDate") Date startDate, @QueryParam("endDate") Date endDate) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			EntityWrapper<ReportDto> wrapper = generateReport(startDate, endDate, false);
			rb.entity(wrapper);
		} catch(Exception ex) {
			Log.error("Error generating regular order report", ex);
			rb = Response.status(Status.BAD_REQUEST);
			rb.entity("Error generating regular order report");
		}
		
		return rb.build();
	}
	
	@GET
	@Path("report/tefap")
	@Produces({MediaType.APPLICATION_JSON})
	public Response generateTefapOrderReport(@QueryParam("startDate") Date startDate, @QueryParam("endDate") Date endDate) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			EntityWrapper<ReportDto> wrapper = generateReport(startDate, endDate, true);
			rb.entity(wrapper);
		} catch(Exception ex) {
			Log.error("Error generating regular order report", ex);
			rb = Response.status(Status.BAD_REQUEST);
			rb.entity("Error generating regular order report");
		}
		
		return rb.build();
	}
	
	public EntityWrapper<ReportDto> generateReport(Date startDate, Date endDate, boolean isTefapReport) {
		
		ReportDto reportDto = new ReportDto();
		List<OrderJpa> orders = new ArrayList<OrderJpa>();
		
		if(isTefapReport) {
			orders = orderDao.generateTefapReport(startDate, endDate);
		} else {
			orders = orderDao.generateOrderReport(startDate, endDate);
		}

		int familyTotal = orders.size();
		Number weightTotal = sum(orders, on(OrderJpa.class).getOrderWeight());
		Number totalAdults = sum(orders, on(OrderJpa.class).getCustomer().getNumOfAdults());
		Number totalKids = sum(orders, on(OrderJpa.class).getCustomer().getNumOfKids());
		Number totalTefapCount = sum(orders, on(OrderJpa.class).getTefapCount());
		reportDto.setTotalFamilies(familyTotal);
		reportDto.setTotalPounds(weightTotal.intValue());
		reportDto.setTotalAdults(totalAdults.intValue());
		reportDto.setTotalKids(totalKids.intValue());
		reportDto.setTotalTefapCount(totalTefapCount.longValue());

		//Determine Totals based on race
		Group<OrderJpa> groupedByEthnicity = group(orders, by(on(OrderJpa.class).getCustomer().getEthnicity()));			
		for(String race : groupedByEthnicity.keySet()){
			EthnicityReportDto ethnicityReport = new EthnicityReportDto();
			ethnicityReport.setEthnicity(race);

			List<OrderJpa> raceGroup = groupedByEthnicity.find(race);
			ethnicityReport.setTotal(raceGroup.size());
			reportDto.addRaceReports(ethnicityReport);
		}

		//Determine totals based of if they attend Bridgeway
		Group<OrderJpa> groupedByAttendee = group(orders, by(on(OrderJpa.class).getCustomer().getIsAttendee()));
		List<OrderJpa> nonBccAttendees = groupedByAttendee.find(0);
		List<OrderJpa> bccAttendees = groupedByAttendee.find(1);
		reportDto.setTotalBccAttendees(bccAttendees.size());
		reportDto.setTotalNonBccAttendees(nonBccAttendees.size());


		//Determine totals based on which service the customer attends
		Group<OrderJpa> groupedByService = group(orders, by(on(OrderJpa.class).getCustomer().getService()));			
		for(String service : groupedByService.keySet()) {
			BccServiceReportDto serviceDto = new BccServiceReportDto();
			List<OrderJpa> serviceGroup = groupedByService.find(service);
			serviceDto.setService(service);
			serviceDto.setTotal(serviceGroup.size());
			reportDto.addBccServiceReport(serviceDto);
		}

		//Converts JPAs to Beans
		List<OrderBean> beans = new ArrayList<OrderBean>();
		for(OrderJpa jpa : orders) {
			OrderBean bean = new OrderBean(jpa);
			beans.add(bean);
		}

		EntityWrapper<ReportDto> wrapper = new EntityWrapper<ReportDto>();
		List<ReportDto> reports = new ArrayList<ReportDto>();
		reports.add(reportDto);
		wrapper.setEntities(reports);
		wrapper.setResultCount(reports.size());
		return wrapper;
	}
	
	private Response createNewOrder(int customerId, String orderType) {
		ResponseBuilder rb = Response.status(Status.OK);

		try {
			CustomerJpa customer = customerDao.findById(customerId);

			// do not continue if we can't find the customer
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
