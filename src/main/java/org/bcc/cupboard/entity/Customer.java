package org.bcc.cupboard.entity;

import java.io.Serializable;


public interface Customer extends Serializable {

	public abstract long getId();
	public abstract void setId(long id);
	public abstract String getLastName();
	public abstract void setLastName(String lastName);
	public abstract String getFirstName();
	public abstract void setFirstName(String firstName);
	public abstract String getStreet();
	public abstract void setStreet(String street);
	public abstract String getCity();
	public abstract void setCity(String city);
	public abstract String getState();
	public abstract void setState(String state);
	public abstract String getZip();
	public abstract void setZip(String zip);
	public abstract String getPhoneNumber();
	public abstract void setPhoneNumber(String phone);
	public abstract int getNumOfAdults();
	public abstract void setNumOfAdults(int numOfAdults);
	public abstract int getNumOfKids();
	public abstract void setNumOfKids(int numOfKids);
}