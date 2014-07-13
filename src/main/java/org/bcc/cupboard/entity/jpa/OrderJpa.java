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

	private int tefapCount;
	private long orderNum;
	private Date orderDate;
	private int orderWeight;
	private int numOfBags;
	private int isPending;
	private String orderType;
	private CustomerJpa customer;
	
	public OrderJpa() {
		
	}
	
	public OrderJpa(Order order) {
		setOrderNum(order.getOrderNum());
		setOrderDate(order.getOrderDate());
		setOrderWeight(order.getOrderWeight());
		setTefapCount(order.getTefapCount());
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

	@Column(name="ORDER_DATE", nullable = false)
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name="ORDER_WEIGHT", nullable = false)
	public int getOrderWeight() {
		return orderWeight;
	}
	
	public void setOrderWeight(int orderWeight) {
		this.orderWeight = orderWeight;
	}

	@Column(name="TEFAP_COUNT", nullable = false)
	public int getTefapCount() {
		return tefapCount;
	}
	
	public void setTefapCount(int count) {
		this.tefapCount = count;
	}
	
	@Column(name="ORDER_BAGS", nullable = false)
	public int getNumOfBags() {
		return numOfBags;
	}
	
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}
	
	@ManyToOne(targetEntity=CustomerJpa.class, fetch=FetchType.EAGER)
	@JoinColumn(name="CUS_NUM")
	public CustomerJpa getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerJpa customer) {
		this.customer = customer;
	}

	@Column(name="PENDING_ORDER")
	public int getIsPending() {
		return isPending;
	}
	
	public void setIsPending(int isPending) {
		this.isPending = isPending;
	}
	
	@Transient
	public boolean isPending() {
		boolean pending = false;
		
		if(isPending == 1) {
			pending = true;
		}
		
		return pending;
	}
	
	@Column(name="ORDER_TYPE")
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
