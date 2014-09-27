package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.bcc.cupboard.entity.Order;

@Entity
@Table(name="ORDERS")
public class OrderJpa implements Serializable, Order {
	private static final long serialVersionUID = 7643039787427721520L;

	@Id
	@Column(name="ORDER_NUM")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long orderNum;

	@Column(name="TEFAP_COUNT", nullable = false)
	private int tefapCount;	

	@Column(name="ORDER_DATE", nullable = false)
	private Date orderDate;
	
	@Column(name="ORDER_WEIGHT", nullable = false)
	private int orderWeight;
	
	@Column(name="ORDER_BAGS", nullable = false)
	private int numOfBags;
	
	@Column(name="PENDING_ORDER", nullable = false)
	private int isPending;
	
	@Column(name="ORDER_TYPE", nullable = false)
	private String orderType;

	@ManyToOne(targetEntity=CustomerJpa.class, fetch=FetchType.EAGER)
	@JoinColumn(name="CUS_NUM", nullable = false)
	private CustomerJpa customer;
	
	public OrderJpa() {
		
	}
	
	public OrderJpa(Order order) {
		setOrderNumber(order.getOrderNumber());
		setOrderDate(order.getOrderDate());
		setOrderWeight(order.getOrderWeight());
		setTefapCount(order.getTefapCount());
	}

	public long getOrderNumber() {
		return orderNum;
	}
	
	public void setOrderNumber(long orderNum) {
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

	public int getTefapCount() {
		return tefapCount;
	}
	
	public void setTefapCount(int count) {
		this.tefapCount = count;
	}

	public int getNumOfBags() {
		return numOfBags;
	}
	
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}

	public CustomerJpa getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerJpa customer) {
		this.customer = customer;
	}

	public int getIsPending() {
		return isPending;
	}
	
	public void setIsPending(int isPending) {
		this.isPending = isPending;
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Transient
	public boolean isPending() {
		boolean pending = false;
		
		if(isPending == 1) {
			pending = true;
		}
		
		return pending;
	}	
	
	@Transient
	public boolean isTefap() {
		boolean isTefap = false;
		if(orderType.equalsIgnoreCase("tefap")) {
			isTefap = true;
		}
		
		return isTefap;
	}
}
