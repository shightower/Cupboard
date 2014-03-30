package org.bcc.cupboard.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TefapBean implements Tefap {
	private static final long serialVersionUID = 5972164685859836735L;
	private long id;
	private int weight;
	private int count;
	private Date orderDate;	
	
	public TefapBean() {
		
	}
	
	public TefapBean(Tefap tefap) {
		setId(tefap.getId());
		setWeight(tefap.getWeight());
		setCount(tefap.getCount());
		setOrderDate(tefap.getOrderDate());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
