package org.bcc.cupboard.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bcc.cupboard.entity.Tefap;

@Entity
@Table(name="TEFAP")
public class TefapJpa extends OrderJpa implements Tefap {
	private static final long serialVersionUID = -3618462910753933404L;
	
	private int count;
	
	public TefapJpa() {
		
	}
	
	public TefapJpa(Tefap tefap) {
		setCount(tefap.getCount());
		setOrderDate(tefap.getOrderDate());
	}

	@Column(name="TEFAP_COUNT")
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}	
}
