package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.Order;
import org.bcc.cupboard.entity.OrderBean;

@Entity
@Table(name="ORDER")
public class OrderJpa extends OrderBean implements Serializable, Order {
	private static final long serialVersionUID = 7643039787427721520L;		
	private List<Customer> customers;
	
	public OrderJpa() {
		
	}
	
	public OrderJpa(Order order) {
		setOrderNum(order.getOrderNum());
		setOrderDate(order.getOrderDate());
		setOrderWeight(order.getOrderWeight());
		setNumOfBags(order.getNumOfBags());
	}

	@Override
	@Column(name="ORDER_NUM")
	@GeneratedValue()
	public long getOrderNum() {
		return orderNum;
	}

	@Override
	public void setOrderNum(long orderNum) {
		super.setOrderNum(orderNum);
	}

	@Override
	@Column(name="ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	@Override
	public void setOrderDate(Date orderDate) {
		super.setOrderDate(orderDate);
	}

	@Override
	@Column(name="ORDER_WEIGHT")
	public int getOrderWeight() {
		return orderWeight;
	}

	@Override
	public void setOrderWeight(int orderWeight) {
		super.setOrderWeight(orderWeight);
	}	

	@Override
	@Column(name="ORDER_BAGS")
	public int getNumOfBags() {
		return numOfBags;
	}

	@Override
	public void setNumOfBags(int numOfBags) {
		super.setNumOfBags(numOfBags);
	}
	
	@ManyToOne(targetEntity=CustomerJpa.class)
	@JoinColumn(name="CUS_NUM")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
