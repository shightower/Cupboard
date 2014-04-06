package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.bcc.cupboard.entity.Order;

@MappedSuperclass
public class OrderJpa implements Serializable, Order {
	private static final long serialVersionUID = 7643039787427721520L;
	
	private long orderNum;
	private Date orderDate;
	private int orderWeight;
	private CustomerJpa customer;
	
	public OrderJpa() {
		
	}
	
	public OrderJpa(Order order) {
		setOrderNum(order.getOrderNum());
		setOrderDate(order.getOrderDate());
		setOrderWeight(order.getOrderWeight());
	}

	@Id
	@Column(name="ORDER_NUM")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name="ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name="ORDER_WEIGHT")
	public int getOrderWeight() {
		return orderWeight;
	}
	
	public void setOrderWeight(int orderWeight) {
		this.orderWeight = orderWeight;
	}	
	
	@ManyToOne(targetEntity=CustomerJpa.class, fetch=FetchType.LAZY)
	@JoinColumn(name="CUS_NUM")
	public CustomerJpa getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerJpa customer) {
		this.customer = customer;
	}

}
