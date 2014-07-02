package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
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
	
	private long id;	
	private String lastName;	
	private String firstName;	
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private int numOfAdults;
	private int numOfKids;
	private Set<NonTefapOrderJpa> orders;
	private Set<TefapJpa> tefaps;
	
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
	}
	
	@Id
	@Column(name="CUS_NUM")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name="CUS_LAST", nullable = false)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="CUS_FIRST", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="CUS_STREET")
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name="CUS_CITY")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="CUS_STATE")
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	@Column(name="CUS_ZIP")
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name="CUS_PHONE")
	public String getPhoneNumber() {
		return phone;
	}
	
	public void setPhoneNumber(String phone) {
		this.phone = phone;
	}

	@Column(name="CUS_NUM_ADULT", nullable = false)
	public int getNumOfAdults() {
		return numOfAdults;
	}
	
	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}

	@Column(name="CUS_NUM_CHILD", nullable = false)
	public int getNumOfKids() {
		return numOfKids;
	}
	
	public void setNumOfKids(int numOfKids) {
		this.numOfKids = numOfKids;
	}

	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<NonTefapOrderJpa> getOrders() {
		return orders;
	}

	public void setOrders(Set<NonTefapOrderJpa> orders) {
		this.orders = orders;
	}

	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<TefapJpa> getTefaps() {
		return tefaps;
	}

	public void setTefaps(Set<TefapJpa> tefaps) {
		this.tefaps = tefaps;
	}	
}
