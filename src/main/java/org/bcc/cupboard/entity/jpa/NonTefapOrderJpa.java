package org.bcc.cupboard.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bcc.cupboard.entity.NonTefapOrder;

@Entity
@Table(name="NON_TEFAP_ORDER")
public class NonTefapOrderJpa extends OrderJpa implements NonTefapOrder {
	private static final long serialVersionUID = -1569793458052545970L;
	private int numOfBags;

	public NonTefapOrderJpa() {
		
	}
	
	public NonTefapOrderJpa(NonTefapOrder order) {
		super(order);
		setNumOfBags(order.getNumOfBags());
	}
	
	@Column(name="ORDER_BAGS")
	public int getNumOfBags() {
		return numOfBags;
	}
	
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}
}
