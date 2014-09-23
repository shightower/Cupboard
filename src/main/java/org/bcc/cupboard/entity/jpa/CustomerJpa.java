package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bcc.cupboard.entity.Customer;

@Entity
@Table(name = "CUSTOMER")
public class CustomerJpa implements Serializable, Customer {
	private static final long serialVersionUID = 5808450971871741014L;
	
	@Id
	@Column(name="CUS_NUM")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;	

	@Column(name="CUS_LAST", nullable = false)
	private String lastName;	
	
	@Column(name="CUS_FIRST", nullable = false)
	private String firstName;	
	
	@Column(name="CUS_STREET", nullable = false)
	private String street;	
	
	@Column(name="CUS_CITY", nullable = false)
	private String city;	
	
	@Column(name="CUS_STATE", nullable = false)
	private String state;	
	
	@Column(name="CUS_ZIP", nullable = false)
	private String zip;	
	
	@Column(name="CUS_PHONE")
	private String phoneNumber;	
	
	@Column(name="CUS_NUM_ADULT", nullable = false)
	private int numOfAdults;	
	
	@Column(name="CUS_NUM_CHILD", nullable = false)
	private int numOfKids;	
	
	@Column(name="LAST_ORDER_DATE")
	private Date lastOrderDate;	
	
	@Column(name="NEXT_AVAILABLE_DATE")
	private Date nextAvailableDate;	
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<OrderJpa> orders;	
	
	@Column(name="ETHNICITY", nullable = false)
	private String ethnicity;
	
	@Column(name="Service")
	private String service;	

	@Column(name="IS_ATTENDEE")
	private int isAttendee;
	
	public CustomerJpa() {
		
	}
	
	public CustomerJpa(Customer customer) {
		setId(customer.getId());
		setFirstName(customer.getFirstName());
		setLastName(customer.getLastName());
		setNumOfAdults(customer.getNumOfAdults());
		setNumOfKids(customer.getNumOfKids());
		setPhoneNumber(customer.getPhoneNumber());
		setStreet(customer.getStreet());
		setCity(customer.getCity());
		setState(customer.getState());
		setZip(customer.getZip());
		setLastOrderDate(customer.getLastOrderDate());
		setNextAvailableDate(customer.getNextAvailableDate());
		setEthnicity(customer.getEthnicity());
		setService(customer.getService());
		setIsAttendee(customer.getIsAttendee());
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}

	public int getNumOfAdults() {
		return numOfAdults;
	}
	
	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}

	public int getNumOfKids() {
		return numOfKids;
	}
	
	public void setNumOfKids(int numOfKids) {
		this.numOfKids = numOfKids;
	}

	public Date getLastOrderDate() {
		return lastOrderDate;
	}

	public void setLastOrderDate(Date lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}

	public Date getNextAvailableDate() {
		return nextAvailableDate;
	}

	public void setNextAvailableDate(Date nextAvailableDate) {
		this.nextAvailableDate = nextAvailableDate;
	}

	public Set<OrderJpa> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderJpa> orders) {
		this.orders = orders;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getIsAttendee() {
		return isAttendee;
	}

	public void setIsAttendee(int isAttendee) {
		this.isAttendee = isAttendee;
	}
}
