package org.bcc.cupboard.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderBean implements Order {
	private static final long serialVersionUID = -5893472014559954025L;
	private long orderNum;
	private Date orderDate;
	private int orderWeight;
	private int numOfBags;
	
	public OrderBean() {
		
	}
	
	public OrderBean(Order order) {
		setOrderNum(order.getOrderNum());
		setOrderDate(order.getOrderDate());
		setOrderWeight(order.getOrderWeight());
		setNumOfBags(order.getNumOfBags());
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderWeight() {
		return orderWeight;
	}

	public void setOrderWeight(int orderWeight) {
		this.orderWeight = orderWeight;
	}

	public int getNumOfBags() {
		return numOfBags;
	}

	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}
}
