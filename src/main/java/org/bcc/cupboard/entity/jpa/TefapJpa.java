package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bcc.cupboard.entity.Customer;
import org.bcc.cupboard.entity.Tefap;

@Entity
@Table(name="TEFAP")
public class TefapJpa implements Serializable, Tefap {
	private static final long serialVersionUID = -3618462910753933404L;

	@Column(name="TEFAP_NUM")
	@GeneratedValue()
	private long id;
	
	@Column(name="TEFAP_WEIGHT")
	private int weight;
	
	@Column(name="TEFAP_COUNT")
	private int count;
	
	@Column(name="TEFAP_DATE")
	private Date orderDate;
	
	@ManyToOne(targetEntity=CustomerJpa.class, fetch=FetchType.LAZY)
	@JoinColumn(name="CUS_NUM")
	private Customer customer;
	
	public TefapJpa() {
		
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getWeight()
	 */
	@Override
	public int getWeight() {
		return weight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setWeight(int)
	 */
	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setCount(int)
	 */
	@Override
	public void setCount(int count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getOrderDate()
	 */
	@Override
	public Date getOrderDate() {
		return orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setOrderDate(java.sql.Date)
	 */
	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getCustomer()
	 */
	@Override
	public Customer getCustomer() {
		return customer;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setCustomer(org.bcc.cupboard.entity.jpa.Customer)
	 */
	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
