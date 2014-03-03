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

@Entity
@Table(name="ORDER")
public class OrderJpa implements Serializable, Order {
	private static final long serialVersionUID = 7643039787427721520L;
	
	@Column(name="ORDER_NUM")
	@GeneratedValue()
	private long orderNum;
	
	@Column(name="ORDER_DATE")
	private Date orderDate;
	
	@Column(name="ORDER_WEIGHT")
	private int orderWeight;
	
	@Column(name="ORDER_BAGS")
	private int numOfBags;
	
	@ManyToOne(targetEntity=CustomerJpa.class)
	@JoinColumn(name="CUS_NUM")
	private List<Customer> customers;
	
	public OrderJpa() {
		
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getOrderNum()
	 */
	@Override
	public long getOrderNum() {
		return orderNum;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setOrderNum(long)
	 */
	@Override
	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getOrderDate()
	 */
	@Override
	public Date getOrderDate() {
		return orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setOrderDate(java.sql.Date)
	 */
	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getOrderWeight()
	 */
	@Override
	public int getOrderWeight() {
		return orderWeight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setOrderWeight(int)
	 */
	@Override
	public void setOrderWeight(int orderWeight) {
		this.orderWeight = orderWeight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getCustomers()
	 */
	@Override
	public List<Customer> getCustomers() {
		return customers;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setCustomers(java.util.List)
	 */
	@Override
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getNumOfBags()
	 */
	@Override
	public int getNumOfBags() {
		return numOfBags;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setNumOfBags(int)
	 */
	@Override
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}

}
