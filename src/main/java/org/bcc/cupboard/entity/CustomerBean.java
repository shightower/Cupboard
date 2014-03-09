package org.bcc.cupboard.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerBean implements Customer {
	
	protected long id;	
	protected String lastName;	
	protected String firstName;	
	protected String street;
	protected String city;
	protected String state;
	protected String zip;
	protected String phone;
	protected int numOfAdults;
	protected int numOfKids;

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
}
