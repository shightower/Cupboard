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
import org.bcc.cupboard.entity.TefapBean;

@Entity
@Table(name="TEFAP")
public class TefapJpa extends TefapBean implements Serializable, Tefap {
	private static final long serialVersionUID = -3618462910753933404L;
	private Customer customer;
	
	public TefapJpa() {
		
	}
	
	public TefapJpa(Tefap tefap) {
		setId(tefap.getId());
		setWeight(tefap.getWeight());
		setCount(tefap.getCount());
		setOrderDate(tefap.getOrderDate());
	}

	@Override
	@Column(name="TEFAP_NUM")
	@GeneratedValue()
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		super.setId(id);
	}

	@Override
	@Column(name="TEFAP_WEIGHT")
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		super.setWeight(weight);
	}

	@Override
	@Column(name="TEFAP_COUNT")
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int count) {
		super.setCount(count);
	}

	@Override
	@Column(name="TEFAP_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	@Override
	public void setOrderDate(Date orderDate) {
		super.setOrderDate(orderDate);
	}

	@ManyToOne(targetEntity=CustomerJpa.class, fetch=FetchType.LAZY)
	@JoinColumn(name="CUS_NUM")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
