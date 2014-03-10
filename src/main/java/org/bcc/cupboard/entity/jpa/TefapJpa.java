package org.bcc.cupboard.entity.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bcc.cupboard.entity.Tefap;

@Entity
@Table(name="TEFAP")
public class TefapJpa implements Serializable, Tefap {
	private static final long serialVersionUID = -3618462910753933404L;
	
	private long id;
	private int weight;
	private int count;
	private Date orderDate;	
	private CustomerJpa customer;
	
	public TefapJpa() {
		
	}
	
	public TefapJpa(Tefap tefap) {
		setId(tefap.getId());
		setWeight(tefap.getWeight());
		setCount(tefap.getCount());
		setOrderDate(tefap.getOrderDate());
	}

	@Id
	@Column(name="TEFAP_NUM")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name="TEFAP_WEIGHT")
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Column(name="TEFAP_COUNT")
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Column(name="TEFAP_DATE")
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
