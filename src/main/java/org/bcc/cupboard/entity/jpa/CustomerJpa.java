package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.Order;
import org.bcc.cupboard.entity.Tefap;

@Entity
@Table(name = "CUSTOMER")
public class CustomerJpa implements Serializable, Customer {
	private static final long serialVersionUID = 5808450971871741014L;
	
	
	@Column(name="CUS_NUM")
	@GeneratedValue()
	private long id;
	
	@Column(name="CUS_LAST")
	private String lastName;
	
	@Column(name="CUS_FIRST")
	private String firstName;
	
	@Column(name="CUS_STREET")
	private String street;
	
	@Column(name="CUS_CITY")
	private String city;
	
	@Column(name="CUS_STATE")
	private String state;
	
	@Column(name="CUS_ZIP")
	private String zip;
	
	@Column(name="CUS_PHONE")
	private String phone;
	
	@Column(name="CUS_NUM_ADULT")
	private int numOfAdults;
	
	@Column(name="CUS_NUM_CHILD")
	private int numOfKids;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Order> orders;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Tefap> tefaps;
	
	public CustomerJpa() {
		
	}
	

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getStreet()
	 */
	@Override
	public String getStreet() {
		return street;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setStreet(java.lang.String)
	 */
	@Override
	public void setStreet(String street) {
		this.street = street;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getCity()
	 */
	@Override
	public String getCity() {
		return city;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setCity(java.lang.String)
	 */
	@Override
	public void setCity(String city) {
		this.city = city;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getState()
	 */
	@Override
	public String getState() {
		return state;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setState(java.lang.String)
	 */
	@Override
	public void setState(String state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getZip()
	 */
	@Override
	public String getZip() {
		return zip;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setZip(java.lang.String)
	 */
	@Override
	public void setZip(String zip) {
		this.zip = zip;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getPhone()
	 */
	@Override
	public String getPhone() {
		return phone;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setPhone(java.lang.String)
	 */
	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getNumOfAdults()
	 */
	@Override
	public int getNumOfAdults() {
		return numOfAdults;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setNumOfAdults(int)
	 */
	@Override
	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getNumOfKids()
	 */
	@Override
	public int getNumOfKids() {
		return numOfKids;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setNumOfKids(int)
	 */
	@Override
	public void setNumOfKids(int numOfKids) {
		this.numOfKids = numOfKids;
	}


	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getOrders()
	 */
	@Override
	public List<Order> getOrders() {
		return orders;
	}


	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setOrders(java.util.List)
	 */
	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#getTefaps()
	 */
	@Override
	public List<Tefap> getTefaps() {
		return tefaps;
	}


	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Customer#setTefaps(java.util.List)
	 */
	@Override
	public void setTefaps(List<Tefap> tefaps) {
		this.tefaps = tefaps;
	}	

}
