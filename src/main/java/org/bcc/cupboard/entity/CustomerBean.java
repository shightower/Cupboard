package org.bcc.cupboard.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerBean implements Customer {
	private static final long serialVersionUID = -5324479997469196416L;
	private long id;	
	private String lastName;	
	private String firstName;	
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private int numOfAdults;
	private int numOfKids;
	private int numOfBags;
	
	public CustomerBean() {
		
	}
	
	public CustomerBean(Customer customer) {
		setId(customer.getId());
		setFirstName(customer.getFirstName());
		setLastName(customer.getLastName());
		setStreet(customer.getStreet());
		setCity(customer.getCity());
		setState(customer.getState());
		setZip(customer.getZip());
		setPhoneNumber(customer.getPhoneNumber());
		setNumOfAdults(customer.getNumOfAdults());
		setNumOfKids(customer.getNumOfKids());
		setNumOfBags(customer.getNumOfBags());
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

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	public int getNumOfBags() {
		return numOfBags;
	}
	
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}
}
