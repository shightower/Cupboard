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
	
	@GET
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response addCustomer(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("numOfKids")  String numOfKids,
			@QueryParam("numOfAdults") String numOfAdults,
			@QueryParam("phoneNumber")  String phoneNumber,
			@QueryParam("ethnicity") String ethnicity,
			@QueryParam("attendee") int attendee,
			@QueryParam("service") String service) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = new CustomerJpa();
			customerJpa.setFirstName(firstName);
			customerJpa.setLastName(lastName);
			customerJpa.setStreet(street);
			customerJpa.setCity(city);
			customerJpa.setState(state);
			customerJpa.setZip(zip);
			customerJpa.setNumOfAdults(Integer.valueOf(numOfAdults));
			customerJpa.setNumOfKids(Integer.valueOf(numOfKids));
			
			if(!phoneNumber.contains("-")) {
				StringBuilder sb = new StringBuilder(phoneNumber);
				sb.insert(3, "-");
				sb.insert(7, "-");
			}
			
			customerJpa.setPhoneNumber(phoneNumber);
			customerJpa.setEthnicity(ethnicity);
			customerJpa.setIsAttendee(attendee);
			
			if(0 == attendee) {
				customerJpa.setService("N/A");
			} else {
				customerJpa.setService(service);
			}
			
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
	@Path("search/name")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response getCustomerByName(
			@QueryParam("firstName") @DefaultValue("") String firstName,
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
	
	@GET
	@Path("search")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response getCustomerById(@QueryParam("id") long id) {
		ResponseBuilder rb = Response.status(Status.OK);
		EntityWrapper<CustomerBean> wrapper = new EntityWrapper<CustomerBean>();
		List<CustomerBean> customers = new ArrayList<CustomerBean>();
		
		Customer customer = customerDao.findById(id);
		
		if(customer != null) {
			CustomerBean bean = new CustomerBean(customer);
			customers.add(bean);
		}
		
		wrapper.setEntities(customers);
		wrapper.setResultCount(customers.size());
		rb.entity(wrapper);
		return rb.build();		
	}
	
	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response getCustomerById() {
		ResponseBuilder rb = Response.status(Status.OK);
		EntityWrapper<CustomerBean> wrapper = new EntityWrapper<CustomerBean>();
		List<CustomerBean> customers = new ArrayList<CustomerBean>();
		
		List<CustomerJpa> found = customerDao.findAll();
		
		for(CustomerJpa customer : found) {
			CustomerBean bean = new CustomerBean(customer);
			customers.add(bean);
		}
		
		wrapper.setEntities(customers);
		wrapper.setResultCount(customers.size());
		rb.entity(wrapper);
		return rb.build();		
	}
	
	@GET
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response updateCustomer(
			@QueryParam("id") @DefaultValue("0") String id,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("numOfKids") String numOfKids,
			@QueryParam("numOfAdults") String numOfAdults,
			@QueryParam("phoneNumber") String phoneNumber,
			@QueryParam("ethnicity") String ethnicity,
			@QueryParam("isAttendee") int attendee,
			@QueryParam("service") String service) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = customerDao.findById(Integer.parseInt(id));
			
			if(customerJpa != null) {
				customerJpa.setFirstName(firstName);
				customerJpa.setLastName(lastName);
				customerJpa.setStreet(street);
				customerJpa.setCity(city);
				customerJpa.setState(state);
				customerJpa.setZip(zip);
				customerJpa.setNumOfAdults(Integer.valueOf(numOfAdults));
				customerJpa.setNumOfKids(Integer.valueOf(numOfKids));
				
				if(!phoneNumber.contains("-")) {
					StringBuilder sb = new StringBuilder(phoneNumber);
					sb.insert(3, "-");
					sb.insert(7, "-");
				}
				
				customerJpa.setPhoneNumber(phoneNumber);
				customerJpa.setEthnicity(ethnicity);
				customerJpa.setIsAttendee(attendee);
				customerJpa.setService(service);
				
				customerDao.update(customerJpa);
				rb.entity("Successfully Added New Customer");
			} else {
				Log.debug("Unable to locate customer to update, Id provided was " + id);
				rb = Response.status(Status.BAD_REQUEST);
				rb.entity("Unable to update Customer, notify Customer Support");
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
	public Response removeCustomer(
			@QueryParam("id") @DefaultValue("0") int id) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		try {
			CustomerJpa customerJpa = customerDao.findById(id);
			
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